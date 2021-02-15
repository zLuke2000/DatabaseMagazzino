package magazzino;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Database {
	
	private final static String  url = "jdbc:ucanaccess://" + ControlloConfig.databasePATH;
	private static List<Articolo> articoliTrovati = new ArrayList<Articolo>();
	private static Articolo articoloTrovato;
		
	static List<Articolo> selectAll() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			String sql = "SELECT * "
					   + "FROM Articoli";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				articoliTrovati.add(new Articolo(rs.getString("Tipologia"),
	 											 rs.getString("CodiceArticolo"),
	 											 rs.getString("Descrizione"),
	 											 rs.getString("Costruttore"),
	 											 rs.getString("Fornitore"),
	 											 rs.getInt("Quantita"),
	 											 rs.getString("Ubicazione"),
	 											 rs.getInt("Prezzo")));
			}
			con.close();
		}
		catch(Exception sqlEx) {
			System.err.println(sqlEx);
		}
		return articoliTrovati;
	}

	static void inserisciArticolo(String insertQuery) {
		try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO Articoli (CodiceArticolo, Tipologia, Descrizione, Costruttore, Fornitore, Quantita, Ubicazione, Prezzo) "
					 	   + "VALUES (" + insertQuery + ")");
			con.close();
		}
		catch(Exception sqlEx) {
			System.err.println(sqlEx);
		}
	}
	
	static void aggiornaArticolo(Articolo articolo) {
		try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url);
			PreparedStatement pstmt = con.prepareStatement("UPDATE Articoli "
														 + "SET CodiceArticolo = ?, "
														 + "Tipologia = ?, "
														 + "Descrizione = ?, "
														 + "Costruttore = ?, "
														 + "Fornitore = ?, "
														 + "Quantita = ?, "
														 + "Ubicazione = ?, "
														 + "Prezzo = ? "
														 + "WHERE CodiceArticolo = ? ");
			pstmt.setString(1, articolo.getCodiceArticolo());
			pstmt.setString(2, articolo.getTipologia());
			pstmt.setString(3, articolo.getDescrizione());
			pstmt.setString(4, articolo.getCostruttore());
			pstmt.setString(5, articolo.getFornitore());
			pstmt.setInt(6, articolo.getQuantita());
			pstmt.setString(7, articolo.getUbicazione());
			pstmt.setDouble(8, articolo.getPrezzo());
			pstmt.setString(9, articolo.getCodiceArticolo());
			pstmt.executeUpdate();
			con.close();
		}
		catch(Exception sqlEx) {
			System.err.println(sqlEx);
		}
	}
	
	static void eliminaArticolo(String codiceArticolo) {
		try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			st.executeUpdate("DELETE FROM Articoli "
					 	   + "WHERE CodiceArticolo = '" + codiceArticolo + "'");
			con.close();
		}
		catch(Exception sqlEx) {
			System.err.println(sqlEx);
		}
	}
	
	static boolean controlloDuplicati(String codiceArticolo) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			String sql = "SELECT CodiceArticolo "
					   + "FROM Articoli "
					   + "WHERE CodiceArticolo = '" + codiceArticolo + "'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next() == true) {
				con.close();
				return true;
			}
			con.close();
		}
		catch(Exception sqlEx) {
			System.err.println(sqlEx);
		}
		return false;
	}

	static Object[] getSimile(String articolo) {
		int n = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			String sql = "SELECT COUNT(CodiceArticolo) "
					   + "FROM Articoli "
				       + "WHERE CodiceArticolo LIKE '*" + articolo + "*'";
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			n = rs.getInt(1);
			Object[] articoliTrovati = new Object[n];
			sql = "SELECT CodiceArticolo "
				+ "FROM Articoli "
				+ "WHERE CodiceArticolo LIKE '*" + articolo + "*'";
			rs = st.executeQuery(sql);
			for(int i=0; i<n; i++) {
				rs.next();
				articoliTrovati[i] = rs.getString("CodiceArticolo");
			}
			con.close();
			return articoliTrovati;
		}
		catch(Exception sqlEx) {
			System.err.println(sqlEx);
		}
		return null;
	}
	
	static Object[] getCostruttori() {
		int n = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			String sql = "SELECT COUNT(DISTINCT Costruttore) "
					   + "FROM Articoli";
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			n = rs.getInt(1);
			sql = "SELECT DISTINCT Costruttore "
				+ "FROM Articoli";
			rs = st.executeQuery(sql);
			Object[] costruttoriTrovati = new Object[n];
			for(int i=0; i<n; i++) {
				rs.next();
				costruttoriTrovati[i] = rs.getString("Costruttore");
			}
			con.close();
			return costruttoriTrovati;
		}
		catch(Exception sqlEx) {
			System.err.println(sqlEx);
		}
		return null;
	}
	
	static Object[] getFornitori() {
		int n = 0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			String sql = "SELECT COUNT(DISTINCT Fornitore) "
					   + "FROM Articoli";
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			n = rs.getInt(1);
			sql = "SELECT DISTINCT Fornitore "
				+ "FROM Articoli";
			rs = st.executeQuery(sql);
			Object[] fornitoriTrovati = new Object[n];
			for(int i=0; i<n; i++) {
				rs.next();
				fornitoriTrovati[i] = rs.getString("Fornitore");
			}
			con.close();
			return fornitoriTrovati;
		}
		catch(Exception sqlEx) {
			System.err.println(sqlEx);
		}
		return null;
	}

	static Articolo getArticoloByCodice(String codiceArticolo) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			String sql = "SELECT * "
					   + "FROM Articoli "
					   + "WHERE CodiceArticolo = '" + codiceArticolo + "'";
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			articoloTrovato = new Articolo(rs.getString("Tipologia"),
					 					   rs.getString("CodiceArticolo"),
					 					   rs.getString("Descrizione"),
					 					   rs.getString("Costruttore"),
					 					   rs.getString("Fornitore"),
					 					   rs.getInt("Quantita"),
					 					   rs.getString("Ubicazione"),
					 				       rs.getInt("Prezzo"));
			return articoloTrovato;
		}
		catch(Exception sqlEx) {
			System.err.println(sqlEx);
		}
		return null;
	}
}