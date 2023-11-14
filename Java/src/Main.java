import java.sql.*;

public class Main {
    Connection con = null;
    String url = "jdbc:oracle:thin:@minseok821lab.kro.kr:1521:orcl";
    String id = "seok3764";
    String pw = "0424";

    public Main(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("드라이버 적재 성공");
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 적재 실패");
        }
    }

    private void DB_Connect(){
        try{
            con = DriverManager.getConnection(url,id,pw);
            System.out.println("DB 연결 성공");
        }
        catch(SQLException e){
            System.out.println("DB 연결 실패");
        }
    }

    private void sqlRun()throws SQLException {
        String query = "select * from 개인회원";
        try {
            DB_Connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("회원ID") + "\t" + rs.getString("비밀번호"));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
    }
    public static void main(String[] args) throws SQLException{
        Main DB = new Main();
        DB.sqlRun();
        LOGIN Login = new LOGIN();
        Login.pack();
        Login.setVisible(true);
        System.exit(0);
    }
}
