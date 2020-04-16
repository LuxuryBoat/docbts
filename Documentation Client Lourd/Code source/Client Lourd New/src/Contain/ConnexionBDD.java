package Contain;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnexionBDD {

	Connection con = null;

	public static Connection Conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql-bestgroupeppe.alwaysdata.net/bestgroupeppe_port?serverTimezone=UTC",
					"201361_admin", "WebAdmin");
			/*
			 if (con!=null) System.out.println("Connexion a la base de données etablie");
			 else System.out.println("Probleme de connexion");
			*/
			return con;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "--> SQLException : " + e);
			return null;		}
	}
}