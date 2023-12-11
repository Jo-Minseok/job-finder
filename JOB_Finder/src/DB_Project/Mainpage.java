package DB_Project;


import java.util.Date;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;

public class Mainpage {
	class Recruit_Information{
		String Company;
		String ID;
		int Post_number;
		Date Deadline;
		int Rate;
		Recruit_Information(String Company, String ID, int Post_number, Date Deadline, int Rate){
			this.Company = Company;
			this.ID = ID;
			this.Post_number = Post_number;
			this.Deadline = Deadline;
			this.Rate = Rate;
		}
	}
	
	class Briefing_Information{
		String Company;
		String Name;
		int count;
		Date open_date;
		
		Briefing_Information(String Company, String Name, int count, Date open_date){
			this.Company = Company;
			this.Name = Name;
			this.count = count;
			this.open_date = open_date;
		}
	}
	
	int recruit_button = 0, recruit_page = 0, briefing_button = 0, briefing_page = 0, up_page = 0, down_page = 0, max_recruit_page, max_briefing_page;
	public JFrame frame;
	private JTextField txt_business;
	private JLabel lbl_post;
	private JLabel lbl_briefing;
	
	private JButton btn_previous;
	private JButton btn_next;
	private JButton btn_home;
	
	private JButton btn_previous_1;
	private JButton btn_next_1;
	private JButton btn_home_1;
	
	private JButton btn_inquiry_1;
	private JButton btn_inquiry_2;
	private JButton btn_inquiry_3;
	private JButton btn_inquiry_4;
	private JButton btn_inquiry_5;
	private JButton btn_inquiry_6;
	private JLabel lbl_inquiry_1;
	private JLabel lbl_inquiry_2;
	private JLabel lbl_inquiry_3;
	private JLabel lbl_inquiry_4;
	private JLabel lbl_inquiry_5;
	private JLabel lbl_inquiry_6;
	private JLabel lbl_Rate_1;
	private JLabel lbl_Rate_2;
	private JLabel lbl_Rate_3;
	private JLabel lbl_Rate_6;
	private JLabel lbl_Rate_5;
	private JLabel lbl_Rate_4;
	
	private JButton btn_briefing_1;
	private JButton btn_briefing_2;
	private JButton btn_briefing_3;
	private JButton btn_briefing_4;
	private JButton btn_briefing_5;
	private JButton btn_briefing_6;
	private JLabel lbl_briefing_1;
	private JLabel lbl_briefing_2;
	private JLabel lbl_briefing_3;
	private JLabel lbl_briefing_6;
	private JLabel lbl_briefing_4;
	private JLabel lbl_briefing_5;
	
	Recruit_Information[][] Recruit_List = null;
	Briefing_Information[][] Briefing_List = null;

	public Mainpage() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("JOB FINDER MAIN PAGE");
		frame.setBounds(100, 100, 806, 782);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_logo = new JLabel("New label");
		lbl_logo.setForeground(Color.BLACK);
		lbl_logo.setBackground(Color.WHITE);
		lbl_logo.setBounds(12, 10, 766, 109);
		lbl_logo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Main.png")));
		frame.getContentPane().add(lbl_logo);
		
		JLabel lbl_business_search = new JLabel("기업 조회");
		lbl_business_search.setBounds(22, 129, 57, 15);
		frame.getContentPane().add(lbl_business_search);
		
		txt_business = new JTextField();
		txt_business.setBounds(106, 126, 220, 21);
		frame.getContentPane().add(txt_business);
		txt_business.setColumns(10);
		
