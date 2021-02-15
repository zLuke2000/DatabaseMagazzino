package magazzino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class ControlloConfig {
	
	//PATH 
	final static String configPATH = System.getProperty("user.dir") + "/config.txt";
	final static String databasePATH = System.getProperty("user.dir") + "/database/MagazzinoSamuel.accdb";
	final static String iconPATH = System.getProperty("user.dir") + "/icon/IconaCerca.png";
	final static String fotoArticoloPATH = System.getProperty("user.dir") + "/fotoArticoli/";
	//PARAMETRI
	final static String[] NOME_PARAMETRO = {"Tipologia", "Codice Articolo", "Descrizione", "Costruttore", "Fornitore", "Quantià", "Ubicazione", "Prezzo"};
	final static int NUM_PARAMETRI = NOME_PARAMETRO.length;
	
	private static String temp;
	private static String sep = " -> ";
	private static String sepAttributi = ",";
	private static String[] tempDiviso;
	private static String[] tempAttributi;
	private static int[] attributi = new int[2];
	private final String defConfig = new String("#####################################################################################" + "\r\n"
											  + "#                                                                                   #" + "\r\n"
											  + "# ATTENZIONE                                                                        #" + "\r\n"
											  + "#                                                                                   #" + "\r\n"
											  + "# modificare solo i valori presenti dopo '->' nel formato indicato tra parentesi () #" + "\r\n"
											  + "#                                                                                   #" + "\r\n"
											  + "# il file verra' ripristinato nel caso di parametri non corretti                    #" + "\r\n"
											  + "#                                                                                   #" + "\r\n"
											  + "#####################################################################################" + "\r\n"
											  + "#"																						+ "\r\n"
											  + "# DEFAULT (dimensione) -> 50"															+ "\r\n"
									   + "\t" + "dimensione_font_testo -> 50"															+ "\r\n"
											  + "#"																						+ "\r\n"
											  + "# DEFAULT (larghezza,altezza) -> 50,50"												+ "\r\n"
									   + "\t" + "dimensione_icona -> 50,50"																+ "\r\n"
											  + "#"																						+ "\r\n"
											  + "# ** INTERFACCIA AGGIUNGI ARTICOLO **" 												+ "\r\n"
											  + "# DEFAULT (true/false) -> true"														+ "\r\n"
									   + "\t" + "AP_prodotto_fullscreen -> true"														+ "\r\n"
											  + "# DEFAULT (true/false) -> true"														+ "\r\n"
									   + "\t" + "AP_nascondi_barra_titolo -> true"														+ "\r\n"
											  + "#"																						+ "\r\n"
											  + "# ** INTERFACCIA MODIFICA ARTICOLO **"													+ "\r\n"
											  + "# DEFAULT (true/false) -> true"														+ "\r\n"
									   + "\t" + "MP_prodotto_fullscreen -> true"														+ "\r\n"
											  + "# DEFAULT (true/false) -> true"														+ "\r\n"
									   + "\t" + "MP_nascondi_barra_titolo -> true"														+ "\r\n"
											  + "#"																						+ "\r\n"
											  + "# ** INTERFACCIA ELIMINA ARTICOLO **"													+ "\r\n"
											  + "# DEFAULT (true/false) -> true"														+ "\r\n"
									   + "\t" + "EA_prodotto_fullscreen -> true"														+ "\r\n"
											  + "# DEFAULT (true/false) -> true"														+ "\r\n"
									   + "\t" + "EA_nascondi_barra_titolo -> true"														+ "\r\n"
											  + "#"																						+ "\r\n"
											  + "# ** INTERFACCIA VISUALIZZA TABELLA ARTICOLO **"										+ "\r\n"
											  + "# DEFAULT (true/false) -> true"														+ "\r\n"
									   + "\t" + "VA_prodotto_fullscreen -> true"														+ "\r\n"
											  + "# DEFAULT (true/false) -> true"														+ "\r\n"
									   + "\t" + "VA_nascondi_barra_titolo -> true"														+ "\r\n"
											  + "#");
	
	private final File f = new File(ControlloConfig.configPATH);
	
	ControlloConfig() {
		if(!f.exists()) {
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(f, true));
				bw.append(defConfig);	
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileReader fr;
		try {
			fr = new FileReader(ControlloConfig.configPATH);
			BufferedReader br = new BufferedReader(fr);
			br.readLine();
			while ((temp = br.readLine()) != null) {
				if(!temp.contains("#")) {
					tempDiviso = temp.split(sep);
					tempDiviso[0].replace("\t", "");
					if(tempDiviso[1].contains(sepAttributi)) {
						tempAttributi = tempDiviso[1].split(sepAttributi);
						attributi[0] = Integer.parseInt(tempAttributi[0]);
						attributi[1] = Integer.parseInt(tempAttributi[1]);
						
						//DA FINIRE
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}