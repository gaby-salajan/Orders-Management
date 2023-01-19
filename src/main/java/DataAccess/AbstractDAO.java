package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;


public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Creates a MySQL select query based on id
     * @return query as String
     */
    private String createSelectQuery() {
        return "SELECT " +
                " * " +
                " FROM " +
                "`" + type.getSimpleName() + "`" +
                " WHERE " + "id" + " =?";
    }

    /**
     * Creates a MySQL insert query based on the given object's name
     * @param obj object which values to insert
     * @return query as String
     */
    private String createInsertQuery(T obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append("`").append(type.getSimpleName()).append("`");
        sb.append(" VALUES ");
        sb.append("(");

        int count = 1;
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try{
                value = field.get(obj);
                sb.append("'").append(value).append("'");
                if(count < obj.getClass().getDeclaredFields().length)
                    sb.append(",");
                count++;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append(");");
        return sb.toString();
    }

    /**
     * Creates a MySQL select query which retrieves all rows from a table
     * @return query as String
     */
    private String createSelectAll() {
        return "SELECT " +
                " * " +
                " FROM " +
                "`" + type.getSimpleName() + "`";
    }

    /**
     * Creates a MySQL update query based on the given object'id
     * @param obj object which values are used to update the row from the table
     * @return query as String
     */
    private String createUpdateQuery(T obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append("`").append(type.getSimpleName()).append("`");
        sb.append(" SET ");
        int count = 1;
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try{
                value = field.get(obj);
                sb.append(field.getName()).append("=").append("'").append(value).append("'");

            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if(count < obj.getClass().getDeclaredFields().length)
                sb.append(",");
            count++;
        }
        sb.append(" WHERE id = ");
        for (Field field : obj.getClass().getDeclaredFields()) {
            if(field.getName().equals("id")){
                field.setAccessible(true);
                Object value;
                try{
                    value = field.get(obj);
                    sb.append(value).append(";");
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    /**
     * Creates a MySQL delete query based on the given object's id
     * @param obj object to delete
     * @return query as String
     */
    private String createDeleteQuery(T obj) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ");
        for (Field field : obj.getClass().getDeclaredFields()) {
            if(field.getName().equals("id")){
                field.setAccessible(true);
                Object value;
                try{
                    value = field.get(obj);
                    sb.append(value).append(";");
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    /**
     * Returns a list with all the selected table row as corresponding object
     * @return list found objects
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAll();
        List<T> list;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            list = (createObjects(resultSet));
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return list;
    }

    /**
     * Returns an object found based on the id
     * @param id id to searched for
     * @return object generated from found row
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creates a list of objects based on the ResultSet found
     * @param resultSet to be transformed into objects
     * @return list of objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor<T>[] ctors;
        ctors = (Constructor<T>[]) type.getDeclaredConstructors();
        Constructor<T> ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Inserts the given objects into the DB
     * @param t object to be inserted
     * @return inserted object
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insertion " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Updates the corresponding row in the DB with the given object
     * @param t object used to update row
     * @return object used to update row
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Deletes the row from the DB corresponding to the given object
     * @param t object to be deleted
     * @return deleted object
     */
    public T delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
}