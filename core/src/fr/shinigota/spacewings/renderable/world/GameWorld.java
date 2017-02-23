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
import fr.shinigota.spacewings.entity.tool.CommonTools;
import fr.shinigota.spacewings.entity.tool.WorldContactListener;
import fr.shinigota.spacewings.entity.type.DynamicEntity;
import fr.shinigota.spacewings.entity.type.Entity;
import fr.shinigota.spacewings.entity.type.EntityState;
import fr.shinigota.spacewings.renderable.Renderable;
import fr.shinigota.spacewings.renderable.world.tool.EntityManager;


/**
 * Created by benjamin on 2/4/17.
 */
public class GameWorld extends Renderable {
    private final World world;

    private final EntityManager entityManager;

    private Player player;

    public GameWorld(Spacewings spacewings) {
        super(spacewings);
        this.world = new World(new Vector2(0, 0), true);
        this.world.setContactListener(new WorldContactListener(this));

        this.player = new Player(CommonTools.UnscaledVector2(70, 300), CommonTools.UnscaledVector2(50, 50), this.world);

        this.entityManager = new EntityManager();
        this.entityManager.addEntity(this.player);
        this.entityManager.addEntity(new BlockingEntity(CommonTools.UnscaledVector2(0, -100), CommonTools.UnscaledVector2(500, 100), this.world));
        this.entityManager.addEntity(new HeavyProp(CommonTools.UnscaledVector2(0, 47), CommonTools.UnscaledVector2(300, 100), this.world));
        this.entityManager.addEntity(new LightProp(CommonTools.UnscaledVector2(32, 120), CommonTools.UnscaledVector2(25, 25), this.world));
        this.entityManager.addEntity(new LightProp(CommonTools.UnscaledVector2(40, 170), CommonTools.UnscaledVector2(25, 25), this.world));
        this.entityManager.addEntity(new LightProp(CommonTools.UnscaledVector2(-20, 224), CommonTools.UnscaledVector2(25, 25), this.world));
    }

    @Override
    public void update(float delta) {
        world.step(1f/60f, 6, 2);

        this.updateEntities(delta);

        if (this.player.isAskingFire())
            this.entityManager.addAllEntities(this.player.shoot());

        this.checkAndDestroyEntities();
        this.destroy();
    }

    private void updateEntities(float delta) {
        for (Entity entity : this.entityManager.getEntities()) {
            if (entity instanceof DynamicEntity) {
                DynamicEntity dynamicEntity = (DynamicEntity) entity;
                dynamicEntity.update(delta);
            }
        }
    }

    private void checkAndDestroyEntities() {
        for (Entity entity : this.entityManager.getEntities()) {
            if (entity.entityState == EntityState.DESTROYED) {
                this.destroyEntity(entity);
            } else if (entity.entityState == EntityState.DESTROYED_NEXT_STEP) {
                entity.entityState = EntityState.DESTROYED;
            }
        }
    }

    private void destroyEntity(Entity entity) {
        this.entityManager.addAllFixturesToDestroy(entity.getBody().getFixtureList());
        this.entityManager.addBodyToDestroy(entity.getBody());
        this.entityManager.addEntityToDestroy(entity);
    }

    private void destroy() {
        this.destroyFixtures();
        this.destroyBodies();
        this.destroyEntities();
    }

    private void destroyFixtures() {
        Array<Fixture> fixturesToDestroy = this.entityManager.getFixturesToDestroy();
        for (Fixture fixtureToDestroy : fixturesToDestroy) {
            fixtureToDestroy.getBody().destroyFixture(fixtureToDestroy);
            fixturesToDestroy.removeValue(fixtureToDestroy, true);
        }
        fixturesToDestroy.clear();
    }

    private void destroyBodies() {
        Array<Body> bodiesToDestroy = this.entityManager.getBodyToDestroy();
        for (Body bodyToDestroy: bodiesToDestroy) {
            world.destroyBody(bodyToDestroy);
            bodiesToDestroy.removeValue(bodyToDestroy, true);
        }
        bodiesToDestroy.clear();
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
}
