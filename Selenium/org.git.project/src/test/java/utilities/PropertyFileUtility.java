package utilities;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

public class PropertyFileUtility
{
	public static String getValueInPropertyFile(String propertyname) throws Exception
	{
		String fp=System.getProperty("user.dir")+"//src//test//resources//datafiles//config.properties";
		File f=new File(fp);
		FileInputStream fi=new FileInputStream(f);
		Properties pro=new Properties();
		pro.load(fi);
		String value=pro.getProperty(propertyname);
		fi.close();
		return(value);
	}
}
