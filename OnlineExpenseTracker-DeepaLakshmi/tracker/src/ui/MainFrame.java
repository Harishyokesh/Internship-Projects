package ui;

import model.Expense;
import service.ExpenseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class MainFrame extends JFrame {
    private ExpenseManager manager = new ExpenseManager();
    private JTable table;
    private DefaultTableModel tableModel;

    public MainFrame() {
        setTitle("Online Expense Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"ID", "Date", "Amount", "Category", "Description"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        String[] buttons = {"Add", "Edit", "Delete", "Set Budget", "View Total"};
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.addActionListener(e -> handleAction(text));
            panel.add(btn);
        }

        add(panel, BorderLayout.SOUTH);
        refreshTable();
    }

    private void handleAction(String action) {
        try {
            switch (action) {
                case "Add" -> {
                    LocalDate date = LocalDate.parse(JOptionPane.showInputDialog("Enter date (YYYY-MM-DD):"));
                    double amt = Double.parseDouble(JOptionPane.showInputDialog("Enter amount:"));
                    String cat = JOptionPane.showInputDialog("Enter category:");
                    String desc = JOptionPane.showInputDialog("Enter description:");
                    manager.addExpense(date, amt, cat, desc);
                    refreshTable();
                }
                case "Edit" -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to edit:"));
                    LocalDate date = LocalDate.parse(JOptionPane.showInputDialog("Enter new date (YYYY-MM-DD):"));
                    double amt = Double.parseDouble(JOptionPane.showInputDialog("Enter new amount:"));
                    String cat = JOptionPane.showInputDialog("Enter new category:");
                    String desc = JOptionPane.showInputDialog("Enter new description:");
                    manager.editExpense(id, date, amt, cat, desc);
                    refreshTable();
                }
                case "Delete" -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to delete:"));
                    manager.deleteExpense(id);
                    refreshTable();
                }
                case "Set Budget" -> {
                    String cat = JOptionPane.showInputDialog("Enter category:");
                    double limit = Double.parseDouble(JOptionPane.showInputDialog("Enter budget limit:"));
                    manager.setBudget(cat, limit);
                    double used = manager.getCategoryTotal(cat);
                    JOptionPane.showMessageDialog(this, "Used: Rs." + used + " / Rs." + limit);
                }
                case "View Total" -> {
                    JOptionPane.showMessageDialog(this, "Total Expense: Rs." + manager.calculateTotal());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Expense e : manager.viewAllExpenses()) {
            tableModel.addRow(new Object[]{e.getId(), e.getDate(), e.getAmount(), e.getCategory(), e.getDescription()});
        }
    }
}

