package easycsv;

/**
 * Created by Dan Geabunea on 3/21/2015.
 */
public class EasyCsvAssertionRuntimeException extends RuntimeException {
    public EasyCsvAssertionRuntimeException(){
        super("EasyCSV assertion has been violated. ");
    }

    public EasyCsvAssertionRuntimeException(String message){
        super("EasyCSV assertion has been violated. " + message);
    }
}
