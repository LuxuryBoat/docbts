package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import Contain.ConnexionBDD;
import Contain.RequeteSql;
import Fonction.AES;
import Vue.AjoutEmployer;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Main extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField_user;
	private JPasswordField passwordField;

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("LuxuryBoat Hub");
		frame.setBounds(100, 100, 500, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		URL url = getClass().getResource("/image/favicon.png");
		ImageIcon img = new ImageIcon(url);
		frame.setIconImage(img.getImage());

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblConnection = new JLabel("Connexion");
		lblConnection.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblConnection.setBounds(167, 11, 149, 50);
		panel.add(lblConnection);

		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur : ");
		lblNomDutilisateur.setBounds(10, 114, 122, 14);
		panel.add(lblNomDutilisateur);

		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(10, 139, 122, 14);
		panel.add(lblMotDePasse);

		JLabel lbl_u = new JLabel("Remplir le champs");
		lbl_u.setForeground(new Color(220, 20, 60));
		lbl_u.setBounds(381, 114, 93, 14);
		panel.add(lbl_u);
		lbl_u.setVisible(false);

		JLabel lbl_p = new JLabel("Remplir le champs");
		lbl_p.setForeground(new Color(220, 20, 60));
		lbl_p.setBounds(381, 139, 93, 14);
		panel.add(lbl_p);
		lbl_p.setVisible(false);

		textField_user = new JTextField();
		textField_user.setToolTipText("");
		textField_user.setBounds(167, 111, 149, 20);
		panel.add(textField_user);
		textField_user.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(167, 136, 149, 20);
		panel.add(passwordField);
		
		JButton btnConnection = new JButton("Connexion");
		btnConnection.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {				
				lbl_u.setVisible(false);
				lbl_p.setVisible(false);
				if (textField_user.getText().equals("")) {
					lbl_u.setVisible(true);
				}
				if (String.valueOf(passwordField.getPassword()).equals("")) {
					lbl_p.setVisible(true);
				} else {
					Connection con = ConnexionBDD.Conexion();
					PreparedStatement ps;

					try {
						ps = con.prepareStatement("SELECT * FROM Compte JOIN Employer WHERE nom = ? AND mdp = ? AND idEmployer = idCompte");
						ps.setString(1, textField_user.getText());
						String oldpwd = String.valueOf(passwordField.getPassword());
						String key = "BESTGROUPEPPEGLR"; // 128 bit key
						String initVector = "1234567898765432";
						String password = AES.encrypt(key, initVector, oldpwd).split(":")[0];
						ps.setString(2, password);

						ResultSet rs = ps.executeQuery();
						if (rs.next()) {
							Index idx = new Index();
							idx.setVisible(true);
							idx.pack();
							idx.setBounds(100, 100, 1240, 720);
							idx.setLocationRelativeTo(null);
							idx.lblBonjour.setText("Bonjour " + rs.getString("nom") + " "+ rs.getString("prenom"));
							idx.lblNom.setText("Nom : "+ rs.getString("nom"));
							idx.lblPrenom.setText("Prenom : "+ rs.getString("prenom"));
							idx.lblEmail.setText("Email : "+ rs.getString("email"));
							idx.lblNumero.setText("Numéro : "+ rs.getString("num"));
							idx.lbl_client.setText("Il y-a actuellement "+ RequeteSql.countData("Client")+" clients.");
							idx.lbl_bateau.setText("Il y-a actuellement "+ RequeteSql.countData("Bateau")+" bateaux.");
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Connexion refusée");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		btnConnection.setIconTextGap(8);
		btnConnection.setIcon(new ImageIcon(Index.class.getResource("/Image/enter.png")));
		btnConnection.setBounds(16, 192, 140, 41);
		panel.add(btnConnection);
		
		JLabel lblCopyright = new JLabel("@ Copyright 2020 LuxuryBoat");
		lblCopyright.setIconTextGap(8);
		lblCopyright.setIcon(new ImageIcon(Index.class.getResource("/Image/favicon.png")));
		lblCopyright.setBounds(138, 263, 208, 32);
		panel.add(lblCopyright);
		
		JButton btnInscription = new JButton("Inscription");
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjoutEmployer ae = new AjoutEmployer();
				ae.setVisible(true);
				ae.setTitle("LuxuryBoat Hub");
				ae.pack();
				ae.setBounds(100, 100, 433, 403);
				ae.setLocationRelativeTo(null);
				ae.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				URL url = getClass().getResource("/image/favicon.png");
				ImageIcon img = new ImageIcon(url);
				ae.setIconImage(img.getImage());
			}
		});
		btnInscription.setIconTextGap(8);
		btnInscription.setIcon(new ImageIcon(Index.class.getResource("/Image/user-add.png")));
		btnInscription.setBounds(328, 192, 140, 41);
		panel.add(btnInscription);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnAnnuler.setIconTextGap(8);
		btnAnnuler.setIcon(new ImageIcon(Index.class.getResource("/Image/remove.png")));
		btnAnnuler.setBounds(172, 192, 140, 41);
		panel.add(btnAnnuler);
	}
	
}