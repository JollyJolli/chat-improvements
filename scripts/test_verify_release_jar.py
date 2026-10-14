"""Unit tests for the distribution JAR verifier using tiny ZIP fixtures."""

import json
import tempfile
import unittest
from pathlib import Path
from zipfile import ZipFile

from verify_release_jar import LOCALES, LOCALE_DIR, validate_jar


class ReleaseJarValidationTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory()
        self.jar = Path(self.temp.name) / "test.jar"
        self.meta = {
            "id": "chatimprovements",
            "version": "0.4.0-rc.2",
            "environment": "client",
            "depends": {
                "minecraft": "~26.2",
                "java": ">=25",
                "fabric-api": "*",
                "fabricloader": ">=0.19.5",
            },
            "entrypoints": {"client": ["example.Mod"]},
        }

    def tearDown(self):
        self.temp.cleanup()

    def build(self, omit=(), translations=None):
        if translations is None:
            translations = {name: '{"label":"hello"}' for name in LOCALES}
        with ZipFile(self.jar, "w") as archive:
            if "fabric.mod.json" not in omit:
                archive.writestr("fabric.mod.json", json.dumps(self.meta))
            for lang, value in translations.items():
                if lang not in omit:
                    archive.writestr(f"{LOCALE_DIR}{lang}.json", value)

    def check(self):
        return validate_jar(self.jar, "0.4.0-rc.2", "26.2")

    def test_valid_fabric_archive(self):
        self.build()
        self.assertEqual([], self.check())

    def test_wrong_mod_version(self):
        self.meta["version"] = "0.3"
        self.build()
        self.assertTrue(any("version" in value for value in self.check()))

    def test_missing_metadata(self):
        self.build(omit=("fabric.mod.json",))
        self.assertTrue(any("Missing fabric.mod.json" in value for value in self.check()))

    def test_missing_language(self):
        self.build(omit=("pt_br",))
        self.assertTrue(any("pt_br" in value for value in self.check()))

    def test_invalid_language_json(self):
        self.build(translations={name: "{" if name == "de_de" else "{}" for name in LOCALES})
        self.assertTrue(any("de_de" in value for value in self.check()))

    def test_wrong_mc_version(self):
        self.meta["depends"]["minecraft"] = "~1.20.1"
        self.build()
        self.assertTrue(any("Minecraft compatibility" in value for value in self.check()))

    def test_bad_archive(self):
        self.jar.write_text("not a jar")
        self.assertTrue(any("Unreadable installable JAR" in value for value in self.check()))


if __name__ == "__main__":
    unittest.main()
