package lab03;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PunktPobran {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		PacjentDAO pacjentDAO = new PacjentDAO();
		Map<String, String> probki = new LinkedHashMap<>();
		List<Próbki> listaProbek = new ArrayList<>();
		List<String> listaCzynnikow = new ArrayList<>();
		LocalDate aktualnaData = LocalDate.now();
		String[] czynniki = { "OB", "CRP", "glukoza", "elektrolity", "kreatynina", "¿elazo", "ciê¿ar", "PH", "bakterie",
				"bia³ko" };
		System.out.println("Witaj w punkcie pobrañ!");

		/**
		 * Wczytanie obiektów z plików
		 */
		pacjentDAO.odczyt();
		pacjentDAO.odczytProbek();

		System.out.println();
		Scanner scanner = new Scanner(System.in);
		String wybor;
		String czynnik;
		boolean czyPrzekazaneProbki = true;
		boolean menu = true;

		while (menu) {
			System.out.println("Co chcesz zrobiæ?");
			System.out.println();
			System.out.println("[1] Przeka¿ skierowanie");
			System.out.println("[2] SprawdŸ status badania");
			System.out.println("[3] Przeka¿ probki do laboratorium");
			System.out.println("[4] Wydaj wyniki");
			System.out.println("[5] Poka¿ bazê");
			System.out.println("[9] WyjdŸ");
			wybor = scanner.nextLine();
			switch (wybor) {
			case "1":
				listaCzynnikow.removeAll(listaCzynnikow);
				probki.clear();
				System.out.println("Podaj dane ze skierowania!");
				System.out.println();
				System.out.println("Podaj idendyfikator skierowania");
				String identyfikatorSkierowania = scanner.nextLine();
				if (!(pacjentDAO.wyszukajPoID(identyfikatorSkierowania) == null)) {
					System.out.println("Takie skierowanie ju¿ istnieje w systemie!");
					System.out.println();
					break;
				} else {
					System.out.println("Podaj datê skierowania");
					boolean poprawnaData = false;
					String dataSkierowania = null;
					while (poprawnaData == false) {
						dataSkierowania = scanner.nextLine();
						try {
							LocalDate data = LocalDate.parse(dataSkierowania);
							poprawnaData = true;
						} catch (DateTimeParseException e) {
							System.out.println("Niepoprawny format daty!");
							System.out.println("Podaj datê w formacie (rok-miesi¹c-dzieñ)");
						}
					}
					System.out.println("Podaj badane czynniki w kolejnych wierszach");
					System.out.println("Aby zakoñczyæ wpisz \"koniec\"");
					System.out.println("[Krew]: OB, CRP, glukoza, elektrolity, kreatynina, ¿elazo");
					System.out.println("[Mocz]: ciê¿ar, PH, bakterie, bia³ko");

					int licznik = 0;
					String idProbki;
					while (true) {
						czynnik = scanner.nextLine();

						if (czynnik.equals("koniec"))
							break;
						else if (listaCzynnikow.contains(czynnik))
							System.out.println("Ju¿ podano ten czynnik!");
						else {
							if (!Arrays.asList(czynniki).contains(czynnik)) {
								System.out.println("Podano niepoprawny czynnik");
							} else {
								listaCzynnikow.add(czynnik);
								idProbki = identyfikatorSkierowania + "_" + licznik;
								Próbki probka = new Próbki(idProbki, czynnik, aktualnaData);
								listaProbek.add(probka);
								probki.put(czynnik, idProbki);
								licznik++;
							}
						}
					}
					czyPrzekazaneProbki = false;
					Pacjent pacjent = new Pacjent(identyfikatorSkierowania, dataSkierowania, listaCzynnikow, probki);
					pacjentDAO.zarejestruj(pacjent);
					System.out.println("Skierowanie oraz probki zosta³y przyjête");
					System.out.println();
					break;
				}

			case "2":
				System.out.println("Podaj identyfikator skierownia");
				identyfikatorSkierowania = scanner.nextLine();
				System.out.println(pacjentDAO.getStatus(identyfikatorSkierowania));
				System.out.println();
				break;

			case "3":
				pacjentDAO.przekazProbki(listaProbek);
				listaProbek.clear();
				czyPrzekazaneProbki = true;
				System.out.println("Próbki zosta³y przekazane do laboratorium");
				System.out.println();
				break;

			case "4":
				System.out.println("Podaj identyfikator skierowania");
				identyfikatorSkierowania = scanner.nextLine();
				pacjentDAO.wydajWyniki(identyfikatorSkierowania);
				break;

			case "5":
				pacjentDAO.pokazBaze();
				break;
			case "9":
				if (czyPrzekazaneProbki != true)
					System.out.println("Zanim zakoñczysz pracê przeka¿ probki do laboratorium!");
				else {
					menu = false;
					pacjentDAO.zapis();
					pacjentDAO.zapisProbek();

					continue;
				}
				break;
			default: {
				System.out.println("Wybrano niepoprawn¹ opcjê! Proszê wybraæ jeden z powy¿szych numerów");
			}

			}

		}
	}
}
