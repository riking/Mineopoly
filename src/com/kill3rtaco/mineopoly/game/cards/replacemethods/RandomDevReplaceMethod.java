package com.kill3rtaco.mineopoly.game.cards.replacemethods;

import java.util.Random;

import com.kill3rtaco.mineopoly.game.cards.VariableReplaceMethod;

public class RandomDevReplaceMethod implements VariableReplaceMethod {

	@Override
	public String replace(String s) {
		String[] devs = new String[]{"jeb_", "notch", "Dinnerbone", "EvilSeph"};
		Random random = new Random();
		String dev = devs[random.nextInt(devs.length)];
		return s.replaceAll("%randomDev", dev);
	}

}
