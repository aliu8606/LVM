import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        notRunner run = new notRunner();

        System.out.println("Welcome to the LVM system.");
        System.out.print("cmd#: ");
        String command = input.nextLine();

        while (!command.equals("exit")) {
            if (command.indexOf("install-drive ") == 0) {
                String driveStr = command.substring(command.indexOf(" ") + 1);
                String driveName = driveStr.substring(0, driveStr.indexOf(" "));
                int driveSize = Integer.parseInt(driveStr.substring(
                        driveStr.indexOf(" ") + 1, driveStr.indexOf("G")));
                System.out.println(run.createDrive(driveName, driveSize));
            }
            System.out.print("cmd#: ");
            command = input.nextLine();
        }
    }
}
