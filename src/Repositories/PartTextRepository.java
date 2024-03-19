package Repositories;

import Domain.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTextRepository {
    private String filePath;

    public PartTextRepository(String filePath) {
        this.filePath = filePath;
    }
    public List<Part> read() {
        List<Part> parts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            boolean inPartList = false;

            // Read the file line by line
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("Part list:")) {
                    inPartList = true;
                    continue;
                } else if (line.trim().startsWith("Part operations:")) {
                    break; // Stop parsing once we reach the part operations section
                }

                if (inPartList) {
                    // Extract parts from the current line
                    List<Part> lineParts = extractPartsFromLine(line);
                    parts.addAll(lineParts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parts;
    }

    private List<Part> extractPartsFromLine(String line) {
        List<Part> parts = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d+)\\.\\s*(.*?)\\s*-\\s*(\\d+)\\s*items?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String name = matcher.group(2).trim();
            int quantity = Integer.parseInt(matcher.group(3).trim());
            parts.add(new Part(name, quantity));
        }
        return parts;
    }
}
