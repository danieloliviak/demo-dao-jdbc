package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class App {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
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

        System.out.println("\n=== test 4: seller insert");
        Seller newSeller = new Seller(null, "greg", new Date(), departament, "greg@gmail.com", 4000.0);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = "+ newSeller.getId());

        System.out.println("\n=== test 5: seller update");
        seller = sellerDao.findById(9);
        seller.setName("Marthe king");
        sellerDao.update(seller);
        System.out.println("Update completed");

        System.out.println("\n=== test 6: seller delete write a id:");
        int id = sc.nextInt();
        sellerDao.delete(id);
        System.out.println("delete completed");

        sc.close();
    }
}
