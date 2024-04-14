package net.danygames2014.unitweaks.util;

import net.danygames2014.unitweaks.UniTweaks;

public class PostProcess {

	public static final double DEFAULT_GAMMA_D = 2.2D;
	public static final double DEFAULT_GAMMA_INV = 1.0D / DEFAULT_GAMMA_D;
	public static PostProcess instance = new PostProcess();

	public PostProcess() {
		/** - Do nothing */
	}

	private float getCalcGamma() {
		if (BrightnessRangeEnum.SMALL == UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.BRIGHTNESS_RANGE) {
			return ((1.0F - ModOptions.brightness) * 1.2F) + 1.6F;
		} else if (BrightnessRangeEnum.EXTRA_LARGE == UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.BRIGHTNESS_RANGE) {
			return ((1.0F - ModOptions.brightness) * 4.2F) + 0.1F;
		} else {
			return ((1.0F - ModOptions.brightness) * 2.4F) + 1.0F;
		}
	}

	private float readFloat(String s) {
		return s.equals("true") ? 1.0F : (s.equals("false") ? 0.0F : Float.parseFloat(s));
	}

	public int red(int r, int g, int b) {
		if(getCalcGamma() != DEFAULT_GAMMA_D) {
			r = (int)(Math.pow(Math.pow((double)r / 255.0D, getCalcGamma()), DEFAULT_GAMMA_INV) * 255.0D);
		}

		return r;
	}

	public int green(int r, int g, int b) {
		if(getCalcGamma() != DEFAULT_GAMMA_D) {
			g = (int)(Math.pow(Math.pow((double)g / 255.0D, getCalcGamma()), DEFAULT_GAMMA_INV) * 255.0D);
		}

		return g;
	}

	public int blue(int r, int g, int b) {
		if(getCalcGamma() != DEFAULT_GAMMA_D) {
			b = (int)(Math.pow(Math.pow((double)b / 255.0D, getCalcGamma()), DEFAULT_GAMMA_INV) * 255.0D);
		}

		return b;
	}

	public float red(float r, float g, float b) {
		if (r != 0) {
			if(getCalcGamma() != DEFAULT_GAMMA_D) {
				r = (float)Math.pow(Math.pow((double)r, getCalcGamma()), DEFAULT_GAMMA_INV);
			}
		}

		return r;
	}

	public float green(float r, float g, float b) {
		if (g != 0) {
			if (getCalcGamma() != DEFAULT_GAMMA_D) {
				g = (float) Math.pow(Math.pow((double) g, getCalcGamma()), DEFAULT_GAMMA_INV);
			}
		}

		return g;
	}

	public float blue(float r, float g, float b) {
		if (b != 0) {
			if (getCalcGamma() != DEFAULT_GAMMA_D) {
				b = (float) Math.pow(Math.pow((double) b, getCalcGamma()), DEFAULT_GAMMA_INV);
			}
		}

		return b;
	}
}