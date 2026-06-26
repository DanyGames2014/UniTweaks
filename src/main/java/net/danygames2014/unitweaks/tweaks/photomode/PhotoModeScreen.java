package net.danygames2014.unitweaks.tweaks.photomode;

import net.danygames2014.unitweaks.util.ModOptions;
import net.danygames2014.unitweaks.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.util.ScreenScaler;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SuppressWarnings("unchecked")
public class PhotoModeScreen extends Screen {
    private ButtonWidget exitButton;
    private SliderWidget tiltSlider;
    private SliderWidget timeSlider;

    private ButtonWidget centerButton;
    private ButtonWidget rotateLeftButton;
    private ButtonWidget rotateRightButton;
    private ButtonWidget screenshotButton;
    private ButtonWidget renderPlayerButton;

    private long originalTOD;
    private long desiredTOD = -1L;
    private long desiredDay = -1L;

    boolean shouldScreenshot = false;
    public boolean renderPlayer = true;

    public float rotation = 0.0f;
    public float rotationGoal = 0.0f;
    public float zoom = 1.0f;
    public float zoomGoal = 1.0f;
    public float tilt = 30.0f;
    public float tiltGoal = 30.0f;
    public float cameraX = 0.0f;
    public float cameraY = 0.0f;

    public Screen previousScreen;

    public PhotoModeScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    public void init() {
        super.init();

        int id = 0;
        this.exitButton = new ButtonWidget(id++, 0, 0, 20, 20, "X");
        this.tiltSlider = new SliderWidgetWithoutSaving(id++, this.width - 150, 0, "Tilt: Default", 0.33333334f);
        this.timeSlider = new SliderWidgetWithoutSaving(id++, this.width - 150, 20, "Time of Day: Current", 0.0f);
        this.centerButton = new ButtonWidget(id++, 0, this.height - 20, 98, 20, "Center");
        this.rotateLeftButton = new ButtonWidget(id++, this.width / 2 - 49 - 2 - 20, this.height - 20, 20, 20, "<");
        this.rotateRightButton = new ButtonWidget(id++, this.width / 2 + 49 + 2, this.height - 20, 20, 20, ">");
        this.screenshotButton = new ButtonWidget(id++, this.width / 2 - 49, this.height - 20, 98, 20, "Take Screenshot");
        this.renderPlayerButton = new ButtonWidget(id++, this.width - 104, this.height - 20, 105, 20, "Render Player: On");

        this.buttons.add(this.exitButton);
        this.buttons.add(this.tiltSlider);
        this.buttons.add(this.centerButton);
        this.buttons.add(this.rotateLeftButton);
        this.buttons.add(this.rotateRightButton);
        this.buttons.add(this.screenshotButton);
        this.buttons.add(this.renderPlayerButton);

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

    // Input
    @Override
    protected void buttonClicked(ButtonWidget button) {
        if (button == this.rotateLeftButton) {
            this.rotateLeft();
        } else if (button == this.rotateRightButton) {
            this.rotateRight();
        } else if (button == this.screenshotButton) {
            this.shouldScreenshot = true;
        } else if (button == this.exitButton) {
            this.minecraft.setScreen(this.previousScreen);
        } else if (button == this.renderPlayerButton) {
            this.renderPlayer = !this.renderPlayer;
        } else if (button == this.centerButton) {
            this.center();
        }
        this.updateButtonsText();
    }

    private void rotateLeft() {
        this.rotationGoal += 0.5f;
        if (this.rotationGoal > 200) {
            this.rotationGoal = 200;
        }
    }

    private void rotateRight() {
        this.rotationGoal -= 0.5f;
        if (this.rotationGoal < -200) {
            this.rotationGoal = -200;
        }
    }
    
    private void center() {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            this.rotation = 0;
            this.rotationGoal = 0;
            this.zoom = 1.0f;
            this.zoomGoal = 1.0f;
            this.tilt = 30.0f;
            this.tiltGoal = 30.0f;
        }
        
        this.cameraX = 0;
        this.cameraY = 0;
    }

    @Override
    protected void keyPressed(char character, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            this.removed();
            this.minecraft.setScreen(null);
            this.minecraft.lockMouse();
        }

