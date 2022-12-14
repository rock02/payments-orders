package br.com.meli.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
	
	public static String URL_API;
	public static int REQ_SIZE;

	private static final String PROP_FILE_CONFIG = "src/test/resources/%s/config.properties";

	private static Properties getProp(String base) throws IOException {

		Properties props = new Properties();

		FileInputStream file = new FileInputStream(String.format(PROP_FILE_CONFIG, base));

		props.load(file);

		return props;
	}

	public static void loadProperties(String base) {

		try {

			Properties properties = getProp(base);

			URL_API = properties.getProperty("url.api");
			REQ_SIZE = Integer.parseInt(properties.getProperty("req.size"));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
