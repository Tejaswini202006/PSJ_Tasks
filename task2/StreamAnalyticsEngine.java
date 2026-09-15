package task2;
import java.util.*;

public class StreamAnalyticsEngine {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // SensorID -> list of temperatures
        Map<String, List<Integer>> sensorData = new HashMap<>();

        // Step 1: Filter temperatures greater than 50
        for (int i = 0; i < n; i++) {

            String sensorId = sc.next();
            int temperature = sc.nextInt();

            if (temperature > 50) {

                // Step 2: Group readings by SensorID
                sensorData.putIfAbsent(sensorId, new ArrayList<>());

                sensorData.get(sensorId).add(temperature);
            }
        }

        // Store SensorID and Average
        List<SensorAverage> result = new ArrayList<>();

        // Step 3: Calculate average temperature
        for (Map.Entry<String, List<Integer>> entry : sensorData.entrySet()) {

            String sensorId = entry.getKey();
            List<Integer> temperatures = entry.getValue();

            double sum = 0;

            for (int temperature : temperatures) {
                sum += temperature;
            }

            double average = sum / temperatures.size();

            result.add(new SensorAverage(sensorId, average));
        }

        // Step 4: Sort by average temperature in descending order
        result.sort((a, b) -> Double.compare(b.average, a.average));

        // Step 5: Display result
        for (SensorAverage data : result) {
            System.out.println(data.sensorId + " " + data.average);
        }

        sc.close();
    }

    // Class to store SensorID and average
    static class SensorAverage {

        String sensorId;
        double average;

        SensorAverage(String sensorId, double average) {
            this.sensorId = sensorId;
            this.average = average;
        }
    }
}