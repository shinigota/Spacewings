package fr.shinigota.spacewings.renderable.world.tool;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import fr.shinigota.spacewings.entity.ship.Projectile;
import fr.shinigota.spacewings.entity.type.Entity;

/**
 * Created by benjamin on 2/18/17.
 */
public class EntityManager {
    private final Array<Entity> entities;
    private final Array<Entity> entitiesToDestroy;
    private final Array<Fixture> fixturesToDestroy;
    private final Array<Body> bodyToDestroy;

    public EntityManager() {
        this.entities = new Array<Entity>();
        this.entitiesToDestroy = new Array<Entity>();
        this.fixturesToDestroy = new Array<Fixture>();
        this.bodyToDestroy = new Array<Body>();
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void addAllEntities(Array<? extends Entity> entities) {
        this.entities.addAll(entities);
    }

    public void addFixtureToDestroy(Fixture fixture) {
        this.fixturesToDestroy.add(fixture);
    }

    public void addAllFixturesToDestroy(Array<Fixture> fixtureList) {
        this.fixturesToDestroy.addAll(fixtureList);
    }

    public void addBodyToDestroy(Body body) {
        this.bodyToDestroy.add(body);
    }

    public void addEntityToDestroy(Entity entity) {
        this.entitiesToDestroy.add(entity);
    }

    public Array<Entity> getEntities() {
        return entities;
    }

    public Array<Entity> getEntitiesToDestroy() {
        return entitiesToDestroy;
    }

    public Array<Fixture> getFixturesToDestroy() {
        return fixturesToDestroy;
    }

    public Array<Body> getBodyToDestroy() {
        return bodyToDestroy;
    }
}
