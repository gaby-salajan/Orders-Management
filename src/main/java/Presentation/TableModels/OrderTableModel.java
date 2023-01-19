package Presentation.TableModels;

import Model.Order;
import java.util.List;

public class OrderTableModel extends TableModel<Order> {

    /**
     * Constructor
     * @param modelData table data to be set
     * @param columnNames table headers to be set
     */
    public OrderTableModel(List<Order> modelData, List<String> columnNames) {
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
        Order order = getRow(rowIndex);

        return switch (columnIndex) {
            case 0 -> order.getId();
            case 1 -> order.getClient_id();
            case 2 -> order.getClient_address();
            case 3 -> order.getProduct_id();
            case 4 -> order.getProduct_name();
            case 5 -> order.getQuantity();
            case 6 -> order.getTotal();
            default -> null;
        };
    }

}
