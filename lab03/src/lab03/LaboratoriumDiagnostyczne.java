package lab03;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class LaboratoriumDiagnostyczne {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		List<Próbki> doWykonania = new ArrayList<>();
		List<Wyniki> wyniki = new ArrayList<>();
		List<Próbki> doOdeslania = new ArrayList<>();
		Map<Próbki, Integer> mapaWynikow = new LinkedHashMap<>();
		String imieNazwisko;
		Scanner scanner = new Scanner(System.in);
		LocalDate data = LocalDate.now();
		PacjentDAO pacjentDAO = new PacjentDAO();
		pacjentDAO.odczyt();
		System.out.println("Witamy w laboratorium diagnostycznym!");
		System.out.println("Dzisiejsza data: " + data);
		pacjentDAO.odczytProbek();
		System.out.println();
		System.out.println("Podaj imiê i nazwisko:");
		imieNazwisko = scanner.nextLine();
		System.out.println("Witamy dr. " + imieNazwisko);

		boolean menu = true;
		boolean wyslane = true;
		int iloscSerii = 0;
		while (menu) {

			System.out.println("Co chcesz zrobiæ?");
			System.out.println("[1] Odbierz próbki");
			System.out.println("[2] PrzeprowadŸ badania");
			System.out.println("[3] Odeœlij wyniki");
			System.out.println("[9] WyjdŸ");
			String wybor = scanner.nextLine();
			String poprzedniIdentyfikator = "";
			String identyfikator;

			switch (wybor) {
			case "1":
				doWykonania = pacjentDAO.odbierzProbki();
				if (doWykonania.isEmpty()) {
					System.out.println("Brak próbek od odebrania");
					break;
				} else {
					System.out.println("Odebrano próbki do badania");
					poprzedniIdentyfikator = doWykonania.get(0).getIdPróbki().substring(0,
							doWykonania.get(0).getIdPróbki().indexOf("_"));
					for (Próbki probka : doWykonania) {
						System.out.println(probka);
						identyfikator = probka.getIdPróbki().substring(0, probka.getIdPróbki().indexOf("_"));
						if (!identyfikator.equals(poprzedniIdentyfikator)) {
							iloscSerii++;
						}
					}

					System.out.println();
				}
				break;
			case "2":
				if (doWykonania == null || doWykonania.isEmpty()) {
					System.out.println("Brak próbek do badania");
					System.out.println();
				} else {
					int wynikBadaniaCzynnika;
					poprzedniIdentyfikator = doWykonania.get(0).getIdPróbki().substring(0,
							doWykonania.get(0).getIdPróbki().indexOf("_"));
					identyfikator = poprzedniIdentyfikator;
					mapaWynikow.clear();

					for (Iterator<Próbki> it = doWykonania.iterator(); it.hasNext();) {
						Próbki probka = it.next();
						identyfikator = probka.getIdPróbki().substring(0, probka.getIdPróbki().indexOf("_"));
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
//                        System.out.println("Pozosta³ych serii do badañ: " + iloscSerii);   //Nie dzia³a jak powinno!
				}
				break;
			case "3":
				pacjentDAO.odeslijWyniki(wyniki);
				System.out.println("Wyniki zosta³y odes³ane do punktu pobrañ");
				System.out.println();
				wyslane = true;
				break;
			case "4":
				System.out.println(doWykonania);
				break;
			case "9":
				if (wyslane != true)
					System.out.println("Zanim zakoñczysz pracê odeœlij wyniki do punktu pobrañ!");
				else
					menu = false;
				pacjentDAO.zapis();
				pacjentDAO.zapisProbek();
				break;
			default:
				System.out.println("Wybrano niepoprawn¹ opcjê! Proszê wybraæ jeden z powy¿szych numerów");
				break;
			}

		}

	}
}
