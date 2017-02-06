package fr.shinigota.spacewings.entity.type;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by benjamin on 2/5/17.
 */
public abstract class DynamicEntity extends Entity {
    protected boolean wake;

    public DynamicEntity(Vector2 position, Vector2 size, World world) {
        super(position, size, world, BodyType.DynamicBody);
        this.wake = false;
    }

    public abstract void update(float delta);

    public boolean isWake() {
        return wake;
    }

    public void setWake(boolean wake) {
        this.wake = wake;
    }
}
