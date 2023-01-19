package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Order;
import Model.Product;
import Presentation.TableModels.ClientTableModel;
import Presentation.TableModels.OrderTableModel;
import Presentation.TableModels.ProductTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class View extends JFrame {

    private JTabbedPane mainTabPane;
    private JPanel topPanel;
    private JTabbedPane productsTab;
    private JTabbedPane ordersTab;
    private JTabbedPane clientsTab;
    private JPanel addClientTab;
    private JPanel viewClientsTab;
    private JTable clientTable;
    private JPanel viewProductsTab;
    private JTable productTable;
    private JPanel createOrderTab;
    private JPanel viewOrdersTab;
    private JTable ordersTable;
    private JList<Client> clientList;
    private JList<Product> productList;
    private JTextField productQuantity;
    private JButton placeOrderButton;
    private JLabel pickClientL;
    private JLabel pickProductL;
    private JLabel productQuantL;
    private JButton viewAllClientsButton;
    private JButton viewAllProductsButton;
    private JButton viewAllOrdersButton;
    private JTextField addressAdd;
    private JTextField lastNameAdd;
    private JTextField firstNameAdd;
    private JTextField emailAdd;
    private JButton addClientButton;
    private JLabel firstNameL;
    private JLabel addressL;
    private JLabel lastNameL;
    private JLabel emailL;
    private JPanel editClientTab;
    private JList<Client> clientListEdit;
    private JLabel firstNameEL;
    private JLabel addressEL;
    private JTextField addressEdit;
    private JLabel lastNameEL;
    private JTextField lastNameEdit;
    private JTextField firstNameEdit;
    private JLabel emailEL;
    private JTextField emailEdit;
    private JButton editClientButton;
    private JPanel addProductTab;
    private JLabel productNameL;
    private JLabel stockL;
    private JTextField productStockAdd;
    private JLabel priceL;
    private JTextField productPriceAdd;
    private JTextField productNameAdd;
    private JButton addProductButton;
    private JPanel editProductTab;
    private JList<Product> productListEdit;
    private JButton deleteClientButton;
    private JButton deleteProductButton;
    private JLabel productNameEL;
    private JLabel productStockEL;
    private JTextField productNameEdit;
    private JButton editProductButton;
    private JTextField productPriceEdit;
    private JLabel productPriceEL;
    private JTextField productStockEdit;
    private JScrollPane clientsScrollPane;
    private JScrollPane productsScrollPane;
    private JScrollPane ordersScrollPane;

    public View() throws HeadlessException {
        super("Warehouse Management");
        this.setContentPane(topPanel);
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Updates the list of clients from the edit client tab
     * with the entries from the database
     * @param clientBLL represents the client business logic class
     */
    public void refreshEditClientTab(ClientBLL clientBLL){
        List<Client> clients = clientBLL.getClients();
        DefaultListModel<Client> listModel = new DefaultListModel<>();
        for (Client cl : clients) {
            listModel.addElement(cl);
        }
        clientListEdit.setModel(listModel);
        clientListEdit.updateUI();
    }
    /**
     * Updates the list of products from the edit product tab
     * with the entries from the database
     * @param productBLL represents the product business logic class
     */
    public void refreshEditProductTab(ProductBLL productBLL) {
        List<Product> products = productBLL.getProducts();
        DefaultListModel<Product> listModel = new DefaultListModel<>();
        for(Product p : products){
            listModel.addElement(p);
        }
        productListEdit.setModel(listModel);
        clientListEdit.updateUI();
    }
    /**
     * Updates the lists of clients and products from the orders tab
     * with the lists from each objects' tab
     */
    public void refreshOrdersTab() {
        clientList.validate();
        clientList.repaint();
        productList.validate();
        productList.repaint();
        ordersTab.validate();
        ordersTab.repaint();
    }
    private boolean checkClientFields() {

        if(firstNameAdd.getText().equals(""))
            return false;
        if(lastNameAdd.getText().equals(""))
            return false;
        if(addressAdd.getText().equals(""))
            return false;
        if(emailAdd.getText().equals(""))
            return false;

        return true;
    }

    private boolean checkProductFields() {

        if(productNameAdd.getText().equals(""))
            return false;
        if(productPriceAdd.getText().equals(""))
            return false;
        if(productStockAdd.getText().equals(""))
            return false;

        return true;
    }

    /**
     * Adds a client to the database based on the data entered
     * in the provided textfields.
     * @param clientBLL represents the client business logic class
     */
    public void addClientButtonListener(ClientBLL clientBLL){
        addClientButton.addActionListener(e -> {
            if(checkClientFields()){
                Client newClient = new Client(firstNameAdd.getText(), lastNameAdd.getText(), addressAdd.getText(), emailAdd.getText());
                clientBLL.insertClient(newClient);
                JOptionPane.showMessageDialog(this, "Client "+newClient+" added");
            }
            else
                JOptionPane.showMessageDialog(this, "Don't leave empty fiels");
        });
    }

    /**
     * Edits a client selected from the database based on the data entered
     * in the provided textfields.
     * @param clientBLL represents the client business logic class
     */
    public void editClientButtonListener(ClientBLL clientBLL){
        editClientButton.addActionListener(e -> {
            Client toChange = clientListEdit.getSelectedValue();
            if(toChange == null){
                JOptionPane.showMessageDialog(this, "Select a client to edit");
            }
            else{
                toChange.setFirst_name(firstNameEdit.getText());
                toChange.setLast_name(lastNameEdit.getText());
                toChange.setAddress(addressEdit.getText());
                toChange.setEmail(emailEdit.getText());
                clientBLL.updateClient(toChange);
                refreshEditClientTab(clientBLL);
            }
        });
    }

    /**
     * Removes the selected client from the database.
     * @param clientBLL represents the client business logic class
     */
    public void removeClientButtonListener(ClientBLL clientBLL){
        deleteClientButton.addActionListener(e -> {
            Client toDelete = clientListEdit.getSelectedValue();
            if(toDelete == null){
                JOptionPane.showMessageDialog(this, "Select a client to delete");
            }
            else{
                clientBLL.deleteClient(toDelete);
                refreshEditClientTab(clientBLL);
                JOptionPane.showMessageDialog(this, "Client "+toDelete+" deleted");
            }
        });
    }
    /**
     * Adds a product to the database based on the data entered
     * in the provided textfields.
     * @param productBLL represents the product business logic class
     */
    public void addProductButtonListener(ProductBLL productBLL){
        addProductButton.addActionListener(e -> {
            if(checkProductFields()){
                Product newProduct = new Product(productNameAdd.getText(), Integer.parseInt(productPriceAdd.getText()), Integer.parseInt(productStockAdd.getText()));
                productBLL.insertProduct(newProduct);
            }
            else
                JOptionPane.showMessageDialog(this, "Don't leave empty fields");
        });
    }
    /**
     * Edits a product selected from the database based on the data entered
     * in the provided textfields.
     * @param productBLL represents the product business logic class
     */
    public void editProductButtonListener(ProductBLL productBLL){
        editProductButton.addActionListener(e -> {
            Product toChange = productListEdit.getSelectedValue();
            if(toChange == null){
                JOptionPane.showMessageDialog(this, "Select a product to edit");
            }
            else{
                toChange.setName(productNameEdit.getText());
                toChange.setPrice(Integer.parseInt(productPriceEdit.getText()));
                toChange.setStock(Integer.parseInt(productStockEdit.getText()));

                productBLL.updateProduct(toChange);
                refreshEditProductTab(productBLL);
            }
        });
    }

    /**
     * Removes the selected product from the database.
     * @param productBLL represents the product business logic class
     */
    public void removeProductButtonListener(ProductBLL productBLL){
        deleteProductButton.addActionListener(e -> {
            Product toDelete = productListEdit.getSelectedValue();
            if(toDelete == null){
                JOptionPane.showMessageDialog(this, "Select a product to delete");
            }
            else{
                productBLL.deleteProduct(toDelete);
                refreshEditProductTab(productBLL);
                JOptionPane.showMessageDialog(this, "Product "+toDelete+" deleted");
            }
        });
    }

    /**
     * Adds an order to the database based on the selected client, product
     * and the product quantity.
     * It also decreases the product's stock.
     * @param productBLL represents the product business logic class
     * @param orderBLL represents the order business logic class
     */
    public void placeOrderButtonListener(ProductBLL productBLL, OrderBLL orderBLL){
        placeOrderButton.addActionListener(e -> {
            Product p = productList.getSelectedValue();
            Client c = clientList.getSelectedValue();
            if(p == null){
                JOptionPane.showMessageDialog(this, "Select a product");
                return;
            }
            if(c == null){
                JOptionPane.showMessageDialog(this, "Select a client");
                return;
            }
            if(productQuantity.getText().equals("") ){
                JOptionPane.showMessageDialog(this, "Enter the desired quantity");
                return;
            }
            if(productQuantity.getText().matches("[a-zA-Z]+")){
                JOptionPane.showMessageDialog(this, "Quantity field accepts only numbers");
                return;
            }
            int quantity = Integer.parseInt(this.productQuantity.getText());
            if(quantity > p.getStock()){
                JOptionPane.showMessageDialog(this, "Not enough products in stock ("+p.getStock()+")");
            }
            else{
                p.setStock(p.getStock()-quantity);
                Order newOrder = new Order(c.getId(), c.getAddress(), p.getId(), p.getName(), quantity);
                newOrder.setTotal(p);
                productBLL.decreaseStock(p);
                orderBLL.insertOrder(newOrder);
                List<Order> orders = orderBLL.getOrders();
                orderBLL.createBill(c, p, orders.get(orders.size()-1));
                JOptionPane.showMessageDialog(this, "Order placed for client "+c);
            }
        });
    }

    /**
     * Checks for the selected tab in the Clients Tab and either removes the unseen data or
     * initializes data in the selected tab.
     * @param clientBLL represents the client business logic class
     */
    public void addClientsTabChangeListener(ClientBLL clientBLL){
        clientsTab.addChangeListener(e -> {
            List<Client> clients = clientBLL.getClients();
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            if(index != 2){
                clientTable.setModel(new DefaultTableModel());
            }
            if(index == 1){
                DefaultListModel<Client> listModel = new DefaultListModel<>();
                for(Client cl : clients){
                    listModel.addElement(cl);
                }
                clientListEdit.setModel(listModel);
                clientListEdit.updateUI();
            }
        });
    }
    /**
     * Checks for the selected tab in the Products Tab and either removes the unseen data or
     * initializes data in the selected tab.
     * @param productBLL represents the product business logic class
     */
    public void addProductsTabChangeListener(ProductBLL productBLL){
        productsTab.addChangeListener(e -> {
            List<Product> products = productBLL.getProducts();
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            if(index != 2){
                productTable.setModel(new DefaultTableModel());
            }
            if(index == 1){
                DefaultListModel<Product> listModel = new DefaultListModel<>();
                for(Product p : products){
                    listModel.addElement(p);
                }
                productListEdit.setModel(listModel);
                productListEdit.updateUI();
            }
        });
    }
    /**
     * Checks for the selected tab in the Orders Tab and either removes the unseen data or
     * initializes data in the selected tab.
     * @param clientBLL represents the client business logic class
     * @param productBLL represents the product business logic class
     */
    public void addOrdersTabChangeListener(ClientBLL clientBLL, ProductBLL productBLL){
        ordersTab.addChangeListener(e -> {
            List<Client> clients = clientBLL.getClients();
            List<Product> products = productBLL.getProducts();

            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            if(index != 0){
                ordersTable.setModel(new DefaultTableModel());
            }
            if(index == 1){
                DefaultListModel<Client> clistModel = new DefaultListModel<>();
                for(Client c : clients){
                    clistModel.addElement(c);
                }
                clientList.setModel(clistModel);

                DefaultListModel<Product> plistModel = new DefaultListModel<>();
                for(Product p : products){
                    plistModel.addElement(p);
                }
                productList.setModel(plistModel);
                refreshOrdersTab();
            }
        });
    }

    /**
     * Sets the textfields of the client edit tab with
     * the selected client's data
     */
    public void clientEditListSelectionListener(){
        clientListEdit.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if(clientListEdit.getSelectedValue() != null){
                    Client c = clientListEdit.getSelectedValue();
                    firstNameEdit.setText(c.getFirst_name());
                    lastNameEdit.setText(c.getLast_name());
                    addressEdit.setText(c.getAddress());
                    emailEdit.setText(c.getEmail());
                }
            }
        });
    }

    /**
     * Sets the textfields of the product edit tab with
     * the selected product's data
     */
    public void productEditListSelectionListener(){
        productListEdit.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if(productListEdit.getSelectedValue() != null){
                    Product p = productListEdit.getSelectedValue();
                    productNameEdit.setText(p.getName());
                    productPriceEdit.setText(p.getPrice()+"");
                    productStockEdit.setText(p.getStock()+"");
                }
            }
        });
    }

    /**
     * Initializes the client table with the data from the DB
     * after clicking the view clients button.
     */
    public void addViewClientTableButtonListener(ClientBLL clientBLL){
        viewAllClientsButton.addActionListener(e -> {
            List<String> headers = clientBLL.generateHeaders();
            ClientTableModel tableModel = new ClientTableModel(clientBLL.getClients(), headers);
            clientTable.setModel(tableModel);
        });
    }

    /**
     * Initializes the product table with the data from the DB
     * after clicking the view products button.
     */
    public void addViewProductTableButtonListener(ProductBLL productBLL){
        viewAllProductsButton.addActionListener(e -> {
            List<String> headers = productBLL.generateHeaders();
            ProductTableModel tableModel = new ProductTableModel(productBLL.getProducts(), headers);
            productTable.setModel(tableModel);
        });
    }

    /**
     * Initializes the order table with the data from the DB
     * after clicking the view orders button.
     */
    public void addViewOrdersTableButtonListener(OrderBLL orderBLL){
        viewAllOrdersButton.addActionListener(e -> {
            List<String> headers = orderBLL.generateHeaders();
            OrderTableModel tableModel = new OrderTableModel(orderBLL.getOrders(), headers);
            ordersTable.setModel(tableModel);
        });
    }
}
