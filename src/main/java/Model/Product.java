package Model;

public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;

    /**
     * Constructor for product with parameters
     * @param name product name
     * @param price product price
     * @param stock product stock
     */
    public Product(String name, int price, int stock) {
        this.id = 0;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructor for empty product
     */
    public Product() {
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.stock = 0;
    }

    /**
     * Returns product's id
     * @return product id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets product's id
     * @param id id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns product's name
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets product's name
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns product's price
     * @return product price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the product's price
     * @param price price to be set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Returns product's stock
     * @return product stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the product's stock
     * @param stock stock to be set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /**
     * Returns the product's data as a String
     * @return product data as String
     */
    @Override
    public String toString() {
        return id +") "+name+ ", "+"price = "+price+", "+"stock = "+stock;
    }
}
