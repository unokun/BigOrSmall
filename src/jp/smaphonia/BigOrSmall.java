package jp.smaphonia;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BigOrSmall {
	private static final Logger LOGGER = LogManager.getLogger(BigOrSmall.class); 
	public static final int MIN_BET = 1;
	public static final int MAX_BET = 20;

	// 選択
	static final int CHOICE_YES = 0;
	static final int CHOICE_NO = 1;
	static final int CHOICE_BIG = 0;
	static final int CHOICE_SMALL = 1;
	
	/**
	 * bet枚数を入力する
	 * @param scanner
	 * @return
	 */
	private int getBet(Scanner scanner) {
		int bet = 0;
		while (true) {
			System.out.println("");
			System.out.println("■BET枚数選択");
			System.out.println("BETするチップ数を入力してください(最低1〜20枚)");
			try {
				bet = Integer.parseInt(scanner.next());
				if (bet < MIN_BET) { continue; }
				if (bet > MAX_BET) { continue; }
//				System.out.println(bet);
				return bet;
			} catch (NumberFormatException e) {				
			}

		}
	}
	/**
	 * 新しいゲームとして続けるかどうか？
	 * 
	 * @param chip
	 * @param scanner
	 * @return
	 */
	private boolean isContinueAsNewGame(Chip chip, Scanner scanner) {
		System.out.println("*****現在のチップ枚数*****");
		System.out.println(chip.toString());
		System.out.println("************************");

		System.out.println("[ゲームを続けますか？]: 0: Yes 1:No");
		String cont = scanner.next();
		return (cont.equals("0"));

	}

	/**
	 * チップ枚数と現在のカードを出力します。
	 * 
	 * @param chip
	 * @param card
	 * @param ps
	 */
	void printStatus(Chip chip, Card card) {
		println("*****チップ枚数とカード*****");
		println(chip.toString());
		println("現在のカード：" + card.toString());
		println("************************");
	}
	void printCard(Card currentCard, Card drawnCard) {
		println("現在のカード：" + currentCard);
		println("引いたカード：" + drawnCard);
	
	}
	/**
	 * 入力ストリーム(標準入力)を取得します
	 * 
	 * @return
	 */
	InputStream getInputStream() {
		return System.in;
	}
	/**
	 * 出力ストリーム(標準出力)を取得します
	 * 
	 * @return
	 */
	PrintStream getPrintStream() {
		return System.out;
	}

	void println(String message) {
		getPrintStream().println(message);
	}
	void print(String message) {
		getPrintStream().print(message);
	}
	/**
	 * 大きいか小さいかの選択を取得します
	 * 
	 * @param scanner
	 * @param currentCard
	 * @param bet
	 * @return
	 */
	int getChoice(Scanner scanner, Card currentCard, int bet) {
		println("■Big or Small選択");
		println("現在のカード：" + currentCard.toString());
		
		String choice = "";
		while (true) {
			println("[Big or Small]: 0: Big 1:Small");
			choice = scanner.next();
			if (choice.equals("0") || choice.equals("1")) { break; }
		}
		
		println("*****Big or Small*****");
		println("BET数：" + bet);
		print("あなたの選択：");
		if (choice.equals("0")) {
			print("Big");
		} else {
			print("Small");
		}
		println("");
		return Integer.parseInt(choice);
	}
	/**
	 * 勝ったかどうか判定します
	 * 選択した結果と実際が一致した場合、勝ちと判定します。
	 * 
	 * @param choice 大きいかちいさいかの選択
	 * @param actual 引いたカードが表示されているカードより大きいかどうか
	 * @return
	 */
	boolean isWin(int choice, boolean actual) {
		if (choice == CHOICE_BIG && actual) {
			return true;
		}
		if (choice == CHOICE_SMALL && !actual) {
			return true;
		}
		return false;
	}
	/**
	 * カードの大小を比較します
	 * 
	 * @param currentCard
	 * @param drawnCard
	 * @return
	 */
	boolean compareCard(Card currentCard, Card drawnCard) {
		boolean isBigger = drawnCard.isBiggerThan(currentCard) ;
		if (isBigger) {
			System.out.println(currentCard.toString() + "は" + drawnCard.toString() + "よりSmall");
			
		} else {
			System.out.println(currentCard.toString() + "は" + drawnCard.toString() + "よりBig");
			
		}
		System.out.println("************************");	
		return isBigger;
	}
	/**
	 * チップ情報を更新します
	 * 
	 * @param chip
	 * @param won
	 * @return 
	 */
	void updateChip(Chip chip, int won) {
		println("Win!!");
		chip.win(won);
		println("チップを" + won + "枚獲得しました");
	}
	/**
	 * 勝ったチップを使ってゲームを継続する?
	 * @return
	 */
	int continueGame(Scanner scanner, int won) {
		String choice = "";
		while (true) {
			System.out.println("[獲得したチップ" + won + "枚でBig or Smallを続けますか？]: 0: Yes 1:No");
			choice = scanner.next();
			if (choice.equals("0") || choice.equals("1")) {
				break;
			}
		}
		return Integer.parseInt(choice);
	}
	/**
	 * ゲームを開始します
	 */
	void startGame() {
		LOGGER.info("start game");
		
		// 初期データ
		Trump trump = new Trump();
		Chip chip = new Chip();

		// 現在表示されているカード
		Card currentCard = null;
		
		// チップ数
		int bet = 0; 
		
		// 連続勝利数
		int consecutiveWin = 0;

		try (Scanner scanner = new Scanner(getInputStream())) {
			while (true) {
				// 表示されているカードがなければカードを引く
				// ゲーム継続中は、currentCardがセットされている
				if (currentCard == null) {
					currentCard = trump.draw();
				}
				printStatus(chip, currentCard);
				
				// bet枚数が未設定の場合には、入力してもらう
				// ゲーム継続中は、betはセットされている
				if (bet == 0) {
					bet = getBet(scanner);
				}
				
				// 次のカードが現在のカードより大きいか小さいかを賭ける
				int choice = getChoice(scanner, currentCard, bet);
				LOGGER.info("choice: " + choice);
				// カードを引く
				Card drawnCard = trump.draw();
				printCard(currentCard, drawnCard);
				
				// カードを比較
				boolean isBigger = compareCard(currentCard, drawnCard);
				if (isWin(choice, isBigger)) {
					int won = bet * 2;
					updateChip(chip, bet);
					
					if (consecutiveWin <= 8) {
						// 勝ったチップを使ってゲームを継続する
						if (continueGame(scanner, won) == CHOICE_YES) {
							currentCard = drawnCard;
							bet = won;
							consecutiveWin += 1;
							continue;
						}
					}
					
				} else {
					println("Lose...");
					chip.lose(bet);
					
					if (chip.getCount() == 0) {
						println("チップがなくなりました");
						return;
					}

				}
				
				// ゲームを継続するかどうか
				if (isContinueAsNewGame(chip, scanner)) {
					// ゲームの初期化
					trump.shuffle();
					currentCard = null;
					bet = 0;
					consecutiveWin = 0;
					continue;
				}
				return;
			
			}
		}
		
	}

	public static void main(String[] args) {
		BigOrSmall game = new BigOrSmall();
		game.startGame();
		game.println("END");
	}
}
