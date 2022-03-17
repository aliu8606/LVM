import java.util.UUID;
public class Volume {
    private String name;
    private int size;
    private UUID uuid;

    public Volume(String n, int s) {
        name = n;
        size = s;
        uuid = generateUUID();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public UUID generateUUID() {
        UUID u = UUID.randomUUID();
        return u;
    }
}
