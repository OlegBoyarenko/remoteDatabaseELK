package com.packagename.myapp.view;

import com.packagename.myapp.component.connect.RmiConnectToDB;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

import data.Command;
import data.Customer;
import data.CustomerTO;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @throws RemoteException
 * Main page with Customer grid
 */
@Route("")
@ComponentScan
public class MainView extends VerticalLayout {

    HorizontalLayout horizontalLayout;
    TextField filterField = new TextField();
    Grid<CustomerTO> grid = new Grid<>(CustomerTO.class);
    Button addCustomerButton = new Button("Add new customer");
    TextArea first_name = new TextArea("Customer first name");
    TextArea second_name = new TextArea("Customer second name");
    TextArea data = new TextArea("Customer data");
    /**
     * Remote connection to database
     */
    private final Command command;

    public MainView(RmiConnectToDB rmiConnectToDB) throws RemoteException {
        command = rmiConnectToDB.getCommand();

        horizontalLayout = new HorizontalLayout();

        filterField.setValue(command.findCustomerById(2L).getFirstName());
        grid.setItems(command.findAllCustomers());

        /**
         * Add new Customer to remote database
         */
        addCustomerButton.addClickListener(click -> {
            try {
                addNewCustomer();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        add(horizontalLayout, filterField, grid, addCustomerButton, first_name, second_name, data);
    }

    /**
     *
     * @throws RemoteException
     * @throws ExecutionException
     * @throws InterruptedException
     * Send new Customer to database
     */
    void addNewCustomer() throws RemoteException, ExecutionException, InterruptedException {
        command.execute(new Customer(first_name.getValue(), second_name.getValue(), data.getValue()));
        grid.setItems(command.findAllCustomers());
    }


}
