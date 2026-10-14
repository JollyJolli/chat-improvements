#!/usr/bin/env python3
"""Validate Minecraft language JSON bundles using only Python's standard library."""

import json
import re
import sys
from collections import Counter
from pathlib import Path

LANG_DIR = Path(__file__).resolve().parents[1] / "src/client/resources/assets/chatimprovements/lang"
PLACEHOLDER = re.compile(r"%(?:\d+\$)?[sd]")


def load(path: Path) -> dict[str, str]:
    def unique_keys(pairs):
        result = {}
        for key, value in pairs:
            if key in result:
                raise ValueError(f"Duplicate translation key: {key!r}")
            result[key] = value
        return result

    data = json.loads(path.read_text(encoding="utf-8"), object_pairs_hook=unique_keys)
    if not isinstance(data, dict) or not all(
        isinstance(key, str) and isinstance(value, str) and value.strip()
        for key, value in data.items()
    ):
        raise ValueError("Translations must be a JSON object of nonempty string values")
    return data


def check(directory: Path) -> list[str]:
    errors = []
    english = directory / "en_us.json"
    if not english.exists():
        return ["Missing base language en_us.json"]
    try:
        baseline = load(english)
    except (ValueError, OSError) as error:
        return [f"{english.name}: {error}"]

    for path in sorted(directory.glob("*.json")):
        try:
            strings = load(path)
        except (ValueError, OSError) as error:
            errors.append(f"{path.name}: {error}")
            continue

        for key in sorted(baseline.keys() - strings.keys()):
            errors.append(f"{path.name}: missing key {key}")
        for key in sorted(strings.keys() - baseline.keys()):
            errors.append(f"{path.name}: unexpected key {key}")
        for key in sorted(baseline.keys() & strings.keys()):
            expected = Counter(PLACEHOLDER.findall(baseline[key]))
            actual = Counter(PLACEHOLDER.findall(strings[key]))
            if expected != actual:
                errors.append(
                    f"{path.name}: placeholders differ for {key}: "
                    f"expected {dict(expected)}, got {dict(actual)}"
                )
    return errors


if __name__ == "__main__":
    problems = check(LANG_DIR)
    if problems:
        for problem in problems:
            print(f"ERROR: {problem}", file=sys.stderr)
        sys.exit(1)
    print(f"Translations OK: {len(list(LANG_DIR.glob('*.json')))} locales checked")
