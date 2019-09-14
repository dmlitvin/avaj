package simulation;

public class Coordinates {
    private int lattidude, longtitude, height;

    Coordinates(int lattidude, int longtitude, int height) {
        height = height > 0 ? height : 0;
        if (height > 100)
            height = 100;

        this.lattidude = lattidude;
        this.longtitude = longtitude;
        this.height = height;
    }

    public int getLattidude() {
        return lattidude;
    }

    public int getLongtitude() {
        return longtitude;
    }

    public int getHeight() {
        return height;
    }
}
