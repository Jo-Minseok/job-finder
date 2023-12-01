package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Job_posting {

	public JFrame frame;

	public Job_posting() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 281, 351);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회사명");
		lblNewLabel.setBounds(12, 10, 57, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("직종");
		lblNewLabel_1.setBounds(12, 35, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("채용분류");
		lblNewLabel_2.setBounds(12, 60, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("고용형태");
		lblNewLabel_3.setBounds(12, 85, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("급여");
		lblNewLabel_4.setBounds(12, 110, 57, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("지역");
		lblNewLabel_5.setBounds(12, 135, 57, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("근무시간");
		lblNewLabel_6.setBounds(12, 160, 57, 15);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("모집인원");
		lblNewLabel_7.setBounds(12, 185, 57, 15);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("직책");
		lblNewLabel_8.setBounds(12, 210, 57, 15);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("마감일");
		lblNewLabel_9.setBounds(12, 235, 57, 15);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		lblNewLabel_10.setBounds(81, 10, 144, 15);
		frame.getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setBounds(81, 35, 144, 15);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		lblNewLabel_12.setBounds(81, 60, 144, 15);
		frame.getContentPane().add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("New label");
		lblNewLabel_13.setBounds(81, 85, 144, 15);
		frame.getContentPane().add(lblNewLabel_13);
		
		JLabel lblNewLabel_10_1 = new JLabel("New label");
		lblNewLabel_10_1.setBounds(81, 110, 144, 15);
		frame.getContentPane().add(lblNewLabel_10_1);
		
		JLabel lblNewLabel_11_1 = new JLabel("New label");
		lblNewLabel_11_1.setBounds(81, 135, 144, 15);
		frame.getContentPane().add(lblNewLabel_11_1);
		
		JLabel lblNewLabel_12_1 = new JLabel("New label");
		lblNewLabel_12_1.setBounds(81, 160, 144, 15);
		frame.getContentPane().add(lblNewLabel_12_1);
		
		JLabel lblNewLabel_10_2 = new JLabel("New label");
		lblNewLabel_10_2.setBounds(81, 185, 144, 15);
		frame.getContentPane().add(lblNewLabel_10_2);
		
		JLabel lblNewLabel_11_2 = new JLabel("New label");
		lblNewLabel_11_2.setBounds(81, 210, 144, 15);
		frame.getContentPane().add(lblNewLabel_11_2);
		
		JLabel lblNewLabel_12_2 = new JLabel("New label");
		lblNewLabel_12_2.setBounds(81, 235, 144, 15);
		frame.getContentPane().add(lblNewLabel_12_2);
		
		JButton btnNewButton = new JButton("닫기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(168, 279, 85, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
