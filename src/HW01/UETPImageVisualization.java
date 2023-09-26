package HW01;

import ij.*;
import ij.plugin.PlugIn;
import ij.process.BinaryProcessor;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UETPImageVisualization implements PlugIn {


    @Override
    public void run(String arg) {
        String crsFilePath = "src/HW01/data/1_car-f-92.crs";
        String stuFilePath = "src/HW01/data/1_car-f-92.stu";

        // 1.
        int N = readCRSFile(crsFilePath);

        // 2.
        ImageProcessor binaryProcessor = new BinaryProcessor(new ByteProcessor(N, N));
        binaryProcessor.setColor(Color.WHITE);
        binaryProcessor.fill();


        // 3.
        markClashingCourses(stuFilePath, binaryProcessor);

        // 4.
        String imageName = crsFilePath.replaceFirst(".crs$", ".png");

        ImagePlus imagePlus = new ImagePlus("UETP Visualization", binaryProcessor);
        imagePlus.show();

        IJ.save(imagePlus, imageName);
    }

    private int readCRSFile(String crsFilePath) {
        int N = -1;
        try (BufferedReader br = new BufferedReader(new FileReader(crsFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                N = Integer.parseInt(line.split(" ")[0]);
            }
        } catch (IOException e) {
            IJ.showMessage("Error", "An error occurred while reading the .crs file.");
        }
        return N;
    }

    private void markClashingCourses(String stuFilePath, ImageProcessor binaryProcessor) {
        try (BufferedReader br = new BufferedReader(new FileReader(stuFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split(" ");
                if (tokens.length > 1) {
                    for (int i = 0; i < tokens.length - 1; i++) {
                        int x = Integer.parseInt(tokens[i]);
                        for (int j = i + 1; j < tokens.length; j++) {
                            int y = Integer.parseInt(tokens[j]);
                            binaryProcessor.putPixel(x - 1, y - 1, 0); // Adjust for 0-based indexing
                        }
                    }
                }
            }
        } catch (IOException e) {
            IJ.showMessage("Error", "An error occurred while reading the .stu file.");
        }
    }
}
