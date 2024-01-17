package net.danygames2014.unitweaks.mixin.bugfixes.bitdepthfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;create()V", ordinal = 0), require = 0)
    public void bitDepthFix() throws LWJGLException {
        // Since with MojangFix the transformation will fail anyway this won't even show but.. meh
        if(FabricLoader.getInstance().isModLoaded("mojangfix")){
            UniTweaks.logger.warn("MojangFix is installed, disabling BitDepthFix");
            return;
        }

        if (UniTweaks.BUGFIXES_CONFIG.bitDepthFix) {
            Display.create(new PixelFormat().withDepthBits(24));
        } else {
            Display.create();
        }
    }
}
