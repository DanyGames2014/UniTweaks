package net.danygames2014.unitweaks.util.gui;

import net.glasslauncher.mods.alwaysmoreitems.gui.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class CustomButtonWidget extends ButtonWidget {
    ButtonIconType iconType;
    ItemStack item;
    String texture;
    String altText;

    String buttonText = "";

    protected CustomButtonWidget(int id, int x, int y, int width, int height) {
        super(id, x, y, width, height, "");
    }

    public CustomButtonWidget(int id, int x, int y, int width, int height, String texture) {
        this(id, x, y, width, height);
        this.texture = texture;
        this.iconType = ButtonIconType.TEXTURE;
    }

    public CustomButtonWidget(int id, int x, int y, int width, int height, ItemStack item) {
        this(id, x, y, width, height);
        this.item = item;
        this.iconType = ButtonIconType.ITEM;
    }

    /**
     * @param id      ID of the button
     * @param x       x position of the button on screen
     * @param y       y position of the button on screen
     * @param width   width of the button
     * @param height  height of the button
     * @param text    the text thats displayed normally
     * @param altText the text thats displayed when SHIFT is held, leave blank or null to be same as normal text
     */
    public CustomButtonWidget(int id, int x, int y, int width, int height, String text, String altText) {
        this(id, x, y, width, height);
        this.text = TranslationStorage.getInstance().get(text);

        if (altText == null || altText.isEmpty()) {
            this.altText = TranslationStorage.getInstance().get(text);
        } else {
            this.altText = TranslationStorage.getInstance().get(altText);
        }

        this.iconType = ButtonIconType.TEXT;
    }

    @Override
    public void render(Minecraft minecraft, int mouseX, int mouseY) {
        if (!visible) {
            return;
        }

        TextRenderer fontrenderer = minecraft.textRenderer;
        RenderHelper.bindTexture("/gui/gui.png");
        boolean isHovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
        int yImage = getYImage(isHovered);

        drawTexture(x, y, 0, 46 + yImage * 20, width / 2, height);
        drawTexture(x + width / 2, y, 200 - width / 2, 46 + yImage * 20, width / 2, height / 2);
        drawTexture(x, y + height / 2, 0, 46 + yImage * 20 + 20 - height / 2, width / 2, height / 2);
        drawTexture(x + width / 2, y + height / 2, 200 - width / 2, 46 + yImage * 20 + 20 - height / 2, width / 2, height / 2);

        switch (iconType) {
            case ITEM -> {
                RenderHelper.enableItemLighting();
                RenderHelper.drawItemStack(x + 2, y + 2, item, false);
                RenderHelper.disableItemLighting();
            }
            case TEXTURE -> {
                GL11.glEnable(GL11.GL_BLEND);
                RenderHelper.bindTexture(texture);
                RenderHelper.drawTexture(x + 2, y + 2, width - 4, height - 4);
                GL11.glDisable(GL11.GL_BLEND);
            }
            case TEXT -> {
                buttonText = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) ? altText : text;

                if (!active) {
                    drawCenteredTextWithShadow(fontrenderer, buttonText, x + width / 2, y + (height - 8) / 2, 0xffa0a0a0);
                } else if (isHovered) {
                    drawCenteredTextWithShadow(fontrenderer, buttonText, x + width / 2, y + (height - 8) / 2, 0xffffa0);
                } else {
                    drawCenteredTextWithShadow(fontrenderer, buttonText, x + width / 2, y + (height - 8) / 2, 0xe0e0e0);
                }
            }
        }

        renderBackground(minecraft, mouseX, mouseY); // postRender
    }

    public enum ButtonIconType {
        ITEM,
        TEXTURE,
        TEXT,
        OTHER
    }
}
