package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Contain.ConnexionBDD;
import Contain.RequeteSql;
import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class GestionUtilisateurs extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionUtilisateurs frame = new GestionUtilisateurs();
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
	public GestionUtilisateurs() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl_titre = new JLabel("Gestion des utilisateurs");
		lbl_titre.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl_titre.setBounds(245, 11, 264, 27);
		panel.add(lbl_titre);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(93, 49, 567, 285);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JComboBox<String> comboBox_utilisateur = new JComboBox<String>();
		comboBox_utilisateur.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				RequeteSql rq = new RequeteSql();
				List<String> List_Utlisateurs = rq.utilisateurBox();
				for (String x : List_Utlisateurs) {
					comboBox_utilisateur.addItem(x);
				}
			}

			public void ancestorMoved(AncestorEvent arg0) {
			}

			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		comboBox_utilisateur.setBounds(209, 354, 135, 20);
		panel.add(comboBox_utilisateur);

		JButton btnNewButton = new JButton("Actualiser");
		btnNewButton.setIcon(new ImageIcon(GestionUtilisateurs.class.getResource("/Image/refreshing.png")));
		btnNewButton.setIconTextGap(8);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listeUtilisateur();
			}
		});
		btnNewButton.setBounds(571, 345, 135, 38);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setIcon(new ImageIcon(GestionUtilisateurs.class.getResource("/Image/unchecked.png")));
		btnNewButton_1.setIconTextGap(8);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(390, 345, 135, 38);
		panel.add(btnNewButton_1);
		
		JButton button = new JButton("Annuler");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setIcon(new ImageIcon(GestionUtilisateurs.class.getResource("/Image/remove.png")));
		button.setIconTextGap(8);
		button.setBounds(46, 345, 117, 38);
		panel.add(button);
	}

	public void listeUtilisateur() {
		Connection con = ConnexionBDD.Conexion();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"SELECT idCompte, nom, prenom, email, num, dateNaissance FROM Compte JOIN Client WHERE idCompte = idClient");
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
