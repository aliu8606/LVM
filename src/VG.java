import java.util.ArrayList;
import java.util.UUID;

public class VG extends Volume{
    private ArrayList<PV> pVList;
    private ArrayList<LV> lVList;
    private int freeSpace;

    public VG(String n, PV pv) {
        super(n, pv.getSize());
        pVList = new ArrayList<PV>();
        pVList.add(pv);
        pv.setVg(this);
        freeSpace = pv.getSize();
        lVList = new ArrayList<LV>();
    }

    public VG(String n, PV pv, UUID u) {
        super(n, pv.getSize(), u);
        pVList = new ArrayList<PV>();
        pVList.add(pv);
        pv.setVg(this);
        freeSpace = pv.getSize();
        lVList = new ArrayList<LV>();
    }

    public ArrayList<PV> getpVList() {
        return pVList;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public void extend(PV pv) {
        pVList.add(pv);
        pv.setVg(this);
        freeSpace += pv.getSize();
        setSize(getSize() + pv.getSize());
    }

    public void addLV(LV lv) {
        lVList.add(lv);
        freeSpace -= lv.getSize();
    }

    public String toString() {
        String str = getName() + ": " + "total:[" + getSize() + "G] available:[" +
                Math.abs(freeSpace) + "G] [";

        for (PV pv : pVList) {
            String name = pv.getName();
            str += name + ",";
        }

        str = str.substring(0, str.length() - 1);
        str += "] [" + getUuid() + "]";
        return str;
    }
}
