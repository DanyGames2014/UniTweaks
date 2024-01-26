package net.danygames2014.unitweaks.mixin.bugfixes.mpentityphysicsfix;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


/**
 * @author calmilamsy
 * Ported from <a href="https://github.com/calmilamsy/MPEntityPhysicsFix/blob/master/src/main/java/net/glasslauncher/mpentityphysicsfix/mixin/MixinEntityBase.java">here</a>
 */
@Mixin(Entity.class)
public class EntityMixin {
    @Redirect(method = "move", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;x:D", opcode = Opcodes.PUTFIELD))
    private void fixX(Entity entity, double value) {
        if (!entity.world.isRemote || entity instanceof PlayerEntity || !(entity instanceof LivingEntity)) {
            entity.x = value;
        }
    }

    @Redirect(method = "move", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;y:D", opcode = Opcodes.PUTFIELD))
    private void fixY(Entity entity, double value) {
        if (!entity.world.isRemote || entity instanceof PlayerEntity || !(entity instanceof LivingEntity)) {
            entity.y = value;
        }
    }

    @Redirect(method = "move", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;z:D", opcode = Opcodes.PUTFIELD))
    private void fixZ(Entity entity, double value) {
        if (!entity.world.isRemote || entity instanceof PlayerEntity || !(entity instanceof LivingEntity)) {
            entity.z = value;
        }
    }
}
