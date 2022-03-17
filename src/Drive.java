public class Drive {
    private String name;
    private int size;

    public Drive(String n, int s) {
        name = n;
        size = s;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        return name + " [" + size + "G]";
    }
}
