import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.UUID;

public class SaveData {
    private File data;
    private ArrayList<Drive> drives;
    private ArrayList<PV> pvs;
    private ArrayList<VG> vgs;
    private ArrayList<LV> lvs;
    private notRunner run;

    public SaveData(notRunner r) {
        run = r;
        data = new File("data.txt");
        drives = run.getDrives();
        pvs = run.getPvs();
        vgs = run.getVgs();
        lvs = run.getLvs();

    }

    public String save() {
        try {
            FileWriter writer = new FileWriter("data.txt");

            for (Drive drive : drives) {
                String driveStr = "D@" + drive.getName() + "|" + drive.getSize() + "\n";
                writer.write(driveStr);
            }
            for (PV pv : pvs) {
                String pvStr = "P@" + pv.getName() + "|" + pv.getUuid() +
                        "|" + pv.getDrive().getName() + "\n";
                writer.write(pvStr);
            }
            for (VG vg : vgs) {
                String vgStr = "V@" + vg.getName() + "|" + vg.getUuid() +
                        "|";
                for (PV p : vg.getpVList()) {
                    vgStr += p.getName() + ",";
                }
                vgStr = vgStr.substring(0, vgStr.length() - 1);
                vgStr += "|\n";

                writer.write(vgStr);
            }
            for (LV lv : lvs) {
                String lvStr = "L@" + lv.getName() + "|" + lv.getSize() + "|" + lv.getUuid() +
                        "|" + lv.getVg().getName() + "\n";
                writer.write(lvStr);
            }

            writer.close();
            return "Saved Data.";
        }
        catch (IOException e) {
            return "Error Message.";
        }
    }

    public String load() {
        try {
            Scanner reader = new Scanner(data);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains("D@")) {
                    String driveName = line.substring(2, line.indexOf("|"));
                    int driveSize = Integer.parseInt(line.substring(line.indexOf("|") + 1));
                    run.createDrive(driveName, driveSize);
                }
                if (line.contains("P@")) {
                    String pvName = line.substring(2, line.indexOf("|"));

                    String nextStr = line.substring(line.indexOf("|") + 1);
                    UUID pvUUID = UUID.fromString(nextStr.substring(0, nextStr.indexOf("|")));

                    nextStr = nextStr.substring(nextStr.indexOf("|") + 1);
                    String pvDriveName = nextStr;

                    run.createPV(pvName, pvDriveName, pvUUID);
                }
                if (line.contains("V@")) {
                    String vgName = line.substring(2, line.indexOf("|"));

                    String nextStr = line.substring(line.indexOf("|") + 1);
                    UUID vgUUID = UUID.fromString(nextStr.substring(0, nextStr.indexOf("|")));

                    nextStr = nextStr.substring(nextStr.indexOf("|") + 1);
                    if (!nextStr.contains(",")) {
                        String vgPVName = nextStr.substring(0, nextStr.indexOf("|"));
                        run.createVG(vgName, vgPVName, vgUUID);
                    }
                    else {
                        String vgPVName = nextStr.substring(0, nextStr.indexOf(","));
                        run.createVG(vgName, vgPVName, vgUUID);

                        nextStr = nextStr.substring(nextStr.indexOf(",") + 1);
                        while (nextStr.contains(",")) {
                            String vgNextPV = nextStr.substring(0, nextStr.indexOf(","));
                            run.extendVG(vgName, vgNextPV);
                            nextStr = nextStr.substring(nextStr.indexOf(",") + 1);
                        }
                        String vgFinalPV = nextStr.substring(0, nextStr.indexOf("|"));
                        run.extendVG(vgName, vgFinalPV);
                    }
                }
                if (line.contains("L@")) {
                    String lvName = line.substring(2, line.indexOf("|"));

                    String nextStr = line.substring(line.indexOf("|") + 1);
                    int lvSize = Integer.parseInt(nextStr.substring(0, nextStr.indexOf("|")));

                    nextStr = nextStr.substring(nextStr.indexOf("|") + 1);
                    UUID lvUUID = UUID.fromString(nextStr.substring(0, nextStr.indexOf("|")));
                    String lvVGName = nextStr.substring(nextStr.indexOf("|") + 1);

                    run.createLV(lvName, lvSize, lvVGName, lvUUID);
                }
            }
            reader.close();
            return "Data loaded.";
        }
        catch (FileNotFoundException e) {
            return "Error Message.";
        }
    }
}
