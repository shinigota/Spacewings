package fr.shinigota.spacewings.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by benjamin on 2/4/17.
 */
public class Player extends DynamicEntity {
    public Player(Vector2 position, Vector2 size, World world) {
        super(position, size, world);
    }

    @Override
    public void update(float delta) {
//        this.body.setTransform(this.getPosition(), this.desiredAngle);

        float bodyAngle = this.body.getAngle();

        float nextAngle = bodyAngle + body.getAngularVelocity() / (1/delta);
        float totalRotation = desiredAngle - nextAngle;
        while ( totalRotation < -180 * MathUtils.degreesToRadians) totalRotation += 360 * MathUtils.degreesToRadians;
        while ( totalRotation >  180 * MathUtils.degreesToRadians ) totalRotation -= 360 * MathUtils.degreesToRadians;
        float desiredAngularVelocity = totalRotation * 60;
        float torque = body.getInertia() * desiredAngularVelocity / delta;
        body.applyTorque( torque, true );


//        float nextAngle = bodyAngle + body.getAngularVelocity() / 1;
//        float totalRotation = desiredAngle - nextAngle;
//        while ( totalRotation < -180 * MathUtils.degreesToRadians ) totalRotation += 360 * MathUtils.degreesToRadians;
//        while ( totalRotation >  180 * MathUtils.degreesToRadians ) totalRotation -= 360 * MathUtils.degreesToRadians;
//        float desiredAngularVelocity = totalRotation * 60;
//        float change = 1 * MathUtils.degreesToRadians; //allow 1 degree rotation per time step
//        desiredAngularVelocity = Math.min( change, Math.max(-change, desiredAngularVelocity));
//        float impulse = body.getInertia() * desiredAngularVelocity;
//        body.applyAngularImpulse( impulse, true);
        float angle = (body.getAngle() * MathUtils.radiansToDegrees);
        Vector2 acceleration = new Vector2(0, this.acceleration);
        acceleration.rotate(angle);
        this.body.setLinearVelocity(acceleration);
    }

}
