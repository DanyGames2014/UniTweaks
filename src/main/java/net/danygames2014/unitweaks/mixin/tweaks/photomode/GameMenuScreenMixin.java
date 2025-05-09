package net.danygames2014.unitweaks.mixin.tweaks.photomode;

import net.danygames2014.unitweaks.tweaks.photomode.PhotoModeScreen;
import net.danygames2014.unitweaks.util.gui.CustomButtonWidget;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unchecked")
@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    @Inject(at = @At("RETURN"), method = "init")
    public void drawMenuButton(CallbackInfo info) {
        // :tf:
        if (Minecraft.INSTANCE.session != null && Minecraft.INSTANCE.session.username.equals("Slainlight")) {
            return;
        }

        if (FabricLoader.getInstance().isModLoaded("modmenu")) {
            this.buttons.add(new CustomButtonWidget(20, this.width / 2 + 104, this.height / 4 + 72 - 16, 20, 20, new ItemStack(Item.PAINTING)));
        } else {
            this.buttons.add(new ButtonWidget(20, this.width / 2 - 100, this.height / 4 + 72 - 16, 200, 20, "Photo Mode"));
        }
    }

    @Inject(method = "buttonClicked", at = @At("HEAD"))
    private void onActionPerformed(ButtonWidget button, CallbackInfo ci) {
        if (button.id == 20) {
            minecraft.setScreen(new PhotoModeScreen(this));
        }
    }
}
