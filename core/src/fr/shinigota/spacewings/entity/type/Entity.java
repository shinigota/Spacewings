package fr.shinigota.spacewings.entity.type;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by benjamin on 2/5/17.
 */
public abstract class Entity {
    protected final Body body;

    public Entity(World world, Vector2 position, Vector2 size) {
        this.body = this.generateBody(world, position,  size);
        if (this instanceof LivingEntity) {
            LivingEntity self = (LivingEntity) this;
            this.body.setUserData(self.initData());
        }
    }

    protected abstract FixtureDef generateFixtureDef();

    protected abstract Body generateBody(World world, Vector2 position, Vector2 size);

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
