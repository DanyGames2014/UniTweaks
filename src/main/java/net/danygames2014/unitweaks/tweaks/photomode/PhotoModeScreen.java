package net.danygames2014.unitweaks.tweaks.photomode;

import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.class_260;
import net.minecraft.class_564;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LightType;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class PhotoModeScreen extends Screen {
    private ButtonWidget buttonRotateLeft;
    private ButtonWidget buttonRotateRight;
    private SliderWidget sliderTimeOfDay;
    private ButtonWidget buttonTakeScreenshot;
    private ButtonWidget buttonExit;
    private SliderWidget sliderFog;
    private SliderWidget sliderTilt;
    private long originalTOD;
    private long desiredTOD = -1L;
    private long desiredDay = -1L;
    boolean shouldScreenshot = false;
    public float rotation = 0.0f;
    public float rotationGoal = 0.0f;
    public float zoom = 1.0f;
    public float zoomGoal = 1.0f;
    public float tilt = 30.0f;
    public float tiltGoal = 30.0f;
    public float fog = 0.5f;
    public float fogGoal = 0.5f;

    @Override
    public void init() {
        super.init();
        this.buttonExit = new ButtonWidget(5, 0, 0, 20, 20, "X");
        this.buttonRotateLeft = new ButtonWidget(0, this.width / 2 - 49 - 2 - 20, this.height - 20, 20, 20, "<");
        this.buttonRotateRight = new ButtonWidget(1, this.width / 2 + 49 + 2, this.height - 20, 20, 20, ">");
        this.buttonTakeScreenshot = new ButtonWidget(4, this.width / 2 - 49, this.height - 20, 98, 20, "Take Screenshot");
        this.sliderTimeOfDay = new SliderWidgetWithoutSaving(2, this.width - 151, 0, "Time of Day: DEFAULT", 0.0f);
        this.sliderFog = new SliderWidgetWithoutSaving(6, this.width - 151, 0,  "Fog: 1.0", 1.0f);
        this.sliderTilt = new SliderWidgetWithoutSaving(7, this.width - 151, 0,  "Tilt: Default", 0.33333334f);
        this.buttons.add(this.sliderTilt);
        if (!this.minecraft.world.isRemote) {
            this.buttons.add(this.sliderTimeOfDay);
            this.buttons.add(this.sliderFog);
        }
        int n2 = 0;
        for (Object buttons : this.buttons) {
            ((ButtonWidget) buttons).y = n2++ * 20;
        }
        this.buttons.add(this.buttonRotateLeft);
        this.buttons.add(this.buttonRotateRight);
        this.buttons.add(this.buttonTakeScreenshot);
        this.buttons.add(this.buttonExit);
        this.originalTOD = this.minecraft.world.getTime();
        if (this.desiredTOD == -1L) {
            this.desiredTOD = this.originalTOD % 24000L;
        } else {
            this.sliderTimeOfDay.field_2591 = (float) this.desiredTOD / 24000.0f;
        }
        if (this.desiredDay == -1L) {
            this.desiredDay = this.originalTOD / 24000L;
        }
        this.updateButtonsText();
    }

    @Override
    public void removed() {
        this.minecraft.world.setTime(this.originalTOD);
        ModOptions.photoModeFogMultiplier = 0.5F;
    }

    @Override
    protected void buttonClicked(ButtonWidget ButtonWidget2) {
        if (ButtonWidget2 == this.buttonRotateLeft) {
            this.rotationGoal += 0.5f;
        } else if (ButtonWidget2 == this.buttonRotateRight) {
            this.rotationGoal -= 0.5f;
        } else if (ButtonWidget2 == this.buttonTakeScreenshot) {
            this.shouldScreenshot = true;
        } else if (ButtonWidget2 == this.buttonExit) {
            this.minecraft.setScreen(null);
        }
        this.updateButtonsText();
    }

    @Override
    public void render(int n2, int n3, float f) {
        class_564 scaledResolution = new class_564(this.minecraft.options, this.minecraft.displayWidth, this.minecraft.displayHeight);

        GL11.glViewport(0, 0, this.minecraft.displayWidth, this.minecraft.displayHeight);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glClear(256);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0,  scaledResolution.method_1857(), scaledResolution.method_1858(), 0.0, 1000.0, 3000.0);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0f, 0.0f, -2000.0f);
        if (this.zoom != this.zoomGoal) {
            this.zoom += (this.zoomGoal - this.zoom) * 0.02f + (this.zoomGoal - this.zoom) * 0.02f * f;
            if (Math.abs(this.zoom - this.zoomGoal) < 5.0E-4f) {
                this.zoom = this.zoomGoal;
            }
        }
        if (this.rotation != this.rotationGoal) {
            this.rotation += (this.rotationGoal - this.rotation) * 0.02f + (this.rotationGoal - this.rotation) * 0.02f * f;
            if (Math.abs(this.rotation - this.rotationGoal) < 5.0E-4f) {
                this.rotation = this.rotationGoal;
            }
        }
        if (this.tilt != this.tiltGoal) {
            this.tilt += (this.tiltGoal - this.tilt) * 0.02f + (this.tiltGoal - this.tilt) * 0.02f * f;
            if (Math.abs(this.tilt - this.tiltGoal) < 0.01f) {
                this.tilt = this.tiltGoal;
            }
        }
        if (this.fogGoal != this.fog) {
            this.fog += (this.fogGoal - this.fog) * 0.02f + (this.fogGoal - this.fog) * 0.02f * f;
            if (Math.abs(this.fog - this.fogGoal) < 5.0E-5f) {
                this.fog = this.fogGoal;
            }
        }
        if (!this.shouldScreenshot) {
            super.render(n2, n3, f);
        } else {
            class_260.method_908(Minecraft.getRunDirectory(), this.minecraft.displayWidth, this.minecraft.displayHeight);
            this.shouldScreenshot = false;
        }
        this.scroll(Mouse.getDWheel());

        if (this.sliderTimeOfDay.field_2592) {
            long var4 = (long)(this.sliderTimeOfDay.field_2591 * 24000.0F);
            if (this.sliderTimeOfDay.field_2591 == 0.0F) {
                this.desiredTOD = this.originalTOD % 24000L;
            } else {
                this.desiredTOD = var4;
            }

            this.minecraft.world.setTime(this.desiredDay + this.desiredTOD);
            this.minecraft.world.method_237();
//            this.minecraft.world.method_232();
            this.minecraft.worldRenderer.method_1148();
            this.updateButtonsText();
        }

        if (this.sliderTilt.field_2592) {
            float var6 = (float)((int)(this.sliderTilt.field_2591 * 90.0F));
            this.tiltGoal = var6;
            this.updateButtonsText();
        }

        if (this.sliderFog.field_2592) {
            this.fogGoal = (float)Math.pow(2.0, 8.0F * this.sliderFog.field_2591 - 8.0F);
            this.updateButtonsText();
        }

        ModOptions.photoModeFogMultiplier = this.sliderFog.field_2591;
    }

    private void updateButtonsText() {
        this.sliderTimeOfDay.text = this.sliderTimeOfDay.field_2591 == 0.0f ? "Time of Day: Default" : "Time of Day: " + (long) (this.sliderTimeOfDay.field_2591 * 24000.0f);
        this.sliderFog.text = "Fog Distance: " + (int) (this.sliderFog.field_2591 * 100.0f) + "%";
        this.sliderTilt.text = (int) (this.sliderTilt.field_2591 * 90.0f) == 30 ? "Tilt: Default" : "Tilt: " + (this.sliderTilt.field_2591 * 90.0f) + " degrees";
    }

    public void scroll(int n2) {
        if (n2 < 0) {
            this.zoomGoal -= 0.25f;
        } else if (n2 > 0) {
            this.zoomGoal += 0.25f;
        }
    }
}
