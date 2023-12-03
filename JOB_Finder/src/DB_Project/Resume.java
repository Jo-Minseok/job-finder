package DB_Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Resume{
	public JFrame frame; 
	
	public Resume() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100,100,669,480 );
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JLabel lbl_resume = new JLabel("이력서 명");
		lbl_resume.setBounds(22, 0, 257, 44);
		frame.getContentPane().add(lbl_resume);
		
		JTextField txt_resume = new JTextField();
		txt_resume.setBounds(22, 40, 242, 34);
		frame.getContentPane().add(txt_resume);
		
		JLabel lbl_graduate = new JLabel("최종 학력");
		lbl_graduate.setBounds(22, 74, 257, 44);
		frame.getContentPane().add(lbl_graduate);
		
		JTextField txt_graduate = new JTextField();
		txt_graduate.setBounds(22, 114, 242, 34);
		frame.getContentPane().add(txt_graduate);
		
		JLabel lbl_toeic = new JLabel("토익 점수");
		lbl_toeic.setBounds(22, 148, 257, 44);
		frame.getContentPane().add(lbl_toeic);
		
		JTextField txt_toeic = new JTextField();
		txt_toeic.setBounds(22, 188, 242, 34);
		frame.getContentPane().add(txt_toeic);
		
		JLabel lbl_foreign = new JLabel("해외 경험 횟수");
		lbl_foreign.setBounds(22, 222, 257, 44);
		frame.getContentPane().add(lbl_foreign);
		
		JTextField txt_foreign = new JTextField();
		txt_foreign.setBounds(22, 262, 242, 34);
		frame.getContentPane().add(txt_foreign);

		JButton btn_exit = new JButton("닫기");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_exit.setBounds(538, 387, 92, 37);
		frame.getContentPane().add(btn_exit);
		
		JButton btn_career = new JButton("경력 추가");
		btn_career.setBounds(297, 112, 107, 36);
		frame.getContentPane().add(btn_career);
		btn_career.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Career window = new Career();
				window.frame.setVisible(true);
			}
		});
		
		JTextArea txt_career = new JTextArea();
		txt_career.setBounds(295, 158, 293, 138);
		frame.getContentPane().add(txt_career);
		
		JButton btn_regist = new JButton("등록");
		btn_regist.setBounds(538, 340, 92, 37);
		frame.getContentPane().add(btn_regist);
		btn_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					Main.con.setAutoCommit(false);
					
					String sql = "UPDATE  개인회원 SET 포인트 = 포인트 -300 WHERE 회원ID = ?";
					Main.pstmt = Main.con.prepareStatement(sql);
					Main.pstmt.setString(1,Main.ID);
					Main.pstmt.executeUpdate();
					
					sql = "INSERT INTO 개인_포인트_수정_내역 VALUE (?, '사용', 300)";
					Main.pstmt = Main.con.prepareStatement(sql);
					Main.pstmt.setString(1, Main.ID);
					Main.pstmt.executeUpdate();
					
					Main.con.commit();
					JOptionPane.showMessageDialog(null, "포인트가 업데이트 되었습니다!","포인트 업데이트 성공", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),"게시글 업로드 실패", JOptionPane.ERROR_MESSAGE);
					try {
						Main.con.rollback();
					} catch(SQLException ex1) {}
				} finally {
					Main.DBClose();
					try {
						Main.con.setAutoCommit(true);
					}
					catch(SQLException ex1) {}
				}
			}
		});
	}
}
