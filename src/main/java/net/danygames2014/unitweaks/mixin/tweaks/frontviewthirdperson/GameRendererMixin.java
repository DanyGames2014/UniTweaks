package net.danygames2014.unitweaks.mixin.tweaks.frontviewthirdperson;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.tweaks.frontviewthirdperson.FrontViewMode;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow
    private Minecraft client;
    
    @Unique
    public Vec3d flipYAroundArbitraryY(Vec3d vec, double arbitraryY)
    {
        double reflectedY = arbitraryY + (arbitraryY - vec.y);
        return Vec3d.create(vec.x, reflectedY, vec.z);
    }
    
    @Unique
    public boolean isFrontViewEnabled() {
        return ModOptions.frontView || (UniTweaks.USER_INTERFACE_CONFIG.frontViewThirdPerson == FrontViewMode.HIDE_HUD && (client.options.thirdPerson && client.options.hideHud));
    }

    @WrapOperation(method = "applyCameraTransform", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/LivingEntity;yaw:F", ordinal = 1, opcode = Opcodes.GETFIELD))
    public float rayCastValueFix(LivingEntity instance, Operation<Float> original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.frontViewThirdPerson == FrontViewMode.DISABLED) {
            return original.call(instance);
        }

        if (isFrontViewEnabled()) {
            return original.call(instance) + 180F;
        }

        return original.call(instance);
    }

//    @ModifyVariable(method = "applyCameraTransform", at = @At("STORE"), ordinal = 2)
//    private float rayCastValueFix(float value)
//    {
//        return value + (isFrontViewEnabled() ? 180.F : 0.F);
//    }

    @Inject(method = "applyCameraTransform", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 6, remap = false))
    public void glRotateCameraFix(float tickDelta, CallbackInfo ci) {
        if (UniTweaks.USER_INTERFACE_CONFIG.frontViewThirdPerson == FrontViewMode.DISABLED) {
            return;
        }

        if (isFrontViewEnabled()) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        }
    }

    @WrapOperation(method = "applyCameraTransform", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;raycast(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/hit/HitResult;"))
    private HitResult weirdYUpsideDownRayCastFix(World instance, Vec3d start, Vec3d end, Operation<HitResult> original)
    {
        if (UniTweaks.USER_INTERFACE_CONFIG.frontViewThirdPerson == FrontViewMode.DISABLED) {
            return original.call(instance, start, end);
        }
        
        return instance.raycast(start, isFrontViewEnabled() ? flipYAroundArbitraryY(end, Minecraft.INSTANCE.camera.y) : end);
    }
}
