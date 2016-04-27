package com.jkr.game.setup;

import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public abstract class AbstractMultiplayerGameTest {

	@Test
	public void testGetPlayerDiscriminatorsReturnsUniqueList() {
		Comparable[] discriminators = getTestedInstance().getPlayerDiscriminators();
		// iterate and compare n-th with others up to the end
		IntStream.range(0, discriminators.length).forEach(i -> {
			IntStream.range(i, discriminators.length).filter(j -> discriminators[i].compareTo(discriminators[j])==0).findAny().ifPresent(j -> Assert.fail("Duplicate discriminator: " + discriminators[j]));
		});
	}
	
	protected abstract MultiplayerGame getTestedInstance();

}
