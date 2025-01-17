/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.event.entity.player;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.Nullable;

/**
 * PlayerDestroyItemEvent is fired when a player destroys an item.<br>
 * This event is fired whenever a player destroys an item in
 * {@link MultiPlayerGameMode#destroyBlock(BlockPos)},
 * {@link MultiPlayerGameMode#useItem(Player, InteractionHand)},
 * {@link MultiPlayerGameMode#useItemOn(LocalPlayer, InteractionHand, BlockHitResult)} ,
 * {@link Player#attack(Entity)},
 * {@code Player#hurtCurrentlyUsedShield(float)},
 * {@link Player#interactOn(Entity, InteractionHand)},
 * {@link CommonHooks#getCraftingRemainder(ItemStack)},
 * {@link ServerPlayerGameMode#useItem(ServerPlayer, Level, ItemStack, InteractionHand)} ,
 * {@link ServerPlayerGameMode#useItemOn(ServerPlayer, Level, ItemStack, InteractionHand, BlockHitResult)}
 * and {@link ServerPlayerGameMode#destroyBlock(BlockPos)}.<br>
 * <br>
 * {@link #original} contains the original ItemStack before the item was destroyed. <br>
 * (@link #hand) contains the hand that the current item was held in.<br>
 * <br>
 * This event is not {@link ICancellableEvent}.<br>
 * <br>
 * This event does not have a result. {@link HasResult}<br>
 * <br>
 * This event is fired from {@link EventHooks#onPlayerDestroyItem(Player, ItemStack, InteractionHand)}.<br>
 * This event is fired on the {@link NeoForge#EVENT_BUS}.
 **/
public class PlayerDestroyItemEvent extends PlayerEvent {
    private final ItemStack original;
    @Nullable
    private final InteractionHand hand; // May be null if this player destroys the item by any use besides holding it.

    public PlayerDestroyItemEvent(Player player, ItemStack original, @Nullable InteractionHand hand) {
        super(player);
        this.original = original;
        this.hand = hand;
    }

    public ItemStack getOriginal() {
        return this.original;
    }

    @Nullable
    public InteractionHand getHand() {
        return this.hand;
    }
}
