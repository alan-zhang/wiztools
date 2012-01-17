package org.wiztools.countrystate;

import java.io.IOException;
import nu.xom.*;

/**
 *
 * @author subwiz
 */
class DataParse {
    private static void populateStates(CountryBean country, Elements eStates) {
        for(int i=0; i<eStates.size(); i++) {
            Element eState = eStates.get(i);
            final String stateCode = eState.getAttributeValue("code");
            final String stateName = eState.getAttributeValue("name");
            
            StateBean state = new StateBean();
            state.setCode(stateCode);
            state.setName(stateName);
            
            country.addState(state);
        }
    }
    
    static CountryState getCountryState() throws IOException {
        
        try {
            CountryState out = new CountryState();

            Builder builder = new Builder();
            Document doc = builder.build(
                    DataParse.class.getResourceAsStream("/countries.xml"));
            
            Element eRoot = doc.getRootElement();
            Elements eCountries = eRoot.getChildElements("country");
            
            for(int i=0; i<eCountries.size(); i++) {
                Element eCountry = eCountries.get(i);
                final String countryCode = eCountry.getAttributeValue("code");
                final String countryName = eCountry.getAttributeValue("name");
                
                CountryBean country = new CountryBean();
                country.setCode(countryCode);
                country.setName(countryName);
                
                // States
                Elements eEncpStates = eCountry.getChildElements("states");
                if(eEncpStates.size() == 1) {
                    Elements eStates = eEncpStates.get(0).getChildElements("state");
                    populateStates(country, eStates);
                }
                
                out.addCountry(country);
            }

            return out;
        }
        catch(ParsingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
