package configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class PropertiesISW extends Properties{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static PropertiesISW prop;



    private PropertiesISW() {
        try {

            this.loadFromXML(getClass().getClassLoader().getResourceAsStream("properties.xml"));
        } catch (InvalidPropertiesFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
    }

    public static PropertiesISW getInstance() {
        if (prop==null) {
            prop=new PropertiesISW();
        }
        return prop;

    }
}
