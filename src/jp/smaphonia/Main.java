package jp.smaphonia;

import java.util.Scanner;

public class Main {

	/**
	 * bet枚数を入力する
	 * @param scanner
	 * @return
	 */
	private static int getBet(Scanner scanner) {
		int bet = 0;
		while (true) {
			System.out.println("");
			System.out.println("■BET枚数選択");
			System.out.println("BETするチップ数を入力してください(最低1〜20枚)");
			try {
				bet = Integer.parseInt(scanner.next());
				if (bet < 1) { continue; }
				if (bet > 20) { continue; }
//				System.out.println(bet);
				return bet;
			} catch (NumberFormatException e) {				
			}

		}
	}
	/**
	 * ゲームを続けるかどうか？
	 * 
	 * @param chip
	 * @param scanner
	 * @return
	 */
	private static boolean isContinueGame(Chip chip, Scanner scanner) {
		System.out.println("*****現在のチップ枚数*****");
		System.out.println(chip.toString());
		System.out.println("************************");

		System.out.println("[ゲームを続けますか？]: 0: Yes 1:No");
		String cont = scanner.next();
		return (cont.equals("0"));

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Trump trump = new Trump();
		Chip chip = new Chip();
		Card card = null;
		int bet = 0; 
		int consecutiveWin = 0;
		while (true) {
			// カードを引く
			if (card == null) {
				card = trump.draw();
			}
			System.out.println("*****チップ枚数とカード*****");
			System.out.println(chip.toString());
			System.out.println("現在のカード：" + card.toString());
			System.out.println("************************");
			
			if (bet == 0) {
				// bet枚数が未設定の場合には、入力してもらう
				bet = getBet(scanner);
			}
			
			System.out.println("■Big or Small選択");
			System.out.println("現在のカード：" + card.toString());
			System.out.println("[Big or Small]: 0: Big 1:Small");
			String choice = scanner.next();
			
			System.out.println("*****Big or Small*****");
			System.out.println("BET数：" + bet);
			System.out.print("あなたの選択：");
			if (choice.equals("0")) {
				System.out.print("Big");
			} else {
				System.out.print("Small");
			}
			System.out.println("");
			System.out.println("現在のカード：" + card.toString());
			Card drawn = trump.draw();
			System.out.println("引いたカード：" + drawn.toString());
			boolean isBigger = drawn.isBigger(card);
			if (isBigger) {
				System.out.println(card.toString() + "は" + drawn.toString() + "よりSmall");
				
			} else {
				System.out.println(card.toString() + "は" + drawn.toString() + "よりBig");
				
			}
			System.out.println("************************");
			
			if ((isBigger && choice.equals("0")) ||
					(!isBigger && choice.equals("1"))) {
				System.out.println("Win!!");
				int won = bet * 2;
				chip.win(won);
				System.out.println("チップを" + won + "枚獲得しました");
				if (consecutiveWin <= 8) {
					System.out.println("[獲得したチップ" + won + "枚でBig or Smallを続けますか？]: 0: Yes 1:No");
					String cont = scanner.next();
					if (cont.equals("0")) {
						card = drawn;
						bet = won;
						consecutiveWin += 1;
						continue;
					}
				}
				
			} else {
				System.out.println("Lose...");
				chip.lose(bet);

			}
			
			// ゲームを継続するかどうか
			if (isContinueGame(chip, scanner)) {
				trump.shuffle();
				card = null;
				bet = 0;
				consecutiveWin = 0;
				continue;
			}
			System.out.println("END");
			System.exit(0);
		}
	}
}
