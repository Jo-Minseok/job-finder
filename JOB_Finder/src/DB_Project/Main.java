package DB_Project;

import java.sql.*;

import javax.swing.JOptionPane;

public class Main {
	static Connection con = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static CallableStatement cstmt = null;
	static ResultSet rs = null;
	
	static String DB_url = "jdbc:oracle:thin:@minseok821lab.kro.kr:1521:orcl";
	static String DB_id = "seok3764";
	static String DB_pw = "0424";
	
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
	
	public static String Won(long amount) {
        long hundred_million = amount / 100000000;                // 억
        long ten_million = (amount % 100000000) / 10000000;        // 천만
        long million = (amount % 10000000) / 1000000;            // 백만

        StringBuilder format = new StringBuilder();
        boolean isAnyPreviousUnitPresent = false;

        if (hundred_million > 0) {
            format.append(hundred_million).append("억");
            isAnyPreviousUnitPresent = true;
        }
        
        if (ten_million > 0) {
            format.append(ten_million);
            if (!isAnyPreviousUnitPresent) {
                format.append("천만원");
            } else {
                format.append("천");
            }
        }
        
        if (million > 0) {
            format.append(million);
            if (!isAnyPreviousUnitPresent) {
                format.append("백만원");
            } else {
                format.append("백");
            }
        }

        if (hundred_million > 0 && ten_million == 0 && million == 0) {
            format.append("원");
        } else if (format.length() > 0 && !format.toString().endsWith("원")) {
            format.append("만원");
        }

        return format.toString().trim();
    }
}
