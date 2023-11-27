package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;

public class Resign {

	private JFrame frame;
	private JTextField ID_txt;
	private JTextField Phone_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resign window = new Resign();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Resign() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(12, 10, 57, 15);
		frame.getContentPane().add(lblNewLabel);
		
		ID_txt = new JTextField();
		ID_txt.setBounds(12, 35, 240, 21);
		frame.getContentPane().add(ID_txt);
		ID_txt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PHONE_NUMBER");
		lblNewLabel_1.setBounds(12, 66, 116, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		Phone_txt = new JTextField();
		Phone_txt.setBounds(12, 91, 240, 21);
		frame.getContentPane().add(Phone_txt);
		Phone_txt.setColumns(10);
		
		JCheckBox chk1 = new JCheckBox("회원 탈퇴 시, 일부 정보는 보존될 수 있습니다.");
		chk1.setBounds(12, 161, 480, 23);
		frame.getContentPane().add(chk1);
		
		JCheckBox chk2 = new JCheckBox("회원 탈퇴 시, 동일한 ID로 회원가입이 불가능 합니다.");
		chk2.setBounds(12, 203, 480, 23);
		frame.getContentPane().add(chk2);
		
		JCheckBox chk3 = new JCheckBox("회원 탈퇴 전에는 현재 계정의 책임과 의무를 정확히 이해 하시기 바랍니다.");
		chk3.setBounds(12, 249, 480, 23);
		frame.getContentPane().add(chk3);
		
		JButton Resign_btn = new JButton("회원 탈퇴");
		Resign_btn.setFont(new Font("굴림", Font.BOLD, 18));
		Resign_btn.setBounds(12, 294, 116, 57);
		frame.getContentPane().add(Resign_btn);
		
		JButton Exit_btn = new JButton("닫기");
		Exit_btn.setBounds(421, 328, 71, 23);
		frame.getContentPane().add(Exit_btn);
		
		Resign_btn.setEnabled(false);
		
		chk1.addItemListener(e -> {
		    if (chk1.isSelected() && chk2.isSelected() && chk3.isSelected()) {
		    	Resign_btn.setEnabled(true); 
		    } else {
		    	Resign_btn.setEnabled(false); 
		    }
		});
		
		chk2.addItemListener(e -> {
		    if (chk1.isSelected() && chk2.isSelected() && chk3.isSelected()) {
		    	Resign_btn.setEnabled(true); 
		    } else {
		    	Resign_btn.setEnabled(false); 
		    }
		});
		
		chk3.addItemListener(e -> {
		    if (chk1.isSelected() && chk2.isSelected() && chk3.isSelected()) {
		    	Resign_btn.setEnabled(true); 
		    } else {
		    	Resign_btn.setEnabled(false); 
		    }
		});
	}
}
