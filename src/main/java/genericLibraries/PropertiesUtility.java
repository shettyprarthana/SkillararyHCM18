package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * This class contains all reusable method to perform actions on Properties file
 * @author Admin
 *
 */
public class PropertiesUtility {
	private Properties property;
	/**
	 * This method is used to initialize properties file
	 * @param filepath
	 */ 
	public void propertiesInit(String filepath) {
		FileInputStream fis=null;
		try {
			 fis = new FileInputStream(filepath);
			//user passess the file path, create parameter path
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 property= new Properties();
		
		try {
			property.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to fetch the data from properties file
	 * @param key
	 * @return
	 */

	public String readData(String key) {
		
		return property.getProperty(key);
		
	}
	/**
	 * This method is used to write and save data to properties file
	 * @param key
	 * @param value
	 * @param filepath
	 */
	public void writeToProperties(String key, String value, String filepath) {
		property.put(key, value);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			property.store(fos, "updated");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
