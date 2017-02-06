package fr.shinigota.spacewings.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.shinigota.spacewings.entity.type.DynamicEntity;

/**
 * Created by benjamin on 2/5/17.
 */
public class Prop extends DynamicEntity {
    public Prop(Vector2 position, Vector2 size, World world) {
        super(position, size, world);
    }

    @Override
    public void update(float delta) {

    }
}
