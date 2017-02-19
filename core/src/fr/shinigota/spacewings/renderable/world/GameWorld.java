package fr.shinigota.spacewings.renderable.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import fr.shinigota.spacewings.Spacewings;
import fr.shinigota.spacewings.entity.BlockingEntity;
import fr.shinigota.spacewings.entity.HeavyProp;
import fr.shinigota.spacewings.entity.LightProp;
import fr.shinigota.spacewings.entity.ship.Player;
import fr.shinigota.spacewings.entity.tool.UnscaledVector2;
import fr.shinigota.spacewings.entity.tool.WorldContactListener;
import fr.shinigota.spacewings.entity.type.DynamicEntity;
import fr.shinigota.spacewings.entity.type.Entity;
import fr.shinigota.spacewings.entity.type.StaticEntity;
import fr.shinigota.spacewings.renderable.Renderable;
import fr.shinigota.spacewings.renderable.world.tool.EntityManager;


/**
 * Created by benjamin on 2/4/17.
 */
public class GameWorld extends Renderable {
    private final World world;
    private final WorldContactListener worldContactListener;

    private final EntityManager entityManager;


    private Player player;

    public GameWorld(Spacewings spacewings) {
        super(spacewings);
        this.world = new World(new Vector2(0, 0), true);
        this.worldContactListener = new WorldContactListener(this);
        this.world.setContactListener(this.worldContactListener);

        this.entityManager = new EntityManager();
        this.player = new Player(this.entityManager, new UnscaledVector2(70, 300), new UnscaledVector2(50, 50), this.world);

        new BlockingEntity(this.entityManager, new UnscaledVector2(0, -100), new UnscaledVector2(500,50), this.world);
        new HeavyProp(this.entityManager, new UnscaledVector2(0, 47), new UnscaledVector2(300, 100), this.world);
        new LightProp(this.entityManager, new UnscaledVector2(32, 120), new UnscaledVector2(25, 25), this.world);
    }

    @Override
    public void update(float delta) {
        this.clearNextStepEntities();
        world.step(1f/60f, 6, 2);

        for (Entity entity : this.entityManager.getEntities()) {
            if (entity instanceof DynamicEntity) {
                DynamicEntity dynamicEntity = (DynamicEntity) entity;
                dynamicEntity.update(delta);
            }
        }

        this.destroyFixtures();
        this.destroyBodies();
        this.destroyEntities();

    }

    private void clearNextStepEntities() {
        Array<Entity> entitiesToDestroyNextStep = this.entityManager.getEntitiesToDestroyNextStep();
        for (Entity entity : entitiesToDestroyNextStep) {
            entity.destroy();
        }
        entitiesToDestroyNextStep.clear();
    }

    private void destroyBodies() {
        Array<Body> bodiesToDestroy = this.entityManager.getBodyToDestroy();
        for (Body bodyToDestroy: bodiesToDestroy) {
            world.destroyBody(bodyToDestroy);
            bodiesToDestroy.removeValue(bodyToDestroy, true);
        }
        bodiesToDestroy.clear();
    }

    private void destroyFixtures() {
        Array<Fixture> fixturesToDestroy = this.entityManager.getFixturesToDestroy();
        for (Fixture fixtureToDestroy : fixturesToDestroy) {
            fixtureToDestroy.getBody().destroyFixture(fixtureToDestroy);
            fixturesToDestroy.removeValue(fixtureToDestroy, true);
        }
        fixturesToDestroy.clear();
    }

    private void destroyEntities() {
        Array<Entity> entitiesToDestroy = this.entityManager.getEntitiesToDestroy();
        Array<Entity> entities = this.entityManager.getEntities();
        for (Entity entity : entitiesToDestroy) {
            entities.removeValue(entity, true);
            entitiesToDestroy.removeValue(entity, true);
        }
        entitiesToDestroy.clear();
    }

    public void dispose() {
        world.dispose();
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
