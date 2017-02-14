package fr.shinigota.spacewings.fixture;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.TimeUtils;
import fr.shinigota.spacewings.Spacewings;
import fr.shinigota.spacewings.entity.ship.Player;
import fr.shinigota.spacewings.entity.ship.Projectile;
import fr.shinigota.spacewings.entity.tool.FixtureType;

/**
 * Created by benjamin on 2/11/17.
 */
public class Turret {
    private static final long RATE_OF_FIRE = 200;
    private static final float SIZE = 10;
    private final Player player;
    private Vector2 relativePosition;

    private long lastShootTime;

    public Turret(Player player) {
        this.player = player;
        CircleShape circleShape = new CircleShape();
        this.relativePosition = new Vector2(-20* Spacewings.PIXELS_TO_METERS,20*Spacewings.PIXELS_TO_METERS);
        circleShape.setPosition(relativePosition);
        circleShape.setRadius((SIZE*Spacewings.PIXELS_TO_METERS)/2);

        FixtureDef fixtureDef = FixtureType.ZERO.fixtureDef;
        fixtureDef.shape = circleShape;
        circleShape.dispose();

        this.lastShootTime = 0;
    }

    public boolean canShoot() {
        return TimeUtils.millis() >= RATE_OF_FIRE + lastShootTime;
    }

    public Projectile shoot() {
        this.lastShootTime = TimeUtils.millis();
        float angle = this.player.getBody().getAngle() * MathUtils.radiansToDegrees;
        Vector2 direction = Vector2.Y.cpy().rotate(angle);
        Vector2 projectileOrigin = relativePosition.cpy().rotate(player.getAngle() * MathUtils.radiansToDegrees).add(player.getPosition());
        return new Projectile(this.player.getBody().getWorld(), projectileOrigin, new Vector2(5*Spacewings.PIXELS_TO_METERS, 10*Spacewings.PIXELS_TO_METERS), direction);
    }
}
