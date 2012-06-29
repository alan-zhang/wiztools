package org.wiztools.ldap.search;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author subwiz
 */
public class SearchFilterBuilderTest {
    
    public SearchFilterBuilderTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of and method, of class SearchFilterBuilder.
     */
    @Test
    public void testAndOr() {
        System.out.println("and-or");
        SearchFilterBuilder instance = new SearchFilterBuilder();
        instance.or("(cn=subhash.chandran)");
        instance.or("(mail=subwiz@wiztools.org)");
        instance.and("(objectClass=inetOrgPerson)");
        
        String exp = "(&(objectClass=inetOrgPerson)(|(mail=subwiz@wiztools.org)(cn=subhash.chandran)))";
        assertEquals(exp, instance.toString());
    }
}
