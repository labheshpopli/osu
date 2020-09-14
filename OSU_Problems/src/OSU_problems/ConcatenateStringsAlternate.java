package OSU_problems;

import java.util.Scanner;

public class ConcatenateStringsAlternate {
	
	private static String longer = "";
	private static String shorter = "";

	public static void main(String[] args) {
		
		System.out.println("Concatenating two Strings alternating between each character - longer String first");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter first String: ");
		String str1 = sc.nextLine();
		
		System.out.print("Enter second String: ");
		String str2 = sc.nextLine();
		
		System.out.print("The concatenated String is: " + concatenateStringsByAlternateChar(str1, str2));
	}
	
	public static String concatenateStringsByAlternateChar(String str1, String str2) {
		
		setLongerAndShorterStr(str1, str2);
		String output = "";
		CharSequence cs = shorter;
		int i = 0;
		for(; i < shorter.length(); i++) {
			output += Character.toString(longer.charAt(i)) + Character.toString(shorter.charAt(i));
		}
		return (output + longer.substring(i, longer.length()));
	}
	
	public static void setLongerAndShorterStr(String str1, String str2) {
		if(str1.length() >= str2.length()) {
			longer = str1;
			shorter = str2;
		} else {
			longer = str2;
			shorter = str1;
		}
	}

}
