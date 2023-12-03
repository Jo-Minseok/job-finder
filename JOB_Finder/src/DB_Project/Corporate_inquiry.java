package DB_Project;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Corporate_inquiry extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Corporate_inquiry(String Business) {
		setTitle("기업 조회");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 376, 467);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_name = new JLabel("<dynamic>");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setFont(new Font("굴림", Font.BOLD, 25));
		lbl_name.setBounds(12, 10, 336, 72);
		contentPane.add(lbl_name);
		
		JLabel lbl_industry = new JLabel("산업 : ");
		lbl_industry.setBounds(25, 85, 226, 20);
		contentPane.add(lbl_industry);
		
		JLabel lbl_category = new JLabel("기업 구분 : ");
		lbl_category.setBounds(25, 120, 226, 20);
		contentPane.add(lbl_category);
		
		JLabel lbl_money = new JLabel("자본금 : ");
		lbl_money.setBounds(25, 155, 226, 20);
		contentPane.add(lbl_money);
		
		JLabel lbl_ceo = new JLabel("대표자 : ");
		lbl_ceo.setBounds(25, 190, 226, 20);
		contentPane.add(lbl_ceo);
		
		JLabel lbl_count = new JLabel("사원 수 : ");
		lbl_count.setBounds(25, 220, 226, 20);
		contentPane.add(lbl_count);
		
		JLabel lbl_date = new JLabel("설립일 : ");
		lbl_date.setBounds(25, 250, 226, 20);
		contentPane.add(lbl_date);
		
		JLabel lbl_take = new JLabel("매출액 : ");
		lbl_take.setBounds(25, 280, 226, 20);
		contentPane.add(lbl_take);
		
		JLabel lbl_salary = new JLabel("대졸 초임 : ");
		lbl_salary.setBounds(25, 310, 226, 20);
		contentPane.add(lbl_salary);
		
		JLabel lbl_local = new JLabel("지역 : ");
		lbl_local.setBounds(25, 340, 226, 20);
		contentPane.add(lbl_local);
		
		JButton btn_close = new JButton("닫기");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_close.setBounds(283, 395, 65, 23);
		contentPane.add(btn_close);
		
		JLabel lbl_rate = new JLabel("평균 경쟁률 :");
		lbl_rate.setBounds(25, 372, 226, 15);
		contentPane.add(lbl_rate);
		
		try {
			Main.DBConnection();
		}
		catch(SQLException ex) {
			
		}
		finally {
			Main.DBClose();
		}
	}
}
