package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/**
 * {@code Shop} class represent a navigation that manage products
 * @version 4.0
 * @author oracle
 */
public class Shop {


    public static void main(String[] args) {

        ProductManager pm = new ProductManager();
        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
//        Product p2 = pm.createProduct(102, "Coffe", BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
//        Product p3 = pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STAR);
//        Product p4 = pm.createProduct(105, "Cookie", BigDecimal.valueOf(3.99), Rating.TWO_STAR, LocalDate.now());
//        Product p6 = pm.createProduct(104, "Chocolate", BigDecimal.valueOf(2.99), Rating.FIVE_STAR);
//        Product p7 = pm.createProduct(104, "Chocolate", BigDecimal.valueOf(2.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));

//        Product p1 = new Drink(101, "Tea", BigDecimal.valueOf(1.99), Rating.THREE_STAR);
//        Product p2 = new Drink(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
//        Product p3 = new Food(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STAR , LocalDate.now().plusDays(2));
//        Product p4 = new Food(105, "Cookie", BigDecimal.valueOf(3.99), Rating.TWO_STAR, LocalDate.now());
//        Product p5 = p3.applyRating(Rating.TWO_STAR);
////        Product p6 = new Drink(104,"Chocolate",BigDecimal.valueOf(2.99),Rating.FIVE_STAR);
////        Product p7 =  new Food(104,"Chocolate",BigDecimal.valueOf(2.99),
////                                Rating.FIVE_STAR, LocalDate.now().plusDays(2));
//
//        Product p8 = p4.applyRating(Rating.FIVE_STAR);
//        Product p9 = p1.applyRating(Rating.TWO_STAR);




//        List<Product> listProducts = List.of(p1, p2, p3, p4, p5 );
//       // System.out.println(p3.getBestBefore);
//
//        for (Product p:listProducts) {
//            System.out.println(p);
////            System.out.println("Producto " + p.getId() + ": " + p.getName() + " " + p.getPrice() + " " + p.getDiscount() + " " + p.getRating().getStars());
//
//        }
//        System.out.println(p6.equals(p7));
//        System.out.println(p8);
//        System.out.println(p9);
//        if (p3 instanceof Food f ){
//            System.out.println(f.getBestBefore());
//        }
//
//        if (p3 instanceof Food ){
//            System.out.println(((Food)p3).getBestBefore());
//        }
//        System.out.println(p3.getBestBefore());
//        System.out.println(p1.getBestBefore());


        pm.printProductReport();
        p1 = pm.reviewProduct(p1, Rating.FOUR_STAR, "Ta bueno ");
        pm.printProductReport();


    }
}



