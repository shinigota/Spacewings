package fr.shinigota.spacewings.entity.tool;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * Created by benjamin on 2/5/17.
 */
public class BodyFactory {

    public static Body rectangleBody(World world, BodyType bodyType, FixtureDef fixtureDef, Vector2 position, Vector2 size, short collisionCategory, short collisionMask) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.bullet = true;
        bodyDef.position.set(position);
        fixtureDef.filter.categoryBits = collisionCategory;
        fixtureDef.filter.maskBits = collisionMask;

        PolygonShape shape = ShapeFactory.rectangleShape(size);
        fixtureDef.shape = shape;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        if (fixtureDef.density != 0) {
            body.setLinearDamping(body.getMass() * 20f);
            body.setAngularDamping(body.getMass() * 20f);
        }
        shape.dispose();

        return body;
    }

    public static Body rectangleBody(World world, BodyType bodyType, FixtureDef fixtureDef, Vector2 position, Vector2 size) {
        return rectangleBody(world, bodyType, fixtureDef, position, size, (short)0x0001, (short)-1);
    }
}
