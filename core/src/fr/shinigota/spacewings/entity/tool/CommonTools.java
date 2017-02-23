package fr.shinigota.spacewings.entity.tool;

import com.badlogic.gdx.math.Vector2;
import fr.shinigota.spacewings.Spacewings;

/**
 * Created by Benjamin on 22/02/2017.
 */
public class CommonTools {
    public static Vector2 UnscaledVector2(float x, float y){
        return new Vector2(x, y).scl(Spacewings.PIXELS_TO_METERS);
    }

    public static Vector2 UnscaledVector2(Vector2 unscaledVector){
        return CommonTools.UnscaledVector2(unscaledVector.x, unscaledVector.y);
    }
}
