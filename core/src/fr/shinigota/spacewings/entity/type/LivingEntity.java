package fr.shinigota.spacewings.entity.type;

import fr.shinigota.spacewings.entity.userdata.LivingEntityData;

/**
 * Created by Benjamin on 07/02/2017.
 */
public interface LivingEntity {
    LivingEntityData initData();
    void onCollision();
}
