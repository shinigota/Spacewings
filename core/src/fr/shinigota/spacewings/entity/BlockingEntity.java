package fr.shinigota.spacewings.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.tool.FixtureType;
import fr.shinigota.spacewings.entity.type.Entity;
import fr.shinigota.spacewings.entity.type.StaticEntity;

/**
 * Created by Benjamin on 14/02/2017.
 */
public class BlockingEntity extends StaticEntity {
    public BlockingEntity(Vector2 position, Vector2 size, World world) {
        super(position, size, world, false);
    }

    @Override
    public FixtureDef generateFixtureDef() {
        return FixtureType.HEAVY.fixtureDef;
    }
}
