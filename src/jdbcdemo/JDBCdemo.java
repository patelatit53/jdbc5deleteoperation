package jdbcdemo;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
public class JDBCdemo {

    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/myd123", "root", "root");
            System.out.println("connection established");
            System.out.println("T1 before Delete ...");
            showTable(conn, "T1");
            Statement stmt = conn.createStatement();
            stmt.execute("delete from T1 where C1 = 99");
            System.out.println("T1 after Delete ...");
            showTable(conn, "T1");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException cnf) {
            System.out.println("Class not found");
        }
    }
    
    static void showTable(Connection conn , String tableName)
    {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from "+tableName);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            for(int count = 1 ; count<=rsmd.getColumnCount();count++)
            {
                System.out.print(rsmd.getColumnName(count)+"\t");
            }
            System.out.println("");
            while(rs.next())
            {
                for(int count = 1 ; count<=rsmd.getColumnCount();count++)
                {
                    System.out.print(rs.getObject(count)+"\t");
                }
                System.out.println("");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

