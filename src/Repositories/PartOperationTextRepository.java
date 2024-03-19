package Repositories;

import Domain.PartOperation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartOperationTextRepository {
    private String filePath;

    public PartOperationTextRepository(String filePath) {
        this.filePath = filePath;
    }

    // Method to read PartOperation objects from a text file
    public List<PartOperation> read() {
        List<PartOperation> partOperations = new ArrayList<>();
        boolean startReading = false;
        int partNumber = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("Part operations:")) {
                    startReading = true;
                    continue;
                }
                if (startReading) {
                    if (line.matches("^\\d+:\\s+-.*")) {
                        partNumber = Integer.parseInt(line.split(":")[0].trim());
                        String[] splitData = line.split(":\\s+", 3);
                        if (splitData.length == 3) {
                            String machineName = splitData[1].trim();
                            String processingTimeString = splitData[2].replaceAll("\\D+", "");
                            if (!processingTimeString.isEmpty()) {
                                int processingTime = Integer.parseInt(processingTimeString);
                                partOperations.add(new PartOperation(partNumber,machineName, processingTime));
                            } else {
                                System.err.println("Skipping line with empty processing time: " + line);
                            }
                        } else {
                            System.err.println("Skipping line with invalid format: " + line);
                        }
                    } else if (line.trim().startsWith("-")) {
                        String[] splitData = line.trim().split(":\\s+");
                        if (splitData.length == 2) {
                            String machineName = splitData[0].substring(1).trim();
                            String processingTimeString = splitData[1].replaceAll("\\D+", "");
                            if (!processingTimeString.isEmpty()) {
                                int processingTime = Integer.parseInt(processingTimeString);
                                partOperations.add(new PartOperation(partNumber,machineName, processingTime));
                            } else {
                                System.err.println("Skipping line with empty processing time: " + line);
                            }
                        } else {
                            System.err.println("Skipping line with invalid format: " + line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Read " + partOperations.size() + " PartOperations.");
        for (PartOperation partOperation : partOperations) {
            if(partOperation.getMachineName().startsWith("- ")){
                String newName=partOperation.getMachineName().substring(2);
                partOperation.setMachineName(newName);
            }
        }
        return partOperations;
    }
}
