package DB_Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Career  {

	public JFrame frame;
	//private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_company;
	private JTextField txt_year;
	private JTextField txt_position;
	private JTextField txt_salary;
	private String resumeName;
	
	public Career(String resumeName) {
		this.resumeName = resumeName;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 325, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_company = new JLabel("기업명");
		lbl_company.setBounds(25, 25, 100, 20);
		contentPane.add(lbl_company);
		
		JLabel lbl_year = new JLabel("년수");
		lbl_year.setBounds(25, 55, 100, 20);
		contentPane.add(lbl_year);
		
		JLabel lbl_position = new JLabel("직급");
		lbl_position.setBounds(25, 85, 100, 20);
		contentPane.add(lbl_position);
		
		JLabel lbl_salary = new JLabel("연봉");
		lbl_salary.setBounds(25, 115, 100, 20);
		contentPane.add(lbl_salary);
		
		txt_company = new JTextField();
		txt_company.setBounds(105, 25, 170, 25);
		contentPane.add(txt_company);
		txt_company.setColumns(10);
		
		txt_year = new JTextField();
		txt_year.setColumns(10);
		txt_year.setBounds(105, 55, 170, 25);
		contentPane.add(txt_year);
		
		txt_position = new JTextField();
		txt_position.setColumns(10);
		txt_position.setBounds(105, 85, 170, 25);
		contentPane.add(txt_position);
		
		txt_salary = new JTextField();
		txt_salary.setColumns(10);
		txt_salary.setBounds(105, 115, 170, 25);
		contentPane.add(txt_salary);
		
		JButton btn_regist = new JButton("작성");
		btn_regist.setBounds(12, 175, 70, 24);
		contentPane.add(btn_regist);
		btn_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					Main.con.setAutoCommit(false);
					
					String sql = "INSERT INTO 이력서_경력 (회원 ID, 이력서명, 경력_위치, 년수, 직급, 연봉) VALUES (?, ?, ?, ?, ?, ?)";
					Main.pstmt = Main.con.prepareStatement(sql);
					
					Main.pstmt.setString(1, Main.ID);
		            Main.pstmt.setString(2, Career.this.resumeName);
		            Main.pstmt.setString(3, txt_company.getText());
		            Main.pstmt.setInt(4, Integer.parseInt(txt_year.getText()));
		            Main.pstmt.setString(5, txt_position.getText());
		            Main.pstmt.setInt(6, Integer.parseInt(txt_salary.getText()));

		            Main.pstmt.executeUpdate();
		            Main.con.commit();
		            
		            JOptionPane.showMessageDialog(null, "경력 정보가 저장되었습니다.", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
		            
		        } catch (Exception ex) {
		            try {
		                Main.con.rollback(); 
		            } catch (Exception exRollback) {	
		            	
		            }
		            JOptionPane.showMessageDialog(null, "경력 정보 저장 실패: " + ex.getMessage(), "저장 실패", JOptionPane.ERROR_MESSAGE);
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
		btn_exit.setBounds(227, 175, 70, 24);
		contentPane.add(btn_exit);
	}
}
