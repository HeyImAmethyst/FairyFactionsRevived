package com.heyimamethyst.fairyfactions.core.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.heyimamethyst.fairyfactions.FairyFactions;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = FairyFactions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	private static final Logger LOGGER = LogManager.getLogger();
	
	@SubscribeEvent
	public static void GatherData(GatherDataEvent event)
	{
			DataGenerator generator = event.getGenerator();
			
			ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

			PackOutput packOutput = generator.getPackOutput();
			Path outputFolder = packOutput.getOutputFolder();

			//RegistryAccess registries = RegistryAccess.builtinCopy();
			//RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, registries);
			Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.create();

		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new ModItemModelProvider(packOutput, existingFileHelper));
		//generator.addProvider(true, new ModWorldGenProvider(generator, existingFileHelper, ops));
	}
	
//	/* Helper method that serializes many objects of the same registry type for us (note the vararg)*/
//	@SafeVarargs
//	private static <T> DataProvider MakeBuiltinRegistryProvider(String modid, Path outputFolder, Gson gson, RegistryOps<JsonElement> ops, RegistryAccess registries, ResourceKey<Registry<T>> registryKey, Codec<T> codec, ResourceKey<T>... keys)
//	{
//		return new DataProvider()
//		{
//			@Override
//			public void run(HashCache cache) throws IOException
//			{
//				// 3) get the relevant registry from the registryaccess
//				// we must retrieve objects from registryaccess and not via our RegistryObject
//				// (as this is where RegistryOps looks up the id names of things to write out)
//				Registry<T> registry = registries.registryOrThrow(registryKey);
//				for (ResourceKey<T> key : keys)
//				{
//					ResourceLocation id = key.location();
//					Path path = outputFolder.resolve(String.join("/", PackType.SERVER_DATA.getDirectory(), id.getNamespace(), registryKey.location().getPath(), id.getPath()+".json"));
//					// 4) get the object from that registry
//					T t = registry.getOrThrow(key);
//					// 5) use the RegistryOps and direct codec to serialize it
//					// (as of 1.18.2 the indirect codec is no longer the correct codec for datagen)
//					codec.encodeStart(ops, t)
//						.resultOrPartial(msg -> LOGGER.error("Failed to encode {}: {}", path, msg))
//						.ifPresent(json -> {
//							try
//							{
//								DataProvider.save(gson, cache, json, path);
//							}
//							catch (IOException e) // we're inside this ifpresent so the throws can't deal with this
//							{
//								e.printStackTrace();
//							}
//						});
//				}
//			}
//
//			@Override
//			public String getName()
//			{
//				return modid + " " + registryKey.location().toString();
//			}
//		};
//	}
}
