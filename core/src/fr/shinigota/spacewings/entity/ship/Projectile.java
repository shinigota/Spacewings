package fr.shinigota.spacewings.entity.ship;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.shinigota.spacewings.entity.tool.BodyFactory;
import fr.shinigota.spacewings.entity.tool.FixtureType;
import fr.shinigota.spacewings.entity.type.DynamicEntity;
import fr.shinigota.spacewings.entity.type.Entity;
import fr.shinigota.spacewings.renderable.world.tool.EntityManager;

/**
 * Created by benjamin on 2/11/17.
 */
public class Projectile extends DynamicEntity implements RayCastCallback{
    public final static short COLLISION_MASK = -1;
//    public final static short COLLISION_CATEGORY = 0x0002;
    private static float SPEED = 30;

    private static float DAMAGE = 50;

    private float closestFraction;
    private Fixture closestFixture;

    public Projectile(EntityManager entityManager, World world, Vector2 position, Vector2 size, Vector2 direction) {
        super(entityManager, world, position, size, true);

        this.body.setTransform(this.body.getPosition(), (direction.angle() - 90) * MathUtils.degreesToRadians);
        this.body.applyLinearImpulse(direction.scl(SPEED), this.body.getWorldCenter(), true);
    }


    public void onCollision(Fixture fixture, ContactImpulse impulse) {
        if (fixture.getBody().getUserData() instanceof Entity) {
            Entity entity = (Entity) fixture.getBody().getUserData();
            entity.damage(DAMAGE);
        }
        this.destroyNextStep();
    }

    @Override
    protected FixtureDef generateFixtureDef() {
        return FixtureType.PROJECTILE.fixtureDef;
    }

    @Override
    protected Body generateBody(World world, Vector2 position, Vector2 size, boolean sensor) {
        return BodyFactory.rectangleBody(world, BodyDef.BodyType.DynamicBody, this.generateFixtureDef(), position, size);
    }

    @Override
    public void update(float delta) {
        this.closestFixture = null;
        this.closestFraction = 1;
        World world = this.body.getWorld();
        Vector2 currentPosition = this.body.getWorldCenter();
        Vector2 nextPosition = currentPosition.cpy().add(this.body.getLinearVelocity().cpy().scl(1f/60f));
        world.rayCast(this, currentPosition, nextPosition);
        if (this.closestFixture != null) {
            this.onCollision(this.closestFixture, null);
        }
    }

    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
        if (fraction < this.closestFraction) {
            this.closestFraction = fraction;
            this.closestFixture = fixture;
        }
        return 0;
    }
}
