package easycsv;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static easycsv.CodeAssertion.verifyThat;

/**
 * Created by Dan Geabunea on 3/21/2015.
 */
public class CsvColumn {
    private String value;

    public CsvColumn(String value) {
        this.value = value;
    }

    public boolean isEmpty(){
        return value == null;
    }

    public String getValue() {
        return value;
    }

    public int getIntValue(){
        verifyThat(value != null, EasyCsvErrorMessages.nullValue(value));
        try {
            int parsedValue = Integer.parseInt(value);
            return parsedValue;
        }
        catch (NumberFormatException e){
            throw new EasyCsvConversionException(EasyCsvErrorMessages.integerConversion(value));
        }
    }

    public double getDoubleValue(){
        verifyThat(value != null, EasyCsvErrorMessages.nullValue(value));
        try {
            double parsedValue = Double.parseDouble(value);
            return parsedValue;
        }
        catch (NumberFormatException e){
            throw new EasyCsvConversionException(EasyCsvErrorMessages.doubleConversion(value));
        }
    }

    public boolean getBooleanValue(){
        verifyThat(value != null, EasyCsvErrorMessages.nullValue(value));

        //check for true values
        if(value.equalsIgnoreCase("true") ||
                value.equalsIgnoreCase("t") ||
                value.equalsIgnoreCase("yes") ||
                value.equalsIgnoreCase("y") ||
                value.equals("1")){
            return true;
        }

        //check for true values
        if(value.equalsIgnoreCase("false") ||
                value.equalsIgnoreCase("f") ||
                value.equalsIgnoreCase("no") ||
                value.equalsIgnoreCase("n") ||
                value.equals("0")){
            return false;
        }

        throw new EasyCsvConversionException(EasyCsvErrorMessages.booleanConversion(value));
    }

    public Date getDateValue(String dateFormat){
        verifyThat(value != null, EasyCsvErrorMessages.nullValue(value));

        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            Date result = df.parse(value);
            return result;
        } catch (ParseException e) {
            throw new EasyCsvConversionException(EasyCsvErrorMessages.dateConversion(value, dateFormat));
        }
    }

}
