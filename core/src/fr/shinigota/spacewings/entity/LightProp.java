package fr.shinigota.spacewings.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.type.DynamicEntity;
import fr.shinigota.spacewings.entity.type.LivingEntity;
import fr.shinigota.spacewings.entity.userdata.LivingEntityData;

/**
 * Created by Benjamin on 07/02/2017.
 */
public class LightProp extends DynamicEntity implements LivingEntity {
    public LightProp(Vector2 position, Vector2 size, World world) {
        super(world, position, size);
    }

    @Override
    public FixtureDef generateFixtureDef() {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;
        return fixtureDef;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public LivingEntityData initData() {
        return new LivingEntityData(1f, 1f, 100);
    }

    @Override
    public void onCollision() {

    }
}