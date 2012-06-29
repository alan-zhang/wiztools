package org.wiztools.ldap.search;

/**
 * Class to build LDAP search query quickly.
 * @author subwiz
 */
public class SearchFilterBuilder {
    private String builder = null;
    
    private static final String PATTERN = "\\(.+\\)";
    
    private void validate(String str) {
        if(str == null)
            throw new NullPointerException("LDAP search filter is null.");
        if(!str.matches(PATTERN))
            throw new IllegalArgumentException(
                    "LDAP search filter must match pattern: " + PATTERN);
    }
    
    public void and(String str) {
        validate(str);
        if(builder != null)
            builder = "(&" + str + builder + ")";
        else
            builder = str;
    }
    
    public void or(String str) {
        validate(str);
        if(builder != null)
            builder = "(|" + str + builder + ")";
        else
            builder = str;
    }

    @Override
    public String toString() {
        return builder;
    }
}
