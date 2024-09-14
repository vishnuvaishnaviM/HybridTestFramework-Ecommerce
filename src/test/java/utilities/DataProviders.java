package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
//dataprovider1
    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException{
        String path = ".\\testData\\Opencart_LoginData.xlsx";
        ExcelUtility xlutil = new ExcelUtility(path);

        int totalrows = xlutil.getRowCount("Sheet1");
        int totlcols = xlutil.getCellCount("Sheet1", 1);
        String loginData[][] = new String[totalrows][totlcols];

        for(int i=1;i<=totalrows;i++){
            for(int j=0;j<totlcols;j++){
                loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }
        return loginData;
    }
}
