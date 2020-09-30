import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Waffle class is used to create my waffle chart.
 * This is also where I compute the percentages for each one and I use the largest remainder method
 * to complete it.
 * <pre>
 * color, an array to identify the colors that I will use currently there are 30 colors only
 * OFFSET, as int to offset the squares from the edge of the window and also to offset the rectangle in the
 * waffle chart.
 * length, an int to create the size of my rectangles
 * maxValue, an int value for the maximum number of descriptions I will compute onto the chart
 * expenditures, an Expenditure array which has all the values I will be considering in the chart
 * <pre>
 * @author: Asakar Hussain
 * @version: 02/12/2019
 */


public class Waffle extends Application {

    private static Color[] colours = { Color.BLACK, Color.BLUE, Color.ORANGE, Color.DIMGREY, Color.GREEN, Color.DARKRED,
            Color.DARKBLUE, Color.CYAN, Color.ORANGE, Color.PURPLE, Color.MEDIUMAQUAMARINE, Color.CORNFLOWERBLUE,
            Color.LIGHTSEAGREEN, Color.DARKSALMON, Color.BISQUE, Color.CHARTREUSE, Color.DARKGREEN, Color.GOLDENROD,
            Color.LAVENDER, Color.KHAKI, Color.CHARTREUSE, Color.GOLD, Color.DEEPPINK, Color.OLIVE,
            Color.PLUM, Color.WHEAT, Color.TOMATO, Color.AZURE, Color.MEDIUMORCHID, Color.TEAL};

    public static final int OFFSET = 35;

    public static final int length = 30;

    private int maxValue = 8;

    int[][] colors = new int[maxValue][3];

    private Expenditure[] expenditures = new Expenditure[]{

            new Expenditure("Salaries", 11000),
            new Expenditure("Paper", 2000),
            new Expenditure("Rent", 5000),
            new Expenditure("Most popular books on java etc.", 10000),
            new Expenditure("Heating", 3000),
            new Expenditure("Coffee/Tea", 7000),
            new Expenditure("Biscuits", 8000),
            new Expenditure("Travel", 18000),
            new Expenditure("Electricity", 1000),
            new Expenditure("Pencils", 3000)

    };

    /**
     * This method percentageOfEach will calculate the percentages of each description and add
     * it to an array list called percentageOfALL
     * @return This returns the percentage of all values of an array list of type double
     */
    public ArrayList<Double> percentageOfEach (){

        Arrays.sort(expenditures, (Expenditure exp1, Expenditure exp2) -> exp2.getValue() - exp1.getValue());
        double sum = 0;
        double sumOther = 0;
        ArrayList<Double> percentageOfALL = new ArrayList<>();

        // This for loop gets the all values of expenditures and adds them to the variable sum
        for(int j = 0; j < expenditures.length; j++) {
            sum += expenditures[j].getValue();
        }

        // This for loop will add the percentages to the array list percentageOfAll
        for(int i = 0; i < (maxValue - 1); i++){
            percentageOfALL.add((expenditures[i].getValue() / sum) * 100);
        }

        for(int i = maxValue-1; i < expenditures.length; i++){
            sumOther += expenditures[i].getValue();
        }

        percentageOfALL.add(sumOther/sum*100);

        return percentageOfALL;

    }

    /**
     * This method flooring will floor the values of my array list percentageOfALL to start my
     * largest remainder method and add these floored values are added to the array list floored percentages
     * @return flooredPercentages of type array list
     */
    public ArrayList<Integer> flooring(){

        ArrayList<Integer> flooredPercentages = new ArrayList<>();

        for(int i = 0; i < percentageOfEach().size(); i++ ){
            flooredPercentages.add((int) Math.floor(percentageOfEach().get(i)));
        }

        return flooredPercentages;

    }

    /**
     * This method addAll computes the total of all the floored percentages
     * @return total as int
     */
    public int addAll(){

        int total = 0;

        for (int i = 0; i < flooring().size(); i++){
            total += flooring().get(i);
        }

        return total;
    }

    /**
     * This method fractionalPart computes the decimal value of percentages from percentageOfALL
     * and adds it to the array list called fractionalValue
     * @return fractionalValue as a array list
     */
    public ArrayList<Double> fractionalPart () {

        ArrayList<Double> fractionalValue = new ArrayList<>();

        for ( int i = 0; i < percentageOfEach().size(); i++ ){
            fractionalValue.add((percentageOfEach().get(i) - flooring().get(i)));
        }

        return fractionalValue;
    }

    /**
     * The following will get the index value of fractional, runs through and gets
     * the top 4 in fractional and each time at that index value it will add one to the actual variable.
     * The actual variable is a array list of type integer and stores my final values which I will use
     * in my waffle chart.
     * @return actual as array list of type integer
     */
    public ArrayList<Integer> actualArray(){

        double max = 0;
        ArrayList<Integer> actual = flooring();

        for(int i = 0; i < (100 - addAll()); i ++){
            for (int j = i; j < fractionalPart().size(); j ++){
                if (fractionalPart().get(j) > max){
                    max = fractionalPart().get(j);
                    actual.set(j, (actual.get(j) +1)); // adding + 1 where needed in the actual array
                }
            }
        }

        return actual;

    }

    /**
     * In the following I have create the variables:
     * grid, 2d Array of type int used to create the waffle chart rectangles
     * root, a Group
     * currentColour, a int for specifying the color I am on
     * counter, a int it is the variable I increment
     * Here I will create my waffle chart, set the colors of the recatngles
     * Here I also create the legends
     * @param stage The window to be displayed.
     * @throws Exception
     */

    @Override
    public void start(Stage stage) throws Exception {
        int[][] grid = new int[10][10];
        Group root = new Group();
        int currentColour = 0;
        int counter = 0;

       // The following for loop I will generate the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (counter == actualArray().get(currentColour)) {
                    currentColour++;
                    counter = 0;
                }

                if(currentColour > colours.length){
                    throw new IndexOutOfBoundsException("Add a color of your choice to the colours array");
                }

                Rectangle rectangle = new Rectangle(OFFSET + j * OFFSET, OFFSET + i * OFFSET, length, length);
                rectangle.setFill(colours[currentColour]);
                counter ++;
                root.getChildren().add(rectangle);
            }
        }

        // The following for loop I will create my legends
        for ( int i = 0; i < actualArray().size(); i++ ){

            Rectangle legends = new Rectangle(410, 50 + i*OFFSET, length, length);
            legends.setFill(colours[i]);
            root.getChildren().add(legends);
            Text legendDescription = new Text(440,  65 + i*OFFSET, expenditures[i].getDescription());

            if(i == (actualArray().size()-1)){
                legendDescription = new Text(440,  65 + i*OFFSET, "Other");
            }

            root.getChildren().add(legendDescription);
        }

        Scene scene = new Scene(root, 700, 600);
        stage.setTitle("Waffle Chart");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Waffle test = new Waffle();
        System.out.println(test.actualArray());
        System.out.println(test.fractionalPart());
        launch(args);
    }
}