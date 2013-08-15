package com.kill3rtaco.mineopoly.game.cards;

import org.apache.commons.lang.ArrayUtils;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.game.MineopolyPlayer;

public abstract class MineopolyCard {
    private String description, name, action;
    protected MineopolyCardAction cardAction;
    protected CardResult result = null;
    private CardType type;
    private boolean valid = true;
    private Object[] params;

    public MineopolyCard(String name, CardType type, String description, String action) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.action = action;
        validate();
    }

    public String getDescription() {
        return description;
    }

    public void readDescription(MineopolyPlayer player) {
        Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + player.getName() + " &3drew a " + type.getName() + " &3card, it says:");
        Mineopoly.plugin.getGame().getChannel().sendMessage(getFormattedDescription());
    }

    public String getFormattedDescription() {
        String message = "";
        for (String s : description.split("\\s+")) {
            boolean found = false;
            for (MineopolyCardVariable v : MineopolyCardVariable.values()) {
                if (s.matches(v.getRegex())) {
                    message += v.getReplaceMethod().replace(s) + " ";
                    found = true;
                }
            }
            if (!found)
                message += s + " ";
        }
        return message;
    }

    public void action(MineopolyPlayer player) {
        result = cardAction.doAction(player, params);
    }

    private void validate() {
        String[] split = action.split("\\s+");
        String[] rawParams = (String[]) ArrayUtils.subarray(split, 1, split.length);
        String name = split[0];
        cardAction = MineopolyCardActionManager.getAction(name);
        char[] requiredArgs = cardAction.getParamTypes();
        final int argsLength = requiredArgs.length;
        if (argsLength != rawParams.length) {
            System.err.println("Bad card: wrong parameter length");
            return;
        }
        params = new Object[argsLength];
        for (int i = 0; i < argsLength; i++) {
            char type = requiredArgs[i];
            if (type == 'b') {
                params[i] = Boolean.valueOf(rawParams[i]);
            } else if (type == 's') {
                params[i] = rawParams[i];
            } else if (type == 'i') {
                try {
                    params[i] = Integer.valueOf(rawParams[i]);
                } catch (NumberFormatException e) {
                    System.err.println("Bad card: parameter not a number");
                    return;
                }
            } else {
                System.err.println("Unrecognized CardAction parameter type");
                params[i] = rawParams[i];
            }
        }
        valid = true;
    }

    public boolean isValid() {
        return valid;
    }

    public CardResult getResult() {
        return result;
    }

    public CardType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getFormattedAction() {
        if (valid) {
            String[] parts = action.split("\\s+");
            String formattedAction = parts[0] + "(";
            for (int i = 1; i < parts.length; i++) {
                formattedAction += parts[i];
                if (i < parts.length - 1) {
                    formattedAction += ", ";
                }
            }
            return formattedAction + ")";
        } else {
            return "InvalidAction";
        }
    }

}
