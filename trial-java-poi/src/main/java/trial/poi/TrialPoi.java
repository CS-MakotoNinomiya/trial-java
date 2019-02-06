package trial.poi;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TrialPoi {

    public static void main(String[] args) {
        try {
            TrialPoi me = new TrialPoi();
            me.execute(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(String[] args) throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("trial_book.xlsx");
        Workbook wb = WorkbookFactory.create(in);
        Sheet sheet = wb.getSheetAt(0);

        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);

        double d = cell.getNumericCellValue();

        System.out.println(d);
    }
}
