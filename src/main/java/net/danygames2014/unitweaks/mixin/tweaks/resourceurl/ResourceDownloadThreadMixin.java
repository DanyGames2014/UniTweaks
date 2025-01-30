package net.danygames2014.unitweaks.mixin.tweaks.resourceurl;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.resource.ResourceDownloadThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ResourceDownloadThread.class)
public class ResourceDownloadThreadMixin {
    @ModifyConstant(method = "run", constant = @Constant(stringValue = "http://s3.amazonaws.com/MinecraftResources/"), remap = false)
    private String getResourcesUrl(String def) {
        return UniTweaks.GENERAL_CONFIG.resourceDownloadUrl;
    }
}
