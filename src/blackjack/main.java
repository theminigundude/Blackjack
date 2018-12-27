package blackjack;

import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    int total = 100, bet = 0;
		boolean gate = false;
		
		while (total > 0) {
			System.out.println("**************************************************************************");
			while (gate == false) {
				Scanner r = new Scanner(System.in);
				System.out.println("How much do you want to bet? You currently have "+ total + " dollars:");
				bet = r.nextInt();
				if (bet > total) {
					System.out.println("Please enter a number witin your total.\n");
				}
				else 
					gate = true;
			}
			game test = new game();
			int outcome = test.endgame();
			if (outcome ==1 ) {
				total += bet *2;
			}
			else if (outcome == 2) {
				total -= bet;
			}
			System.out.println("Your new balance is: " + total);
			gate = false;
		}
    System.out.println("\n**************************************************************************");
		System.out.println("Game over. You have no money left");

  }
}
