package Model;

/**
 * Class that represents the order from the DB
 */
public class Order {
    private int id;
    private int client_id;
    private String client_address;
    private int product_id;
    private String product_name;
    private int quantity;
    private int total;

    /**
     * Constructor for Order
     * @param client_id represents the client's id
     * @param address represents the client's address
     * @param product_id represents the product's id
     * @param product_name represents the product's name
     * @param quantity represents the amount of product bought
     */
    public Order(int client_id, String address, int product_id, String product_name, int quantity) {
        this.id = 0;
        this.client_id = client_id;
        this.client_address = address;
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity = quantity;
    }

    /**
     * Constructor for an empty order
     */
    public Order() {
        this.id = 0;
        this.client_id = 0;
        this.client_address = "";
        this.product_id = 0;
        this.product_name = "";
        this.quantity = 0;
        this.total = 0;
    }

    /**
     * Returns the order's id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the order's id
     * @param id id to be set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Return's the client's id
     * @return client id
     */
    public int getClient_id() {
        return client_id;
    }
    /**
     * Sets the client's id
     * @param client_id client's id to be set
     */
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    /**
     * Returns the product's id
     * @return product id
     */
    public int getProduct_id() {
        return product_id;
    }
    /**
     * Sets the product's id
     * @param product_id product's id to be set
     */
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    /**
     * Returns the quantity of products
     * @return product quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of products
     * @param quantity quantity to be set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Returns the price of the order
     * @return order price
     */
    public int getTotal() {
        return total;
    }
    /**
     * Sets the price of the order based on the quantity
     * and the product's price
     * @param product product that is added to order
     */
    public void setTotal(Product product) {
        this.total = quantity * product.getPrice();
    }

    /**
     * Returns the client's address
     * @return client address
     */
    public String getClient_address() {
        return client_address;
    }

    /**
     * Sets the client's address
     * @param client_address client's address
     */
    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    /**
     * Returns the name of the product
     * @return product name
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * Sets the name of the product
     * @param product_name product name
     */
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    /**
     * Sets the price of the order by the given amount
     * @param total the price to be set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Returns the order's data as a String
     * @return order data as String
     */
    @Override
    public String toString() {
        return id +") "+client_id+ ", "+client_address+ ", "+product_id+", "+product_name+ ", "+quantity+", "+total;
    }
}
