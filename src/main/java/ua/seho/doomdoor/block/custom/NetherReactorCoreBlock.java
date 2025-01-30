package ua.seho.doomdoor.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
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

//    @Override
//    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//        if (!world.isClient) {
//            ReactorState currentState = state.get(STATE);
//            ReactorState nextState = switch (currentState) {
//                case NEUTRAL -> ReactorState.ACTIVE;
//                case ACTIVE -> ReactorState.INACTIVE;
//                case INACTIVE -> ReactorState.NEUTRAL;
//            };
//            changeState(world, pos, state, nextState);
//            player.sendMessage(Text.literal("Reactor is now " + nextState.asString()), true);
//        }
//        return ActionResult.SUCCESS;
//    }
}

