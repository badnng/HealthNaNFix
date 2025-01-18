package team.teampotato.HealthNaNFix.Event;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LivingEntity;
import team.teampotato.HealthNaNFix.HealthNaNFix;
import team.teampotato.HealthNaNFix.HealthNaNFixConfig;

public class HealthNaNFixEvent implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof LivingEntity livingEntity) {
                getNaNEntity(livingEntity);
            } else {
                if (HealthNaNFixConfig.debug.equals("true")) {
                    HealthNaNFix.I.warn("[HealthNaNFix-debug] Loaded an entity that is not a LivingEntity: " + entity.getClass().getName());
                }
            }
        });
    }
    public static void getNaNEntity(LivingEntity entity){
        if (entity.getHealth() == (0.0F)) { //如果血量是0
            entity.setHealth(0.0F); //设置为0
            if(HealthNaNFixConfig.debug.equals("true")) {
                HealthNaNFix.I.error("[HealthNaNFix-debug]Killed an entity with NaN health");
            }
        }
        if (Float.isNaN(entity.getHealth())) {
            entity.setHealth(0.0F);
            if(HealthNaNFixConfig.debug.equals("true")) {
                HealthNaNFix.I.error("[HealthNaNFix-debug]Killed an entity with NaN health");
            }
        }
        if (Float.isNaN(entity.getAbsorptionAmount())) {
            entity.setAbsorptionAmount(0);
            if(HealthNaNFixConfig.debug.equals("true")) {
                HealthNaNFix.I.error("[HealthNaNFix-debug]Killed an entity with NaN health");
            }
        }
    }
    //谢谢土豆
}