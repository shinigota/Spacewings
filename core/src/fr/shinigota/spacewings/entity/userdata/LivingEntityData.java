package fr.shinigota.spacewings.entity.userdata;

/**
 * Created by Benjamin on 07/02/2017.
 */
public class LivingEntityData {
    public float normalResistance;
    public float tangentResistance;
    public int health;

    public LivingEntityData(float normalResistance, float tangentResistance, int health) {
        this.normalResistance = normalResistance;
        this.tangentResistance = tangentResistance;
        this.health = health;
    }
}
