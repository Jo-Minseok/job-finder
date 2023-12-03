package DB_Project;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class Resign {

	public JFrame frame;
	private JTextField txt_id;
	private JTextField txt_phone;

	public Resign() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lbl_ID = new JLabel("ID");
		lbl_ID.setBounds(12, 10, 57, 15);
		frame.getContentPane().add(lbl_ID);
		
		txt_id = new JTextField();
		txt_id.setBounds(12, 35, 240, 21);
		frame.getContentPane().add(txt_id);
		txt_id.setColumns(10);
		
		JLabel lbl_phone = new JLabel("PHONE_NUMBER");
		lbl_phone.setBounds(12, 66, 116, 15);
		frame.getContentPane().add(lbl_phone);
		
		txt_phone = new JTextField();
		txt_phone.setBounds(12, 91, 240, 21);
		frame.getContentPane().add(txt_phone);
		txt_phone.setColumns(10);
		
		JCheckBox chk1 = new JCheckBox("(필수) 탈퇴 후, JOB Finder 서비스의 이용이 불가한 것에 동의합니다.");
		chk1.setBounds(12, 161, 480, 23);
		frame.getContentPane().add(chk1);
		
		JCheckBox chk2 = new JCheckBox("(선택) 탈퇴 후, 회원님의 동일한 ID로 재생성하는 것에 동의합니다.");
		chk2.setBounds(12, 203, 480, 23);
		frame.getContentPane().add(chk2);
		
		JButton btn_resign = new JButton("회원 탈퇴");
		btn_resign.setFont(new Font("굴림", Font.BOLD, 18));
		btn_resign.setBounds(12, 294, 116, 57);
		
		// 기능 구현 : 회원 탈퇴
		
		btn_resign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					
					String tablename;
					
					if(Main.mode == "개인")
						tablename = "개인회원";
					else
						tablename = "기업회원";
					
					String sql = "DELETE FROM " + tablename + " WHERE 회원ID = ? AND 휴대폰 = ?";
					
					Main.pstmt = Main.con.prepareStatement(sql);
					Main.pstmt.setString(1, txt_id.getText());
					Main.pstmt.setString(2, txt_phone.getText());
					
					Main.rs = Main.pstmt.executeQuery();
					
					if(chk2.isSelected()) { 
						// 탈퇴 후에, 동일ID로 재생설할 수 있게
					}
						
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
