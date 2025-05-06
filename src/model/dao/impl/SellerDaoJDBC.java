package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                "SELECT seller.id, seller.nome, seller.email, seller.baseSalary, seller.birth_date, seller.departament_id, Departament.nome as DepName "+
                "FROM seller INNER JOIN Departament "+
                "ON seller.departament_id = Departament.id "+
                "WHERE seller.id = ? ");
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
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT seller.id, seller.nome, seller.email, seller.baseSalary, seller.birth_date, seller.departament_id, Departament.nome as DepName "+
                "FROM seller INNER JOIN Departament "+
                "ON seller.departament_id = Departament.id "+
                "ORDER BY seller.nome ");
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Departament> map = new HashMap<>();
            while(rs.next()){
                Departament dep = map.get(rs.getInt("departament_id"));
                if(dep==null){
                    dep = instantieteDepartament(rs);
                    map.put(rs.getInt("departament_id"), dep);
                }
                Seller obj = instantieteSeller(rs, dep);
                list.add(obj);
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

    @Override
    public List<Seller> findByDepartment(Departament departament) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT seller.id, seller.nome, seller.email, seller.baseSalary, seller.birth_date, seller.departament_id, Departament.nome AS DepName "+
                "FROM seller INNER JOIN Departament "+
                "ON seller.departament_id = Departament.id "+
                "WHERE Departament.id = ? "+
                "ORDER BY seller.nome ");
            st.setInt(1, departament.getId());
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Departament> map = new HashMap<>();
            while(rs.next()){
                Departament dep = map.get(rs.getInt("departament_id"));
                if(dep==null){
                    dep = instantieteDepartament(rs);
                    map.put(rs.getInt("departament_id"), dep);
                }
                Seller obj = instantieteSeller(rs, dep);
                list.add(obj);
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
}
