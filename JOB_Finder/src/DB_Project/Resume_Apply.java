package DB_Project;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Resume_Apply {

	public JFrame frame;
	private Job_posting Pass;
	private JComboBox<String> comboBox;

	public Resume_Apply(Job_posting Pass) {
		this.Pass = Pass;
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("지원");
		frame.setBounds(100, 100, 450, 160);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setBounds(12, 48, 301, 23);
		frame.getContentPane().add(comboBox);
		AddressComboBox();
		
		JButton btnNewButton = new JButton("지원");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.DBConnection();
					String sql = "INSERT INTO 지원 VALUES (?,?,?,?,?)";
					Main.pstmt = Main.con.prepareStatement(sql);
					Main.pstmt.setInt(1, Pass.Post_ID);
					Main.pstmt.setString(2,Main.ID);
					Main.pstmt.setString(3, comboBox.getSelectedItem().toString());
					Main.pstmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
					Main.pstmt.setString(5, "N");
					Main.pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "지원을 하였습니다!", "지원 완료",JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					Pass.frame.dispose();
				}
				catch(SQLException ex) {
					
				}
				finally {
					Main.DBClose();
				}
			}
		});
		btnNewButton.setBounds(325, 48, 97, 23);
		frame.getContentPane().add(btnNewButton);
	}
	public ArrayList<String> getResumeData(){
		ArrayList<String> ResumeList = new ArrayList<>();
		
		try {	           
            Main.DBConnection();

            String query = "SELECT 이력서명 FROM 이력서 WHERE 작성자ID = '" + Main.ID +"'";
            java.sql.Statement stmt = Main.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String ResumeName = rs.getString("이력서명");
                ResumeList.add(ResumeName);
                //System.out.println("Retrieved Region: " + regionName);
            }

        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), "오류",JOptionPane.ERROR_MESSAGE);
        }
		finally {
			Main.DBClose();
		}

        return ResumeList;
    }
	
	// 지역 콤보박스 리스트 추가
	public void AddressComboBox() {
	    ArrayList<String> addressList = getResumeData();
	    for (String address : addressList) {
	    	comboBox.addItem(address);
	        //System.out.println("Retrieved Region: " + address);
	    }
	}
}
