package yutt.conn;

import java.sql.*;

public class connect {
    public static void main(String[] args) {
        {
            //????Connection????
            Connection con;
            //??????????
            String driver = "com.mysql.cj.jdbc.Driver";
            //URL????????????????mydata
            String url = "jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            //MySQL????????????
            String user = "root";
            //MySQL???????????
            String password = "0000";
            //????????????
            try {
                //????????????
                Class.forName(driver);
                //1.getConnection()??????????MySQL???????
                con = DriverManager.getConnection(url,user,password);
                if(!con.isClosed())
                    System.out.println("Succeeded connecting to the Database!");
                //2.????statement????????????SQL?????
                Statement statement = con.createStatement();
                //???§Ö?SQL???
                String sql = "select * from employee";
                //3.ResultSet?????????????????????
                ResultSet rs = statement.executeQuery(sql);
                System.out.println("-----------------");
                System.out.println("??§ß?????????:");
                System.out.println("-----------------");
                System.out.println("????" + "\t" + "???");
                System.out.println("-----------------");

                String lastName = null;
                String email = null;
                while(rs.next()){
                    //???stuname????????
                    lastName = rs.getString("lastName");
                    //???stuid????????
                    email = rs.getString("email");

                    //??????
                    System.out.println(lastName + "\t" + email);
                }
                rs.close();
                con.close();
            } catch(ClassNotFoundException e) {
                //?????????????????
                System.out.println("Sorry,can`t find the Driver!");
                e.printStackTrace();
            } catch(SQLException e) {
                //??????????????????
                e.printStackTrace();
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }finally{
                System.out.println("??????????????????");
            }
        }
    }
}