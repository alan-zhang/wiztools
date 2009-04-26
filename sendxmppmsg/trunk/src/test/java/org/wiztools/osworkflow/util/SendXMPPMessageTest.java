/*
 * SendXMPPMessageTest.java
 * JUnit based test
 *
 * Created on October 22, 2007, 4:29 PM
 */

package org.wiztools.osworkflow.util;

import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.basic.BasicWorkflow;
import junit.framework.TestCase;

/**
 *
 * @author schandran
 */
public class SendXMPPMessageTest extends TestCase {
    
    public SendXMPPMessageTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testExecute() throws Exception {
        System.out.println("execute");
        try{
            Workflow wf = new BasicWorkflow("subhash");
            System.out.println("BasicWorkFlow created!");
            long id = wf.initialize("test", 1, null);
            System.out.println("Initialization id: " + id);
            wf.doAction(id, 2, null);
        }
        catch(Throwable e){
            e.printStackTrace();
            fail("The test case is a prototype.");
        }
        System.out.println("Ended!");
    }
    
}
