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
            else if (command.equals("list-drives")) {
                run.printDrives();
            }
            else if (command.indexOf("pvcreate ") == 0) {
                String pvStr = command.substring(command.indexOf(" ") + 1);
                String pvName = pvStr.substring(0, pvStr.indexOf(" "));
                String driveFor = pvStr.substring(pvStr.indexOf(" ") + 1);
                System.out.println(run.createPV(pvName, driveFor));
            }
            else if (command.equals("pvlist")) {
                run.printPVs();
            }
            else if (command.indexOf("vgcreate ") == 0) {
                String vgStr = command.substring(command.indexOf(" ") + 1);
                String vgName = vgStr.substring(0, vgStr.indexOf(" "));
                String pvFor = vgStr.substring(vgStr.indexOf(" ") + 1);
                System.out.println(run.createVG(vgName, pvFor));
            }
            else if (command.indexOf("vgextend ") == 0) {
                String vgStr = command.substring(command.indexOf(" ") + 1);
                String vgName = vgStr.substring(0, vgStr.indexOf(" "));
                String pvName = vgStr.substring(vgStr.indexOf(" ") + 1);
                System.out.println(run.extendVG(vgName, pvName));
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
                String vgFor = lvStr.substring(lvNextStr.indexOf(" ") + 1);
                System.out.println(run.createLV(lvName, lvSize, vgFor));
            }
            else if (command.equals("lvlist")) {
                run.printLVs();
            }
            else {
                System.out.println("\\033[3mError\\033[0m");
            }
            System.out.print("cmd#: ");
            command = input.nextLine();
        }
    }
}
