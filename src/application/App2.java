package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartamentDao;
import model.entities.Departament;

public class App2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        DepartamentDao departamentDao = DaoFactory.creteDepartamentDao();
        
        System.out.println("=== test 1: department findById ======");
        Departament dep = departamentDao.findById(3);
        System.out.println(dep);

        System.out.println("\n=== test 2: department findAll");
        List<Departament> list = departamentDao.findAll();
        for(Departament d : list){
            System.out.println(d);
        }

        // System.out.println("\n=== test 3: department insert");
        // Departament newDepartament = new Departament(null, "PC");
        // departamentDao.insert(newDepartament);
        // System.out.println("Inserted! New id = "+ newDepartament.getId());

        System.out.println("\n=== test 4: department update");
        dep = departamentDao.findById(5);
        dep.setName("components");
        departamentDao.update(dep);
        System.out.println("Update completed");

        // System.out.println("\n=== test 6: seller delete write a id:");
        // int id = sc.nextInt();
        // departamentDao.delete(id);
        // System.out.println("delete completed");

        sc.close();
    }
}
