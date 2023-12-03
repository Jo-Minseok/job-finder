package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Career  {

	public JFrame frame;
	//private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	
	public Career() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 325, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb1 = new JLabel("기업명");
		lb1.setBounds(25, 25, 100, 20);
		contentPane.add(lb1);
		
		JLabel lb2 = new JLabel("년수");
		lb2.setBounds(25, 55, 100, 20);
		contentPane.add(lb2);
		
		JLabel lb3 = new JLabel("직급");
		lb3.setBounds(25, 85, 100, 20);
		contentPane.add(lb3);
		
		JLabel lb4 = new JLabel("연봉");
		lb4.setBounds(25, 115, 100, 20);
		contentPane.add(lb4);
		
		tf1 = new JTextField();
		tf1.setBounds(105, 25, 170, 25);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(105, 55, 170, 25);
		contentPane.add(tf2);
		
		tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(105, 85, 170, 25);
		contentPane.add(tf3);
		
		tf4 = new JTextField();
		tf4.setColumns(10);
		tf4.setBounds(105, 115, 170, 25);
		contentPane.add(tf4);
		
		JButton bt1 = new JButton("작성");
		bt1.setBounds(12, 175, 70, 24);
		contentPane.add(bt1);
		
		JButton bt2 = new JButton("닫기");
		bt2.setBounds(227, 175, 70, 24);
		contentPane.add(bt2);
	}
}
