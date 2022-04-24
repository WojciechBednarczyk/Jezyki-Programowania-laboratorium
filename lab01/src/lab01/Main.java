/**
 * 
 */
package lab01;

/**
 * @author Wojciech Bednarczyk
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(args[0]);
		int linia = 0;
		int spacje = n;
		int spacje_srodkowe = n * 2 - 1;

		// Rysowanie górnej czêœci
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < spacje; j++) {
				System.out.print(" ");
			}
			System.out.print("*");
			for (int k = 0; k < linia * 2 - 1; k++) {
				System.out.print(" ");
			}
			if (linia != 0) {
				System.out.print("*");
			}
			System.out.println();
			spacje--;
			linia++;

		}

		// Rysowanie œrodkowej
		System.out.print("*");
		for (int i = 0; i < spacje_srodkowe; i++) {
			System.out.print(" ");
		}
		System.out.print("*");
		for (int i = 0; i < spacje_srodkowe; i++) {
			System.out.print(" ");
		}
		System.out.print("*");
		System.out.println();

		// Rysowanie dolnej czêœci
		spacje = n * 2 + 1;
		linia = n - 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < spacje; j++) {
				System.out.print(" ");
			}
			System.out.print("*");
			for (int k = 0; k < linia * 2 - 1; k++) {
				System.out.print(" ");
			}
			if (linia != 0) {
				System.out.print("*");
			}
			System.out.println();

			linia--;
			spacje++;
		}

	}

}
