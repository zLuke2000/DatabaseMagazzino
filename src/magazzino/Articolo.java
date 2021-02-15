package magazzino;

class Articolo {
	private String tipologia;
	private String codiceArticolo;
	private String descrizione;
	private String costruttore;
	private String fornitore;
	private int quantita;
	private String ubicazione;
	private double prezzo;
	
	Articolo() {}
	
	Articolo(String tipologia, String codiceArticolo, String descrizione, String costruttore, String fornitore, int quantita, String ubicazione, double prezzo) {
		this.tipologia = tipologia;
		this.codiceArticolo = codiceArticolo;
		this.descrizione = descrizione;
		this.costruttore = costruttore;
		this.fornitore = fornitore;
		this.quantita = quantita;
		this.ubicazione = ubicazione;
		this.prezzo = prezzo;
	}

	String getTipologia() {
		return tipologia;
	}

	void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	String getCodiceArticolo() {
		return codiceArticolo;
	}

	void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	String getDescrizione() {
		return descrizione;
	}

	void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	String getCostruttore() {
		return costruttore;
	}

	void setCostruttore(String costruttore) {
		this.costruttore = costruttore;
	}

	String getFornitore() {
		return fornitore;
	}

	void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}

	int getQuantita() {
		return quantita;
	}

	void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	String getUbicazione() {
		return ubicazione;
	}

	void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

	double getPrezzo() {
		return prezzo;
	}

	void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
}
