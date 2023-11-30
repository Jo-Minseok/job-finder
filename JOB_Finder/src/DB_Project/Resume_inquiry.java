package DB_Project;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Resume_inquiry extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resume_inquiry frame = new Resume_inquiry();
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
	public Resume_inquiry() {
		setTitle("이력서 조회");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb1 = new JLabel("내가 작성한 이력서 / 기업에 지원한 이력서");
		lb1.setBounds(20, 30, 240, 30);
		contentPane.add(lb1);
		
		JButton bt_close = new JButton("닫기");
		bt_close.setBounds(1375, 30, 60, 30);
		contentPane.add(bt_close);
		
		JTextArea txtArea_resume = new JTextArea();
		txtArea_resume.setEditable(false);
		txtArea_resume.setBounds(20, 80, 1440, 610);
		contentPane.add(txtArea_resume);
	}
}
