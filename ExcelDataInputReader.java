package utillity;


	import org.apache.poi.ss.usermodel.*;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;

	public class ExcelDataInputReader {

	    private static String filePath;
	    private static String sheetName;

	    public ExcelDataInputReader(String filePath, String sheetName) {
	        ExcelDataInputReader.filePath = filePath;
	        ExcelDataInputReader.sheetName = sheetName;
	    }

	    public static List<List<String>> readExcelData() throws IOException {
	        List<List<String>> data = new ArrayList<>();
	        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
	        Workbook workbook = WorkbookFactory.create(fileInputStream);
	        Sheet sheet = workbook.getSheet(sheetName);
	        
	        for (Row row : sheet) {
	            List<String> rowData = new ArrayList<>();
	            for (Cell cell : row) {
	                rowData.add(cell.toString());
	            }
	            data.add(rowData);
	        }

	        workbook.close();
	        fileInputStream.close();
	        return data;
	    }
	}


//	public static String user_first_name;
//	public static String user_last_name;
//	public static String user_contact_number;
//	public static String user_email_id;
//	public static String plotNumber;
//	public static String street;
//	public static String state;
//	public static String country;
//	public static String zipCode;
//	 
//	static Properties properties = new Properties();
//	
//	public static void loadProperties()  {
//		
//		FileReader reader= null;
//	excelutility excel = null;
//		
//		String excelURL = "/Users/praveenag/Desktop/APIBootCamp new/excel2.xlsx";
//		String path=System.getProperty("user.dir");
//		excel= new excelutility(path+excelURL,"apitesting");
//		
//		
//		
//		
//		user_first_name =excel.getCellDataString(1, 1); 
//		user_last_name = excel.getCellDataString(1, 2); 
//		user_contact_number = excel.getCellDataString(1, 3); 
//		user_email_id =  excel.getCellDataString2(1, 4);
//		plotNumber = excel.getCellDataString(1, 5); 
//		street =  excel.getCellDataString2(1, 6); 
//		state = excel.getCellDataString2(1, 7); 
//		country = excel.getCellDataString(1, 8); 
//		zipCode = excel.getCellDataString2(1, 9); 
//		 
//}
//	
//	public String getUserFirstname() {
//		return user_first_name;
//	}
//	
//	public String getUserLastname() {
//		return user_last_name;
//	}
//	
//	public String getUsrNumber() {
//		return user_contact_number;
//	}
//	
//	public String getUseremailId() {
//		return user_email_id ;
//	}
//	
//	public String getplotNumber() {
//		return plotNumber;
//	}
//	
//	public String getStreet() {
//		return street ;
//	}
//	
//	public String getState() {
//		return state;
//	}
//	
//	public String getCoutry() {
//		return country;
//	}
//	
//	public static String getzipcode() {
//		return zipCode;
//	}
//	
//	 
//	
//	
//
//}
