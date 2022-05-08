package utils;

/** Util class for calculating with prime numbers */
public class PrimeCalculator {

	/** checks if number is prime or not */
	public static boolean isPrime(int number) {
		
		if (number < 1) {
			return false;
		}
		
		for (int divisor = 2; divisor < Math.sqrt(number); divisor++) {
			if (number % divisor == 0) {
				return false;
			}
		}
		
		return true;
	}
}
