package DataAccess;

import Model.Product;
import Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;


/**
 * This class extends AbstractDAO for the Product Class
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * Creates a MySQL query to decrease the stock of a given product.
     * @param p product to update
     * @return query as String
     */
    private String decreaseStockQuery(Product p) {
        String sb = "UPDATE Product SET stock=" +
                "'" + p.getStock() + "'" +
                " WHERE id = " +
                p.getId() + ";";
        return sb;
    }

    /**
     * Connects to the DB and updates the stock of the given product.
     * @param p product to update
     */
    public void decreaseStock(Product p) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = decreaseStockQuery(p);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, this.getClass().getName() + ":decreaseStock " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
