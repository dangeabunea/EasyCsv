package easycsv;

/**
 * Created by Dan Geabunea on 3/21/2015.
 */
public class EasyCsvErrorMessages {
    public static String nullValue(String variableName){
        return variableName + " should not be null";
    }

    public static String integerConversion(String value){
        return "Can not convert value '" + value + "' to Integer";
    }

    public static String doubleConversion(String value){
        return "Can not convert value '" + value + "' to Double";
    }

    public static String booleanConversion(String value) {
        return "Can not convert value '" + value + "' to Boolean";
    }

    public static String dateConversion(String value, String dateFormat) {
        return "Can not convert value '" + value + "' to Date using format " + dateFormat;
    }

    public static String emptyList(String variableName) {
        return "List " + variableName + " should not be empty. ";
    }
}
