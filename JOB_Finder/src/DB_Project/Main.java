package DB_Project;

import java.sql.*;

import javax.swing.JOptionPane;

public class Main {
	static Connection con = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static CallableStatement cstmt = null;
	static ResultSet rs = null;
	
	static String DB_url = "jdbc:oracle:thin:@unknown:1521:orcl";
	static String DB_id = "unknown";
	static String DB_pw = "unknwon";
	
	static String mode = null;
	static String ID = null;
	public static void main(String[] args) {
		DriverLoad();
		DBConnection();
		Login window = new Login();
		window.frame.setVisible(true);
		DBClose();
	}
	
	public static void DriverLoad() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "드라이버 적재 실패","DB 오픈 실패", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	public static void DBConnection() {
		try {
			con = DriverManager.getConnection(DB_url,DB_id,DB_pw);
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "연결 실패","DB 오픈 실패", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
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
	
	public static void Reset() {
		mode = null;
		ID = null;
	}
	
	public static String Won(long money) {
        String[] num = {"","1","2","3","4","5","6","7","8","9"};
        String[] num2 = {"","십","백","천"};
        String[] num3 = {"","만","억","조","경"};
        
        StringBuffer result = new StringBuffer();
        int leng = String.valueOf(money).length();
        int initInt = 0;
        
        for(int i=leng-1;i>=0;i--) {
        	initInt=Integer.parseInt(String.valueOf(String.valueOf(money).charAt(leng-i-1)));
        	if(initInt>0) {
        		result.append(num[initInt]);
        		result.append(num2[i%4]);
        	}
        	if(i%4==0) {
        		result.append(num3[i/4]);
        		result.append(" ");
        	}
        }
        result.append("원");
        return result.toString();
    }
}
