package jp.smaphonia;

import java.util.Scanner;

public class Main {

	private static boolean isContinueGame(Chip chip, Scanner scanner) {
		System.out.println("*****現在のチップ枚数*****");
		System.out.println(chip.toString());
		System.out.println("************************");

		System.out.println("[ゲームを続けますか？]: 0: Yes 1:No");
		String cont = scanner.next();
		return (cont.equals("0"));

	}

	public static void main(String[] args) {
		Trump trump = new Trump();
		Chip chip = new Chip();
		Scanner scanner = new Scanner(System.in);
		Card card = null;
		int bet = 0; 
		while (true) {
			// カードを引く
			if (card == null) {
				card = trump.draw();
			}
			System.out.println("*****チップ枚数とカード*****");
			System.out.println("総数：" + chip.toString());
			System.out.println("現在のカード：" + card.toString());
			System.out.println("************************");
			
			if (bet == 0) {
				System.out.println("");
				System.out.println("■BET枚数選択");
				System.out.println("BETするチップ数を入力してください(最低1〜20枚)");
				try {
					bet = Integer.parseInt(scanner.next());
					System.out.println(bet);
				} catch (NumberFormatException e) {
					
				}
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
				System.out.println("[獲得したチップ" + won + "枚でBig or Smallを続けますか？]: 0: Yes 1:No");
				String cont = scanner.next();
				if (cont.equals("0")) {
					card = drawn;
					bet = won;
					continue;
				}
				
			} else {
				System.out.println("Lose...");
				chip.lose(bet);
			}
			
			// ゲームを継続するかどうか
			if (isContinueGame(chip, scanner)) {
				card = null;
				bet = 0;
				continue;
			}
			System.out.println("END");
			System.exit(0);
		}
	}
}
