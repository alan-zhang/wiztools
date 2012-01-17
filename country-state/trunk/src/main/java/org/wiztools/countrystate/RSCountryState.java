package org.wiztools.countrystate;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            Logger.getLogger(RSCountryState.class.getName()).log(Level.WARNING,
                    "Not available for country: {0}", countryNameCode);
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                        .entity("No country found!")
                        .build());
        }
        
        // States
        final List<StateBean> states = country.getStates();
        if(states.isEmpty()) {
            throw new WebApplicationException(
                    Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("No state available for country!")
                        .build());
        }
        return states;
    }
}
