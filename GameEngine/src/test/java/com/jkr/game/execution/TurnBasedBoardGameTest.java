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
		presentation.showAvailableMoves();
		expectLastCall().once();
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
		presentation.showCurrentState();
		expectLastCall().once();
		replay(player, presentation, board, response);
		Board4Test.board = board;
		Board4Test.presentation = presentation;
		TurnBasedBoardGame<Integer, Character> game = Board4Test.getBoard4TesingTurnBasedBoardGame(board, presentation);
		assertEquals(true, game.nextTurnForPlayer(player));
	}
	
	private void createMocksForSuccessfullPuttingMarkAndAssertingVictory(boolean expectedWin, boolean expectedFilled) throws GameExecutionException {
		int[] point = {0,0};
		Player player = strictMock(Player.class);
		Presentation presentation = strictMock(Presentation.class);
		presentation.showAvailableMoves();
		expectLastCall().once();
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
			if (!expectedFilled) {
				presentation.showCurrentState();
				expectLastCall().once();
			}
		}
		replay(player, presentation, board, response);
		Board4Test.board = board;
		Board4Test.presentation = presentation;
		TurnBasedBoardGame<Integer, Character> game = Board4Test.getBoard4TesingTurnBasedBoardGame(board, presentation);
		assertEquals(expectedWin, game.nextTurnForPlayer(player));
		
	}

}
