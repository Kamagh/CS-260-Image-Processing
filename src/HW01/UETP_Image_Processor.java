package HW01;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;


public class UETP_Image_Processor implements PlugInFilter {


    public int setup(String args, ImagePlus im) {
        return DOES_8G; // this plugin accepts 8-bit grayscale images
    }

    public static void main(String[] args) {
        System.out.println("he");
    }
    public void run(ImageProcessor ip) {
        int M = ip.getWidth();
        int N = ip.getHeight();

        // iterate over all image coordinates (u,v)
        for (int u = 0; u < M; u++) {
            for (int v = 0; v < N; v++) {
                int p = ip.getPixel(u, v);
                ip.putPixel(u, v, 255 - p);
            }
        }
    }
}

//public class UETPImageProcessor implements PlugInFilter {
//    public int setup(String args, ImagePlus im) {
//        return DOES_RGB;
//    }
//
//    public void run(ImageProcessor inputIP) {
//        int width = inputIP.getWidth();
//        int height = inputIP.getHeight();
//
//        // Define the region of interest (ROI)
//        int roiWidth = width / 2; // Adjust this as needed
//        int roiHeight = height / 2; // Adjust this as needed
//        int roiX = 0; // Adjust this as needed
//        int roiY = 0; // Adjust this as needed
//
//        inputIP.setRoi(roiX, roiY, roiWidth, roiHeight);
//
//        // Process the image
//        shift(inputIP, roiWidth);
//
//        // Clear the ROI
//        inputIP.resetRoi();
//
//        // Display the processed image
//        ImagePlus resultImage = new ImagePlus("Processed Image", inputIP);
//        resultImage.show();
//    }
//
//    private void swap(ImageProcessor ip, int row, int left, int right) {
//        int temp = ip.getPixel(left, row);
//        ip.putPixel(left, row, ip.getPixel(right, row));
//        ip.putPixel(right, row, temp);
//    }
//
//    private void flip(ImageProcessor ip, int row, int start, int end) {
//        while (start < end) {
//            swap(ip, row, start, end);
//            start++;
//            end--;
//        }
//    }
//
//    private void shift(ImageProcessor ip, int offset) {
//        int width = ip.getWidth();
//        int height = ip.getHeight();
//        for (int row = 0; row < height; row++) {
//            flip(ip, row, 0, offset - 1);
//            flip(ip, row, offset, width - 1);
//            flip(ip, row, 0, width - 1);
//        }
//    }
//}
