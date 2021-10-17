package com.github.crimsondawn45.fabricshieldlib.initializers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

@SuppressWarnings("deprecation")
public class FabricShieldLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        if(FabricLoader.getInstance().isDevelopmentEnvironment()) {

            //Warn about dev code
            FabricShieldLib.logger.warn("FABRIC SHIELD LIB DEVELOPMENT CODE RAN!!!, if you are not in a development environment this is very bad! Client side banner code ran!");

            /*
             * Registers sprite directories and model layer, will be done by player, dev code
             */
            EntityModelLayerRegistry.registerModelLayer(FabricShieldLib.fabric_shield_model_layer, ShieldEntityModel::getTexturedModelData);
            ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
                registry.register(new Identifier(FabricShieldLib.MOD_ID, "entity/fabric_shield_base"));
                registry.register(new Identifier(FabricShieldLib.MOD_ID, "entity/fabric_shield_base_nopattern"));
            });
        }
    }
}
