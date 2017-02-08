package fr.shinigota.spacewings.entity.behavior;

import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import fr.shinigota.spacewings.entity.data.CollidableData;
import fr.shinigota.spacewings.renderable.world.GameWorld;

/**
 * Created by Benjamin on 07/02/2017.
 */
public interface Collidable {
    CollidableData getData();
    void onCollision(Fixture fixture, ContactImpulse impulse, GameWorld gameWorld);
}
