package com.jkr.game.execution;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.partialMockBuilder;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.strictMock;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import com.jkr.game.interaction.Player;

@SuppressWarnings("all")
public class TurnBasedGameExecutionHandlerTest {

	@Test(expected = GameExecutionException.class)
	public void testExceptionIfNoUsersSet() throws GameExecutionException {
		Board4Test game = partialMockBuilder(Board4Test.class).addMockedMethod("getPlayers").createStrictMock();
		expect(game.getPlayers()).andReturn(new ArrayList<>()).once();
		replay(game);
		new TurnBasedGameExecutionHandler<>(game).play();
	}

	@Test
	public void testWinInFirstRound() throws GameExecutionException {
		Player player1 = strictMock(Player.class);
		Player player2 = strictMock(Player.class);
		List players = new ArrayList();
		players.add(player1);
		players.add(player2);
		Board4Test game = partialMockBuilder(Board4Test.class).addMockedMethod("nextTurnForPlayer").addMockedMethod("getPlayers").createStrictMock();
		expect(game.getPlayers()).andReturn(players).once();
		expect(game.nextTurnForPlayer(player1)).andReturn(Boolean.TRUE).once();
		player1.showMessage(EasyMock.anyString());
		expectLastCall().once();
		replay(game, player1, player2);
		new TurnBasedGameExecutionHandler<>(game).play();
		verify(game, player1, player2);
	}

	@Test
	public void testWinInSecondRound() throws GameExecutionException {
		Player player1 = strictMock(Player.class);
		Player player2 = strictMock(Player.class);
		List players = new ArrayList();
		players.add(player1);
		players.add(player2);
		Board4Test game = partialMockBuilder(Board4Test.class).addMockedMethod("nextTurnForPlayer").addMockedMethod("getPlayers").createStrictMock();
		expect(game.getPlayers()).andReturn(players).once();
		expect(game.nextTurnForPlayer(player1)).andReturn(Boolean.FALSE).once();
		expect(game.nextTurnForPlayer(player2)).andReturn(Boolean.TRUE).once();
		player2.showMessage(EasyMock.anyString());
		expectLastCall().once();
		replay(game, player1, player2);
		new TurnBasedGameExecutionHandler<>(game).play();
		verify(game, player1, player2);
	}

	@Test
	public void testWinInThirdRound() throws GameExecutionException {
		Player player1 = strictMock(Player.class);
		Player player2 = strictMock(Player.class);
		List players = new ArrayList();
		players.add(player1);
		players.add(player2);
		Board4Test game = partialMockBuilder(Board4Test.class).addMockedMethod("nextTurnForPlayer").addMockedMethod("getPlayers").createStrictMock();
		expect(game.getPlayers()).andReturn(players).once();
		expect(game.nextTurnForPlayer(player1)).andReturn(Boolean.FALSE).once();
		expect(game.nextTurnForPlayer(player2)).andReturn(Boolean.FALSE).once();
		expect(game.nextTurnForPlayer(player1)).andReturn(Boolean.TRUE).once();
		player1.showMessage(EasyMock.anyString());
		expectLastCall().once();
		replay(game, player1, player2);
		new TurnBasedGameExecutionHandler<>(game).play();
		verify(game, player1, player2);
	}

}
