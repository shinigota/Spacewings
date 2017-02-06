package fr.shinigota.spacewings.renderable.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.shinigota.spacewings.Spacewings;
import fr.shinigota.spacewings.entity.*;
import fr.shinigota.spacewings.entity.tool.UnscaledVector2;
import fr.shinigota.spacewings.entity.type.StaticEntity;
import fr.shinigota.spacewings.renderable.Renderable;


/**
 * Created by benjamin on 2/4/17.
 */
public class GameWorld extends Renderable {
    private final World world;
    private final StaticEntity staticEntity;
    private final Prop prop;

    private Player player;

    public GameWorld(Spacewings spacewings) {
        super(spacewings);
        this.world = new World(new Vector2(0, 0), true);
        this.staticEntity = new StaticEntity(new UnscaledVector2(0, -100), new UnscaledVector2(500,50), this.world);
        this.prop= new Prop(new UnscaledVector2(0, 50), new UnscaledVector2(100, 100), this.world);
        this.player = new Player(new UnscaledVector2(70, 300), new UnscaledVector2(50, 50), this.world);
    }

    @Override
    public void update(float delta) {
        world.step(1f/60f, 6, 2);
        this.player.update(delta);
//        this.entity.update(delta);
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
