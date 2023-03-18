package com.heyimamethyst.fairyfactions.events.mod;

import com.heyimamethyst.fairyfactions.FairyFactions;
import com.heyimamethyst.fairyfactions.client.model.ModModelLayers;
import com.heyimamethyst.fairyfactions.client.model.entity.FairyEyesModel;
import com.heyimamethyst.fairyfactions.client.model.entity.FairyModel;
import com.heyimamethyst.fairyfactions.client.model.entity.FairyProps2Model;
import com.heyimamethyst.fairyfactions.client.model.entity.FairyPropsModel;
import com.heyimamethyst.fairyfactions.client.render.entity.FairyFishHookEntityRenderer;
import com.heyimamethyst.fairyfactions.client.render.entity.FairyRenderer;
import com.heyimamethyst.fairyfactions.common.items.ModSpawnEggItem;
import com.heyimamethyst.fairyfactions.core.registry.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FairyFactions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD , value = Dist.CLIENT)
public class ModClientEvents
{
    @SubscribeEvent
	public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event)
	{
		ModSpawnEggItem.InitSpawnEggs();
	}
    
	@SubscribeEvent
	public static void doClientStuff(final FMLClientSetupEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		EntityRenderDispatcher manager = mc.getEntityRenderDispatcher();
		//ModKeyBinds.register(event);
	}
	
	@SubscribeEvent
	public static void RegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
	{
		event.registerLayerDefinition(ModModelLayers.FAIRY_LAYER_LOCATION, FairyModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.FAIRY_EYES_LAYER_LOCATION, FairyEyesModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.FAIRY_PROPS_LAYER_LOCATION, FairyPropsModel::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.FAIRY_PROPS2_LAYER_LOCATION, FairyProps2Model::createBodyLayer);
		event.registerLayerDefinition(ModModelLayers.FAIRY_WITHERED_LAYER_LOCATION, FairyModel::createBodyLayer);
	}
	
	@SubscribeEvent
	public static void RegisterRenderers(EntityRenderersEvent.RegisterRenderers event)
	{
		event.registerEntityRenderer(ModEntities.FAIRY_ENTITY.get(), FairyRenderer::new);
		event.registerEntityRenderer(ModEntities.FAIRY_FISHING_BOBBER_ENTITY.get(), FairyFishHookEntityRenderer::new);
	}
	
	@SubscribeEvent
	public static void AddRenderers(EntityRenderersEvent.AddLayers event)
	{
//        Map<String, EntityRenderer<? extends Player>> skinMapWings = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap();
//
//        for (PlayerRenderer render : new PlayerRenderer[]{(PlayerRenderer) skinMapWings.get("default"), (PlayerRenderer) skinMapWings.get("slim")})
//        {
//            render.addLayer(new WingsLayer(render, event.getEntityModels()));
//            render.addLayer(new SpellHandParticleLayer<>(render));
//        }
        
        //ModModelLayers.WITCH_HAT_MODEL = new WitchHatBase(event.getEntityModels().bakeLayer(ModModelLayers.WITCH_HAT_BASE_LAYER_LOCATION));
	}

}
