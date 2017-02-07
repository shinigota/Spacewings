package fr.shinigota.spacewings.entity.tool;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * Created by benjamin on 2/5/17.
 */
public class BodyCreator {
    public static Body createStaticBody(World world, FixtureDef fixtureDef, Vector2 position, Vector2 size) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.StaticBody;
        bodyDef.position.set(position);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x/2, size.y/2);
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }

    public static Body createDynamicBody(World world, FixtureDef fixtureDef, Vector2 position, Vector2 size) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(position);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x/2, size.y/2);
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);

        body.setLinearDamping(body.getMass() * 20f);
        body.setAngularDamping(body.getMass() * 20f);
        shape.dispose();

        return body;
    }
}
