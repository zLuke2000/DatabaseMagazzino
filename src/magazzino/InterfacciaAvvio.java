package magazzino;

import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
class InterfacciaAvvio extends JFrame{
		
	private final int LARGHEZZA = 300;
	private final int ALTEZZA = 250;
	
	static InterfacciaAvvio GUIavvio;
	// LAYOUT
	private Container container;
	private final GridBagLayout layout = new GridBagLayout();
	private final GridBagConstraints constraints = new GridBagConstraints();
	// JBUTTON
	private final JButton b_inserisciAtricolo = new JButton("Inserisci articolo");
	private final JButton b_visualizzaArticolo = new JButton("Visualizza articolo");
	private final JButton b_modificaArticolo = new JButton("Modifica articolo");
	private final JButton b_eliminaArticolo = new JButton("Elimina articolo");
	private final JButton b_visualizzaTabella = new JButton("Visualizza ACCESS");
	
	
	public static void main(String[] args) {
		GUIavvio = new InterfacciaAvvio();
	}

	private InterfacciaAvvio() {
		super("Gestione magazzino");
		//CONTROLLO FILE CONFIGURAZIONE
		new ControlloConfig();
		
		//LAYOUT
		container = getContentPane();
		container.setLayout(layout);
		
		//JBUTTON
		addComponent(b_inserisciAtricolo, 0, 0, 2, 1);
		b_inserisciAtricolo.addActionListener(event -> aggiungiBH(event));
		addComponent(b_visualizzaArticolo, 1, 0, 2, 1);
		b_visualizzaArticolo.addActionListener(event -> visualizzaBH(event));
		addComponent(b_modificaArticolo, 2, 0, 2, 1);
		b_modificaArticolo.addActionListener(event -> modificaBH(event));
		addComponent(b_eliminaArticolo, 3, 0, 2, 1);
		b_eliminaArticolo.addActionListener(event -> eliminaBH(event));
		addComponent(b_visualizzaTabella, 4, 0, 2, 1);
		b_visualizzaTabella.addActionListener(event -> visualizzaACCESSBH(event));
		
		setSize(LARGHEZZA, ALTEZZA);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void aggiungiBH(ActionEvent e) {
		setVisible(false);
		new InterfacciaAA();
	}
	private void visualizzaBH(ActionEvent e) {
		setVisible(false);
		new InterfacciaVA();
	}
	private void modificaBH(ActionEvent e) {
		setVisible(false);
		new InterfacciaMA();
	}
	private void eliminaBH(ActionEvent e) {
		//setVisible(false);
		new InterfacciaEA();
	}
	private void visualizzaACCESSBH(ActionEvent e) {
		try {
			Desktop.getDesktop().open(new File(ControlloConfig.databasePATH));
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			JOptionPane.showMessageDialog(null, "Nessun database trovato", "Errore!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void addComponent(Component componente, int riga, int colonna, int larghezza, int altezza) {
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = colonna;
		constraints.gridy = riga;
		constraints.gridwidth = larghezza;
		constraints.gridheight = altezza;
		layout.setConstraints(componente, constraints);
		container.add(componente);
	}
}