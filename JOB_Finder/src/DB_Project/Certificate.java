package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Certificate {

	public JFrame frame;
	private JTextField txt_certifi;
	private String resumeName;

	public Certificate(String resumeName) {
		this.resumeName = resumeName;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 364, 171);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_certifi = new JLabel("자격증 명");
		lbl_certifi.setFont(new Font("굴림", Font.PLAIN, 14));
		lbl_certifi.setBounds(12, 10, 66, 24);
		frame.getContentPane().add(lbl_certifi);
		
		txt_certifi = new JTextField();
		txt_certifi.setBounds(90, 10, 242, 24);
		frame.getContentPane().add(txt_certifi);
		txt_certifi.setColumns(10);
		
		JButton btn_regist = new JButton("작성");
		btn_regist.setBounds(12, 83, 97, 39);
		frame.getContentPane().add(btn_regist);
		btn_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					Main.con.setAutoCommit(false);
					
					String sql = "INSERT INTO 이력서_자격증 (이력서_작성자, 이력서명, 자격증명) VALUES (?, ?, ?)";
					Main.pstmt = Main.con.prepareStatement(sql);
					
					Main.pstmt.setString(1, Main.ID);
		            Main.pstmt.setString(2, Certificate.this.resumeName);
		            Main.pstmt.setString(3, txt_certifi.getText());

		            Main.pstmt.executeUpdate();
		            Main.con.commit();
		            
		            JOptionPane.showMessageDialog(null, "자격증이 저장되었습니다.", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
		            
		        }  catch (Exception ex) {
		            try {
		                Main.con.rollback(); 
		            } catch (Exception exRollback) {	
		            	
		            }
		            JOptionPane.showMessageDialog(null, "자격증 정보 저장 실패: " + ex.getMessage(), "저장 실패", JOptionPane.ERROR_MESSAGE);
		        } finally {
		            Main.DBClose();
		            frame.dispose();
		        }
			}
		});
		
		JButton btn_exit = new JButton("닫기");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_exit.setBounds(235, 83, 97, 39);
		frame.getContentPane().add(btn_exit);
	}
}
