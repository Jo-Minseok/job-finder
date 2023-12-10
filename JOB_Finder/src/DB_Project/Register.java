package DB_Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;

public class Register {

	public JFrame frame;
	private JTextField txt_name;
	private JTextField txt_id;
	private JTextField txt_pw;
	private JTextField txt_birth;
	private JTextField txt_corporate;
	private JTextField txt_salary;
	private JTextField txt_position;
	private JComboBox<String> com_address = new JComboBox<>();
	private JTextField txt_companynumber;
	private JCheckBox chk_member;
	private JButton btn_create;

	public Register() {
		initialize();
	}
	private void updateButtonState() {
		if (chk_member.isSelected() && txt_corporate.getText().trim().isEmpty()) {
            btn_create.setEnabled(false);
        } else {
            btn_create.setEnabled(true);
        }
	}
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("회원가입");
		frame.setBounds(100, 100, 450, 745);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setBounds(30, 10, 190, 15);
		frame.getContentPane().add(lblNewLabel);
		
		txt_name = new JTextField();
		txt_name.setBounds(30, 28, 190, 21);
		frame.getContentPane().add(txt_name);
		txt_name.setColumns(10);
		
		JLabel lblPhonenumber = new JLabel("PHONE_NUMBER");
		lblPhonenumber.setBounds(30, 59, 190, 15);
		frame.getContentPane().add(lblPhonenumber);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(30, 108, 190, 15);
		frame.getContentPane().add(lblId);
		
		txt_id = new JTextField();
		txt_id.setColumns(10);
		txt_id.setBounds(30, 126, 190, 21);
		frame.getContentPane().add(txt_id);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(30, 157, 190, 15);
		frame.getContentPane().add(lblPassword);
		
		txt_pw = new JTextField();
		txt_pw.setColumns(10);
		txt_pw.setBounds(30, 175, 190, 21);
		frame.getContentPane().add(txt_pw);
		
		JLabel lblBirthDay = new JLabel("BIRTH DAY (형식: yyyyMMdd)");
		lblBirthDay.setBounds(30, 206, 190, 15);
		frame.getContentPane().add(lblBirthDay);
		
		txt_birth = new JTextField();
		txt_birth.setColumns(10);
		txt_birth.setBounds(30, 224, 190, 21);
		frame.getContentPane().add(txt_birth);
		
		JLabel lblGender = new JLabel("GENDER");
		lblGender.setBounds(30, 255, 190, 15);
		frame.getContentPane().add(lblGender);
		
