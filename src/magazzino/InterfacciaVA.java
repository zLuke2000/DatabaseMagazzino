package magazzino;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
class InterfacciaVA extends JFrame{

    private final String valColonne[] = ControlloConfig.NOME_PARAMETRO;
    private int numArticoli;
    private String valori[][];
	private JTable tabella;
	private JScrollPane sp;
	private List<Articolo> articoli = new ArrayList<Articolo>();
    	
	InterfacciaVA() {
		super("Visualizza articolo");
		articoli = Database.selectAll();
		if(articoli != null) {
			numArticoli = articoli.size();
			valori = new String[numArticoli][ControlloConfig.NUM_PARAMETRI];
			for(int i=0; i<numArticoli; i++) {
				valori[i][0] = articoli.get(i).getTipologia();
				valori[i][1] = articoli.get(i).getCodiceArticolo();
				valori[i][2] = articoli.get(i).getDescrizione();
				valori[i][3] = articoli.get(i).getCostruttore();
				valori[i][4] = articoli.get(i).getFornitore();
				valori[i][5] = String.valueOf(articoli.get(i).getQuantita());
				valori[i][6] = articoli.get(i).getUbicazione();
				valori[i][7] = "\u20AC " + String.valueOf(articoli.get(i).getPrezzo());
			}
			tabella = new JTable(valori, valColonne);
			tabella.setEnabled(false);
			sp = new JScrollPane(tabella);
			getContentPane().add(sp);
			
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	            	InterfacciaAvvio.GUIavvio.setVisible(true);
	            	if(articoli != null) {
	            		articoli.clear();
	            	}
	        		dispose();
	            }
	        });
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setSize(700,700);
			setVisible(true);
		}
		else {
			InterfacciaAvvio.GUIavvio.setVisible(true);
			dispose();
		}
	}
}
