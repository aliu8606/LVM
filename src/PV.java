public class PV extends Volume{
    private Drive drive;
    private VG vg;

    public PV(String n, Drive d) {
        super(n, d.getSize());
        drive = d;
        vg = null;
    }

    public Drive getDrive() {
        return drive;
    }

    public VG getVg() {
        return vg;
    }

    public void setVg(VG vg) {
        this.vg = vg;
    }

    public String toString() {
        String str = getName() + ": [" + getSize() + "G] ";
        if (vg != null) {
            str += "[" + vg.getName() + "] ";
        }
        str += "[" + getUuid() + "]";
        return str;
    }
}
