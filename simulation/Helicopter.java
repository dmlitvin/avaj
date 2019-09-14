package simulation;

public class Helicopter extends Aircraft implements Flyable {

    static String weatherArray[] = {"This is hot.",
                                    "Ehhh, I knew I should have to fix these wipers...",
                                    "May god bless the radar.",
                                    "My rotor is going to freeze!"};

    private WeatherTower weatherTower;

    @Override public void updateConditions() {
        int weatherId = 0;
        String  randomWeather = weatherTower.getWeather(coordinates);
        switch (randomWeather) {
            case "SUN" : coordinates = new Coordinates( coordinates.getLattidude() + 10, coordinates.getLongtitude(), coordinates.getHeight() + 2); break;
            case "RAIN" : coordinates = new Coordinates(coordinates.getLattidude() + 5, coordinates.getLongtitude(), coordinates.getHeight()); weatherId = 1; break;
            case "FOG" : coordinates = new Coordinates( coordinates.getLattidude() + 1, coordinates.getLongtitude(), coordinates.getHeight()); weatherId = 2; break;
            case "SNOW" : coordinates = new Coordinates(coordinates.getLattidude(), coordinates.getLongtitude(), coordinates.getHeight() - 12); weatherId = 3; break;
        }
        Simulation.logStream.println("Helicopter#" + this.name + "(" + this.id + "): " + weatherArray[weatherId]);

        if (this.coordinates.getHeight() == 0) {
            Simulation.logStream.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            Simulation.logStream.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }
    @Override public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Simulation.logStream.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

    Helicopter(String name, Coordinates coordinates)  {
        super(name, coordinates);
    }

}
