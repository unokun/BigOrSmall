package jp.smaphonia;

import java.io.PrintStream;

/**
 * トランプのカードクラス
 * @author unokun
 *
 */
public class Card {
	public static final int NUM_OF_CARDS = 13;
	
	private static final int SUIT_ID_SPADE = 0;
	private static final String SUIT_NAME_SPADE = "スペード";
//	private static final String SUIT_PIC_SPADE = "♠";
	private static final int SUIT_ID_HEART = 1;
	private static final String SUIT_NAME_HEART = "ハート";
//	private static final String SUIT_PIC_HEART = "♡";
	private static final int SUIT_ID_DIAMOND = 2;
	private static final String SUIT_NAME_DIAMOND = "ダイヤ";
//	private static final String SUIT_PIC_DIAOND = "♢";
	private static final int SUIT_ID_CLUB = 3;
	private static final String SUIT_NAME_CLUB = "クラブ";
//	private static final String SUIT_PIC_CLUB = "♣";
	private static final int SUIT_ID_JOKER = 4;
	private static final String SUIT_NAME_JOKER = "ジョーカー";
//	private static final String SUIT_PIC_JOKER = "";
	private static final int SUIT_ID_UNKNOWN = 5;
	private static final String SUIT_NAME_UNKNOWN = "不明";
//	private static final String SUIT_PIC_UNKNOWN = "";
	
	// http://qiita.com/KeithYokoma/items/9681b130ea132cfad64d
	public enum Suit {
		SPADES(SUIT_ID_SPADE, SUIT_NAME_SPADE), HEARTS(SUIT_ID_HEART, SUIT_NAME_HEART), DIAMONDS(SUIT_ID_DIAMOND, SUIT_NAME_DIAMOND),
		CLUBS(SUIT_ID_CLUB, SUIT_NAME_CLUB), JOKER(SUIT_ID_JOKER, SUIT_NAME_JOKER), UNKNOWN(SUIT_ID_UNKNOWN, SUIT_NAME_UNKNOWN); 
		
		private final int id;
		private final String name;
		
		private Suit(int id, String name) {
			this.id = id;
			this.name= name;
		}
	    public static Suit valueOf(int id) {
	        for (Suit suit : values()) {
	            if (suit.getId() == id) {
	                return suit;
	            }
	        }

//	        throw new IllegalArgumentException("no such enum object for the id: " + id);
	        // Null-Object パターンにしたがって、列挙に UNKNOWN みたいなのを入れておくのも良い
	         return UNKNOWN;
	    }
		public int getId() {
			return this.id;
		}
		public String getName() {
			return name;
		}
	}
	
	// カードの種類
	private Suit suit;
	
	// ナンバー 1...10,11(J),12(Q),13(K)
	private int number;

	public Suit getCardSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int getNumber() {
		return number;
	}

	private Card() {
		
	}
	
	/**
	 * カードを作成します
	 * 
	 * @param cardType
	 * @param number
	 */
	private Card(Card.Suit cardType, int number) {
		this.suit = cardType;
		this.number = number;
	}
	/**
	 * カードを作成します
	 * 
	 * @param value 0...52の値
	 * 
	 * @return　カードクラス
	 */
	public static Card createCard(int value) {
		int suits = value % 4;
		int number = value / 4;

		return new Card(createCardType(suits), number);
	}
	
	private static Card.Suit createCardType(int id) {
		return Card.Suit.valueOf(id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(suit.getName());
		builder.append(this.number + 1);
		return builder.toString();
	}
	/**
	 * カードが大きいか？
	 * ・最初に数値で比較する。
	 * 　数値が大きい場合、trueを返す。
	 * 　数値が大きい場合、falseを返す。
	 * ・数値が等しい場合には絵柄で比較する
	 * 　「スペード」 > 「ハート」 > 「ダイヤ」 > 「クラブ」の順であり、スペードが最も強く、クラブが最も弱い。 
	 * 
	 * @param card
	 * @return
	 */
	public boolean isBiggerThan(Card card) {
		// 絵柄が同じ場合は数字で比較する
		int num1 = this.getNumber();
		int num2 = card.getNumber();
		if (num1 == num2) {
			return (this.getCardSuit().getId() < card.getCardSuit().getId());
		}
		return (num1 > num2);
	}
	public void print(PrintStream ps) {
		ps.println("test");
	}
	
	public static void main(String[] args) {
		Card card = Card.createCard(4);
		card.print(System.out);
	}
}
