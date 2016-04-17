package jp.smaphonia;

/**
 * トランプのカードクラス
 * @author unokun
 *
 */
public class Card {
	public static int NUM_OF_CARDS = 13;
	
	// http://qiita.com/KeithYokoma/items/9681b130ea132cfad64d
	public enum Suit {
		SPADES(0), HEARTS(1), DIAMONDS(2), CLUBS(3), JOKER(4), UNKNOWN(5); 
		
		private final int id;
		private Suit(int id) {
			this.id = id;
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

	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * カードを作成します
	 * 
	 * @param cardType
	 * @param number
	 */
	public Card(Card.Suit cardType, int number) {
		this.suit = cardType;
		this.number = number;
	}
	/**
	 * ランダムにカードを作成します
	 * 
	 * @param random
	 * @return
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
		switch (this.suit) {
		case SPADES:
			builder.append("スペード");
			break;
		case HEARTS:
			builder.append("スペード");
			break;
		case DIAMONDS:
			builder.append("ダイヤ");
			break;
		case CLUBS:
			builder.append("クラブ");
			break;
		case JOKER:
			builder.append("ジョーカー");
			break;
		case UNKNOWN:
			builder.append("不明");
			break;
		}
		builder.append(this.number);
		
		return builder.toString();
	}
	/**
	 * 数値が大きいか？
	 * 
	 * @param card
	 * @return
	 */
	public boolean isBigger(Card card) {
		// 絵柄が同じ場合は数字で比較する
		int num1 = this.getNumber();
		int num2 = card.getNumber();
		if (num1 == num2) {
			return (this.getCardSuit().getId() < card.getCardSuit().getId());
		}
		return (num1 > num2);
	}

}
