/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

/**
 * @author manana
 **/
public class ProductManager {
    private Product product;
    private Review review;

    private Review[] reviews = new Review[5];
    private Map<Product, List<Review>> products = new HashMap<>();


    /*NUEVAS VARIABLES Y METODOS TEMA 10-1*/
    private static Map<String, ResourceFormatter> formatters =
            Map.of("en-GB", new ResourceFormatter(Locale.UK),
                    "en-US", new ResourceFormatter(Locale.US),
                    "ru-RU", new ResourceFormatter(Locale.of("ru","RU")),
                    "fr-FR", new ResourceFormatter(Locale.FRANCE),
                    "zh-CN", new ResourceFormatter(Locale.CHINA));



    private ResourceFormatter formatter;

    public void changeLocale (String languageTag){
        formatter = formatters.getOrDefault(languageTag,
                formatters.get("en-GB"));
    }

    public static Set <String> getSupportedLocales (){
        return formatters.keySet();
    }

    public ProductManager (String languajeTag){
        changeLocale(languajeTag);
    }









    public ProductManager (Locale locale){
//        this.locale= locale;
//        resources = ResourceBundle.getBundle("labs.pm.data.resources", locale);
//        dateFormart = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
//                .localizedBy(locale);
//
//        moneyFormat = NumberFormat.getCurrencyInstance(locale);
        this(locale.toLanguageTag());
    }

    public Product createProduct (int id, String name, BigDecimal price,
                                Rating rating, LocalDate bestBefore){
        product =  new Food(id, name, price,rating,bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product createProduct (int id, String name, BigDecimal price,
                                  Rating rating){
        product =  new Drink (id, name, price,rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product reviewProduct (Product product, Rating rating, String comments){
//        review = new Review(rating, comments);
        List <Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));

        int sum = 0;
        for(Review review: reviews){
            sum += review.rating().ordinal();
        }
//        if (reviews [reviews.length-1] != null) {
//            reviews = Arrays.copyOf(reviews, reviews.length+5);
//        }
//
//        boolean reviewed = false;
//
//        while(i<reviews.length && !reviewed){
//            if (reviews[i] == null){
//                reviews [i] = new Review(rating, comments);
//                reviewed = true;
//            }
//            //El metodo rating me pasa la posicion
//            sum += reviews[i].rating().ordinal();
//            i++;
//        }
        product = product.applyRating(Rateable.convert(Math.round((float)sum/reviews.size())));
        products.put(product, reviews);
        return this.product;
    }

    public void printProductReport (Product product) {
        List <Review> reviews = products.get(product);
        Collections.sort(reviews);
        StringBuilder txt = new StringBuilder();
//        String type = switch (product){
//            case Food food -> resources.getString("food");
//            case Drink drink -> resources.getString("drink");
//        };

        //reamining method logic
//        txt.append(MessageFormat.format(resources.getString("product"),
//            product.getName(),
//            moneyFormat.format(product.getPrice()),
//            product.getRating().getStars(),
//            dateFormart.format(product.getBestBefore()),
//            type));
        txt.append(formatter.formatProduct(product));
        txt.append('\n');
        for (Review review: reviews){
//            txt.append(MessageFormat.format(resources.getString("review"),
//                    review.rating().getStars(),
//                    review.comments()));
            txt.append(formatter.formatProduct(product));
            txt.append('\n');
        }
        if(reviews.isEmpty()){
//            txt.append(resources.getString("no.reviews"));
            txt.append(formatter.formatProduct(product));
            txt.append('\n');

        }


//        txt.append(product);
//        txt.append('\n');
//        if (review != null){
//            txt.append(review);
//        }else{
//            txt.append("Not reviewed");
//        }
//        txt.append('\n');
        System.out.println(txt);


    }


    public Product findProduct (int id){
        Product result = null;
        for (Product product : products.keySet()){
            if(product.getId() == id){
                result=product;
                break;
            }

        }
        return result;
    }

    public Product reviewProduct (int id, Rating rating, String comments){
        return reviewProduct(findProduct(id),rating,comments);
    }

    public void printProductReport (int id) {
        printProductReport(findProduct(id));
    }




    //Practica 10-1 clase anidada estatica, solo la usa ProductManager (private)
    private static class ResourceFormatter {
        private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormart;
        private NumberFormat moneyFormat;

        private ResourceFormatter (Locale locale){
            this.locale = locale;
            resources = ResourceBundle.getBundle("labs.pm.data.resources", locale);
            dateFormart = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                    .localizedBy(locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct (Product product){
            //switch exhaustivo: Product es sealed y solo permite Drink y Food
            String type = switch (product) {
                case Food food -> resources.getString("food");
                case Drink drink -> resources.getString("drink");
            };
            return MessageFormat.format(resources.getString("product"),
                            product.getName(),
                            moneyFormat.format(product.getPrice()),
                            product.getRating().getStars(),
                            dateFormart.format(product.getBestBefore()),
                            type);
        }

        private String formatReview (Review review){
            return MessageFormat.format(resources.getString("review"),
            review.rating().getStars(),
            review.comments());
        }
        private String getText(String key){
            return resources.getString(key);
        }



    }



}
