package DB_Project;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class Find_ID_PW extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_ID_NAME;
	private JTextField tf_ID_PHONE;
	private JTextField tf_PW_ID;
	private JTextField tf_PW_NAME;
	private JTextField tf_PW_PHONE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Find_ID_PW frame = new Find_ID_PW();
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
	public Find_ID_PW() {
		setTitle("ID/PW 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_ID_find = new JLabel("ID 찾기");
		lb_ID_find.setBounds(25, 30, 40, 20);
		contentPane.add(lb_ID_find);
		
		JLabel lb_ID_NAME = new JLabel("NAME");
		lb_ID_NAME.setBounds(25, 60, 40, 20);
		contentPane.add(lb_ID_NAME);
		
		JLabel lb_ID_PHONE = new JLabel("PHONE_NUMBER");
		lb_ID_PHONE.setBounds(25, 115, 100, 15);
		contentPane.add(lb_ID_PHONE);
		
		JLabel lb_ID_result1 = new JLabel("검색 결과 ID : ");
		lb_ID_result1.setBounds(210, 80, 80, 25);
		contentPane.add(lb_ID_result1);
		
		JLabel lb_ID_result2 = new JLabel("Nan");
		lb_ID_result2.setBounds(290, 80, 150, 25);
		contentPane.add(lb_ID_result2);
		
		tf_ID_NAME = new JTextField();
		tf_ID_NAME.setBounds(25, 80, 150, 25);
		contentPane.add(tf_ID_NAME);
		tf_ID_NAME.setColumns(10);
		
		tf_ID_PHONE = new JTextField();
		tf_ID_PHONE.setColumns(10);
		tf_ID_PHONE.setBounds(25, 130, 150, 25);
		contentPane.add(tf_ID_PHONE);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(15, 190, 405, 5);
		contentPane.add(separator);
		
		JButton bt_ID_search = new JButton("검색");
		bt_ID_search.setBounds(230, 130, 75, 25);
		contentPane.add(bt_ID_search);
		
		JLabel lb_PW_find_1 = new JLabel("PW 찾기");
		lb_PW_find_1.setBounds(25, 220, 50, 20);
		contentPane.add(lb_PW_find_1);
		
		JLabel lb_PW_ID = new JLabel("ID");
		lb_PW_ID.setBounds(25, 250, 15, 20);
		contentPane.add(lb_PW_ID);
		
		JLabel lb_PW_NAME = new JLabel("NAME");
		lb_PW_NAME.setBounds(25, 305, 40, 20);
		contentPane.add(lb_PW_NAME);
		
		JLabel lb_PW_PHONE = new JLabel("PHONE_NUMBER");
		lb_PW_PHONE.setBounds(25, 360, 100, 20);
		contentPane.add(lb_PW_PHONE);
		
		JLabel lb_PW_result1 = new JLabel("검색 결과 PW : ");
		lb_PW_result1.setBounds(210, 270, 90, 25);
		contentPane.add(lb_PW_result1);
		
		JLabel lb_PW_result2 = new JLabel("pas***");
		lb_PW_result2.setBounds(300, 270, 130, 25);
		contentPane.add(lb_PW_result2);
		
		tf_PW_ID = new JTextField();
		tf_PW_ID.setColumns(10);
		tf_PW_ID.setBounds(25, 270, 150, 25);
		contentPane.add(tf_PW_ID);
		
		tf_PW_NAME = new JTextField();
		tf_PW_NAME.setColumns(10);
		tf_PW_NAME.setBounds(25, 325, 150, 25);
		contentPane.add(tf_PW_NAME);
		
		tf_PW_PHONE = new JTextField();
		tf_PW_PHONE.setColumns(10);
		tf_PW_PHONE.setBounds(25, 380, 150, 25);
		contentPane.add(tf_PW_PHONE);
		
		JButton bt_PW_search = new JButton("검색");
		bt_PW_search.setBounds(230, 325, 75, 25);
		contentPane.add(bt_PW_search);
		
		JButton bt_exit = new JButton("닫기");
		bt_exit.setBounds(360, 396, 60, 25);
		contentPane.add(bt_exit);
	}
}
