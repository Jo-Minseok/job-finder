package DB_Project;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Corporate_inquiry extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Corporate_inquiry frame = new Corporate_inquiry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Corporate_inquiry() {
		setTitle("기업 조회");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbA = new JLabel("기업 이름 : ");
		lbA.setBounds(25, 50, 65, 20);
		contentPane.add(lbA);
		
		JLabel lbA_1 = new JLabel("산업 : ");
		lbA_1.setBounds(25, 85, 40, 20);
		contentPane.add(lbA_1);
		
		JLabel lbA_2 = new JLabel("기업 구분 : ");
		lbA_2.setBounds(25, 120, 65, 20);
		contentPane.add(lbA_2);
		
		JLabel lbA_3 = new JLabel("자본금 : ");
		lbA_3.setBounds(25, 155, 50, 20);
		contentPane.add(lbA_3);
		
		JLabel lbA_4 = new JLabel("대표자 : ");
		lbA_4.setBounds(25, 190, 50, 20);
		contentPane.add(lbA_4);
		
		JLabel lbA_5 = new JLabel("주요사업 : ");
		lbA_5.setBounds(25, 225, 65, 20);
		contentPane.add(lbA_5);
		
		JLabel lbA_6 = new JLabel("홈페이지 : ");
		lbA_6.setBounds(25, 260, 65, 20);
		contentPane.add(lbA_6);
		
		JLabel lbA_7 = new JLabel("사원 수 : ");
		lbA_7.setBounds(25, 295, 55, 20);
		contentPane.add(lbA_7);
		
		JLabel lbA_8 = new JLabel("설립일 : ");
		lbA_8.setBounds(25, 330, 50, 20);
		contentPane.add(lbA_8);
		
		JLabel lbA_9 = new JLabel("매출액 : ");
		lbA_9.setBounds(25, 365, 50, 20);
		contentPane.add(lbA_9);
		
		JLabel lbA_10 = new JLabel("대졸 초임 : ");
		lbA_10.setBounds(25, 400, 65, 20);
		contentPane.add(lbA_10);
		
		JLabel lbA_11 = new JLabel("주소 : ");
		lbA_11.setBounds(25, 435, 40, 20);
		contentPane.add(lbA_11);
		
		JLabel lbB = new JLabel("Nan");
		lbB.setBounds(92, 50, 430, 20);
		contentPane.add(lbB);
		
		JLabel lbB_1 = new JLabel("Nan");
		lbB_1.setBounds(65, 85, 460, 20);
		contentPane.add(lbB_1);
		
		JLabel lbB_2 = new JLabel("Nan");
		lbB_2.setBounds(90, 120, 432, 20);
		contentPane.add(lbB_2);
		
		JLabel lbB_3 = new JLabel("Nan");
		lbB_3.setBounds(75, 155, 447, 20);
		contentPane.add(lbB_3);
		
		JLabel lbB_4 = new JLabel("Nan");
		lbB_4.setBounds(75, 190, 447, 20);
		contentPane.add(lbB_4);
		
		JLabel lbB_5 = new JLabel("Nan");
		lbB_5.setBounds(90, 225, 432, 20);
		contentPane.add(lbB_5);
		
		JLabel lbB_6 = new JLabel("Nan");
		lbB_6.setBounds(90, 260, 432, 20);
		contentPane.add(lbB_6);
		
		JLabel lbB_7 = new JLabel("Nan");
		lbB_7.setBounds(80, 295, 442, 20);
		contentPane.add(lbB_7);
		
		JLabel lbB_8 = new JLabel("Nan");
		lbB_8.setBounds(75, 330, 447, 20);
		contentPane.add(lbB_8);
		
		JLabel lbB_9 = new JLabel("Nan");
		lbB_9.setBounds(75, 365, 447, 20);
		contentPane.add(lbB_9);
		
		JLabel lbB_10 = new JLabel("Nan");
		lbB_10.setBounds(90, 400, 432, 20);
		contentPane.add(lbB_10);
		
		JLabel lbB_11 = new JLabel("Nan");
		lbB_11.setBounds(65, 435, 457, 20);
		contentPane.add(lbB_11);
	}

}
