package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Job_posting {

	public JFrame frame;
	private int Post_ID;
	
	public Job_posting(int Post_ID) {
		this.Post_ID = Post_ID;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 281, 351);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_name = new JLabel("회사명");
		lbl_name.setBounds(12, 10, 57, 15);
		frame.getContentPane().add(lbl_name);
		
		JLabel lbl_category = new JLabel("직종");
		lbl_category.setBounds(12, 35, 57, 15);
		frame.getContentPane().add(lbl_category);
		
		JLabel lbl_job_category = new JLabel("채용분류");
		lbl_job_category.setBounds(12, 60, 57, 15);
		frame.getContentPane().add(lbl_job_category);
		
		JLabel lbl_type = new JLabel("고용형태");
		lbl_type.setBounds(12, 85, 57, 15);
		frame.getContentPane().add(lbl_type);
		
		JLabel lbl_salary = new JLabel("급여");
		lbl_salary.setBounds(12, 110, 57, 15);
		frame.getContentPane().add(lbl_salary);
		
		JLabel lbl_address = new JLabel("지역");
		lbl_address.setBounds(12, 135, 57, 15);
		frame.getContentPane().add(lbl_address);
		
		JLabel lbl_time = new JLabel("근무시간");
		lbl_time.setBounds(12, 160, 57, 15);
		frame.getContentPane().add(lbl_time);
		
		JLabel lbl_count = new JLabel("모집인원");
		lbl_count.setBounds(12, 185, 57, 15);
		frame.getContentPane().add(lbl_count);
		
		JLabel lbl_position = new JLabel("직책");
		lbl_position.setBounds(12, 210, 57, 15);
		frame.getContentPane().add(lbl_position);
		
		JLabel lbl_date = new JLabel("마감일");
		lbl_date.setBounds(12, 235, 57, 15);
		frame.getContentPane().add(lbl_date);
		
		JLabel lbl_name_data = new JLabel();
		lbl_name_data.setBounds(81, 10, 144, 15);
		frame.getContentPane().add(lbl_name_data);
		
		JLabel lbl_category_data = new JLabel();
		lbl_category_data.setBounds(81, 35, 144, 15);
		frame.getContentPane().add(lbl_category_data);
		
		JLabel lbl_job_category_data = new JLabel();
		lbl_job_category_data.setBounds(81, 60, 144, 15);
		frame.getContentPane().add(lbl_job_category_data);
		
		JLabel lbl_type_data = new JLabel();
		lbl_type_data.setBounds(81, 85, 144, 15);
		frame.getContentPane().add(lbl_type_data);
		
		JLabel lbl_salary_data = new JLabel();
		lbl_salary_data.setBounds(81, 110, 144, 15);
		frame.getContentPane().add(lbl_salary_data);
		
		JLabel lbl_address_data = new JLabel();
		lbl_address_data.setBounds(81, 135, 144, 15);
		frame.getContentPane().add(lbl_address_data);
		
		JLabel lbl_time_data = new JLabel();
		lbl_time_data.setBounds(81, 160, 144, 15);
		frame.getContentPane().add(lbl_time_data);
		
		JLabel lbl_count_data = new JLabel();
		lbl_count_data.setBounds(81, 185, 144, 15);
		frame.getContentPane().add(lbl_count_data);
		
		JLabel lbl_position_data = new JLabel();
		lbl_position_data.setBounds(81, 210, 144, 15);
		frame.getContentPane().add(lbl_position_data);
		
		JLabel lbl_date_data = new JLabel();
		lbl_date_data.setBounds(81, 235, 144, 15);
		frame.getContentPane().add(lbl_date_data);
		
		JButton btn_close = new JButton("닫기");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_close.setBounds(168, 279, 85, 23);
		frame.getContentPane().add(btn_close);
		try {
			Main.DBConnection();
			String sql = "SELECT 기업명,직종,채용_분류,고용형태,급여,지역,근무시간,모집인원,직책,마감일,경쟁률 FROM 채용_게시글,기업회원 WHERE 기업회원.회원ID = 채용_게시글.작성자ID AND 게시글_번호 = " + Post_ID;
			Main.stmt = Main.con.createStatement();
			Main.rs = Main.stmt.executeQuery(sql);
			if(Main.rs.next()) {
				lbl_name_data.setText(Main.rs.getString("기업명"));
				frame.setTitle("채용 게시글 조회 - [" + Main.rs.getString("기업명") + "]");
				lbl_category_data.setText(Main.rs.getString("직종"));
				lbl_job_category_data.setText(Main.rs.getString("채용_분류"));
				lbl_type_data.setText(Main.rs.getString("고용형태"));
				lbl_salary_data.setText(Main.rs.getString("급여"));
				lbl_address_data.setText(Main.rs.getString("지역"));
				lbl_time_data.setText(String.valueOf(Main.rs.getInt("근무시간")));
				lbl_count_data.setText(String.valueOf(Main.rs.getInt("모집인원")));
				lbl_position_data.setText(Main.rs.getString("직책"));
				lbl_date_data.setText(String.valueOf(Main.rs.getDate("마감일")));
			}
			else {
				throw(new SQLException());
			}
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"게시글이 사라졌거나 불러올 수 없습니다.","게시글 로드 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
	}

}
