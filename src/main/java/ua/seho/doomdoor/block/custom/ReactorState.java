package ua.seho.doomdoor.block.custom;

import net.minecraft.util.StringIdentifiable;

public enum ReactorState implements StringIdentifiable {
    NEUTRAL("neutral"),
    ACTIVE("active"),
    INACTIVE("inactive");

    private final String name;

    ReactorState(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
