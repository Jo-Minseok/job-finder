package DB_Project;

import java.sql.*;

import javax.swing.JOptionPane;

public class Main {
	static Connection con = null;
	static String url = "jdbc:oracle:thin:@minseok821lab.kro.kr:1521:orcl";
	static String id = "seok3764";
	static String pw = "0424";
	
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,id,pw);
			Login window = new Login();
			window.frame.setVisible(true);
		}
		catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "드라이버 적재 실패","DB 오픈 실패", JOptionPane.ERROR_MESSAGE);
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "연결 실패","DB 오픈 실패", JOptionPane.ERROR_MESSAGE);
		}
	}
}