		JLabel lblNewLabel_1 = new JLabel("ADDRESS");
		lblNewLabel_1.setBounds(30, 304, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		//JComboBox comboBox = new JComboBox();
		com_address.setBounds(30, 319, 111, 23);
		frame.getContentPane().add(com_address);
		AddressComboBox();
		
		JLabel lblNewLabel_2 = new JLabel("개인정보 유효 기간");
		lblNewLabel_2.setBounds(30, 352, 111, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		String private_year[] = {"1년","3년","5년"};
		JComboBox com_period = new JComboBox(private_year);
		com_period.setBounds(30, 369, 111, 23);
		frame.getContentPane().add(com_period);
		
		this.chk_member = new JCheckBox("기업회원");
		chk_member.setBounds(149, 369, 115, 23);
		frame.getContentPane().add(chk_member);
		
		JCheckBox chk_employed = new JCheckBox("무직");
		chk_employed.setBounds(30, 398, 115, 23);
		frame.getContentPane().add(chk_employed);		
		
		JLabel lbl_corporate = new JLabel("기업 이름");
		lbl_corporate.setBounds(30, 427, 190, 15);
		frame.getContentPane().add(lbl_corporate);
		
		txt_corporate = new JTextField();
		txt_corporate.setColumns(10);
		txt_corporate.setBounds(30, 445, 190, 21);
		frame.getContentPane().add(txt_corporate);
		
		JLabel lbl_salary = new JLabel("연봉");
		lbl_salary.setBounds(30, 476, 190, 15);
		frame.getContentPane().add(lbl_salary);
		
		txt_salary = new JTextField();
		txt_salary.setColumns(10);
		txt_salary.setBounds(30, 494, 190, 21);
		frame.getContentPane().add(txt_salary);
		
		JLabel lbl_position = new JLabel("직책");
		lbl_position.setBounds(30, 522, 190, 15);
		frame.getContentPane().add(lbl_position);
		
		txt_position = new JTextField();
		txt_position.setColumns(10);
		txt_position.setBounds(30, 540, 190, 21);
		frame.getContentPane().add(txt_position);
		
		this.btn_create = new JButton("생 성");
		btn_create.setBounds(129, 627, 91, 35);
		frame.getContentPane().add(btn_create);
		
		JButton btn_cancle = new JButton("취 소");
		btn_cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_cancle.setBounds(30, 627, 91, 35);
		frame.getContentPane().add(btn_cancle);
		
		JComboBox com_gender = new JComboBox();
		com_gender.setModel(new DefaultComboBoxModel(new String[] {"남", "여"}));
		com_gender.setBounds(30, 269, 111, 23);
		frame.getContentPane().add(com_gender);
		
		JLabel lbl_companynumber = new JLabel("사업자등록번호");
		lbl_companynumber.setBounds(30, 571, 190, 15);
		frame.getContentPane().add(lbl_companynumber);
		
		txt_companynumber = new JTextField();
		txt_companynumber.setColumns(10);
		txt_companynumber.setBounds(30, 589, 190, 21);
		frame.getContentPane().add(txt_companynumber);
		
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("###-####-####");
			formatter.setPlaceholderCharacter('_');
		}
		catch(Exception ex) {}
		JFormattedTextField txt_phone = new JFormattedTextField(formatter);
		txt_phone.setColumns(15);
		txt_phone.setBounds(30, 77, 190, 21);
		frame.getContentPane().add(txt_phone);
		
//=============================================================
//===========================기능 구현===========================
//=============================================================
		
		btn_create.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(!chk_member.isSelected()) {  // 개인회원
		        try {
		        	Main.DBConnection();
		        	Main.con.setAutoCommit(false);
		        	Main.cstmt = Main.con.prepareCall("{CALL CREATE_ACCOUNT_PERSONAL(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"); // 개인 회원
		        	
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		            java.util.Date parsedDate = dateFormat.parse(txt_birth.getText());
		            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
		            
		            Main.cstmt.setString(1, txt_name.getText());
		            Main.cstmt.setString(2, txt_phone.getText());
		            Main.cstmt.setString(3, txt_id.getText());
		            Main.cstmt.setString(4, txt_pw.getText());
		            Main.cstmt.setDate(5, sqlDate);
		            Main.cstmt.setString(6, com_gender.getSelectedItem().toString());
		            Main.cstmt.setString(7, com_address.getSelectedItem().toString());
		            
		            String selectedPeriod = com_period.getSelectedItem().toString();
		            int validityPeriod = switch (selectedPeriod) {
		                case "1년" -> 1;
		                case "3년" -> 3;
		                case "5년" -> 5;
		                default -> 0;
		            };
		            Main.cstmt.setInt(8, validityPeriod);
		            if(!(chk_employed.isSelected())) {
			            if(txt_corporate.getText().isEmpty()) {
			            	throw new Exception("기업명 칸이 비었습니다!");
			            }
			            else {
			            	Main.cstmt.setString(9,txt_corporate.getText());
			            }
			            if(txt_salary.getText().isEmpty()) {
			            	throw new Exception("연봉 칸이 비었습니다!");
			            }
			            else {
			            	Main.cstmt.setLong(10,Long.valueOf(txt_salary.getText()));
			            }
			            if(txt_position.getText().isEmpty()) {
			            	throw new Exception("직책 칸이 비었습니다!");
			            }
			            else {
			            	Main.cstmt.setString(11,txt_position.getText());
			            }
		            }
		            else {
		            	Main.cstmt.setNull(9, java.sql.Types.NVARCHAR);
		            	Main.cstmt.setNull(10, java.sql.Types.NUMERIC);
		            	Main.cstmt.setNull(11, java.sql.Types.NVARCHAR);
		            }
		            Main.cstmt.registerOutParameter(12,Types.NVARCHAR);
		            Main.cstmt.execute();
		            String result = Main.cstmt.getString(12);
		            
		            if(!(chk_employed.isSelected())) {
		            	Main.cstmt = Main.con.prepareCall("{CALL RECALCULATE(?,?,?,?,?,?)");
		            	Main.cstmt.setString(1,txt_corporate.getText());
		            	Main.cstmt.setString(3,txt_position.getText());
		            	Main.cstmt.setLong(5,Long.valueOf(txt_salary.getText()));
		            	Main.cstmt.setNull(2, java.sql.Types.NVARCHAR);
		            	Main.cstmt.setNull(4, java.sql.Types.NVARCHAR);
		            	Main.cstmt.setNull(6, java.sql.Types.NUMERIC);
		            	Main.cstmt.execute();
		            }
		            Main.con.commit();
		            JOptionPane.showMessageDialog(frame, result + "\n" + txt_name.getText() + "님의 회원가입을 환영합니다.", "성공!", JOptionPane.INFORMATION_MESSAGE);
		            frame.dispose();
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(frame, "SQL 예외가 발생했습니다: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		        } catch (ParseException ex) {
		            JOptionPane.showMessageDialog(frame, "날짜 형식이 올바르지 않습니다: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(frame, "예외가 발생했습니다: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		        }
		         
		        finally {
		        	try {
		        	Main.con.setAutoCommit(true);
		        	}
		        	catch(Exception ex) {}
		            Main.DBClose();
		        }
		    } else {  // 기업회원 
		    	try {
		        	Main.DBConnection();
		        	Main.cstmt = Main.con.prepareCall("{CALL CREATE_ACCOUNT_BUSINESS(?, ?, ?, ?, ?, ?, ?, ?)}");
	           
		            
		            // 기업명, ID, PW, NAME, 사업자등록번호, 휴대폰번호, 개인정보유효기간
		            Main.cstmt.setString(1, txt_corporate.getText());
		            Main.cstmt.setString(2, txt_name.getText());
		            Main.cstmt.setString(3, txt_phone.getText());
		            Main.cstmt.setString(4, txt_id.getText());
		            Main.cstmt.setString(5, txt_pw.getText());
		            
		            String selectedPeriod = com_period.getSelectedItem().toString();
		            int validityPeriod = switch (selectedPeriod) {
		                case "1년" -> 1;
		                case "3년" -> 3;
		                case "5년" -> 5;
		                default -> 0;
		            };
		            Main.cstmt.setInt(6, validityPeriod);		            

		            Main.cstmt.setString(7, txt_companynumber.getText());
		            Main.cstmt.registerOutParameter(8,Types.NVARCHAR);
		            
		            Main.cstmt.execute();
		            String result = Main.cstmt.getString(8);
		            
		            if (result != null && !result.isEmpty()) {
		                JOptionPane.showMessageDialog(frame, result + "\n" + txt_name.getText() + "님의 회원가입을 환영합니다.", "성공!", JOptionPane.INFORMATION_MESSAGE);
			            frame.dispose();
		            } else {
		                JOptionPane.showMessageDialog(frame, "회원가입 처리 결과가 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		            }
		            
		            //JOptionPane.showMessageDialog(frame, result + "\n" + txt_name.getText() + "님의 회원가입을 환영합니다.", "성공!", JOptionPane.INFORMATION_MESSAGE);
		        } catch (SQLException ex) {
		        	String errorMessage = ex.toString(); // toString()은 예외 이름과 메시지를 모두 포함합니다.
		            JOptionPane.showMessageDialog(frame, errorMessage, "SQL 오류", JOptionPane.ERROR_MESSAGE);
		            
		        } catch (Exception ex) {
		        	 String errorMessage = ex.toString(); // toString() 사용
		             JOptionPane.showMessageDialog(frame, errorMessage, "일반 오류", JOptionPane.ERROR_MESSAGE);
		        }
		        finally {
		            Main.DBClose();
		        }
		    }
		  }
		});
		
		
		
		// 무직 체크박스
		chk_employed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				updateButtonState();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txt_corporate.setEnabled(false); 
                	txt_salary.setEnabled(false);
                	txt_position.setEnabled(false);
					txt_companynumber.setEnabled(false);
					chk_member.setEnabled(false);
                } else {
					txt_corporate.setEnabled(true); 
					txt_salary.setEnabled(true);
					txt_position.setEnabled(true);
					txt_companynumber.setEnabled(true);
					chk_member.setEnabled(true);
                }
			}
			
		});
		

		
		// 기업회원 체크시, 기업이름이 빈칸이면 생성 버튼 비활성화
		txt_corporate.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        updateButtonState();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        updateButtonState();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        updateButtonState();
		    }

		});
		
		// 기업회원 체크박스
		chk_member.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				updateButtonState();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txt_corporate.setEnabled(true);
					txt_salary.setEnabled(false);
					txt_position.setEnabled(false);
					txt_birth.setEnabled(false);
					com_gender.setEnabled(false);
					chk_employed.setEnabled(false);
				} else {
					txt_birth.setEnabled(true);
					txt_salary.setEnabled(true);
					txt_position.setEnabled(true);
					com_gender.setEnabled(true);
					chk_employed.setEnabled(true);
				}
				
			}


			
			
		});
		
		
	}
	
	
	// 지역 콤보박스 DB연결
	public ArrayList<String> getRegionData(){
		ArrayList<String> regionList = new ArrayList<>();
		
		try {	           
            Main.DBConnection();

            String query = "SELECT \"지역명\" FROM \"SEOK3764\".\"지역\"";
            Main.stmt = Main.con.createStatement();
            Main.rs = Main.stmt.executeQuery(query);

            while (Main.rs.next()) {
                String regionName = Main.rs.getString("지역명");
                regionList.add(regionName);
                //System.out.println("Retrieved Region: " + regionName);
            }

        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), "오류",JOptionPane.ERROR_MESSAGE);
        }
		finally {
			Main.DBClose();
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
