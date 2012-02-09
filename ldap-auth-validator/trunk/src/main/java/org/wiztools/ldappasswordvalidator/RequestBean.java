package org.wiztools.ldappasswordvalidator;

/**
 *
 * @author subwiz
 */
public class RequestBean {
    private String ldapUrl;
    private String baseDN;
    private String userCN;
    private String password;

    public String getBaseDN() {
        return baseDN;
    }

    public void setBaseDN(String baseDN) {
        this.baseDN = baseDN;
    }

    public String getFqdnLdapUrl() {
        StringBuilder sb = new StringBuilder(ldapUrl);
        if(!ldapUrl.endsWith("/")) {
            sb.append("/");
        }
        sb.append(baseDN);
        return sb.toString();
    }

    public void setLdapUrl(String ldapUrl) {
        this.ldapUrl = ldapUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCN() {
        return userCN;
    }

    public void setUserCN(String userCN) {
        this.userCN = userCN;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("ldapUrl=").append(ldapUrl).append("; ")
                .append("baseDN=").append(baseDN).append("; ")
                .append("userCN=").append(userCN).append("}");
        return sb.toString();
    }
}
