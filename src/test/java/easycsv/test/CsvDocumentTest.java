package easycsv.test;

import easycsv.CsvColumn;
import easycsv.CsvConfiguration;
import easycsv.CsvDocument;
import easycsv.CsvRow;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dan Geabunea on 3/26/2015.
 */
public class CsvDocumentTest {

    @Test
    public void with_no_config_options_should_parse_csv_file_and_create_csv_document() throws IOException {
        //arrange
        String csvPath = getClass().getClassLoader().getResource("csv_test_file.csv").getPath();

        //act
        CsvDocument document = CsvDocument.read(csvPath);

        //assert
        assertEquals(3, document.getCsvRows().size());

        CsvRow header = document.getCsvRows().get(0);
        assertEquals("Header 1,Header 2,Header 3,Header 4,Header 5", header.toString());
        assertEquals("1,1.1,abc,TRUE,11/1/2014", document.getCsvRows().get(1).toString());
        assertEquals("2,2.2,def,FALSE,11/2/2014", document.getCsvRows().get(2).toString());
    }

    @Test
    public void when_configured_to_skip_header_should_parse_csv_document_without_header() throws IOException {
        //arrange
        String csvPath = getClass().getClassLoader().getResource("csv_test_file.csv").getPath();
        CsvConfiguration skipHeaderConfig = new CsvConfiguration(){{
            setSkipHeader(true);
        }};

        //act
        CsvDocument document = CsvDocument.read(csvPath, skipHeaderConfig);

        //assert
        assertEquals(2, document.getCsvRows().size());
        assertEquals("1,1.1,abc,TRUE,11/1/2014", document.getCsvRows().get(0).toString());
        assertEquals("2,2.2,def,FALSE,11/2/2014", document.getCsvRows().get(1).toString());
    }

    @Test
    public void when_configured_to_parse_specific_columns_should_parse_csv_document_without_selected_columns() throws IOException {
        //arrange
        String csvPath = getClass().getClassLoader().getResource("csv_test_file.csv").getPath();
        CsvConfiguration skipHeaderConfig = new CsvConfiguration(){{
            setColumnIndexesToParse(0, 1, 2);
        }};

        //act
        CsvDocument document = CsvDocument.read(csvPath, skipHeaderConfig);

        //assert
        assertEquals(3, document.getCsvRows().size());
        assertEquals("Header 1,Header 2,Header 3", document.getCsvRows().get(0).toString());
        assertEquals("1,1.1,abc", document.getCsvRows().get(1).toString());
        assertEquals("2,2.2,def", document.getCsvRows().get(2).toString());
    }

    @Test
    public void should_allow_easy_mapping_to_pojo() throws IOException {
        //arrange
        String csvPath = getClass().getClassLoader().getResource("csv_person_file_test.csv").getPath();
        CsvConfiguration skipHeaderConfig = new CsvConfiguration(){{
            setSkipHeader(true);
        }};
        CsvDocument personDocument = CsvDocument.read(csvPath, skipHeaderConfig);

        //act
        List<Person> persons = personDocument.getCsvRows().stream()
                .map(x -> new Person(
                        x.getColumnAtIndex(0).getColumnValue(),
                        x.getColumnAtIndex(1).getInteger(),
                        x.getColumnAtIndex(2).getBoolean()
                ))
                .collect(Collectors.toList());



        //assert
        assertEquals(2, persons.size());

        Person firstPerson = persons.get(0);
        assertEquals("John", firstPerson.getName());
        assertEquals(23, firstPerson.getAge());
        assertEquals(false, firstPerson.isEmployed());

        Person secondPerson = persons.get(1);
        assertEquals("Mary", secondPerson.getName());
        assertEquals(31, secondPerson.getAge());
        assertEquals(true, secondPerson.isEmployed());
    }

    @Test
    @Ignore()
    public void the_write_method_should_write_to_file(){
        //arrange
        String outputPath = "write_path_here";
        CsvRow someRow = new CsvRow(new CsvColumn("some value"), new CsvColumn(2.2), new CsvColumn(true));
        CsvDocument documentWithOneRow = new CsvDocument(new ArrayList<>(Arrays.asList(someRow)));

        //act
        boolean result = CsvDocument.tryWriteToFile(documentWithOneRow, outputPath);

        //assert
        assertTrue(result);
    }

    /*
    Class used ofr testing reasons
     */
    private class Person {
        private String name;
        private int age;
        private boolean isEmployed;

        public Person(String name, int age, boolean isEmployed){
            this.name = name;
            this.age = age;
            this.isEmployed = isEmployed;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public boolean isEmployed() {
            return isEmployed;
        }
    }
}
