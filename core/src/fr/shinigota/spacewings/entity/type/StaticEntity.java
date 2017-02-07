package fr.shinigota.spacewings.entity.type;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.tool.BodyCreator;
import fr.shinigota.spacewings.entity.tool.FixtureType;

/**
 * Created by benjamin on 2/5/17.
 */
public class StaticEntity extends Entity {
    public StaticEntity(Vector2 position, Vector2 size, World world) {
        super(world, position, size);
    }

    @Override
    public FixtureDef generateFixtureDef() {
        return FixtureType.HEAVY.fixtureDef;
    }

    @Override
    protected Body generateBody(World world, Vector2 position, Vector2 size) {
        return BodyCreator.createStaticBody(world, this.generateFixtureDef(), position, size);
    }
}
