// compile the file using "javac main.java" and run the file using "java main"
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Hahsmap to store the input
        Map<String, Double> store = new HashMap<>();
        store.put("event 1", 20.0); 
        store.put("event 2", 50.0); 
        store.put("event 3", 30.0);

        //Running the code 1000 times.
        int occ = 1000;
        for (int i = 0; i < occ; i++) {
            String outcome = eventoutcome(store);
            System.out.println("Simulated outcome: " + outcome);
        }
    }

    public static String eventoutcome(Map<String, Double> store) {
        //intialise a total variable to zero
        double total = 0;
        for (double p : store.values()) {
            total += p;
        }

        //if sum of all events probablitiy is 100 then throw error
        if ((int)total != 100) {

            throw new IllegalArgumentException("Sum must be 100");
        }

        //generate a random number between 1 and 100
        Random ran = new Random();
        double rnum = ran.nextInt(100);

        //if the num lies between the range of probailities return the outcome accordingly.
        double sum = 0;
        for (Map.Entry<String, Double> eachele : store.entrySet()) {
            String outcome = eachele.getKey();
            double p = eachele.getValue();
            sum += p;
            if (rnum <= sum) {
                return outcome;
            }
        }

        // This should not happen if probabilities are correctly defined
        throw new RuntimeException("Invalid probabilities");
    }
}
