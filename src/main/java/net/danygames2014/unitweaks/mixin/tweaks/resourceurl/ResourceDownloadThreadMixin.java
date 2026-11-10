package net.danygames2014.unitweaks.mixin.tweaks.resourceurl;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.resource.ResourceDownloadThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.net.URL;

@Mixin(value = ResourceDownloadThread.class, priority = 1500)
public class ResourceDownloadThreadMixin {
    @Shadow
    public File resourcesDirectory;

    @ModifyConstant(method = "run", constant = @Constant(stringValue = "http://s3.amazonaws.com/MinecraftResources/"), remap = false)
    private String getResourcesUrl(String original) {
        if (UniTweaks.GENERAL_CONFIG.resourcesDownloadUrl.isEmpty()) {
            return original;
        }
        
        return UniTweaks.GENERAL_CONFIG.resourcesDownloadUrl;
    }
    
    @WrapOperation(method = "run", at = @At(value = "INVOKE", target = "Ljavax/xml/parsers/DocumentBuilderFactory;newDocumentBuilder()Ljavax/xml/parsers/DocumentBuilder;"))
    private DocumentBuilder disableXmlParserFeatures(DocumentBuilderFactory instance, Operation<DocumentBuilder> original) {
        try {
            instance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            instance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            instance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            instance.setXIncludeAware(false);
            instance.setExpandEntityReferences(false);
        } catch (ParserConfigurationException ignored) {
            
        }
        
        return original.call(instance);
    }
    
    @Inject(method = "loadFromUrl", at = @At(value = "HEAD"), cancellable = true)
    private void preventPathTraversal(URL url, String path, long size, int type, CallbackInfo ci) {
        try {
            int slashIndex = path.indexOf("/");
            if (slashIndex == -1) {
                ci.cancel();
                return;
            }
            
            File targetFile = new File(this.resourcesDirectory, path);

            String canonicalBasePath = this.resourcesDirectory.getCanonicalPath();
            String canonicalDestPath = targetFile.getCanonicalPath();

            if (!canonicalDestPath.startsWith(canonicalBasePath + File.separator) && !canonicalDestPath.equals(canonicalBasePath)) {
                UniTweaks.LOGGER.error("Path traversal attempt blocked in resource key: {}", path);
                ci.cancel();
            }
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            ci.cancel();
        }
    }
}
