import java.sql.*;

public class App {

public static void oracleConnection(
        String db_driver,
        String db_type,
        String db_driver_type,
        String db_host_name,
        String db_posrt,
        String db_service_name,
        String db_username,
        String db_password){
            String Connection_string = String.format("%s:%s:%s:@%s:%s/%s",db_driver, db_type,db_driver_type, db_host_name, db_posrt, db_service_name);
            System.out.println(Connection_string);
        }

    public static void main(String[] args) throws Exception {
        oracleConnection("jdbc", "oracle", "thin", "minseok821lab.kro.kr", "3306", "orcl", "seok3764", "0424");
    }
}
