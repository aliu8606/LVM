import java.util.ArrayList;

public class VG extends Volume{
    private ArrayList<PV> pVList;
    private ArrayList<LV> lVList;
    private int freeSpace;

    public VG(String n, PV pv) {
        super(n, pv.getSize());
        pVList = new ArrayList<PV>();
        pVList.add(pv);
        freeSpace = pv.getSize();
        lVList = new ArrayList<LV>();
    }

    public ArrayList<PV> getpVList() {
        return pVList;
    }

    public ArrayList<LV> getlVList() {
        return lVList;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public void extend(PV pv) {
        pVList.add(pv);
        pv.setVg(this);
        freeSpace += pv.getSize();
        super.setSize(getSize() + pv.getSize());
    }

    public void addLV(LV lv) {
        lVList.add(lv);
        freeSpace -= lv.getSize();
    }

    public String toString() {
        String str = getName() + ": " + "total:[" + getSize() + "G] available:[" +
                freeSpace + "G] [";
        for (PV pv : pVList) {
            String name = pv.getName();
            str += name + ",";
        }

        str = str.substring(0, str.length() - 1);
        str += "] [" + getUuid() + "]";
        return str;
    }
}
