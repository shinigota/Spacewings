package fr.shinigota.spacewings.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by benjamin on 2/5/17.
 */
public abstract class DynamicEntity extends Entity {
    public static final float ACCELERATION_STEP = 10;
    public static final float MAX_ACCELERATION = 100;
    public static final float MIN_ACCELERATION = 0;

    protected float desiredAngle;
    protected float acceleration;

    public DynamicEntity(Vector2 position, Vector2 size, World world) {
        super(position, size, world, BodyType.DynamicBody);
        this.acceleration = 0;
    }

    public abstract void update(float delta);

    public void setDesiredAngle(float desiredAngle) {
        this.desiredAngle = desiredAngle;
    }

    public void changeDesiredAcceleration(float amount) {
        if (MIN_ACCELERATION + amount * ACCELERATION_STEP < MIN_ACCELERATION)
            this.acceleration = MIN_ACCELERATION;
        else if (MAX_ACCELERATION + amount * ACCELERATION_STEP > MAX_ACCELERATION)
            this.acceleration = MAX_ACCELERATION;
        else
            this.acceleration += amount * ACCELERATION_STEP;
    }
}
