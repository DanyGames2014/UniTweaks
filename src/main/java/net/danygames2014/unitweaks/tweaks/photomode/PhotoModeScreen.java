package net.danygames2014.unitweaks.tweaks.photomode;

import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.util.ScreenScaler;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SuppressWarnings("unchecked")
public class PhotoModeScreen extends Screen {
    private ButtonWidget rotateLeftButton;
    private ButtonWidget rotateRightButton;
    private SliderWidget timeSlider;
    private ButtonWidget screenshotButton;
    private ButtonWidget exitButton;
    private SliderWidget tiltSlider;
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
    public Screen previousScreen;

    public PhotoModeScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    public void init() {
        super.init();
        this.exitButton = new ButtonWidget(5, 0, 0, 20, 20, "X");
        this.rotateLeftButton = new ButtonWidget(0, this.width / 2 - 49 - 2 - 20, this.height - 20, 20, 20, "<");
        this.rotateRightButton = new ButtonWidget(1, this.width / 2 + 49 + 2, this.height - 20, 20, 20, ">");
        this.screenshotButton = new ButtonWidget(4, this.width / 2 - 49, this.height - 20, 98, 20, "Take Screenshot");
        this.tiltSlider = new SliderWidgetWithoutSaving(7, this.width - 151, 0,  "Tilt: Default", 0.33333334f);
        this.timeSlider = new SliderWidgetWithoutSaving(2, this.width - 151, 20, "Time of Day: Current", 0.0f);

        this.buttons.add(this.tiltSlider);
        this.buttons.add(this.rotateLeftButton);
        this.buttons.add(this.rotateRightButton);
        this.buttons.add(this.screenshotButton);
        this.buttons.add(this.exitButton);
        if (!this.minecraft.world.isRemote) {
            this.buttons.add(this.timeSlider);
        }

        this.originalTOD = this.minecraft.world.getTime();
        if (this.desiredTOD == -1L) {
            this.desiredTOD = this.originalTOD % 24000L;
        } else {
            this.timeSlider.value = (float) this.desiredTOD / 24000.0f;
        }
        if (this.desiredDay == -1L) {
            this.desiredDay = this.originalTOD / 24000L;
        }
        this.updateButtonsText();
        ModOptions.photoModeFogMultiplier = 100F;
    }

    @Override
    public void removed() {
        this.minecraft.world.setTime(this.originalTOD);
        ModOptions.photoModeFogMultiplier = 1.0F;
    }

    @Override
    protected void buttonClicked(ButtonWidget ButtonWidget2) {
        if (ButtonWidget2 == this.rotateLeftButton) {
            this.rotationGoal += 0.5f;
        } else if (ButtonWidget2 == this.rotateRightButton) {
            this.rotationGoal -= 0.5f;
        } else if (ButtonWidget2 == this.screenshotButton) {
            this.shouldScreenshot = true;
        } else if (ButtonWidget2 == this.exitButton) {
            this.minecraft.setScreen(this.previousScreen);
        }
        this.updateButtonsText();
    }

    @Override
    public void render(int n2, int n3, float f) {
        ScreenScaler scaledResolution = new ScreenScaler(this.minecraft.options, this.minecraft.displayWidth, this.minecraft.displayHeight);

        GL11.glViewport(0, 0, this.minecraft.displayWidth, this.minecraft.displayHeight);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glClear(256);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0,  scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 0.0, 1000.0, 3000.0);
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
        if (!this.shouldScreenshot) {
            super.render(n2, n3, f);
        } else {
            Screenshot.take(Minecraft.getRunDirectory(), this.minecraft.displayWidth, this.minecraft.displayHeight);
            this.shouldScreenshot = false;
        }
        this.scroll(Mouse.getDWheel());

        if (this.timeSlider.dragging) {
            long var4 = (long)(this.timeSlider.value * 24000.0F);
            if (this.timeSlider.value == 0.0F) {
                this.desiredTOD = this.originalTOD % 24000L;
            } else {
                this.desiredTOD = var4;
            }

            this.minecraft.world.setTime(this.desiredDay + this.desiredTOD);
            this.minecraft.world.updateSkyBrightness();
//            this.minecraft.world.method_232();
            this.minecraft.worldRenderer.notifyAmbientDarknessChanged();
            this.updateButtonsText();
        }

        if (this.tiltSlider.dragging) {
            this.tiltGoal = (float)((int)(this.tiltSlider.value * 90.0F));
            this.updateButtonsText();
        }
    }

    private void updateButtonsText() {
        this.timeSlider.text = this.timeSlider.value == 0.0f ? "Time of Day: Default" : "Time of Day: " + (long) (this.timeSlider.value * 24000.0f);
        this.tiltSlider.text = (int) (this.tiltSlider.value * 90.0f) == 30 ? "Tilt: Default" : "Tilt: " + (Math.floor(this.tiltSlider.value * 90.0f)) + " degrees";
    }

    public void scroll(int direction) {
        if (direction < 0) {
            this.zoomGoal -= 0.25f;
        } else if (direction > 0) {
            this.zoomGoal += 0.25f;
        }
    }
}
