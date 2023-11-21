package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Login {

	private JFrame frame;
	private JTextField txt_ID;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(142, 133, 134, 21);
		frame.getContentPane().add(passwordField);
		
		txt_ID = new JTextField();
		txt_ID.setBounds(142, 91, 134, 21);
		frame.getContentPane().add(txt_ID);
		txt_ID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("PASSWORD");
		lblNewLabel.setBounds(37, 137, 67, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(63, 94, 19, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btn_login = new JButton("LOGIN");
		btn_login.setBounds(314, 109, 80, 23);
		frame.getContentPane().add(btn_login);
		
		JButton btn_exit = new JButton("종료");
		btn_exit.setBounds(327, 197, 67, 23);
		frame.getContentPane().add(btn_exit);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("기업회원");
		chckbxNewCheckBox.setBounds(152, 160, 115, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("회원 가입");
		btnNewButton.setBounds(25, 197, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ID/PW 찾기");
		btnNewButton_1.setBounds(170, 197, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\this0\\OneDrive\\Desktop\\캡처12.PNG"));
		lblNewLabel_2.setBounds(124, 10, 164, 59);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
