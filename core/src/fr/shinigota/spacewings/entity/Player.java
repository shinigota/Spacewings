package fr.shinigota.spacewings.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.shinigota.spacewings.entity.tool.FixtureType;
import fr.shinigota.spacewings.entity.type.DynamicEntity;

/**
 * Created by benjamin on 2/4/17.
 */
public class Player extends DynamicEntity {
    public static final float ACCELERATION_STEP = 0.2f;
    public static final float MAX_ACCELERATION = 2f;
    public static final float MIN_ACCELERATION = 0;

    private static final float DESIRED_ANGLE_CHECK_MARGIN = 5;
    private static final float DESIRED_ANGLE_LOOK_AHEAD = 15;
    private static final float DESIRED_ANGULAR_VELOCITY_CHECK_MARGIN = -.5f;

    protected float desiredAngle;
    protected float acceleration;

    public Player(Vector2 position, Vector2 size, World world) {
        super(world, position, size);
        this.acceleration = 0;
    }

    @Override
    public FixtureDef generateFixtureDef() {
        return FixtureType.LIGHT.fixtureDef;
    }

    @Override
    public void update(float delta) {
        float bodyAngle = this.body.getAngle();
        if(this.wake) {
            this.rotateToDesiredAngle(delta, bodyAngle);
            this.updateSpeed(delta);

            this.wake = this.isValidVelocity(delta) || this.isValidAngle();
        }
    }

    private void rotateToDesiredAngle(float delta, float bodyAngle) {
        float nextAngle = bodyAngle + body.getAngularVelocity() * (delta * DESIRED_ANGLE_LOOK_AHEAD);
        float totalRotation = desiredAngle - nextAngle;
        while ( totalRotation < -180 * MathUtils.degreesToRadians) totalRotation += 360 * MathUtils.degreesToRadians;
        while ( totalRotation >  180 * MathUtils.degreesToRadians ) totalRotation -= 360 * MathUtils.degreesToRadians;
        float desiredAngularVelocity = totalRotation / delta;
        float change = 10 * MathUtils.degreesToRadians;
        desiredAngularVelocity = Math.min( change, Math.max(-change, desiredAngularVelocity));
        float impulse = body.getInertia() * desiredAngularVelocity;
        body.applyAngularImpulse( impulse, true );
    }

    private void updateSpeed(float delta) {
        float angle = (body.getAngle() * MathUtils.radiansToDegrees);
        Vector2 desiredVelocity = new Vector2(0, this.acceleration);
        desiredVelocity.rotate(angle);
        Vector2 currentVelocity = this.body.getLinearVelocity();
        Vector2 force = desiredVelocity.cpy().sub(currentVelocity).scl(this.body.getMass()).cpy().scl(1/delta);
        body.applyForce(force, this.body.getWorldCenter(), true);
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

    private boolean isValidVelocity(float delta) {
        return  this.isValidLinearVelocity() || this.isValidAngularVelocity(delta);
    }

    private boolean isValidAngularVelocity(float delta) {
        float angularVelocity = this.getBody().getAngularVelocity();
        return ! ( -DESIRED_ANGULAR_VELOCITY_CHECK_MARGIN * delta <= angularVelocity * MathUtils.degreesToRadians && MathUtils.degreesToRadians <= DESIRED_ANGULAR_VELOCITY_CHECK_MARGIN * delta) ;
    }

    private boolean isValidLinearVelocity() {
        return this.acceleration != 0;
    }

    private boolean isValidAngle() {
        float angle = Math.abs((this.getAngle() * MathUtils.radiansToDegrees) % 360);
        float desiredAngleDeg = Math.abs((this.desiredAngle * MathUtils.radiansToDegrees) % 360);
        return ! (desiredAngleDeg - DESIRED_ANGLE_CHECK_MARGIN <= angle && angle <= desiredAngleDeg + DESIRED_ANGLE_CHECK_MARGIN);
    }
}
