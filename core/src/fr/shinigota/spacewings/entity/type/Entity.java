package fr.shinigota.spacewings.entity.type;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.shinigota.spacewings.entity.tool.BodyCreator;

/**
 * Created by benjamin on 2/5/17.
 */
public abstract class Entity {
    protected final Body body;

    public Entity(Vector2 position, Vector2 size, World world, BodyDef.BodyType bodyType) {
        this.body = BodyCreator.createBody(world, position, size, bodyType);
    }

    public Body getBody() {
        return body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }

    public float getAngle() {
        return this.body.getAngle();
    }
}
