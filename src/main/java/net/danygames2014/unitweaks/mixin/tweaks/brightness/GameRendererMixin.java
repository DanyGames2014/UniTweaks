package net.danygames2014.unitweaks.mixin.tweaks.brightness;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.danygames2014.unitweaks.util.PostProcess;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Material;
import net.minecraft.class_555;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.FloatBuffer;

@Environment(EnvType.CLIENT)
@Mixin(class_555.class)
public abstract class GameRendererMixin {

    @Shadow
    private float field_2350;

    @Shadow private Minecraft field_2349;

    @Shadow private FloatBuffer field_2345;

    @Shadow private float field_2346;

    @Shadow private float field_2347;

    @Shadow private float field_2348;

    @Shadow private boolean field_2330;

    @Inject(
            method = "method_1852",
            at = @At("HEAD"),
            cancellable = true
    )
    private void clientsideEssentials_method_1852(float f, CallbackInfo ci) {
        if (  (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_SLIDER)
           && (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_FOG)
        ) {
            World level = this.field_2349.world;
            LivingEntity living = this.field_2349.field_2807;
            PostProcess pp = PostProcess.instance;
            Vec3d vec3f = level.method_279(this.field_2349.field_2807, f);
            float red = (float) vec3f.x;
            float green = (float) vec3f.y;
            float blue = (float) vec3f.z;
            if (this.field_2330) {
                Vec3d vec3f3 = level.method_282(f);
                this.field_2346 = (float) vec3f3.x;
                this.field_2347 = (float) vec3f3.y;
                this.field_2348 = (float) vec3f3.z;
                red = this.field_2346;
                green = this.field_2347;
                blue = this.field_2348;
            } else if (living.isInFluid(Material.WATER)) {
                this.field_2346 = 0.02f;
                this.field_2347 = 0.02f;
                this.field_2348 = 0.2f;
                red = this.field_2346;
                green = this.field_2347;
                blue = this.field_2348;
            } else if (living.isInFluid(Material.LAVA)) {
                this.field_2346 = 0.6f;
                this.field_2347 = 0.1f;
                this.field_2348 = 0.0f;
                red = this.field_2346;
                green = this.field_2347;
                blue = this.field_2348;
            }
            if (0.0F != ModOptions.fogDensity) {
                red = this.field_2346;
                green = this.field_2347;
                blue = this.field_2348;
            }
            GL11.glClearColor(pp.red(red, green, blue), pp.green(red, green, blue), pp.blue(red, green, blue), 0.0f);
            ci.cancel();
        }
    }

    @Inject(
            method = "method_1839",
            at = @At("HEAD"),
            cancellable = true
    )
    private void clientsideEssentials_method_1839(float red, float green, float blue, float i, CallbackInfoReturnable<FloatBuffer> cir) {
        if (  (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_SLIDER)
           && (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_FOG)
        ) {
            PostProcess pp = PostProcess.instance;
            this.field_2345.clear();
            this.field_2345.put(pp.red(red, green, blue)).put(pp.green(red, green, blue)).put(pp.blue(red, green, blue)).put(i);
            this.field_2345.flip();
            cir.setReturnValue(this.field_2345);
        }
    }
}
