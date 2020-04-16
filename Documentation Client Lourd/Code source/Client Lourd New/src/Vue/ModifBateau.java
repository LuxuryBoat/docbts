package Vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Contain.ConnexionBDD;
import Contain.RequeteSql;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ModifBateau extends JFrame {

	private JPanel contentPane;
	private JTextField textField_nom;
	private JTextField textField_createdat = new JTextField();
	private JTextField textField_prix;
	private JTextField textField_image;
	private JTextField textField_id;
	private JTextArea textArea_desc;
	private JTable table;
	private JTextField textField_commande;
	private JTextField textField_boutique;
	private JTextField textField_employer;
	private JTextField textField_categorie;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifBateau frame = new ModifBateau();
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
	public ModifBateau() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lbl_titre = new JLabel("Modification bateau");
		lbl_titre.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl_titre.setBounds(501, 11, 221, 27);
		panel.add(lbl_titre);

		JLabel lbl_Id = new JLabel("Id");
		lbl_Id.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_Id.setBounds(10, 78, 192, 14);
		panel.add(lbl_Id);

		JLabel lbl_nom = new JLabel("Nom");
		lbl_nom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_nom.setBounds(10, 103, 192, 14);
		panel.add(lbl_nom);

		JLabel lbl_image = new JLabel("Image");
		lbl_image.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_image.setBounds(10, 128, 192, 14);
		panel.add(lbl_image);

		JLabel lbl_prix = new JLabel("Prix");
		lbl_prix.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_prix.setBounds(10, 153, 192, 14);
		panel.add(lbl_prix);

		JLabel lbl_description = new JLabel("Description");
		lbl_description.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_description.setBounds(10, 178, 192, 14);
		panel.add(lbl_description);

		JLabel lbl_commande = new JLabel("idCommande");
		lbl_commande.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_commande.setBounds(10, 256, 192, 20);
		panel.add(lbl_commande);

		JLabel lbl_boutique = new JLabel("idBoutique");
		lbl_boutique.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_boutique.setBounds(10, 287, 192, 20);
		panel.add(lbl_boutique);

		JLabel lblIdemployer = new JLabel("idEmployer");
		lblIdemployer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIdemployer.setBounds(10, 318, 192, 14);
		panel.add(lblIdemployer);

		JLabel lblIdcategorie = new JLabel("idCategorie");
		lblIdcategorie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIdcategorie.setBounds(10, 349, 192, 14);
		panel.add(lblIdcategorie);

		textField_id = new JTextField();
		textField_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				RequeteSql rq = new RequeteSql();
				rq.SelectInsertBateau(textField_nom, textField_prix, textField_image, textField_id, textArea_desc,
						textField_commande, textField_boutique, textField_employer, textField_categorie,
						textField_createdat);
			}
		});
		textField_id.setColumns(10);
		textField_id.setBounds(212, 77, 145, 20);
		panel.add(textField_id);

		textField_nom = new JTextField();
		textField_nom.setBounds(212, 102, 145, 20);
		panel.add(textField_nom);
		textField_nom.setColumns(10);

		textField_createdat.setBounds(1, 1, 1, 1);
		panel.add(textField_createdat);
		textField_createdat.setColumns(10);

		textField_prix = new JTextField();
		textField_prix.setBounds(212, 152, 145, 20);
		panel.add(textField_prix);
		textField_prix.setColumns(10);

		textField_image = new JTextField();
		textField_image.setColumns(10);
		textField_image.setBounds(212, 127, 145, 20);
		panel.add(textField_image);

		textArea_desc = new JTextArea();
		textArea_desc.setBounds(212, 175, 145, 72);
		panel.add(textArea_desc);

		textField_commande = new JTextField();
		textField_commande.setColumns(10);
		textField_commande.setBounds(212, 258, 145, 20);
		panel.add(textField_commande);

		textField_boutique = new JTextField();
		textField_boutique.setColumns(10);
		textField_boutique.setBounds(212, 289, 145, 20);
		panel.add(textField_boutique);

		textField_employer = new JTextField();
		textField_employer.setColumns(10);
		textField_employer.setBounds(212, 317, 145, 20);
		panel.add(textField_employer);

		textField_categorie = new JTextField();
		textField_categorie.setColumns(10);
		textField_categorie.setBounds(212, 348, 145, 20);
		panel.add(textField_categorie);

		JButton btn_annuler = new JButton("Annuler");
		btn_annuler.setIconTextGap(8);
		btn_annuler.setIcon(new ImageIcon(ModifBateau.class.getResource("/Image/remove.png")));
		btn_annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btn_annuler.setBounds(57, 393, 117, 38);
		panel.add(btn_annuler);

		JButton btn_modifier = new JButton("Modifier");
		btn_modifier.setIconTextGap(8);
		btn_modifier.setIcon(new ImageIcon(ModifBateau.class.getResource("/Image/settings.png")));
		btn_modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RequeteSql rq = new RequeteSql();
				rq.UpdateBateau(textField_nom, textField_prix, textField_image, textField_id, textArea_desc,
						textField_commande, textField_boutique, textField_employer, textField_categorie,
						textField_createdat);
				textField_id.setText("");
				textField_nom.setText("");
				textField_createdat.setText("");
				textField_prix.setText("");
				textField_image.setText("");
				textArea_desc.setText("");
				textField_commande.setText("");
				textField_boutique.setText("");
				textField_employer.setText("");
				textField_categorie.setText("");
				listBateau();
			}
		});
		btn_modifier.setBounds(231, 393, 117, 38);
		panel.add(btn_modifier);

		JButton btn_supprimer = new JButton("Supprimer");
		btn_supprimer.setIconTextGap(8);
		btn_supprimer.setIcon(new ImageIcon(ModifBateau.class.getResource("/Image/unchecked.png")));
		btn_supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RequeteSql rq = new RequeteSql();
				rq.SuppBateau(textField_id);
				textField_id.setText("");
				textField_nom.setText("");
				textField_createdat.setText("");
				textField_prix.setText("");
				textField_image.setText("");
				textArea_desc.setText("");
				textField_commande.setText("");
				textField_boutique.setText("");
				textField_employer.setText("");
				textField_categorie.setText("");
				listBateau();
			}
		});
		btn_supprimer.setBounds(406, 393, 145, 38);
		panel.add(btn_supprimer);

		JButton btn_Actualiser = new JButton("Actualiser");
		btn_Actualiser.setIconTextGap(8);
		btn_Actualiser.setIcon(new ImageIcon(ModifBateau.class.getResource("/Image/refreshing.png")));
		btn_Actualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listBateau();
			}
		});
		btn_Actualiser.setBounds(1078, 393, 136, 38);
		panel.add(btn_Actualiser);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(410, 78, 804, 285);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public void listBateau() {
		Connection con = ConnexionBDD.Conexion();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT * FROM Bateau");
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
