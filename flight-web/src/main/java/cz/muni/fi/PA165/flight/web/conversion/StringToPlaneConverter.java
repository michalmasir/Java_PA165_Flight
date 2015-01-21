package cz.muni.fi.PA165.flight.web.conversion;

import cz.muni.fi.PA165.flight.service.PlaneService;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: V
 * Date: 25.11.2014
 * Time: 12:26
 */
@Component
public class StringToPlaneConverter  implements Converter<String, PlaneTO> {

    @Autowired
    private PlaneService planeService;

    public PlaneTO convert(String id) {
        try {
            return planeService.getPlaneById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
