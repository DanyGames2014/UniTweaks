package net.danygames2014.unitweaks.mixin.tweaks.bedtweaks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlayerEntity.class)
public interface PlayerEntityAccessor {
    @Accessor("spawnPos")
    void setRespawnPos(Vec3i respawnPos);
}
