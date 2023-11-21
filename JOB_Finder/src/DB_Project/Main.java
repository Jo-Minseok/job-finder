package DB_Project;

import java.sql.*;

public class Main {
	static Connection con = null;
	static String url = "jdbc:oracle:thin:@minseok821lab.kro.kr:1521:orcl";
	static String id = "seok3764";
	static String pw = "0424";
	
	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.frame.setVisible(true);
			Register window2 = new Register();
			window2.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url,id,pw);
			System.out.println("데이터베이스 연결 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 찾기 실패");
		} catch(SQLException e) {
			System.out.println("연결에 실패");
		}
		System.out.println("test");
	}

}
