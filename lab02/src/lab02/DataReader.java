package lab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataReader {

	private int orderID = 1;

	/**
	 * Zmienna potrzebna do minimalizowania wypieczonych pizz
	 */
	private int maxSlices = 8;

	public DataReader(String fileName, int maxPizzas) {
		List<Order> orderList = new LinkedList<>();
		List<Pizza> pizzaList = new LinkedList<>();

		/**
		 * Czytanie z pliku oraz zapisywanie zamówieñ do listy
		 */
		try (Scanner s = new Scanner(new File(fileName))) {

			s.nextLine();
			s.useDelimiter(";|\\r?\\n|\\r");

			while (s.hasNext()) {
				Order order = new Order();
				order.setOrderID(orderID);
				orderID++;
				order.setNumber(s.nextInt());
				String[] listaDodatkow = s.next().split(",");
				for (String str : listaDodatkow) {
					order.getTopingsList().add(Integer.parseInt(str.trim()));
				}

				orderList.add(order);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Nie znaleziono pliku!");
		}

		/**
		 * Sortowanie listy dodatków
		 */
		for (Order order : orderList) {
			Collections.sort(order.getTopingsList());

		}

		/**
		 * Tworzenie listy pizz i sumowanie zamówieñ
		 */
		int counter;
		for (int i = 0; i < orderList.size(); i++) {
			Pizza pizza = new Pizza();
			pizza.setNumber(orderList.get(i).getNumber());
			pizza.setTopingsList(orderList.get(i).getTopingsList());
			pizza.addCustomer(pizza.getNumber());
			pizza.setOrderID(orderList.get(i).getOrderID());
			pizza.addOrderID(pizza.getOrderID());
			pizzaList.add(pizza);
		}

		/**
		 * Zliczanie takich samych zamówieñ
		 */
		for (int i = 0; i < pizzaList.size(); i++) {

			counter = 1;
			for (int j = i + 1; j < pizzaList.size(); j++) {

				/**
				 * Sprawdzanie czy taka pizza ju¿ istnieje i usuwanie jej z listy jeœli tak oraz
				 * dodanie do licznika
				 */
				if (pizzaList.get(i).getTopingsList().equals(pizzaList.get(j).getTopingsList())) {
					counter++;
					pizzaList.get(i).addCustomer(pizzaList.get(j).getNumber());
					pizzaList.get(i).addOrderID(pizzaList.get(j).getOrderID());
					pizzaList.remove(j);
					j--;
				}
			}
			pizzaList.get(i).setAmountOfSlices(counter);
		}

		System.out.println();

		/**
		 * Tworzenie pizzy
		 */

		boolean bake = true;
		while (bake) {
			for (int i = 0; i < pizzaList.size(); i++) {
				while (pizzaList.get(i).getAmountOfSlices() / maxSlices > 0) {
					pizzaList.get(i).makePizza(pizzaList.get(i).getAmountOfPizzas() + 1);
					maxPizzas--;

					/**
					 * Przeszukiwanie które zamówienia zaliczyæ jako zrealizowane i usuniêcie ich z
					 * orderList
					 */
					for (int j = 0; j < pizzaList.get(i).getOrderIDList().size(); j++) {
						for (int k = 0; k < orderList.size(); k++) {

							if (orderList.get(k).getOrderID() == pizzaList.get(i).getOrderIDList().get(j)) {
								orderList.remove(k);
								k--;
							}
						}
					}

					if (maxPizzas == 0)
						break;
					pizzaList.get(i).setAmountOfSlices(pizzaList.get(i).getAmountOfSlices() - maxSlices);
				}
				if (maxPizzas == 0)
					break;
			}

			maxSlices--;
			if (maxSlices == 0 || maxPizzas == 0)
				bake = false;
		}

		/**
		 * Wypisanie zestawu wypieczonych pizz z przypisaniem kawa³ków do klientów
		 */
		System.out.println("Liczba pizz;Lista dodatków;Lista numerów klientów przypisanych do kawa³ków");
		for (Pizza pizza : pizzaList) {
			if (pizza.getAmountOfPizzas() != 0)
				System.out.println(pizza);
		}

		/**
		 * Wypisanie niezrealizowanych zamówieñ
		 */
		System.out.println();
		System.out.println("Zamówienia niezrealizowane");
		System.out.println("Numer klienta;Lista dodatków");
		for (Order order : orderList) {
			System.out.println(order);
		}

	}
}
