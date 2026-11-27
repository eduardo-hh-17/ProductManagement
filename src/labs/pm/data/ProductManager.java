/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package labs.pm.data;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author manana
 **/
public class ProductManager {


    private Product product;
    private Review review;
    private Review[] reviews = new Review[5];
    private Map<Product, List<Review>> products = new HashMap<>();
    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());
    private ResourceBundle config = ResourceBundle.getBundle("labs.pm.data.config");
    private MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));





    /*NUEVAS VARIABLES Y METODOS TEMA 10-1*/
    private static Map<String, ResourceFormatter> formatters =
            Map.of("en-GB", new ResourceFormatter(Locale.UK),
                    "en-US", new ResourceFormatter(Locale.US),
                    "es-ES", new ResourceFormatter(Locale.of("es","ES")),
                    "fr-FR", new ResourceFormatter(Locale.FRANCE),
                    "zh-CN", new ResourceFormatter(Locale.CHINA));



    private ResourceFormatter formatter;


    //=======Cargamos la rutas donde vamos a guardar los archivos========
    private Path reportsFolder =
                        Path.of(config.getString("reports.folder"));

    private Path dataFolder =
            Path.of(config.getString("data.folder"));

    private Path tempFolder =
            Path.of(config.getString("temp.folder"));



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
        this(locale.toLanguageTag());
    }


    public void printProducts (Predicate<Product> filter,
                               Comparator<Product> sorter){

        StringBuilder txt = new StringBuilder();
        products.keySet()
                .stream()
                .sorted(sorter)
                .filter(filter)
                .forEach(p ->
                        txt.append(formatter.formatProduct(p)+'\n'));

//        List<Product> productList = new ArrayList<>(products.keySet());
//        productList.sort(sorter);

//        for (Product product : productList){
//            txt.append(formatter.formatProduct(product));
//            txt.append('\n');
//
//        }
        System.out.println(txt);
    }

    //TODO: Pagina 288. Punto 5
    private List <Review> loadReview (Product product){
        List <Review> reviews = null;
        Path file =
                dataFolder.resolve(
                        MessageFormat.format(
                        config.getString("reviews.data.file"), product.getId()));

        if (Files.notExists(file)){
            reviews = new ArrayList<>();
        }
        else{
            try {
                reviews = Files.lines(file, Charset.forName("UTF-8"))
                        .map(text -> parseReview(text))
                        .filter(review -> review != null)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                logger.log(Level.WARNING,
                        "Error loading reviews "+e.getMessage());
            }
        }
        return reviews;
    }

    public Review parseReview (String text) throws ProductManagerException {
        Review review = null;
        try {
            Object [] values = reviewFormat.parse(text);
           review = new Review (
            Rateable.convert(Integer.parseInt((String)values[0])),
                                              (String)values[1]);
        } catch (ParseException  | NumberFormatException e) {
            logger.log(Level.WARNING, "Error parsing review "+text, e);
            throw new ProductManagerException("Unable to parse review", e);
        }
       return review;
    }

    public Product parseProduct (String text){
        Product product = null;
        try {
            Object [] values = productFormat.parse(text);
            int id = Integer.parseInt((String)values[1]);
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String)values[3]));
            Rating rating = Rateable.convert(Integer.parseInt((String) values[4]));
            switch ((String) values[0]){
                case "D":
                    product = new Drink (id,name,price,rating);
                    break;

                case "F":
                    LocalDate bestBefore = LocalDate.parse((String)values[5],formatter.dateFormart);
                    product = new Food (id,name,price,rating,bestBefore);
                    break;


            }
            //parse values and create product object
        } catch (ParseException | NumberFormatException | DateTimeParseException e) {
            logger.log (Level.WARNING, "Error parsing product "+text+" "+e.getMessage());
        }

        return product;
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
        //Crear listado de reseñas por producto
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }




    public Product reviewProduct (Product product, Rating rating, String comments){
//        review = new Review(rating, comments);
        List <Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));

//        int sum = 0;
//        for(Review review: reviews){
//            sum += review.rating().ordinal();
//        }
//        product = product.applyRating(Rateable.convert(Math.round((float)sum/reviews.size())));
        product = product.applyRating(
                Rateable.convert(
                (int)Math.round(
                reviews.stream()
                                .mapToInt(r -> r.rating().ordinal())
                                .average()
                                .orElse(0))));

        products.putIfAbsent(product, reviews);
        return product;
    }

    public Product reviewProduct (int id, Rating rating, String comments){
        try {
            return reviewProduct(findProduct(id),rating,comments);
        } catch (ProductManagerException e) {
            logger.log(Level.INFO, e.getMessage());
            return null;
        }
    }



    public void printProductReport (Product product) throws IOException {
        List <Review> reviews = products.get(product);
        Collections.sort(reviews);
//        StringBuilder txt = new StringBuilder();
        Path productFile =
                reportsFolder.resolve(
                        MessageFormat.format(
                                config.getString("reports.file"), product.getId()));

        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                        Files.newOutputStream(productFile,
                                StandardOpenOption.CREATE),"UTF-8"))) {


            //reamining method logic
            out.append(formatter.formatProduct(product)
                                +System.lineSeparator());

            if (reviews.isEmpty()) {
                out.append(formatter.getText("no.reviews")
                        +System.lineSeparator());
            } else {
                out.append(reviews.stream()
                        .map(r -> formatter.formatReview(r)
                                +System.lineSeparator())
                        .collect(Collectors.joining()));

            }

            System.out.println(out);

        }

    }


    public void printProductReport (int id) {
        try {
            printProductReport(findProduct(id));
        } catch (ProductManagerException e) {
            logger.log(Level.INFO, e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE,
                    "Error printing product report "+e.getMessage(), e);
        }
    }


    public Map <String, String> getDiscount (){
        return products.keySet()
                .stream()
                .collect(
                        Collectors.groupingBy(product ->
                                product.getRating().getStars(),
                        Collectors.collectingAndThen(
                                Collectors.summingDouble(
                                        product -> product.getDiscount().doubleValue()
                                ),
                                discount -> formatter.moneyFormat.format(discount)
                        )
                ));
    }

    public Product findProduct (int id) throws ProductManagerException {
//        Product result = null;
//        for (Product product : products.keySet()){
//            if(product.getId() == id){
//                result=product;
//                break;
//            }
//
//        }
//        return result;
          return products.keySet()
                  .stream()
                  .filter(p -> p.getId() == id)
                  .findFirst()
                  .orElseThrow(() ->
                          new ProductManagerException( "Product whit id "+ id + "not found"));

    }





    //Practica 10-1 clase anidada estatica, solo la usa ProductManager (private)
    private static class ResourceFormatter {
        private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormart;
        private NumberFormat moneyFormat;

        // La lógica que habia del Constructor de ProductManager se traslada a la clase anidada
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
