package lab03;

import java.io.Serializable;
import java.time.LocalDate;

public class Pr�bki implements Serializable {

	private final String idPr�bki;
	private final String badanyCzynnik;
	private final LocalDate dataPobrania;

	public Pr�bki(String idPr�bki, String badanyCzynnik, LocalDate dataPobrania) {
		this.idPr�bki = idPr�bki;
		this.badanyCzynnik = badanyCzynnik;
		this.dataPobrania = dataPobrania;
	}

	@Override
	public String toString() {
		System.out.println();
		return idPr�bki + " " + badanyCzynnik + " " + dataPobrania;
	}

	public String getIdPr�bki() {
		return idPr�bki;
	}

	public String getBadanyCzynnik() {
		return badanyCzynnik;
	}
}
