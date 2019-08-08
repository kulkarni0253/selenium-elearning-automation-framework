package com.training.dataproviders;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData() throws IOException{
		String fileName =""; 
		return new ApachePOIExcelRead().getExcelContent(fileName);
	}
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}
	
	
//	@DataProvider
//  public Object[][] getData() throws IOException
//  {
//      ApachePOIExcelRead apachePOIExcelRead = new ApachePOIExcelRead();
//      List<List<Object>> data = ApachePOIExcelRead.getExcelContent("C:/Users/SarojiniEmekar/Desktop/Testing.xlsx");
//      
//        Object[][] a = new Object[data.size()][3];
//        
//        for(int i = 0; i < data.size(); i++) { 
//            for(int j = 0; j < 3; j++) { 
//                a[i][j] =  (data.get(i)).get(j); 
//                System.out.println("Data Provider values: "+a[i][j]);
//                } 
//            }
//      return a;
//  }
}
