/*
 * Copyright (c) 2026. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package labs.pm.data;

@FunctionalInterface
/**
 * @author manana
 **/
public interface Rateable <T> {

    public static final Rating DEFAULT_RATING = Rating.NOT_RATED;


    public abstract T applyRating (Rating rating);
    public String toString();

    public default Rating getDefaultRating (){
        return DEFAULT_RATING;
    }

    public static Rating convert(int stars){
        //method logic will be assed here
        return (stars>=0&&stars<=5) ? Rating.values () [stars] : DEFAULT_RATING;
    }


}
