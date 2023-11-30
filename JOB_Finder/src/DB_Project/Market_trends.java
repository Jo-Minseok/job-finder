import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class Market_trends extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf_start;
	private JTextField tf_end;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Market_trends frame = new Market_trends();
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
	public Market_trends() {
		setTitle("시장 동향");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLocation(10, 10);
		panel.setSize(1162, 30);
		contentPane.add(panel);
		
		JComboBox cb = new JComboBox();
		cb.setBounds(0, 0, 120, 30);
		cb.setModel(new DefaultComboBoxModel(new String[] {"전체", "회사A", "회사B", "회사C"}));

		//setContentPane(new GraphPanel());
		setLayout(new BorderLayout());
		Container c = getContentPane();
		GraphPanel graphpanel = new GraphPanel();
		c.add(graphpanel, BorderLayout.CENTER);
		
		tf_start = new JTextField();
		tf_start.setBounds(130, 5, 120, 25);
		tf_start.setText("시작일");
		tf_start.setHorizontalAlignment(SwingConstants.CENTER);
		tf_start.setColumns(10);
		
		JLabel lb1 = new JLabel("~");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(250, 5, 25, 25);
		
		tf_end = new JTextField();
		tf_end.setBounds(275, 5, 120, 25);
		tf_end.setText("종료일");
		tf_end.setHorizontalAlignment(SwingConstants.CENTER);
		tf_end.setColumns(10);
		
		JButton bt_close = new JButton("닫기");
		bt_close.setBounds(1087, 0, 75, 30);
		panel.setLayout(null);
		panel.add(cb);
		panel.add(tf_start);
		panel.add(lb1);
		panel.add(tf_end);
		panel.add(bt_close);
		
		
	}
	
	class GraphPanel extends JPanel{
		public void paintComponent(Graphics g) {
			// 그래프 그리기
			int v1=20, v2=60, v3=30, v4=10, v5=50, v6=40;
			
			g.clearRect(0, 0, getWidth(), getHeight());

			g.drawLine(50, 140, 50, 470);
			
			for(int i = 1; i<11; i++) {
				g.drawString(i*10+"", 25, 475-(30*i));         // 10 ~ 100 표기
				g.drawLine(50,  470-(30*i),  660, 470-(30*i)); // 10 마다 선 생성
			}
			g.drawLine(50,  470, 660,  470); 
			
			g.drawString("현재 채용중인 공고", 70, 490);
			g.drawString("평균 연봉", 195, 490);
			g.drawString("매출액", 295, 490);
			g.drawString("옵션4", 405, 490);
			g.drawString("옵션5", 505, 490);
			g.drawString("옵션6", 605, 490);
			
			g.setColor(Color.BLUE);
			if(v1>0)
				g.fillRect(110, 470-v1*3, 20, v1*3); // 가로(20)크기의 막대 그래프 생성
			if(v2>0)
				g.fillRect(210, 470-v2*3, 20, v2*3);
			if(v3>0)
				g.fillRect(310, 470-v3*3, 20, v3*3);
			if(v4>0)
				g.fillRect(410, 470-v4*3, 20, v4*3);
			if(v5>0)
				g.fillRect(510, 470-v5*3, 20, v5*3);
			if(v6>0)
				g.fillRect(610, 470-v6*3, 20, v6*3);
		}
	}
}
