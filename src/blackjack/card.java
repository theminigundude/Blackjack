package blackjack;

import java.util.HashMap;

public class card {
	
	String first, second;
	int first_num, second_num;
	HashMap<Integer, String> four;
	HashMap<Integer, String> odd_stuff;
	
	public void setup () {
		four = new HashMap<>();
		four.put(0, "Spades"); four.put(1, "Hearts");
		four.put(2, "Diamonds"); four.put(3, "Clubs");
		odd_stuff = new HashMap<>();
		odd_stuff.put(1, "Ace"); odd_stuff.put(11,"Jack");
		odd_stuff.put(12,"Queen"); odd_stuff.put(13,"King");
	}
	
	public card (String in) {
		String temp = in;
		first = temp.substring(0, 1);
		second = in.substring(1,in.length());
		first_num = Integer.parseInt(first);
		second_num = Integer.parseInt(second);

	}
	
	public void print_card () {
		setup();
		String temp = "";
		if (second_num > 1 && second_num < 11) { temp = Integer.toString(second_num); }
		else { temp = odd_stuff.get(second_num); }
		System.out.println("You drew a " + four.get(first_num) + " " + temp);
	}

}
