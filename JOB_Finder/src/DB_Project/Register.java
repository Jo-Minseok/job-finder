package DB_Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Register {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	public Register() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 745);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setBounds(30, 10, 190, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(30, 28, 190, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPhonenumber = new JLabel("PHONE_NUMBER");
		lblPhonenumber.setBounds(30, 59, 190, 15);
		frame.getContentPane().add(lblPhonenumber);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(30, 77, 190, 21);
		frame.getContentPane().add(textField_2);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(30, 108, 190, 15);
		frame.getContentPane().add(lblId);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(30, 126, 190, 21);
		frame.getContentPane().add(textField_3);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(30, 157, 190, 15);
		frame.getContentPane().add(lblPassword);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(30, 175, 190, 21);
		frame.getContentPane().add(textField_4);
		
		JLabel lblBirthDay = new JLabel("BIRTH DAY");
		lblBirthDay.setBounds(30, 206, 190, 15);
		frame.getContentPane().add(lblBirthDay);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(30, 224, 190, 21);
		frame.getContentPane().add(textField_5);
		
		JLabel lblGender = new JLabel("GENDER");
		lblGender.setBounds(30, 255, 190, 15);
		frame.getContentPane().add(lblGender);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(30, 273, 190, 21);
		frame.getContentPane().add(textField_6);
		
		JLabel lblNewLabel_1 = new JLabel("ADDRESS");
		lblNewLabel_1.setBounds(30, 304, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(30, 319, 111, 23);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("개인정보 유효 기간");
		lblNewLabel_2.setBounds(30, 352, 111, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		String private_year[] = {"1년","3년","5년"};
		JComboBox comboBox_1 = new JComboBox(private_year);
		comboBox_1.setBounds(30, 369, 111, 23);
		frame.getContentPane().add(comboBox_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("기업회원");
		chckbxNewCheckBox.setBounds(149, 369, 115, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chk_employed = new JCheckBox("무직");
		chk_employed.setBounds(30, 398, 115, 23);
		frame.getContentPane().add(chk_employed);		
		
		JLabel com_name = new JLabel("기업 이름");
		com_name.setBounds(30, 427, 190, 15);
		frame.getContentPane().add(com_name);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setColumns(10);
		textField_7.setBounds(30, 445, 190, 21);
		frame.getContentPane().add(textField_7);
		
		JLabel income = new JLabel("연봉");
		income.setBounds(30, 476, 190, 15);
		frame.getContentPane().add(income);
		
		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setColumns(10);
		textField_8.setBounds(30, 494, 190, 21);
		frame.getContentPane().add(textField_8);
		
		JLabel position = new JLabel("직책");
		position.setBounds(30, 522, 190, 15);
		frame.getContentPane().add(position);
		
		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setColumns(10);
		textField_9.setBounds(30, 540, 190, 21);
		frame.getContentPane().add(textField_9);
		
		JButton btnNewButton = new JButton("생 성");
		btnNewButton.setBounds(129, 571, 91, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취 소");

		btnNewButton_1.setBounds(30, 571, 91, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		chk_employed.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textField_7.setEnabled(true); 
					textField_8.setEnabled(true);
					textField_9.setEnabled(true);
                } else {
                	textField_7.setEnabled(false); 
                	textField_8.setEnabled(false);
                	textField_9.setEnabled(false);
                }
			}
			
		});
	}
}
