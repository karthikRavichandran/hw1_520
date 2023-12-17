import javax.swing.table.DefaultTableModel;

/**
 * The ExpenseTrackerApp class allows users to add/remove daily transactions.
 */
public class ExpenseTrackerApp {
  /**
   * The main function which creates an ExpenseTracker for user interaction.
   * @param args Command line inputs which are not used in this program.
   */
  public static void main(String[] args) {
    
    // Create MVC components
    /**
     * The frontend component table
     */
    DefaultTableModel tableModel = new DefaultTableModel();
    tableModel.addColumn("Serial");
    tableModel.addColumn("Amount");
    tableModel.addColumn("Category");
    tableModel.addColumn("Date");
    

    /**
     * Creating the MVC class and linking it with the frontend table
     */
    ExpenseTrackerView view = new ExpenseTrackerView(tableModel);

    /**
     *  Creating an object for InputValidation
     */ 
    InputValidation iv = new InputValidation();

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      
      // Get transaction data from view
      double amount = view.getAmountField(); 
      String category = view.getCategoryField();

      iv.input_validation(amount, category);
      Transaction t = new Transaction(amount, category);
          // Call controller to add transaction
      view.addTransaction(t);
    });

  }

}