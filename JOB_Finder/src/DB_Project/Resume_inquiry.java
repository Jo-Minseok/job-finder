package DB_Project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.util.*;
import java.util.ArrayList;

import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Resume_inquiry extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbl_resume;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	private JButton btn_prev;
	private JButton btn_next;
	private JButton btn_delete;
	
	// 이력서에 정보 할당할 변수
	private String memberName = "", birthDate = "", education = "",
			phoneNumber = "", carrerName = "없음", carrerPosition = "";
	private int toeic = 0, overseasExperience = 0, carrerYear = 0, carrerIncome = 0;
	
	// (개인회원) 조회된 이력서명들
	private ArrayList<String> resumeNames = new ArrayList<>();

	// (개인회원) 현재 이력서명 인덱스
	private int Name_Index = 0;
	
	// 자격증명
	private ArrayList<String> cirtifinames = new ArrayList<>();
	private StringBuilder cirtifiStrings = new StringBuilder();
	
	// (기업회원) 채용 게시글 번호
	private int postNum = 0;
	
	// (기업회원) 조회된 지원자 아이디들과 이력서명
	private ArrayList<String> applicantIDs = new ArrayList<>();
	private ArrayList<String> applicantNames = new ArrayList<>();
	
	// (기업회원) 현재 아이디 인덱스
	private int ID_Index = 0;
	
	public Resume_inquiry() {
		setTitle("이력서 조회");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		lbl_resume = new JLabel("내가 작성한 이력서");
		lbl_resume.setFont(new Font("굴림", Font.BOLD, 16));
		lbl_resume.setBounds(20, 15, 500, 30);
		contentPane.add(lbl_resume);
		
		// 이력서 조회
		// 개인 회원일 경우 본인이 작성한 이력서 조회
		// 기업 회원일 경우 본 기업에 들어온 이력서 조회
		
		try {
			Main.DBConnection();
			
			// 개인 회원일 경우 본인이 작성한 이력서 조회
			if(Main.mode.equals("개인")){
				// 이력서 조회
				String sql_resume_1 = "SELECT 이력서명 FROM 이력서 WHERE 작성자ID = ?";
				
				Main.pstmt = Main.con.prepareStatement(sql_resume_1);
				Main.pstmt.setString(1, Main.ID);
				Main.rs = Main.pstmt.executeQuery();
				
				// 이력서명들 모두 조회
				
				resumeNames.clear(); // 리스트 초기화
				
				while(Main.rs.next()) {
					String temp_resumeName = Main.rs.getString(1);
					resumeNames.add(temp_resumeName);
					// 이력서명 중복 제외
					if(!resumeNames.contains(temp_resumeName))
						resumeNames.add(temp_resumeName);
				}
				
				lbl_resume.setText("내가 작성한 이력서 ( " + (Name_Index+1) + " / " + resumeNames.size()+ " )");
				
				DBinquiry(Main.ID, resumeNames.get(Name_Index));
				
				}
			// 기업 회원일 경우 내 채용 게시글에 지원한 이력서들 조회
			else {
				
				// 내 아이디로 작성한 채용 게시글(번호) 조회
				
				String sql_post_num = "SELECT 게시글_번호 FROM 채용_게시글 WHERE 작성자ID = ?";
				
				Main.pstmt = Main.con.prepareStatement(sql_post_num);
				Main.pstmt.setString(1, Main.ID);
				Main.rs = Main.pstmt.executeQuery();
				
				if(Main.rs.next()) {
					postNum = Main.rs.getInt(1);
				} else {
					JOptionPane.showMessageDialog(null, "게시글을 찾을 수 없습니다", "조회 실패", JOptionPane.ERROR_MESSAGE);
				}

				// 내 게시글의 번호에 지원한 회원ID, 이력서명 조회
				
				String sql_post_id = "SELECT * FROM 지원 WHERE 게시글_번호 = ?";
				
				Main.pstmt = Main.con.prepareStatement(sql_post_id);
				Main.pstmt.setInt(1, postNum);
				Main.rs = Main.pstmt.executeQuery();
				
				applicantIDs.clear();
				applicantNames.clear(); // 리스트 초기화
				
				while(Main.rs.next()) {
					String temp_ID = Main.rs.getString("지원자");
					String temp_Name = Main.rs.getString("이력서명");
					applicantIDs.add(temp_ID);
					applicantNames.add(temp_Name);
				}
				
				lbl_resume.setText("내 채용 게시글에 지원한 이력서 ( " + (ID_Index+1) + " / " + applicantIDs.size() + " )");
				
				DBinquiry(applicantIDs.get(ID_Index), applicantNames.get(ID_Index));
			}
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "이력서 조회 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
		
		JButton btn_save = new JButton("저장");
		btn_save.setFont(new Font("굴림", Font.PLAIN, 14));
		btn_save.setBounds(715, 15, 70, 40);
		contentPane.add(btn_save);
		
		// 변경 내용 저장 버튼
		
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(Main.mode.equals("개인")){
					DataToDB(Main.ID, resumeNames.get(Name_Index));
				}
				else {
					DataToDB(applicantIDs.get(ID_Index), applicantNames.get(ID_Index));
				}
			}
		});
		
		// 삭제 버튼
		
		btn_delete = new JButton("삭제");
		btn_delete.setFont(new Font("굴림", Font.PLAIN, 14));
		btn_delete.setBounds(800, 15, 70, 40);
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(Main.mode.equals("개인")) {
					DBdelete(Main.ID, resumeNames.get(Name_Index));
				}
				else {
					DBdelete(applicantIDs.get(ID_Index), applicantNames.get(ID_Index));
				}
				dispose();
			}
		});
		contentPane.add(btn_delete);
		
		// 닫기 버튼
		
		JButton btn_close = new JButton("닫기");
		btn_close.setFont(new Font("굴림", Font.PLAIN, 14));
		btn_close.setBounds(800, 360, 70, 40);
		contentPane.add(btn_close);

		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				dispose();
			}
		});
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 71, 860, 280);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setColumnSelectionAllowed(true);
		table_1.setRowSelectionAllowed(false);
		table_1.setFont(new Font("굴림", Font.PLAIN, 15));
		table_1.setRowHeight(40);
		
		String resume_Name;
		
		if(Main.mode.equals("개인"))
			resume_Name = resumeNames.get(Name_Index);
		else
			resume_Name = applicantNames.get(ID_Index);
		
		table_1.setModel(new DefaultTableModel( new Object[][] {
	                {"이력서", resume_Name, null, null},
	                {"성명", memberName, "생년월일", birthDate},
	                {"핸드폰", phoneNumber, "학력", education},
	                {"토익", toeic, "해외 경험 횟수", overseasExperience},
	                {"경력 위치", carrerName, "년수", carrerYear},
	                {"직급", carrerPosition, "연봉", carrerIncome},
	                {"자격증", cirtifiStrings, null, null}
	                }, new String[] {"항목", "내용", "항목", "내용"})
		{ boolean[][] columnEditables = new boolean[][] {
				{false, true, false, false}, // 1행
				{false, true, false, false}, // 2행
				{false, false, false, true}, // 3행
				{false, true, false, true}, // 4행
				{false, true, false, true}, // 5행
				{false, true, false, true}, // 6행
				{false, false, false, false} // 7행
				};
			public boolean isCellEditable(int row, int column) { return columnEditables[row][column]; }
		});

		if(Main.mode.equals("기업"))
			table_1.setEnabled(false);
		
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(0).setMinWidth(60);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(500);
		table_1.getColumnModel().getColumn(1).setMinWidth(500);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(90);
		table_1.getColumnModel().getColumn(2).setMinWidth(90);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(3).setMinWidth(150);
		scrollPane_1.setColumnHeaderView(table_1);
		
		// 이전 버튼
		
		btn_prev = new JButton("<");
		btn_prev.setFont(new Font("굴림", Font.PLAIN, 13));
		
		btn_prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_next.setEnabled(true);
				if(Main.mode.equals("개인")) {
					if(Name_Index!=0) {
						Name_Index--;
					DBinquiry(Main.ID, resumeNames.get(Name_Index));
					if(Name_Index == 0) {
						btn_prev.setEnabled(false);
						}
					}
				}else {
					if(ID_Index!= 0) {
						ID_Index--;
					}
					DBinquiry(applicantIDs.get(ID_Index), applicantNames.get(ID_Index));
					if(ID_Index == 0) {
						btn_prev.setEnabled(false);
					}
				}
			}
		});
		btn_prev.setEnabled(false);
		btn_prev.setBounds(350, 370, 60, 30);
		contentPane.add(btn_prev);
		
		// 다음 버튼
		
		btn_next = new JButton(">");
		btn_next.setFont(new Font("굴림", Font.PLAIN, 13));
		
		btn_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_prev.setEnabled(true);
				if(Main.mode.equals("개인")) {
					if(Name_Index < resumeNames.size()-1) {
						Name_Index++;
					}
					if(Name_Index == resumeNames.size()-1) {
						btn_next.setEnabled(false);
					}
					DBinquiry(Main.ID, resumeNames.get(Name_Index));
				}else {
					if(ID_Index < applicantIDs.size()-1) {
						ID_Index++;
					}
					if(ID_Index == applicantIDs.size()-1) {
						btn_next.setEnabled(false);
					}
					DBinquiry(applicantIDs.get(ID_Index), applicantNames.get(ID_Index));
					}
				}
		});
		btn_next.setBounds(460, 370, 60, 30);
		contentPane.add(btn_next);
		
	}
	
	// ID와 이력서명 받아서 이력서 조회
	
	public void DBinquiry(String ID, String resumeName) {
		
		try {
			Main.DBConnection();
			
			// 0. 이름, 생년월일, 휴대폰
			
			String sql_name = "SELECT * FROM 개인회원 WHERE 회원ID = ?";
			
			Main.pstmt = Main.con.prepareStatement(sql_name);
			Main.pstmt.setString(1, ID);
			Main.rs = Main.pstmt.executeQuery();
			
			if(Main.rs.next()) {
				memberName = Main.rs.getString("이름");
				birthDate = Main.rs.getString("생년월일");
				phoneNumber = Main.rs.getString("휴대폰");
			}
			else {
				JOptionPane.showMessageDialog(null, "인적사항을 불러올 수 없습니다.", "이력서 조회 실패", JOptionPane.ERROR_MESSAGE);
			}
			
			if(Main.mode.equals("기업"))
				lbl_resume.setText("내 채용 게시글에 지원한 이력서 ( " + (ID_Index+1) + " / " + applicantIDs.size() + " )");
			else
				lbl_resume.setText("내가 작성한 이력서 ( " + (Name_Index+1) + " / " + resumeNames.size()+ " )");
			
			
			
			// 1. 이력서 조회 : 학력, 토익, 해외_경험_횟수
			
			String sql_resume_2 = "SELECT * FROM 이력서 WHERE 작성자ID = ? AND 이력서명 = ?";
			
			Main.pstmt = Main.con.prepareStatement(sql_resume_2);
			Main.pstmt.setString(1, ID);
			Main.pstmt.setString(2, resumeName);
			
			Main.rs = Main.pstmt.executeQuery();
			
			while(Main.rs.next()) {
				education = Main.rs.getString("학력");
				toeic = Main.rs.getInt("토익");
				overseasExperience = Main.rs.getInt("해외_경험_횟수");
			}
			
			
			// 2. 이력서_경력 조회 : 경력_위치, 년수, 직급, 연봉
			
			String sql_resume_carrer = "SELECT * FROM 이력서_경력 WHERE 회원ID = ? AND 이력서명 = ?";
			
			Main.pstmt = Main.con.prepareStatement(sql_resume_carrer);
			Main.pstmt.setString(1, ID);
			Main.pstmt.setString(2, resumeName);
			
			Main.rs = Main.pstmt.executeQuery();
			
			if(Main.rs.next()) {
				carrerName = Main.rs.getString("경력_위치");
				carrerYear = Main.rs.getInt("년수");
				carrerPosition = Main.rs.getString("직급");
				carrerIncome = Main.rs.getInt("연봉");
			}
			
			// 3. 이력서_자격증 조회 : 자격증명
			
			String sql_resume_cirtifi = "SELECT 자격증명 FROM 이력서_자격증 WHERE 이력서_작성자 = ? AND 이력서명 = ?";

			cirtifinames.clear(); // 리스트 초기화
			
			Main.pstmt = Main.con.prepareStatement(sql_resume_cirtifi);
			Main.pstmt.setString(1, ID);
			Main.pstmt.setString(2, resumeName);
			
			Main.rs = Main.pstmt.executeQuery();
			
			while(Main.rs.next()) {
				String temp = Main.rs.getString("자격증명");
				// 자격증명 중복 제외
				if(!cirtifinames.contains(temp))
					cirtifinames.add(temp);
			}

			cirtifiStrings.setLength(0); // StringBuilder 초기화
			
			for(int i = 0; i< cirtifinames.size(); i++) {
				String temp = cirtifinames.get(i);
				cirtifiStrings.append(temp);
				if(i < cirtifinames.size() - 1)
					cirtifiStrings.append(", ");
			}
			
			
			if(table_1 != null) {
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				model.setValueAt(resumeName, 0, 1);
				model.setValueAt(memberName, 1, 1);
				model.setValueAt(birthDate, 1, 3);
				model.setValueAt(phoneNumber, 2, 1);
				model.setValueAt(education, 2, 3);
				model.setValueAt(toeic, 3, 1);
				model.setValueAt(overseasExperience, 3, 3);
				model.setValueAt(carrerName, 4, 1);
				model.setValueAt(carrerYear, 4, 3);
				model.setValueAt(carrerPosition, 5, 1);
				model.setValueAt(carrerIncome, 5, 3);
				model.setValueAt(cirtifiStrings, 6, 1);
				table_1.setModel(model);
			}
			
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "이력서 조회 실패", JOptionPane.ERROR_MESSAGE);
		}
		
		finally {
			Main.DBClose();
		}
	}
	
	// 이력서에서 정보 가져오기
	
	public void DataToDB(String personalID, String resumeName) {

		resumeName = table_1.getValueAt(0, 1).toString();
		memberName = table_1.getValueAt(1, 1).toString();
		education = table_1.getValueAt(2, 3).toString();
		toeic = Integer.parseInt(table_1.getValueAt(3, 1).toString());
		overseasExperience = Integer.parseInt(table_1.getValueAt(3, 3).toString());
		carrerName = table_1.getValueAt(4, 1).toString();
		carrerYear = Integer.parseInt(table_1.getValueAt(4, 3).toString());
		carrerPosition = table_1.getValueAt(5, 1).toString();
		carrerIncome = Integer.parseInt(table_1.getValueAt(5, 3).toString());
		
		DBsave(personalID, resumeName, memberName, education, toeic, overseasExperience, carrerName, carrerYear, carrerPosition, carrerIncome);
		
	}
	
	// 가져온 정보 DB에 저장
	
	public void DBsave(String personalID, String resumeName, String memberName, String education, 
				int toeic, int overseasExperience, String carrerName, int carrerYear, String carrerPosition, int carrerIncome) {
		try {
			Main.DBConnection();
			
			// 이름 수정 ( 생년월일, 휴대폰은 정보 수정에서 가능하기에 제외 )
			String sql_name_update = "UPDATE 개인회원 SET 이름 = ? WHERE 회원ID = ?";
			Main.pstmt = Main.con.prepareStatement(sql_name_update);
			Main.pstmt.setString(1, memberName);
			Main.pstmt.setString(2, personalID);
			Main.pstmt.executeUpdate();
			
			// 이력서 수정 : 이력서명, 학력, 토익, 해외_경험_횟수
			String sql_resume_update = "UPDATE 이력서 SET 이력서명 = ?, 학력 = ?, 토익 = ?, 해외_경험_횟수 = ? WHERE 작성자ID = ?";
			Main.pstmt = Main.con.prepareStatement(sql_resume_update);
			Main.pstmt.setString(1, resumeName);
			Main.pstmt.setString(2, education);
			Main.pstmt.setInt(3, toeic);
			Main.pstmt.setInt(4, overseasExperience);
			Main.pstmt.setString(5,  personalID);
			Main.pstmt.executeUpdate();
			
			// 이력서_경력 수정 : 경력_위치, 년수, 직급, 연봉
			String sql_resume_carrer_update = "UPDATE 이력서_경력 SET 경력_위치 = ?, 년수 = ?, 직급 = ?, 연봉 = ? WHERE 회원ID = ?";
			Main.pstmt = Main.con.prepareStatement(sql_resume_carrer_update);
			Main.pstmt.setString(1, carrerName);
			Main.pstmt.setInt(2, carrerYear);
			Main.pstmt.setString(3, carrerPosition);
			Main.pstmt.setInt(4, carrerIncome);
			Main.pstmt.setString(5, personalID);
			
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "이력서 저장 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
	}
	
	// 조회한 이력서 삭제
	
	public void DBdelete(String personalID, String resumeName) {
		try {
			Main.DBConnection();
			
			String sql_delete_1 = "DELETE FROM 이력서 WHERE 작성자ID = ? AND 이력서명 = ?";
			String sql_delete_2 = "DELETE FROM 이력서_경력 WHERE 회원ID = ? AND 이력서명 = ?";
			String sql_delete_3 = "DELETE FROM 이력서_자격증 WHERE 이력서_작성자 = ? AND 이력서명 = ?";
			
			Main.pstmt = Main.con.prepareStatement(sql_delete_1);
			Main.pstmt.setString(1, personalID);
			Main.pstmt.setString(2, resumeName);
			Main.pstmt.executeUpdate();
			
			Main.pstmt = Main.con.prepareStatement(sql_delete_2);
			Main.pstmt.setString(1, personalID);
			Main.pstmt.setString(2, resumeName);
			Main.pstmt.executeUpdate();
			
			Main.pstmt = Main.con.prepareStatement(sql_delete_3);
			Main.pstmt.setString(1, personalID);
			Main.pstmt.setString(2, resumeName);
			Main.rs = Main.pstmt.executeQuery();
			
			JOptionPane.showMessageDialog(null, "해당 이력서가 성공적으로 삭제되었습니다.", "이력서 삭제", JOptionPane.INFORMATION_MESSAGE);

			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "이력서 삭제 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
	}
}
