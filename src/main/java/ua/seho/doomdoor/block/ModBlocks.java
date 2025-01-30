package ua.seho.doomdoor.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import ua.seho.doomdoor.Doomdoor;
import ua.seho.doomdoor.block.custom.NetherReactorCoreBlock;

public class ModBlocks {

    public static final Block NETHER_REACTOR_CORE = registerBlock("nether_reactor_core",
            new NetherReactorCoreBlock(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.COPPER).registryKey(getBlockKey("nether_reactor_core"))));

    private static RegistryKey<Block> getBlockKey(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Doomdoor.MOD_ID, name));
    }

    private static Block registerBlock(String name, Block block) {
//        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Doomdoor.MOD_ID, name));
//        block.setSettings(block.getSettings().registryKey(key));

        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Doomdoor.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Doomdoor.MOD_ID, name));
        Registry.register(Registries.ITEM, Identifier.of(Doomdoor.MOD_ID, name),
                new BlockItem(block, new Item.Settings().registryKey(itemKey)));
    }

    public static void registerModBlocks() {
        Doomdoor.LOGGER.info("Registering Blocks for " + Doomdoor.MOD_ID);
    }
}
