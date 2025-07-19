package net.danygames2014.unitweaks.util;

import com.google.common.collect.ImmutableMap;
import net.danygames2014.unitweaks.tweaks.moresounds.ChestSoundsEnum;
import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigFactoryProvider;
import net.glasslauncher.mods.gcapi3.impl.SeptFunction;
import net.glasslauncher.mods.gcapi3.impl.factory.DefaultFactoryProvider;
import net.glasslauncher.mods.gcapi3.impl.object.ConfigEntryHandler;
import net.glasslauncher.mods.gcapi3.impl.object.entry.EnumConfigEntryHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.function.Function;

public class ConfigFactories implements ConfigFactoryProvider {

    @Override
    public void provideLoadFactories(ImmutableMap.Builder<Type, SeptFunction<String, ConfigEntry, Field, Object, Boolean, Object, Object, ConfigEntryHandler<?>>> immutableBuilder) {
        immutableBuilder.put(ChestSoundsEnum.class, ((id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrOrdinal, defaultEnum) -> new EnumConfigEntryHandler<ChestSoundsEnum>(id, configEntry, parentField, parentObject, isMultiplayerSynced, DefaultFactoryProvider.enumOrOrdinalToOrdinal(enumOrOrdinal), ((ChestSoundsEnum) defaultEnum).ordinal(), ChestSoundsEnum.class)));
        
    }

    @Override
    public void provideSaveFactories(ImmutableMap.Builder<Type, Function<Object, Object>> immutableBuilder) {
        immutableBuilder.put(ChestSoundsEnum.class, object -> object);
    }
}
