package easycsv;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static easycsv.CodeAssertion.verifyThat;

/**
 * Created by dan on 3/24/15.
 */
public class CsvDocument {
    private List<CsvRow> csvRows;

    public CsvDocument(List<CsvRow> csvRows){
        verifyThat(csvRows != null, EasyCsvErrorMessages.nullValue("csvRows"));
        verifyThat(csvRows.size() > 0, EasyCsvErrorMessages.emptyList("csvRows"));

        this.csvRows = csvRows;
    }

    public List<CsvRow> getCsvRows() {
        return csvRows;
    }

    public boolean isEmpty(){
        return this.csvRows.size() == 0;
    }

    public static CsvDocument read(String filePath) throws IOException {
        CsvConfiguration defaultConfiguration = new CsvConfiguration();
        return CsvDocument.read(filePath, defaultConfiguration);
    }

    public static CsvDocument read(String filePath, CsvConfiguration csvConfiguration) throws IOException {
        verifyThat(filePath != null, EasyCsvErrorMessages.nullValue("filePath"));
        verifyThat(csvConfiguration != null, EasyCsvErrorMessages.nullValue("csvConfiguration"));

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            //skip header
            if (csvConfiguration.skipHeader()) {
                bufferedReader.readLine();
            }

            //parse rows
            String csvLine;
            List<CsvRow> parsedCsvRows = new ArrayList<>();
            while ((csvLine = bufferedReader.readLine()) != null) {
                CsvRow row = parseCsvRow(csvConfiguration, csvLine);
                parsedCsvRows.add(row);
            }

            //build document
            CsvDocument parsedDocument = new CsvDocument(parsedCsvRows);

            return parsedDocument;
        }
    }

    public static void save(CsvDocument document, String savePath){
        throw new NotImplementedException();
    }

    private static CsvRow parseCsvRow(CsvConfiguration csvConfiguration, String csvLine) {
        final String COMA_SEPARATOR = ",";
        String[] columns = csvLine.split(COMA_SEPARATOR);

        List<CsvColumn> csvColumns = new ArrayList<>();
        if(csvConfiguration.parseAllColumns()){
            for(int i=0; i<columns.length;i++){
                csvColumns.add(new CsvColumn(columns[i]));
            }
        }
        else{
           for(int columnIndex : csvConfiguration.getColumnIndexesToParse()){
               csvColumns.add(new CsvColumn(columns[columnIndex]));
           }
        }

        return new CsvRow(csvColumns);
    }
}