        // Controls via keyboard
        if (Keyboard.getEventKeyState()) {
            switch (keyCode) {
                case Keyboard.KEY_Q -> this.rotateLeft();
                case Keyboard.KEY_E -> this.rotateRight();
                case Keyboard.KEY_SPACE -> this.center();
            }
        }
    }
    
    private boolean shiftHeld() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public void handleInput(int mouseX, int mouseY, float delta) {
        // Zoom with Scroll Wheel
        int mouseWheelDirection = Mouse.getDWheel();
        if (mouseWheelDirection < 0) {
            this.zoomGoal -= 0.25f;
            this.zoomGoal = Math.max(this.zoomGoal, 0.01f);
        } else if (mouseWheelDirection > 0) {
            this.zoomGoal += 0.25f;
            this.zoomGoal = Math.min(this.zoomGoal, 12.0f);
        }

        // Movement via keyboard
        boolean shift = shiftHeld();
        float cameraVelocity = (float) (0.005f / Math.pow(2, this.zoom));
        float mouseCameraVelocity = (float) (0.001f / Math.pow(2, this.zoom));
        if (shift) cameraVelocity *= 2;
        if (shift) mouseCameraVelocity *= 2;

        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            this.cameraY -= cameraVelocity;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            this.cameraY += cameraVelocity;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            this.cameraX += cameraVelocity;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            this.cameraX -= cameraVelocity;
        }

        // Movement via mouse
        boolean hoveringButton = false;
        for (Object buttonO : buttons) {
            if (buttonO instanceof SliderWidget slider) {
                if (slider.dragging) {
                    hoveringButton = true;
                    break;
                }
            }
            
            if (buttonO instanceof ButtonWidget button) {
                if (isMouseInBounds(mouseX, mouseY, button)) {
                    hoveringButton = true;
                    break;
                }
            }
        }
        
        if (!hoveringButton) {
            if (Mouse.isButtonDown(0)) {
                this.cameraX += Mouse.getDX() * mouseCameraVelocity;
                this.cameraY += Mouse.getDY() * mouseCameraVelocity;
            } else if (Mouse.isButtonDown(1) || Mouse.isButtonDown(2)) {
                this.tiltSlider.value -= Mouse.getDY() * 0.0025f;
                this.tiltGoal = (float) ((int) (this.tiltSlider.value * 90.0F));
                this.tiltSlider.value = Util.clamp(this.tiltSlider.value, 0.0f, 1.0f);
                
                this.rotationGoal += Mouse.getDX() * 0.0025f;
            }
        }
        
        updateButtonsText();
    }
    
    private boolean isMouseInBounds(int mouseX, int mouseY, ButtonWidget button) {
        return button.active && mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height;
    }

    // UI
    private void updateButtonsText() {
        this.timeSlider.text = this.timeSlider.value == 0.0f ? "Time of Day: Default" : "Time of Day: " + (long) (this.timeSlider.value * 24000.0f);
        this.tiltSlider.text = (int) (this.tiltSlider.value * 90.0f) == 30 ? "Tilt: Default" : "Tilt: " + (Math.floor(this.tiltSlider.value * 90.0f)) + " degrees";
        this.renderPlayerButton.text = this.renderPlayer ? "Render Player: On" : "Render Player: Off";
        this.centerButton.text = shiftHeld() ? "Full Reset" : "Center";
    }

    // Rendering
    @Override
    public void render(int mouseX, int mouseY, float delta) {
        ScreenScaler scaledResolution = new ScreenScaler(this.minecraft.options, this.minecraft.displayWidth, this.minecraft.displayHeight);

        GL11.glViewport(0, 0, this.minecraft.displayWidth, this.minecraft.displayHeight);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glClear(256);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 0.0, 1000.0, 3000.0);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0f, 0.0f, -2000.0f);

        if (this.zoom != this.zoomGoal) {
            this.zoom += (this.zoomGoal - this.zoom) * 0.02f + (this.zoomGoal - this.zoom) * 0.02f * delta;
            if (Math.abs(this.zoom - this.zoomGoal) < 5.0E-4f) {
                this.zoom = this.zoomGoal;
            }
        }
        if (this.rotation != this.rotationGoal) {
            this.rotation += (this.rotationGoal - this.rotation) * 0.02f + (this.rotationGoal - this.rotation) * 0.02f * delta;
            if (Math.abs(this.rotation - this.rotationGoal) < 5.0E-4f) {
                this.rotation = this.rotationGoal;
            }
        }
        if (this.tilt != this.tiltGoal) {
            this.tilt += (this.tiltGoal - this.tilt) * 0.02f + (this.tiltGoal - this.tilt) * 0.02f * delta;
            if (Math.abs(this.tilt - this.tiltGoal) < 0.01f) {
                this.tilt = this.tiltGoal;
            }
        }
        if (!this.shouldScreenshot) {
            super.render(mouseX, mouseY, delta);
        } else {
            Screenshot.take(Minecraft.getRunDirectory(), this.minecraft.displayWidth, this.minecraft.displayHeight);
            this.shouldScreenshot = false;
        }
        this.handleInput(mouseX, mouseY, delta);

        if (this.timeSlider.dragging) {
            long var4 = (long) (this.timeSlider.value * 24000.0F);
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
            this.tiltGoal = (float) ((int) (this.tiltSlider.value * 90.0F));
            this.updateButtonsText();
        }
    }
}
