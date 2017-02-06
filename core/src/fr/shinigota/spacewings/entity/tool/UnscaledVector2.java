package fr.shinigota.spacewings.entity.tool;

import com.badlogic.gdx.math.Vector2;
import fr.shinigota.spacewings.Spacewings;

/**
 * Created by Benjamin on 06/02/2017.
 */
public class UnscaledVector2 extends Vector2 {
    public UnscaledVector2() {
        super();
    }

    public UnscaledVector2(float x, float y) {
        super(x * Spacewings.PIXELS_TO_METERS,  y * Spacewings.PIXELS_TO_METERS);
    }

    public UnscaledVector2(Vector2 v) {
        super(v.cpy().scl(Spacewings.PIXELS_TO_METERS));
    }
}
