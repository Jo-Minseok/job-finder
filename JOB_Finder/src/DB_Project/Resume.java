package DB_Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Resume{
	public JFrame frame; 
	private String company;
    private String year;
    private String position;
    private String salary; 
    private JTextArea txt_career;
    private JTextArea txt_certifi;
    
	public Resume() {
		initialize();
	}
	
	public void Careerinfo(String companyinfo, String yearinfo, String positioninfo, String salaryinfo) {
		this.company = companyinfo;
		this.year = yearinfo;
		this.position = positioninfo;
		this.salary = salaryinfo;
	}
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("이력서 작성");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lbl_toeic = new JLabel("토익 점수 (숫자만)");
		lbl_toeic.setBounds(22, 148, 257, 44);
		frame.getContentPane().add(lbl_toeic);
		
		JTextField txt_toeic = new JTextField();
		txt_toeic.setBounds(22, 188, 242, 34);
		frame.getContentPane().add(txt_toeic);
		
		JLabel lbl_foreign = new JLabel("해외 경험 횟수 (숫자만)");
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
		
		JButton btn_regist = new JButton("등록");
		btn_regist.setBounds(538, 340, 92, 37);
		frame.getContentPane().add(btn_regist);
		
		JButton btn_cirtifi = new JButton("자격증 추가");
		btn_cirtifi.setBounds(293, 176, 107, 36);
		frame.getContentPane().add(btn_cirtifi);
		btn_cirtifi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Certificate window = new Certificate(Resume.this);
				window.frame.setVisible(true);
			}
		});
		
		txt_certifi = new JTextArea();
		txt_certifi.setBounds(291, 222, 293, 74);
		frame.getContentPane().add(txt_certifi);
		txt_certifi.setEnabled(false);
		
		JButton btn_career = new JButton("경력 추가");
		btn_career.setBounds(293, 40, 107, 36);
		frame.getContentPane().add(btn_career);
		btn_career.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Career window = new Career(Resume.this);
				window.frame.setVisible(true);
			}			
		});	
		
		txt_career = new JTextArea();
		txt_career.setBounds(291, 86, 293, 74);
		frame.getContentPane().add(txt_career);
		txt_career.setEnabled(false);
		
		btn_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					Main.con.setAutoCommit(false);
					
					String sql = "INSERT INTO 이력서 (작성자ID, 이력서명, 학력, 토익, 해외_경험_횟수, 작성일자) VALUES (?, ?, ?, ?, ?, SYSDATE)";
		            Main.pstmt = Main.con.prepareStatement(sql);	            
		            Main.pstmt.setString(1, Main.ID);	            
		            Main.pstmt.setString(2, txt_resume.getText());
		            Main.pstmt.setString(3, txt_graduate.getText());
		            Main.pstmt.setInt(4, Integer.parseInt(txt_toeic.getText()));
		            Main.pstmt.setInt(5, Integer.parseInt(txt_foreign.getText()));
		            Main.pstmt.executeUpdate();
            
					sql = "UPDATE 개인회원 SET 포인트 = 포인트 -300 WHERE 회원ID = ?";
					Main.pstmt = Main.con.prepareStatement(sql);
					Main.pstmt.setString(1,Main.ID);
					Main.pstmt.executeUpdate();
					
					sql = "INSERT INTO 개인_포인트_수정_내역 VALUES (?, '사용', 300)";
					Main.pstmt = Main.con.prepareStatement(sql);
					Main.pstmt.setString(1, Main.ID);					
					Main.pstmt.executeUpdate();
									
					// Certificate
					if(txt_certifi != null && !txt_certifi.getText().trim().isEmpty()) {
						sql = "INSERT INTO 이력서_자격증 (이력서_작성자, 이력서명, 자격증명) VALUES (?, ?, ?)";
						Main.pstmt = Main.con.prepareStatement(sql);
						
						Main.pstmt.setString(1, Main.ID);
			            Main.pstmt.setString(2, txt_resume.getText());
			            Main.pstmt.setString(3, txt_certifi.getText());
			            
			            Main.pstmt.executeUpdate();
					}
					
		            // Career
					if(txt_career != null && !txt_career.getText().trim().isEmpty()) {
			            sql = "INSERT INTO 이력서_경력 (회원ID, 이력서명, 경력_위치, 년수, 직급, 연봉) VALUES (?, ?, ?, ?, ?, ?)";
						Main.pstmt = Main.con.prepareStatement(sql);
						
						Main.pstmt.setString(1, Main.ID);
			            Main.pstmt.setString(2, txt_resume.getText());
			            Main.pstmt.setString(3, Resume.this.company);			         
			            Main.pstmt.setInt(4, Integer.parseInt(Resume.this.year));			            
			            Main.pstmt.setString(5, Resume.this.position);
			            Main.pstmt.setInt(6, Integer.parseInt(Resume.this.salary));	 		            
	
			            Main.pstmt.executeUpdate();
					}
					
					CallableStatement cstmt = Main.con.prepareCall("{CALL POST_COUNT_PERSONAL()}");
					cstmt.execute();

		            Main.con.commit();
		            
					JOptionPane.showMessageDialog(null, "이력서가 저장되었습니다.", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
					txt_resume.setText("");
					txt_graduate.setText("");
					txt_toeic.setText("");
					txt_foreign.setText("");
					txt_certifi.setText("");
					txt_career.setText("");
				}
				catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),"이력서 저장 실패", JOptionPane.ERROR_MESSAGE);
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
	public void updateCareerinfo(String careerinfo) {
		txt_career.setText(careerinfo);
		}
	public void updateCertificateinfo(String certificateinfo){
		txt_certifi.setText(certificateinfo);
		}
}
