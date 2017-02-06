package fr.shinigota.spacewings.entity.type;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.*;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by benjamin on 2/5/17.
 */
public class StaticEntity extends Entity {
    public StaticEntity(Vector2 position, Vector2 size, World world) {
        super(position, size, world, BodyType.StaticBody);
    }
}
