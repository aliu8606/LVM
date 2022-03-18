import java.util.ArrayList;

public class notRunner {
    private ArrayList<Drive> drives;
    private ArrayList<PV> pvs;
    private ArrayList<VG> vgs;
    private ArrayList<LV> lvs;

    public String createDrive(String name, int size) {
        if (!checkDriveList(drives, name)) {
            drives.add(new Drive(name, size));
            return "Drive " + name + " installed";
        }
        else {
            return "Drive " + name + " installation failed";
        }
    }

    public String createPV(String name, Drive drive) {
        if (checkDrive(drive) && !checkPVList(pvs, name)) {
            pvs.add(new PV(name, drive));
            return name + " created";
        }
        else {
            return name + " creation failed";
        }
    }

    public String createVG(String name, PV pv) {
        if (checkPV(pv) && !checkVGList(vgs, name)) {
            vgs.add(new VG(name, pv));
            return name + " created";
        }
        else {
            return name + " creation failed";
        }
    }

    public String extendVG(VG vg, PV pv) {
        if (checkPV(pv)) {
            vg.extend(pv);
            return pv.getName() + " added to " + vg.getName();
        }
        else {
            return vg.getName() + " extension failed";
        }
    }

    public String createLV(String name, int size, VG vg) {
        if (checkVG(vg, size) && !checkLVList(lvs, name)) {
            lvs.add(new LV(name, size, vg));
            return name + " created";
        }
        else {
            return name + " creation failed";
        }
    }

    public void printDrives() {
        for (Drive drive : drives) {
            System.out.println(drive);
        }
    }

    public void printPVs() {
        sortByVGWithPV(pvs);
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
        sortByVGWithLV(lvs);
        for (LV lv : lvs) {
            System.out.println(lv);
        }
    }

    public boolean checkDriveList(ArrayList<Drive> list, String name) {
        for (Drive o : list) {
            if (o.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPVList(ArrayList<PV> list, String name) {
        for (PV o : list) {
            if (o.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkVGList(ArrayList<VG> list, String name) {
        for (VG o : list) {
            if (o.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLVList(ArrayList<LV> list, String name) {
        for (LV o : list) {
            if (o.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDrive(Drive drive) {
        if (!drives.contains(drive)) {
            return false;
        }

        for (PV pv : pvs) {
            if (pv.getDrive().equals(drive)) {
                return false;
            }
        }

        return true;
    }

    public boolean checkPV(PV pv) {
        if (!pvs.contains(pv)) {
            return false;
        }

        for (VG vg : vgs) {
            for (PV p : vg.getpVList()) {
                if (p.equals(pv)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkVG(VG vg, int size) {
        if (!vgs.contains(vg)) {
            return false;
        }

        return vg.getFreeSpace() >= size;
    }

    public boolean checkLV(LV lv, VG vg) {
        if (!lvs.contains(lv)) {
            return false;
        }

        return vg.getFreeSpace() >= lv.getSize();
    }

    public void sortByVGWithPV(ArrayList<PV> list) {
        for(int i = 0; i < list.size(); i++)
        {
            PV pv = list.get(i);
            String pvName = pv.getVg().getName();
            int j;
            for(j = 0; j < list.size() && list.get(j).getVg().getName().compareTo(pvName)< 0; j++) {}
            list.add(j,pv);
        }
    }

    public void sortByVGWithLV(ArrayList<LV> list) {
        for(int i = 0; i < list.size(); i++)
        {
            LV lv = list.get(i);
            String lvName = lv.getVg().getName();
            int j;
            for(j = 0; j < list.size() && list.get(j).getVg().getName().compareTo(lvName)< 0; j++) {}
            list.add(j,lv);
        }
    }
}
