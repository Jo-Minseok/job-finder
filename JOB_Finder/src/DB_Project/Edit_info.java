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

public class Edit_info { // 수정 버튼 기능 구현해야함

	public JFrame frame;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	public Edit_info() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 659);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPhonenumber = new JLabel("PHONE_NUMBER");
		lblPhonenumber.setBounds(30, 59, 190, 15);
		frame.getContentPane().add(lblPhonenumber);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(30, 77, 190, 21);
		frame.getContentPane().add(textField_2);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(30, 10, 190, 15);
		frame.getContentPane().add(lblId);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(30, 28, 190, 21);
		frame.getContentPane().add(textField_3);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(30, 108, 190, 15);
		frame.getContentPane().add(lblPassword);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(30, 126, 190, 21);
		frame.getContentPane().add(textField_4);
		
		JLabel lblBirthDay = new JLabel("BIRTH DAY");
		lblBirthDay.setBounds(30, 157, 190, 15);
		frame.getContentPane().add(lblBirthDay);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(30, 175, 190, 21);
		frame.getContentPane().add(textField_5);
		
		JLabel lblGender = new JLabel("GENDER");
		lblGender.setBounds(30, 206, 190, 15);
		frame.getContentPane().add(lblGender);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(30, 224, 190, 21);
		frame.getContentPane().add(textField_6);
		
		JLabel lblNewLabel_1 = new JLabel("ADDRESS");
		lblNewLabel_1.setBounds(30, 255, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(30, 270, 111, 23);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("개인정보 유효 기간");
		lblNewLabel_2.setBounds(30, 303, 111, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(30, 320, 111, 23);
		frame.getContentPane().add(comboBox_1);
		
		JCheckBox chk_employed = new JCheckBox("무직");
		chk_employed.setBounds(30, 368, 115, 23);
		frame.getContentPane().add(chk_employed);		
		
		JLabel com_name = new JLabel("기업 이름");
		com_name.setBounds(30, 419, 190, 15);
		frame.getContentPane().add(com_name);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setColumns(10);
		textField_7.setBounds(30, 437, 190, 21);
		frame.getContentPane().add(textField_7);
		
		JLabel income = new JLabel("연봉");
		income.setBounds(30, 468, 190, 15);
		frame.getContentPane().add(income);
		
		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setColumns(10);
		textField_8.setBounds(30, 486, 190, 21);
		frame.getContentPane().add(textField_8);
		
		JLabel position = new JLabel("직책");
		position.setBounds(30, 514, 190, 15);
		frame.getContentPane().add(position);
		
		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setColumns(10);
		textField_9.setBounds(30, 532, 190, 21);
		frame.getContentPane().add(textField_9);
		
		JButton Edit_btn = new JButton("수정");
		Edit_btn.setBounds(30, 581, 67, 29);
		frame.getContentPane().add(Edit_btn);
		
		JButton Exit_btn = new JButton("닫기");
		Exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		Exit_btn.setBounds(355, 581, 67, 29);
		frame.getContentPane().add(Exit_btn);
		
		JButton btnNewButton = new JButton("회원 탈퇴");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resign Resignwindow = new Resign();
				Resignwindow.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(331, 77, 91, 22);
		frame.getContentPane().add(btnNewButton);
		
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
