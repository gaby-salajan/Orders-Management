package BusinessLogic;

import DataAccess.ProductDAO;
import Model.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    private final ProductDAO productDAO;
    /**
     * Constructor which initializes productDAO
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * Returns all the products from the DB
     * @return list of Products
     */
    public List<Product> getProducts() {
        return productDAO.findAll();
    }
    /**
     * Inserts a product into the DB
     * @param p Product to be inserted
     */
    public void insertProduct(Product p) {
        productDAO.insert(p);
    }
    /**
     * Updates the matching row from the DB with the given product
     * @param p Product to be updated with current value
     */
    public void updateProduct(Product p) {
        productDAO.update(p);
    }
    /**
     * Deletes the given product from the DB
     * @param p Product to be deleted
     */
    public void deleteProduct(Product p) {
        productDAO.delete(p);
        System.out.println("Product "+p+" deleted");
    }

    /**
     * Updates the matching stock from the DB with the given product's stock
     * @param p updated product
     */
    public void decreaseStock(Product p) {
        productDAO.decreaseStock(p);
        System.out.println(p+" stock decreased");
    }
    /**
     * Generates the headers for the Product Table based on the declared fields of the class.
     * @return list of Product class fields
     */
    public List<String> generateHeaders(){
        List<String> res = new ArrayList<>();
        for (Field field : Product.class.getDeclaredFields()) {
            res.add(field.getName());
        }
        return res;
    }
}
