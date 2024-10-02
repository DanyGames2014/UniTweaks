package net.danygames2014.unitweaks.util.compat;

import static com.matthewperiut.babric_sprint.BabricSprint.lastMovementFovMultiplier;
import static com.matthewperiut.babric_sprint.BabricSprint.movementFovMultiplier;

public class SprintHelper {
    public static float modifyFOV(float input, float f) {
        input *= lastMovementFovMultiplier + (movementFovMultiplier - lastMovementFovMultiplier) * f;
        return input;
    }
}
