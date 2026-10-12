# v0.3 integration preview (not a public release)

This branch combines independent PRs #12 (copy), #13 (mentions) and #14 (settings). It does NOT contain the experimental Player Heads PR #7.

## Manual test
1. Install Fabric 26.2 + Fabric API, remove all other Chat Improvements jars.
2. Download the **non-sources** jar from this preview branch's *successful* Actions build. Crash? attach latest.log/crash-reports.
3. Confirm timestamps still appear on vanilla/Paper chat.
4. Open `/chatimprovements settings`, toggle all five options, click Done; reopen to confirm labels. Restart Minecraft to check saved preferences.
5. With chat open, click the `[C]` glyph on an incoming message. Clipboard should contain visible message text WITHOUT the timestamp or copy glyph, unless the mention marker is enabled (known limitation); verify original link click actions still work.
6. Enable mentions, have a friend send a message containing your actual Minecraft username; verify `[@]` appears, and does not appear for embedded substrings.
7. Test long messages, chat scaling, scrollback, nicknames, different Paper chat formats, resource packs, Unicode.
8. Send no private chat contents as part of logs unless deliberately needed and redacted.

## Known limitations
- Copy glyph visible even when chat is closed; may be refined later.
- Mentions marker may appear in self-echo and quoted text; no signed sender verification.
- This branch does not fix player heads on large servers (#8).
- This is **CI compilation only** until real game validation.
