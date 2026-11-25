package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/**
 * {@code Shop} class represent a navigation that manage products
 * @version 4.0
 * @author oracle
 */
public class Shop {


    public static void main(String[] args) {


        try {
            ProductManager pm = new ProductManager("es-ES");
            System.setProperty("java.locale.providers", "HOST,CLDR");

//            Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);

            pm.parseProduct("F,101,tea,1.99,0,2021-09-21");
            pm.printProductReport(101);
            pm.parseReview("101,4,Nice hot cup of tea");
//            pm.parseReview("Despues de esto nos vamos para casa");
//        p1 = pm.reviewProduct(101, Rating.FOUR_STAR, "Nice hot cup of tea");
//        p1 = pm.reviewProduct(p1, Rating.TWO_STAR, "Rather weak tea");
//        p1 = pm.reviewProduct(p1, Rating.FOUR_STAR, "Fine tea");
//        p1 = pm.reviewProduct(p1, Rating.FOUR_STAR, "Good tea");
//        p1 = pm.reviewProduct(p1, Rating.FIVE_STAR, "Perfect tea");
//        p1 = pm.reviewProduct(p1, Rating.THREE_STAR, "Just add some lemon");
            pm.printProductReport(101);


//        Product p2 = pm.createProduct(102, "Coffee",
//                BigDecimal.valueOf(1.99), Rating.NOT_RATED);
//        p2 = pm.reviewProduct(102, Rating.THREE_STAR, "Coffee was ok");
//        p2 = pm.reviewProduct(102, Rating.ONE_STAR, "Where is the milk?!");
//        p2 = pm.reviewProduct(102, Rating.FIVE_STAR,
//                "It's perfect with ten spoons of sugar!");
//        pm.printProductReport(102);
//
//
//
//        Product p3 = pm.createProduct(103, "Cake",
//                BigDecimal.valueOf(3.99), Rating.NOT_RATED,
//                LocalDate.now().plusDays(2));
//        p3 = pm.reviewProduct(103, Rating.FIVE_STAR, "Very nice cake");
//        p3 = pm.reviewProduct(103, Rating.FOUR_STAR,
//                "Good, but I've expected more chocolate");
//        p3 = pm.reviewProduct(103, Rating.FIVE_STAR, "This cake is perfect!");
//        pm.printProductReport(103);
//
//
//
//
//        Product p4 = pm.createProduct(104, "Cookie",
//                BigDecimal.valueOf(2.99), Rating.NOT_RATED,
//                LocalDate.now());
//        p4 = pm.reviewProduct(104, Rating.THREE_STAR, "Just another cookie");
//        p4 = pm.reviewProduct(104, Rating.THREE_STAR, "Ok");
//        pm.printProductReport(104);
//
//        Product p5 = pm.createProduct(105, "Hot Chocolate",
//                BigDecimal.valueOf(2.50), Rating.NOT_RATED);
//        p5 = pm.reviewProduct(105, Rating.FOUR_STAR, "Tasty!");
//        p5 = pm.reviewProduct(105, Rating.FOUR_STAR, "No bad at all");
//        pm.printProductReport(105);
//
//
//
//
//        Product p6 = pm.createProduct(106, "Chocolate",
//                BigDecimal.valueOf(2.50), Rating.NOT_RATED,
//                LocalDate.now().plusDays(3));
//        p6 = pm.reviewProduct(106, Rating.TWO_STAR, "Too sweet");
//        p6 = pm.reviewProduct(106, Rating.THREE_STAR, "Better then cookie");
//        p6 = pm.reviewProduct(106, Rating.TWO_STAR, "Too bitter");
//        p6 = pm.reviewProduct(106, Rating.ONE_STAR, "I don't get it!");
//        pm.printProductReport(106);


//        for (Product p: pm.products.keySet()){
//            System.out.println("Reseñas de " + p);
//            for (Review r : pm.products.get(p)){
//                System.out.println("\t" + r);
//            }
//
//        }
//        pm.printProducts((pr1,pr2)->
//                        pr1.getRating().ordinal() -
//                        pr2.getRating().ordinal());

//        pm.printProducts((pr1,pr2)->
//                        pr2.getPrice().compareTo(pr1.getPrice()));

//        pm.printProducts(p -> p.getPrice().floatValue() < 2,
//                (pr1,pr2) -> pr2.getRating().ordinal()-pr1.getRating().ordinal());


//        Comparator<Product> priceSorter = (pr1, pr2)->
//                pr2.getPrice().compareTo(pr1.getPrice());
//        Comparator<Product> ratingSorter = (pr1,pr2)->
//                pr2.getRating().ordinal() -
//                            pr1.getRating().ordinal();
//        pm.printProducts(p -> p.getPrice().floatValue() <3,
//                    priceSorter);
//        pm.printProducts(p -> p.getPrice().floatValue() <4,
//                ratingSorter.thenComparing(priceSorter).reversed());

        } catch (ProductManagerException e) {
            Throwable cause = e.getCause();
        }

    }
}



