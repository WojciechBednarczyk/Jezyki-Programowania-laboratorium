package lab02;

/**
 * @author Wojciech Bednarczyk
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			final int maxPizzas = Integer.parseInt(args[0]);
			new DataReader("inputData.txt", maxPizzas);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Nie podano parametru!");
		}

	}

}
