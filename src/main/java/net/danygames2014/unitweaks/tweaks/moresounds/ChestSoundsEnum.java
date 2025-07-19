package net.danygames2014.unitweaks.tweaks.moresounds;

public enum ChestSoundsEnum {

    NONE("None", "", ""),
    MODERN("Modern", "unitweaks:random.chestopen", "unitweaks:random.chestclosed"),
    DOOR("Door", "random.door_open", "random.door_close");

    final String stringValue;
    final String openSound;
    final String closeSound;

    ChestSoundsEnum(String stringValue, String openSound, String closeSound) {
        this.stringValue = stringValue;
        this.openSound = openSound;
        this.closeSound = closeSound;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public String getOpenSound() {
        return openSound;
    }

    public String getCloseSound() {
        return closeSound;
    }
}