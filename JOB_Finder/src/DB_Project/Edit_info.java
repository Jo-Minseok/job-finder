package DB_Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import java.awt.Font;

public class Edit_info {

	public JFrame frame;
	private JTextField txt_id;
	private JTextField txt_pw;
	private JTextField txt_birth;
	private JTextField txt_corporate;
	private JTextField txt_salary;
	private JTextField txt_position;
	private JFormattedTextField txt_phone;
	private JComboBox com_gender;
	private JComboBox com_period;
	private JComboBox<String> com_address = new JComboBox<>();
	private String old_company;
	private String old_position;
	private String old_salary;

	public Edit_info() {		
		initialize();
		Load_info();
	}

	private void Load_info() {
		if(Main.mode.equals("개인")) {
			try {
				Main.DBConnection();
				
				String sql = "SELECT 휴대폰, 비밀번호, 생년월일, 성별, 거주_지역, 개인정보_유효기간, 기업_이름, 연봉, 직책 FROM 개인회원 WHERE 회원ID = ?";
				Main.pstmt = Main.con.prepareStatement(sql);
				Main.pstmt.setString(1, Main.ID);
				Main.rs = Main.pstmt.executeQuery();
				
				
				if(Main.rs.next()) {
					
					txt_phone.setText(Main.rs.getString("휴대폰"));
					txt_pw.setText(Main.rs.getString("비밀번호"));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					txt_birth.setText(sdf.format(Main.rs.getDate("생년월일")));
					com_gender.setSelectedItem(Main.rs.getString("성별"));
					com_address.setSelectedItem(Main.rs.getString("거주_지역"));
					com_period.setSelectedItem(Main.rs.getString("개인정보_유효기간"));
					txt_corporate.setText(Main.rs.getString("기업_이름"));
					txt_salary.setText(Main.rs.getString("연봉"));
					txt_position.setText(Main.rs.getString("직책"));
					old_company = new String(Main.rs.getString("기업_이름"));
					old_position = new String(Main.rs.getString("직책"));
					old_salary = new String(Main.rs.getString("연봉"));
					
					txt_id.setText(Main.ID);
					txt_id.setEditable(false);
				}
			} catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "데이터 로드 실패", JOptionPane.ERROR_MESSAGE);
			} finally {
				Main.DBClose();
			}
		}
		else {
			try {
				Main.DBConnection();
				
				String sql = "SELECT 휴대폰, 비밀번호, 개인정보_유효기간, 기업명 FROM 기업회원 WHERE 회원ID = ?";
				Main.pstmt = Main.con.prepareStatement(sql);
				Main.pstmt.setString(1, Main.ID);
				Main.rs = Main.pstmt.executeQuery();
				
				
				if(Main.rs.next()) {
					
					txt_phone.setText(Main.rs.getString("휴대폰"));
					txt_pw.setText(Main.rs.getString("비밀번호"));
					com_period.setSelectedItem(Main.rs.getString("개인정보_유효기간"));
					txt_corporate.setText(Main.rs.getString("기업명"));
					
					txt_id.setText(Main.ID);
					txt_id.setEditable(false);
				}
			} catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "데이터 로드 실패", JOptionPane.ERROR_MESSAGE);
			} finally {
				Main.DBClose();
			}
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("회원 정보 수정");
		frame.setBounds(100, 100, 450, 659);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPhonenumber = new JLabel("PHONE_NUMBER");
		lblPhonenumber.setBounds(30, 59, 190, 15);
		frame.getContentPane().add(lblPhonenumber);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(30, 10, 190, 15);
		frame.getContentPane().add(lblId);
		
		txt_id = new JTextField();
		txt_id.setColumns(10);
		txt_id.setBounds(30, 28, 190, 21);
		frame.getContentPane().add(txt_id);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(30, 108, 190, 15);
		frame.getContentPane().add(lblPassword);
		
		txt_pw = new JTextField();
		txt_pw.setColumns(10);
		txt_pw.setBounds(30, 126, 190, 21);
		frame.getContentPane().add(txt_pw);
		
		JLabel lblBirthDay = new JLabel("BIRTH DAY (형식: yyyyMMdd)");
		lblBirthDay.setBounds(30, 157, 190, 15);
		frame.getContentPane().add(lblBirthDay);
		
		txt_birth = new JTextField();
		txt_birth.setColumns(10);
		txt_birth.setBounds(30, 175, 190, 21);
		frame.getContentPane().add(txt_birth);
		
		JLabel lblGender = new JLabel("GENDER");
		lblGender.setBounds(30, 206, 190, 15);
		frame.getContentPane().add(lblGender);
		
		JLabel lblNewLabel_1 = new JLabel("ADDRESS");
		lblNewLabel_1.setBounds(30, 255, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		//JComboBox com_address = new JComboBox();
		com_address.setBounds(30, 270, 111, 23);
		frame.getContentPane().add(com_address);
		AddressComboBox();
		
		JLabel lblNewLabel_2 = new JLabel("개인정보 유효 기간");
		lblNewLabel_2.setBounds(30, 303, 111, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		com_period = new JComboBox();
		com_period.setModel(new DefaultComboBoxModel(new String[] {"1년", "3년", "5년"}));
		com_period.setBounds(30, 320, 111, 23);
		frame.getContentPane().add(com_period);
		
		JCheckBox chk_employed = new JCheckBox("무직");
		chk_employed.setBounds(30, 368, 115, 23);
		frame.getContentPane().add(chk_employed);		
		
		JLabel lbl_company = new JLabel("기업 이름");
		lbl_company.setBounds(30, 419, 190, 15);
		frame.getContentPane().add(lbl_company);
		
		txt_corporate = new JTextField();
		txt_corporate.setColumns(10);
		txt_corporate.setBounds(30, 437, 190, 21);
		frame.getContentPane().add(txt_corporate);
		
		JLabel lbl_salary = new JLabel("연봉");
		lbl_salary.setBounds(30, 468, 190, 15);
		frame.getContentPane().add(lbl_salary);
		
		txt_salary = new JTextField();
		txt_salary.setColumns(10);
		txt_salary.setBounds(30, 486, 190, 21);
		frame.getContentPane().add(txt_salary);
		
		JLabel lbl_position = new JLabel("직책");
		lbl_position.setBounds(30, 514, 190, 15);
		frame.getContentPane().add(lbl_position);
		
		txt_position = new JTextField();
		txt_position.setColumns(10);
		txt_position.setBounds(30, 532, 190, 21);
		frame.getContentPane().add(txt_position);
		
		JButton btn_edit = new JButton("수정");
		btn_edit.setBounds(337, 542, 85, 29);
		frame.getContentPane().add(btn_edit);
		
		JButton btn_exit = new JButton("닫기");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_exit.setBounds(337, 581, 85, 29);
		frame.getContentPane().add(btn_exit);
		
		JButton btn_resign = new JButton("회원 탈퇴");
		btn_resign.setFont(new Font("굴림", Font.PLAIN, 11));
		btn_resign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resign Resignwindow = new Resign();
				Resignwindow.frame.setVisible(true);
				frame.dispose();
			}
		});
		btn_resign.setBounds(337, 503, 85, 29);
		frame.getContentPane().add(btn_resign);
		
		com_gender = new JComboBox();
		com_gender.setModel(new DefaultComboBoxModel(new String[] {"남", "여"}));
		com_gender.setBounds(30, 222, 111, 23);
		frame.getContentPane().add(com_gender);
		
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("###-####-####");
			formatter.setPlaceholderCharacter('_');
			formatter.install(txt_phone);
		}
		catch(Exception ex) {}
		
		txt_phone = new JFormattedTextField(formatter);
		txt_phone.setColumns(15);
		txt_phone.setBounds(30, 77, 190, 21);
		frame.getContentPane().add(txt_phone);
		
