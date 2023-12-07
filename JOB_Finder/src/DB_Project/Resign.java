package DB_Project;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.JFormattedTextField;

public class Resign {

	public JFrame frame;
	private JTextField txt_id;

	public Resign() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("회원 탈퇴");
		frame.setBounds(100, 100, 520, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lbl_ID = new JLabel("ID");
		lbl_ID.setBounds(12, 10, 57, 15);
		frame.getContentPane().add(lbl_ID);
		
		txt_id = new JTextField();
		txt_id.setEditable(false);
		txt_id.setText(Main.ID);
		txt_id.setBounds(12, 35, 240, 21);
		frame.getContentPane().add(txt_id);
		txt_id.setColumns(10);
		
		JLabel lbl_phone = new JLabel("PHONE_NUMBER");
		lbl_phone.setBounds(12, 66, 116, 15);
		frame.getContentPane().add(lbl_phone);
		
		JCheckBox chk1 = new JCheckBox("(필수) 탈퇴 후, JOB Finder 서비스의 이용이 불가한 것에 동의합니다.");
		chk1.setBounds(12, 161, 480, 23);
		frame.getContentPane().add(chk1);
		
		JCheckBox chk2 = new JCheckBox("(선택) 탈퇴 후, 회원님의 동일한 ID로 재생성하는 것에 동의합니다.");
		chk2.setBounds(12, 203, 480, 23);
		frame.getContentPane().add(chk2);
		
		JButton btn_resign = new JButton("회원 탈퇴");
		btn_resign.setFont(new Font("굴림", Font.BOLD, 18));
		btn_resign.setBounds(12, 294, 116, 57);
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("###-####-####");
			formatter.setPlaceholderCharacter('_');
		}
		catch(Exception ex) {}
		JFormattedTextField txt_phonenumber = new JFormattedTextField(formatter);
		txt_phonenumber.setColumns(15);
		txt_phonenumber.setBounds(12, 91, 240, 21);
		frame.getContentPane().add(txt_phonenumber);
		
		// 기능 구현 : 회원 탈퇴
		
		btn_resign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					Main.con.setAutoCommit(false);
					
					String tablename, table2;
					if(Main.mode == "개인")
						tablename = "개인회원";
					else
						tablename = "기업회원";
					
					// DELETE
					String sql = "DELETE FROM " + tablename + " WHERE 회원ID = ? AND 휴대폰 = ?";
					Main.pstmt = Main.con.prepareStatement(sql);
					Main.pstmt.setString(1, txt_id.getText());
					Main.pstmt.setString(2, txt_phonenumber.getText());
					Main.rs = Main.pstmt.executeQuery();
					
					if(!chk2.isSelected()) { 
						// 탈퇴 후에, 동일ID로 재생성할 수 없게
						if(Main.mode == "개인")
							table2 = "개인_회원_정보_변경";
						else
							table2 = "기업_회원_정보_변경";
						
						String sql2 = "INSERT INTO " + table2 + " VALUES (?, ?, ?, ?)";
						
						Main.pstmt = Main.con.prepareStatement(sql2);
						Main.pstmt.setString(1, txt_id.getText());
						Main.pstmt.setString(2, "탈퇴");
						Main.pstmt.setNull(3, Types.NVARCHAR);
						Main.pstmt.setNull(4, Types.NVARCHAR);
						Main.pstmt.executeUpdate();
					}
					Main.con.commit();
					
					if(Main.rs.next()) {
						JOptionPane.showMessageDialog(null, "회원탈퇴가 완료되었습니다.", "탈퇴 성공", JOptionPane.INFORMATION_MESSAGE);
						
						// 모든 창 종료하고 로그인 화면으로 돌아가기
						
						for(Window window : Window.getWindows()) {
							window.dispose();
						}
						
						Login loginScreen = new Login();
						loginScreen.frame.setVisible(true);
						
					} else {
						JOptionPane.showMessageDialog(null, "정보가 일치하지 않습니다.", "탈퇴 실패", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "오류 : 탈퇴 실패", JOptionPane.ERROR_MESSAGE);
				}
				finally {
					Main.DBClose();
					try {
						Main.con.setAutoCommit(true);
					}
					catch(Exception ex) {}
				}
			}
		});
		
		frame.getContentPane().add(btn_resign);
		
		
		
		JButton btn_exit = new JButton("닫기");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_exit.setBounds(421, 328, 71, 23);
		frame.getContentPane().add(btn_exit);
		
		btn_resign.setEnabled(false);
		
		chk1.addItemListener(e -> {
		    if (chk1.isSelected()) {
		    	btn_resign.setEnabled(true); 
		    } else {
		    	btn_resign.setEnabled(false); 
		    }
		});
	
	}
}
