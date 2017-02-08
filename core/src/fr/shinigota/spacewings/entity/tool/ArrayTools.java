package fr.shinigota.spacewings.entity.tool;

/**
 * Created by Benjamin on 08/02/2017.
 */
public class ArrayTools {
    public static float sum(float[] array) {
        float output = 0;
        for(float f : array)
            output += f;
        return output;
    }
}
