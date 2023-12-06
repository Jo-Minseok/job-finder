package DB_Project;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class CompanyFullView {

	public JFrame frame;
	private JTable table;
	private TableRowSorter<TableModel> sorter;

	public CompanyFullView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("기업 전체 조회");
		frame.setResizable(false);
		frame.setBounds(100, 100, 590, 652);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] head = new String[] {"기업명", "매출", "급여", "지원 경쟁률"};
		// Object부분은 예시로 넣음
		Object[][] data = null;
		try {
			Main.DBConnection();
			String sql = "SELECT 기업명, 매출액, 급여, 경쟁률 FROM 채용_게시글, 기업, 기업회원 WHERE 기업회원.기업명 = 기업.이름 AND 채용_게시글.작성자ID = 기업회원.회원ID";
			Main.stmt = Main.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			Main.rs = Main.stmt.executeQuery(sql);
			Main.rs.last();
			int row_count = Main.rs.getRow();
			Main.rs.beforeFirst();
			data = new Object[row_count][4];
			row_count = 0;
			while(Main.rs.next()) {
				data[row_count][0] = Main.rs.getString("기업명");
				data[row_count][1] = Main.rs.getLong("매출액");
				data[row_count][2] = Main.rs.getLong("급여");
				data[row_count][3] = Main.rs.getInt("경쟁률") + "%";
				row_count++;
			}
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),"데이터 로드 실패", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			Main.DBClose();
		}
		JRadioButton radio_name = new JRadioButton("이름 순");
		radio_name.setBounds(12, 35, 121, 23);
		frame.getContentPane().add(radio_name);
		
		JRadioButton radio_sale = new JRadioButton("매출액 순");
		radio_sale.setBounds(144, 35, 121, 23);
		frame.getContentPane().add(radio_sale);
		
		JRadioButton radio_latest = new JRadioButton("급여 순");
		radio_latest.setBounds(269, 35, 121, 23);
		frame.getContentPane().add(radio_latest);
		
		JRadioButton radio_competition = new JRadioButton("지원 경쟁률 순");
		radio_competition.setBounds(400, 35, 121, 23);
		frame.getContentPane().add(radio_competition);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radio_name);
		buttonGroup.add(radio_sale);
		buttonGroup.add(radio_latest);
		buttonGroup.add(radio_competition);
		
		table = new JTable(data, head);
		sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		
		table.setBounds(8, 78, 554, 493);
		//frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(12, 64, 554, 539);
		//table.setPreferredScrollableViewportSize(new Dimension(700, 600));
		//table.setFillsViewportHeight(true);
		frame.getContentPane().add(scrollPane);
		
		radio_name.addActionListener(e -> sortColumn(0));
		radio_sale.addActionListener(e -> sortColumn(1));
        radio_latest.addActionListener(e -> sortColumn(2));
        radio_competition.addActionListener(e -> sortColumn(3));
	}
	
	private void sortColumn(int column) {
		sorter.setSortKeys(null);
		sorter.toggleSortOrder(column);
	}
}
