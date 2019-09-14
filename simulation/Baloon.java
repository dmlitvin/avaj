package simulation;

import java.util.Map;
import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {

    static String weatherArray[] = {"Let's enjoy the good weather and take some pics.",
                                    "Damn you rain! You messed up my baloon.",
                                    "what's the fuck ! I am blind!!",
                                    "It's snowing. We're gonna crash."};

    private WeatherTower weatherTower;

    @Override public void updateConditions() {
        int wetherId = 0;
        String  randomWeather = weatherTower.getWeather(coordinates);
        switch (randomWeather) {
            case "SUN" : coordinates = new Coordinates( coordinates.getLattidude(), coordinates.getLongtitude() + 4, coordinates.getHeight() + 2); break;
            case "RAIN" : coordinates = new Coordinates(coordinates.getLattidude(), coordinates.getLongtitude(), coordinates.getHeight() - 5); wetherId = 1; break;
            case "FOG" : coordinates = new Coordinates( coordinates.getLattidude(), coordinates.getLongtitude(), coordinates.getHeight() - 3); wetherId = 2; break;
            case "SNOW" : coordinates = new Coordinates(coordinates.getLattidude(), coordinates.getLongtitude(), coordinates.getHeight() - 15); wetherId = 3; break;
        }
        Simulation.logStream.println("Baloon#" + this.name + "(" + this.id + "): " + weatherArray[wetherId]);

        if (this.coordinates.getHeight() == 0) {
            Simulation.logStream.println("Baloon#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            Simulation.logStream.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    @Override public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Simulation.logStream.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

    Baloon(String name, Coordinates coordinates)  {
        super(name, coordinates);
    }

}