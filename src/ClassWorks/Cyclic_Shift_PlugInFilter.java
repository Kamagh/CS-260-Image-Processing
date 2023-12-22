import ij.ImagePlus;
import ij.process.ImageProcessor;
import ij.io.FileSaver;
import ij.plugin.filter.PlugInFilter;

import java.awt.Rectangle;
import java.io.File;

class BatchImageBinaryConverter implements PlugInFilter {

    private int temp;

    public int setup(String args, ImagePlus im) {
        return DOES_RGB;
    }

    public void run(ImageProcessor inputIP) {
        // Specify the input and output folders
        String inputFolderPath = "path/to/your/input/folder";
        String outputFolderPath = "path/to/your/output/folder";

        File inputFolder = new File(inputFolderPath);

        if (!inputFolder.exists() || !inputFolder.isDirectory()) {
            System.err.println("Invalid input folder path");
            return;
        }

        // List all files in the input folder
        File[] files = inputFolder.listFiles();

        if (files == null || files.length == 0) {
            System.err.println("No files found in the input folder");
            return;
        }

        for (File inputFile : files) {
            if (inputFile.isFile()) {
                // Process each image in the folder
                processImage(inputFile, outputFolderPath);
            }
        }

        System.out.println("Batch processing complete.");
    }

    private void processImage(File inputFile, String outputFolderPath) {
        try {
            ImagePlus imagePlus = new ImagePlus(inputFile.getAbsolutePath());
            ImageProcessor processor = imagePlus.getProcessor();

            // Convert the image to grayscale
            processor.convertToByte(true);

            // Threshold the image to create a binary image
            processor.threshold(128);

            // Save the binary image to a new file in the output folder
            String outputFilePath = outputFolderPath + File.separator + inputFile.getName();
            FileSaver fileSaver = new FileSaver(imagePlus);
            fileSaver.saveAsJpeg(outputFilePath);

            System.out.println("Binary image saved successfully: " + outputFilePath);
        } catch (Exception e) {
            System.err.println("Error processing image: " + inputFile.getName());
            e.printStackTrace();
        }
    }
}
