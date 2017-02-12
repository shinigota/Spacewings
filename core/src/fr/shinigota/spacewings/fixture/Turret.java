package fr.shinigota.spacewings.fixture;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.TimeUtils;
import fr.shinigota.spacewings.Spacewings;
import fr.shinigota.spacewings.entity.ship.Player;
import fr.shinigota.spacewings.entity.ship.Projectile;
import fr.shinigota.spacewings.entity.tool.FixtureType;

/**
 * Created by benjamin on 2/11/17.
 */
public class Turret {
    private static final float RATE_OF_FIRE = 200;
    private static final float SIZE = 10;
    private final Player player;

    private float lastShootTime;

    public Turret(Player player) {
        CircleShape circleShape = new CircleShape();
        circleShape.setPosition(new Vector2(-20* Spacewings.PIXELS_TO_METERS,20*Spacewings.PIXELS_TO_METERS));
        circleShape.setRadius((SIZE*Spacewings.PIXELS_TO_METERS)/2);

        FixtureDef fixtureDef = FixtureType.ZERO.fixtureDef;
        fixtureDef.shape = circleShape;
        player.getBody().createFixture(fixtureDef);
        this.player = player;
        circleShape.dispose();

        this.lastShootTime = 0;
    }

    public boolean canShoot() {
        return TimeUtils.millis() >= RATE_OF_FIRE + lastShootTime;
    }

    public Projectile shoot() {
        float angle = this.player.getBody().getAngle() * MathUtils.radiansToDegrees;
        Vector2 direction = Vector2.Y.cpy().rotate(angle);
//        return null;
        return new Projectile(this.player.getBody().getWorld(), Vector2.Zero, new Vector2(5*Spacewings.PIXELS_TO_METERS, 10*Spacewings.PIXELS_TO_METERS), direction);
    }
}
