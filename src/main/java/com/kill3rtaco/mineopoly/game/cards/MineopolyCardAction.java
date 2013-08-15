package com.kill3rtaco.mineopoly.game.cards;

import com.kill3rtaco.mineopoly.game.MineopolyPlayer;

public abstract class MineopolyCardAction {

    protected String name;
    protected char[] paramTypes;

    public MineopolyCardAction(String name) {
        this.name = name;
        this.paramTypes = new char[0];
    }

    public MineopolyCardAction(String name, String paramTypes) {
        this.name = name;
        this.paramTypes = paramTypes.toCharArray();
    }

    public MineopolyCardAction(String name, char... paramTypes) {
        this.name = name;
        this.paramTypes = paramTypes;
    }

    public abstract CardResult doAction(MineopolyPlayer player, Object... params);

    public String getName() {
        return name;
    }

    public int getRequiredParamLength() {
        return paramTypes.length;
    }

    public char[] getParamTypes() {
        return paramTypes;
    }

}
