package Presentation;

import BusinessLogic.*;

public class Controller {
    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;
    private final OrderBLL orderBLL;

    /**
     * Adds all the listeners that connect the view to the DB
     * with the business logic classes.
     * @param gui represents the whole graphical user interface.
     */
    private void initGUI(View gui){
        gui.addClientsTabChangeListener(clientBLL);
        gui.addProductsTabChangeListener(productBLL);
        gui.addOrdersTabChangeListener(clientBLL, productBLL);

        gui.addViewClientTableButtonListener(clientBLL);
        gui.addViewProductTableButtonListener(productBLL);
        gui.addViewOrdersTableButtonListener(orderBLL);

        gui.addClientButtonListener(clientBLL);
        gui.editClientButtonListener(clientBLL);
        gui.removeClientButtonListener(clientBLL);

        gui.addProductButtonListener(productBLL);
        gui.editProductButtonListener(productBLL);
        gui.removeProductButtonListener(productBLL);

        gui.placeOrderButtonListener(productBLL, orderBLL);
        gui.clientEditListSelectionListener();
        gui.productEditListSelectionListener();
    }

    /**
     * Initializes the gui and the business logic.
     */
    Controller(){
        View gui = new View();
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();
        initGUI(gui);
    }

    public static void main(String[] args){
        new Controller();
    }
}
