import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WaffleTest {

    /**
     * Here I will do my tests
     * @author Asakar Hussain
     * @version 03-12-2019
     */

    private Waffle test1 = new Waffle();

    @org.junit.jupiter.api.Test
    void percentageOfEach() {
        ArrayList<Double> required = new ArrayList<>();
        required.add(26.47058823529412);
        required.add(16.176470588235293);
        required.add(14.705882352941178);
        required.add(11.76470588235294);
        required.add(10.294117647058822);
        required.add(7.352941176470589);
        required.add(4.411764705882353);
        required.add(8.823529411764707);
        ArrayList<Double> actualValues = test1.percentageOfEach();
        assertEquals(required, actualValues, "The percentage of each is not correct");
    }

    @org.junit.jupiter.api.Test
    void flooring() {
        ArrayList<Integer> required = new ArrayList<>();
        required.add(26);
        required.add(16);
        required.add(14);
        required.add(11);
        required.add(10);
        required.add(7);
        required.add(4);
        required.add(8);
        ArrayList<Integer> actualValues = test1.flooring();
        assertEquals(required, actualValues, "The flooring of each is not correct");
    }

    @org.junit.jupiter.api.Test
    void addAll() {
        int required = 96;
        int actualValues = test1.addAll();
        assertEquals(required, actualValues, "The add all is not correct");
    }

    @org.junit.jupiter.api.Test
    void fractionalPart() {
        ArrayList<Double> required = new ArrayList<>();
        required.add(0.47058823529411953);
        required.add(0.1764705882352935);
        required.add(0.7058823529411775);
        required.add(0.7647058823529402);
        required.add(0.2941176470588225);
        required.add(0.35294117647058876);
        required.add(0.41176470588235325);
        required.add(0.8235294117647065);
        ArrayList<Double> actualValues = test1.fractionalPart();
        assertEquals(required, actualValues, "The decimals of each is not correct");
    }

    @org.junit.jupiter.api.Test
    void actualArray() {
        ArrayList<Double> required = new ArrayList<>();
        ArrayList<Double> actualValues = test1.fractionalPart();
        assertFalse(required == actualValues, "Array is empty");
    }

}