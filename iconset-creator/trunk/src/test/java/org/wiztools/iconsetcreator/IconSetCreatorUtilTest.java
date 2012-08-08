package org.wiztools.iconsetcreator;

import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author subwiz
 */
public class IconSetCreatorUtilTest {
    
    public IconSetCreatorUtilTest() {
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
     * Test of create method, of class IconSetCreatorUtil.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        File imageFile = new File("src/test/resources/rest-client-icns.png");
        File outParentDir = File.createTempFile("iconset-creator-", "-unit-test");
        outParentDir.delete();
        outParentDir.mkdir();
        System.out.println("Parent Directory: " + outParentDir.getAbsolutePath());
        IconSetCreatorUtil.create(imageFile, outParentDir);
    }
}
