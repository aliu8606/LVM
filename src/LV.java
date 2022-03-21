public class LV extends Volume{
    private VG vg;

    public LV(String n, int s, VG v) {
        super(n, s);
        vg = v;
        vg.addLV(this);
    }

    public VG getVg() {
        return vg;
    }

    public String toString() {
        return getName() + ": [" + getSize() + "G] [" + vg.getName() + "] [" + getUuid() + "]";
    }
}
