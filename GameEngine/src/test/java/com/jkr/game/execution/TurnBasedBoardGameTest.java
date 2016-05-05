package com.jkr.game.execution;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.partialMockBuilder;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.strictMock;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.jkr.game.area.Board;
import com.jkr.game.interaction.Player;
import com.jkr.game.interaction.Player.Response;
import com.jkr.game.presentation.Presentation;

@SuppressWarnings("all")
public class TurnBasedBoardGameTest {

	@Test(expected = GameExecutionException.class)
	public void testExceptionIfNoUsersSet() throws GameExecutionException {
		TestBoard game = partialMockBuilder(TestBoard.class).withConstructor().createMock();
		replay(game);
		game.play();
	}

	@Test
	public void testNextTurnWins() throws GameExecutionException {
		createMocksForSuccessfullPuttingMarkAndAssertingVictory(true, false);
	}

	@Test
	public void testNextTurnDoesNotWin() throws GameExecutionException {
		createMocksForSuccessfullPuttingMarkAndAssertingVictory(false, false);
	}

	@Test(expected = GameExecutionException.class)
	public void testNextTurnDoesNotWinAndBoardFullyFilled() throws GameExecutionException {
		createMocksForSuccessfullPuttingMarkAndAssertingVictory(false, true);
	}
	
	@Test
	public void testAskAgainIfCannotPutMark() throws GameExecutionException {
		int[] point = {0,0};
		Player player = strictMock(Player.class);
		Presentation presentation = strictMock(Presentation.class);
		presentation.showToUser();
		expectLastCall().anyTimes();
		Board board = strictMock(Board.class);
		Response response = strictMock(Response.class);
		expect(response.getCargo()).andReturn("").anyTimes();
		expect(player.askForResponse(EasyMock.anyString())).andReturn(response).once();
		expect(presentation.convertPresentationOrdinalToSpaceAreaOrdinal("")).andReturn(point).once();
		expect(board.canPutMark(point[0], point[1])).andReturn(Boolean.FALSE).once();
		player.showMessage(EasyMock.anyString());
		expectLastCall().once();
		expect(player.askForResponse(EasyMock.anyString())).andReturn(response).once();
		expect(presentation.convertPresentationOrdinalToSpaceAreaOrdinal("")).andReturn(point).once();
		expect(board.canPutMark(point[0], point[1])).andReturn(Boolean.TRUE).once();
		expect(player.getDiscriminator()).andReturn(Integer.valueOf(1)).once();
		board.putMark(point[0], point[1], Integer.valueOf(1));
		expectLastCall().once();
		expect(board.isWinConditionMet(point[0],  point[1])).andReturn(Boolean.TRUE).once();
		replay(player, presentation, board, response);
		TestBoard.board = board;
		TestBoard.presentation = presentation;
		TurnBasedBoardGame<Integer, Character> game = new TestBoard();
		assertEquals(true, game.nextTurnForPlayer(player));
	}
	
	private void createMocksForSuccessfullPuttingMarkAndAssertingVictory(boolean expectedWin, boolean expectedFilled) throws GameExecutionException {
		int[] point = {0,0};
		Player player = strictMock(Player.class);
		Presentation presentation = strictMock(Presentation.class);
		presentation.showToUser();
		expectLastCall().anyTimes();
		Board board = strictMock(Board.class);
		Response response = strictMock(Response.class);
		expect(response.getCargo()).andReturn("").anyTimes();
		expect(player.askForResponse(EasyMock.anyString())).andReturn(response).once();
		expect(presentation.convertPresentationOrdinalToSpaceAreaOrdinal("")).andReturn(point).once();
		expect(board.canPutMark(point[0], point[1])).andReturn(Boolean.TRUE).once();
		expect(player.getDiscriminator()).andReturn(Integer.valueOf(1)).once();
		board.putMark(point[0], point[1], Integer.valueOf(1));
		expectLastCall().once();
		expect(board.isWinConditionMet(point[0],  point[1])).andReturn(expectedWin).once();
		if (!expectedWin) {
			expect(board.isFullyFilled()).andReturn(expectedFilled).once();
		}
		replay(player, presentation, board, response);
		TestBoard.board = board;
		TestBoard.presentation = presentation;
		TurnBasedBoardGame<Integer, Character> game = new TestBoard();
		assertEquals(expectedWin, game.nextTurnForPlayer(player));
		
	}

	@Test
	public void testWinInFirstRound() throws GameExecutionException {
		Player player1 = strictMock(Player.class);
		Player player2 = strictMock(Player.class);
		List players = new ArrayList();
		players.add(player1);
		players.add(player2);
		TestBoard game = partialMockBuilder(TestBoard.class).addMockedMethod("nextTurnForPlayer").withConstructor().createMock();
		expect(game.nextTurnForPlayer(player1)).andReturn(Boolean.TRUE).once();
		player1.showMessage(EasyMock.anyString());
		expectLastCall().once();
		replay(game, player1, player2);
		game.setPlayers(players);
		game.play();
	}

	@Test
	public void testWinInSecondRound() throws GameExecutionException {
		Player player1 = strictMock(Player.class);
		Player player2 = strictMock(Player.class);
		List players = new ArrayList();
		players.add(player1);
		players.add(player2);
		TestBoard game = partialMockBuilder(TestBoard.class).addMockedMethod("nextTurnForPlayer").withConstructor().createMock();
		expect(game.nextTurnForPlayer(player1)).andReturn(Boolean.FALSE).once();
		expect(game.nextTurnForPlayer(player2)).andReturn(Boolean.TRUE).once();
		player2.showMessage(EasyMock.anyString());
		expectLastCall().once();
		replay(game, player1, player2);
		game.setPlayers(players);
		game.play();
	}

	@Test
	public void testWinInThirdRound() throws GameExecutionException {
		Player player1 = strictMock(Player.class);
		Player player2 = strictMock(Player.class);
		List players = new ArrayList();
		players.add(player1);
		players.add(player2);
		TestBoard game = partialMockBuilder(TestBoard.class).addMockedMethod("nextTurnForPlayer").withConstructor().createMock();
		expect(game.nextTurnForPlayer(player1)).andReturn(Boolean.FALSE).once();
		expect(game.nextTurnForPlayer(player2)).andReturn(Boolean.FALSE).once();
		expect(game.nextTurnForPlayer(player1)).andReturn(Boolean.TRUE).once();
		player1.showMessage(EasyMock.anyString());
		expectLastCall().once();
		replay(game, player1, player2);
		game.setPlayers(players);
		game.play();
	}
	
	@Before
	public void before() {
		TestBoard.board = null;
		TestBoard.presentation = null;
	}
	
	
	private static class TestBoard extends TurnBasedBoardGame<Integer, Character> {
		
		private static Board<Integer> board;
		private static Presentation<int[], Character> presentation;
		
		TestBoard() {}

		@Override
		public Board<Integer> prepareCleanBoard() {
			return board;
		}

		@Override
		public Comparable<?>[] getPlayerDiscriminators() {
			return null;
		}

		@Override
		protected Presentation<int[], Character> getPresentation() {
			return presentation;
		}
		
	}

}
