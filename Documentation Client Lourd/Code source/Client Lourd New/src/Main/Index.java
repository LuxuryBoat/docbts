package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Vue.AjoutBateau;
import Vue.AjoutCategorie;
import Vue.AjoutEmployer;
import Vue.GestionEmployer;
import Vue.GestionUtilisateurs;
import Vue.ModifBateau;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class Index extends JFrame {

	private JPanel contentPane;
	static JLabel lblBonjour;
	static JLabel lbl_client;
	static JLabel lbl_bateau;
	static JLabel lblNom;
	static JLabel lblPrenom;
	static JLabel lblEmail;
	static JLabel lblNumero;
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1240, 720);
		contentPane = new JPanel();
		setTitle("LuxuryBoat Hub");
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		URL url = getClass().getResource("/image/favicon.png");
		ImageIcon img = new ImageIcon(url);
		setIconImage(img.getImage());

		lblBonjour = new JLabel("Bonjour ***");
		lblBonjour.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblBonjour.setBounds(516, 11, 430, 36);
		contentPane.add(lblBonjour);

		lbl_client = new JLabel("Il y-a actuellement *** clients.");
		lbl_client.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_client.setBounds(62, 80, 449, 25);
		contentPane.add(lbl_client);

		lbl_bateau = new JLabel("Il y-a actuellement *** bateaux.");
		lbl_bateau.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_bateau.setBounds(62, 130, 383, 25);
		contentPane.add(lbl_bateau);

		JButton btnNewButton = new JButton("Ajout employer");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setIconTextGap(8);
		btnNewButton.setIcon(new ImageIcon(Index.class.getResource("/Image/user-add.png")));
		btnNewButton.setBounds(62, 320, 191, 62);
		contentPane.add(btnNewButton);

		JButton btnAjoutBateau = new JButton("Ajout Bateau");
		btnAjoutBateau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjoutBateau ab = new AjoutBateau();
				ab.setVisible(true);
				ab.setTitle("LuxuryBoat Hub");
				ab.pack();
				ab.setBounds(100, 100, 399, 452);
				ab.setLocationRelativeTo(null);
				ab.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				URL url = getClass().getResource("/image/favicon.png");
				ImageIcon img = new ImageIcon(url);
				ab.setIconImage(img.getImage());
			}
		});
		btnAjoutBateau.setIconTextGap(8);
		btnAjoutBateau.setIcon(new ImageIcon(Index.class.getResource("/Image/boat.png")));
		btnAjoutBateau.setBounds(62, 220, 191, 62);
		contentPane.add(btnAjoutBateau);

		JButton btnModificationBateau = new JButton("Modification bateau");
		btnModificationBateau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifBateau mb = new ModifBateau();
				mb.setVisible(true);
				mb.setTitle("LuxuryBoat Hub");
				mb.pack();
				mb.setBounds(100, 100, 1250, 500);
				mb.setLocationRelativeTo(null);
				mb.listBateau();
				mb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				URL url = getClass().getResource("/image/favicon.png");
				ImageIcon img = new ImageIcon(url);
				mb.setIconImage(img.getImage());
			}
		});
		btnModificationBateau.setIconTextGap(8);
		btnModificationBateau.setIcon(new ImageIcon(Index.class.getResource("/Image/clipboard.png")));
		btnModificationBateau.setBounds(379, 220, 191, 62);
		contentPane.add(btnModificationBateau);

		JButton btnGestionUtilisateurs = new JButton("Gestion Utilisateurs");
		btnGestionUtilisateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionUtilisateurs gu = new GestionUtilisateurs();
				gu.setVisible(true);
				gu.setTitle("LuxuryBoat Hub");
				gu.pack();
				gu.setBounds(100, 100, 780, 449);
				gu.setLocationRelativeTo(null);
				gu.listeUtilisateur();
				gu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				URL url = getClass().getResource("/image/favicon.png");
				ImageIcon img = new ImageIcon(url);
				gu.setIconImage(img.getImage());
			}
		});
		btnGestionUtilisateurs.setIconTextGap(8);
		btnGestionUtilisateurs.setIcon(new ImageIcon(Index.class.getResource("/Image/list.png")));
		btnGestionUtilisateurs.setBounds(379, 420, 191, 62);
		contentPane.add(btnGestionUtilisateurs);

		JButton btnAjoutCategorie = new JButton("Gestion Categorie");
		btnAjoutCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjoutCategorie ac = new AjoutCategorie();
				ac.setVisible(true);
				ac.setTitle("LuxuryBoat Hub");
				ac.pack();
				ac.setBounds(100, 100, 884, 383);
				ac.setLocationRelativeTo(null);
				ac.listeCategorie();
				ac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				URL url = getClass().getResource("/image/favicon.png");
				ImageIcon img = new ImageIcon(url);
				ac.setIconImage(img.getImage());
			}
		});
		btnAjoutCategorie.setIconTextGap(8);
		btnAjoutCategorie.setIcon(new ImageIcon(Index.class.getResource("/Image/list.png")));
		btnAjoutCategorie.setBounds(62, 420, 191, 62);
		contentPane.add(btnAjoutCategorie);

		JButton btnGestionEmployes = new JButton("Gestion Employ\u00E9es");
		btnGestionEmployes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GestionEmployer ge = new GestionEmployer();
				ge.setVisible(true);
				ge.setTitle("LuxuryBoat Hub");
				ge.pack();
				ge.setBounds(100, 100, 780, 449);
				ge.setLocationRelativeTo(null);
				ge.listeEmployer();
				ge.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				URL url = getClass().getResource("/image/favicon.png");
				ImageIcon img = new ImageIcon(url);
				ge.setIconImage(img.getImage());
			}
		});
		btnGestionEmployes.setIconTextGap(8);
		btnGestionEmployes.setIcon(new ImageIcon(Index.class.getResource("/Image/group-of-men.png")));
		btnGestionEmployes.setBounds(379, 320, 191, 62);
		contentPane.add(btnGestionEmployes);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModifier.setIconTextGap(8);
		btnModifier.setIcon(new ImageIcon(Index.class.getResource("/Image/pay-card.png")));
		btnModifier.setBounds(845, 420, 191, 62);
		contentPane.add(btnModifier);
		
		JLabel lblMonProfile = new JLabel("Profil");
		lblMonProfile.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMonProfile.setBounds(900, 170, 115, 25);
		contentPane.add(lblMonProfile);
		
		JButton btnPageDacceuil = new JButton("Page d'accueil");
		btnPageDacceuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("http://luxuryboat.fr/").toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPageDacceuil.setIconTextGap(8);
		btnPageDacceuil.setIcon(new ImageIcon(Index.class.getResource("/Image/seo-and-web-home.png")));
		btnPageDacceuil.setBounds(379, 552, 191, 62);
		contentPane.add(btnPageDacceuil);
		
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("http://luxuryboat.fr/aconnexion").toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDashboard.setIconTextGap(8);
		btnDashboard.setIcon(new ImageIcon(Index.class.getResource("/Image/seo-and-web-dashboard.png")));
		btnDashboard.setBounds(632, 552, 191, 62);
		contentPane.add(btnDashboard);
		
		lblNom = new JLabel("Nom : ***");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNom.setBounds(791, 220, 303, 25);
		contentPane.add(lblNom);
		
		lblPrenom = new JLabel("Prenom : ***");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrenom.setBounds(791, 257, 303, 25);
		contentPane.add(lblPrenom);
		
		lblEmail = new JLabel("Email : ***");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(791, 292, 303, 25);
		contentPane.add(lblEmail);
		
		lblNumero = new JLabel("Num\u00E9ro : ***");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumero.setBounds(791, 328, 303, 25);
		contentPane.add(lblNumero);
		
		JLabel lblCopyright = new JLabel("@ Copyright 2020 LuxuryBoat");
		lblCopyright.setIconTextGap(8);
		lblCopyright.setIcon(new ImageIcon(Index.class.getResource("/Image/favicon.png")));
		lblCopyright.setBounds(519, 638, 274, 32);
		contentPane.add(lblCopyright);
		
		JButton btnDeconexion = new JButton("D\u00E9connexion");
		btnDeconexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnDeconexion.setIconTextGap(8);
		btnDeconexion.setIcon(new ImageIcon(Index.class.getResource("/Image/remove.png")));
		btnDeconexion.setBounds(1023, 552, 191, 62);
		contentPane.add(btnDeconexion);
	}

	public static JLabel lblBonjour() {
		return lblBonjour;
	}
	public static JLabel lbl_client() {
		return lbl_client;
	}
	public static JLabel lbl_bateau() {
		return lbl_bateau;
	}
	public static JLabel lblNom() {
		return lblNom;
	}
	public static JLabel lblPrenom() {
		return lblPrenom;
	}
	public static JLabel lblEmail() {
		return lblEmail;
	}
	public static JLabel lblNumero() {
		return lblNumero;
	}
}
