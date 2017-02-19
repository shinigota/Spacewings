package fr.shinigota.spacewings.renderable.world.tool;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import fr.shinigota.spacewings.entity.type.Entity;

/**
 * Created by benjamin on 2/18/17.
 */
public class EntityManager {
    private final Array<Entity> entities;
    private final Array<Fixture> fixturesToDestroy;
    private final Array<Body> bodyToDestroy;

    public EntityManager(Array<Entity> entities, Array<Fixture> fixturesToDestroy, Array<Body> bodyToDestroy) {
        this.entities = entities;
        this.fixturesToDestroy = fixturesToDestroy;
        this.bodyToDestroy = bodyToDestroy;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.removeValue(entity, true);
    }

    public void addFixtureToDestroy(Fixture fixture) {
        this.fixturesToDestroy.add(fixture);
    }

    public void addBodyToDestroy(Body body) {
        this.bodyToDestroy.add(body);
    }
}
