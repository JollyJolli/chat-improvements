package io.github.jollyjolli.chatimprovements;

/** Four original palettes for the client's settings screen. */
public final class UiPalette {
    private static final int[] ACCENTS = {0xFF55E6DE, 0xFFA994FF, 0xFFFFB85E, 0xFF91E788};
    private static final int[] DEEP = {0xFF153D45, 0xFF33264A, 0xFF47301D, 0xFF293D2A};
    public static final int TEXT = 0xFFF1F6FF;
    public static final int MUTED = 0xFFB5C0D0;
    public static final int WARNING = 0xFFFFB85E;
    private UiPalette() { }
    public static int index() { return Math.floorMod(ChatImprovementsConfig.get().uiAccentTheme, 4); }
    public static int accent() { return ACCENTS[index()]; }
    public static int accent(int index) { return ACCENTS[Math.floorMod(index, 4)]; }
    public static int deep() { return DEEP[index()]; }
}
