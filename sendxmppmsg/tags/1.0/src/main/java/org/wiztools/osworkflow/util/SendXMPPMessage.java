/*
 * SendXMPPMessage.java
 * 
 * Created on Oct 22, 2007, 3:30:49 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wiztools.osworkflow.util;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * Sends XMPP message to a group of users. The following arguments are expected:
 * <ul>
 * <li>server - XMPP server host</li>
 * <li>port - XMPP server port</li>
 * <li>to - Comma-separated-value of JIDs</li>
 * <li>username - Username to login into XMPP server (message will be sent from this user)</li>
 * <li>password - Password for the corresponding username</li>
 * <li>message - The message to send</li>
 * </ul>
 * @author Subhash Chandran
 */
public class SendXMPPMessage implements FunctionProvider {
    
    private static final Log log = LogFactory.getLog(SendXMPPMessage.class);
    
    private static final String SERVER = "server";
    private static final String PORT = "port";
    private static final String TO = "to";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";

    public void execute(Map transientVars, Map args, PropertySet ps) throws WorkflowException {
        
        String server = (String) args.get(SERVER);
        int port = Integer.parseInt((String)args.get(PORT));
        String to = (String) args.get(TO);
        String message = (String) args.get(MESSAGE);
        String username = (String) args.get(USERNAME);
        String password = (String) args.get(PASSWORD);

        XMPPConnection con = null;
        try {
            ConnectionConfiguration cf = new ConnectionConfiguration(server, port);
            con = new XMPPConnection(cf);
            con.connect();
            con.login(username, password);
            ChatManager chatmanager = con.getChatManager();
            String[] to_arr = to.split(",");
            for(String t: to_arr){
                if(t != null){
                    t = t.trim();
                    if(t.matches(".*@.*")){
                        Chat newChat = chatmanager.createChat(t, null);
                        newChat.sendMessage(message);
                    }
                    else{
                        log.error("Not a valid JID: " + t);
                    }
                }
            }
        } catch (XMPPException ex) {
            log.error("Error sending XMPP message:", ex);
        } finally{
            if(con != null){
                con.disconnect();
            }
        }
        
    }

}
