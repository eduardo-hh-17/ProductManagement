package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/**
 * {@code Shop} class represent a navigation that manage products
 * @version 4.0
 * @author oracle
 */
public class Shop {


    public static void main(String[] args) {

        ProductManager pm = new ProductManager("zh-CN");
        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
//        System.out.println(p1);
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


        pm.printProductReport(101);

        p1 = pm.reviewProduct(101, Rating.FOUR_STAR, "Nice hot cup of tea");
        p1 = pm.reviewProduct(101, Rating.TWO_STAR, "Rather weak tea");
        p1 = pm.reviewProduct(101, Rating.FOUR_STAR, "Fine tea");
        p1 = pm.reviewProduct(101, Rating.FOUR_STAR, "Good tea");
        p1 = pm.reviewProduct(101, Rating.FIVE_STAR, "Perfect tea");
        p1 = pm.reviewProduct(101, Rating.THREE_STAR, "Just add some lemon");


        pm.printProductReport(p1);



        Product p2 = pm.createProduct(102, "Coffee",
                BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        p2 = pm.reviewProduct(102, Rating.THREE_STAR, "Coffee was ok");
        p2 = pm.reviewProduct(102, Rating.ONE_STAR, "Where is the milk?!");
        p2 = pm.reviewProduct(102, Rating.FIVE_STAR,
                "It's perfect with ten spoons of sugar!");
        pm.printProductReport(102);

        Product p3 = pm.createProduct(103, "Cake",
                BigDecimal.valueOf(3.99), Rating.NOT_RATED,
                LocalDate.now().plusDays(2));
        p3 = pm.reviewProduct(103, Rating.FIVE_STAR, "Very nice cake");
        p3 = pm.reviewProduct(103, Rating.FOUR_STAR,
                "Good, but I've expected more chocolate");
        p3 = pm.reviewProduct(103, Rating.FIVE_STAR, "This cake is perfect!");
        pm.printProductReport(103);

        Product p4 = pm.createProduct(104, "Cookie",
                BigDecimal.valueOf(2.99), Rating.NOT_RATED,
                LocalDate.now());
        p4 = pm.reviewProduct(104, Rating.THREE_STAR, "Just another cookie");
        p4 = pm.reviewProduct(104, Rating.THREE_STAR, "Ok");
        pm.printProductReport(104);

        Product p5 = pm.createProduct(105, "Hot Chocolate",
                BigDecimal.valueOf(2.50), Rating.NOT_RATED);
        p5 = pm.reviewProduct(105, Rating.FOUR_STAR, "Tasty!");
        p5 = pm.reviewProduct(105, Rating.FOUR_STAR, "No bad at all");
        pm.printProductReport(105);

        Product p6 = pm.createProduct(106, "Chocolate",
                BigDecimal.valueOf(2.50), Rating.NOT_RATED,
                LocalDate.now().plusDays(3));
        p6 = pm.reviewProduct(106, Rating.TWO_STAR, "Too sweet");
        p6 = pm.reviewProduct(106, Rating.THREE_STAR, "Better then cookie");
        p6 = pm.reviewProduct(106, Rating.TWO_STAR, "Too bitter");
        p6 = pm.reviewProduct(106, Rating.ONE_STAR, "I don't get it!");
        pm.printProductReport(106);




    }
}



