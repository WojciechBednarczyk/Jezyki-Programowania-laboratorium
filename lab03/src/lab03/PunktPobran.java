package lab03;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PunktPobran {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		PacjentDAO pacjentDAO = new PacjentDAO();
		Map<String, String> probki = new LinkedHashMap<>();
		List<Pr�bki> listaProbek = new ArrayList<>();
		List<String> listaCzynnikow = new ArrayList<>();
		LocalDate aktualnaData = LocalDate.now();
		String[] czynniki = { "OB", "CRP", "glukoza", "elektrolity", "kreatynina", "�elazo", "ci�ar", "PH", "bakterie",
				"bia�ko" };
		System.out.println("Witaj w punkcie pobra�!");

		/**
		 * Wczytanie obiekt�w z plik�w
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
			System.out.println("Co chcesz zrobi�?");
			System.out.println();
			System.out.println("[1] Przeka� skierowanie");
			System.out.println("[2] Sprawd� status badania");
			System.out.println("[3] Przeka� probki do laboratorium");
			System.out.println("[4] Wydaj wyniki");
			System.out.println("[5] Poka� baz�");
			System.out.println("[9] Wyjd�");
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
					System.out.println("Takie skierowanie ju� istnieje w systemie!");
					System.out.println();
					break;
				} else {
					System.out.println("Podaj dat� skierowania");
					boolean poprawnaData = false;
					String dataSkierowania = null;
					while (poprawnaData == false) {
						dataSkierowania = scanner.nextLine();
						try {
							LocalDate data = LocalDate.parse(dataSkierowania);
							poprawnaData = true;
						} catch (DateTimeParseException e) {
							System.out.println("Niepoprawny format daty!");
							System.out.println("Podaj dat� w formacie (rok-miesi�c-dzie�)");
						}
					}
					System.out.println("Podaj badane czynniki w kolejnych wierszach");
					System.out.println("Aby zako�czy� wpisz \"koniec\"");
					System.out.println("[Krew]: OB, CRP, glukoza, elektrolity, kreatynina, �elazo");
					System.out.println("[Mocz]: ci�ar, PH, bakterie, bia�ko");

					int licznik = 0;
					String idProbki;
					while (true) {
						czynnik = scanner.nextLine();

						if (czynnik.equals("koniec"))
							break;
						else if (listaCzynnikow.contains(czynnik))
							System.out.println("Ju� podano ten czynnik!");
						else {
							if (!Arrays.asList(czynniki).contains(czynnik)) {
								System.out.println("Podano niepoprawny czynnik");
							} else {
								listaCzynnikow.add(czynnik);
								idProbki = identyfikatorSkierowania + "_" + licznik;
								Pr�bki probka = new Pr�bki(idProbki, czynnik, aktualnaData);
								listaProbek.add(probka);
								probki.put(czynnik, idProbki);
								licznik++;
							}
						}
					}
					czyPrzekazaneProbki = false;
					Pacjent pacjent = new Pacjent(identyfikatorSkierowania, dataSkierowania, listaCzynnikow, probki);
					pacjentDAO.zarejestruj(pacjent);
					System.out.println("Skierowanie oraz probki zosta�y przyj�te");
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
				System.out.println("Pr�bki zosta�y przekazane do laboratorium");
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
					System.out.println("Zanim zako�czysz prac� przeka� probki do laboratorium!");
				else {
					menu = false;
					pacjentDAO.zapis();
					pacjentDAO.zapisProbek();

					continue;
				}
				break;
			default: {
				System.out.println("Wybrano niepoprawn� opcj�! Prosz� wybra� jeden z powy�szych numer�w");
			}

			}

		}
	}
}
