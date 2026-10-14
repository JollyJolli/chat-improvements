"""Regression tests for the CI localization validator (no extra dependencies)."""

import json
import tempfile
import unittest
from pathlib import Path

from check_translations import check


class TranslationCheckTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        self.dir = Path(self.temp.name)
        self.write("en_us", {"one": "Hello %s", "two": "Ready"})

    def tearDown(self):
        self.temp.cleanup()

    def write(self, locale, strings):
        (self.dir / f"{locale}.json").write_text(
            json.dumps(strings), encoding="utf-8"
        )

    def test_matching_translations(self):
        self.write("de_de", {"one": "Hallo %s", "two": "Fertig"})
        self.assertEqual([], check(self.dir))

    def test_missing_key(self):
        self.write("de_de", {"one": "Hallo %s"})
        self.assertTrue(any("missing key two" in item for item in check(self.dir)))

    def test_extra_key(self):
        self.write("de_de", {"one": "Hallo %s", "two": "Fertig", "extra": "X"})
        self.assertTrue(any("unexpected key extra" in item for item in check(self.dir)))

    def test_placeholders(self):
        self.write("de_de", {"one": "Hallo", "two": "Fertig"})
        self.assertTrue(any("placeholders differ" in item for item in check(self.dir)))

    def test_duplicate_keys(self):
        (self.dir / "de_de.json").write_text(
            '{"one":"Hallo %s","one":"Hallo %s","two":"Fertig"}', encoding="utf-8"
        )
        self.assertTrue(any("Duplicate translation key" in item for item in check(self.dir)))

    def test_invalid_json(self):
        (self.dir / "de_de.json").write_text("{bad json", encoding="utf-8")
        self.assertTrue(check(self.dir))

    def test_empty_string(self):
        self.write("de_de", {"one": "Hallo %s", "two": " "})
        self.assertTrue(any("nonempty string" in item for item in check(self.dir)))


if __name__ == "__main__":
    unittest.main()
