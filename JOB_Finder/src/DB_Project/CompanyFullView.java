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
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class CompanyFullView {

	public JFrame frame;
	private JTable table;
	private TableRowSorter<TableModel> sorter;

	public CompanyFullView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 620);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] head = new String[] {"기업명", "매출", "게시글 등록 날짜", "지원 경쟁률"};
		// Object부분은 예시로 넣음
		Object[][] data = new Object[][] {
			{"동의대", "200", "2023-11-27","30%"},
			{"동서대", "150", "2023-11-29","40%"},
			{"부경대", "300", "2023-11-01","10%"},
			{"부산대", "500", "2022-11-27","35%"}
		};
		
		JRadioButton Name_radio = new JRadioButton("이름 순");
		Name_radio.setBounds(8, 35, 65, 23);
		frame.getContentPane().add(Name_radio);
		
		JRadioButton Sale_radio = new JRadioButton("매출 순");
		Sale_radio.setBounds(87, 35, 65, 23);
		frame.getContentPane().add(Sale_radio);
		
		JRadioButton Latest_radio = new JRadioButton("게시글 등록 최신 순");
		Latest_radio.setBounds(169, 35, 138, 23);
		frame.getContentPane().add(Latest_radio);
		
		JRadioButton Competitiobn_radio = new JRadioButton("지원 경쟁률 순");
		Competitiobn_radio.setBounds(321, 35, 121, 23);
		frame.getContentPane().add(Competitiobn_radio);
		
		JButton Exit_btn = new JButton("닫기");
		Exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		Exit_btn.setBounds(489, 10, 73, 32);
		frame.getContentPane().add(Exit_btn);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(Name_radio);
		buttonGroup.add(Sale_radio);
		buttonGroup.add(Latest_radio);
		buttonGroup.add(Competitiobn_radio);
		
		table = new JTable(data, head);
		sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		
		table.setBounds(8, 78, 554, 493);
		//frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(8, 78, 554, 493);
		//table.setPreferredScrollableViewportSize(new Dimension(700, 600));
		//table.setFillsViewportHeight(true);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("기업 전체 조회");
		lblNewLabel.setBounds(8, 10, 94, 15);
		frame.getContentPane().add(lblNewLabel);
		
		Name_radio.addActionListener(e -> sortColumn(0));
		Sale_radio.addActionListener(e -> sortColumn(1));
        Latest_radio.addActionListener(e -> sortColumn(2));
        Competitiobn_radio.addActionListener(e -> sortColumn(3));
	}
	
	private void sortColumn(int column) {
		sorter.setSortKeys(null);
		sorter.toggleSortOrder(column);
	}
}
