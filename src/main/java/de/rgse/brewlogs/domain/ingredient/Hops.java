package de.rgse.brewlogs.domain.ingredient;

@SuppressWarnings("serial")
public class Hops extends Ingredient {

	private float acidRatio;
	private PackageDrum packageDrum;

	public float getAcidRatio() {
		return acidRatio;
	}

	public void setAcidRatio(float acidRatio) {
		this.acidRatio = acidRatio;
	}

	public PackageDrum getPackageDrum() {
		return packageDrum;
	}

	public void setPackageDrum(PackageDrum packageDrum) {
		this.packageDrum = packageDrum;
	}

	public enum PackageDrum {
		PALLETS, EXTRACT, UMBEL;
	}
}
