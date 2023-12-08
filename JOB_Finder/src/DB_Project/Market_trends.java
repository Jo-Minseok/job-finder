package DB_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Market_trends {

	public JFrame frame;
	private JPanel contentPane;
	private JTextField txt_start;
	private JTextField txt_end;
	private JComboBox<String> cb_company;
	private JButton btn_serch;
	private JPanel panel;
	private JLabel lbl_name;
	private JLabel lbl_gender;
	private JLabel lbl_now;
	private JLabel lbl_salary;
	private JLabel lbl_take;
	private JLabel lbl_date;
	private JLabel lbl_date_data;
	private JLabel lbl_avgsalary;
	private JLabel lbl_avgsalary_data;
	private JLabel lbl_avgtime;
	private JLabel lbl_avgtime_data;
	private JLabel lbl_avgrate;
	private JLabel lbl_avgrate_data;
	private long total_post = 0, take = 0, man = 0, all = 0, avg_salary = 0, date_post = 0, date_salary = 0, date_work_time = 0, date_rate = 0;

	public Market_trends() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("채용 시장 트랜드");
		frame.setBounds(100, 100, 785, 361);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		cb_company = new JComboBox<String>();
		cb_company.setEditable(true);
		cb_company.setBounds(14, 22, 312, 25);
		CompanyComboBox();
		
		Container c = frame.getContentPane();
		c.setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(cb_company);
		
		JButton btn_exit = new JButton("닫기");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_exit.setBounds(660, 4, 97, 23);
		c.add(btn_exit);
		
		panel = new JPanel();
		panel.setBounds(380, 37, 377, 53);
		c.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{188, 188, 0};
		gbl_panel.rowHeights = new int[]{17, 17, 17, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lbl_name = new JLabel("[전체/회사이름별]");
		GridBagConstraints gbc_lbl_name = new GridBagConstraints();
		gbc_lbl_name.fill = GridBagConstraints.BOTH;
		gbc_lbl_name.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_name.gridx = 0;
		gbc_lbl_name.gridy = 0;
		panel.add(lbl_name, gbc_lbl_name);
		
		lbl_gender = new JLabel("남여 성비: ");
		GridBagConstraints gbc_lbl_gender = new GridBagConstraints();
		gbc_lbl_gender.fill = GridBagConstraints.BOTH;
		gbc_lbl_gender.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_gender.gridx = 1;
		gbc_lbl_gender.gridy = 0;
		panel.add(lbl_gender, gbc_lbl_gender);
		
		lbl_now = new JLabel("현재 채용중인 공고: ");
		GridBagConstraints gbc_lbl_now = new GridBagConstraints();
		gbc_lbl_now.fill = GridBagConstraints.BOTH;
		gbc_lbl_now.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_now.gridx = 0;
		gbc_lbl_now.gridy = 1;
		panel.add(lbl_now, gbc_lbl_now);
		
		lbl_salary = new JLabel("평균 연봉: ");
		GridBagConstraints gbc_lbl_salary = new GridBagConstraints();
		gbc_lbl_salary.fill = GridBagConstraints.BOTH;
		gbc_lbl_salary.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_salary.gridx = 1;
		gbc_lbl_salary.gridy = 1;
		panel.add(lbl_salary, gbc_lbl_salary);
		
		lbl_take = new JLabel("매출액: ");
		GridBagConstraints gbc_lbl_take = new GridBagConstraints();
		gbc_lbl_take.gridwidth = 2;
		gbc_lbl_take.fill = GridBagConstraints.BOTH;
		gbc_lbl_take.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_take.gridx = 0;
		gbc_lbl_take.gridy = 2;
		panel.add(lbl_take, gbc_lbl_take);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-33, 57, 411, 33);
		c.add(panel_1);
		
		txt_start = new JTextField();
		panel_1.add(txt_start);
		txt_start.setHorizontalAlignment(SwingConstants.CENTER);
		txt_start.setColumns(10);
		
		JLabel lb1 = new JLabel("~");
		panel_1.add(lb1);
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		
		txt_end = new JTextField();
		panel_1.add(txt_end);
		txt_end.setHorizontalAlignment(SwingConstants.CENTER);
		txt_end.setColumns(10);
		
		btn_serch = new JButton("검색");
		panel_1.add(btn_serch);
		
		lbl_date = new JLabel("~ 일 채용 게시글 개수");
		lbl_date.setBounds(12, 109, 745, 15);
		frame.getContentPane().add(lbl_date);
		
		lbl_date_data = new JLabel("");
		lbl_date_data.setBounds(14, 134, 312, 15);
		frame.getContentPane().add(lbl_date_data);
		
		lbl_avgsalary = new JLabel("~ 일 채용 평균 연봉");
		lbl_avgsalary.setBounds(14, 159, 743, 15);
		frame.getContentPane().add(lbl_avgsalary);
		
		lbl_avgsalary_data = new JLabel("");
		lbl_avgsalary_data.setBounds(14, 184, 312, 15);
		frame.getContentPane().add(lbl_avgsalary_data);
		
		lbl_avgtime = new JLabel("~ 일 채용 평균 근무 시간");
		lbl_avgtime.setBounds(14, 209, 743, 15);
		frame.getContentPane().add(lbl_avgtime);
		
		lbl_avgtime_data = new JLabel("");
		lbl_avgtime_data.setBounds(14, 234, 312, 15);
		frame.getContentPane().add(lbl_avgtime_data);
		
		lbl_avgrate = new JLabel("~ 일 평균 경쟁률");
		lbl_avgrate.setBounds(14, 266, 743, 15);
		frame.getContentPane().add(lbl_avgrate);
		
		lbl_avgrate_data = new JLabel("");
		lbl_avgrate_data.setBounds(14, 291, 312, 15);
		frame.getContentPane().add(lbl_avgrate_data);
		DB_To_Date(null,null,null);
		
		btn_serch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_start.getText().isEmpty() && txt_end.getText().isEmpty()){
					if(cb_company.getSelectedItem().toString().equals("")) {
						DB_To_Date(null,null,null);
					}
					else {
						DB_To_Date(cb_company.getSelectedItem().toString(),null,null);
					}
				}
				else if(!(txt_start.getText().isEmpty()) && txt_end.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "종료 날짜를 입력하세요","검색 실패", JOptionPane.ERROR_MESSAGE);
				}
				else if(txt_start.getText().isEmpty() && !(txt_end.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "시작 날짜를 입력하세요","검색 실패", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(cb_company.getSelectedItem().toString().equals("")) {
						DB_To_Date(null,txt_start.getText(),txt_start.getText());
					}
					else {
						DB_To_Date(cb_company.getSelectedItem().toString(),txt_start.getText(),txt_end.getText());
					}
				}
				
			}
		});
	}
	
		// 회사 콤보박스 DB연결
		public ArrayList<String> getcompanyNameData(){
			ArrayList<String> regionList = new ArrayList<>();
			
			try {	           
				Main.DBConnection();

	            String query = "SELECT \"이름\" FROM \"SEOK3764\".\"기업\"";
	            Main.stmt = Main.con.createStatement();
	            Main.rs = Main.stmt.executeQuery(query);

	            while (Main.rs.next()) {
	                String companyName = Main.rs.getString("이름");
	                regionList.add(companyName);
	            }

	            Main.rs.close();
	            Main.stmt.close();
	            Main.con.close();

	        } catch (SQLException e) {
	        	JOptionPane.showMessageDialog(null, "기업을 DB로부터 읽어오지 못했습니다.","DB 연결 실패", JOptionPane.ERROR_MESSAGE);
	        	frame.dispose();
	        }
	        return regionList;
	    }
		
		// 회사 콤보박스 리스트 추가
		public void CompanyComboBox() {
		    ArrayList<String> companyNameList = getcompanyNameData();
		    for (String companyName : companyNameList) {
		        cb_company.addItem(companyName);
		    }
		}
		
		public void DB_To_Date(String Company_name, String start, String end) {
			try {
				Main.DBConnection();
				String sql = "{CALL COUNT_TREND_PROGRAM(?,?,?,?,?,?,?,?,?,?,?,?)}";
				Main.cstmt = Main.con.prepareCall(sql);
				if(Company_name == null) {
					Main.cstmt.setNull(1, java.sql.Types.NVARCHAR);
					lbl_name.setText("[전체 조회]");
				}
				else {
					Main.cstmt.setString(1, Company_name);
					lbl_name.setText("[" + Company_name + "]");
				}
				if(start == null) {
					Main.cstmt.setNull(2, java.sql.Types.DATE);
					Main.cstmt.setNull(3, java.sql.Types.DATE);
				}
				else {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
					LocalDate start_ = LocalDate.parse(start, formatter);
					LocalDate end_ = LocalDate.parse(end, formatter);
					java.sql.Date start_d = java.sql.Date.valueOf(start_);
					java.sql.Date end_d = java.sql.Date.valueOf(end_);
					Main.cstmt.setDate(2, start_d);
					Main.cstmt.setDate(3, end_d);
				}
				
				
				Main.cstmt.registerOutParameter(4, java.sql.Types.NUMERIC);
				Main.cstmt.registerOutParameter(5, java.sql.Types.NUMERIC);
				Main.cstmt.registerOutParameter(6, java.sql.Types.NUMERIC);
				Main.cstmt.registerOutParameter(7, java.sql.Types.NUMERIC);
				Main.cstmt.registerOutParameter(8, java.sql.Types.NUMERIC);
				Main.cstmt.registerOutParameter(9, java.sql.Types.NUMERIC);
				Main.cstmt.registerOutParameter(10, java.sql.Types.NUMERIC);
				Main.cstmt.registerOutParameter(11, java.sql.Types.NUMERIC);
				Main.cstmt.registerOutParameter(12, java.sql.Types.NUMERIC);
				Main.cstmt.execute();
				
				total_post = Main.cstmt.getLong(4);
				take = Main.cstmt.getLong(5);
				man = Main.cstmt.getLong(6);
				all = Main.cstmt.getLong(7);
				avg_salary = Main.cstmt.getLong(8);
				date_post = Main.cstmt.getLong(9);
				date_salary = Main.cstmt.getLong(10);
				date_work_time = Main.cstmt.getLong(11);
				date_rate = Main.cstmt.getLong(12);
				
				DecimalFormat decimalFormat = new DecimalFormat("#.#");
				double woman_rate = 1 - (double)man / all;
				double man_rate = (double)man/all;
				lbl_now.setText("현재 채용중인 공고: " + total_post + "개");
				lbl_take.setText("매출액: " + Main.Won(take));
				lbl_gender.setText("남여 성비: " + decimalFormat.format(man_rate) + ":" + decimalFormat.format(woman_rate) + "(남:여)");
				lbl_salary.setText("연봉: " + Main.Won(avg_salary));
				if(start == null) {
					lbl_date.setText("전체 채용 게시글 개수");
					lbl_date_data.setText(date_post + "개");
					lbl_avgsalary.setText("전체 채용 게시글 평균 연봉");
					lbl_avgsalary_data.setText(Main.Won(date_salary));
					lbl_avgtime.setText("전체 채용 게시글 평균 근무 시간");
					lbl_avgtime_data.setText(date_work_time + "시간(주)");
					lbl_avgrate.setText("전체 채용 게시글 평균 경쟁률");
					lbl_avgrate_data.setText(date_rate + "%");
				}
				else {
					lbl_date.setText(start + " ~ " + end + " 기간 지원 가능 채용 게시글 개수");
					lbl_date_data.setText(date_post + "개");
					lbl_avgsalary.setText(start + " ~ " + end + " 기간 지원 가능 채용 평균 연봉");
					lbl_avgsalary_data.setText(Main.Won(date_salary) + "원");
					lbl_avgtime.setText(start + " ~ " + end + " 기간 지원 가능 채용 평균 근무 시간");
					lbl_avgtime_data.setText(date_work_time + "시간(주)");
					lbl_avgrate.setText(start + " ~ " + end + " 기간 지원 가능 채용 평균 경쟁률");
					lbl_avgrate_data.setText(date_rate + "%");
				}
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "DB로부터 데이터를 읽어오지 못했습니다.","DB 연결 실패", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
				frame.dispose();
			}
			finally {
				Main.DBClose();
			}
		}
}