package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.Test;
public class PropertyFileUtility 
{
	public static String getValueInProperty(String propertyname) throws Exception
	{
		String fp=System.getProperty("user.dir")+"//src//test//resources//properties//config.properties";
		File f=new File(fp);
		FileInputStream fi=new FileInputStream(f);
		Properties pro=new Properties();
		pro.load(fi);
		String value=pro.getProperty(propertyname);
		fi.close();
		return(value);
	}
@Test
public void main() throws Exception
{
	 String x=getValueInProperty("url");
	 System.out.println(x);
}
}