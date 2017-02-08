package fr.shinigota.spacewings.entity.data;

/**
 * Created by Benjamin on 07/02/2017.
 */
public class CollidableData {
    public float normalResistance;
    public float tangentResistance;
    public int health;

    public CollidableData(float normalResistance, float tangentResistance, int health) {
        this.normalResistance = normalResistance;
        this.tangentResistance = tangentResistance;
        this.health = health;
    }
}
