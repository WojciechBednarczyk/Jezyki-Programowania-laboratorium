package lab03;

import java.io.Serializable;
import java.time.LocalDate;

public class Próbki implements Serializable {

	private final String idPróbki;
	private final String badanyCzynnik;
	private final LocalDate dataPobrania;

	public Próbki(String idPróbki, String badanyCzynnik, LocalDate dataPobrania) {
		this.idPróbki = idPróbki;
		this.badanyCzynnik = badanyCzynnik;
		this.dataPobrania = dataPobrania;
	}

	@Override
	public String toString() {
		System.out.println();
		return idPróbki + " " + badanyCzynnik + " " + dataPobrania;
	}

	public String getIdPróbki() {
		return idPróbki;
	}

	public String getBadanyCzynnik() {
		return badanyCzynnik;
	}
}
