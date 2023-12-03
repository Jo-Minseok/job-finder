package DB_Project;
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
	private JTextField txt_name;
	private JTextField txt_num;
	private JTextField txt_date;
	private JTextField txt_address;

	public Upload_briefing() {
		setTitle("설명회 게시글 작성");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> com_category = new JComboBox<String>();
		com_category.setModel(new DefaultComboBoxModel(new String[] {"채용 게시글", "설명회 게시글"}));
		com_category.setSelectedIndex(1);
		com_category.setMaximumRowCount(2);
		com_category.setBounds(20, 20, 150, 30);
		contentPane.add(com_category);
		
		com_category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> combo = (JComboBox)e.getSource();
				int selectedIndex = combo.getSelectedIndex();
				
				if(selectedIndex==0) {
					dispose();
					try {
						Upload_recruit frame = new Upload_recruit();
						frame.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		
		JLabel lbl_name = new JLabel("기업명");
		lbl_name.setBounds(20, 60, 150, 20);
		contentPane.add(lbl_name);
		
		txt_name = new JTextField();
		txt_name.setText("( DB에서 불러와서 수정 불가 )");
		txt_name.setEditable(false);
		txt_name.setColumns(10);
		txt_name.setBounds(20, 80, 350, 25);
		contentPane.add(txt_name);
		
		JLabel lbl_num = new JLabel("회차");
		lbl_num.setBounds(20, 110, 150, 20);
		contentPane.add(lbl_num);
		
		txt_num = new JTextField();
		txt_num.setColumns(10);
		txt_num.setBounds(20, 130, 350, 25);
		contentPane.add(txt_num);
		
		JLabel lbl_date = new JLabel("개최일시");
		lbl_date.setBounds(20, 160, 150, 20);
		contentPane.add(lbl_date);
		
		txt_date = new JTextField();
		txt_date.setColumns(10);
		txt_date.setBounds(20, 180, 350, 25);
		contentPane.add(txt_date);
		
		JLabel lbl_address = new JLabel("주소");
		lbl_address.setBounds(20, 210, 150, 20);
		contentPane.add(lbl_address);
		
		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(20, 230, 350, 25);
		contentPane.add(txt_address);
		
		JButton btn_upload = new JButton("작성");
		btn_upload.setBounds(540, 190, 75, 30);
		contentPane.add(btn_upload);
		
		JButton btn_close = new JButton("닫기");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_close.setBounds(540, 230, 75, 30);
		contentPane.add(btn_close);
    }
}
