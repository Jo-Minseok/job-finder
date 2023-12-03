package DB_Project;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.event.*;

public class Upload_recruit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_job;
	private JTextField txt_category;
	private JTextField txt_salary;
	private JTextField txt_address;
	private JTextField txt_time;
	private JTextField txt_position;
	private JTextField txt_deadline;
	private JTextField txt_human;

	public Upload_recruit() {
		setTitle("채용 게시글 작성");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 593);
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
		
		txt_name = new JTextField();
		txt_name.setEditable(false);
		try {
			Main.DBConnection();
			String sql = "SELECT 기업명 FROM 기업회원 WHERE 회원ID = '" + Main.ID + "'";
			Main.stmt = Main.con.createStatement();
			Main.rs = Main.stmt.executeQuery(sql);
			Main.rs.next();
			txt_name.setText(Main.rs.getString(1));
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),"DB 연결 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
		txt_name.setBounds(20, 80, 350, 25);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		JLabel lbl_job = new JLabel("직종");
		lbl_job.setBounds(20, 110, 150, 20);
		contentPane.add(lbl_job);
		
		txt_job = new JTextField();
		txt_job.setBounds(20, 130, 350, 25);
		contentPane.add(txt_job);
		txt_job.setColumns(10);
		
		JLabel lbl_category = new JLabel("채용 분류");
		lbl_category.setBounds(20, 160, 150, 20);
		contentPane.add(lbl_category);
		
		txt_category = new JTextField();
		txt_category.setBounds(20, 180, 350, 25);
		contentPane.add(txt_category);
		txt_category.setColumns(10);
		
		JLabel lbl_salary = new JLabel("급여");
		lbl_salary.setBounds(20, 210, 150, 20);
		contentPane.add(lbl_salary);
		
		txt_salary = new JTextField();
		txt_salary.setBounds(20, 230, 350, 25);
		contentPane.add(txt_salary);
		txt_salary.setColumns(10);
		
		JLabel lbl_address = new JLabel("근무 지역");
		lbl_address.setBounds(20, 260, 150, 20);
		contentPane.add(lbl_address);
		
		txt_address = new JTextField();
		txt_address.setBounds(20, 280, 350, 25);
		contentPane.add(txt_address);
		txt_address.setColumns(10);
		
		JLabel lbl_time = new JLabel("근무 시간(주)");
		lbl_time.setBounds(20, 310, 150, 20);
		contentPane.add(lbl_time);
		
		txt_time = new JTextField();
		txt_time.setBounds(20, 330, 350, 25);
		contentPane.add(txt_time);
		txt_time.setColumns(10);
		
		JLabel lbl_position = new JLabel("직책");
		lbl_position.setBounds(20, 418, 150, 20);
		contentPane.add(lbl_position);
		
		txt_position = new JTextField();
		txt_position.setBounds(20, 438, 350, 25);
		contentPane.add(txt_position);
		txt_position.setColumns(10);
		
		JLabel lbl_deadline = new JLabel("마감일");
		lbl_deadline.setBounds(20, 468, 150, 20);
		contentPane.add(lbl_deadline);
		
		txt_deadline = new JTextField();
		txt_deadline.setBounds(20, 488, 350, 25);
		contentPane.add(txt_deadline);
		txt_deadline.setColumns(10);
		
		JButton btn_upload = new JButton("작성");
		btn_upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					String sql = "INSERT INTO 채용_게시글 VALUES (0,'" + Main.ID + "','" + txt_job + "','" + txt_category + "','" + txt_salary + "','" + txt_address + "','" + txt_time +"','" + txt_human + "','" + txt_position + "','" + txt_deadline + "')";
					
					Main.con.commit();
				}
				catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),"게시글 업로드 실패", JOptionPane.ERROR_MESSAGE);
					try {
						Main.con.rollback();
					}
					catch(SQLException ex1) {}
				}
				finally {
					Main.DBClose();
				}
			}
		});
		btn_upload.setBounds(540, 390, 75, 30);
		contentPane.add(btn_upload);
		
		JButton btn_close = new JButton("닫기");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_close.setBounds(540, 430, 75, 30);
		contentPane.add(btn_close);
		
		JLabel lbl_human = new JLabel("모집인원");
		lbl_human.setBounds(20, 363, 150, 20);
		contentPane.add(lbl_human);
		
		txt_human = new JTextField();
		txt_human.setColumns(10);
		txt_human.setBounds(20, 383, 350, 25);
		contentPane.add(txt_human);
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
