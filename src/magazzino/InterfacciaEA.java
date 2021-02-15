package magazzino;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
class InterfacciaEA extends JFrame{
	
	private String articoloCorrente;
	private Object[] articoliTrovati;
	private Articolo articoloTrovato = new Articolo();
	private int confermaEliminazione;

	InterfacciaEA() {
		super("Elimina articolo");
		articoloCorrente = (String)JOptionPane.showInputDialog(null, "Immettere codice articolo \nLasciare vuoto per vedere la lista completa", "Selezione articolo", JOptionPane.PLAIN_MESSAGE, null, null, null);
		articoliTrovati = Database.getSimile(articoloCorrente.toUpperCase());
		if(articoliTrovati != null && articoliTrovati.length > 0) {
			articoloCorrente = (String)JOptionPane.showInputDialog(null, "Selezionare articolo da modificare:", "Selezione articolo", JOptionPane.PLAIN_MESSAGE, null, articoliTrovati, null);
			articoloTrovato = Database.getArticoloByCodice(articoloCorrente);
			if(articoloTrovato != null) {
				confermaEliminazione = JOptionPane.showConfirmDialog(null, "Rimuovere: " + articoloTrovato.getCodiceArticolo() + " ? \n" + "Una volta confermato non \u00E8 più possibile recuperare l'articolo"
															  , "Rimuovere articolo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if(confermaEliminazione == 0) {
					Database.eliminaArticolo(articoloTrovato.getCodiceArticolo());
					this.dispose();
				}
				else {
					this.dispose();
				}
			}
		}
	}
}