/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.common.crafting;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Wrapper around a {@link RecipeOutput} that adds conditions to all received recipes.
 * Do not use directly, obtain via {@link RecipeOutput#withConditions(ICondition...)}.
 */
@ApiStatus.Internal
public class ConditionalRecipeOutput implements RecipeOutput {
    private final RecipeOutput inner;
    private final ICondition[] conditions;

    public ConditionalRecipeOutput(RecipeOutput inner, ICondition[] conditions) {
        this.inner = inner;
        this.conditions = conditions;
    }

    @Override
    public Advancement.Builder advancement() {
        return inner.advancement();
    }

    @Override
    public void accept(ResourceKey<Recipe<?>> id, Recipe<?> recipe, @Nullable AdvancementHolder advancement, ICondition... conditions) {
        ICondition[] innerConditions;
        if (conditions.length == 0) {
            innerConditions = this.conditions;
        } else if (this.conditions.length == 0) {
            innerConditions = conditions;
        } else {
            innerConditions = ArrayUtils.addAll(this.conditions, conditions);
        }
        inner.accept(id, recipe, advancement, innerConditions);
    }

    @Override
    public void includeRootAdvancement() {
        inner.includeRootAdvancement();
    }
}
