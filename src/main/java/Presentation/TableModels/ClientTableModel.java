package Presentation.TableModels;

import Model.Client;
import java.util.List;

public class ClientTableModel extends TableModel<Client> {

    /**
     * Constructor
     * @param modelData table data to be set
     * @param columnNames table headers to be set
     */
    public ClientTableModel(List<Client> modelData, List<String> columnNames) {
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
        Client client = getRow(rowIndex);

        return switch (columnIndex) {
            case 0 -> client.getId();
            case 1 -> client.getFirst_name();
            case 2 -> client.getLast_name();
            case 3 -> client.getAddress();
            case 4 -> client.getEmail();
            default -> null;
        };
    }
}
