package service;

import entity.Customers;
import entity.Sellers;
import exception.CustomerNotFoundException;
import exception.WrongPasswordException;
import repository.CustomersRepository;

public class CustomersService {
    private final CustomersRepository customersRepository = new CustomersRepository();


    public void register(Customers customers) {
        if (customers.getName() == null || customers.getName().isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (customers.getPhone() == null || customers.getPhone().isBlank()) {
            throw new IllegalArgumentException("phone cannot be empty");
        }
        if (customers.getUsername() == null || customers.getUsername().isBlank()) {
            throw new IllegalArgumentException("username cannot be empty");
        }
        if (customers.getPassword() == null || customers.getPassword().isBlank()) {
            throw new IllegalArgumentException("password cannot be empty");
        }
        if (customersRepository.findByUsername(customers.getUsername()) != null) {
            throw new IllegalArgumentException("username already exists");
        }
        customersRepository.save(customers);
    }

    public Customers login(String username, String password) {
        Customers customers = customersRepository.findByUsername(username);
        if (customers == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        if (!customers.getPassword().equals(password)) {
            throw new WrongPasswordException("Wrong password");
        }
        return customers;
    }
}
