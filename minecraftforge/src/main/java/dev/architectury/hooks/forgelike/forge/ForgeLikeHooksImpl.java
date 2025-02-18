/*
 * This file is part of architectury.
 * Copyright (C) 2020, 2021, 2022 architectury
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

package dev.architectury.hooks.forgelike.forge;

import com.mojang.serialization.Codec;
import dev.architectury.platform.hooks.EventBusesHooks;
import dev.architectury.utils.ArchitecturyConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.Supplier;

public class ForgeLikeHooksImpl {
    public static void registerBiomeModifier(ResourceLocation id, Supplier<Codec<? extends BiomeModifier>> codecSupplier) {
        EventBusesHooks.whenAvailable(ArchitecturyConstants.MOD_ID, bus -> {
            bus.<RegisterEvent>addListener(event -> {
                event.register(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, registry -> {
                    registry.register(id, codecSupplier.get());
                });
            });
        });
    }
    
    public static void registerBucketItemCapability(Item item) {
    }
}
