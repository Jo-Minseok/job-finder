package DB_Project;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.*;
import java.awt.event.*;

public class Upload_recruit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField recruit_tf1;
	private JTextField recruit_tf2;
	private JTextField recruit_tf3;
	private JTextField recruit_tf4;
	private JTextField recruit_tf5;
	private JTextField recruit_tf6;
	private JTextField recruit_tf7;
	private JTextField recruit_tf8;

	public Upload_recruit() {
		setTitle("채용 게시글 작성");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox com_category = new JComboBox();
		com_category.setMaximumRowCount(2);
		com_category.setModel(new DefaultComboBoxModel(new String[] {"채용 게시글", "설명회 게시글"}));
		com_category.setSelectedIndex(0);
		com_category.setBounds(20, 20, 150, 30);
		contentPane.add(com_category);
		
		com_category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox)e.getSource();
				int selectedIndex = combo.getSelectedIndex();
				
				if(selectedIndex==1) {
					dispose();
					openWindow();
				}
			}
		});
		
		JLabel lbl_name = new JLabel("기업명");
		lbl_name.setBounds(20, 60, 150, 20);
		contentPane.add(lbl_name);
		
		recruit_tf1 = new JTextField();
		recruit_tf1.setEditable(false);
		recruit_tf1.setText("( DB에서 불러와서 수정 불가 )");
		recruit_tf1.setBounds(20, 80, 350, 25);
		contentPane.add(recruit_tf1);
		recruit_tf1.setColumns(10);
		
		JLabel recruit_lb2 = new JLabel("직종");
		recruit_lb2.setBounds(20, 110, 150, 20);
		contentPane.add(recruit_lb2);
		
		recruit_tf2 = new JTextField();
		recruit_tf2.setBounds(20, 130, 350, 25);
		contentPane.add(recruit_tf2);
		recruit_tf2.setColumns(10);
		
		JLabel recruit_lb3 = new JLabel("채용 분류");
		recruit_lb3.setBounds(20, 160, 150, 20);
		contentPane.add(recruit_lb3);
		
		recruit_tf3 = new JTextField();
		recruit_tf3.setBounds(20, 180, 350, 25);
		contentPane.add(recruit_tf3);
		recruit_tf3.setColumns(10);
		
		JLabel recruit_lb4 = new JLabel("급여");
		recruit_lb4.setBounds(20, 210, 150, 20);
		contentPane.add(recruit_lb4);
		
		recruit_tf4 = new JTextField();
		recruit_tf4.setBounds(20, 230, 350, 25);
		contentPane.add(recruit_tf4);
		recruit_tf4.setColumns(10);
		
		JLabel recruit_lb5 = new JLabel("근무 지역");
		recruit_lb5.setBounds(20, 260, 150, 20);
		contentPane.add(recruit_lb5);
		
		recruit_tf5 = new JTextField();
		recruit_tf5.setBounds(20, 280, 350, 25);
		contentPane.add(recruit_tf5);
		recruit_tf5.setColumns(10);
		
		JLabel recruit_lb6 = new JLabel("근무 시간(주)");
		recruit_lb6.setBounds(20, 310, 150, 20);
		contentPane.add(recruit_lb6);
		
		recruit_tf6 = new JTextField();
		recruit_tf6.setBounds(20, 330, 350, 25);
		contentPane.add(recruit_tf6);
		recruit_tf6.setColumns(10);
		
		JLabel recruit_lb7 = new JLabel("직책");
		recruit_lb7.setBounds(20, 360, 150, 20);
		contentPane.add(recruit_lb7);
		
		recruit_tf7 = new JTextField();
		recruit_tf7.setBounds(20, 380, 350, 25);
		contentPane.add(recruit_tf7);
		recruit_tf7.setColumns(10);
		
		JLabel recruit_lb8 = new JLabel("마감일");
		recruit_lb8.setBounds(20, 410, 150, 20);
		contentPane.add(recruit_lb8);
		
		recruit_tf8 = new JTextField();
		recruit_tf8.setBounds(20, 430, 350, 25);
		contentPane.add(recruit_tf8);
		recruit_tf8.setColumns(10);
		
		JButton bt_upload = new JButton("작성");
		bt_upload.setBounds(540, 390, 75, 30);
		contentPane.add(bt_upload);
		
		JButton bt_close = new JButton("닫기");
		bt_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bt_close.setBounds(540, 430, 75, 30);
		contentPane.add(bt_close);
	}
	
	private void openWindow() {
		try {
			Upload_briefing frame = new Upload_briefing();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
