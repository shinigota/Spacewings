package fr.shinigota.spacewings.entity.type;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.tool.BodyCreator;

/**
 * Created by benjamin on 2/5/17.
 */
public abstract class DynamicEntity extends Entity {
    protected boolean wake;

    public DynamicEntity(World world, Vector2 position, Vector2 size, boolean sensor) {
        super(world, position, size, sensor);
        this.wake = false;
    }

    @Override
    protected Body generateBody(World world, Vector2 position, Vector2 size, boolean sensor) {
        return BodyCreator.createDynamicBody(world, this.generateFixtureDef(), position, size, sensor);
    }

    public abstract void update(float delta);

    public boolean isWake() {
        return wake;
    }

    public void setWake(boolean wake) {
        this.wake = wake;
    }
}
