package cz.muni.fi.PA165.flight.web.json;

import cz.muni.fi.PA165.flight.transfer.AirportTO;
import cz.muni.fi.PA165.flight.transfer.FlightTO;
import cz.muni.fi.PA165.flight.transfer.PlaneTO;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

/**
 * User: PC
 * Date: 14. 12. 2014
 * Time: 13:13
 */
@Component
public class JsonObjectBuilderHelper {


    public JsonObjectBuilder planeJsonObjectBuilder(PlaneTO planeTO) {
        return Json.createObjectBuilder()
                .add("id", planeTO.getId())
                .add("fuelLeft", planeTO.getFuelLeft())
                .add("manufacturer", planeTO.getManufacturer())
                .add("type", planeTO.getType())
                .add("passangerSeatsCount", planeTO.getPassangerSeatsCount())
                .add("staffSeatsCount", planeTO.getStaffSeatsCount())
                .add("tankCapacity", planeTO.getTankCapacity())
                .add("lastRevisionTime", planeTO.getLastRevisionTime() != null ? planeTO.getLastRevisionTime().getTime(): 0 )
                .add("totalFlightTime", planeTO.getTotalFlightTime())
                .add("totalFlightDistance", planeTO.getTotalFlightDistance());
    }

    public JsonObjectBuilder airportJsonObjectBuilder(AirportTO airportTO) {
        return Json.createObjectBuilder()
                .add("id", airportTO.getId())
                .add("name", airportTO.getName())
                .add("state", airportTO.getState())
                .add("city", airportTO.getCity());
    }

    public JsonObjectBuilder stewardJsonObjectBuilder(StewardTO stewardTO) {
        return Json.createObjectBuilder()
                .add("id", stewardTO.getId())
                .add("firstName", stewardTO.getFirstName())
                .add("lastName", stewardTO.getLastName());
    }


    public JsonObjectBuilder flightJsonObjectBuilder(FlightTO flightTO) {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("id", flightTO.getId())
                .add("departureTime", flightTO.getDepartureTime().getTime())
                .add("airportFrom", airportJsonObjectBuilder(flightTO.getFrom()))
                .add("arrivalTime", flightTO.getArrivalTime().getTime())
                .add("airportTo", airportJsonObjectBuilder(flightTO.getTo()))
                .add("plane", planeJsonObjectBuilder(flightTO.getPlane()));
        JsonArrayBuilder stewards = Json.createArrayBuilder();

        for(StewardTO stewardTO : flightTO.getStewards()){
            stewards.add(stewardJsonObjectBuilder(stewardTO));
        }

        builder.add("stewards", stewards);
        return builder;
    }

}
