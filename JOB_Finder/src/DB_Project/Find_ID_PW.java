package DB_Project;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.JCheckBox;

public class Find_ID_PW extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_id_name;
	private JTextField txt_id_phone;
	private JTextField txt_pw_id;
	private JTextField txt_pw_name;
	private JTextField txt_pw_phone;

	/**
	 * Create the frame.
	 */
	public Find_ID_PW() {
		setTitle("ID/PW 찾기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_id_find = new JLabel("ID 찾기");
		lbl_id_find.setBounds(25, 30, 40, 20);
		contentPane.add(lbl_id_find);
		
		JLabel lbl_id_name = new JLabel("NAME");
		lbl_id_name.setBounds(25, 60, 40, 20);
		contentPane.add(lbl_id_name);
		
		JLabel lbl_id_phone = new JLabel("PHONE_NUMBER");
		lbl_id_phone.setBounds(25, 115, 100, 15);
		contentPane.add(lbl_id_phone);
		
		JLabel lbl_id_result1 = new JLabel("검색 결과 ID : ");
		lbl_id_result1.setBounds(210, 60, 80, 25);
		contentPane.add(lbl_id_result1);
		
		JLabel lbl_id_result2 = new JLabel("\"\"");
		lbl_id_result2.setBounds(290, 60, 150, 25);
		contentPane.add(lbl_id_result2);
		
		txt_id_name = new JTextField();
		txt_id_name.setBounds(25, 80, 150, 25);
		contentPane.add(txt_id_name);
		txt_id_name.setColumns(10);
		
		txt_id_phone = new JTextField();
		txt_id_phone.setColumns(10);
		txt_id_phone.setBounds(25, 130, 150, 25);
		contentPane.add(txt_id_phone);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(15, 190, 405, 5);
		contentPane.add(separator);
		
		JButton btn_id_search = new JButton("검색");
		btn_id_search.setBounds(230, 130, 75, 25);

		JCheckBox chk_business_id = new JCheckBox("기업회원");
		chk_business_id.setBounds(230, 100, 115, 20);
		contentPane.add(chk_business_id);
		
		JCheckBox chk_business_pw = new JCheckBox("기업회원");
		chk_business_pw.setBounds(230, 295, 115, 20);
		contentPane.add(chk_business_pw);
	
	
		// 기능구현1 = 아이디 검색
		// PreparedStatement로
		
		btn_id_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					Main.DBConnection();
					
					String sql = "SELECT 회원ID FROM ";
					if(chk_business_id.isSelected()) {
						sql += "기업회원 ";
					}
					else {
						sql += "개인회원 ";
					}
					sql += "WHERE 이름 = ? AND 휴대폰 = ?";
					
					Main.pstmt = Main.con.prepareStatement(sql);
					Main.pstmt.setString(1, txt_id_name.getText());
					Main.pstmt.setString(2, txt_id_phone.getText());
					
					Main.rs = Main.pstmt.executeQuery(); // 쿼리 실행 및 결과 조회
					if(Main.rs.next()) { // 결과가 있을 경우
						String memberID = Main.rs.getString("회원ID");
						lbl_id_result2.setText(memberID);						
					}
					else { // 결과가 없는 경우
						JOptionPane.showMessageDialog(null, "해당 정보로 ID를 찾을 수 없습니다.", "ID 찾기 실패", JOptionPane.ERROR_MESSAGE);
					}
		
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,  ex.getMessage(), "ID 찾기 실패", JOptionPane.ERROR_MESSAGE);
				}
				finally {
					Main.DBClose();
				}
			}
		});
		
		contentPane.add(btn_id_search);
		
		JLabel lbl_pw_find_1 = new JLabel("PW 찾기");
		lbl_pw_find_1.setBounds(25, 220, 50, 20);
		contentPane.add(lbl_pw_find_1);
		
		JLabel lbl_pw_id = new JLabel("ID");
		lbl_pw_id.setBounds(25, 250, 15, 20);
		contentPane.add(lbl_pw_id);
		
		JLabel lbl_pw_name = new JLabel("NAME");
		lbl_pw_name.setBounds(25, 305, 40, 20);
		contentPane.add(lbl_pw_name);
		
		JLabel lbl_pw_phone = new JLabel("PHONE_NUMBER");
		lbl_pw_phone.setBounds(25, 360, 100, 20);
		contentPane.add(lbl_pw_phone);
		
		JLabel lbl_pw_result1 = new JLabel("검색 결과 PW : ");
		lbl_pw_result1.setBounds(210, 250, 90, 25);
		contentPane.add(lbl_pw_result1);
		
		JLabel lbl_pw_result2 = new JLabel("\"\"");
		lbl_pw_result2.setBounds(300, 250, 130, 25);
		contentPane.add(lbl_pw_result2);
		
		txt_pw_id = new JTextField();
		txt_pw_id.setColumns(10);
		txt_pw_id.setBounds(25, 270, 150, 25);
		contentPane.add(txt_pw_id);
		
		txt_pw_name = new JTextField();
		txt_pw_name.setColumns(10);
		txt_pw_name.setBounds(25, 325, 150, 25);
		contentPane.add(txt_pw_name);
		
		txt_pw_phone = new JTextField();
		txt_pw_phone.setColumns(10);
		txt_pw_phone.setBounds(25, 380, 150, 25);
		contentPane.add(txt_pw_phone);
		
		JButton btn_pw_search = new JButton("검색");
		btn_pw_search.setBounds(230, 325, 75, 25);
		
		// 기능구현2 = 비밀번호 검색
		// CallableStatement로
		
		btn_pw_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					
					
		
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,  ex.getMessage(), "비밀번호 찾기 실패", JOptionPane.ERROR_MESSAGE);
				}
				finally {
					Main.DBClose();
				}
			}
		});
		
		contentPane.add(btn_pw_search);
		
		JButton btn_exit = new JButton("닫기");
		btn_exit.setBounds(360, 396, 60, 25);
		contentPane.add(btn_exit);
	}
}
