package net.danygames2014.unitweaks.tweaks.frontviewthirdperson;

public enum FrontViewMode {
    DISABLED("Disabled"),
    NORMAL("Normal"),
    HIDE_HUD("Hide HUD");

    final String displayName;
    
    FrontViewMode(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
