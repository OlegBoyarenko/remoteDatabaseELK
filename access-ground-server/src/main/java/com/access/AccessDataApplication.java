package com.access;

import com.access.entity.Customer;
import com.access.repository.CustomerRepository;
import com.access.service.CustomerService;
import data.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SpringBootApplication
public class AccessDataApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessDataApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessDataApplication.class);
	}

	/**
	 * Put some data to database
	 * @param customerRepository
	 * @return
	 */
	@Bean
	public CommandLineRunner setDataToDB(CustomerRepository customerRepository) {
		return (args) -> {
//			customerRepository.save(new Customer("firstName", "secondName", "Lorem ipsum dolor sit amet"));
//			customerRepository.save(new Customer("firstName2", "secondName2", "Consectetuer adipiscing elit"));
//			customerRepository.save(new Customer("firstName3", "secondName3", "Aenean commodo ligula eget dolor"));
//			customerRepository.save(new Customer("firstName4", "secondName4", "Aenean massa"));
//			customerRepository.save(new Customer("firstName5", "secondName5", "Cum sociis natoque penatibus et magnis dis parturient montes"));
//			customerRepository.save(new Customer("firstName6", "secondName6", "Donec pede justo"));

		};
	}

	/**
	 * Put customerService to RMI
	 * @param customerService
	 * @return
	 */
	@Bean
	public CommandLineRunner createRmiConnection(CustomerService customerService) {
		return (args) -> {
			Registry registry = LocateRegistry.createRegistry(2005);
			Command remoteCustomer = (Command) UnicastRemoteObject.exportObject(customerService, 2005);
			registry.bind("Command", remoteCustomer);
			registry.bind(Command.class.getName(), remoteCustomer);

		};
	}

}
