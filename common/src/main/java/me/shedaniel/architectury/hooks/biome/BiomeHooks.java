/*
 * This file is part of architectury.
 * Copyright (C) 2020, 2021 shedaniel
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package me.shedaniel.architectury.hooks.biome;

import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.BiomeSpecialEffects.GrassColorModifier;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Supplier;

public final class BiomeHooks {
    @NotNull
    public static BiomeProperties getBiomeProperties(Biome biome) {
        return new BiomeWrapped(biome);
    }
    
    public static class BiomeWrapped implements BiomeProperties {
        protected final Biome biome;
        protected final ClimateProperties climateProperties;
        protected final EffectsProperties effectsProperties;
        protected final GenerationProperties generationProperties;
        protected final SpawnProperties spawnProperties;
        
        public BiomeWrapped(Biome biome) {
            this(biome,
                    new ClimateWrapped(biome),
                    new EffectsWrapped(biome),
                    new GenerationSettingsWrapped(biome),
                    new SpawnSettingsWrapped(biome));
        }
        
        public BiomeWrapped(Biome biome,
                ClimateProperties climateProperties,
                EffectsProperties effectsProperties,
                GenerationProperties generationProperties,
                SpawnProperties spawnProperties) {
            this.biome = biome;
            this.climateProperties = climateProperties;
            this.effectsProperties = effectsProperties;
            this.generationProperties = generationProperties;
            this.spawnProperties = spawnProperties;
        }
        
        @Override
        @NotNull
        public ClimateProperties getClimateProperties() {
            return climateProperties;
        }
        
        @Override
        @NotNull
        public EffectsProperties getEffectsProperties() {
            return effectsProperties;
        }
        
        @NotNull
        @Override
        public GenerationProperties getGenerationProperties() {
            return generationProperties;
        }
        
        @NotNull
        @Override
        public SpawnProperties getSpawnProperties() {
            return spawnProperties;
        }
        
        @Override
        @NotNull
        public BiomeCategory getCategory() {
            return biome.biomeCategory;
        }
        
        @Override
        public float getDepth() {
            return biome.depth;
        }
        
        @Override
        public float getScale() {
            return biome.scale;
        }
    }
    
    public static class MutableBiomeWrapped extends BiomeWrapped implements BiomeProperties.Mutable {
        public MutableBiomeWrapped(Biome biome,
                GenerationProperties.Mutable generationProperties,
                SpawnProperties.Mutable spawnProperties) {
            this(biome,
                    new ClimateWrapped(biome.climateSettings),
                    new EffectsWrapped(biome.getSpecialEffects()),
                    generationProperties,
                    spawnProperties);
        }
        
        public MutableBiomeWrapped(Biome biome,
                ClimateProperties.Mutable climateProperties,
                EffectsProperties.Mutable effectsProperties,
                GenerationProperties.Mutable generationProperties,
                SpawnProperties.Mutable spawnProperties) {
            super(biome,
                    climateProperties,
                    effectsProperties,
                    generationProperties,
                    spawnProperties);
        }
        
        @Override
        public @NotNull ClimateProperties.Mutable getClimateProperties() {
            return (ClimateProperties.Mutable) super.getClimateProperties();
        }
        
        @Override
        public @NotNull EffectsProperties.Mutable getEffectsProperties() {
            return (EffectsProperties.Mutable) super.getEffectsProperties();
        }
        
        @Override
        public @NotNull GenerationProperties.Mutable getGenerationProperties() {
            return (GenerationProperties.Mutable) super.getGenerationProperties();
        }
        
        @Override
        public @NotNull SpawnProperties.Mutable getSpawnProperties() {
            return (SpawnProperties.Mutable) super.getSpawnProperties();
        }
        
        @Override
        @NotNull
        public Mutable setCategory(@NotNull BiomeCategory category) {
            biome.biomeCategory = category;
            return this;
        }
        
        @Override
        @NotNull
        public Mutable setDepth(float depth) {
            biome.depth = depth;
            return this;
        }
        
        @Override
        @NotNull
        public Mutable setScale(float scale) {
            biome.scale = scale;
            return this;
        }
    }
    
    public static class ClimateWrapped implements ClimateProperties.Mutable {
        protected final Biome.ClimateSettings climateSettings;
        
        public ClimateWrapped(Biome biome) {
            this(biome.climateSettings);
        }
        
        public ClimateWrapped(Biome.ClimateSettings climateSettings) {
            this.climateSettings = climateSettings;
        }
        
        @Override
        @NotNull
        public Mutable setPrecipitation(@NotNull Biome.Precipitation precipitation) {
            climateSettings.precipitation = precipitation;
            return this;
        }
        
        @Override
        @NotNull
        public Mutable setTemperature(float temperature) {
            climateSettings.temperature = temperature;
            return this;
        }
        
        @Override
        @NotNull
        public Mutable setTemperatureModifier(@NotNull Biome.TemperatureModifier temperatureModifier) {
            climateSettings.temperatureModifier = temperatureModifier;
            return this;
        }
        
        @Override
        @NotNull
        public Mutable setDownfall(float downfall) {
            climateSettings.downfall = downfall;
            return this;
        }
        
        @Override
        @NotNull
        public Biome.Precipitation getPrecipitation() {
            return climateSettings.precipitation;
        }
        
        @Override
        public float getTemperature() {
            return climateSettings.temperature;
        }
        
        @Override
        @NotNull
        public Biome.TemperatureModifier getTemperatureModifier() {
            return climateSettings.temperatureModifier;
        }
        
        @Override
        public float getDownfall() {
            return climateSettings.downfall;
        }
    }
    
    public static class EffectsWrapped implements EffectsProperties.Mutable {
        protected final BiomeSpecialEffects effects;
        
        public EffectsWrapped(Biome biome) {
            this(biome.getSpecialEffects());
        }
        
        public EffectsWrapped(BiomeSpecialEffects effects) {
            this.effects = effects;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setFogColor(int color) {
            effects.fogColor = color;
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setWaterColor(int color) {
            effects.waterColor = color;
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setWaterFogColor(int color) {
            effects.waterFogColor = color;
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setSkyColor(int color) {
            effects.skyColor = color;
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setFoliageColorOverride(@Nullable Integer colorOverride) {
            effects.foliageColorOverride = Optional.ofNullable(colorOverride);
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setGrassColorOverride(@Nullable Integer colorOverride) {
            effects.grassColorOverride = Optional.ofNullable(colorOverride);
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setGrassColorModifier(@NotNull GrassColorModifier modifier) {
            effects.grassColorModifier = modifier;
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setAmbientParticle(@Nullable AmbientParticleSettings settings) {
            effects.ambientParticleSettings = Optional.ofNullable(settings);
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setAmbientLoopSound(@Nullable SoundEvent sound) {
            effects.ambientLoopSoundEvent = Optional.ofNullable(sound);
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setAmbientMoodSound(@Nullable AmbientMoodSettings settings) {
            effects.ambientMoodSettings = Optional.ofNullable(settings);
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setAmbientAdditionsSound(@Nullable AmbientAdditionsSettings settings) {
            effects.ambientAdditionsSettings = Optional.ofNullable(settings);
            return this;
        }
        
        @Override
        @NotNull
        public EffectsProperties.Mutable setBackgroundMusic(@Nullable Music music) {
            effects.backgroundMusic = Optional.ofNullable(music);
            return this;
        }
        
        @Override
        public int getFogColor() {
            return effects.fogColor;
        }
        
        @Override
        public int getWaterColor() {
            return effects.waterColor;
        }
        
        @Override
        public int getWaterFogColor() {
            return effects.waterFogColor;
        }
        
        @Override
        public int getSkyColor() {
            return effects.skyColor;
        }
        
        @Override
        @NotNull
        public OptionalInt getFoliageColorOverride() {
            return effects.foliageColorOverride.map(OptionalInt::of).orElseGet(OptionalInt::empty);
        }
        
        @Override
        @NotNull
        public OptionalInt getGrassColorOverride() {
            return effects.grassColorOverride.map(OptionalInt::of).orElseGet(OptionalInt::empty);
        }
        
        @Override
        @NotNull
        public GrassColorModifier getGrassColorModifier() {
            return effects.grassColorModifier;
        }
        
        @Override
        @NotNull
        public Optional<AmbientParticleSettings> getAmbientParticle() {
            return effects.ambientParticleSettings;
        }
        
        @Override
        @NotNull
        public Optional<SoundEvent> getAmbientLoopSound() {
            return effects.ambientLoopSoundEvent;
        }
        
        @Override
        @NotNull
        public Optional<AmbientMoodSettings> getAmbientMoodSound() {
            return effects.ambientMoodSettings;
        }
        
        @Override
        @NotNull
        public Optional<AmbientAdditionsSettings> getAmbientAdditionsSound() {
            return effects.ambientAdditionsSettings;
        }
        
        @Override
        @NotNull
        public Optional<Music> getBackgroundMusic() {
            return effects.backgroundMusic;
        }
    }
    
    public static class GenerationSettingsWrapped implements GenerationProperties {
        protected final BiomeGenerationSettings settings;
        
        public GenerationSettingsWrapped(Biome biome) {
            this(biome.getGenerationSettings());
        }
        
        public GenerationSettingsWrapped(BiomeGenerationSettings settings) {
            this.settings = settings;
        }
        
        @Override
        public @NotNull Optional<Supplier<ConfiguredSurfaceBuilder<?>>> getSurfaceBuilder() {
            return Optional.ofNullable(settings.getSurfaceBuilder());
        }
        
        @Override
        public @NotNull List<Supplier<ConfiguredWorldCarver<?>>> getCarvers(GenerationStep.Carving carving) {
            return settings.getCarvers(carving);
        }
        
        @Override
        public @NotNull List<List<Supplier<ConfiguredFeature<?, ?>>>> getFeatures() {
            return settings.features();
        }
        
        @Override
        public @NotNull List<Supplier<ConfiguredStructureFeature<?, ?>>> getStructureStarts() {
            return (List<Supplier<ConfiguredStructureFeature<?, ?>>>) settings.structures();
        }
    }
    
    public static class SpawnSettingsWrapped implements SpawnProperties {
        protected final MobSpawnSettings settings;
        
        public SpawnSettingsWrapped(Biome biome) {
            this(biome.getMobSettings());
        }
        
        public SpawnSettingsWrapped(MobSpawnSettings settings) {
            this.settings = settings;
        }
        
        @Override
        public float getCreatureProbability() {
            return this.settings.getCreatureProbability();
        }
        
        @Override
        @NotNull
        public Map<MobCategory, List<MobSpawnSettings.SpawnerData>> getSpawners() {
            return null;
        }
        
        @Override
        @NotNull
        public Map<EntityType<?>, MobSpawnSettings.MobSpawnCost> getMobSpawnCosts() {
            return null;
        }
        
        @Override
        public boolean isPlayerSpawnFriendly() {
            return this.settings.playerSpawnFriendly();
        }
    }
}
