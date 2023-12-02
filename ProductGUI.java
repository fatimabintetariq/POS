/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos;

/**
 *
 * @author fatimabintetariq
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductGUI extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JComboBox<String> categoryComboBox;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new ProductGUI().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ProductGUI() {
        setTitle("Product Catalog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        // Create table model
        String[] columnNames = {"Name", "Category", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Create form components
        nameField = new JTextField(15);
        categoryComboBox = new JComboBox<>(new String[]{"Pain Relief", "Ointments", "Other"});
        priceField = new JTextField(10);
        quantityField = new JTextField(10);

        // Create buttons
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addProduct());

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> editProduct());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteProduct());

        // Create form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        formPanel.add(categoryComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        formPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        formPanel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(editButton, gbc);

        gbc.gridx = 1;
        formPanel.add(deleteButton, gbc);

        // Add components to the frame
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(formPanel, BorderLayout.EAST);
    }

    private void addProduct() {
        String name = nameField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Object[] rowData = {name, category, price, quantity};
        tableModel.addRow(rowData);

        clearFields();
    }

    private void editProduct() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        String name = nameField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        String priceText = priceField.getText();
        String quantityText = quantityField.getText();

        if (!priceText.isEmpty() && !quantityText.isEmpty()) {
            try {
                double price = Double.parseDouble(priceText);
                int quantity = Integer.parseInt(quantityText);

                tableModel.setValueAt(name, selectedRow, 0);
                tableModel.setValueAt(category, selectedRow, 1);
                tableModel.setValueAt(price, selectedRow, 2);
                tableModel.setValueAt(quantity, selectedRow, 3);

                clearFields();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input for price or quantity.", "Edit Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter values for price and quantity.", "Edit Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a product to edit.", "Edit Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private void deleteProduct() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        categoryComboBox.setSelectedIndex(0);
        priceField.setText("");
        quantityField.setText("");
    }
}
