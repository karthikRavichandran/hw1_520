import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;


/**
 * InputValidation class to validate the inputs given by the user in the UI
 */
public class InputValidation{
    /**
    * This Function check if :
    * • the amount  is greater than 0 and less than 1000.
    * • the category is  a valid string that is from this list : ”food”, ”travel”, ”bills”, ”entertainment”,
    * and ”other”.
    * 
    * @param amount The amount of money exchanged in this transaction.
    * @param category The category this transaction falls under.
    * @throws RuntimeException if category doesn't falls in the given list ["food", "travel", "bills", "entertainment","other"]
    * @throws RuntimeException if the amount should be smaller than 0 and greater than 1000
    */
    public void input_validation(double amount, String category)
    {
        if (amount < 0 | amount > 1000)
        { //Checking for the amount that should stay in the range [0,1000]
            JOptionPane.showMessageDialog(null, "Error!!! The amount should be greater than 0 and less than 1000");
            throw new RuntimeException("Error!!! The amount should be greater than 0 and less than 1000");
        }

        List<String> valid_list_of_items = Arrays.asList("food", "travel", "bills", "entertainment","other");
        
        if (!(valid_list_of_items.contains(category))) // Checking if category falles in the given list ["food", "travel", "bills", "entertainment","other"]
        {
            
            JOptionPane.showMessageDialog(null, "Error!!! The category must be from \"food\", \"travel\", \"bills\", \"entertainment\",\"other\"");
            throw new RuntimeException("Error!!! The category must be from \"food\", \"travel\", \"bills\", \"entertainment\",\"other\"");
        }


    }



}
