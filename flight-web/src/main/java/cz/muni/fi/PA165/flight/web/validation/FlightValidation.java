package cz.muni.fi.PA165.flight.web.validation;

import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: PC
 * Date: 22. 11. 2014
 * Time: 18:45
 */
public class FlightValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FlightTO.class.isAssignableFrom(clazz);
    }

    public boolean flightsInterfere(FlightTO flight1, FlightTO flight2) {
        if (flight2.getDepartureTime().compareTo(flight1.getDepartureTime()) <= 0 && flight1.getDepartureTime().compareTo(flight2.getArrivalTime()) <= 0) {
            return true;
        }
        if (flight1.getDepartureTime().compareTo(flight2.getDepartureTime()) <= 0 && flight2.getDepartureTime().compareTo(flight2.getArrivalTime()) <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        FlightTO flight = (FlightTO) target;
        if (flight.getArrivalTime() == null) {
            errors.rejectValue("arrivalTime", "null");
        }
        if (flight.getDepartureTime() == null) {
            errors.rejectValue("departureTime", "null");
        }

        if (flight.getPlane() == null) {
            errors.rejectValue("plane", "null");
        }

        if (flight.getFrom() == null) {
            errors.rejectValue("from", "null");
        }

        if (flight.getTo() == null) {
            errors.rejectValue("to", "null");
        }

        if (flight.getArrivalTime() != null && flight.getDepartureTime() != null) {

            if ((flight.getArrivalTime().compareTo(flight.getDepartureTime()) < 0)) {
                errors.rejectValue("arrivalTime", "conflict");
            }

            for (FlightTO f : flight.getPlane().getFlights()) {
                if (f.equals(flight)) {
                    continue;
                }
                if (flightsInterfere(f, flight)) {
                    errors.rejectValue("plane", "conflict");
                }

            }

            for (StewardTO steward : flight.getStewards()) {
                for (FlightTO f : steward.getFlights()) {
                    if (f.equals(flight)) {
                        continue;
                    }
                    if (flightsInterfere(f, flight)) {
                        errors.rejectValue("stewards", "conflict");
                    }
                }
            }
        }
    }
}
