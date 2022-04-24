package lab03;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class LaboratoriumDiagnostyczne {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		List<Pr�bki> doWykonania = new ArrayList<>();
		List<Wyniki> wyniki = new ArrayList<>();
		List<Pr�bki> doOdeslania = new ArrayList<>();
		Map<Pr�bki, Integer> mapaWynikow = new LinkedHashMap<>();
		String imieNazwisko;
		Scanner scanner = new Scanner(System.in);
		LocalDate data = LocalDate.now();
		PacjentDAO pacjentDAO = new PacjentDAO();
		pacjentDAO.odczyt();
		System.out.println("Witamy w laboratorium diagnostycznym!");
		System.out.println("Dzisiejsza data: " + data);
		pacjentDAO.odczytProbek();
		System.out.println();
		System.out.println("Podaj imi� i nazwisko:");
		imieNazwisko = scanner.nextLine();
		System.out.println("Witamy dr. " + imieNazwisko);

		boolean menu = true;
		boolean wyslane = true;
		int iloscSerii = 0;
		while (menu) {

			System.out.println("Co chcesz zrobi�?");
			System.out.println("[1] Odbierz pr�bki");
			System.out.println("[2] Przeprowad� badania");
			System.out.println("[3] Ode�lij wyniki");
			System.out.println("[9] Wyjd�");
			String wybor = scanner.nextLine();
			String poprzedniIdentyfikator = "";
			String identyfikator;

			switch (wybor) {
			case "1":
				doWykonania = pacjentDAO.odbierzProbki();
				if (doWykonania.isEmpty()) {
					System.out.println("Brak pr�bek od odebrania");
					break;
				} else {
					System.out.println("Odebrano pr�bki do badania");
					poprzedniIdentyfikator = doWykonania.get(0).getIdPr�bki().substring(0,
							doWykonania.get(0).getIdPr�bki().indexOf("_"));
					for (Pr�bki probka : doWykonania) {
						System.out.println(probka);
						identyfikator = probka.getIdPr�bki().substring(0, probka.getIdPr�bki().indexOf("_"));
						if (!identyfikator.equals(poprzedniIdentyfikator)) {
							iloscSerii++;
						}
					}

					System.out.println();
				}
				break;
			case "2":
				if (doWykonania == null || doWykonania.isEmpty()) {
					System.out.println("Brak pr�bek do badania");
					System.out.println();
				} else {
					int wynikBadaniaCzynnika;
					poprzedniIdentyfikator = doWykonania.get(0).getIdPr�bki().substring(0,
							doWykonania.get(0).getIdPr�bki().indexOf("_"));
					identyfikator = poprzedniIdentyfikator;
					mapaWynikow.clear();

					for (Iterator<Pr�bki> it = doWykonania.iterator(); it.hasNext();) {
						Pr�bki probka = it.next();
						identyfikator = probka.getIdPr�bki().substring(0, probka.getIdPr�bki().indexOf("_"));
						if (!identyfikator.equals(poprzedniIdentyfikator)) {
							identyfikator = poprzedniIdentyfikator;
							break;
						} else {
							wynikBadaniaCzynnika = (int) (Math.random() * 3) - 1;
							mapaWynikow.put(probka, wynikBadaniaCzynnika);
							doOdeslania.add(probka);
							it.remove();
						}
					}

					Wyniki wynik = new Wyniki(identyfikator, data, mapaWynikow, imieNazwisko);
					wyslane = false;
					wyniki.add(wynik);
					System.out.println(wynik);
					System.out.println();
//                        System.out.println("Pozosta�ych serii do bada�: " + iloscSerii);   //Nie dzia�a jak powinno!
				}
				break;
			case "3":
				pacjentDAO.odeslijWyniki(wyniki);
				System.out.println("Wyniki zosta�y odes�ane do punktu pobra�");
				System.out.println();
				wyslane = true;
				break;
			case "4":
				System.out.println(doWykonania);
				break;
			case "9":
				if (wyslane != true)
					System.out.println("Zanim zako�czysz prac� ode�lij wyniki do punktu pobra�!");
				else
					menu = false;
				pacjentDAO.zapis();
				pacjentDAO.zapisProbek();
				break;
			default:
				System.out.println("Wybrano niepoprawn� opcj�! Prosz� wybra� jeden z powy�szych numer�w");
				break;
			}

		}

	}
}
