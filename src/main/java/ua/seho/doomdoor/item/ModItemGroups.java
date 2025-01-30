package ua.seho.doomdoor.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ua.seho.doomdoor.Doomdoor;
import ua.seho.doomdoor.block.ModBlocks;

public class ModItemGroups {

    public static final ItemGroup DOOMDOOR_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Doomdoor.MOD_ID, "doomdoor_group"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.NETHER_REACTOR_CORE))
                    .displayName(Text.translatable("itemgroup.doomdoor.doomdoor_group"))
                    .entries(((displayContext, entries) -> {
                        entries.add(ModBlocks.NETHER_REACTOR_CORE);
                        //Додати предмети сюди
                    }))
                    .build());

    public static void registerModItemGroups() {
        Doomdoor.LOGGER.info("Registering Blocks for " + Doomdoor.MOD_ID);
    }
}
