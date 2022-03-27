import java.util.ArrayList;
import java.util.UUID;

public class notRunner {
    private ArrayList<Drive> drives;
    private ArrayList<PV> pvs;
    private ArrayList<VG> vgs;
    private ArrayList<LV> lvs;
    private SaveData file;

    public notRunner() {
        drives = new ArrayList<Drive>();
        pvs = new ArrayList<PV>();
        vgs = new ArrayList<VG>();
        lvs = new ArrayList<LV>();
        file = new SaveData(this);
    }

    public ArrayList<Drive> getDrives() {
        return drives;
    }

    public ArrayList<PV> getPvs() {
        return pvs;
    }

    public ArrayList<VG> getVgs() {
        return vgs;
    }

    public ArrayList<LV> getLvs() {
        return lvs;
    }

    public String saveData() {
        return file.save();
    }

    public String loadData() {
        return file.load();
    }

    public String createDrive(String name, int size) {
        if (!checkList(name, "Drive")) {
            drives.add(new Drive(name, size));
            return "Drive " + name + " installed";
        }
        else {
            return "Drive " + name + " installation failed";
        }
    }

    public String createPV(String name, String driveName) {
        Drive drive;
        for (Drive d : drives) {
            if (d.getName().equals(driveName)) {
                drive = d;
                if (checkDrive(drive) && !checkList(name, "PV")) {
                    pvs.add(new PV(name, drive));
                    return name + " created";
                }
                else {
                    break;
                }
            }
        }

        return name + " creation failed";

    }

    public void createPV(String name, String driveName, UUID uuid) {
        Drive drive;
        for (Drive d : drives) {
            if (d.getName().equals(driveName)) {
                drive = d;
                pvs.add(new PV(name, drive, uuid));
                break;
            }
        }
    }

    public String createVG(String name, String pvName) {
        PV pv;
        for (PV p : pvs) {
            if (p.getName().equals(pvName)) {
                pv = p;
                if (checkPV(pv) && !checkList(name, "VG")) {
                    vgs.add(new VG(name, pv));
                    return name + " created";
                }
            }
        }

        return name + " creation failed";
    }

    public void createVG(String name, String pvName, UUID uuid) {
        PV pv;
        for (PV p : pvs) {
            if (p.getName().equals(pvName)) {
                pv = p;
                vgs.add(new VG(name, pv, uuid));
            }
        }
    }

    public String extendVG(String vgName, String pvName) {
        VG vg;
        for (VG v : vgs) {
            if (v.getName().equals(vgName)) {
                vg = v;
                PV pv;
                for (PV p : pvs) {
                    if (p.getName().equals(pvName)) {
                        pv = p;
                        if (checkPV(pv)) {
                            vg.extend(pv);
                            return pvName + " added to " + vgName;
                        }
                        else {
                            break;
                        }
                    }
                }
            }
        }

        return vgName + " extension failed";
    }

    public String createLV(String name, int size, String vgName) {
        VG vg;
        for (VG v : vgs) {
            if (v.getName().equals(vgName)) {
                vg = v;
                if (checkVG(vg, size) && !checkList(name, "LV")) {
                    lvs.add(new LV(name, size, vg));
                    return name + " created";
                }
                else {
                    break;
                }
            }
        }

        return name + " creation failed";
    }

    public void createLV(String name, int size, String vgName, UUID uuid) {
        VG vg;
        for (VG v : vgs) {
            if (v.getName().equals(vgName)) {
                vg = v;
                lvs.add(new LV(name, size, vg, uuid));
            }
        }
    }

    public void printDrives() {
        for (Drive drive : drives) {
            System.out.println(drive);
        }
    }

    public void printPVs() {
        sortByVG("PV");
        for (PV pv : pvs) {
            System.out.println(pv);
        }
    }

    public void printVGs() {
        for (VG vg : vgs) {
            System.out.println(vg);
        }
    }

    public void printLVs() {
        sortByVG("LV");
        for (LV lv : lvs) {
            System.out.println(lv);
        }
    }

    private boolean checkList(String name, String type) {
        if (type.equals("Drive")) {
            for (Drive o : drives) {
                if (o.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }
        else if (type.equals("PV")) {
            for (PV o : pvs) {
                if (o.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }
        else if (type.equals("VG")) {
            for (VG o : vgs) {
                if (o.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }
        else if (type.equals("LV")) {
            for (LV o : lvs) {
                if (o.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean checkDrive(Drive drive) {
        for (PV pv : pvs) {
            if (pv.getDrive().equals(drive)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPV(PV pv) {
        for (VG vg : vgs) {
            for (PV p : vg.getpVList()) {
                if (p.equals(pv)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkVG(VG vg, int size) {
        return vg.getFreeSpace() >= size;
    }

    private void sortByVG(String type) {
        if (type.equals("PV")) {
            for (int i = 1; i < pvs.size(); i++) {
                PV currentPV = pvs.get(i);
                int j = i - 1;
                while(j >= 0 && compare(currentPV, pvs.get(j)) < 0) {
                    pvs.set(j + 1, pvs.get(j));
                    j--;
                }
                pvs.set(j + 1, currentPV);
            }
        }
        if (type.equals("LV")) {
            for (int i = 1; i < lvs.size(); i++) {
                LV currentLV = lvs.get(i);
                int j = i - 1;
                while(j >= 0 && compare(currentLV, lvs.get(j)) < 0) {
                    lvs.set(j + 1, lvs.get(j));
                    j--;
                }
                lvs.set(j + 1, currentLV);
            }
        }

    }

    private int compare(PV pv1, PV pv2) {
        if (pv1.getVg() == null ||  pv2.getVg() == null) {
            return -1;
        }
        return pv1.getVg().getName().compareTo(pv2.getVg().getName());
    }

    private int compare(LV lv1, LV lv2) {
        return lv1.getVg().getName().compareTo(lv2.getVg().getName());
    }

}
