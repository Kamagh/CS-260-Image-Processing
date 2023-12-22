package FinalProject;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import ij.io.FileSaver;

import java.io.File;

public class BatchImageBinaryConverter implements PlugInFilter {

    public static void main(String[] args) {
        String inputFolderPath = "./pictures";

        // Replace "path/to/your/output/folder" with the actual path to your output folder
        String outputFolderPath = "./binary_pictures";

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

    private static void processImage(File inputFile, String outputFolderPath) {
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

    @Override
    public int setup(String s, ImagePlus imagePlus) {
        return DOES_RGB;
    }

    @Override
    public void run(ImageProcessor imageProcessor) {

    }
}

