package fr.shinigota.spacewings.entity.type;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.behavior.Collidable;
import fr.shinigota.spacewings.renderable.world.tool.EntityManager;

/**
 * Created by benjamin on 2/5/17.
 */
public abstract class Entity {
    protected final Body body;
    protected float health;
    public EntityState entityState;

    public Entity(World world, Vector2 position, Vector2 size, boolean sensor) {
        this.body = this.generateBody(world, position,  size, sensor);
        this.body.setUserData(this);
        this.health = this instanceof Collidable ? ((Collidable)this).initHealth() : 0;
        this.entityState = EntityState.ALIVE;
    }

    protected abstract Body generateBody(World world, Vector2 position, Vector2 size, boolean sensor);

    protected abstract FixtureDef generateFixtureDef();

    public void destroyNextStep() {
        this.entityState = EntityState.DESTROYED_NEXT_STEP;
    }

    public void destroy() {
        this.entityState = EntityState.DESTROYED;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public void damage(float amount) {
        this.health -= amount;
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

