package DB_Project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.util.*;
import java.util.ArrayList;

import java.sql.*;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Resume_inquiry extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbl_resume;
	private JTextArea txtArea_resume;
	
	public Resume_inquiry() {
		setTitle("이력서 조회");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 760, 480);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		lbl_resume = new JLabel("내가 작성한 이력서");
		lbl_resume.setFont(new Font("굴림", Font.BOLD, 15));
		lbl_resume.setBounds(20, 15, 240, 30);
		contentPane.add(lbl_resume);

		txtArea_resume = new JTextArea();
		txtArea_resume.setLineWrap(true);
		txtArea_resume.setFont(new Font("굴림", Font.BOLD, 14));
		txtArea_resume.setEditable(false);
		txtArea_resume.setBounds(20, 60, 700, 360);
		contentPane.add(txtArea_resume);

		JScrollPane scrollPane = new JScrollPane(txtArea_resume);
		scrollPane.setBounds(20, 60, 700, 360);
		
		contentPane.add(scrollPane);

		// 이력서 조회
		// 개인 회원일 경우 본인이 작성한 이력서 조회
		// 기업 회원일 경우 본 기업에 들어온 이력서 조회
		
		try {
			Main.DBConnection();
			
			// 개인 회원일 경우 본인이 작성한 이력서 조회
			if(Main.mode.equals("개인")){
				
				// 이력서 조회
				DBinquiry(Main.ID);
				txtArea_resume.append("\n================================================");
			}
			// 기업 회원일 경우 본 기업에 들어온 이력서들 조회
			else {
				
				// 본 기업 조회
				
				String sql_business = "SELECT 기업명 FROM 기업회원 WHERE 회원ID = ?";
				
				Main.pstmt = Main.con.prepareStatement(sql_business);
				Main.pstmt.setString(1, Main.ID);
				Main.rs = Main.pstmt.executeQuery();
				
				String businessName = "";
				
				if(Main.rs.next()) {
					businessName = Main.rs.getString(1);
				} else {
					JOptionPane.showMessageDialog(null, "기업을 찾을 수 없습니다", "조회 실패", JOptionPane.ERROR_MESSAGE);
				}

				lbl_resume.setText("본 기업(" + businessName + ")에 지원한 이력서");
				
				// [기업이름]에 등록한 회원ID 조회
				
				String sql_business_id = "SELECT 회원ID FROM 개인회원_BUSINESS WHERE 기업_이름 = ?";
				
				Main.pstmt = Main.con.prepareStatement(sql_business_id);
				Main.pstmt.setString(1, businessName);
				Main.rs = Main.pstmt.executeQuery();
				
				ArrayList<String> resumeIDs = new ArrayList<>();
				
				while(Main.rs.next()) {
					String tempID = Main.rs.getString(1);
					resumeIDs.add(tempID);
				}
				
				// [회원ID]가 작성한 이력서 조회 및 출력

				for (String ID : resumeIDs) {
					DBinquiry(ID);
				}
				txtArea_resume.append("\n================================================");
			}
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "이력서 조회 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
		
		// 닫기 버튼
		
		JButton btn_close = new JButton("닫기");
		btn_close.setBounds(645, 15, 60, 30);
		contentPane.add(btn_close);
		
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				dispose();
			}
		});
		
	}
	
	// ID 받아서 이력서 조회
	
	public void DBinquiry(String ID) {
		try {
			Main.DBConnection();
			
			// 0. 이름
			
			String sql_name = "SELECT 이름 FROM 개인회원 WHERE 회원ID = ?";
			
			Main.pstmt = Main.con.prepareStatement(sql_name);
			Main.pstmt.setString(1, ID);
			Main.rs = Main.pstmt.executeQuery();
			
			String memberName = "";
			
			if(Main.rs.next()) {
				memberName = Main.rs.getString(1);
			}
			else {
				JOptionPane.showMessageDialog(null, "ERROR", "이력서 조회 실패", JOptionPane.ERROR_MESSAGE);
			}
			
			// 1. 이력서 조회
			
			String sql_resume = "SELECT * FROM 이력서 WHERE 작성자ID = ?";
			
			Main.pstmt = Main.con.prepareStatement(sql_resume);
			Main.pstmt.setString(1, ID);
			Main.rs = Main.pstmt.executeQuery();
			
			String resumeName = "", edu = "", writtenDate = "";
			int toeic = 0, overseasExperience = 0;
			
			if(Main.rs.next()) {
				resumeName = Main.rs.getString("이력서명");
				edu = Main.rs.getString("학력");
				toeic = Main.rs.getInt("토익");
				overseasExperience = Main.rs.getInt("해외_경험_횟수");
				writtenDate = Main.rs.getString("작성일자");
			}
			else {
				txtArea_resume.setText("해당 ID의 이력서를 찾을 수 없습니다.");
			}
			
			// 2. 이력서_경력 조회
			
			String sql_resume_carrer = "SELECT * FROM 이력서_경력 WHERE 회원ID = ?";
			
			Main.pstmt = Main.con.prepareStatement(sql_resume_carrer);
			Main.pstmt.setString(1, ID);
			Main.rs = Main.pstmt.executeQuery();
			
			String carrerName = "없음", carrerPosition = "";
			int carrerYear = 0, carrerIncome = 0;
			
			if(Main.rs.next()) {
				carrerName = Main.rs.getString("경력_위치");
				carrerYear = Main.rs.getInt("년수");
				carrerPosition = Main.rs.getString("직급");
				carrerIncome = Main.rs.getInt("연봉");
			}
			
			// 3. 이력서_자격증 조회
			
			String sql_resume_cirtifi = "SELECT 자격증명 FROM 이력서_자격증 WHERE 이력서_작성자 = ?";
			
			// 자격증명
			ArrayList<String> cirtifinames = new ArrayList<>();
			
			Main.pstmt = Main.con.prepareStatement(sql_resume_cirtifi);
			Main.pstmt.setString(1, ID);
			Main.rs = Main.pstmt.executeQuery();
			
			while(Main.rs.next()) {
				String temp = Main.rs.getString("자격증명");
				cirtifinames.add(temp);
			}
			
			StringBuilder resumeInfo = new StringBuilder();
			resumeInfo.append("================================================\n")
				.append("\n이름(아이디) : ").append(memberName).append("(").append(ID).append(")\n")
				.append("이력서명 : ").append(resumeName).append("\n\n")
				.append("[ 학력 및 토익 점수 ]\n학력 : ").append(edu).append("\n")
				.append("토익 : ").append(toeic).append("\n")
				.append("해외 경험 횟수 : ").append(overseasExperience).append("\n\n")
				.append("[ 경력 ]\n경력 위치 : ").append(carrerName).append("\n")
				.append("년수 : ").append(carrerYear).append("\n")
				.append("직급 : ").append(carrerPosition).append("\n")
				.append("연봉 : ").append(carrerIncome).append("\n\n")
				.append("[ 자격증 ]\n자격증명 : ");
			
			int Count = cirtifinames.size();
					
			for (int i = 0; i < Count; i++) {
				String temp = cirtifinames.get(i);
				resumeInfo.append(temp);
				if ( i < Count - 1)
					resumeInfo.append(", ");
			}
			
			resumeInfo.append("\n\n작성일자 : ").append(writtenDate).append("\n\n");
				
			
			txtArea_resume.append(resumeInfo.toString());	
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "이력서 조회 실패", JOptionPane.ERROR_MESSAGE);
		}
		
		finally {
			Main.DBClose();
		}
	}
}
