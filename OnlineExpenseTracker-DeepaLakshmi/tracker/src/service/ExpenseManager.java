package service;

import model.Expense;
import model.Budget;

import java.time.LocalDate;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private Map<String, Budget> budgets = new HashMap<>();
    private int nextId = 1;

    public void addExpense(LocalDate date, double amount, String category, String desc) {
        expenses.add(new Expense(nextId++, date, amount, category, desc));
    }

    public void editExpense(int id, LocalDate date, double amount, String category, String desc) {
        for (Expense e : expenses) {
            if (e.getId() == id) {
                e.setDate(date);
                e.setAmount(amount);
                e.setCategory(category);
                e.setDescription(desc);
                break;
            }
        }
    }

    public void deleteExpense(int id) {
        expenses.removeIf(e -> e.getId() == id);
    }

    public List<Expense> viewAllExpenses() {
        return new ArrayList<>(expenses);
    }

    public double calculateTotal() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public void setBudget(String category, double limit) {
        budgets.put(category, new Budget(category, limit));
    }

    public Budget getBudget(String category) {
        return budgets.get(category);
    }

    public double getCategoryTotal(String category) {
        return expenses.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(category))
                .mapToDouble(Expense::getAmount).sum();
    }
}
