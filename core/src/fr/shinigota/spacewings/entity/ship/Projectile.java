package fr.shinigota.spacewings.entity.ship;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.shinigota.spacewings.entity.behavior.Collidable;
import fr.shinigota.spacewings.entity.data.CollidableData;
import fr.shinigota.spacewings.entity.tool.FixtureType;
import fr.shinigota.spacewings.entity.type.DynamicEntity;
import fr.shinigota.spacewings.renderable.world.GameWorld;

/**
 * Created by benjamin on 2/11/17.
 */
public class Projectile extends DynamicEntity implements Collidable {
    private static float SPEED = 10;
    public Projectile(World world, Vector2 position, Vector2 size, Vector2 direction) {
        super(world, position, size, true);
        this.body.setTransform(this.body.getPosition(), (direction.angle() - 90) * MathUtils.degreesToRadians);
        this.body.applyLinearImpulse(direction.scl(SPEED), this.body.getWorldCenter(), true);
    }

    @Override
    public CollidableData getData() {
        return new CollidableData(0, 0);
    }

    @Override
    public void onCollision(Fixture fixture, ContactImpulse impulse, GameWorld gameWorld) {
        System.out.println("Projectile.onCollision");
        gameWorld.addFixtureToDestroy(fixture);
        gameWorld.addBodyToDestroy(this.body);
    }

    @Override
    public float initHealth() {
        return 0;
    }

    @Override
    protected FixtureDef generateFixtureDef() {
        return FixtureType.ZERO.fixtureDef;
    }

    @Override
    public void update(float delta) {

    }
}
