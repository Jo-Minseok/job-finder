package DB_Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Register {

	public JFrame frame;
	private JTextField txt_name;
	private JTextField txt_phone;
	private JTextField txt_id;
	private JTextField txt_pw;
	private JTextField txt_birth;
	private JTextField txt_gender;
	private JTextField txt_corporate;
	private JTextField txt_salary;
	private JTextField txt_position;
	private JComboBox<String> com_address = new JComboBox<>();

	public Register() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 745);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(30, 77, 190, 21);
		frame.getContentPane().add(txt_phone);
		
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
		
		JLabel lblBirthDay = new JLabel("BIRTH DAY");
		lblBirthDay.setBounds(30, 206, 190, 15);
		frame.getContentPane().add(lblBirthDay);
		
		txt_birth = new JTextField();
		txt_birth.setColumns(10);
		txt_birth.setBounds(30, 224, 190, 21);
		frame.getContentPane().add(txt_birth);
		
		JLabel lblGender = new JLabel("GENDER");
		lblGender.setBounds(30, 255, 190, 15);
		frame.getContentPane().add(lblGender);
		
		txt_gender = new JTextField();
		txt_gender.setColumns(10);
		txt_gender.setBounds(30, 273, 190, 21);
		frame.getContentPane().add(txt_gender);
		
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
		
		JCheckBox chk_member = new JCheckBox("기업회원");
		chk_member.setBounds(149, 369, 115, 23);
		frame.getContentPane().add(chk_member);
		
		JCheckBox chk_employed = new JCheckBox("무직");
		chk_employed.setBounds(30, 398, 115, 23);
		frame.getContentPane().add(chk_employed);		
		
		JLabel lbl_corporate = new JLabel("기업 이름");
		lbl_corporate.setBounds(30, 427, 190, 15);
		frame.getContentPane().add(lbl_corporate);
		
		txt_corporate = new JTextField();
		txt_corporate.setEnabled(false);
		txt_corporate.setColumns(10);
		txt_corporate.setBounds(30, 445, 190, 21);
		frame.getContentPane().add(txt_corporate);
		
		JLabel lbl_salary = new JLabel("연봉");
		lbl_salary.setBounds(30, 476, 190, 15);
		frame.getContentPane().add(lbl_salary);
		
		txt_salary = new JTextField();
		txt_salary.setEnabled(false);
		txt_salary.setColumns(10);
		txt_salary.setBounds(30, 494, 190, 21);
		frame.getContentPane().add(txt_salary);
		
		JLabel lbl_position = new JLabel("직책");
		lbl_position.setBounds(30, 522, 190, 15);
		frame.getContentPane().add(lbl_position);
		
		txt_position = new JTextField();
		txt_position.setEnabled(false);
		txt_position.setColumns(10);
		txt_position.setBounds(30, 540, 190, 21);
		frame.getContentPane().add(txt_position);
		
		JButton btn_create = new JButton("생 성");
		btn_create.setBounds(129, 571, 91, 35);
		frame.getContentPane().add(btn_create);
		
		JButton btn_cancle = new JButton("취 소");
		btn_cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login window = new Login();
				window.frame.setVisible(true);
			}
		});
		btn_cancle.setBounds(30, 571, 91, 35);
		frame.getContentPane().add(btn_cancle);
		
//=============================================================
//===========================기능 구현===========================
//=============================================================
		
		//  생성 버튼 
		btn_create.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        String connectionString = "jdbc:oracle:thin:@minseok821lab.kro.kr:1521:orcl";
		        String dbUser = "seok3764";
		        String dbPassword = "0424";
		        
		        try (Connection con = DriverManager.getConnection(connectionString, dbUser, dbPassword);
		        	CallableStatement cstmt = con.prepareCall("{CALL SEOK3764.CREATE_ACCOUNT_PERSONAL(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
		            
		            cstmt.setString(1, txt_name.getText());
		            cstmt.setString(2, txt_phone.getText());
		            cstmt.setString(3, txt_id.getText());
		            cstmt.setString(4, txt_pw.getText());
		            
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		            java.util.Date parsedDate = dateFormat.parse(txt_birth.getText());
		            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
		            cstmt.setDate(5, sqlDate);
		            
		            cstmt.setString(6, txt_gender.getText());
		            cstmt.setString(7, (String) com_address.getSelectedItem());
		            
		            String selectedPeriod = (String) com_period.getSelectedItem();
		            int validityPeriod = switch (selectedPeriod) {
		                case "1년" -> 1;
		                case "3년" -> 3;
		                case "5년" -> 5;
		                default -> 0;
		            };
		            cstmt.setInt(8, validityPeriod);
		            
		            cstmt.setString(9, txt_corporate.getText());
		            cstmt.setString(10, txt_salary.getText().isEmpty() ? null : txt_salary.getText()); // 연봉이 비어있지 않은 경우에만 설정
		            cstmt.setString(11, txt_position.getText().isEmpty() ? null : txt_position.getText()); // 직책이 비어있지 않은 경우에만 설정
		            
		            cstmt.execute();
		            
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(frame, "SQL 예외가 발생했습니다: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        } catch (ParseException ex) {
		            JOptionPane.showMessageDialog(frame, "날짜 형식이 올바르지 않습니다: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(frame, "예외가 발생했습니다: " + ex.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});
		
		// 무직 체크박스
		chk_employed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txt_corporate.setEnabled(true); 
					txt_salary.setEnabled(true);
					txt_position.setEnabled(true);
                } else {
                	txt_corporate.setEnabled(false); 
                	txt_salary.setEnabled(false);
                	txt_position.setEnabled(false);
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

		    private void updateButtonState() {
		        if (chk_member.isSelected() && txt_corporate.getText().trim().isEmpty()) {
		            btn_create.setEnabled(false);
		        } else {
		            btn_create.setEnabled(true);
		        }
		    }
		});
		
		// 기업회원 체크박스
		chk_member.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txt_corporate.setEnabled(true);
					txt_corporate.requestFocus();
					txt_birth.setEnabled(false);
					txt_gender.setEnabled(false);
				} else {
					txt_corporate.setEnabled(false);
					txt_birth.setEnabled(true);
					txt_gender.setEnabled(true);
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