package Contain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RequeteSql {
	Connection con = ConnexionBDD.Conexion();
	PreparedStatement ps;
	private List<String> List_Categorie = new ArrayList<String>();
	private List<String> List_Employer = new ArrayList<String>();
	private List<String> List_Utlisateurs = new ArrayList<String>();

	public static int countData(String tableName) {
		int total = 0;
		Connection con = ConnexionBDD.Conexion();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) AS 'total' FROM " + tableName);
			while (rs.next()) {
				total = rs.getInt(1);
				}
			con.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return total;

	}

	/*
	 * 
	 * Compte / Employer
	 * 
	 */

	public List<String> employerBox() {
		try {
			String requete = "SELECT E.idEmployer, C.idCompte, C.nom FROM Employer AS E JOIN Compte AS C WHERE E.idEmployer = C.idCompte";
			ps = con.prepareStatement(requete);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				List_Employer.add(rs.getInt("idEmployer") + " | " + rs.getString("C.nom"));
			}
			con.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return List_Employer;
	}

	public void insertCompte(char operation, String nom, String prenom, String email, String num,
			String password, String date) {
		String requete = "INSERT INTO Compte (`nom`, `prenom`, `email`, `mdp`, `num`, `dateNaissance`) VALUES (?,?,?,?,?,?)";
		if (operation == 'i') {
			try {
				ps = con.prepareStatement(requete);
				ps.setString(1, nom);
				ps.setString(2, prenom);
				ps.setString(3, email);
				ps.setString(4, password);
				ps.setString(5, num);
				ps.setString(6, date);

				if (ps.executeUpdate() > 0) {
					JOptionPane.showMessageDialog(null, "Nouveau employer ajouté");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void selectEmployer(String nom, String prenom, String email, String num) {
		String requete = "SELECT idCompte From Compte WHERE nom = '" + nom + "' AND prenom = '" + prenom
				+ "' AND email = '" + email + "' AND num = '" + num + "'";
		String tmpidCompte = null;

		try {
			ps = con.prepareStatement(requete);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tmpidCompte = rs.getString("idCompte");
				System.out.println(tmpidCompte);
				deleteClient(tmpidCompte);
				insertEmployer(tmpidCompte);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteClient(String tmpidCompte) {
		String requete = "DELETE FROM Client WHERE idClient = '" + tmpidCompte + "'";
		System.out.println(tmpidCompte);

		try {
			ps = con.prepareStatement(requete);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertEmployer(String tmpidCompte) {
		String requete = "INSERT INTO Employer (`idEmployer`) VALUES ('" + tmpidCompte + "')";
		System.out.println(tmpidCompte);

		try {
			ps = con.prepareStatement(requete);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * Bateau
	 * 
	 */

	public void insertBateau(char operation, String nom, String image, String prix, String description, int idEmployer,
			int idCategorie) {

		if (operation == 'i') {
			try {
				String requete = "INSERT INTO Bateau (`nomBateau`, `imageBateau`, `prix`, `description`, `idEmployer`, `idCategorie`) VALUES (?,?,?,?,?,?)";
				ps = con.prepareStatement(requete);
				ps.setString(1, nom);
				ps.setString(2, image);
				ps.setString(3, prix);
				ps.setString(4, description);
				ps.setInt(5, idEmployer);
				ps.setInt(6, idCategorie);

				if (ps.executeUpdate() > 0) {
					JOptionPane.showMessageDialog(null, "Nouveau bateau ajouté");
				}
				con.close();
				ps.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void SuppBateau(JTextField textField_id) {
		String requete = "DELETE FROM Bateau WHERE idBateau = ?";

		try {
			ps = con.prepareStatement(requete);
			ps.setString(1, textField_id.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Bateau Supprimer");
			con.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SelectInsertBateau(JTextField textField_nom, JTextField textField_prix, JTextField textField_image,
			JTextField textField_id, JTextArea textArea_desc, JTextField textField_commande,
			JTextField textField_boutique, JTextField textField_employer, JTextField textField_categorie,
			JTextField textField_createdat) {
		String requete = "SELECT * FROM Bateau WHERE idBateau = ?";

		try {
			ps = con.prepareStatement(requete);
			ps.setString(1, textField_id.getText());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String created_at = rs.getString("created_at");
				String nomBateau = rs.getString("nomBateau");
				String imageBateau = rs.getString("imageBateau");
				String prix = rs.getString("prix");
				String description = rs.getString("description");
				String idCommande = rs.getString("idCommande");
				String idBoutique = rs.getString("idBoutique");
				String idEmployer = rs.getString("idEmployer");
				String idCategorie = rs.getString("idCategorie");

				textField_createdat.setText(created_at);
				textField_nom.setText(nomBateau);
				textField_image.setText(imageBateau);
				textField_prix.setText(prix);
				textArea_desc.setText(description);
				textField_commande.setText(idCommande);
				textField_boutique.setText(idBoutique);
				textField_employer.setText(idEmployer);
				textField_categorie.setText(idCategorie);
			}
			con.close();
			ps.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void UpdateBateau(JTextField textField_nom, JTextField textField_prix, JTextField textField_image,
			JTextField textField_id, JTextArea textArea_desc, JTextField textField_commande,
			JTextField textField_boutique, JTextField textField_employer, JTextField textField_categorie,
			JTextField textField_createdat) {

		try {

			if (!textField_commande.getText().isEmpty()) {
				if (!textField_boutique.getText().isEmpty()) {
					System.out.println("commande & boutique rempli");
					String requete = "Update Bateau set created_at = ?, nomBateau = ?, imageBateau = ?, prix = ?, description = ?, idCommande = ?, idBoutique = ?, idEmployer = ?, idCategorie = ? WHERE idBateau = ?";
					ps = con.prepareStatement(requete);
					ps.setString(1, textField_createdat.getText().toString());
					ps.setString(2, textField_nom.getText().toString());
					ps.setString(3, textField_image.getText().toString());
					ps.setString(4, textField_prix.getText().toString());
					ps.setString(5, textArea_desc.getText().toString());
					ps.setString(6, textField_commande.getText().toString());
					ps.setString(7, textField_boutique.getText().toString());
					ps.setString(8, textField_employer.getText().toString());
					ps.setString(9, textField_categorie.getText().toString());
					ps.setString(10, textField_id.getText().toString());
					ps.execute();
					JOptionPane.showMessageDialog(null, "Le bateau "+textField_id.getText().toString()+" à été mis a jours.");
				} else {
					System.out.println("commande rempli");
					String requete = "Update Bateau set created_at = ?, nomBateau = ?, imageBateau = ?, prix = ?, description = ?, idCommande = ?, idEmployer = ?, idCategorie = ? WHERE idBateau = ?";
					ps = con.prepareStatement(requete);
					ps.setString(1, textField_createdat.getText().toString());
					ps.setString(2, textField_nom.getText().toString());
					ps.setString(3, textField_image.getText().toString());
					ps.setString(4, textField_prix.getText().toString());
					ps.setString(5, textArea_desc.getText().toString());
					ps.setString(6, textField_commande.getText().toString());
					ps.setString(7, textField_employer.getText().toString());
					ps.setString(8, textField_categorie.getText().toString());
					ps.setString(9, textField_id.getText().toString());
					ps.execute();
					JOptionPane.showMessageDialog(null, "Le bateau "+textField_id.getText().toString()+" à été mis a jours.");
				}
			} else if (textField_commande.getText().isEmpty()) {
				if (textField_boutique.getText().isEmpty()) {
					System.out.println("2 vide");
					String requete = "Update Bateau set created_at = ?, nomBateau = ?, imageBateau = ?, prix = ?, description = ?, idEmployer = ?, idCategorie = ? WHERE idBateau = ?";
					ps = con.prepareStatement(requete);
					ps.setString(1, textField_createdat.getText().toString());
					ps.setString(2, textField_nom.getText().toString());
					ps.setString(3, textField_image.getText().toString());
					ps.setString(4, textField_prix.getText().toString());
					ps.setString(5, textArea_desc.getText().toString());
					ps.setString(6, textField_employer.getText().toString());
					ps.setString(7, textField_categorie.getText().toString());
					ps.setString(8, textField_id.getText().toString());
					ps.execute();
					JOptionPane.showMessageDialog(null, "Le bateau "+textField_id.getText().toString()+" à été mis a jours.");
				} else {
					System.out.println("boutique rempli");
					String requete = "Update Bateau set created_at = ?, nomBateau = ?, imageBateau = ?, prix = ?, description = ?, idBoutique = ?, idEmployer = ?, idCategorie = ? WHERE idBateau = ?";
					ps = con.prepareStatement(requete);
					ps.setString(1, textField_createdat.getText().toString());
					ps.setString(2, textField_nom.getText().toString());
					ps.setString(3, textField_image.getText().toString());
					ps.setString(4, textField_prix.getText().toString());
					ps.setString(5, textArea_desc.getText().toString());
					ps.setString(6, textField_boutique.getText().toString());
					ps.setString(7, textField_employer.getText().toString());
					ps.setString(8, textField_categorie.getText().toString());
					ps.setString(9, textField_id.getText().toString());
					ps.execute();
					JOptionPane.showMessageDialog(null, "Le bateau "+textField_id.getText().toString()+" à été mis a jours.");
				}
			}

			con.close();
			ps.close();

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 
	 * Categorie
	 * 
	 */

	public List<String> categorieBox() {
		try {
			String requete = "SELECT * FROM `Categorie`";
			ps = con.prepareStatement(requete);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				List_Categorie.add(rs.getInt("idCategorie") + " | " + rs.getString("nomCategorie"));
			}
			con.close();
			ps.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return List_Categorie;
	}

	public void insertCategorie(char operation, String nom) {
		String requete = "INSERT INTO Categorie (`nomCategorie`) VALUES (?)";
		if (operation == 'i') {
			try {
				ps = con.prepareStatement(requete);
				ps.setString(1, nom);

				if (ps.executeUpdate() > 0) {
					JOptionPane.showMessageDialog(null, "Nouveau bateau ajouté");
				}
				con.close();
				ps.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void SuppCategorie(JTextField textField_categorie) {
		String requete = "DELETE FROM Categorie WHERE idCategorie = ?";

		try {
			ps = con.prepareStatement(requete);
			ps.setString(1, textField_categorie.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Bateau Supprimer");
			con.close();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SelectInsertCategorie(JTextField textField_id, JTextField textField_categorie) {
		String requete = "SELECT * FROM Categorie WHERE idCategorie = ?";

		try {
			ps = con.prepareStatement(requete);
			ps.setString(1, textField_id.getText());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nomCategorie = rs.getString("nomCategorie");

				textField_categorie.setText(nomCategorie);
			}
			con.close();
			ps.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * Utilisateurs
	 * 
	 */

	public List<String> utilisateurBox() {
		try {
			String requete = "SELECT * FROM Compte JOIN Client WHERE idCompte = idClient";
			ps = con.prepareStatement(requete);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				List_Utlisateurs.add(rs.getInt("idCompte") + " | " + rs.getString("nom") + " " + rs.getString("prenom"));
			}
			con.close();
			ps.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return List_Categorie;
	}
}
