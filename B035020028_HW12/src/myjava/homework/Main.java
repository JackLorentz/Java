package myjava.homework;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/java?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";

    public static void main(String[] args){
        Connection connection = null;
        int id, score;
        String name = "";

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("與資料庫連線成功");

            String sql = "";
            while(true) {
                Statement stat = connection.createStatement();
                System.out.println("1.新增帳號");
                System.out.println("2.刪除帳號");
                System.out.println("3.修改分數");
                System.out.println("4.列出所有");
                System.out.println("5.離開");
                System.out.print("輸入選擇: ");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.print("輸入id: ");
                        id = scanner.nextInt();
                        System.out.print("輸入name: ");
                        name = scanner.next();
                        System.out.print("輸入score: ");
                        score = scanner.nextInt();
                        sql = "INSERT INTO hw12(id, name, score) VALUES ('" + id + "' , '" + name + "' , '" + score + "')";
                        if(stat.executeUpdate(sql) > 0){
                            System.out.println("新增成功");
                        }
                        break;
                    case 2:
                        System.out.print("輸入id: ");
                        id = scanner.nextInt();
                        sql = "DELETE FROM hw12 WHERE id = " + id;
                        if(stat.executeUpdate(sql) > 0){
                            System.out.println("刪除成功");
                        }
                        break;
                    case 3:
                        System.out.print("輸入id: ");
                        id = scanner.nextInt();
                        System.out.print("輸入score: ");
                        score = scanner.nextInt();
                        sql = "UPDATE hw12 SET score = " + score + " WHERE id = " + id;
                        if(stat.executeUpdate(sql) > 0){
                            System.out.println("修改成功");
                        }
                        break;
                    case 4:
                        sql = "SELECT * FROM hw12";
                        ResultSet resultSet = stat.executeQuery(sql);
                        System.out.println("id    name    score");
                        while(resultSet.next()){
                            System.out.println(resultSet.getString("id" )+ "    " +
                                            resultSet.getString("name") + "    " +
                                            resultSet.getString("score"));
                        }
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong Input !");
                        break;
                }
                System.out.println();
                if (stat != null) stat.close();
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try{
                if (connection != null) connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
