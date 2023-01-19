package Presentation.TableModels;

import Model.Product;
import java.util.List;

public class ProductTableModel extends TableModel<Product> {

    /**
     * Constructor
     * @param modelData table data to be set
     * @param columnNames table headers to be set
     */
    public ProductTableModel(List<Product> modelData, List<String> columnNames) {
        super(modelData, columnNames);
    }

    /**
     * Return the value of a cell based on the given indexes
     * @param rowIndex row index
     * @param columnIndex column index
     * @return data from the selected field
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = getRow(rowIndex);

        return switch (columnIndex) {
            case 0 -> product.getId();
            case 1 -> product.getName();
            case 2 -> product.getPrice();
            case 3 -> product.getStock();
            default -> null;
        };
    }
}