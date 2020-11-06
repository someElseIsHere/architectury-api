/*
 * Copyright 2020 shedaniel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.shedaniel.architectury.event.events;

import com.mojang.brigadier.CommandDispatcher;
import me.shedaniel.architectury.event.Event;
import me.shedaniel.architectury.event.EventFactory;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public interface CommandRegistrationEvent {
    /**
     * Invoked after server registers its commands, equivalent to forge's {@code RegisterCommandsEvent} and fabric's {@code CommandRegistrationCallback}.
     */
    Event<CommandRegistrationEvent> EVENT = EventFactory.createLoop(CommandRegistrationEvent.class);
    
    void register(CommandDispatcher<CommandSourceStack> dispatcher, Commands.CommandSelection selection);
}