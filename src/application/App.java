package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class App {
    public static void main(String[] args){
        Departament obj = new Departament(1, "books");
        Seller seller = new Seller(21, "bob", "bob@gmail.com", new Date(), 3000.0, obj);
        SellerDao sellerDao = DaoFactory.creteSellerDao();
        System.out.println(seller);
    }
}
