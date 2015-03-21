package easycsv;

/**
 * Created by Dan on 3/21/2015.
 */
public class EasyCsvConversionException extends RuntimeException {
    public EasyCsvConversionException(){
        super("EasyCSV conversion failed.");
    }

    public EasyCsvConversionException(String message){
        super("EasyCSV conversion failed. " + message);
    }
}
