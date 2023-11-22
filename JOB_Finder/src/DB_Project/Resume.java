package DB_Project;

import java.awt.*;
import javax.swing.*;

public class Resume{
	public JFrame frame; 
	
	public Resume() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100,100,669,480 );
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JLabel la1 = new JLabel("이력서 명");
		la1.setBounds(22, 0, 257, 44);
		frame.getContentPane().add(la1);
		
		JTextField tf1 = new JTextField();
		tf1.setBounds(22, 40, 242, 34);
		frame.getContentPane().add(tf1);
		
		JLabel la2 = new JLabel("최종 학력");
		la2.setBounds(22, 74, 257, 44);
		frame.getContentPane().add(la2);
		
		JTextField tf2 = new JTextField();
		tf2.setBounds(22, 114, 242, 34);
		frame.getContentPane().add(tf2);
		
		JLabel la3 = new JLabel("토익 점수");
		la3.setBounds(22, 148, 257, 44);
		frame.getContentPane().add(la3);
		
		JTextField tf3 = new JTextField();
		tf3.setBounds(22, 188, 242, 34);
		frame.getContentPane().add(tf3);
		
		JLabel la4 = new JLabel("해외 경험 횟수");
		la4.setBounds(22, 222, 257, 44);
		frame.getContentPane().add(la4);
		
		JTextField tf4 = new JTextField();
		tf4.setBounds(22, 262, 242, 34);
		frame.getContentPane().add(tf4);

		JButton bt1 = new JButton("닫기");
		bt1.setBounds(538, 387, 92, 37);
		frame.getContentPane().add(bt1);
		
		JButton bt2 = new JButton("경력 추가");
		bt2.setBounds(297, 112, 107, 36);
		frame.getContentPane().add(bt2);
		
		JTextField tf5 = new JTextField();
		tf5.setBounds(297, 150, 303, 146);
		frame.getContentPane().add(tf5);
		
		JButton bt3 = new JButton("삭제");
		bt3.setBounds(530, 307, 70, 24);
		frame.getContentPane().add(bt3);
	}

}
