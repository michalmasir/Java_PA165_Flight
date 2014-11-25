package cz.muni.fi.PA165.flight.web.conversion;

import cz.muni.fi.PA165.flight.service.StewardService;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: V
 * Date: 25.11.2014
 * Time: 12:43
 */
public class StringToStewardConverter  implements Converter<String, StewardTO> {

    @Autowired
    private StewardService stewardService;

    public StewardTO convert(String id) {
        try {
            return stewardService.getStewardById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

}