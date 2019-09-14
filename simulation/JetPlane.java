package simulation;

public class JetPlane extends Aircraft implements Flyable {

    static private String[] weatherArray = {"Where is the A / C ?",
                                            "It's raining. Better watch out for lightings.",
                                            "MAYDAY MAYDAY, PILOT HERE, I AM BLIND !",
                                            "OMG! Winter is coming!"};

    private WeatherTower weatherTower;

    @Override public void updateConditions() {
        int weatherId = 0;
        String  randomWeather = weatherTower.getWeather(coordinates);
        switch (randomWeather) {
            case "SUN" : coordinates = new Coordinates( coordinates.getLattidude() + 10, coordinates.getLongtitude(), coordinates.getHeight() + 2); break;
            case "RAIN" : coordinates = new Coordinates(coordinates.getLattidude() + 5, coordinates.getLongtitude(), coordinates.getHeight()); weatherId = 1; break;
            case "FOG" : coordinates = new Coordinates( coordinates.getLattidude() + 1, coordinates.getLongtitude(), coordinates.getHeight()); weatherId = 2; break;
            case "SNOW" : coordinates = new Coordinates(coordinates.getLattidude(), coordinates.getLongtitude(), coordinates.getHeight() - 7); weatherId = 3; break;
        }
        Simulation.logStream.println("JetPlane#" + this.name + "(" + this.id + "): " + weatherArray[weatherId]);

        if (this.coordinates.getHeight() == 0) {
            Simulation.logStream.println("JetPlane#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            Simulation.logStream.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }
    @Override public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Simulation.logStream.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

    JetPlane(String name, Coordinates coordinates)  {
        super(name, coordinates);
    }

}