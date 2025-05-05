package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbExeception;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
    private Connection conn;
    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        // TODO
    }

    @Override
    public void update(Seller obj) {
        // TODO
    }

    @Override
    public void delete(Integer id) {
        // TODO
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "select seller. * Departament.nome as DepName"+
                "from seller inner join Departament"+
                "on seller.departament_id = Departament.id"+
                "where seller.id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Departament dep = instantieteDepartament(rs);
                Seller obj = instantieteSeller(rs, dep);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbExeception(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instantieteSeller(ResultSet rs, Departament dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("nome"));
        obj.setEmail(rs.getString("email"));
        obj.setBaseSalary(rs.getDouble("baseSalary"));
        obj.setBirthDate(rs.getDate("birth_date"));
        obj.setDerpartament(dep);
        return obj;
    }

    private Departament instantieteDepartament(ResultSet rs) throws SQLException {
        Departament dep = new Departament();
        dep.setId(rs.getInt("departament_id"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
