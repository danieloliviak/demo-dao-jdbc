package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbExeception;
import model.dao.DepartamentDao;
import model.entities.Departament;

public class DepartmentDaoJDBC implements DepartamentDao {
    private Connection conn;
    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Departament obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "INSERT INTO Departament (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                st.setString(1, obj.getName());

                int rowsAffected = st.executeUpdate();
                if(rowsAffected>0){
                    ResultSet rs = st.getGeneratedKeys();
                    if(rs.next()){
                        int id = rs.getInt(1);
                        obj.setId(id);
                    }
                    DB.closeResultSet(rs);
                }else{
                    throw new DbExeception("Unexpected error! No rows affected!");
                }
            } catch (SQLException e) {
            throw new DbExeception(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Departament obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Departament findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT * FROM Departament WHERE id = ? "
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Departament dep = instantieteDepartament(rs);
                return dep;
            }
            return null;
        } catch (SQLException e) {
            throw new DbExeception(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Departament> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT * FROM Departament ORDER BY id ");
            rs = st.executeQuery();
            List<Departament> list = new ArrayList<>();
            Departament dep = null;
            while(rs.next()){
                dep = instantieteDepartament(rs);
                list.add(dep);
            }
            return list;
        } catch (SQLException e) {
            throw new DbExeception(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Departament instantieteDepartament(ResultSet rs) throws SQLException {
        Departament dep = new Departament();
        dep.setId(rs.getInt("id"));
        dep.setName(rs.getString("nome"));
        return dep;
    }
    
}
