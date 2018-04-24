package Util;

import javax.xml.bind.*;
import java.io.File;

public class MarshallerUtil {
    public void save(String dir, Object object) {

        try {
            JAXBContext context = JAXBContext.newInstance(
                    object.getClass() );
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            File file = new File( dir );
            marshaller.marshal(object, file);
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }

    public Object load(String dir, Object object) {

        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass() );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(
                    new File(dir) );

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
