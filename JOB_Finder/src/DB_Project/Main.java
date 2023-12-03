package DB_Project;

import java.sql.*;

import javax.swing.JOptionPane;

public class Main {
	static Connection con = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static CallableStatement cstmt = null;
	static ResultSet rs = null;
	
	static String url = "jdbc:oracle:thin:@minseok821lab.kro.kr:1521:orcl";
	static String id = "seok3764";
	static String pw = "0424";
	static String mode = null;
	
	public static void main(String[] args) {
		DriverLoad();
		DBConnection();
		Register window = new Register();
		window.frame.setVisible(true);
		DBClose();
	}
	
	public static void DriverLoad() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "드라이버 적재 실패","DB 오픈 실패", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void DBConnection() {
		try {
			con = DriverManager.getConnection(url,id,pw);
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "연결 실패","DB 오픈 실패", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void DBClose() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(cstmt != null) {
				cstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"DB 연결 오류", JOptionPane.ERROR_MESSAGE);
		}
	}
}
