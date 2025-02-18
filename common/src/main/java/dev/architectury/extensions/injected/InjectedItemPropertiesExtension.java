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

package dev.architectury.extensions.injected;

import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

public interface InjectedItemPropertiesExtension {
    @ApiStatus.Experimental
    default Item.Properties arch$tab(CreativeModeTab tab) {
        throw new UnsupportedOperationException();
    }
    
    @ApiStatus.Experimental
    default Item.Properties arch$tab(DeferredSupplier<CreativeModeTab> tab) {
        throw new UnsupportedOperationException();
    }
    
    @ApiStatus.Experimental
    default Item.Properties arch$tab(ResourceKey<CreativeModeTab> tab) {
        throw new UnsupportedOperationException();
    }
}
