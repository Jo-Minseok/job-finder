package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Point {

	public JFrame frame;
	private JTextField charge_txt;

	public Point() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 270, 290);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("현재 당신의 포인트 : ");
		lblNewLabel.setBounds(12, 24, 116, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("총 획득 포인트 :");
		lblNewLabel_1.setBounds(12, 49, 88, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("총 사용 포인트 :");
		lblNewLabel_2.setBounds(12, 74, 88, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(131, 24, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(108, 49, 57, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(108, 74, 57, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("[충전하기]");
		lblNewLabel_6.setBounds(12, 120, 60, 15);
		frame.getContentPane().add(lblNewLabel_6);
		
		charge_txt = new JTextField();
		charge_txt.setBounds(12, 139, 176, 21);
		frame.getContentPane().add(charge_txt);
		charge_txt.setColumns(10);
		
		JButton charge_btn = new JButton("충전");
		charge_btn.setBounds(145, 218, 97, 23);
		frame.getContentPane().add(charge_btn);
	}
}
