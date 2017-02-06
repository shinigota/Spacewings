package fr.shinigota.spacewings.entity.tool;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * Created by benjamin on 2/5/17.
 */
public class BodyCreator {
    public static Body createBody(World world, Vector2 position, Vector2 size, BodyType bodyType) {
        if(bodyType == BodyType.StaticBody) {
            return createStaticBody(world, position, size);
        }
        return createDynamicBody(world, position, size);
    }

    public static Body createStaticBody(World world, Vector2 position, Vector2 size) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;

        System.out.println("position = " + position);
        bodyDef.position.set(position);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x/2, size.y/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.5f;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }

    public static Body createDynamicBody(World world, Vector2 position, Vector2 size) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;

        bodyDef.position.set(position);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x/2, size.y/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.01f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }
}
