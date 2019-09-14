package simulation;

import java.util.Random;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String weather[] = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int randomNumber = new Random().nextInt();
        randomNumber = randomNumber > 0 ? randomNumber : -randomNumber;

        return weather[(randomNumber +
                coordinates.getLattidude() +
                coordinates.getHeight() +
                coordinates.getLongtitude())
                % 4];
    }

}
