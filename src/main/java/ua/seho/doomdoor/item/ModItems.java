package ua.seho.doomdoor.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ua.seho.doomdoor.Doomdoor;

public class ModItems {

    private static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Doomdoor.MOD_ID, id), item);
    }

    public static void registerModItems() {
        Doomdoor.LOGGER.info("Registering Items for " + Doomdoor.MOD_ID);
    }
}
