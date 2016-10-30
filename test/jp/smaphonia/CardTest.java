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
				String s = card.toString();
				assertEquals((i / 4), card.getNumber());
				assertEquals((i % 4), card.getCardSuit().getId());
				
				StringBuilder builder = new StringBuilder();
				switch (card.getCardSuit()) {
				case SPADES:
					builder.append("スペード");
					break;
				case HEARTS:
					builder.append("ハート");
					break;
				case DIAMONDS:
					builder.append("ダイヤ");
					break;
				case CLUBS:
					builder.append("クラブ");
					break;
				case UNKNOWN:
					break;
				default:
				}
				builder.append(i / 4 + 1);
				assertEquals(builder.toString(), s);
			}
		} catch (Exception e) {
			fail();
		}
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
			System.out.println("card1: " + card1);
			System.out.println("card2: " + card2);
			assertTrue(card1.isBigger(card2));
		} catch (Exception e) {
			fail();
		}
	}

}
