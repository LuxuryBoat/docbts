package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Contain.ConnexionBDD;
import Contain.RequeteSql;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class AjoutCategorie extends JFrame {

	private JPanel contentPane;
	private JTextField textField_categorie;
	private JTable table;
	private JTextField textField_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutCategorie frame = new AjoutCategorie();
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
	public AjoutCategorie() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lbl_titre = new JLabel("Gestion cat\u00E9gorie");
		lbl_titre.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl_titre.setBounds(331, 11, 195, 27);
		panel.add(lbl_titre);

		JLabel lbl_categorie = new JLabel("Nom categorie");
		lbl_categorie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_categorie.setBounds(10, 159, 135, 14);
		panel.add(lbl_categorie);

		textField_categorie = new JTextField();
		textField_categorie.setBounds(155, 158, 145, 20);
		panel.add(textField_categorie);
		textField_categorie.setColumns(10);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setIconTextGap(8);
		btnAjouter.setIcon(new ImageIcon(AjoutCategorie.class.getResource("/Image/checked.png")));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = textField_categorie.getText();

				RequeteSql rq = new RequeteSql();
				rq.insertCategorie('i', nom);
			}
		});
		btnAjouter.setBounds(137, 285, 117, 38);
		panel.add(btnAjouter);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setIconTextGap(8);
		btnAnnuler.setIcon(new ImageIcon(AjoutCategorie.class.getResource("/Image/remove.png")));
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnuler.setBounds(10, 285, 117, 38);
		panel.add(btnAnnuler);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 64, 507, 212);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setIconTextGap(8);
		btnSupprimer.setIcon(new ImageIcon(AjoutCategorie.class.getResource("/Image/unchecked.png")));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequeteSql rq = new RequeteSql();
				rq.SuppCategorie(textField_categorie);
				textField_categorie.setText("");
				textField_id.setText("");
				listeCategorie();
			}
		});
		btnSupprimer.setBounds(718, 285, 130, 38);
		panel.add(btnSupprimer);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.setIconTextGap(8);
		btnActualiser.setIcon(new ImageIcon(AjoutCategorie.class.getResource("/Image/refreshing.png")));
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listeCategorie();
			}
		});
		btnActualiser.setBounds(718, 10, 130, 38);
		panel.add(btnActualiser);
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(10, 134, 135, 14);
		panel.add(lblId);
		
		textField_id = new JTextField();
		textField_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				RequeteSql rq = new RequeteSql();
				rq.SelectInsertCategorie(textField_id, textField_categorie);
			}
		});
		textField_id.setColumns(10);
		textField_id.setBounds(155, 127, 145, 20);
		panel.add(textField_id);
	}
	
	public void listeCategorie() {
		Connection con = ConnexionBDD.Conexion();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"SELECT * FROM Categorie");
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
