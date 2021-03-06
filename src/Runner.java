import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        notRunner run = new notRunner();
        String ITALICS = "\033[3m";
        String REGULAR = "\033[0m";

        System.out.println(ITALICS + run.loadData() + REGULAR);

        System.out.println("Welcome to the LVM system.");
        System.out.print("cmd#: ");
        String command = input.nextLine();

        while (!command.equals("exit")) {
            if (command.indexOf("install-drive ") == 0) {
                String driveStr = command.substring(command.indexOf(" ") + 1);

                String driveName = driveStr.substring(0, driveStr.indexOf(" "));
                int driveSize = Integer.parseInt(driveStr.substring(
                        driveStr.indexOf(" ") + 1, driveStr.indexOf("G")));

                System.out.println(ITALICS + run.createDrive(driveName, driveSize) + REGULAR);
            }
            else if (command.equals("list-drives")) {
                run.printDrives();
            }
            else if (command.indexOf("pvcreate ") == 0) {
                String pvStr = command.substring(command.indexOf(" ") + 1);

                String pvName = pvStr.substring(0, pvStr.indexOf(" "));
                String driveFor = pvStr.substring(pvStr.indexOf(" ") + 1);

                System.out.println(ITALICS + run.createPV(pvName, driveFor) + REGULAR);
            }
            else if (command.equals("pvlist")) {
                run.printPVs();
            }
            else if (command.indexOf("vgcreate ") == 0) {
                String vgStr = command.substring(command.indexOf(" ") + 1);

                String vgName = vgStr.substring(0, vgStr.indexOf(" "));
                String pvFor = vgStr.substring(vgStr.indexOf(" ") + 1);

                System.out.println(ITALICS + run.createVG(vgName, pvFor) + REGULAR);
            }
            else if (command.indexOf("vgextend ") == 0) {
                String vgStr = command.substring(command.indexOf(" ") + 1);

                String vgName = vgStr.substring(0, vgStr.indexOf(" "));
                String pvName = vgStr.substring(vgStr.indexOf(" ") + 1);

                System.out.println(ITALICS + run.extendVG(vgName, pvName) + REGULAR);
            }
            else if (command.equals("vglist")) {
                run.printVGs();
            }
            else if (command.indexOf("lvcreate ") == 0) {
                String lvStr = command.substring(command.indexOf(" ") + 1);

                String lvName = lvStr.substring(0, lvStr.indexOf(" "));
                String lvNextStr = lvStr.substring(lvStr.indexOf(" ") + 1);
                int lvSize = Integer.parseInt(lvNextStr.substring(0,
                        lvNextStr.indexOf("G")));
                String vgFor = lvNextStr.substring(lvNextStr.indexOf("G") + 2);

                System.out.println(lvName + " " + lvSize + " " + vgFor);

                System.out.println(ITALICS + run.createLV(lvName, lvSize, vgFor) + REGULAR);
            }
            else if (command.equals("lvlist")) {
                run.printLVs();
            }
            else {
                System.out.println(ITALICS + "Error" + REGULAR);
            }
            System.out.print("cmd#: ");
            command = input.nextLine();
        }

        System.out.println(ITALICS + run.saveData() + REGULAR);
    }
}
