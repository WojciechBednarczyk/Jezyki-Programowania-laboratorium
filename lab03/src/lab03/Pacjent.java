package lab03;

import java.io.Serializable;
import java.util.*;

public class Pacjent implements Serializable {

	private final String id;
	private final String dataSkierowania;
	private List<String> listaCzynnikow = new ArrayList<>();
	private final Map<String, String> probki;
	private String status = "Oczekuj¹ce";
	private Wyniki wynikiBadania;

	public Pacjent(String id, String dataSkierowania, List<String> listaCzynnikow, Map<String, String> probki) {
		this.id = id;
		this.dataSkierowania = dataSkierowania;
		this.listaCzynnikow = listaCzynnikow;
		this.probki = probki;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setWynikiBadania(Wyniki wynikiBadania) {
		this.wynikiBadania = wynikiBadania;
	}

	public String getId() {
		return id;
	}

	public Wyniki getWynikiBadania() {
		return wynikiBadania;
	}

	@Override
	public String toString() {
		return id + " " + dataSkierowania + " " + status;
	}
}
