package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class App {
    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        Statement st = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from itens");
            while(rs.next()){
                System.out.println(rs.getInt("id")+", "+rs.getString("nome"));
            }
            pst = conn.prepareStatement(
                "insert into seller " +
                "(nome, birth_date, departament_id, email) " +
                "values " +
                "(?, ?, ?, ?)"
            );
            pst.setString(1, "carl purple");
            pst.setDate(2, new java.sql.Date(sdf.parse("22/06/2001").getTime()));
            pst.setInt(3, 1);
            pst.setString(4, "carlpurple@gmail.com");
            int rows = pst.executeUpdate();
            System.out.printf("done! Rows affected: %d%n", rows);
            rs = st.executeQuery("select * from seller ");
            while(rs.next()){
                System.out.println(rs.getInt("id")+", "+
                rs.getString("nome")+", "+
                rs.getDate("birth_date")+", "+
                rs.getInt("departament_id")+", "+
                rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        } finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
