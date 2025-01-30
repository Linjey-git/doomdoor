package ua.seho.doomdoor.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ua.seho.doomdoor.Doomdoor;

public class ModBlocks {

    private static Block registerBlock(String id, Block block) {
        registerBlockItem(id, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Doomdoor.MOD_ID, id), block);
    }

    private static void registerBlockItem(String id, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Doomdoor.MOD_ID, id),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Doomdoor.LOGGER.info("Registering Blocks for " + Doomdoor.MOD_ID);
    }
}
