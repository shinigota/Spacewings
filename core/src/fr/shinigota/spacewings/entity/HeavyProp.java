package fr.shinigota.spacewings.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.tool.FixtureType;
import fr.shinigota.spacewings.entity.type.DynamicEntity;

/**
 * Created by benjamin on 2/5/17.
 */
public class HeavyProp extends DynamicEntity {
    public HeavyProp(Vector2 position, Vector2 size, World world) {
        super(world, position, size, false);
    }

    @Override
    public FixtureDef generateFixtureDef() {
        return FixtureType.HEAVY.fixtureDef;
    }

    @Override
    public void update(float delta) {

    }
}
