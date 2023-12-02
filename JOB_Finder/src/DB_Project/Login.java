package DB_Project;

import javax.swing.JFrame;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class Login {

	public JFrame frame;
	private JTextField txt_ID;
	private JPasswordField txt_password;

	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		txt_password = new JPasswordField();
		txt_password.setBounds(142, 133, 134, 21);
		frame.getContentPane().add(txt_password);
		
		txt_ID = new JTextField();
		txt_ID.setBounds(142, 91, 134, 21);
		frame.getContentPane().add(txt_ID);
		txt_ID.setColumns(10);
		
		JLabel label_password = new JLabel("PASSWORD");
		label_password.setBounds(37, 137, 67, 15);
		frame.getContentPane().add(label_password);
		
		JLabel label_id = new JLabel("ID");
		label_id.setBounds(63, 94, 19, 15);
		frame.getContentPane().add(label_id);
		
		JButton btn_exit = new JButton("종료");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_exit.setBounds(327, 197, 67, 23);
		frame.getContentPane().add(btn_exit);
		
		JCheckBox chk_business = new JCheckBox("기업회원");
		chk_business.setBounds(152, 160, 115, 23);
		frame.getContentPane().add(chk_business);
		
		JButton btn_login = new JButton("LOGIN");
		btn_login.setBounds(314, 109, 80, 23);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					PreparedStatement pstmt = null;
					String sql = "SELECT * FROM ";
					if(chk_business.isSelected()) {
						sql += "기업회원 ";
						Main.mode = "기업";
					}
					else {
						sql += "개인회원 ";
						Main.mode = "개인";
					}
					sql += "WHERE 회원ID = ? AND 비밀번호 = ?";
					pstmt = Main.con.prepareStatement(sql);
					pstmt.setString(1, txt_ID.getText());
					pstmt.setString(2, String.valueOf(txt_password.getPassword()));
					Main.rs = pstmt.executeQuery();
					if(Main.rs.next()) {
						Mainpage window = new Mainpage();
						window.frame.setVisible(true);
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "ID/PW가 올바르지 않거나 존재하지 않는 계정입니다.","로그인 실패", JOptionPane.ERROR_MESSAGE);
					}
						
					Main.rs.close();
					pstmt.close();
					Main.DBClose();
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),"로그인 실패", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		frame.getContentPane().add(btn_login);
		
		JButton btn_register = new JButton("회원 가입");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.dispose();
				Register window = new Register();
				window.frame.setVisible(true);
			}
		});
		btn_register.setBounds(25, 197, 97, 23);
		frame.getContentPane().add(btn_register);
		
		JButton btn_find = new JButton("ID/PW 찾기");
		btn_find.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//frame.dispose();
				Find_ID_PW window = new Find_ID_PW();
				window.setVisible(true);
			}
		});
		btn_find.setBounds(170, 197, 97, 23);
		frame.getContentPane().add(btn_find);
		
		JLabel lbl_logo = new JLabel("");
		lbl_logo.setIcon(new ImageIcon("C:\\Users\\this0\\OneDrive\\Desktop\\캡처12.PNG"));
		lbl_logo.setBounds(124, 10, 164, 59);
		frame.getContentPane().add(lbl_logo);
	}
}
