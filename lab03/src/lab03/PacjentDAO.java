package lab03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacjentDAO {

	private List<Pacjent> listaPacjentow = new ArrayList<>();
	private List<Pr�bki> probki;
	private List<Wyniki> wyniki;

	public void zapis() throws IOException {
		ObjectOutputStream plik;
		try {
			plik = new ObjectOutputStream(new FileOutputStream("ListaPacjent�w.txt"));
			plik.writeObject(listaPacjentow);
			plik.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void zapisProbek() throws IOException {
		ObjectOutputStream plikProbek;
		try {
			plikProbek = new ObjectOutputStream(new FileOutputStream("Pr�bki.txt"));
			plikProbek.writeObject(probki);
			plikProbek.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void odczyt() {
		ObjectInputStream plikOdczyt;
		try {
			plikOdczyt = new ObjectInputStream(new FileInputStream("ListaPacjent�w.txt"));
			listaPacjentow = (List<Pacjent>) plikOdczyt.readObject();
//            System.out.println("Wczytano baz� pacjent�w");

		} catch (IOException ignored) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void odczytProbek() {
		ObjectInputStream plikOdczytProbek;
		probki = null;
		try {
			plikOdczytProbek = new ObjectInputStream(new FileInputStream("Pr�bki.txt"));
			probki = (List<Pr�bki>) plikOdczytProbek.readObject();
			System.out.println("Wczytano pr�bki do badania");

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

	// Przekazanie pr�bek do laboratorium
	public void przekazProbki(List<Pr�bki> probki) {

		if (this.probki == null)
			this.probki = new ArrayList<>();
		this.probki.addAll(probki);
		for (Pacjent pacjent : listaPacjentow) {
			if (pacjent.getStatus().equals("Oczekuj�ce")) {
				pacjent.setStatus("Przekazane do laboratorium");

			}
		}
	}

	public List<Pr�bki> odbierzProbki() {
		return probki;
	}

	// Wydanie wynik�w pacjentowi
	public void wydajWyniki(String id) {
		if (wyszukajPoID(id).equals(null))
			System.out.println("Nie znaleziono takiego pacjenta. Sprawd� jeszcze raz identyfikator skierowania.");
		else {
			if (!wyszukajPoID(id).getStatus().equals("Wyniki bada� gotowe do odebrania"))
				System.out.println("Wyniki bada� jeszcze nie s� gotowe do odebrania");
			else {
				System.out.println("Legenda:");
				System.out.println("-1 wyniki poni�ej normy");
				System.out.println("0 Wynik w normie");
				System.out.println("1 Wynik powy�ej normy");
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
					pacjent.setStatus("Wyniki bada� gotowe do odebrania");
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