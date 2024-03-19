import Repositories.MachineTextRepository;
import Repositories.PartOperationTextRepository;
import Repositories.PartTextRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename = "C:\\Users\\admin\\IdeaProjects\\InterChallange\\src\\TextSource\\Input_One.txt";
        Scanner scanner = new Scanner(System.in);
        MachineTextRepository machineTextRepository = new MachineTextRepository(filename);
       // System.out.println(machineTextRepository.read());
        PartTextRepository partTextRepository = new PartTextRepository(filename);
       // System.out.println(partTextRepository.read());
        PartOperationTextRepository partOperationTextRepository = new PartOperationTextRepository(filename);
        //System.out.println(partOperationTextRepository.read())
        Main main = new Main();
        while (true){
            main.menu();
            int choice = scanner.nextInt();
            if(choice==1){
                System.out.println(machineTextRepository.read());
            }
            if(choice==2){
                System.out.println(partTextRepository.read());
            }
            if(choice==3){
                System.out.println(partOperationTextRepository.read());
            }
            if(choice==5){
                break;
            }
        }
    }
    public void menu(){
        System.out.println("1. Read machines");
        System.out.println("2. Read parts");
        System.out.println("3. Read part operations");
        System.out.println("5. Exit");
    }
}
