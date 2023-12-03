package DB_Project;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class Upload_briefing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_business;
	private JTextField txt_num;
	private JTextField txt_date;
	private JTextField txt_address;
	private JTextField txt_name;

	public Upload_briefing() {
		setTitle("설명회 게시글 작성");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 411);
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
		
		
		JLabel lbl_business = new JLabel("기업명");
		lbl_business.setBounds(20, 60, 150, 20);
		contentPane.add(lbl_business);
		
		txt_business = new JTextField();
		try {
			Main.DBConnection();
			String sql = "SELECT 기업명 FROM 기업회원 WHERE 회원ID = '" + Main.ID + "'";
			Main.stmt = Main.con.createStatement();
			Main.rs = Main.stmt.executeQuery(sql);
			Main.rs.next();
			txt_business.setText(Main.rs.getString(1));
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),"DB 연결 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
		txt_business.setEditable(false);
		txt_business.setColumns(10);
		txt_business.setBounds(20, 80, 350, 25);
		contentPane.add(txt_business);
		
		JLabel lbl_num = new JLabel("회차");
		lbl_num.setBounds(20, 176, 150, 20);
		contentPane.add(lbl_num);
		
		txt_num = new JTextField();
		txt_num.setColumns(10);
		txt_num.setBounds(20, 196, 350, 25);
		contentPane.add(txt_num);
		
		JLabel lbl_date = new JLabel("개최일시");
		lbl_date.setBounds(20, 226, 150, 20);
		contentPane.add(lbl_date);
		
		txt_date = new JTextField();
		txt_date.setColumns(10);
		txt_date.setBounds(20, 246, 350, 25);
		contentPane.add(txt_date);
		
		JLabel lbl_address = new JLabel("개최 주소");
		lbl_address.setBounds(20, 276, 150, 20);
		contentPane.add(lbl_address);
		
		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(20, 296, 350, 25);
		contentPane.add(txt_address);
		
		JButton btn_upload = new JButton("작성");
		btn_upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					Main.con.setAutoCommit(false);
					String sql = "INSERT INTO 채용_설명회 VALUES ('" + txt_business.getText() + "','"+ txt_name.getText() + "','" + txt_num.getText() + "','" + txt_date.getText() + "','"+ txt_address.getText() + "')";
					Main.stmt = Main.con.createStatement();
					Main.stmt.executeUpdate(sql);
					
					sql = "UPDATE 기업회원 SET 포인트 = 포인트 - 500 WHERE 회원ID = '" + Main.ID + "'";
					Main.stmt = Main.con.createStatement();
					Main.stmt.executeUpdate(sql);
					
					sql = "INSERT INTO 기업_포인트_수정_내역 VALUES ('" + Main.ID + "','사용','500')";
					Main.stmt = Main.con.createStatement();
					Main.stmt.executeUpdate(sql);
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
					try {
						Main.con.setAutoCommit(true);
					}
					catch(SQLException ex2) {}
				}
			}
		});
		btn_upload.setBounds(439, 205, 75, 30);
		contentPane.add(btn_upload);
		
		JButton btn_close = new JButton("닫기");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_close.setBounds(439, 246, 75, 30);
		contentPane.add(btn_close);
		
		JLabel lbl_name = new JLabel("설명회명");
		lbl_name.setBounds(20, 121, 150, 20);
		contentPane.add(lbl_name);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(20, 141, 350, 25);
		contentPane.add(txt_name);
    }
}
