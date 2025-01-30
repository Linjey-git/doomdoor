package ua.seho.doomdoor.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class NetherReactorCoreBlock extends Block {
    public static final EnumProperty<ReactorState> STATE = EnumProperty.of("state", ReactorState.class);

    public NetherReactorCoreBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(STATE, ReactorState.NEUTRAL));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STATE);
    }

    // Метод для перемикання стану
    public void changeState(World world, BlockPos pos, BlockState state, ReactorState newState) {
        world.setBlockState(pos, state.with(STATE, newState), 3);
    }

    // Приклад взаємодії гравця
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            if (!isValidStructure(world, pos)) {
                player.sendMessage(Text.literal("Multiblock structure is incorrect!").formatted(Formatting.RED), false);
                return ActionResult.FAIL;
            }

            ReactorState currentState = state.get(STATE);
            ReactorState nextState = switch (currentState) {
                case NEUTRAL -> ReactorState.ACTIVE;
                case ACTIVE -> ReactorState.INACTIVE;
                case INACTIVE -> ReactorState.NEUTRAL;
            };
            changeState(world, pos, state, nextState);
            player.sendMessage(Text.literal("Reactor is now " + nextState.asString()), false);
        }
        return ActionResult.SUCCESS;
    }

    private boolean isValidStructure(World world, BlockPos pos) {
        return isValidGoldPlacement(world, pos) && isValidCobblePlacement(world, pos) && isValidRedStoneTorchPlacement(world, pos);
    }

    private boolean isValidGoldPlacement(World world, BlockPos pos) {
        // Список координат кутових блоків на рівень нижче
        BlockPos[] corners = {
                pos.add(-1, -1, -1), // Лівий задній кут
                pos.add(-1, -1, 1),  // Лівий передній кут
                pos.add(1, -1, -1),  // Правий задній кут
                pos.add(1, -1, 1)    // Правий передній кут
        };

        for (BlockPos cornerPos : corners) {
            BlockState cornerState = world.getBlockState(cornerPos);
            if (cornerState.getBlock() != Blocks.GOLD_BLOCK) {
                return false; // Якщо хоч один блок не залізний, структура неправильна
            }
        }
        return true; // Всі кути правильні
    }

    private boolean isValidCobblePlacement(World world, BlockPos pos) {
        // Список позицій центрального блоку та країв (без кутів)
        BlockPos[] edges = {
                //Нижній рівень
                pos.add(0, -1, 0),  // Центральний блок
                pos.add(-1, -1, 0), // Лівий край
                pos.add(1, -1, 0),  // Правий край
                pos.add(0, -1, -1), // Верхній край
                pos.add(0, -1, 1),  // Нижній край

                //Спільний рівень
                pos.add(-1, 0, -1), // Лівий задній кут
                pos.add(-1, 0, 1),  // Лівий передній кут
                pos.add(1, 0, -1),  // Правий задній кут
                pos.add(1, 0, 1),    // Правий передній кут

                //Вищий рівень
                pos.add(0, 1, 0),  // Центральний блок
                pos.add(-1, 1, 0), // Лівий край
                pos.add(1, 1, 0),  // Правий край
                pos.add(0, 1, -1), // Верхній край
                pos.add(0, 1, 1),  // Нижній край
        };

        for (BlockPos edgePos : edges) {
            BlockState edgeState = world.getBlockState(edgePos);
            if (edgeState.getBlock() != Blocks.COBBLESTONE && edgeState.getBlock() != Blocks.COBBLED_DEEPSLATE) {
                return false; // Якщо хоч один блок не залізний, структура неправильна
            }
        }
        return true; // Всі краї правильні
    }

    private boolean isValidRedStoneTorchPlacement(World world, BlockPos pos) {
        // Список координат кутових блоків на рівень нижче
        BlockPos[] corners = {
                pos.add(-1, 1, -1), // Лівий задній кут
                pos.add(-1, 1, 1),  // Лівий передній кут
                pos.add(1, 1, -1),  // Правий задній кут
                pos.add(1, 1, 1)    // Правий передній кут
        };

        for (BlockPos cornerPos : corners) {
            BlockState cornerState = world.getBlockState(cornerPos);
            if (cornerState.getBlock() != Blocks.REDSTONE_TORCH) {
                return false; // Якщо хоч один блок не залізний, структура неправильна
            }
        }
        return true; // Всі кути правильні
    }
}