package com.access.service;

import com.access.entity.Customer;
import com.access.repository.CustomerRepository;
import data.Command;
import data.CustomerTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements Command {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public CustomerTO findCustomerById(Long id) {
        return update(customerRepository.findById(id).orElse(null));
    }

    @Override
    public ArrayList<CustomerTO> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        ArrayList<data.CustomerTO> customerList = new ArrayList<>();

        for (Customer customer : customers) {
            customerList.add(update(customer));
        }

        return customerList;
    }

    @Override
    public void execute(CustomerTO obj) throws RemoteException {
        customerRepository.save(new Customer(obj.getFirstName(), obj.getLastName(), obj.getData()));

    }

    public data.Customer update (Customer customer) {
        return new data.Customer(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getData());
    }



}
