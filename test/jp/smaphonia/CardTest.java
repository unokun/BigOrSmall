package jp.smaphonia;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;


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
//				assertEquals(getCardInfo(card), actual);
				assertThat(actual).isEqualTo(getCardInfo(card));
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
//			assertTrue(card1.isBiggerThan(card2));
			assertThat(card1.isBiggerThan(card2)).isTrue();
			// card1: ダイヤ 13
			// card2: クラブ 12
			card1 = Card.createCard(50);
			card2 = Card.createCard(47);
//			System.out.println("card1: " + card1);
//			System.out.println("card2: " + card2);
//			assertTrue(card1.isBiggerThan(card2));
			assertThat(card1.isBiggerThan(card2)).isTrue();
			
			// card1: スペード 8
			// card2: クラブ 8
			card1 = Card.createCard(28);
			card2 = Card.createCard(31);
//			System.out.println("card1: " + card1);
//			System.out.println("card2: " + card2);
//			assertTrue(card1.isBiggerThan(card2));
			assertThat(card1.isBiggerThan(card2)).isTrue();
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testPrint() {
		try {
			Card card = Card.createCard(4);
			try (ByteArrayOutputStream bas = new ByteArrayOutputStream(); PrintStream ps = new PrintStream(bas)) {
				card.print(ps);
				byte[] bytes = bas.toByteArray();
				System.out.println(new String(bytes));
			}
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testScanner() {
		try {
			try (InputStream in = new ByteArrayInputStream("test".getBytes()); Scanner scanner = new Scanner(in)) {
				String input = scanner.next();
				System.out.println(input);
			}
			
		} catch (Exception e) {
			fail();
		}
	}
//	public void aaa() {
//		// ディラーがゲームの初期化をします
//		
//		// 1. ディラーがカードを一枚配ります
//		// 配ったカードに対して、プレーヤーが掛け金を設定します
//		// ディラーが結果を判定します
//		// 負けた場合
//		//   1に進む
//		
//		// 勝った場合
//		//  ダブルアップするかどうか聞きます
//		//  ダブルアップする場合
//		//   　カードを配ります。
//		//　　　掛け金を設定します
//		//     結果を判定します
//		//　ダブルアップしない場合
//		
//	}

}
