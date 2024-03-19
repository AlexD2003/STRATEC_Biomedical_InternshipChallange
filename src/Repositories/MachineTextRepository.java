package Repositories;

import Domain.Machine;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MachineTextRepository {
    private String filePath;
    public MachineTextRepository(String filePath) {
        this.filePath = filePath;
    }
    public List<Machine> read() {
        List<Machine> machines = new ArrayList<>();
        List<String> name=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String inputText= sb.toString();
            machines.addAll(extractMachines(inputText));
            name.addAll(extractMachineNames(inputText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i=0;
        for(Machine m:machines){
            m.setName(name.get(i));
            i++;
        }
        return machines;
    }
    private static List<String> extractMachineNames(String inputText) {
        List<String> machineNames = new ArrayList<>();
        String[] lines = inputText.split("\n");
        boolean startReading = false;
        for (String line : lines) {
            if (startReading) {
                if (line.matches("\\d+\\.\\s.*")) {
                    String machineName = line.substring(line.indexOf(". ") + 2);
                    machineNames.add(machineName);
                }
                if (line.trim().isEmpty()) {
                    break;
                }
            } else if (line.trim().equals("Available machines:")) {
                startReading = true;
            }
        }
        return machineNames;
    }
    private List<Machine> extractMachines(String inputText) {
        List<Machine> machines = new ArrayList<>();

        // Regular expressions to match machine data
        Pattern machinePattern = Pattern.compile("(\\d+):\\s+- Capacity: (.+)\\s+- Cooldown time: (.+)");
        Matcher matcher = machinePattern.matcher(inputText);

        while (matcher.find()) {
            int machineNumber = Integer.parseInt(matcher.group(1));
            String capacity = matcher.group(2);
            String cooldownTime = matcher.group(3);

            // Extracting cooldown time in seconds
            int cooldownSeconds = 0;
            if (!cooldownTime.equals("none")) {
                String[] cooldownParts = cooldownTime.split("\\s+");
                cooldownSeconds = Integer.parseInt(cooldownParts[0]) *
                        (cooldownParts[1].equals("seconds") ? 1 :
                                cooldownParts[1].equals("minutes") ? 60 : 3600);
            }


            // Creating Machine objects and adding them to the list
            machines.add(new Machine("Machine " + machineNumber, capacity, cooldownSeconds));
        }
        return machines;
    }
}
