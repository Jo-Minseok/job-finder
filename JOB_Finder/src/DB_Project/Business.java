package DB_Project;

import java.awt.*;
import javax.swing.*;

public class Business{
	public JFrame frame;
	public Business() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("기업 등록");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setBounds(100,100,922,994);
		
		JLabel la1 = new JLabel("기업 이름");
		la1.setBounds(30, 17, 432, 52);
		frame.getContentPane().add(la1);
		
		JTextField tf1 = new JTextField("");
		tf1.setBounds(30, 67, 373, 44);
		frame.getContentPane().add(tf1);
		
		JLabel la2 = new JLabel("산업");
		la2.setBounds(30, 111, 432, 52);
		frame.getContentPane().add(la2);
		
		JTextField tf2 = new JTextField("");
		tf2.setBounds(30, 161, 373, 44);
		frame.getContentPane().add(tf2);
		
		JLabel la3 = new JLabel("기업 구분");
		la3.setBounds(30, 205, 432, 52);
		frame.getContentPane().add(la3);
		
		JTextField tf3 = new JTextField("");
		tf3.setBounds(30, 255, 373, 44);
		frame.getContentPane().add(tf3);
		
		JLabel la4 = new JLabel("자본금");
		la4.setBounds(30, 292, 432, 52);
		frame.getContentPane().add(la4);
		
		JTextField tf4 = new JTextField("");
		tf4.setBounds(30, 342, 373, 44);
		frame.getContentPane().add(tf4);
		
		JLabel la5 = new JLabel("대표자");
		la5.setBounds(30, 386, 432, 52);
		frame.getContentPane().add(la5);
		
		JTextField tf5 = new JTextField("");
		tf5.setBounds(30, 436, 373, 44);
		frame.getContentPane().add(tf5);
		
		JLabel la6 = new JLabel("사원 수");
		la6.setBounds(30, 480, 432, 52);
		frame.getContentPane().add(la6);
		
		JTextField tf6 = new JTextField("");
		tf6.setBounds(30, 530, 373, 44);
		frame.getContentPane().add(tf6);
		
		JLabel la7 = new JLabel("설립일");
		la7.setBounds(30, 574, 432, 52);
		frame.getContentPane().add(la7);
		
		JTextField tf7 = new JTextField("");
		tf7.setBounds(30, 624, 373, 44);
		frame.getContentPane().add(tf7);
		
		JLabel la8 = new JLabel("매출액");
		la8.setBounds(30, 668, 432, 52);
		frame.getContentPane().add(la8);
		
		JTextField tf8 = new JTextField("");
		tf8.setBounds(30, 718, 373, 44);
		frame.getContentPane().add(tf8);
		
		JLabel la9 = new JLabel("대졸 초임");
		la9.setBounds(30, 755, 432, 52);
		frame.getContentPane().add(la9);
		
		JTextField tf9 = new JTextField("");
		tf9.setBounds(30, 805, 373, 44);
		frame.getContentPane().add(tf9);
		
		JLabel la10 = new JLabel("주소");
		la10.setBounds(30, 849, 432, 52);
		frame.getContentPane().add(la10);
		
		JTextField tf10 = new JTextField("");
		tf10.setBounds(30, 899, 373, 44);
		frame.getContentPane().add(tf10);

		JButton bt1 = new JButton("닫기");
		bt1.setBounds(763, 859, 103, 31);
		frame.getContentPane().add(bt1);
		
		JButton bt2 = new JButton("등록");
		bt2.setBounds(762, 906, 105, 29);
		frame.getContentPane().add(bt2);
		
	}

}
