package DB_Project;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.awt.GridLayout;
import javax.swing.JFormattedTextField;

public class Market_trends {

	public JFrame frame;
	private JPanel contentPane;
	private JTextField txt_start;
	private JTextField txt_end;
	private JComboBox txt_company;
	private JButton btn_serch;

	public Market_trends() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 785, 745);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		txt_company = new JComboBox();
		txt_company.setEditable(true);
		txt_company.setBounds(14, 22, 312, 25);
		CompanyComboBox();

		frame.setContentPane(new GraphPanel());
		Container c = frame.getContentPane();
		c.setLayout(null);
		GraphPanel graphpanel = new GraphPanel();
		graphpanel.setBounds(-103, 0, 0, 0);
		c.add(graphpanel);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(txt_company);
		
		JButton btn_exit = new JButton("닫기");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btn_exit.setBounds(660, 4, 97, 23);
		c.add(btn_exit);
		
		JPanel panel = new JPanel();
		panel.setBounds(380, 37, 377, 53);
		c.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("[전체/회사이름별]");
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("남여 성비:");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("현재 채용중인 공고:");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_2 = new JLabel("평균 연봉:");
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1 = new JLabel("시장 동향:");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("매출액:");
		panel.add(lblNewLabel_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(-33, 57, 411, 33);
		c.add(panel_1);
		
		txt_start = new JTextField();
		panel_1.add(txt_start);
		txt_start.setHorizontalAlignment(SwingConstants.CENTER);
		txt_start.setColumns(10);
		
		JLabel lb1 = new JLabel("~");
		panel_1.add(lb1);
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		
		txt_end = new JTextField();
		panel_1.add(txt_end);
		txt_end.setHorizontalAlignment(SwingConstants.CENTER);
		txt_end.setColumns(10);
		
		btn_serch = new JButton("검색");
		panel_1.add(btn_serch);
		
		JPanel panel_graph = new JPanel();
		panel_graph.setBounds(12, 92, 745, 604);
		c.add(panel_graph);
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
		        txt_company.addItem(companyName);
		    }
		}
		
		// 그래프 부분
		public class PostChart extends Apllication{
			@Override
		}
}