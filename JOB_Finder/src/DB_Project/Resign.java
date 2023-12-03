package DB_Project;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Resign {

	public JFrame frame;
	private JTextField txt_id;
	private JTextField txt_phone;

	public Resign() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_ID = new JLabel("ID");
		lbl_ID.setBounds(12, 10, 57, 15);
		frame.getContentPane().add(lbl_ID);
		
		txt_id = new JTextField();
		txt_id.setBounds(12, 35, 240, 21);
		frame.getContentPane().add(txt_id);
		txt_id.setColumns(10);
		
		JLabel lbl_phone = new JLabel("PHONE_NUMBER");
		lbl_phone.setBounds(12, 66, 116, 15);
		frame.getContentPane().add(lbl_phone);
		
		txt_phone = new JTextField();
		txt_phone.setBounds(12, 91, 240, 21);
		frame.getContentPane().add(txt_phone);
		txt_phone.setColumns(10);
		
		JCheckBox chk1 = new JCheckBox("회원 탈퇴 시, 일부 정보는 보존될 수 있습니다.");
		chk1.setBounds(12, 161, 480, 23);
		frame.getContentPane().add(chk1);
		
		JCheckBox chk2 = new JCheckBox("회원 탈퇴 시, 동일한 ID로 회원가입이 불가능 합니다.");
		chk2.setBounds(12, 203, 480, 23);
		frame.getContentPane().add(chk2);
		
		JCheckBox chk3 = new JCheckBox("회원 탈퇴 전에는 현재 계정의 책임과 의무를 정확히 이해 하시기 바랍니다.");
		chk3.setBounds(12, 249, 480, 23);
		frame.getContentPane().add(chk3);
		
		JButton btn_resign = new JButton("회원 탈퇴");
		btn_resign.setFont(new Font("굴림", Font.BOLD, 18));
		btn_resign.setBounds(12, 294, 116, 57);
		frame.getContentPane().add(btn_resign);
		
		JButton btn_exit = new JButton("닫기");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_exit.setBounds(421, 328, 71, 23);
		frame.getContentPane().add(btn_exit);
		
		btn_resign.setEnabled(false);
		
		chk1.addItemListener(e -> {
		    if (chk1.isSelected() && chk2.isSelected() && chk3.isSelected()) {
		    	btn_resign.setEnabled(true); 
		    } else {
		    	btn_resign.setEnabled(false); 
		    }
		});
		
		chk2.addItemListener(e -> {
		    if (chk1.isSelected() && chk2.isSelected() && chk3.isSelected()) {
		    	btn_resign.setEnabled(true); 
		    } else {
		    	btn_resign.setEnabled(false); 
		    }
		});
		
		chk3.addItemListener(e -> {
		    if (chk1.isSelected() && chk2.isSelected() && chk3.isSelected()) {
		    	btn_resign.setEnabled(true); 
		    } else {
		    	btn_resign.setEnabled(false); 
		    }
		});
	}
}
