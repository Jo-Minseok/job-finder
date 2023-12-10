package DB_Project;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Job_posting {

	public JFrame frame;
	public int Post_ID;
	
	public Job_posting(int Post_ID) {
		this.Post_ID = Post_ID;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 384, 415);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_category = new JLabel("직종");
		lbl_category.setBounds(12, 85, 57, 15);
		frame.getContentPane().add(lbl_category);
		
		JLabel lbl_job_category = new JLabel("채용분류");
		lbl_job_category.setBounds(12, 110, 57, 15);
		frame.getContentPane().add(lbl_job_category);
		
		JLabel lbl_type = new JLabel("고용형태");
		lbl_type.setBounds(12, 135, 57, 15);
		frame.getContentPane().add(lbl_type);
		
		JLabel lbl_salary = new JLabel("급여");
		lbl_salary.setBounds(12, 160, 57, 15);
		frame.getContentPane().add(lbl_salary);
		
		JLabel lbl_address = new JLabel("지역");
		lbl_address.setBounds(12, 185, 57, 15);
		frame.getContentPane().add(lbl_address);
		
		JLabel lbl_time = new JLabel("근무시간");
		lbl_time.setBounds(12, 210, 57, 15);
		frame.getContentPane().add(lbl_time);
		
		JLabel lbl_count = new JLabel("모집인원");
		lbl_count.setBounds(12, 235, 57, 15);
		frame.getContentPane().add(lbl_count);
		
		JLabel lbl_position = new JLabel("직책");
		lbl_position.setBounds(12, 260, 57, 15);
		frame.getContentPane().add(lbl_position);
		
		JLabel lbl_date = new JLabel("마감일");
		lbl_date.setBounds(12, 285, 57, 15);
		frame.getContentPane().add(lbl_date);
		
		JLabel lbl_name_data = new JLabel();
		lbl_name_data.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name_data.setFont(new Font("굴림", Font.BOLD, 20));
		lbl_name_data.setBounds(12, 10, 344, 63);
		frame.getContentPane().add(lbl_name_data);
		
		JLabel lbl_category_data = new JLabel();
		lbl_category_data.setBounds(81, 85, 275, 15);
		frame.getContentPane().add(lbl_category_data);
		
		JLabel lbl_job_category_data = new JLabel();
		lbl_job_category_data.setBounds(81, 110, 275, 15);
		frame.getContentPane().add(lbl_job_category_data);
		
		JLabel lbl_type_data = new JLabel();
		lbl_type_data.setBounds(81, 135, 275, 15);
		frame.getContentPane().add(lbl_type_data);
		
		JLabel lbl_salary_data = new JLabel();
		lbl_salary_data.setBounds(81, 160, 275, 15);
		frame.getContentPane().add(lbl_salary_data);
		
		JLabel lbl_address_data = new JLabel();
		lbl_address_data.setBounds(81, 185, 275, 15);
		frame.getContentPane().add(lbl_address_data);
		
		JLabel lbl_time_data = new JLabel();
		lbl_time_data.setBounds(81, 210, 275, 15);
		frame.getContentPane().add(lbl_time_data);
		
		JLabel lbl_count_data = new JLabel();
		lbl_count_data.setBounds(81, 235, 275, 15);
		frame.getContentPane().add(lbl_count_data);
		
		JLabel lbl_position_data = new JLabel();
		lbl_position_data.setBounds(81, 260, 275, 15);
		frame.getContentPane().add(lbl_position_data);
		
		JLabel lbl_date_data = new JLabel();
		lbl_date_data.setBounds(81, 285, 275, 15);
		frame.getContentPane().add(lbl_date_data);
		
		JButton btn_close = new JButton("닫기");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_close.setBounds(271, 343, 85, 23);
		frame.getContentPane().add(btn_close);
		
		JButton btn_apply = new JButton("지원");
		btn_apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resume_Apply window = new Resume_Apply(Job_posting.this);
				window.frame.setVisible(true);
			}
		});
		btn_apply.setBounds(174, 343, 85, 23);
		if(Main.mode == "기업") {
			btn_apply.setEnabled(false);
		}
		frame.getContentPane().add(btn_apply);
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
				lbl_salary_data.setText(Main.Won(Main.rs.getLong("급여")) + "(월급)");
				lbl_address_data.setText(Main.rs.getString("지역"));
				lbl_time_data.setText(String.valueOf(Main.rs.getInt("근무시간")) + "시간 (주)");
				lbl_count_data.setText(String.valueOf(Main.rs.getInt("모집인원"))+ "명");
				lbl_position_data.setText(Main.rs.getString("직책"));
				lbl_date_data.setText(String.valueOf(Main.rs.getDate("마감일")));
				Date modifiedDateA = subtractDays(Main.rs.getDate("마감일"),3);
				LocalDate currentDate = LocalDate.now();
				if(!modifiedDateA.toLocalDate().isAfter(currentDate)) {
					lbl_date_data.setForeground(Color.RED);
					lbl_date_data.setText(lbl_date_data.getText() + "(3일 이내로 남았습니다!)");
				}
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
	private Date subtractDays(Date date,int days) {
		long milliseconds = date.getTime() - (days*24*60*60*1000L);
		return new Date(milliseconds);
	}
}
