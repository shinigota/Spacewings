package fr.shinigota.spacewings.renderable;

import fr.shinigota.spacewings.Spacewings;


/**
 * Created by benjamin on 2/4/17.
 */
public abstract class Renderable {
    private final Spacewings spacewings;

    public Renderable(Spacewings spacewings) {
        this.spacewings = spacewings;
    }

    public abstract void update(float delta);

}
