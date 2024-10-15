package net.danygames2014.unitweaks.mixin.tweaks.photomode;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.tweaks.photomode.PhotoModeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class class_555Mixin {
    @Shadow
    private Minecraft client;

    @Shadow
    private float viewDistance;

    // 1/13
    @WrapOperation(method = "applyCameraTransform", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isSleeping()Z"))
    public boolean test(LivingEntity instance, Operation<Boolean> original) {
        return original.call(instance) && !(this.client.currentScreen instanceof PhotoModeScreen);
    }

    // 2/13

    // 3/13

    // 4/13
    @WrapOperation(method = "applyCameraTransform", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;debugCamera:Z", ordinal = 2))
    public boolean test4(GameOptions instance, Operation<Boolean> original) {
        return this.client.currentScreen instanceof PhotoModeScreen || original.call(instance);
    }

    // 5/13
    @Inject(method = "applyCameraTransform", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", shift = At.Shift.BEFORE, ordinal = 4, remap = false))
    public void test5(float par1, CallbackInfo ci) {
        if (this.client.currentScreen instanceof PhotoModeScreen) {
            GL11.glRotatef(((PhotoModeScreen) this.client.currentScreen).tilt, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F + 90.0F * ((PhotoModeScreen) this.client.currentScreen).rotation, 0.0F, 1.0F, 0.0F);
        }
    }

    // 8/13
    @ModifyExpressionValue(method = "renderWorld", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/GameRenderer;zoom:D", ordinal = 0))
    public double test8_forceif(double original) {
        if (this.client.currentScreen instanceof PhotoModeScreen) {
            return 0.99D;
        }
        return original;
    }

    @WrapWithCondition(method = "renderWorld", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", ordinal = 1, remap = false))
    public boolean disableTranslateF(float x, float y, float z) {
        return !(this.client.currentScreen instanceof PhotoModeScreen);
    }

    @WrapWithCondition(method = "renderWorld", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glScaled(DDD)V", ordinal = 0, remap = false))
    public boolean disableScaled(double x, double y, double z) {
        return !(this.client.currentScreen instanceof PhotoModeScreen);
    }

    @WrapWithCondition(method = "renderWorld", at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/GLU;gluPerspective(FFFF)V", ordinal = 0, remap = false))
    public boolean disableGluPerspective(float fovy, float aspect, float zNear, float zFar) {
        return !(this.client.currentScreen instanceof PhotoModeScreen);
    }

    @Inject(method = "renderWorld", at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/GLU;gluPerspective(FFFF)V", ordinal = 0, shift = At.Shift.BEFORE, remap = false))
    public void test8(float i, int par2, CallbackInfo ci) {
        if (this.client.currentScreen instanceof PhotoModeScreen) {
            double var3 = Math.pow(2.0, ((PhotoModeScreen) this.client.currentScreen).zoom);
            GL11.glTranslatef(1.0F, 1.0F, 0.0F);
            GL11.glOrtho(
                    0.0,
                    (double) this.client.displayWidth / var3,
                    0.0,
                    (double) this.client.displayHeight / var3,
                    (double) this.viewDistance * -2.0,
                    (double) this.viewDistance * 2.0
            );
        }
    }

    // 10/13
    @WrapWithCondition(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;applyViewBobbing(F)V"))
    public boolean test10(GameRenderer instance, float v) {
        return !(this.client.currentScreen instanceof PhotoModeScreen);
    }

    // 11/13
    @WrapWithCondition(method = "renderFirstPersonHand", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;render(F)V"))
    public boolean test11(HeldItemRenderer instance, float v) {
        return !(this.client.currentScreen instanceof PhotoModeScreen);
    }

    // 12/13
//    @WrapOperation(method = "method_1842", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 3))
//    public void test12(int pname, float param, Operation<Void> original) {
//        if (field_2349.currentScreen instanceof PhotoModeScreen) {
//            original.call(pname, param * (((PhotoModeScreen) this.field_2349.currentScreen).fog));
//        }
//        original.call(pname, param);
//    }

    // Hide HUD
    @WrapWithCondition(method = "onFrameUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;render(FZII)V"))
    public boolean hideHudInPhotoMode(InGameHud instance, float eeny, boolean meeny, int miny, int moe){
        return !(this.client.currentScreen instanceof PhotoModeScreen);
    }

}
