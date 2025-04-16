package net.danygames2014.unitweaks.mixin.hooks;

import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyBindingListener;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    @Shadow public KeyBinding[] allKeys;

    @Inject(method = "<init>()V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;difficulty:I", ordinal = 0, opcode = Opcodes.PUTFIELD))
    public void initKeybinds(CallbackInfo ci){
        initKeybinds();
    }

    @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;difficulty:I", ordinal = 0, opcode = Opcodes.PUTFIELD))
    public void initKeybinds2(CallbackInfo ci){
        initKeybinds();
    }

    @Unique
    private void initKeybinds() {
        if(FabricLoader.getInstance().isModLoaded("stationapi")){
            return;
        }
        
        List<KeyBinding> keyBindingList = new ArrayList<>(Arrays.asList(allKeys));
        KeyBindingListener.registerKeyBindings(keyBindingList);
        allKeys = keyBindingList.toArray(new KeyBinding[0]);
    }
}
