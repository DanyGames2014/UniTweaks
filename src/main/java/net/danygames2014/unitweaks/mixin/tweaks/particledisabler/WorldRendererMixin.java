package net.danygames2014.unitweaks.mixin.tweaks.particledisabler;

import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

	@Shadow private Minecraft client;

	@Shadow private World world;

	@Shadow private TextureManager textureManager;

	@Inject(method = "method_1153", at = @At("HEAD"), cancellable = true)
	public void addParticle(String string, double d, double e, double f, double g, double h, double i, CallbackInfo ci) {
		if (UniTweaks.TWEAKS_CONFIG.disableParticles) {
			ci.cancel();
		}
//		if (this.client == null || this.client.field_2807 == null || this.client.field_2808 == null) {
//			return;
//		}
//		double d2 = this.client.field_2807.x - d;
//		double d3 = this.client.field_2807.y - e;
//		double d4 = this.client.field_2807.z - f;
//		double d5 = 16.0;
//		if (d2 * d2 + d3 * d3 + d4 * d4 > d5 * d5) {
//			return;
//		}
//		if (string.equals("bubble")) {
//			this.client.field_2808.method_325(new WaterBubbleParticle(this.world, d, e, f, g, h, i));
//		} else if (string.equals("smoke")) {
//			this.client.field_2808.method_325(new FireSmokeParticle(this.world, d, e, f, g, h, i));
//		} else if (string.equals("note")) {
//			this.client.field_2808.method_325(new NoteParticle(this.world, d, e, f, g, h, i));
//		} else if (string.equals("portal")) {
//			this.client.field_2808.method_325(new PortalParticle(this.world, d, e, f, g, h, i));
//		} else if (string.equals("explode")) {
//			this.client.field_2808.method_325(new ExplosionParticle(this.world, d, e, f, g, h, i));
//		} else if (string.equals("flame")) {
//			this.client.field_2808.method_325(new FlameParticle(this.world, d, e, f, g, h, i));
//		} else if (string.equals("lava")) {
//			this.client.field_2808.method_325(new LavaEmberParticle(this.world, d, e, f));
//		} else if (string.equals("footstep")) {
//			this.client.field_2808.method_325(new FootstepParticle(this.textureManager, this.world, d, e, f));
//		} else if (string.equals("splash")) {
//			this.client.field_2808.method_325(new WaterSplashParticle(this.world, d, e, f, g, h, i));
//		} else if (string.equals("largesmoke")) {
//			this.client.field_2808.method_325(new FireSmokeParticle(this.world, d, e, f, g, h, i, 2.5f));
//		} else if (string.equals("reddust")) {
//			this.client.field_2808.method_325(new RedDustParticle(this.world, d, e, f, (float)g, (float)h, (float)i));
//		} else if (string.equals("snowballpoof")) {
//			this.client.field_2808.method_325(new ItemParticle(this.world, d, e, f, Item.SNOWBALL));
//		} else if (string.equals("snowshovel")) {
//			this.client.field_2808.method_325(new SnowflakeParticle(this.world, d, e, f, g, h, i));
//		} else if (string.equals("slime")) {
//			this.client.field_2808.method_325(new ItemParticle(this.world, d, e, f, Item.SLIMEBALL));
//		} else if (string.equals("heart")) {
//			this.client.field_2808.method_325(new HeartParticle(this.world, d, e, f, g, h, i));
//		}
	}
}
