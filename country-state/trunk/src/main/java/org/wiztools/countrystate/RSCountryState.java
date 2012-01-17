package org.wiztools.countrystate;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author subwiz
 */
@Path("/")
public class RSCountryState {
    private final static CountryState data;
    static {
        try {
            data = DataParse.getCountryState();
        }
        catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @GET
    @Produces("application/json")
    @Path("/countries.json")
    public List<CountryBean> getCountries() {
        return data.getAllCountries();
    }
    
    @GET
    @Produces("application/json")
    @Path("/country/{countryNameCode}/states.json")
    public List<StateBean> getStates(
            @PathParam("countryNameCode") String countryNameCode) {
        // Get the country first
        final String nameCode = countryNameCode.toLowerCase();
        final CountryBean country = data.getCountryByCode(nameCode)==null
                ? data.getCountryByName(nameCode)
                : data.getCountryByCode(nameCode);
        if(country == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                        .entity("No country found: " + countryNameCode)
                        .build());
        }
        
        // States
        final List<StateBean> states = country.getStates();
        if(states.isEmpty()) {
            throw new WebApplicationException(
                    Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("No state information available for country: "
                            + countryNameCode)
                        .build());
        }
        return states;
    }
}
