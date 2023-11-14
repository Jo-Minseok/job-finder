import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Main extends JDialog {
     Connection con = null;
    String url = "jdbc:oracle:thin:@minseok821lab.kro.kr:1521:orcl";
    String id = "seok3764";
    String pw = "0424";
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Main() {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("드라이버 적재 성공");
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 적재 실패");
        }
        setContentPane(contentPane);
        setModal(true);aaa
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // X 클릭 시 onCancel() 호출
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // ESCAPE 시 onCancel() 호출
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("드라이버 적재 성공");
        }
        catch(ClassNotFoundException e){
            System.out.println("드라이버 적재 실패");
        }
    }

    private void onOK() {
        // 이곳에 코드 추가
        dispose();
    }

    private void onCancel() {
        // 필요한 경우 이곳에 코드 추가
        dispose();
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

    public static void main(String[] args) throws SQLException {
        Main dialog = new Main();
        dialog.sqlRun();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
