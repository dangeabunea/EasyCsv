package easycsv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan-geabunea on 3/26/2015.
 */
public class CsvConfiguration {
    private boolean skipHeader;
    private List<Integer> columnIndexesToParse;

    public CsvConfiguration(){
        this.skipHeader = true;
        this.columnIndexesToParse = new ArrayList<>();
    }

    public boolean skipHeader() {
        return skipHeader;
    }

    public void setSkipHeader(boolean skipHeader) {
        this.skipHeader = skipHeader;
    }

    public List<Integer> getColumnIndexesToParse() {
        return columnIndexesToParse;
    }

    public void setColumnIndexesToParse(List<Integer> columnIndexesToParse) {
        this.columnIndexesToParse = columnIndexesToParse;
    }

    public boolean parseAllColumns(){
        return this.columnIndexesToParse.size() == 0;
    }
}
