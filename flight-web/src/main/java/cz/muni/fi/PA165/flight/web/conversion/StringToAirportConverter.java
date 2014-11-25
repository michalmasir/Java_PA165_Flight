package cz.muni.fi.PA165.flight.web.conversion;

import cz.muni.fi.PA165.flight.service.AirportService;
import cz.muni.fi.PA165.flight.transfer.AirportTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: V
 * Date: 25.11.2014
 * Time: 12:38
 */
public class StringToAirportConverter  implements Converter<String, AirportTO> {

    @Autowired
    private AirportService airportService;

    public AirportTO convert(String id) {
        try {
            return airportService.getAirportById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

}

