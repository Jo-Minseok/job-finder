package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mainpage {

	public JFrame frame;
	private JTextField textField;

	public Mainpage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 806, 617);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(12, 10, 766, 109);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("기업 조회");
		lblNewLabel_1.setBounds(22, 129, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(106, 126, 220, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("조회");
		btnNewButton.setBounds(343, 125, 78, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("전체");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompanyFullView window = new CompanyFullView();
				window.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(433, 125, 78, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("현재 모집중인 공고 개수  : ");
		lblNewLabel_2.setBounds(557, 126, 153, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(709, 126, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton market_btn = new JButton("채용 시장 분석");
		market_btn.setBounds(567, 151, 172, 23);
		frame.getContentPane().add(market_btn);
		
		JLabel lblNewLabel_4 = new JLabel("이름 :");
		lblNewLabel_4.setBounds(557, 184, 57, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("아이디 :");
		lblNewLabel_5.setBounds(557, 209, 57, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("작성한 이력서 개수 :");
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setBounds(557, 234, 121, 15);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("작성한 채용 게시글 개수 :");
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setBounds(557, 259, 153, 15);
		frame.getContentPane().add(lblNewLabel_7);
		
		JButton Logout_btn = new JButton("로그아웃");
		Logout_btn.setBounds(557, 284, 89, 23);
		frame.getContentPane().add(Logout_btn);
		
		JButton Edit_btn = new JButton("정보수정");
		Edit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edit_info window = new Edit_info();
				window.frame.setVisible(true);
			}
		});
		Edit_btn.setBounds(658, 284, 89, 23);
		frame.getContentPane().add(Edit_btn);
		
		JButton btnNewButton_2 = new JButton("이력서 / 채용 게시글 작성");
		btnNewButton_2.setBounds(557, 329, 190, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("이력서 조회");
		btnNewButton_2_1.setBounds(557, 362, 190, 23);
		frame.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("기업 등록");
		btnNewButton_2_2.setForeground(Color.BLACK);
		btnNewButton_2_2.setBounds(557, 395, 190, 23);
		frame.getContentPane().add(btnNewButton_2_2);
		
		JLabel lblNewLabel_8 = new JLabel("채용 게시글");
		lblNewLabel_8.setBounds(12, 173, 78, 15);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("채용 게시글");
		lblNewLabel_8_1.setBounds(12, 366, 78, 15);
		frame.getContentPane().add(lblNewLabel_8_1);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(51, 198, 112, 69);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("New button");
		btnNewButton_3_1.setBounds(214, 198, 112, 69);
		frame.getContentPane().add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("New button");
		btnNewButton_3_2.setBounds(374, 198, 112, 69);
		frame.getContentPane().add(btnNewButton_3_2);
		
		JButton btnNewButton_3_3 = new JButton("New button");
		btnNewButton_3_3.setBounds(51, 284, 112, 69);
		frame.getContentPane().add(btnNewButton_3_3);
		
		JButton btnNewButton_3_1_1 = new JButton("New button");
		btnNewButton_3_1_1.setBounds(214, 284, 112, 69);
		frame.getContentPane().add(btnNewButton_3_1_1);
		
		JButton btnNewButton_3_2_1 = new JButton("New button");
		btnNewButton_3_2_1.setBounds(374, 284, 112, 69);
		frame.getContentPane().add(btnNewButton_3_2_1);
		
		JButton btnNewButton_3_4 = new JButton("New button");
		btnNewButton_3_4.setBounds(51, 391, 112, 69);
		frame.getContentPane().add(btnNewButton_3_4);
		
		JButton btnNewButton_3_1_2 = new JButton("New button");
		btnNewButton_3_1_2.setBounds(214, 391, 112, 69);
		frame.getContentPane().add(btnNewButton_3_1_2);
		
		JButton btnNewButton_3_2_2 = new JButton("New button");
		btnNewButton_3_2_2.setBounds(374, 391, 112, 69);
		frame.getContentPane().add(btnNewButton_3_2_2);
		
		JButton btnNewButton_3_5 = new JButton("New button");
		btnNewButton_3_5.setBounds(51, 478, 112, 69);
		frame.getContentPane().add(btnNewButton_3_5);
		
		JButton btnNewButton_3_1_3 = new JButton("New button");
		btnNewButton_3_1_3.setBounds(214, 478, 112, 69);
		frame.getContentPane().add(btnNewButton_3_1_3);
		
		JButton btnNewButton_3_2_3 = new JButton("New button");
		btnNewButton_3_2_3.setBounds(374, 478, 112, 69);
		frame.getContentPane().add(btnNewButton_3_2_3);
		
		JLabel lblNewLabel_9 = new JLabel("공고");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(85, 269, 42, 15);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_9_1 = new JLabel("공고");
		lblNewLabel_9_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_1.setBounds(247, 269, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_1);
		
		JLabel lblNewLabel_9_2 = new JLabel("공고");
		lblNewLabel_9_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_2.setBounds(409, 269, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_2);
		
		JLabel lblNewLabel_9_3 = new JLabel("공고");
		lblNewLabel_9_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_3.setBounds(85, 353, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_3);
		
		JLabel lblNewLabel_9_1_1 = new JLabel("공고");
		lblNewLabel_9_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_1_1.setBounds(247, 353, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_1_1);
		
		JLabel lblNewLabel_9_2_1 = new JLabel("공고");
		lblNewLabel_9_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_2_1.setBounds(409, 353, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_2_1);
		
		JLabel lblNewLabel_9_4 = new JLabel("설명회");
		lblNewLabel_9_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_4.setBounds(85, 462, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_4);
		
		JLabel lblNewLabel_9_1_2 = new JLabel("설명회");
		lblNewLabel_9_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_1_2.setBounds(247, 462, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_1_2);
		
		JLabel lblNewLabel_9_2_2 = new JLabel("설명회");
		lblNewLabel_9_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_2_2.setBounds(409, 462, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_2_2);
		
		JLabel lblNewLabel_9_2_2_1 = new JLabel("설명회");
		lblNewLabel_9_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_2_2_1.setBounds(409, 553, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_2_2_1);
		
		JLabel lblNewLabel_9_4_1 = new JLabel("설명회");
		lblNewLabel_9_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_4_1.setBounds(85, 553, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_4_1);
		
		JLabel lblNewLabel_9_1_2_1 = new JLabel("설명회");
		lblNewLabel_9_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9_1_2_1.setBounds(247, 553, 42, 15);
		frame.getContentPane().add(lblNewLabel_9_1_2_1);
	}
}
