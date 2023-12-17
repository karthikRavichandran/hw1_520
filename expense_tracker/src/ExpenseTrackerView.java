

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 

/**
 * This class has the bulk of the code for the ExpenseTracker.
 */
public class ExpenseTrackerView extends JFrame {

  /** This table is presented to the user with transaction information. (View) */
  private JTable transactionsTable;
  /** This button allows the user to add transactions. (Controller) */
  private JButton addTransactionBtn;
  /** This field allows the user to enter the amount of money for a given transaction. (Controller) */
  private JTextField amountField;
  /** This field allows the user to enter the name of a given transaction. (Controller) */
  private JTextField categoryField;
  /** This is the frontend component which we show to the user. */
  private DefaultTableModel model;
  /** This field allows the user to enter the amount of money for a given transaction. (Controller) */
  private List<Transaction> transactions = new ArrayList<>();
  /** Initiating an object for Input validation */
  InputValidation iv = new InputValidation();
  /** Return transaction table.
   * @return The transaction table used for the view. */
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  /** Return amount in field.
   * @return The amount the user has entered in the field, as a double. */
  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    // add try and except for double check
    
    try {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Error!!! The amount should be a number");
      throw new IllegalArgumentException("Error!!! The amount should be a number");
  }
}

    }
  

  /** 
   * Change the value in the amount field. 
   * @param amountField the amount to set into the field
   */
  public void setAmountField(JTextField amountField) {
    this.amountField = amountField;
  }

  /** Return value in category field.
   * @return The category the user has entered in the field, as a String. */
  public String getCategoryField() {
    return categoryField.getText();
  }

  /** 
   * Change the value in the category field. 
   * @param categoryField the value to set into the field
   */
  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  /** Return transaction button.
   * @return The "Add Transaction" button object. */
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  /** Return TableModel object
   * @return The frontend UI object. */
  public DefaultTableModel getTableModel() {
    return model;
  }

  /**
   * This sets the basic properties of the frontend and initializes important fields we will track
   * @param model the linked frontend object to modify
   */
  public ExpenseTrackerView(DefaultTableModel model) {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger
    this.model = model;

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    amountField = new JTextField(10);
    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  /**
   * refresh the view with updated list of transactions
   * @param transactions the list of transaction we will show to the user
   */
  public void refreshTable(List<Transaction> transactions) {
      // model.setRowCount(0);
      model.setRowCount(0);
      int rowNum = model.getRowCount();
      double totalCost=0;
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
  
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()});

      }
      Object[] totalRow = {"Total", null, null, totalCost};
      model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  /**
   * Update the view using the internal list of transactions
   */
  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = getTransactions();
  
    // Pass to view
    refreshTable(transactions);
  
  }
  /**
   * Return list of transactions in database
   * @return list of transactions we have accepted until now
   */
  public List<Transaction> getTransactions() {
    return transactions;
  }
  /**
   * Add a new transaction to the list
   * @param t the transaction we are adding
   */
  public void addTransaction(Transaction t) {
    transactions.add(t);
    getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
  }
  


  // Other view methods
}
