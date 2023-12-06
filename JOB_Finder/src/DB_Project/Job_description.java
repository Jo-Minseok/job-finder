package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Job_description {

	public JFrame frame;
	private String Company_name;
	private String Briefing_name;
	
	public Job_description(String Company_name, String Briefing_name) {
		this.Company_name = Company_name;
		this.Briefing_name = Briefing_name;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 225);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_company = new JLabel("<기업명>");
		lbl_company.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_company.setFont(new Font("굴림", Font.BOLD, 18));
		lbl_company.setBounds(12, 10, 410, 40);
		frame.getContentPane().add(lbl_company);
		
		JLabel lbl_briefing_name = new JLabel("<설명회명>");
		lbl_briefing_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_name.setBounds(12, 53, 410, 15);
		frame.getContentPane().add(lbl_briefing_name);
		
		JLabel lbl_count = new JLabel("회차 : ");
		lbl_count.setBounds(12, 78, 36, 15);
		frame.getContentPane().add(lbl_count);
		
		JLabel lbl_date = new JLabel("일시 : ");
		lbl_date.setBounds(12, 103, 36, 15);
		frame.getContentPane().add(lbl_date);
		
		JLabel lbl_location = new JLabel("장소 : ");
		lbl_location.setBounds(12, 128, 36, 15);
		frame.getContentPane().add(lbl_location);
		
		JLabel lbl_count_data = new JLabel("New label");
		lbl_count_data.setBounds(49, 78, 373, 15);
		frame.getContentPane().add(lbl_count_data);
		
		JLabel lbl_date_data = new JLabel("New label");
		lbl_date_data.setBounds(49, 103, 373, 15);
		frame.getContentPane().add(lbl_date_data);
		
		JLabel lbl_location_data = new JLabel("New label");
		lbl_location_data.setBounds(49, 128, 373, 15);
		frame.getContentPane().add(lbl_location_data);
		
		JButton btn_close = new JButton("닫기");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_close.setBounds(325, 153, 97, 23);
		frame.getContentPane().add(btn_close);
		
		try {
			Main.DBConnection();
			Main.stmt = Main.con.createStatement();
			String sql = "SELECT 회차,일시,장소 FROM 채용_설명회 WHERE 기업명 = '"+ Company_name + "' AND " + "설명회명 = '" + Briefing_name + "'";
			Main.rs = Main.stmt.executeQuery(sql);
			if(Main.rs.next()) {
				lbl_company.setText(Company_name);
				frame.setTitle("채용 설명회 - [" + Company_name + "]");
				lbl_briefing_name.setText(Briefing_name);
				lbl_count_data.setText(String.valueOf(Main.rs.getInt("회차")));
				lbl_date_data.setText(String.valueOf(Main.rs.getDate("일시")));
				lbl_location_data.setText(Main.rs.getString("장소"));
			}
			else {
				throw (new SQLException());
			}
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "기업 또는 게시글이 존재하지 않습니다.","검색 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
	}

}
