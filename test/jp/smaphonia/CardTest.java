package jp.smaphonia;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CardTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		try {
			for (int i = 0; i <= Card.NUM_OF_CARDS * 4; i++) {
				Card card = Card.createCard(i);
				assertEquals((i / 4), card.getNumber());
				assertEquals((i % 4), card.getCardSuit().getId());

				String actual = card.toString();
				assertEquals(getCardInfo(card), actual);
			}
		} catch (Exception e) {
			fail();
		}
	}
	private String getCardInfo(Card card) {
		StringBuilder builder = new StringBuilder();
		builder.append(card.getCardSuit().getName());
		builder.append(card.getNumber() + 1);
		return builder.toString();
	}
	/**
	 * 数値が大きい場合
	 */
	@Test
	public void testIsBigger() {
		try {
			Card card1;
			Card card2;

			// card1: スペード 2
			// card2: ハート 1
			card1 = Card.createCard(4);
			card2 = Card.createCard(1);
//			System.out.println("card1: " + card1);
//			System.out.println("card2: " + card2);
			assertTrue(card1.isBigger(card2));
			
			// card1: ダイヤ 13
			// card2: クラブ 12
			card1 = Card.createCard(50);
			card2 = Card.createCard(47);
//			System.out.println("card1: " + card1);
//			System.out.println("card2: " + card2);
			assertTrue(card1.isBigger(card2));
			
			// card1: スペード 8
			// card2: クラブ 8
			card1 = Card.createCard(28);
			card2 = Card.createCard(31);
//			System.out.println("card1: " + card1);
//			System.out.println("card2: " + card2);
			assertTrue(card1.isBigger(card2));
		} catch (Exception e) {
			fail();
		}
	}
	


}
