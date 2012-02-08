package org.wiztools.ldappasswordvalidator;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 *
 * @author subwiz
 */
public class LdapPasswordValidator {
    private static final String INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    private static final String SIMPLE_AUTHENTICATION = "simple";
    
    static void validate(RequestBean bean) throws NamingException {
        final Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, bean.getFqdnLdapUrl());
        
        // Authentication info:
        env.put(Context.SECURITY_AUTHENTICATION, SIMPLE_AUTHENTICATION);
        env.put(Context.SECURITY_PRINCIPAL, bean.getUserCN());
        env.put(Context.SECURITY_CREDENTIALS, bean.getPassword());
        
        // Bind:
        DirContext ctx = new InitialDirContext(env);
    }
}
