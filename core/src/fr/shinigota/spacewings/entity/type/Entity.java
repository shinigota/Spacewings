package fr.shinigota.spacewings.entity.type;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.behavior.Collidable;

/**
 * Created by benjamin on 2/5/17.
 */
public abstract class Entity {
    protected final Body body;
    protected float health;

    public Entity(World world, Vector2 position, Vector2 size) {
        this.body = this.generateBody(world, position,  size);
        this.body.setUserData(this);
        this.health = this instanceof Collidable ? ((Collidable)this).initHealth() : 0;
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

    public boolean isDead() {
        return this.health <= 0;
    }

    protected void damage(float amount) {
        this.health -= amount;
    }
}
