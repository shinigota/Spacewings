package fr.shinigota.spacewings.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.type.DynamicEntity;

/**
 * Created by Benjamin on 07/02/2017.
 */
public class LightProp extends DynamicEntity {
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
}