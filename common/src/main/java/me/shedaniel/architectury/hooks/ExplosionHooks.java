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

package me.shedaniel.architectury.hooks;

import me.shedaniel.architectury.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public final class ExplosionHooks {
    private ExplosionHooks() {}
    
    @ExpectPlatform
    public static Vec3 getPosition(Explosion explosion) {
        throw new AssertionError();
    }
    
    @Nullable
    @ExpectPlatform
    public static Entity getSource(Explosion explosion) {
        throw new AssertionError();
    }
    
    @ExpectPlatform
    public static float getRadius(Explosion explosion) {
        throw new AssertionError();
    }
    
    @ExpectPlatform
    public static void setRadius(Explosion explosion, float radius) {
        throw new AssertionError();
    }
}
