package easycsv.test;

import easycsv.CsvColumn;
import easycsv.CsvRow;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dan on 3/24/15.
 */
public class CsvRowTest {

    @Test
    public void the_constructor_with_column_values_argument_should_build_row(){
        //arrange
        String someValue = "1";
        String anotherValue = "value";

        //act
        CsvRow someRow = new CsvRow(someValue, anotherValue);

        //assert
        assertEquals(2, someRow.getNumberOfColumns());
        assertFalse(someRow.isEmpty());
        assertTrue(someRow.getColumnAtIndex(0).getColumnValue().equals(someValue));
        assertTrue(someRow.getColumnAtIndex(1).getColumnValue().equals(anotherValue));
    }

    @Test
    public void the_constructor_with_variable_columns_argument_should_build_row(){
        //arrange
        String someValue = "1";
        String anotherValue = "value";
        CsvColumn someColumn = new CsvColumn(someValue);
        CsvColumn anotherColumn = new CsvColumn(anotherValue);

        //act
        CsvRow someRow = new CsvRow(someColumn, anotherColumn);

        //assert
        assertEquals(2, someRow.getNumberOfColumns());
        assertFalse(someRow.isEmpty());
        assertTrue(someRow.getColumnAtIndex(0).getColumnValue().equals(someValue));
        assertTrue(someRow.getColumnAtIndex(1).getColumnValue().equals(anotherValue));
    }

    @Test
    public void the_constructor_with_column_list_argument_should_build_row(){
        //arrange
        String someValue = "1";
        String anotherValue = "value";
        CsvColumn someColumn = new CsvColumn(someValue);
        CsvColumn anotherColumn = new CsvColumn(anotherValue);
        List<CsvColumn> rowColumns = new ArrayList<>(Arrays.asList(someColumn,anotherColumn));

        //act
        CsvRow someRow = new CsvRow(rowColumns);

        //assert
        assertEquals(2, someRow.getNumberOfColumns());
        assertFalse(someRow.isEmpty());
        assertTrue(someRow.getColumnAtIndex(0).getColumnValue().equals(someValue));
        assertTrue(someRow.getColumnAtIndex(1).getColumnValue().equals(anotherValue));
    }
}
