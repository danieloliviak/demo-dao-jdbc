package application;

import java.util.Date;

import model.entities.Derpartament;
import model.entities.Seller;

public class App {
    public static void main(String[] args){
        Derpartament obj = new Derpartament(1, "books");
        Seller seller = new Seller(21, "bob", "bob@gmail.com", new Date(), 3000.0, obj);
        System.out.println(seller);
    }
}
