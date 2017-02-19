package fr.shinigota.spacewings.entity.type;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.*;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.tool.BodyCreator;
import fr.shinigota.spacewings.renderable.world.tool.EntityManager;

/**
 * Created by benjamin on 2/5/17.
 */
public abstract class StaticEntity extends Entity {
    public StaticEntity(EntityManager entityManager, Vector2 position, Vector2 size, World world, boolean sensor) {
        super(entityManager, world, position, size, sensor);
    }

    @Override
    protected Body generateBody(World world, Vector2 position, Vector2 size, boolean sensor) {
        return BodyCreator.rectangleBody(world, BodyType.StaticBody, this.generateFixtureDef(), position, size);
    }
}
