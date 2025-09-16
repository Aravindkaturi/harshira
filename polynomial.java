

import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.json.JSONArray;

public class polynomial {
    public static double findConstant(double[] x, double[] y, int k) {
        double constant = 0.0;
        
        for (int i = 0; i < k; i++) {
            double term = y[i];
            for (int j = 0; j < k; j++) {
                if (j != i) {
                    term *= (0 - x[j]) / (x[i] - x[j]);
                }
            }
            constant += term;
        }
        
        return constant;
    }

    public static int convertToDecimal(String value, int base) {
        return Integer.parseInt(value, base);
    }

    public static void main(String[] args) {
        try {
            // Read and parse the JSON file
            String content = Files.readString(Paths.get("test1.json"));
            JSONObject obj = new JSONObject(content);
            JSONObject keys = obj.getJSONObject("keys");
            int k = keys.getInt("k");

            // Extract the first k entries (1, 2, 3) as points
            double[] x = new double[k]; // Using keys 1, 2, 3 as x values
            double[] y = new double[k]; // Converted values as y values

            for (int i = 1; i <= k; i++) {
                JSONObject point = obj.getJSONObject(String.valueOf(i));
                int base = Integer.parseInt(point.getString("base"));
                String value = point.getString("value");
                x[i - 1] = i; // Assign x as the key (1, 2, 3)
                y[i - 1] = convertToDecimal(value, base); // Convert to decimal
            }

            // Compute and print the constant
            double result = findConstant(x, y, k);
            System.out.println("Constant: " + result);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}