package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.event.AncestorListener;

import Contain.RequeteSql;

import javax.swing.event.AncestorEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class AjoutBateau extends JFrame {

	private JPanel contentPane;
	private JTextField textField_nom;
	private JTextField textField_prix;
	private JTextField textField_image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutBateau frame = new AjoutBateau();
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
	public AjoutBateau() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lbl_titre = new JLabel("Ajout bateau");
		lbl_titre.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl_titre.setBounds(114, 11, 145, 31);
		panel.add(lbl_titre);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 103, 192, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Image");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 128, 192, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Prix");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 153, 192, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Description");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(10, 178, 192, 14);
		panel.add(lblNewLabel_3);

		JLabel lblCategorie = new JLabel("Categorie");
		lblCategorie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategorie.setBounds(10, 256, 192, 20);
		panel.add(lblCategorie);

		JLabel lblEmployer = new JLabel("Employer");
		lblEmployer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmployer.setBounds(10, 287, 192, 20);
		panel.add(lblEmployer);

		textField_nom = new JTextField();
		textField_nom.setBounds(212, 102, 145, 20);
		panel.add(textField_nom);
		textField_nom.setColumns(10);

		textField_prix = new JTextField();
		textField_prix.setBounds(212, 152, 145, 20);
		panel.add(textField_prix);
		textField_prix.setColumns(10);

		textField_image = new JTextField();
		textField_image.setColumns(10);
		textField_image.setBounds(212, 127, 145, 20);
		panel.add(textField_image);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(212, 175, 145, 72);
		panel.add(textArea);

		JComboBox<String> comboBox_Categorie = new JComboBox<String>();
		comboBox_Categorie.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				RequeteSql rq = new RequeteSql();
				List<String> List_Categorie = rq.categorieBox();
				for (String x : List_Categorie) {
					comboBox_Categorie.addItem(x);
				}
			}

			public void ancestorMoved(AncestorEvent arg0) {
			}

			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		comboBox_Categorie.setBounds(212, 258, 145, 20);
		panel.add(comboBox_Categorie);

		JComboBox<String> comboBox_Employer = new JComboBox<String>();
		comboBox_Employer.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				RequeteSql rq = new RequeteSql();
				List<String> List_Employer = rq.employerBox();
				for (String x : List_Employer) {
					comboBox_Employer.addItem(x);
				}
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		comboBox_Employer.setBounds(212, 289, 145, 20);
		panel.add(comboBox_Employer);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setIconTextGap(8);
		btnAnnuler.setIcon(new ImageIcon(AjoutBateau.class.getResource("/Image/remove.png")));
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnuler.setBounds(46, 354, 117, 38);
		panel.add(btnAnnuler);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setIconTextGap(8);
		btnAjouter.setIcon(new ImageIcon(AjoutBateau.class.getResource("/Image/checked.png")));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = textField_nom.getText();
				String image = textField_image.getText();
				String prix = textField_prix.getText();
				String description = textArea.getText();
				String chaine = comboBox_Employer.getSelectedItem().toString();
				String idChaine = chaine.split(" | ")[0];
				int idEmployer = Integer.parseInt(idChaine);
				chaine = comboBox_Categorie.getSelectedItem().toString();
				idChaine = chaine.split(" | ")[0];
				int idCategorie = Integer.parseInt(idChaine);

				RequeteSql rq = new RequeteSql();
				rq.insertBateau('i', nom, image, prix, description, idEmployer, idCategorie);
			}
		});
		btnAjouter.setBounds(209, 354, 117, 38);
		panel.add(btnAjouter);
	}

}
