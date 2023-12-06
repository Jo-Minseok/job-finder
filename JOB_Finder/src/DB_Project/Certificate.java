package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Certificate {

	public JFrame frame;
	private JTextField txt_certifi;

	public Certificate(Resume resume) {
		initialize(resume);
	}

	private void initialize(Resume resume) {
		frame = new JFrame();
		frame.setBounds(100, 100, 364, 171);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_certifi = new JLabel("자격증 명");
		lbl_certifi.setFont(new Font("굴림", Font.PLAIN, 14));
		lbl_certifi.setBounds(12, 10, 66, 24);
		frame.getContentPane().add(lbl_certifi);
		
		txt_certifi = new JTextField();
		txt_certifi.setBounds(90, 10, 242, 24);
		frame.getContentPane().add(txt_certifi);
		txt_certifi.setColumns(10);
		
		JButton btn_regist = new JButton("작성");
		btn_regist.setBounds(12, 83, 97, 39);
		frame.getContentPane().add(btn_regist);
		btn_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String certifi = txt_certifi.getText();
				
				String certificateinfo = certifi;
				
				resume.updateCertificateinfo(certificateinfo);
	            frame.dispose();
			}
		});
		
		JButton btn_exit = new JButton("닫기");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_exit.setBounds(235, 83, 97, 39);
		frame.getContentPane().add(btn_exit);
	}
}
