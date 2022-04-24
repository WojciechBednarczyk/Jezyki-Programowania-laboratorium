package lab03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacjentDAO {

	private List<Pacjent> listaPacjentow = new ArrayList<>();
	private List<Próbki> probki;
	private List<Wyniki> wyniki;

	public void zapis() throws IOException {
		ObjectOutputStream plik;
		try {
			plik = new ObjectOutputStream(new FileOutputStream("ListaPacjentów.txt"));
			plik.writeObject(listaPacjentow);
			plik.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void zapisProbek() throws IOException {
		ObjectOutputStream plikProbek;
		try {
			plikProbek = new ObjectOutputStream(new FileOutputStream("Próbki.txt"));
			plikProbek.writeObject(probki);
			plikProbek.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void odczyt() {
		ObjectInputStream plikOdczyt;
		try {
			plikOdczyt = new ObjectInputStream(new FileInputStream("ListaPacjentów.txt"));
			listaPacjentow = (List<Pacjent>) plikOdczyt.readObject();
//            System.out.println("Wczytano bazê pacjentów");

		} catch (IOException ignored) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void odczytProbek() {
		ObjectInputStream plikOdczytProbek;
		probki = null;
		try {
			plikOdczytProbek = new ObjectInputStream(new FileInputStream("Próbki.txt"));
			probki = (List<Próbki>) plikOdczytProbek.readObject();
			System.out.println("Wczytano próbki do badania");

		} catch (IOException ignored) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getStatus(String id) {
		if (wyszukajPoID(id) == null)
			return "Nie znaleziono takiego pacjenta!";
		else
			return wyszukajPoID(id).getStatus();
	}

	// zarejestrowanie pacjenta w systemie
	public void zarejestruj(Pacjent pacjent) {
		listaPacjentow.add(pacjent);
	}

	// Przekazanie próbek do laboratorium
	public void przekazProbki(List<Próbki> probki) {

		if (this.probki == null)
			this.probki = new ArrayList<>();
		this.probki.addAll(probki);
		for (Pacjent pacjent : listaPacjentow) {
			if (pacjent.getStatus().equals("Oczekuj¹ce")) {
				pacjent.setStatus("Przekazane do laboratorium");

			}
		}
	}

	public List<Próbki> odbierzProbki() {
		return probki;
	}

	// Wydanie wyników pacjentowi
	public void wydajWyniki(String id) {
		if (wyszukajPoID(id).equals(null))
			System.out.println("Nie znaleziono takiego pacjenta. SprawdŸ jeszcze raz identyfikator skierowania.");
		else {
			if (!wyszukajPoID(id).getStatus().equals("Wyniki badañ gotowe do odebrania"))
				System.out.println("Wyniki badañ jeszcze nie s¹ gotowe do odebrania");
			else {
				System.out.println("Legenda:");
				System.out.println("-1 wyniki poni¿ej normy");
				System.out.println("0 Wynik w normie");
				System.out.println("1 Wynik powy¿ej normy");
				System.out.println(wyszukajPoID(id).getWynikiBadania());
				listaPacjentow.remove(wyszukajPoID(id));
			}
		}
	}

	public void odeslijWyniki(List<Wyniki> wyniki) {
		this.wyniki = wyniki;
		for (Wyniki wynik : wyniki) {
			for (Pacjent pacjent : listaPacjentow) {
				if (wynik.getIdentyfikator().equals(pacjent.getId())) {
					pacjent.setWynikiBadania(wynik);
					pacjent.setStatus("Wyniki badañ gotowe do odebrania");
				}
			}
		}
	}

	public Pacjent wyszukajPoID(String id) {
		for (Pacjent pacjent : listaPacjentow) {
			if (pacjent.getId().equals(id)) {
				return pacjent;
			}
		}
		return null;
	}

	public void pokazBaze() {
		System.out.println("Identyfikator skierowania | Data skierowania | Status");
		System.out.println();
		for (Pacjent pacjent : listaPacjentow) {
			System.out.println(pacjent);
		}
		System.out.println();
	}

}