//=============================================================
//===========================기능 구현===========================
//=============================================================
		if(Main.mode.equals("기업")) {
			chk_employed.setEnabled(false);
	        txt_corporate.setEnabled(false);
	        txt_salary.setEnabled(false);
	        txt_position.setEnabled(false);
	        txt_birth.setEnabled(false);
	        com_gender.setEnabled(false);
	        com_address.setEnabled(false);
		}		
		
		btn_edit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(Main.mode.equals("개인")) {  // 개인회원
		        try {
		        	Main.DBConnection();
		        	Main.con.setAutoCommit(false);
		        	String updateSQL = "UPDATE 개인회원 SET 휴대폰 = ?, 비밀번호 = ?, 생년월일 = ?, 성별 = ?, 거주_지역 = ?, 개인정보_유효기간 = ?, 기업_이름 = ?, 연봉 = ?, 직책 = ? WHERE 회원ID = ?";
		            Main.pstmt = Main.con.prepareStatement(updateSQL);

		        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		            java.util.Date parsedDate = dateFormat.parse(txt_birth.getText());
		            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
		            
		            Main.pstmt.setString(1, txt_phone.getText());
		            Main.pstmt.setString(2, txt_pw.getText());
		            Main.pstmt.setDate(3, sqlDate);
		            Main.pstmt.setString(4, com_gender.getSelectedItem().toString());
		            Main.pstmt.setString(5, com_address.getSelectedItem().toString());
	                String selectedPeriod = com_period.getSelectedItem().toString();
		            int validityPeriod = switch (selectedPeriod) {
		                case "1년" -> 1;
		                case "3년" -> 3;
		                case "5년" -> 5;
		                default -> 0;
		            };
		            Main.pstmt.setInt(6, validityPeriod);
	                if (txt_corporate.getText().isEmpty()) {
	                	Main.pstmt.setNull(7, java.sql.Types.NVARCHAR);
	                } else {
	                	Main.pstmt.setString(7, txt_corporate.getText());
	                }
	                if (txt_salary.getText().isEmpty()) {
	                	Main.pstmt.setNull(8, java.sql.Types.NVARCHAR);
	                } else {
	                	Main.pstmt.setString(8, txt_salary.getText());
	                }
	                if (txt_position.getText().isEmpty()) {
	                	Main.pstmt.setNull(9, java.sql.Types.NVARCHAR);
	                } else {
	                	Main.pstmt.setString(9, txt_position.getText());
	                }
	                Main.pstmt.setString(10, txt_id.getText());

		            int affectedRows = Main.pstmt.executeUpdate();

	                if (affectedRows > 0) {
	                    JOptionPane.showMessageDialog(null, "회원 정보가 성공적으로 업데이트 되었습니다.");
	                } else {
	                    JOptionPane.showMessageDialog(null, "업데이트된 회원 정보가 없습니다.");
	                }
	                
	                String sql = "{CALL RECALCULATE(?,?,?,?,?,?)}";
	                Main.cstmt = Main.con.prepareCall(sql);
	                Main.cstmt.setString(1, txt_corporate.getText());
	                Main.cstmt.setString(2, old_company);
	                Main.cstmt.setString(3, txt_position.getText());
	                Main.cstmt.setString(4, old_position);
	                Main.cstmt.setString(5, txt_salary.getText());
	                Main.cstmt.setString(6, old_salary);
	                Main.cstmt.execute();
	                
;	                Main.con.commit();
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "SQL 오류: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "오류: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        } finally {
		        	try {
		        		Main.con.setAutoCommit(true);
		        	}
		        	catch (Exception ex) {}
		            Main.DBClose();
		        }
		    } else {  // 기업회원 
		    	try {
		        	Main.DBConnection();
		        	String updateSQL = "UPDATE 기업회원 SET 휴대폰 = ?, 비밀번호 = ?, 개인정보_유효기간 = ? WHERE 회원ID = ?";


		            PreparedStatement pstmt = Main.con.prepareStatement(updateSQL);

		        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		            java.util.Date parsedDate = dateFormat.parse(txt_birth.getText());
		            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
		            
		            
		            pstmt.setString(1, txt_phone.getText());
	                pstmt.setString(2, txt_pw.getText());
	                String selectedPeriod = com_period.getSelectedItem().toString();
		            int validityPeriod = switch (selectedPeriod) {
		                case "1년" -> 1;
		                case "3년" -> 3;
		                case "5년" -> 5;
		                default -> 0;
		            };
		            pstmt.setInt(3, validityPeriod);
		            pstmt.setString(4, txt_id.getText());
		            
		            int affectedRows = pstmt.executeUpdate();

	                if (affectedRows > 0) {
	                    JOptionPane.showMessageDialog(null, "회원 정보가 성공적으로 업데이트 되었습니다.");
	                } else {
	                    JOptionPane.showMessageDialog(null, "업데이트된 회원 정보가 없습니다.");
	                }

		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "SQL 오류: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "오류: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        } finally {
		            Main.DBClose();
		        }
		    }
		  }
		});
		
		// 무직 버튼
		chk_employed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txt_corporate.setEnabled(false); 
                	txt_salary.setEnabled(false);
                	txt_position.setEnabled(false);
                	
                } else {
                	txt_corporate.setEnabled(true); 
					txt_salary.setEnabled(true);
					txt_position.setEnabled(true);
                }
			}
			
		});
	}
	
	// 지역 콤보박스 DB연결
		public ArrayList<String> getRegionData(){
			ArrayList<String> regionList = new ArrayList<>();
			
			try {	           
	            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@minseok821lab.kro.kr:1521:orcl", "seok3764", "0424");

	            String query = "SELECT \"지역명\" FROM \"SEOK3764\".\"지역\"";
	            java.sql.Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(query);

	            while (rs.next()) {
	                String regionName = rs.getString("지역명");
	                regionList.add(regionName);
	                //System.out.println("Retrieved Region: " + regionName);
	            }

	            rs.close();
	            stmt.close();
	            con.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return regionList;
	    }
		
		// 지역 콤보박스 리스트 추가
		public void AddressComboBox() {
		    ArrayList<String> addressList = getRegionData();
		    for (String address : addressList) {
		        com_address.addItem(address);
		        //System.out.println("Retrieved Region: " + address);
		    }
		}
}
