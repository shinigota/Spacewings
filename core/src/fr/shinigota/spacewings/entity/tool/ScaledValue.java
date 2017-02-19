package fr.shinigota.spacewings.entity.tool;

import fr.shinigota.spacewings.Spacewings;

/**
 * Created by benjamin on 2/11/17.
 */
public class ScaledValue {
    public final float pixel;
    public final float meter;

    public ScaledValue(float baseValue, boolean baseValueInPixels) {
        this.pixel = baseValueInPixels ? baseValue : baseValue / Spacewings.PIXELS_TO_METERS;
        this.meter = baseValueInPixels ? baseValue * Spacewings.PIXELS_TO_METERS : baseValue;
    }

    public ScaledValue(float baseValueInPixels) {
        this(baseValueInPixels, true);
    }

}
