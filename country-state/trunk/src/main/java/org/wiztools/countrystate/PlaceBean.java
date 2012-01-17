package org.wiztools.countrystate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author subwiz
 */
@XmlRootElement
public class PlaceBean {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("{")
                .append("code=").append(code)
                .append("; ")
                .append("name=").append(name)
                .append("}")
                .toString();
    }
}
