package DB_Project;


import java.util.Date;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class Mainpage {

	public JFrame frame;
	private JTextField txt_business;

	public Mainpage() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 806, 617);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_logo = new JLabel("New label");
		lbl_logo.setForeground(Color.BLACK);
		lbl_logo.setBackground(Color.WHITE);
		lbl_logo.setBounds(12, 10, 766, 109);
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
				Corporate_inquiry window = new Corporate_inquiry(txt_business.getText());
			}
		});
		btn_search.setBounds(343, 125, 78, 23);
		frame.getContentPane().add(btn_search);
		
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
		btn_market.setBounds(557, 151, 190, 23);
		frame.getContentPane().add(btn_market);
		
		JLabel lbl_name = new JLabel("이름 : ");
		lbl_name.setBounds(557, 205, 190, 15);
		frame.getContentPane().add(lbl_name);
		
		JLabel lbl_ID = new JLabel("아이디 : ");
		lbl_ID.setText(lbl_ID.getText() + Main.ID);
		lbl_ID.setBounds(557, 250, 190, 15);
		frame.getContentPane().add(lbl_ID);
		
		JLabel lbl_post_count = new JLabel("작성한 게시글 개수  : ");
		lbl_post_count.setForeground(Color.BLACK);
		lbl_post_count.setBounds(557, 275, 190, 15);
		frame.getContentPane().add(lbl_post_count);
		
		JButton btn_logout = new JButton("로그아웃");
		btn_logout.setBounds(557, 338, 89, 23);
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
		btn_edit.setBounds(658, 338, 89, 23);
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
		
		btn_write.setBounds(557, 371, 190, 23);
		frame.getContentPane().add(btn_write);
		
		JButton btn_resume_search = new JButton("이력서 조회");
		btn_resume_search.setBounds(557, 404, 190, 23);
		frame.getContentPane().add(btn_resume_search);
		
		JButton btn_upload_business = new JButton("기업 등록");
		btn_upload_business.setForeground(Color.BLACK);
		btn_upload_business.setBounds(557, 437, 190, 23);
		frame.getContentPane().add(btn_upload_business);
		
		JLabel lbl_post = new JLabel("채용 게시글");
		lbl_post.setBounds(12, 173, 78, 15);
		frame.getContentPane().add(lbl_post);
		
		JLabel lbl_briefing = new JLabel("채용 설명회");
		lbl_briefing.setBounds(12, 366, 78, 15);
		frame.getContentPane().add(lbl_briefing);
		
		JButton btn_inquiry_1 = new JButton("New button");
		btn_inquiry_1.setBounds(51, 198, 112, 69);
		frame.getContentPane().add(btn_inquiry_1);
		
		JButton btn_inquiry_2 = new JButton("New button");
		btn_inquiry_2.setBounds(214, 198, 112, 69);
		frame.getContentPane().add(btn_inquiry_2);
		
		JButton btn_inquiry_3 = new JButton("New button");
		btn_inquiry_3.setBounds(374, 198, 112, 69);
		frame.getContentPane().add(btn_inquiry_3);
		
		JButton btn_inquiry_4 = new JButton("New button");
		btn_inquiry_4.setBounds(51, 284, 112, 69);
		frame.getContentPane().add(btn_inquiry_4);
		
		JButton btn_inquiry_5 = new JButton("New button");
		btn_inquiry_5.setBounds(214, 284, 112, 69);
		frame.getContentPane().add(btn_inquiry_5);
		
		JButton btn_inquiry_6 = new JButton("New button");
		btn_inquiry_6.setBounds(374, 284, 112, 69);
		frame.getContentPane().add(btn_inquiry_6);
		
		JButton btn_briefing_1 = new JButton("New button");
		btn_briefing_1.setBounds(51, 391, 112, 69);
		frame.getContentPane().add(btn_briefing_1);
		
		JButton btn_briefing_2 = new JButton("New button");
		btn_briefing_2.setBounds(214, 391, 112, 69);
		frame.getContentPane().add(btn_briefing_2);
		
		JButton btn_briefing_3 = new JButton("New button");
		btn_briefing_3.setBounds(374, 391, 112, 69);
		frame.getContentPane().add(btn_briefing_3);
		
		JButton btn_briefing_4 = new JButton("New button");
		btn_briefing_4.setBounds(51, 478, 112, 69);
		frame.getContentPane().add(btn_briefing_4);
		
		JButton btn_briefing_5 = new JButton("New button");
		btn_briefing_5.setBounds(214, 478, 112, 69);
		frame.getContentPane().add(btn_briefing_5);
		
		JButton btn_briefing_6 = new JButton("New button");
		btn_briefing_6.setBounds(374, 478, 112, 69);
		frame.getContentPane().add(btn_briefing_6);
		
		JLabel lbl_inquiry_1 = new JLabel("공고");
		lbl_inquiry_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_1.setBounds(85, 269, 42, 15);
		frame.getContentPane().add(lbl_inquiry_1);
		
		JLabel lbl_inquiry_2 = new JLabel("공고");
		lbl_inquiry_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_2.setBounds(247, 269, 42, 15);
		frame.getContentPane().add(lbl_inquiry_2);
		
		JLabel lbl_inquiry_3 = new JLabel("공고");
		lbl_inquiry_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_3.setBounds(409, 269, 42, 15);
		frame.getContentPane().add(lbl_inquiry_3);
		
		JLabel lbl_inquiry_4 = new JLabel("공고");
		lbl_inquiry_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_4.setBounds(85, 353, 42, 15);
		frame.getContentPane().add(lbl_inquiry_4);
		
		JLabel lbl_inquiry_5 = new JLabel("공고");
		lbl_inquiry_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_5.setBounds(247, 353, 42, 15);
		frame.getContentPane().add(lbl_inquiry_5);
		
		JLabel lbl_inquiry_6 = new JLabel("공고");
		lbl_inquiry_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_inquiry_6.setBounds(409, 353, 42, 15);
		frame.getContentPane().add(lbl_inquiry_6);
		
		JLabel lbl_briefing_1 = new JLabel("설명회");
		lbl_briefing_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_1.setBounds(85, 462, 42, 15);
		frame.getContentPane().add(lbl_briefing_1);
		
		JLabel lbl_briefing_2 = new JLabel("설명회");
		lbl_briefing_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_2.setBounds(247, 462, 42, 15);
		frame.getContentPane().add(lbl_briefing_2);
		
		JLabel lbl_briefing_3 = new JLabel("설명회");
		lbl_briefing_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_3.setBounds(409, 462, 42, 15);
		frame.getContentPane().add(lbl_briefing_3);
		
		JLabel lbl_briefing_4 = new JLabel("설명회");
		lbl_briefing_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_4.setBounds(409, 553, 42, 15);
		frame.getContentPane().add(lbl_briefing_4);
		
		JLabel lbl_briefing_5 = new JLabel("설명회");
		lbl_briefing_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_5.setBounds(85, 553, 42, 15);
		frame.getContentPane().add(lbl_briefing_5);
		
		JLabel lbl_briefing_6 = new JLabel("설명회");
		lbl_briefing_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_briefing_6.setBounds(247, 553, 42, 15);
		frame.getContentPane().add(lbl_briefing_6);
		
		JLabel lbl_point = new JLabel("포인트 : ");
		lbl_point.setBounds(557, 225, 190, 15);
		frame.getContentPane().add(lbl_point);
		
		JLabel lbl_Count = new JLabel("지원 받은 이력서 개수, 포지션 개수");
		lbl_Count.setBounds(557, 300, 190, 15);
		frame.getContentPane().add(lbl_Count);
		
		try {
			Main.DBConnection();
			Main.cstmt = Main.con.prepareCall("{CALL MAIN_FIND(?,?,?,?,?,?,?,?)}");
			// 1번: 모드, 2번: ID, 3번: 현재일, 4번: 출력_이름, 5번: 출력_포인트, 6번: 출력_게시글_개수, 7번: 제안_개수, 8번: 현재_게시글_개수
			Main.cstmt.setString(1, Main.mode);
			Main.cstmt.setString(2, Main.ID);
			java.sql.Date currentDate = new java.sql.Date(new Date().getTime());
			Main.cstmt.setDate(3, currentDate);
			
			Main.cstmt.registerOutParameter(4,Types.NCHAR);
			Main.cstmt.registerOutParameter(5,Types.NUMERIC);
			Main.cstmt.registerOutParameter(6,Types.NUMERIC);
			Main.cstmt.registerOutParameter(7,Types.NUMERIC);
			Main.cstmt.registerOutParameter(8,Types.NUMERIC);
			
			Main.cstmt.execute();
			lbl_name.setText(lbl_name.getText() + Main.cstmt.getString(4));
			lbl_point.setText(lbl_point.getText() + Main.cstmt.getInt(5));
			lbl_now.setText("현재 모집중인 공고 개수 : " + Main.cstmt.getInt(8));
			
			if(Main.mode == "개인") {
				lbl_post_count.setText("작성한 이력서 개수: " + Main.cstmt.getInt(6));
				lbl_Count.setText("제안 받은 포지션 개수: " + Main.cstmt.getInt(7));
			}
			else {
				lbl_post_count.setText("작성한 채용 게시글 개수: " + Main.cstmt.getInt(6));
				lbl_Count.setText("지원 받은 이력서 개수: " + Main.cstmt.getInt(7));
			}
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "DB로부터 데이터 로드를 실패했습니다.","DB 접속 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
	}
}
