package org.wiztools.countrystate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author subwiz
 */
@XmlRootElement
public class CountryBean extends PlaceBean {
    @XmlTransient
    private final List<StateBean> states = new ArrayList<StateBean>();
    
    public void addState(StateBean state) {
        states.add(state);
    }

    public List<StateBean> getStates() {
        return Collections.unmodifiableList(states);
    }
}
