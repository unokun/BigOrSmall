package jp.smaphonia;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Trump {
	public static int TOTAL_CARD = 4 * Card.NUM_OF_CARDS;

	// 上に積み上げたカード
	// 上から順番にドローする
	private Deque<Card> deck;
	
	public Trump() {
		shuffle();
	}
	/**
	 * カードをシャッフルします
	 */
	public void shuffle() {
		this.deck = new ArrayDeque<Card>(TOTAL_CARD);
		
		// 0 - 51までの数字をセットする
		int[] cards = new int[TOTAL_CARD];
		for (int i = 0; i < TOTAL_CARD; i++) {
			cards[i] = i;
		}
		// シャッフルする
		Random random = new Random();
		for (int i = 0; i < TOTAL_CARD; i++) {
			int j = random.nextInt(TOTAL_CARD - i) + i ;
			swapCard(i, j, cards);
		}		

		// デッキにカードをセットする
		for (int i = 0; i < TOTAL_CARD; i++) {
			Card card = Card.createCard(cards[i]);
//			System.out.println(card.toString());
			deck.add(card);
		}		
		
	}
	/**
	 * 入れ替える
	 * 
	 * @param i
	 * @param j
	 * @param cards
	 */
	private void swapCard(int i, int j, int[] cards) {
		int tmp = cards[i];
		cards[i] = cards[j];
		cards[j] = tmp;
	}
	/**
	 * カードを引く
	 * 
	 * @return
	 */
	public Card draw() {
		return this.deck.pop();
	}
	public int countDeck() {
		return this.deck.size();
	}
	public static void main(String[] args) {
		Trump trump = new Trump();
		for (int i = 0; i < 5; i++) {
			Card card = trump.draw();
			System.out.println(card.toString());
			System.out.println("残り： " + trump.countDeck());
			
		}
	}
}
