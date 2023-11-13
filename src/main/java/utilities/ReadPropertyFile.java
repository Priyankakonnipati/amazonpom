package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {


	public static void main(String[] args) throws IOException {

		//Give path of src/test/resources : Configfiles : config.Properties File
	    FileReader fr = new FileReader("System.getProperty(\\\"user.dir\\\")+/src/test/resources/configfiles/config.properties") ;

		//Create Object of Properties Class
	    Properties p = new  Properties();

	    p.load(fr);

	    //It take values from config.Properties File
	    System.out.println(p.getProperty("Browser"));
	    System.out.println(p.getProperty("TestUrl"));


		}



}
