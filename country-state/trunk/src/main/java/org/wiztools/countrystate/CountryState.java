package org.wiztools.countrystate;

import java.util.*;

/**
 *
 * @author subwiz
 */
class CountryState {
    private final List<CountryBean> countries = new ArrayList<CountryBean>();
    private final Map<String, CountryBean> countryCode = new HashMap<String, CountryBean>();
    private final Map<String, CountryBean> countryName = new HashMap<String, CountryBean>();
    
    void addCountry(CountryBean country) {
        countries.add(country);
        
        countryCode.put(country.getCode().toLowerCase(), country);
        countryName.put(country.getName().toLowerCase(), country);
    }
    
    List<CountryBean> getAllCountries() {
        return Collections.unmodifiableList(countries);
    }
    
    CountryBean getCountryByCode(String code) {
        return countryCode.get(code);
    }
    
    CountryBean getCountryByName(String name) {
        return countryName.get(name);
    }
}
