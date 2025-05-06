package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class App {
    public static void main(String[] args){
        SellerDao sellerDao = DaoFactory.creteSellerDao();
        
        System.out.println("=== test 1: seller findById ======");
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);

        System.out.println("\n=== test 2: seller findByDepartment");
        Departament departament = new Departament(1, null);
        List<Seller> list = sellerDao.findByDepartment(departament);
        for(Seller obj:list){
            System.out.println(obj);
        }

        System.out.println("\n=== test 3: seller findAll");
        list = sellerDao.findAll();
        for(Seller obj:list){
            System.out.println(obj);
        }
    }
}
