package blackjack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class game {
	
	int[][] cards;
	ArrayList<card> player= new ArrayList<card>();
	ArrayList<card> dealer= new ArrayList<card>();
	Scanner r = new Scanner(System.in);
	int card_count = 1, dealer_hit = 1, outcome = 0;
	
	public game () {
		//setup game
		set_deck();
		//deal first two cards
		for (int n = 0; n < 2; n++) { deal_cards_player(); }
		for (int n = 0; n < 2; n++) { deal_cards_dealer(); }
		//calculate your starting hand
		System.out.println("Your current number is at: " + calculate(player));
	
		
		while (check(player) == 1) {
			//dealer
			if (dealer_hit != 2) {
				String dealer_temp = calculate(dealer);
				String[] hj = dealer_temp.split("/");
				int d_actual = Integer.parseInt(hj[0]);
				if (d_actual >= 17 && d_actual < 21) { dealer_hit = 2; }
				if (d_actual == 21) { 
					outcome = 2;
					System.out.println("Dealer won because dealer drew 21!");
					break;
				} // you lost dealer won 
				if (d_actual > 21) { outcome = 1; 
				System.out.println("You won because dealer drew over 21!");
				break;} // you lost dealer won 
				if (d_actual < 17) { 
					deal_cards_dealer(); 
				}
				
			}
			
			if (outcome == 0) {
				//player
				System.out.print("\nDo you want to hit or stand (1 for hit and 2 for stand): ");
				int n = r.nextInt();
				if (n == 1) {
					deal_cards_player();
					System.out.println("Your current number is at: " + calculate(player));
					int output = check(player);
					if (output == 2) {
						System.out.println("You lost :(");
						break;
					}
					else if (output == 3) {
						System.out.println("You win!");
						outcome = 1;
						break;
					}
					else 
						System.out.println("keep on going!");
				}
				else if (n == 2) {
					if (dealer_hit == 2) {
						System.out.println("Dealer also stand. You lost :(");
						outcome = 2;
						break;
					}
				
				}	
			}

		}	
	}
	
	public int check (ArrayList<card> in) {
		
		String temp = calculate(in);
		
		String[] buffer = temp.split("/");
		int number = Integer.parseInt(buffer[0]);
		if (buffer.length < 2) {
			if (number < 21) 
				return 1;
			else if (number == 21)
				return 3;
			else
				return 2;
		}
		else {
			for (int n = 1; n < buffer.length; n++) {
				int temp_2 = Integer.parseInt(buffer[n]);
				if (temp_2 > 21) {
					break;
				}
			}
			if (number < 21) 
				return 1;
			else if (number == 21)
				return 3;
			else
				return 2;
			
		}
		//1 = keep on going; 3 = win!; 2 = lost :(
	}
	
	public String calculate (ArrayList<card> in) {
		String output = "";
		int count = 0, ace = 0;
		
		for (int n = 0; n < in.size(); n++) {
			card temp = in.get(n);
			int value = temp.second_num;
			if (value > 10) {
				value = 10;
			}
			else if (value == 1) {
				ace++;
			}
				count += value;
		}
		output = Integer.toString(count);
		if (ace > 0) {
			String extra = "";
			for (int n = 0; n < ace; n++) {
				count += 10;
				extra += "/" + count ;
			}
			output += extra;
			return output;
		}
		else {
			return output;
		}
	}
	
	public void deal_cards_dealer () {
		card init = new card(pick_card());
		dealer.add(init);
	}
	
	public void deal_cards_player () {
		card init = new card(pick_card());
		init.print_card();
		player.add(init);
	}
	
	public void print () {
		for (int n = 0; n < cards.length; n++) {
			System.out.print(n + "   ");
			for (int a = 0; a < cards[n].length; a++) {
				System.out.print(cards[n][a] + " ");
			}
			System.out.println("");
		}
	}
	
	public String pick_card () {	
		String out = "";
		while (1 == 1) {
			Random rand = new Random();
			int n = rand.nextInt(3);
			int a = rand.nextInt(12)+0;
			if (cards[n][a] != 0) {
				out+= n;
				out += cards[n][a];
				cards[n][a] = 0;
				return out;
			}
		}
	}
	
	public int[][] set_deck () {
		
		cards = new int[4][13];
		for (int n = 0; n < cards.length; n++) {
			for (int a = 0; a < cards[n].length; a++) {	
					cards[n][a] = card_count;
					card_count++;
			}
			card_count = 1;
		}
	
		return cards;
	}

	public int endgame () {
		if (outcome == 1)
			return 1;
		else 
			return 2;
	}
}
