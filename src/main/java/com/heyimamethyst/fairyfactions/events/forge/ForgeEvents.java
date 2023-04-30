package com.heyimamethyst.fairyfactions.events.forge;

import com.heyimamethyst.fairyfactions.FairyFactions;
import com.heyimamethyst.fairyfactions.common.entities.FairyEntity;
import com.heyimamethyst.fairyfactions.core.proxy.ClientMethods;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.*;

@Mod.EventBusSubscriber(modid = FairyFactions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents
{
	@SubscribeEvent
	public void onRenderName(RenderNameplateEvent event)
	{
		Entity entity = event.getEntity();

		if(entity instanceof FairyEntity)
		{
			FairyEntity fairy = (FairyEntity) entity;

			if (fairy.getFaction() != 0)
			{
				event.setResult(Event.Result.ALLOW);
			}
			else if (fairy.tamed())
			{

				if (fairy.isRuler(ClientMethods.getCurrentPlayer()))
				{
					event.setResult(Event.Result.ALLOW);
				}
				else
				{
					event.setResult(Event.Result.DEFAULT);
				}
			}
			else
			{
				event.setResult(Event.Result.DEFAULT);
			}
		}

	}

//	@SuppressWarnings("unchecked")
//	@SubscribeEvent
//	public static void OnFairySpawned(EntityJoinWorldEvent e)
//	{
//		Entity entity = e.getEntity();
//
//		if( entity != null && !entity.level.isClientSide)
//		{
//			if(entity instanceof FairyEntity)
//			{
//				FairyEntity fairy = (FairyEntity) entity;
//
//				boolean doCreateGroup = false; //= fairy.getRandom().nextInt(2) != 0;
//
//				//int a = new Random().nextInt(11);
//
//				if (percentChance(fairy, 0.8))
//				{
//					doCreateGroup = true;
//				}
//
//				if(fairy.getRuler() == null && !fairy.isRuler(Minecraft.getInstance().player) && fairy.getFaction() == 0 && doCreateGroup)
//				{
//					List list = fairy.level.getEntitiesOfClass(FairyEntity.class,
//							fairy.getBoundingBox().inflate(32D, 32D, 32D));
//
//					if (( list == null || list.size() < 1 ) && !fairy.level.isClientSide)
//					{
//						fairy.setJob(0);
//						fairy.setSpecialJob(true);
//						fairy.heal(30);
//						fairy.setHealth(30);
//						int i = fairy.getRandom().nextInt(15) + 1;
//						fairy.setFaction(i);
//						fairy.setSkin(fairy.getRandom().nextInt(4));
//						fairy.setCower(false);
//						fairy.createGroup = true;
//					}
//				}
//			}
//		}
//	}
}
