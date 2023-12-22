package FinalProject;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import ij.io.FileSaver;

public class ImageBinaryConverter {

    public static void main(String[] args) {
        // Replace "path/to/your/image.jpg" with the actual path to your image file
        String inputImagePath = "path/to/your/image.jpg";
        String outputImagePath = "path/to/your/binary/image.jpg";

        ImagePlus imagePlus = new ImagePlus(inputImagePath);
        ImageProcessor processor = imagePlus.getProcessor();

        // Convert the image to grayscale
        processor.convertToByte(true);

        // Threshold the image to create a binary image
        processor.threshold(128);

        // Save the binary image to a new file
        FileSaver fileSaver = new FileSaver(imagePlus);
        fileSaver.saveAsJpeg(outputImagePath);

        System.out.println("Binary image saved successfully.");
    }
}
