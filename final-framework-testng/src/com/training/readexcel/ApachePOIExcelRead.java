package com.training.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 
 * @author Naveen
 * @see this class  will take the records from excel sheet, and return it as list of list of object, and can be 
 *    		 generic, can given any records until it exists. Test it with main method provided, and the path is 
 *    		hard coded, participatns are asked to refractor this path in the property file and access.  
 */
public class ApachePOIExcelRead {
public static Object[][] getExcelContent(String file_location) throws IOException
{
FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
    XSSFWorkbook workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
   // String SheetName = null;
	XSSFSheet worksheet = workbook.getSheetAt(0);// get my sheet from workbook
    XSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0   
 
    int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
    int ColNum= Row.getLastCellNum(); // get last ColNum 
     
    Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
     
        for(int i=0; i<RowNum-1; i++) //Loop work for Rows
        {  
            XSSFRow row= worksheet.getRow(i+1);
             
            for (int j=0; j<ColNum; j++) //Loop work for colNum
            {
                if(row==null)
                    Data[i][j]= "";
                else
                {
                	XSSFCell cell= row.getCell(j);
                    if(cell==null)
                        Data[i][j]= ""; //if it get Null value it pass no data 
                    else
                    {	DataFormatter formatter= new DataFormatter();
						String value=formatter.formatCellValue(cell);
                        Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                    }
                }
            }
        }

    return Data;
}

	public static void main(String[] args) throws IOException {
		String file_location = "C:\\Users\\HarshalKulkarni\\Desktop\\Morgage_Incoorect_testdata.xlsx";

		for (Object[] temp : getExcelContent(file_location)) {
			System.out.println(temp[0] + "		" + temp[1]+"		" + temp[2]+"		" + temp[3]);
		}
	}
} 