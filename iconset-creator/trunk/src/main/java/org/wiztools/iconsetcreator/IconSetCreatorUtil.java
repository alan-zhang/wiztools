package org.wiztools.iconsetcreator;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author subwiz
 */
public class IconSetCreatorUtil {
    private IconSetCreatorUtil() {}
    
    private static final String PNG = "png";
    private static final String name_prefix = "icon";
    
    private static final Dimension d_512_2x = new Dimension(1024, 1024);
    private static final String name_suffix_512_2x = "_512x512@2x";
    
    private static final Dimension d_512 = new Dimension(512, 512);
    private static final String name_suffix_512 = "_512x512";
    
    private static final Dimension d_256_2x = new Dimension(512, 512);
    private static final String name_suffix_256_2x = "_256x256@2x";
    
    private static final Dimension d_256 = new Dimension(256, 256);
    private static final String name_suffix_256 = "_256x256";
    
    private static final Dimension d_128_2x = new Dimension(256, 256);
    private static final String name_suffix_128_2x = "_128x128@2x";
    
    private static final Dimension d_128 = new Dimension(128, 128);
    private static final String name_suffix_128 = "_128x128";
    
    private static final Dimension d_32_2x = new Dimension(64, 64);
    private static final String name_suffix_32_2x = "_32x32@2x";
    
    private static final Dimension d_32 = new Dimension(32, 32);
    private static final String name_suffix_32 = "_32x32";
    
    private static final Dimension d_16_2x = new Dimension(32, 32);
    private static final String name_suffix_16_2x = "_16x16@2x";
    
    private static final Dimension d_16 = new Dimension(16, 16);
    private static final String name_suffix_16 = "_16x16";
    
    
    public static void create(File imageFile, File outParentDir) throws IOException {
        BufferedImage origImg = ImageIO.read(imageFile);
        if(origImg.getHeight() != origImg.getWidth()) {
            throw new IllegalArgumentException("Image dimension is NOT square!");
        }
        
        if(origImg.getHeight() < 1024) {
            throw new IllegalArgumentException("Image dimension SHOULD BE more than 1024px.");
        }
        
        // Create out directory:
        if(!outParentDir.exists() || !outParentDir.isDirectory()) {
            throw new IOException("Directory specified does NOT exist: " + outParentDir.getAbsolutePath());
        }
        File outDir = new File(outParentDir, "icon.iconset");
        if(outDir.exists()) {
            throw new IOException("Directory already exists: " + outDir.getAbsolutePath());
        }
        outDir.mkdir();
        
        // Save images:
        
        { // 1024
            BufferedImage img = resize(origImg, d_512_2x);
            ImageIO.write(img, PNG, new File(outDir, name_prefix + name_suffix_512_2x + "." + PNG));
        }
        
        { // 512
            BufferedImage img = resize(origImg, d_512);
            ImageIO.write(img, PNG, new File(outDir, name_prefix + name_suffix_512 + "." + PNG));
        }
        
        { // 512
            BufferedImage img = resize(origImg, d_256_2x);
            ImageIO.write(img, PNG, new File(outDir, name_prefix + name_suffix_256_2x + "." + PNG));
        }
        
        { // 256
            BufferedImage img = resize(origImg, d_256);
            ImageIO.write(img, PNG, new File(outDir, name_prefix + name_suffix_256 + "." + PNG));
        }
        
        { // 256
            BufferedImage img = resize(origImg, d_128_2x);
            ImageIO.write(img, PNG, new File(outDir, name_prefix + name_suffix_128_2x + "." + PNG));
        }
        
        { // 128
            BufferedImage img = resize(origImg, d_128);
            ImageIO.write(img, PNG, new File(outDir, name_prefix + name_suffix_128 + "." + PNG));
        }
        
        { // 64
            BufferedImage img = resize(origImg, d_32_2x);
            ImageIO.write(img, PNG, new File(outDir, name_prefix + name_suffix_32_2x + "." + PNG));
        }
        
        { // 32
            BufferedImage img = resize(origImg, d_32);
            ImageIO.write(img, PNG, new File(outDir, name_prefix + name_suffix_32 + "." + PNG));
        }
        
        { // 32
            BufferedImage img = resize(origImg, d_16_2x);
            ImageIO.write(img, PNG, new File(outDir, name_prefix + name_suffix_16_2x + "." + PNG));
        }
        
        { // 16
            BufferedImage img = resize(origImg, d_16);
            ImageIO.write(img, PNG, new File(outDir, name_prefix + name_suffix_16 + "." + PNG));
        }
    }
    
    private static BufferedImage resize(BufferedImage origImg, Dimension d) throws IOException {
        BufferedImage newImage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g = newImage.createGraphics();
        g.drawImage(origImg, 0, 0, d.width, d.height, null);
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.dispose();
        
        return newImage;
    }
}
