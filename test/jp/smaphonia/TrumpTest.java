package jp.smaphonia;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TrumpTest {

	Trump trump;
	
	@Before
	public void setUp() throws Exception {
		trump = new Trump();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTrump() {
		try {
			// 最初はカードの枚数は52枚。
			assertEquals(Trump.TOTAL_CARD, trump.countDeck());
			Card card = trump.draw();
			// カードを引くと残り枚数が1枚少なくなっていること
			assertEquals(Trump.TOTAL_CARD - 1, trump.countDeck());

			// 引いたカードの値(範囲)チェック
			int number = card.getNumber();
			int suitId = card.getCardSuit().getId();
			assertTrue((number >= 0) && (number < Card.NUM_OF_CARDS));
			assertTrue((suitId >= Card.Suit.SPADES.getId()) && (suitId <= Card.Suit.CLUBS.getId()));
		} catch (Exception e) {
			fail();
			
		}
	}

}
