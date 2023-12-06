package DB_Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Career  {
	public JFrame frame;
	private JPanel contentPane;
	private JTextField txt_company;
	private JTextField txt_year;
	private JTextField txt_position;
	private JTextField txt_salary;
	
	public Career(Resume resume) {		
		initialize(resume);
	}

	private void initialize(Resume resume) {
		frame = new JFrame();
		frame.setTitle("이력서 - 경력 작성");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 325, 250);
		frame.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_company = new JLabel("기업명");
		lbl_company.setBounds(25, 25, 100, 20);
		contentPane.add(lbl_company);
		
		JLabel lbl_year = new JLabel("년수");
		lbl_year.setBounds(25, 55, 100, 20);
		contentPane.add(lbl_year);
		
		JLabel lbl_position = new JLabel("직급");
		lbl_position.setBounds(25, 85, 100, 20);
		contentPane.add(lbl_position);
		
		JLabel lbl_salary = new JLabel("연봉");
		lbl_salary.setBounds(25, 115, 100, 20);
		contentPane.add(lbl_salary);
		
		txt_company = new JTextField();
		txt_company.setBounds(105, 25, 170, 25);
		contentPane.add(txt_company);
		txt_company.setColumns(10);
		
		txt_year = new JTextField();
		txt_year.setColumns(10);
		txt_year.setBounds(105, 55, 170, 25);
		contentPane.add(txt_year);
		
		txt_position = new JTextField();
		txt_position.setColumns(10);
		txt_position.setBounds(105, 85, 170, 25);
		contentPane.add(txt_position);
		
		txt_salary = new JTextField();
		txt_salary.setColumns(10);
		txt_salary.setBounds(105, 115, 170, 25);
		contentPane.add(txt_salary);
		
		JButton btn_regist = new JButton("작성");
		btn_regist.setBounds(12, 175, 70, 24);
		contentPane.add(btn_regist);
		btn_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String company = txt_company.getText();
	             String year = txt_year.getText();
	             String position = txt_position.getText();
	             String salary = txt_salary.getText();
	             String careerinfo = "회사명: " + company + ", 년수: " + year + ", 직급: " + position + ", 연봉: " + salary;
	             
	             resume.updateCareerinfo(careerinfo);
	             frame.dispose();
			}
		});
		
		JButton btn_exit = new JButton("닫기");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_exit.setBounds(227, 175, 70, 24);
		contentPane.add(btn_exit);
	}
}
