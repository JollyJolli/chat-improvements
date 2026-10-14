package io.github.jollyjolli.chatimprovements;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ChatNameRulesTest {
    @Test
    void matchesWholeNamesIgnoringCase() {
        assertTrue(ChatNameRules.containsWholeName("Hi JollyJolli!", "jollyjolli"));
        assertTrue(ChatNameRules.containsWholeName("@JOLLYJOLLI your turn", "JollyJolli"));
        assertTrue(ChatNameRules.containsWholeName("<JollyJolli> ready", "JollyJolli"));
        assertTrue(ChatNameRules.containsWholeName("a JollyJolli? yes", "JollyJolli"));
    }

    @Test
    void rejectsSubstringsOfLongerUsernames() {
        assertFalse(ChatNameRules.containsWholeName("BestJollyJolli", "JollyJolli"));
        assertFalse(ChatNameRules.containsWholeName("JollyJolli2", "JollyJolli"));
        assertFalse(ChatNameRules.containsWholeName("JollyJolli_more", "JollyJolli"));
        assertFalse(ChatNameRules.containsWholeName("nojollyjolli_here", "JollyJolli"));
    }

    @Test
    void rejectsEmptyOrInvalidNames() {
        assertFalse(ChatNameRules.containsWholeName(null, "Steve"));
        assertFalse(ChatNameRules.containsWholeName("Steve", null));
        assertFalse(ChatNameRules.containsWholeName("Steve", ""));
        assertFalse(ChatNameRules.containsWholeName("Steve", "Ste ve"));
        assertFalse(ChatNameRules.containsWholeName("Steve", "St"));
    }

    @Test
    void handlesUnderscoresNumbersAndUnicodeContext() {
        assertTrue(ChatNameRules.validPlayerName("_Steve_1"));
        assertTrue(ChatNameRules.containsWholeName("Hola, _Steve_1!", "_Steve_1"));
        assertFalse(ChatNameRules.containsWholeName("_Steve_12", "_Steve_1"));
        assertTrue(ChatNameRules.containsWholeName("🙂 Steve!", "Steve"));
    }

    @Test
    void validatesMinecraftUsernameCharactersAndLength() {
        assertTrue(ChatNameRules.validPlayerName("abc"));
        assertTrue(ChatNameRules.validPlayerName("A123456789012345"));
        assertFalse(ChatNameRules.validPlayerName("ab"));
        assertFalse(ChatNameRules.validPlayerName("A1234567890123456"));
        assertFalse(ChatNameRules.validPlayerName("name.with.dot"));
        assertFalse(ChatNameRules.validPlayerName("niño"));
        assertFalse(ChatNameRules.validPlayerName(null));
    }
}
