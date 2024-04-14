package net.danygames2014.unitweaks.util;

import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonPrimitive;
import com.google.common.collect.ImmutableMap;
import net.glasslauncher.mods.api.gcapi.api.ConfigFactoryProvider;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;
import net.glasslauncher.mods.api.gcapi.impl.NonFunction;
import net.glasslauncher.mods.api.gcapi.impl.config.ConfigEntry;
import net.glasslauncher.mods.api.gcapi.impl.config.entry.EnumConfigEntry;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.function.Function;

public class BrightnessRangeEnumFactory implements ConfigFactoryProvider {
    @Override
    public void provideLoadFactories(ImmutableMap.Builder<Type, NonFunction<String, String, String, Field, Object, Boolean, Object, Object, MaxLength, ConfigEntry<?>>> immutableBuilder) {
        immutableBuilder.put(BrightnessRangeEnum.class, ((id, name, description, parentField, parentObject, isMultiplayerSynced, enumOrOrdinal, defaultEnum, maxLength) ->
        {
            int enumOrdinal;
            if(enumOrOrdinal instanceof Integer ordinal) {
                enumOrdinal = ordinal;
            }
            else {
                enumOrdinal = ((BrightnessRangeEnum) enumOrOrdinal).ordinal();
            }
            return new EnumConfigEntry<BrightnessRangeEnum>(id, name, description, parentField, parentObject, isMultiplayerSynced, enumOrdinal, ((BrightnessRangeEnum) defaultEnum).ordinal(), BrightnessRangeEnum.class);
        }));
    }

    @Override
    public void provideSaveFactories(ImmutableMap.Builder<Type, Function<Object, JsonElement>> immutableBuilder) {
        immutableBuilder.put(BrightnessRangeEnum.class, enumEntry -> new JsonPrimitive(((BrightnessRangeEnum) enumEntry).ordinal()));
    }

    @Override
    public void provideLoadTypeAdapterFactories(@SuppressWarnings("rawtypes") ImmutableMap.Builder<Type, Class> immutableBuilder) {
        immutableBuilder.put(BrightnessRangeEnum.class, Integer.class);
    }
}
