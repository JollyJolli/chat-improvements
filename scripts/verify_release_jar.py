#!/usr/bin/env python3
"""Validate the built, installable Fabric JAR before distributing a release candidate.

Only Python standard library; never execute JAR contents. This is packaging
validation, NOT an in-game compatibility test.
"""
import json
import sys
from pathlib import Path
from zipfile import BadZipFile, ZipFile

LOCALES = ("en_us", "es_es", "pt_br", "de_de")
LOCALE_DIR = "assets/chatimprovements/lang/"


def expected_versions(properties: Path) -> tuple[str, str]:
    values = {}
    for line in properties.read_text(encoding="utf-8").splitlines():
        line = line.strip()
        if not line or line.startswith("#") or "=" not in line:
            continue
        key, value = line.split("=", 1)
        values[key.strip()] = value.strip()
    return values["mod_version"], values["minecraft_version"]


def validate_jar(path: Path, version: str, minecraft: str) -> list[str]:
    problems = []
    try:
        with ZipFile(path) as archive:
            entries = set(archive.namelist())
            if "fabric.mod.json" not in entries:
                return ["Missing fabric.mod.json inside installable JAR"]
            metadata = json.loads(archive.read("fabric.mod.json").decode("utf-8"))
            for key, expected in (
                ("id", "chatimprovements"),
                ("version", version),
                ("environment", "client"),
            ):
                if metadata.get(key) != expected:
                    problems.append(f"fabric.mod.json {key}: expected {expected!r}, got {metadata.get(key)!r}")

            dependencies = metadata.get("depends", {})
            if not isinstance(dependencies, dict):
                problems.append("Fabric dependencies should be an object")
            else:
                if dependencies.get("minecraft") != f"~{minecraft}":
                    problems.append("Incorrect Minecraft compatibility in fabric.mod.json")
                for required in ("fabricloader", "fabric-api", "java"):
                    if required not in dependencies:
                        problems.append(f"Missing dependency: {required}")

            if not isinstance(metadata.get("entrypoints", {}).get("client"), list):
                problems.append("Missing client entrypoint list")

            for locale in LOCALES:
                name = f"{LOCALE_DIR}{locale}.json"
                if name not in entries:
                    problems.append(f"Missing bundled translation {name}")
                else:
                    try:
                        strings = json.loads(archive.read(name).decode("utf-8"))
                        if not isinstance(strings, dict) or not strings:
                            problems.append(f"Invalid or empty translation {name}")
                    except (UnicodeDecodeError, ValueError) as error:
                        problems.append(f"Unreadable translation {name}: {error}")
    except (OSError, BadZipFile, UnicodeDecodeError, ValueError) as error:
        problems.append(f"Unreadable installable JAR: {error}")
    return problems


def main() -> int:
    if len(sys.argv) != 2:
        print("Usage: python3 scripts/verify_release_jar.py <installable.jar>", file=sys.stderr)
        return 2
    repo = Path(__file__).resolve().parents[1]
    try:
        version, minecraft = expected_versions(repo / "gradle.properties")
        errors = validate_jar(Path(sys.argv[1]), version, minecraft)
    except (OSError, KeyError, ValueError) as error:
        errors = [f"Could not resolve expected versions: {error}"]
    for message in errors:
        print(f"ERROR: {message}", file=sys.stderr)
    if errors:
        return 1
    print(f"Verified Fabric mod metadata, version {version}, Minecraft {minecraft}, and {len(LOCALES)} locales")
    return 0


if __name__ == "__main__":
    sys.exit(main())
