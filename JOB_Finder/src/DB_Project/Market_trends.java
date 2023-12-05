package DB_Project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Market_trends {

	public JFrame frame;
	private JPanel contentPane;
	private JTextField txt_start;
	private JTextField txt_end;
	private JComboBox com_company;
	private JButton btn_serch;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Market_trends window = new Market_trends();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Market_trends() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 888, 745);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		com_company = new JComboBox();
		com_company.setBounds(0, 0, 192, 30);
		CompanyComboBox();

		frame.setContentPane(new GraphPanel());
		Container c = frame.getContentPane();
		c.setLayout(null);
		GraphPanel graphpanel = new GraphPanel();
		graphpanel.setBounds(0, 0, 0, 0);
		c.add(graphpanel);
		
		txt_start = new JTextField();
		txt_start.setBounds(204, 3, 131, 25);
		txt_start.setHorizontalAlignment(SwingConstants.CENTER);
		txt_start.setColumns(10);
		
		JLabel lb1 = new JLabel("~");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(344, 3, 25, 25);
		
		txt_end = new JTextField();
		txt_end.setBounds(381, 3, 131, 25);
		txt_end.setHorizontalAlignment(SwingConstants.CENTER);
		txt_end.setColumns(10);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(com_company);
		frame.getContentPane().add(txt_start);
		frame.getContentPane().add(lb1);
		frame.getContentPane().add(txt_end);
		
		JButton btn_exit = new JButton("닫기");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_exit.setBounds(763, 10, 97, 23);
		c.add(btn_exit);
		
		btn_serch = new JButton("검색");
		btn_serch.setBounds(524, 4, 97, 23);
		c.add(btn_serch);
		btn_serch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
		// 회사 콤보박스 DB연결
		public ArrayList<String> getcompanyNameData(){
			ArrayList<String> regionList = new ArrayList<>();
			
			try {	           
				Main.DBConnection();

	            String query = "SELECT \"이름\" FROM \"SEOK3764\".\"기업\"";
	            Main.stmt = Main.con.createStatement();
	            Main.rs = Main.stmt.executeQuery(query);

	            while (Main.rs.next()) {
	                String companyName = Main.rs.getString("이름");
	                regionList.add(companyName);
	            }

	            Main.rs.close();
	            Main.stmt.close();
	            Main.con.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return regionList;
	    }
		
		// 회사 콤보박스 리스트 추가
		public void CompanyComboBox() {
		    ArrayList<String> companyNameList = getcompanyNameData();
		    for (String companyName : companyNameList) {
		        com_company.addItem(companyName);
		    }
		}
	}
	
	// 그래프 부분
	class GraphPanel extends JPanel{
		public void paintComponent(Graphics g) {
			
		}
	}
	
