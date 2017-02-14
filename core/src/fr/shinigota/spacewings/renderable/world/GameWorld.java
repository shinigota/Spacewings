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
import fr.shinigota.spacewings.entity.type.StaticEntity;
import fr.shinigota.spacewings.renderable.Renderable;


/**
 * Created by benjamin on 2/4/17.
 */
public class GameWorld extends Renderable {
    private final World world;
    private final WorldContactListener worldContactListener;

    private final Array<Fixture> fixturesToDestroy;
    private final Array<Body> bodiesToDestroy;

    private final BlockingEntity staticEntity;
    private final HeavyProp heavyProp;
    private final LightProp lightProp;

    private Player player;

    public GameWorld(Spacewings spacewings) {
        super(spacewings);
        this.world = new World(new Vector2(0, 0), true);
        this.worldContactListener = new WorldContactListener(this);
        this.world.setContactListener(this.worldContactListener);

        this.fixturesToDestroy = new Array<Fixture>();
        this.bodiesToDestroy = new Array<Body>();

        this.staticEntity = new BlockingEntity(new UnscaledVector2(0, -100), new UnscaledVector2(500,50), this.world);
        this.heavyProp = new HeavyProp(new UnscaledVector2(0, 47), new UnscaledVector2(300, 100), this.world);
        this.lightProp = new LightProp(new UnscaledVector2(32, 120), new UnscaledVector2(25, 25), this.world);
        this.player = new Player(new UnscaledVector2(70, 300), new UnscaledVector2(50, 50), this.world);
    }

    @Override
    public void update(float delta) {
        world.step(1f/60f, 6, 2);
        this.player.update(delta);

        this.destroyFixtures();
        this.destroyBodies();

    }

    private void destroyBodies() {
        for (Body bodyToDestroy: bodiesToDestroy) {
            world.destroyBody(bodyToDestroy);
            bodiesToDestroy.removeValue(bodyToDestroy, true);
        }
        bodiesToDestroy.clear();
    }

    private void destroyFixtures() {
        for (Fixture fixtureToDestroy : fixturesToDestroy) {
            fixtureToDestroy.getBody().destroyFixture(fixtureToDestroy);
            fixturesToDestroy.removeValue(fixtureToDestroy, true);
        }
        fixturesToDestroy.clear();
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

    public void addFixtureToDestroy(Fixture fixture) {
        this.fixturesToDestroy.add(fixture);
    }

    public void addBodyToDestroy(Body body) {
        this.bodiesToDestroy.add(body);
    }
}
