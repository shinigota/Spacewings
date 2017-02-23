package fr.shinigota.spacewings.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.behavior.Collidable;
import fr.shinigota.spacewings.entity.data.CollidableData;
import fr.shinigota.spacewings.entity.tool.ArrayTools;
import fr.shinigota.spacewings.entity.tool.FixtureType;
import fr.shinigota.spacewings.entity.type.DynamicEntity;

/**
 * Created by Benjamin on 07/02/2017.
 */
public class LightProp extends DynamicEntity implements Collidable {
    public LightProp(Vector2 position, Vector2 size, World world) {
        super(world, position, size, false);
    }

    @Override
    public CollidableData getData() {
        return new CollidableData(0.045f, 0.05f);
    }

    @Override
    public void onCollision(Fixture fixture, ContactImpulse impulse) {
        if (impulse == null)
            return;

        float normalImpulses = ArrayTools.sum(impulse.getNormalImpulses());
        float tangentImpulses = ArrayTools.sum(impulse.getTangentImpulses());

        this.damage(normalImpulses * 100);
        this.damage(tangentImpulses * 100);
    }

    @Override
    public float initHealth() {
        return 50;
    }

    @Override
    public FixtureDef generateFixtureDef() {
        return FixtureType.LIGHT.fixtureDef;
    }

    @Override
    public void update(float delta) {
        if (this.isDead())
            this.destroy();
    }
}