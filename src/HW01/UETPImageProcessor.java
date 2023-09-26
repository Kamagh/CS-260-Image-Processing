package HW01;

import ij.*;
import ij.process.ImageProcessor;
import ij.plugin.filter.PlugInFilter;

public class UETPImageProcessor implements PlugInFilter {
    public static void main(String[] args) {
        System.out.println("Hi");
    }
    public int setup(String arg, ImagePlus imp) {
        return DOES_8G | DOES_16;
    }

    public void run(ImageProcessor ip) {
        int width = ip.getWidth();
        int height = ip.getHeight();

        // 1.
        int leftWidth = width / 2;
        int rightWidth = width - leftWidth;

        ImageProcessor leftPanel = ip.createProcessor(leftWidth, height);
        ImageProcessor rightPanel = ip.createProcessor(rightWidth, height);


        // 2.
        leftPanel.copyBits(ip, 0, 0, leftWidth);
        rightPanel.copyBits(ip, leftWidth, 0, rightWidth);

        ip.insert(rightPanel, 0, 0);
        ip.insert(leftPanel, rightWidth, 0);

        // 3.
        int topHeight = height / 2;
        int bottomHeight = height - topHeight;

        ImageProcessor topPanel = ip.createProcessor(width, topHeight);
        ImageProcessor bottomPanel = ip.createProcessor(width, bottomHeight);

        // Task 4: Swap the top and bottom panels vertically
        topPanel.copyBits(ip, 0, 0, width);
        bottomPanel.copyBits(ip, 0, topHeight, width);


        ip.insert(bottomPanel, 0, 0);
        ip.insert(topPanel, 0, bottomHeight);

        ImagePlus modifiedImage = new ImagePlus("Modified UETP Image", ip);
        IJ.saveAs(modifiedImage, "PNG", "copy.png");

        modifiedImage.show();
    }
}

