import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class Upload_briefing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Upload_briefing frame = new Upload_briefing();
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
	public Upload_briefing() {
		setTitle("설명회 게시글 작성");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox cb = new JComboBox();
		cb.setModel(new DefaultComboBoxModel(new String[] {"채용 게시글", "설명회 게시글"}));
		cb.setSelectedIndex(1);
		cb.setMaximumRowCount(2);
		cb.setBounds(20, 20, 150, 30);
		contentPane.add(cb);
		
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox)e.getSource();
				int selectedIndex = combo.getSelectedIndex();
				
				if(selectedIndex==0) {
					dispose();
					openWindow();
				}
			}
		});
		
		JLabel briefing_lb1 = new JLabel("기업명");
		briefing_lb1.setBounds(20, 60, 150, 20);
		contentPane.add(briefing_lb1);
		
		textField = new JTextField();
		textField.setText("( DB에서 불러와서 수정 불가 )");
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(20, 80, 350, 25);
		contentPane.add(textField);
		
		JLabel briefing_lb2 = new JLabel("회차");
		briefing_lb2.setBounds(20, 110, 150, 20);
		contentPane.add(briefing_lb2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(20, 130, 350, 25);
		contentPane.add(textField_1);
		
		JLabel briefing_lb3 = new JLabel("개최일시");
		briefing_lb3.setBounds(20, 160, 150, 20);
		contentPane.add(briefing_lb3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(20, 180, 350, 25);
		contentPane.add(textField_2);
		
		JLabel briefing_lb4 = new JLabel("주소");
		briefing_lb4.setBounds(20, 210, 150, 20);
		contentPane.add(briefing_lb4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(20, 230, 350, 25);
		contentPane.add(textField_3);
		
		JButton bt_upload = new JButton("작성");
		bt_upload.setBounds(540, 190, 75, 30);
		contentPane.add(bt_upload);
		
		JButton bt_close = new JButton("닫기");
		bt_close.setBounds(540, 230, 75, 30);
		contentPane.add(bt_close);
    }
	private void openWindow() {
		try {
			Upload_recruit frame = new Upload_recruit();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
