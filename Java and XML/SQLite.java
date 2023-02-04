package hotel;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SQLite {
	final String JDBC_DRIVER = "org.sqlite.JDBC";
	final String URL = "jdbc:sqlite:vendeg.sqlite";
	final String USERNAME = "root";
	final String PASSWORD = "";

	Connection conn = null;
	Statement createStatement = null;
	DatabaseMetaData dbmd = null;

	public SQLite() {
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("A kapcsolat létrejött az adatbázissal!");

		} catch (SQLException ex) {
			System.out.println("Valami baj van a kapcsolat létrehozásakor.");
			System.out.println("" + ex);
		}

		if (conn != null) {
			try {
				createStatement = conn.createStatement();

			} catch (SQLException ex) {
				System.out.println("Valami baj van van a createStatament létrehozásakor.");
				System.out.println("" + ex);
			}

		}

		try {
			dbmd = conn.getMetaData();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a DatabaseMetaData létrehozásakor..");
			System.out.println("" + ex);
		}

		try {
			ResultSet rs = dbmd.getTables(null, null, "%", null);
			if (!rs.next()) {
				createStatement.execute(
						"CREATE TABLE `vendegek` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `vezeteknev` TEXT, `keresztnev` TEXT, `cim` TEXT, `telszam` INTEGER, `bejelentkezes` TEXT, `kijelentkezes` TEXT, `szobaszam` TEXT, `szobatipus` TEXT, `fo` TEXT);");
			}

		} catch (SQLException ex) {
			System.out.println("Valami baj van az adattáblák létrehozásakor.");
			System.out.println("" + ex);
		}
	}

	public ObservableList<Vendeg> getAllGuest() {
		String sql = "select * from vendegek";
		ResultSet rs = null;
		ObservableList<Vendeg> guestList = FXCollections.observableArrayList();
		try {
			rs = createStatement.executeQuery(sql);
			while (rs.next()) {
				Vendeg adat = new Vendeg(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10));

				guestList.add(adat);

			}

		} catch (SQLException ex) {
			System.out.println("Valami baj van a userek kiolvasásakor");
			System.out.println("" + ex);
			return null;
		}

		return guestList;
	}

	public void addVendeg(String vezeteknev, String keresztnev, String cim, int telszam, String bejelentkezes,
			String kijelentkezes, String szobaszam, String szobatipus, String fo) {
		try {
			String sql = "insert into vendegek (vezeteknev, keresztnev, cim, telszam, bejelentkezes, kijelentkezes, szobaszam, szobatipus, fo) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, vezeteknev);
			preparedStatement.setString(2, keresztnev);
			preparedStatement.setString(3, cim);
			preparedStatement.setInt(4, telszam);
			preparedStatement.setString(5, bejelentkezes);
			preparedStatement.setString(6, kijelentkezes);
			preparedStatement.setString(7, szobaszam);
			preparedStatement.setString(8, szobatipus);
			preparedStatement.setString(9, fo);

			preparedStatement.execute();
		
		} catch (SQLException ex) {
			System.out.println("Valami baj van a user hozzáadásakor");
			System.out.println("" + ex);
		}
	}

	public void updateVendeg(String id, String vezeteknev, String keresztnev, String cim, int telszam,
			String bejelentkezes, String kijelentkezes, String szobaszam, String szobatipus, String fo) {
		try {
			String sql = "update vendegek set vezeteknev = ?, keresztnev =?, cim=?, telszam=?, bejelentkezes=?, kijelentkezes=?, szobaszam=?, szobatipus=?, fo=? where id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, vezeteknev);
			preparedStatement.setString(2, keresztnev);
			preparedStatement.setString(3, cim);
			preparedStatement.setInt(4, telszam);
			preparedStatement.setString(5, bejelentkezes);
			preparedStatement.setString(6, kijelentkezes);
			preparedStatement.setString(7, szobaszam);
			preparedStatement.setString(8, szobatipus);
			preparedStatement.setString(9, fo);
			preparedStatement.setString(10, id);

			preparedStatement.execute();

		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact hozzáadásakor");
			System.out.println("" + ex);
		}

	}

	public void removeGuest(String id) {
		try {
			String sql = "delete from vendegek where id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact törlésekor");
			System.out.println("" + ex);
		}
	}
}
