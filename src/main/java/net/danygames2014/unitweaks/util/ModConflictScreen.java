package net.danygames2014.unitweaks.util;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionButtonWidget;

public class ModConflictScreen extends Screen {
    public String confirmText = "Acknowledge";
    public String cancelText = "Quit Game";

    @Override
    public void tick() {
        super.tick();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init() {
        this.buttons.add(new OptionButtonWidget(0, this.width / 2 - 155, this.height / 6 + 144, this.confirmText));
        this.buttons.add(new OptionButtonWidget(1, this.width / 2 - 155 + 160, this.height / 6 + 144, this.cancelText));
    }

    @Override
    protected void buttonClicked(ButtonWidget button) {
        switch (button.id) {
            case 0 -> {
                this.minecraft.setScreen(new TitleScreen());
            }

            case 1 -> {
                this.minecraft.scheduleStop();
            }
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        int lineHeight = this.height / 4 - 60;

        this.drawCenteredTextWithShadow(this.textRenderer, "Mod Conflict", this.width / 2, lineHeight += 20, 16777215);
        this.drawTextWithShadow(this.textRenderer, "UniTweaks has detected a conflict with another mod.", this.width / 2 - 140, lineHeight += 20, 10526880);
        this.drawTextWithShadow(this.textRenderer, "This is either because it is completely replaced by UniTweaks", this.width / 2 - 140, lineHeight += 20, 10526880);
        this.drawTextWithShadow(this.textRenderer, "or it has conflicting features.", this.width / 2 - 140, lineHeight += 10, 10526880);

        lineHeight += 10;
        for (ModConflicts.ModConflictEntry c : ModConflicts.detectedConflicts) {
            this.drawTextWithShadow(this.textRenderer, "- " + c.modName() + " (" + c.comment() + ")", this.width / 2 - 140, lineHeight += 12, 10526880);
        }

        super.render(mouseX, mouseY, delta);
    }
}
