package net.pokepalms.palmsmod.placeholder;

import eu.pb4.placeholders.api.PlaceholderResult;
import eu.pb4.placeholders.api.Placeholders;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModPlaceholders {

    public static void initialize() {
        Placeholders.register(new Identifier("palmsmod", "inventory_slot/item_id"), (ctx, arg) -> {
            if (ctx.hasPlayer() && arg != null) {
                try {
                    int slot = Integer.parseInt(arg);

                    var inventory = ctx.player().getInventory();

                    if (slot >= 0 && slot < inventory.size()) {
                        var stack = inventory.getStack(slot);

                        return PlaceholderResult.value(Registries.ITEM.getId(stack.getItem()).toString());
                    }
                }
                catch (Exception e) {
                    // noop
                }
                return PlaceholderResult.invalid("Invalid argument");
            } else {
                return PlaceholderResult.invalid("No player or invalid argument!");
            }
        });

        Placeholders.register(new Identifier("palmsmod", "inventory_slot/item_nbt"), (ctx, arg) -> {
            if (ctx.hasPlayer() && arg != null) {
                try {
                    int slot = Integer.parseInt(arg);

                    var inventory = ctx.player().getInventory();

                    if (slot >= 0 && slot < inventory.size()) {
                        var stack = inventory.getStack(slot);

                        return PlaceholderResult.value(stack.getOrCreateNbt().toString());
                    }
                }
                catch (Exception e) {
                    // noop
                }
                return PlaceholderResult.invalid("Invalid argument");
            } else {
                return PlaceholderResult.invalid("No player or invalid argument!");
            }
        });

        Placeholders.register(new Identifier("palmsmod", "equipment_slot/item_id"), (ctx, arg) -> {
            if (ctx.player() != null && arg != null) {
                try {
                    var slot = EquipmentSlot.byName(arg);

                    var stack = ctx.player().getEquippedStack(slot);
                    return PlaceholderResult.value(Registries.ITEM.getId(stack.getItem()).toString());
                }
                catch (Exception e) {
                    // noop
                }
                return PlaceholderResult.invalid("Invalid argument");
            } else {
                return PlaceholderResult.invalid("No player or invalid argument!");
            }
        });

        Placeholders.register(new Identifier("palmsmod", "equipment_slot/item_nbt"), (ctx, arg) -> {
            if (ctx.player() != null && arg != null) {
                try {
                    var slot = EquipmentSlot.byName(arg);

                    var stack = ctx.player().getEquippedStack(slot);
                    return PlaceholderResult.value(stack.getOrCreateNbt().toString());
                }
                catch (Exception e) {
                    // noop
                }
                return PlaceholderResult.invalid("Invalid argument");
            } else {
                return PlaceholderResult.invalid("No player or invalid argument!");
            }
        });
    }
}
