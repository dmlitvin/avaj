package simulation;

public abstract class Aircraft {

    protected long          id;
    static private long     idCount;
    protected String        name;
    protected Coordinates   coordinates;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    private long nextId() {
        return ++idCount;
    }

}
