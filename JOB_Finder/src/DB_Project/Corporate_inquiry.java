package DB_Project;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
			String sql = "SELECT * FROM 기업 WHERE 이름 = '" + Business + "'";
			Main.stmt = Main.con.createStatement();
			Main.rs = Main.stmt.executeQuery(sql);
			Main.rs.next();
			lbl_name.setText(Main.rs.getString(1));
			lbl_industry.setText(lbl_industry.getText() + Main.rs.getString(2));
			lbl_category.setText(lbl_category.getText() + Main.rs.getString(3));
			lbl_money.setText(lbl_money.getText() + Main.rs.getString(4));
			lbl_ceo.setText(lbl_ceo.getText() + Main.rs.getString(5));
			lbl_count.setText(lbl_count.getText() + Main.rs.getString(6));
			lbl_date.setText(lbl_date.getText() + Main.rs.getDate(7));
			lbl_take.setText(lbl_take.getText() + Main.rs.getInt(8));
			lbl_salary.setText(lbl_salary.getText() + Main.rs.getString(9));
			lbl_local.setText(lbl_local.getText() + Main.rs.getString(10));
			
			sql = "SELECT AVG(경쟁률) FROM 채용_게시글 WHERE 작성자ID IN (SELECT 회원ID FROM 기업회원 WHERE 기업명 = '"+ Business + "')";
			Main.rs = Main.stmt.executeQuery(sql);
			Main.rs.next();
			lbl_rate.setText(lbl_rate.getText() + Main.rs.getInt(1));
			if(Main.rs.getInt(1) < 50) {
				lbl_rate.setForeground(Color.BLUE);
			}
			else if(Main.rs.getInt(1) <= 100) {
				lbl_rate.setForeground(Color.GREEN);
			}
			else {
				lbl_rate.setForeground(Color.RED);
			}
			
		}
		catch(Exception ex) {
			dispose();
			JOptionPane.showMessageDialog(null, "기업이 존재하지 않습니다.","검색 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
	}
}
