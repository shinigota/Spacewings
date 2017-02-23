package fr.shinigota.spacewings.entity.tool;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Benjamin on 22/02/2017.
 */
public class ShapeFactory {
    public static PolygonShape rectangleShape(float width, float height) {
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(width/2, height/2);

        return rectangleShape;
    }

    public static PolygonShape rectangleShape(Vector2 size) {
        return ShapeFactory.rectangleShape(size.x, size.y);
    }

    public static CircleShape circleShape(float radius) {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        return circleShape;
    }
}
