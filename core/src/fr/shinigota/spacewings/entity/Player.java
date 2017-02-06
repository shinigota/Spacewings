package fr.shinigota.spacewings.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.shinigota.spacewings.entity.type.DynamicEntity;

/**
 * Created by benjamin on 2/4/17.
 */
public class Player extends DynamicEntity {
    public static final float ACCELERATION_STEP = 0.2f;
    public static final float MAX_ACCELERATION = 2f;
    public static final float MIN_ACCELERATION = 0;

    protected float desiredAngle;
    protected float acceleration;

    public Player(Vector2 position, Vector2 size, World world) {
        super(position, size, world);
        this.acceleration = 0;
    }

    @Override
    public void update(float delta) {
        float bodyAngle = this.body.getAngle();

        float nextAngle = bodyAngle + body.getAngularVelocity() / (1/delta);
        float totalRotation = desiredAngle - nextAngle;
        while ( totalRotation < -180 * MathUtils.degreesToRadians) totalRotation += 360 * MathUtils.degreesToRadians;
        while ( totalRotation >  180 * MathUtils.degreesToRadians ) totalRotation -= 360 * MathUtils.degreesToRadians;
        float desiredAngularVelocity = totalRotation * 60;
        float torque = body.getInertia() * desiredAngularVelocity / delta;
        body.applyTorque( torque, true );

        if(this.wake) {
            float angle = (body.getAngle() * MathUtils.radiansToDegrees);
            Vector2 acceleration = new Vector2(0, this.acceleration);
            acceleration.rotate(angle);

            this.body.setLinearVelocity(acceleration);
            this.wake = this.acceleration != 0;
        }
    }


    public void changeDesiredAcceleration(float amount) {
        if (this.acceleration + amount * ACCELERATION_STEP < MIN_ACCELERATION)
            this.acceleration = MIN_ACCELERATION;
        else if (this.acceleration + amount * ACCELERATION_STEP > MAX_ACCELERATION)
            this.acceleration = MAX_ACCELERATION;
        else
            this.acceleration += amount * ACCELERATION_STEP;
    }

    public void setDesiredAngle(float desiredAngle) {
        this.desiredAngle = desiredAngle;
    }

}
