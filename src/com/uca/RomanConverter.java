package com.uca;


import java.util.Collection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanConverter{
	
	// Table des symboles
	private static final Collection<RomanNumber> SYMBOLS = new ArrayList<>();
	static {
		SYMBOLS.add(new RomanNumber(1000, "M"));
		SYMBOLS.add(new RomanNumber(900, "CM"));
		SYMBOLS.add(new RomanNumber(500, "D"));
		SYMBOLS.add(new RomanNumber(400, "CD"));
		SYMBOLS.add(new RomanNumber(100, "C"));
		SYMBOLS.add(new RomanNumber(90, "XC"));
		SYMBOLS.add(new RomanNumber(50, "L"));
		SYMBOLS.add(new RomanNumber(40, "XL"));
		SYMBOLS.add(new RomanNumber(10, "X"));
		SYMBOLS.add(new RomanNumber(9, "IX"));
		SYMBOLS.add(new RomanNumber(5, "V"));
		SYMBOLS.add(new RomanNumber(4, "IV"));
		SYMBOLS.add(new RomanNumber(1, "I"));
	  }

	// Expression reguliere de validation
	private static final Pattern VALIDATION_RE = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");


	public static String getRomanFromNumber(int a) throws IllegalArgumentException{
		String result = new String();

		if (a < 0) {
			throw new IllegalArgumentException("Number can't be negative!");
		}

		if (a < 1 || a > 3999) {
			throw new IllegalArgumentException("Number is out of range!");
		}

		for (RomanNumber i : SYMBOLS) {
			while (a >= i.getValue()) {
				result += i.getRoman();
				a -= i.getValue();
			}
		}
		return result;
	}
	
	public static int getNumberFromRoman(String a) throws IllegalArgumentException{
		int result = 0;
		int i = 0;
		Matcher m = VALIDATION_RE.matcher(a);

		if (!m.matches()) {
			throw new IllegalArgumentException("Repetition is not allowed!");
		}


		for (RomanNumber j : SYMBOLS) {
			while ((i + j.getRoman().length() <= a.length()) && (a.substring(i, i + j.getRoman().length()).equals(j.getRoman()))) {
				result += j.getValue();
				i += j.getRoman().length();
			}
		}
		return result;
	}
}