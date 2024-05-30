package net.danygames2014.unitweaks.tweaks.photomode;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.option.Option;
import org.lwjgl.opengl.GL11;

public class SliderWidgetWithoutSaving extends SliderWidget {
    public SliderWidgetWithoutSaving(int id, int x, int y, String text, float value) {
        super(id, x, y, null, text, value);
    }

    @Override
    protected void method_1188(Minecraft minecraft, int mouseX, int mouseY) {
        if (this.visible) {
            if (this.field_2592) {
                this.field_2591 = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
                if (this.field_2591 < 0.0F) {
                    this.field_2591 = 0.0F;
                }

                if (this.field_2591 > 1.0F) {
                    this.field_2591 = 1.0F;
                }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexture(this.x + (int)(this.field_2591 * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
            this.drawTexture(this.x + (int)(this.field_2591 * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
        }
    }

    @Override
    public boolean isMouseOver(Minecraft minecraft, int mouseX, int mouseY) {
        if (this.active && (mouseX >= this.x) && (mouseY >= this.y) && (mouseX < (this.x + this.width)) && (mouseY < (this.y + this.height))) {
            this.field_2591 = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
            if (this.field_2591 < 0.0F) {
                this.field_2591 = 0.0F;
            }

            if (this.field_2591 > 1.0F) {
                this.field_2591 = 1.0F;
            }

            this.field_2592 = true;
            return true;
        } else {
            return false;
        }
    }
}
