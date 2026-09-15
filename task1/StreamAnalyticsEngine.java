import java.util.*;

public class StreamAnalyticsEngine {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Map<String, List<Integer>> sensorData = new HashMap<>();

        // Step 1: Filter temperatures greater than 50
        for (int i = 0; i < n; i++) {

            String sensorId = sc.next();
            int temperature = sc.nextInt();

            if (temperature > 50) {

                // Step 2: Group by Sensor ID
                sensorData.putIfAbsent(sensorId, new ArrayList<>());

                sensorData.get(sensorId).add(temperature);
            }
        }

        // Store sensor ID and average
        List<SensorAverage> result = new ArrayList<>();

        // Step 3: Calculate average
        for (Map.Entry<String, List<Integer>> entry : sensorData.entrySet()) {

            String sensorId = entry.getKey();
            List<Integer> temperatures = entry.getValue();

            double sum = 0;

            for (int temp : temperatures) {
                sum += temp;
            }

            double average = sum / temperatures.size();

            result.add(new SensorAverage(sensorId, average));
        }

        // Step 4: Sort in descending order
        result.sort((a, b) -> Double.compare(b.average, a.average));

        // Step 5: Display result
        for (SensorAverage data : result) {
            System.out.println(data.sensorId + " " + data.average);
        }

        sc.close();
    }

    static class SensorAverage {

        String sensorId;
        double average;

        SensorAverage(String sensorId, double average) {
            this.sensorId = sensorId;
            this.average = average;
        }
    }
}