		JButton btn_search = new JButton("조회");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_business.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "기업 이름을 입력하세요!","기업명 입력 요망", JOptionPane.WARNING_MESSAGE);
				}
				else {
					Corporate_inquiry window = new Corporate_inquiry(txt_business.getText());
				}
			}
		});
		btn_search.setBounds(343, 125, 78, 23);
		frame.getContentPane().add(btn_search);
		
		JButton btn_Point = new JButton("포인트");
		btn_Point.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point window = new Point();
				window.frame.setVisible(true);
			}
		});
		btn_Point.setBounds(557, 328, 190, 23);
		frame.getContentPane().add(btn_Point);
		
		JButton btn_all = new JButton("전체");
		btn_all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompanyFullView window = new CompanyFullView();
				window.frame.setVisible(true);
			}
		});
		btn_all.setBounds(433, 125, 78, 23);
		frame.getContentPane().add(btn_all);
		
		JLabel lbl_now = new JLabel("현재 모집중인 공고 개수  : ");
		lbl_now.setBounds(557, 126, 182, 15);
		frame.getContentPane().add(lbl_now);
		
		JButton btn_market = new JButton("채용 시장 분석");
		btn_market.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					Main.cstmt = Main.con.prepareCall("{CALL COUNT_TREND_UPDATE()}");
					Main.cstmt.execute();
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "불러오기 실패","DB로부터 업데이트를 하지 못하였습니다.", JOptionPane.ERROR_MESSAGE);
				}
				finally {
					Main.DBClose();
				}
				Market_trends window = new Market_trends();
				window.frame.setVisible(true);
			}
		});
		btn_market.setBounds(557, 151, 190, 23);
		frame.getContentPane().add(btn_market);
		
		JLabel lbl_name = new JLabel("이름 : ");
		lbl_name.setBounds(557, 205, 190, 15);
		frame.getContentPane().add(lbl_name);
		
		JLabel lbl_ID = new JLabel("아이디 : ");
		lbl_ID.setText(lbl_ID.getText() + Main.ID);
		lbl_ID.setBounds(557, 255, 190, 15);
		frame.getContentPane().add(lbl_ID);
		
		JLabel lbl_post_count = new JLabel("작성한 게시글 개수  : ");
		lbl_post_count.setForeground(Color.BLACK);
		lbl_post_count.setBounds(557, 275, 190, 15);
		frame.getContentPane().add(lbl_post_count);
		
		JButton btn_logout = new JButton("로그아웃");
		btn_logout.setBounds(557, 360, 89, 23);
		frame.getContentPane().add(btn_logout);
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.Reset();
				Login window = new Login();
				window.frame.setVisible(true);
				frame.dispose();
			}
		});
		
		JButton btn_edit = new JButton("정보수정");
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edit_info window = new Edit_info();
				window.frame.setVisible(true);
			}
		});
		btn_edit.setBounds(658, 360, 89, 23);
		frame.getContentPane().add(btn_edit);
		
		JButton btn_write = new JButton("이력서 작성/채용 게시글 작성");
		btn_write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		if(Main.mode == "개인") {
			btn_write.setText("이력서 작성");
			btn_write.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Resume window = new Resume();
					window.frame.setVisible(true);
				}
			});
		}
		else {
			btn_write.setText("채용 게시글/설명회 작성");
			btn_write.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Upload_recruit window = new Upload_recruit();
					window.setVisible(true);
				}
			});
		}
		
		btn_write.setBounds(557, 393, 190, 23);
		frame.getContentPane().add(btn_write);
		
		JButton btn_resume_search = new JButton("이력서 조회");
		btn_resume_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resume_inquiry window = new Resume_inquiry();
				window.setVisible(true);
			}
		});
		btn_resume_search.setBounds(557, 426, 190, 23);
		frame.getContentPane().add(btn_resume_search);
		
		lbl_post = new JLabel("채용 게시글");
		lbl_post.setBounds(12, 179, 474, 15);
		frame.getContentPane().add(lbl_post);
		
		lbl_briefing = new JLabel("채용 설명회");
		lbl_briefing.setBounds(12, 481, 474, 15);
		frame.getContentPane().add(lbl_briefing);
		
		btn_inquiry_1 = new JButton("New button");
		btn_inquiry_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenCompany(Recruit_List[up_page][0].Post_number);
			}
		});
		btn_inquiry_1.setBounds(51, 204, 112, 69);
		frame.getContentPane().add(btn_inquiry_1);
		
		btn_inquiry_2 = new JButton("New button");
		btn_inquiry_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenCompany(Recruit_List[up_page][1].Post_number);
			}
		});
		btn_inquiry_2.setBounds(214, 204, 112, 69);
		frame.getContentPane().add(btn_inquiry_2);
		
		btn_inquiry_3 = new JButton("New button");
		btn_inquiry_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenCompany(Recruit_List[up_page][2].Post_number);
			}
		});
		btn_inquiry_3.setBounds(374, 204, 112, 69);
		frame.getContentPane().add(btn_inquiry_3);
		
		btn_inquiry_4 = new JButton("New button");
		btn_inquiry_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenCompany(Recruit_List[up_page][3].Post_number);
			}
		});
		btn_inquiry_4.setBounds(51, 315, 112, 69);
		frame.getContentPane().add(btn_inquiry_4);
		
		btn_inquiry_5 = new JButton("New button");
		btn_inquiry_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenCompany(Recruit_List[up_page][4].Post_number);
			}
		});
		btn_inquiry_5.setBounds(214, 315, 112, 69);
		frame.getContentPane().add(btn_inquiry_5);
		
		btn_inquiry_6 = new JButton("New button");
		btn_inquiry_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenCompany(Recruit_List[up_page][5].Post_number);
			}
		});
		btn_inquiry_6.setBounds(374, 315, 112, 69);
		frame.getContentPane().add(btn_inquiry_6);
		
		btn_briefing_1 = new JButton("New button");
		btn_briefing_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenBriefing(Briefing_List[down_page][0].Company,Briefing_List[down_page][0].Name);
			}
		});
		btn_briefing_1.setBounds(51, 506, 112, 69);
		frame.getContentPane().add(btn_briefing_1);
		
		btn_briefing_2 = new JButton("New button");
		btn_briefing_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenBriefing(Briefing_List[down_page][1].Company,Briefing_List[down_page][1].Name);
			}
		});
		btn_briefing_2.setBounds(214, 506, 112, 69);
		frame.getContentPane().add(btn_briefing_2);
		
		btn_briefing_3 = new JButton("New button");
		btn_briefing_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenBriefing(Briefing_List[down_page][2].Company,Briefing_List[down_page][2].Name);
			}
		});
		btn_briefing_3.setBounds(374, 506, 112, 69);
		frame.getContentPane().add(btn_briefing_3);
		
		btn_briefing_4 = new JButton("New button");
		btn_briefing_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenBriefing(Briefing_List[down_page][3].Company,Briefing_List[down_page][3].Name);
			}
		});
		btn_briefing_4.setBounds(51, 593, 112, 69);
		frame.getContentPane().add(btn_briefing_4);
		
		btn_briefing_5 = new JButton("New button");
		btn_briefing_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenBriefing(Briefing_List[down_page][4].Company,Briefing_List[down_page][4].Name);
			}
		});
		btn_briefing_5.setBounds(214, 593, 112, 69);
		frame.getContentPane().add(btn_briefing_5);
		
		btn_briefing_6 = new JButton("New button");
		btn_briefing_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenBriefing(Briefing_List[down_page][5].Company,Briefing_List[down_page][5].Name);
			}
		});
		btn_briefing_6.setBounds(374, 593, 112, 69);
		frame.getContentPane().add(btn_briefing_6);
		
		lbl_inquiry_1 = new JLabel("공고");
		lbl_inquiry_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_1.setBounds(51, 275, 112, 15);
		frame.getContentPane().add(lbl_inquiry_1);
		
		lbl_inquiry_2 = new JLabel("공고");
		lbl_inquiry_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_2.setBounds(214, 275, 112, 15);
		frame.getContentPane().add(lbl_inquiry_2);
		
		lbl_inquiry_3 = new JLabel("공고");
		lbl_inquiry_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_3.setBounds(374, 275, 112, 15);
		frame.getContentPane().add(lbl_inquiry_3);
		
		lbl_inquiry_4 = new JLabel("공고");
		lbl_inquiry_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_4.setBounds(51, 384, 112, 15);
		frame.getContentPane().add(lbl_inquiry_4);
		
		lbl_inquiry_5 = new JLabel("공고");
		lbl_inquiry_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_5.setBounds(214, 384, 112, 15);
		frame.getContentPane().add(lbl_inquiry_5);
		
		lbl_inquiry_6 = new JLabel("공고");
		lbl_inquiry_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_6.setBounds(374, 384, 112, 15);
		frame.getContentPane().add(lbl_inquiry_6);
		
		lbl_Rate_1 = new JLabel("경쟁률");
		lbl_Rate_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Rate_1.setBounds(51, 290, 112, 15);
		frame.getContentPane().add(lbl_Rate_1);
		
		lbl_Rate_2 = new JLabel("경쟁률");
		lbl_Rate_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Rate_2.setBounds(214, 290, 112, 15);
		frame.getContentPane().add(lbl_Rate_2);
		
		lbl_Rate_3 = new JLabel("경쟁률");
		lbl_Rate_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Rate_3.setBounds(374, 290, 112, 15);
		frame.getContentPane().add(lbl_Rate_3);
		
		lbl_Rate_6 = new JLabel("경쟁률");
		lbl_Rate_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Rate_6.setBounds(374, 397, 112, 15);
		frame.getContentPane().add(lbl_Rate_6);
		
		lbl_Rate_5 = new JLabel("경쟁률");
		lbl_Rate_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Rate_5.setBounds(214, 397, 112, 15);
		frame.getContentPane().add(lbl_Rate_5);
		
		lbl_Rate_4 = new JLabel("경쟁률");
		lbl_Rate_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Rate_4.setBounds(51, 397, 112, 15);
		frame.getContentPane().add(lbl_Rate_4);
		
		lbl_briefing_1 = new JLabel("설명회");
		lbl_briefing_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_1.setBounds(51, 577, 112, 15);
		frame.getContentPane().add(lbl_briefing_1);
		
		lbl_briefing_2 = new JLabel("설명회");
		lbl_briefing_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_2.setBounds(214, 577, 112, 15);
		frame.getContentPane().add(lbl_briefing_2);
		
		lbl_briefing_3 = new JLabel("설명회");
		lbl_briefing_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_3.setBounds(374, 577, 112, 15);
		frame.getContentPane().add(lbl_briefing_3);
		
		lbl_briefing_6 = new JLabel("설명회");
		lbl_briefing_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_6.setBounds(374, 668, 112, 15);
		frame.getContentPane().add(lbl_briefing_6);
		
		lbl_briefing_4 = new JLabel("설명회");
		lbl_briefing_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_4.setBounds(51, 668, 112, 15);
		frame.getContentPane().add(lbl_briefing_4);
		
		lbl_briefing_5 = new JLabel("설명회");
		lbl_briefing_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_5.setBounds(214, 668, 112, 15);
		frame.getContentPane().add(lbl_briefing_5);
		
		JLabel lbl_point = new JLabel("포인트 : ");
		lbl_point.setBounds(557, 230, 190, 15);
		frame.getContentPane().add(lbl_point);
		
		JLabel lbl_Count = new JLabel("지원 받은 개수");
		lbl_Count.setBounds(557, 300, 190, 15);
		frame.getContentPane().add(lbl_Count);
		
		btn_previous_1 = new JButton("<");
		btn_previous_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_next_1.setEnabled(true);
				if(up_page != 0) {
					up_page--;
				}
				recruit_load_page(up_page);
				if(up_page == 0) {
					btn_previous_1.setEnabled(false);
				}
			}
		});
		btn_previous_1.setEnabled(false);
		btn_previous_1.setBounds(180, 437, 42, 23);
		frame.getContentPane().add(btn_previous_1);
		
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("HOME.png"));
		btn_home_1 = new JButton(icon);
		btn_home_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_previous_1.setEnabled(false);
				btn_next_1.setEnabled(true);
				up_page = 0;
				recruit_load_page(up_page);
			}
		});
		btn_home_1.setBounds(234, 437, 69, 23);
		frame.getContentPane().add(btn_home_1);
		
		btn_next_1 = new JButton(">");
		btn_next_1.setBounds(315, 437, 42, 23);
		frame.getContentPane().add(btn_next_1);
		btn_next_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_previous_1.setEnabled(true);
				if(up_page != max_recruit_page-2) {
					up_page++;
				}
				else {
					btn_next_1.setEnabled(false);
				}
				recruit_load_page(up_page);
			}
		});
		
		btn_previous = new JButton("<");
		btn_previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_next.setEnabled(true);
				if(down_page != 0) {
					down_page--;
				}
				briefing_load_page(down_page);
				if(down_page == 0) {
					btn_previous.setEnabled(false);
				}
			}
		});
		btn_previous.setEnabled(false);
		btn_previous.setBounds(180, 697, 42, 23);
		frame.getContentPane().add(btn_previous);
		
		btn_home = new JButton(icon);
		btn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_previous.setEnabled(false);
				btn_next.setEnabled(true);
				down_page = 0;
				briefing_load_page(down_page);
			}
		});
		btn_home.setBounds(234, 697, 69, 23);
		frame.getContentPane().add(btn_home);
		
		btn_next = new JButton(">");
		btn_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_previous.setEnabled(true);
				if(down_page != max_briefing_page-2){
					down_page++;
				}
				else {
					btn_next.setEnabled(false);
				}
				briefing_load_page(down_page);
			}
		});
		btn_next.setBounds(315, 697, 42, 23);
		frame.getContentPane().add(btn_next);
		
		try {
			Main.DBConnection();
			Main.cstmt = Main.con.prepareCall("{CALL MAIN_FIND(?,?,?,?,?,?,?,?)}");
			// 1번: 모드, 2번: ID, 3번: 현재일, 4번: 출력_이름, 5번: 출력_포인트, 6번: 출력_게시글_개수, 7번: 제안_개수, 8번: 현재_게시글_개수
			Main.cstmt.setString(1, Main.mode);
			Main.cstmt.setString(2, Main.ID);
			java.sql.Date currentDate = new java.sql.Date(new Date().getTime());
			Main.cstmt.setDate(3, currentDate);
			
			Main.cstmt.registerOutParameter(4,Types.NVARCHAR);
			Main.cstmt.registerOutParameter(5,Types.NUMERIC);
			Main.cstmt.registerOutParameter(6,Types.NUMERIC);
			Main.cstmt.registerOutParameter(7,Types.NUMERIC);
			Main.cstmt.registerOutParameter(8,Types.NUMERIC);
			
			Main.cstmt.execute();
			lbl_name.setText(lbl_name.getText() + Main.cstmt.getString(4));
			lbl_point.setText(lbl_point.getText() + Main.cstmt.getInt(5));
			lbl_now.setText("현재 모집중인 공고 개수 : " + Main.cstmt.getInt(8)+ "개");
			
			if(Main.mode == "개인") {
				lbl_post_count.setText("작성한 이력서 개수: " + Main.cstmt.getInt(6)+ "개");
				lbl_Count.setText("제안 받은 포지션 개수: " + Main.cstmt.getInt(7)+ "개");
			}
			else {
				lbl_post_count.setText("작성한 채용 게시글 개수: " + Main.cstmt.getInt(6)+ "개");
				lbl_Count.setText("지원 받은 이력서 개수: " + Main.cstmt.getInt(7)+ "개") ;
			}
			
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "DB로부터 데이터 로드를 실패했습니다.","DB 접속 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
	
		try {
			Main.DBConnection();
			Main.stmt = Main.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		            ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT 기업명, 회원ID, 게시글_번호, 마감일, 경쟁률 FROM 채용_게시글, 기업회원 WHERE 마감일 > SYSDATE AND 채용_게시글.작성자ID = 기업회원.회원ID";
			Main.rs = Main.stmt.executeQuery(sql);
			
			Main.rs.last();
			if(Main.rs.getRow()%6 != 0) {
				max_recruit_page = Main.rs.getRow()/6+2;
			}
			else{
				max_recruit_page = Main.rs.getRow()/6+1;
			}
			Recruit_List = new Recruit_Information[max_recruit_page][6];
			Main.rs.beforeFirst();
			while(Main.rs.next()) {
				Recruit_List[recruit_page][recruit_button] = new Recruit_Information(Main.rs.getString(1),Main.rs.getString(2), Main.rs.getInt(3), Main.rs.getDate(4), Main.rs.getInt(5));
				recruit_button++;
				if(recruit_button == 6) {
					recruit_button = 0;
					recruit_page++;
				}
			}
			
			Main.stmt = Main.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		            ResultSet.CONCUR_UPDATABLE);
			sql = "SELECT 기업명, 설명회명, 회차, 일시 FROM 채용_설명회 WHERE 일시 > SYSDATE";
			Main.rs = Main.stmt.executeQuery(sql);
			
			Main.rs.last();
			if(Main.rs.getRow()%6 != 0) {
				max_briefing_page = Main.rs.getRow()/6+2;
			}
			else {
				max_briefing_page = Main.rs.getRow()/6+1;
			}
			Briefing_List = new Briefing_Information[max_briefing_page][6];
			Main.rs.beforeFirst();
			while(Main.rs.next()){
				Briefing_List[briefing_page][briefing_button] = new Briefing_Information(Main.rs.getString(1),Main.rs.getString(2), Main.rs.getInt(3), Main.rs.getDate(4));
				briefing_button++;
				if(briefing_button == 6) {
					briefing_button = 0;
					briefing_page++;
				}
			}
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "DB로부터 게시글 로드를 실패했습니다.", "DB 접속 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
		recruit_load_page(0);
		briefing_load_page(0);
	}
	
	private void recruit_load_page(int page) {
		lbl_post.setText("채용 게시글 (" + (page+1) + "/" + (max_recruit_page-1) + ")");
		for(int i=0;i<6;i++) {
			if(Recruit_List[page][i] == null) {
				if(page == 0) {
					btn_previous_1.setVisible(false);
					btn_next_1.setVisible(false);
					btn_home_1.setVisible(false);
				}
				switch(i){
				case 0:
					btn_inquiry_1.setVisible(false);
					lbl_inquiry_1.setVisible(false);
					lbl_Rate_1.setVisible(false);
					break;
				case 1:
					btn_inquiry_2.setVisible(false);
					lbl_inquiry_2.setVisible(false);
					lbl_Rate_2.setVisible(false);
					break;
				case 2:
					btn_inquiry_3.setVisible(false);
					lbl_inquiry_3.setVisible(false);
					lbl_Rate_3.setVisible(false);
					break;
				case 3:
					btn_inquiry_4.setVisible(false);
					lbl_inquiry_4.setVisible(false);
					lbl_Rate_4.setVisible(false);
					break;
				case 4:
					btn_inquiry_5.setVisible(false);
					lbl_inquiry_5.setVisible(false);
					lbl_Rate_5.setVisible(false);
					break;
				case 5:
					btn_inquiry_6.setVisible(false);
					lbl_inquiry_6.setVisible(false);
					lbl_Rate_6.setVisible(false);
					break;
				}
			}
			else {
				Date modifiedDateA = subtractDays(Recruit_List[page][i].Deadline,3);
				LocalDate currentDate = LocalDate.now();
				switch(i){
				case 0:
					btn_inquiry_1.setVisible(true);
					lbl_inquiry_1.setVisible(true);
					lbl_Rate_1.setVisible(true);
					btn_inquiry_1.setText(Recruit_List[page][i].Company);
					lbl_inquiry_1.setText("마감일: " + Recruit_List[page][i].Deadline.toString());
					if(Recruit_List[page][i].Rate<= 50) {
						lbl_Rate_1.setForeground(Color.BLUE);
					}
					else if(Recruit_List[page][i].Rate <= 100) {
						lbl_Rate_1.setForeground(Color.GREEN);
					}
					else {
						lbl_Rate_1.setForeground(Color.RED);
					}
					modifiedDateA = subtractDays(Recruit_List[page][i].Deadline,3);
					currentDate = LocalDate.now();
					if(!modifiedDateA.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(currentDate)) {
						lbl_inquiry_1.setForeground(Color.RED);
					}
					lbl_Rate_1.setText("경쟁률: " + Recruit_List[page][i].Rate + "%");
					break;
				case 1:
					btn_inquiry_2.setVisible(true);
					lbl_inquiry_2.setVisible(true);
					lbl_Rate_2.setVisible(true);
					btn_inquiry_2.setText(Recruit_List[page][i].Company);
					lbl_inquiry_2.setText("마감일: " + Recruit_List[page][i].Deadline.toString());
					lbl_Rate_2.setText("경쟁률: " + Recruit_List[page][i].Rate + "%");
					if(Recruit_List[page][i].Rate<= 50) {
						lbl_Rate_2.setForeground(Color.BLUE);
					}
					else if(Recruit_List[page][i].Rate <= 100) {
						lbl_Rate_2.setForeground(Color.GREEN);
					}
					else {
						lbl_Rate_2.setForeground(Color.RED);
					}
					modifiedDateA = subtractDays(Recruit_List[page][i].Deadline,3);
					currentDate = LocalDate.now();
					if(!modifiedDateA.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(currentDate)) {
						lbl_inquiry_2.setForeground(Color.RED);
					}
					break;
				case 2:
					btn_inquiry_3.setVisible(true);
					lbl_inquiry_3.setVisible(true);
					lbl_Rate_3.setVisible(true);
					btn_inquiry_3.setText(Recruit_List[page][i].Company);
					lbl_inquiry_3.setText("마감일: " + Recruit_List[page][i].Deadline.toString());
					lbl_Rate_3.setText("경쟁률: " + Recruit_List[page][i].Rate + "%");
					if(Recruit_List[page][i].Rate<= 50) {
						lbl_Rate_3.setForeground(Color.BLUE);
					}
					else if(Recruit_List[page][i].Rate <= 100) {
						lbl_Rate_3.setForeground(Color.GREEN);
					}
					else {
						lbl_Rate_3.setForeground(Color.RED);
					}
					modifiedDateA = subtractDays(Recruit_List[page][i].Deadline,3);
					currentDate = LocalDate.now();
					if(!modifiedDateA.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(currentDate)) {
						lbl_inquiry_3.setForeground(Color.RED);
					}
					break;
				case 3:
					btn_inquiry_4.setVisible(true);
					lbl_inquiry_4.setVisible(true);
					lbl_Rate_4.setVisible(true);
					btn_inquiry_4.setText(Recruit_List[page][i].Company);
					lbl_inquiry_4.setText("마감일: " + Recruit_List[page][i].Deadline.toString());
					lbl_Rate_4.setText("경쟁률: " + Recruit_List[page][i].Rate + "%");
					if(Recruit_List[page][i].Rate<= 50) {
						lbl_Rate_4.setForeground(Color.BLUE);
					}
					else if(Recruit_List[page][i].Rate <= 100) {
						lbl_Rate_4.setForeground(Color.GREEN);
					}
					else {
						lbl_Rate_4.setForeground(Color.RED);
					}
					modifiedDateA = subtractDays(Recruit_List[page][i].Deadline,3);
					currentDate = LocalDate.now();
					if(!modifiedDateA.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(currentDate)) {
						lbl_inquiry_4.setForeground(Color.RED);
					}
					break;
				case 4:
					btn_inquiry_5.setVisible(true);
					lbl_inquiry_5.setVisible(true);
					lbl_Rate_5.setVisible(true);
					btn_inquiry_5.setText(Recruit_List[page][i].Company);
					lbl_inquiry_5.setText("마감일: " + Recruit_List[page][i].Deadline.toString());
					lbl_Rate_5.setText("경쟁률: " + Recruit_List[page][i].Rate + "%");
					if(Recruit_List[page][i].Rate<= 50) {
						lbl_Rate_5.setForeground(Color.BLUE);
					}
					else if(Recruit_List[page][i].Rate <= 100) {
						lbl_Rate_5.setForeground(Color.GREEN);
					}
					else {
						lbl_Rate_5.setForeground(Color.RED);
					}
					modifiedDateA = subtractDays(Recruit_List[page][i].Deadline,3);
					currentDate = LocalDate.now();
					if(!modifiedDateA.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(currentDate)) {
						lbl_inquiry_5.setForeground(Color.RED);
					}
					break;
				case 5:
					btn_inquiry_6.setVisible(true);
					lbl_inquiry_6.setVisible(true);
					lbl_Rate_6.setVisible(true);
					btn_inquiry_6.setText(Recruit_List[page][i].Company);
					lbl_inquiry_6.setText("마감일: " + Recruit_List[page][i].Deadline.toString());
					lbl_Rate_6.setText("경쟁률: " + Recruit_List[page][i].Rate + "%");
					if(Recruit_List[page][i].Rate<= 50) {
						lbl_Rate_6.setForeground(Color.BLUE);
					}
					else if(Recruit_List[page][i].Rate <= 100) {
						lbl_Rate_6.setForeground(Color.GREEN);
					}
					else {
						lbl_Rate_6.setForeground(Color.RED);
					}
					modifiedDateA = subtractDays(Recruit_List[page][i].Deadline,3);
					currentDate = LocalDate.now();
					if(!modifiedDateA.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(currentDate)) {
						lbl_inquiry_6.setForeground(Color.RED);
					}
					break;
				}
			}
		}
	}
	
	private void briefing_load_page(int page) {
		lbl_briefing.setText("채용 설명회 (" + (page+1) + "/" + (max_briefing_page-1) + ")");
		for(int i=0;i<6;i++) {
			if(Briefing_List[page][i] == null) {
				if(page == 0) {
					btn_previous.setVisible(false);
					btn_next.setVisible(false);
					btn_home.setVisible(false);
				}
				switch(i){
				case 0:
					btn_briefing_1.setVisible(false);
					lbl_briefing_1.setVisible(false);
					break;
				case 1:
					btn_briefing_2.setVisible(false);
					lbl_briefing_2.setVisible(false);
					break;
				case 2:
					btn_briefing_3.setVisible(false);
					lbl_briefing_3.setVisible(false);
					break;
				case 3:
					btn_briefing_4.setVisible(false);
					lbl_briefing_4.setVisible(false);
					break;
				case 4:
					btn_briefing_5.setVisible(false);
					lbl_briefing_5.setVisible(false);
					break;
				case 5:
					btn_briefing_6.setVisible(false);
					lbl_briefing_6.setVisible(false);
					break;
				}
			}
			else {
				switch(i){
				case 0:
					btn_briefing_1.setVisible(true);
					lbl_briefing_1.setVisible(true);
					btn_briefing_1.setText(Briefing_List[page][i].Company);
					lbl_briefing_1.setText("일시: " + Briefing_List[page][i].open_date.toString());
					break;
				case 1:
					btn_briefing_2.setVisible(true);
					lbl_briefing_2.setVisible(true);
					btn_briefing_2.setText(Briefing_List[page][i].Company);
					lbl_briefing_2.setText("일시: " + Briefing_List[page][i].open_date.toString());
					break;
				case 2:
					btn_briefing_3.setVisible(true);
					lbl_briefing_3.setVisible(true);
					btn_briefing_3.setText(Briefing_List[page][i].Company);
					lbl_briefing_3.setText("일시: " + Briefing_List[page][i].open_date.toString());
					break;
				case 3:
					btn_briefing_4.setVisible(true);
					lbl_briefing_4.setVisible(true);
					btn_briefing_4.setText(Briefing_List[page][i].Company);
					lbl_briefing_4.setText("일시: " + Briefing_List[page][i].open_date.toString());
					break;
				case 4:
					btn_briefing_5.setVisible(true);
					lbl_briefing_5.setVisible(true);
					btn_briefing_5.setText(Briefing_List[page][i].Company);
					lbl_briefing_5.setText("일시: " + Briefing_List[page][i].open_date.toString());
					break;
				case 5:
					btn_briefing_6.setVisible(true);
					lbl_briefing_6.setVisible(true);
					btn_briefing_6.setText(Briefing_List[page][i].Company);
					lbl_briefing_6.setText("일시: " + Briefing_List[page][i].open_date.toString());
					break;
				}
			}
		}
	}
	
	private void OpenCompany(int Post_ID) {
		Job_posting window = new Job_posting(Post_ID);
		window.frame.setVisible(true);
	}
	
	private void OpenBriefing(String Company_name, String Briefing_name) {
		Job_description window = new Job_description(Company_name,Briefing_name);
		window.frame.setVisible(true);
	}
	
	private Date subtractDays(Date date,int days) {
		long milliseconds = date.getTime() - (days*24*60*60*1000L);
		return new Date(milliseconds);
	}
}
