package hotel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vendeg {

	private SimpleStringProperty id;
	private SimpleStringProperty vezeteknev;
	private SimpleStringProperty keresztnev;
	private SimpleStringProperty cim;
	private SimpleIntegerProperty telszam;
	private SimpleStringProperty bejelentkezes;
	private SimpleStringProperty kijelentkezes;
	private SimpleStringProperty szobaszam;
	private SimpleStringProperty szobatipus;
	private SimpleStringProperty fo;

	public Vendeg() {
		id = new SimpleStringProperty();
		vezeteknev = new SimpleStringProperty();
		keresztnev = new SimpleStringProperty();
		cim = new SimpleStringProperty();
		telszam = new SimpleIntegerProperty();
		bejelentkezes = new SimpleStringProperty();
		kijelentkezes = new SimpleStringProperty();
		szobaszam = new SimpleStringProperty();
		szobatipus = new SimpleStringProperty();
		fo = new SimpleStringProperty();

	}

	public Vendeg(String id, String vezeteknev, String keresztnev, String cim, int telszam, String bejelentkezes,
			String kijelentkezes, String szobaszam, String szobatipus, String fo) {

		this.id = new SimpleStringProperty(id);
		this.vezeteknev = new SimpleStringProperty(vezeteknev);
		this.keresztnev = new SimpleStringProperty(keresztnev);
		this.cim = new SimpleStringProperty(cim);
		this.telszam = new SimpleIntegerProperty(telszam);
		this.bejelentkezes = new SimpleStringProperty(bejelentkezes);
		this.kijelentkezes = new SimpleStringProperty(kijelentkezes);
		this.szobaszam = new SimpleStringProperty(szobaszam);
		this.szobatipus = new SimpleStringProperty(szobatipus);
		this.fo = new SimpleStringProperty(fo);
	}

	public final SimpleStringProperty idProperty() {
		return this.id;
	}

	public final String getId() {
		return this.idProperty().get();
	}

	public final void setId(final String id) {
		this.idProperty().set(id);
	}

	public final SimpleStringProperty vezeteknevProperty() {
		return this.vezeteknev;
	}

	public final String getVezeteknev() {
		return this.vezeteknevProperty().get();
	}

	public final void setVezeteknev(final String vezeteknev) {
		this.vezeteknevProperty().set(vezeteknev);
	}

	public final SimpleStringProperty keresztnevProperty() {
		return this.keresztnev;
	}

	public final String getKeresztnev() {
		return this.keresztnevProperty().get();
	}

	public final void setKeresztnev(final String keresztnev) {
		this.keresztnevProperty().set(keresztnev);
	}

	public final SimpleStringProperty cimProperty() {
		return this.cim;
	}

	public final String getCim() {
		return this.cimProperty().get();
	}

	public final void setCim(final String cim) {
		this.cimProperty().set(cim);
	}

	public final SimpleIntegerProperty telszamProperty() {
		return this.telszam;
	}

	public final int getTelszam() {
		return this.telszamProperty().get();
	}

	public final void setTelszam(final int telszam) {
		this.telszamProperty().set(telszam);
	}

	public final SimpleStringProperty bejelentkezesProperty() {
		return this.bejelentkezes;
	}

	public final String getBejelentkezes() {
		return this.bejelentkezesProperty().get();
	}

	public final void setBejelentkezes(final String bejelentkezes) {
		this.bejelentkezesProperty().set(bejelentkezes);
	}

	public final SimpleStringProperty kijelentkezesProperty() {
		return this.kijelentkezes;
	}

	public final String getKijelentkezes() {
		return this.kijelentkezesProperty().get();
	}

	public final void setKijelentkezes(final String kijelentkezes) {
		this.kijelentkezesProperty().set(kijelentkezes);
	}

	public final SimpleStringProperty szobaszamProperty() {
		return this.szobaszam;
	}

	public final String getSzobaszam() {
		return this.szobaszamProperty().get();
	}

	public final void setSzobaszam(final String szobaszam) {
		this.szobaszamProperty().set(szobaszam);
	}

	public final SimpleStringProperty szobatipusProperty() {
		return this.szobatipus;
	}

	public final String getSzobatipus() {
		return this.szobatipusProperty().get();
	}

	public final void setSzobatipus(final String szobatipus) {
		this.szobatipusProperty().set(szobatipus);
		;
	}

	public final SimpleStringProperty foProperty() {
		return this.fo;
	}

	public final String getFo() {
		return this.foProperty().get();
	}

	public final void setFo(final String fo) {
		this.foProperty().set(fo);

	}

	@Override
	public String toString() {
		return "Vendeg [id=" + id + ", vezeteknev=" + vezeteknev + ", keresztnev=" + keresztnev + ", cim=" + cim
				+ ", telszam=" + telszam + ", bejelentkezes=" + bejelentkezes + ", kijelentkezes=" + kijelentkezes
				+ ", szobaszam=" + szobaszam + ", szobatipus=" + szobatipus + ", fo=" + fo + "]";
	}

}
