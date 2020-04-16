package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Contain.RequeteSql;
import Fonction.AES;
import javax.swing.ImageIcon;
@SuppressWarnings("serial")
public class AjoutEmployer extends JFrame {

	private JPanel contentPane;
	private JTextField textField_nom;
	private JTextField textField_prenom;
	private JTextField textField_email;
	private JTextField textField_num;
	private JPasswordField passwordField;
	private JTextField textField_date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutEmployer frame = new AjoutEmployer();
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
	public AjoutEmployer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lbl_titre = new JLabel("Ajout employé");
		lbl_titre.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbl_titre.setBounds(113, 11, 181, 31);
		panel.add(lbl_titre);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 103, 192, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Prenom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 128, 192, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 153, 192, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Num\u00E9ro de t\u00E9l\u00E9phone");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(10, 178, 192, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Mot de passe");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(10, 203, 192, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Date de naissance");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(10, 228, 192, 14);
		panel.add(lblNewLabel_5);

		textField_nom = new JTextField();
		textField_nom.setBounds(212, 102, 145, 20);
		panel.add(textField_nom);
		textField_nom.setColumns(10);

		textField_prenom = new JTextField();
		textField_prenom.setBounds(212, 127, 145, 20);
		panel.add(textField_prenom);
		textField_prenom.setColumns(10);

		textField_email = new JTextField();
		textField_email.setBounds(212, 152, 145, 20);
		panel.add(textField_email);
		textField_email.setColumns(10);

		textField_num = new JTextField();
		textField_num.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (!Character.isDigit(arg0.getKeyChar())) {
					arg0.consume();
				}
			}
		});
		textField_num.setBounds(212, 177, 145, 20);
		panel.add(textField_num);
		textField_num.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(212, 202, 145, 20);
		panel.add(passwordField);

		textField_date = new JTextField();
		textField_date.setToolTipText("AAAA-MM-DD");
		textField_date.setBounds(212, 227, 145, 20);
		panel.add(textField_date);
		textField_date.setColumns(10);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setIconTextGap(8);
		btnAjouter.setIcon(new ImageIcon(AjoutEmployer.class.getResource("/Image/checked.png")));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = textField_nom.getText();
				String prenom = textField_prenom.getText();
				String email = textField_email.getText();
				String num = textField_num.getText();
				String oldpwd = String.valueOf(passwordField.getPassword());
				String key = "BESTGROUPEPPEGLR"; // 128 bit key
				String initVector = "1234567898765432";
				String password = AES.encrypt(key, initVector, oldpwd).split(":")[0];
				String date = textField_date.getText();
				RequeteSql rq = new RequeteSql();
				rq.insertCompte('i', nom, prenom, email, num, password, date);
				rq.selectEmployer(nom, prenom, email, num);
				dispose();
			}
		});
		btnAjouter.setBounds(231, 276, 117, 38);
		panel.add(btnAjouter);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setIconTextGap(8);
		btnAnnuler.setIcon(new ImageIcon(AjoutEmployer.class.getResource("/Image/remove.png")));
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnuler.setBounds(57, 276, 117, 38);
		panel.add(btnAnnuler);
	}

}
