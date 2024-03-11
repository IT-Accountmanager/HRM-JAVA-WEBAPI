package com.hrm.helper;

public class Convert {

	public static String convertToWords(int number) {
		if (number == 0) {
			return "Zero";
		}
		return convertChunkToWords(number);
	}

	private static String convertChunkToWords(int number) {
		String[] units = { "", "Thousand", "Million", "Billion", "Trillion", "Quadrillion" };
		int chunkIndex = 0;

		String words = "";

		do {
			int chunk = number % 1000;
			if (chunk != 0) {
				words = convertThreeDigitNumberToWords(chunk) + " " + units[chunkIndex] + " " + words;
			}
			number /= 1000;
			chunkIndex++;
		} while (number > 0);

		return words.trim();
	}

	private static String convertThreeDigitNumberToWords(int number) {
		String[] belowTwenty = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
				"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
		String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

		int hundred = number / 100;
		int remainder = number % 100;

		String words = "";

		if (hundred > 0) {
			words += belowTwenty[hundred] + " Hundred ";
		}

		if (remainder < 20) {
			words += belowTwenty[remainder];
		} else {
			words += tens[remainder / 10] + " " + belowTwenty[remainder % 10];
		}

		return words.trim();
	}
}
