package org.wiztools.countrystate;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author subwiz
 */
public class DataParseTest {
    
    public DataParseTest() {
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
     * Test of getCountryState method, of class DataParse.
     */
    @Test
    public void testGetCountryState() throws Exception {
        System.out.println("getCountryState");
        CountryState expResult = null;
        CountryState result = DataParse.getCountryState();
        System.out.println(result.getAllCountries());
        // assertEquals(expResult, result);
    }
}
