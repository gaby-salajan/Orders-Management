package BusinessLogic;

import DataAccess.OrderDAO;
import Model.Client;
import Model.Order;
import Model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class OrderBLL {
    private final OrderDAO orderDAO;
    /**
     * Constructor which initializes orderDAO
     */
    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    /**
     * Returns all the orders from the DB
     * @return list of Orders
     */
    public List<Order> getOrders() {
        return orderDAO.findAll();
    }
    /**
     * Inserts an order into the DB
     * @param o Order to be inserted
     */
    public void insertOrder(Order o) {
        orderDAO.insert(o);
    }
    /**
     * Generates the headers for the Orders Table based on the declared fields of the class.
     * @return list of Order class fields
     */
    public List<String> generateHeaders(){
        List<String> res = new ArrayList<>();
        for (Field field : Order.class.getDeclaredFields()) {
            res.add(field.getName());
        }
        return res;
    }

    /**
     * Creates a file as a receipt using client's data, product's data and order's data.
     * @param c client
     * @param p product
     * @param o order
     */
    public void createBill(Client c, Product p, Order o){
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/receipt_"+o.getId()+".txt");
            myWriter.write("Bill for order "+o.getId()+"\n");
            myWriter.write("Client "+c.getFirst_name()+" "+c.getLast_name()+" with id "+c.getId()+" bought\n");
            myWriter.write(o.getQuantity()+" products of type "+o.getProduct_name()+" with price "+p.getPrice()+"\n");
            myWriter.write("Total "+o.getTotal());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
