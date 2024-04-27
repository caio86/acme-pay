package br.com.acmepay.domain;

import br.com.acmepay.exception.CustomerWithDocumentNotExists;
import br.com.acmepay.exception.CustomerWithIdNotExists;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Customer {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
//    private List<Account> accounts;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private static final List<Customer> customerList = new ArrayList<>();

    // create
    public void create(Customer customer) {
        this.setId(customer.id);
        this.setCreated_at(LocalDateTime.now());
        this.setUpdated_at(null);
        this.setName(customer.name);
        this.setEmail(customer.email);
        this.setPhone(customer.phone);
        this.setDocument(customer.document);
        Customer.customerList.add(this);
    }

    // list
    public static List<Customer> list() {
        return Customer.customerList;
    }

    // delete
    public static void delete(Long id) throws Exception {
        int customerIndex = IntStream.range(0, Customer.customerList.size())
                .filter(num -> Customer.customerList.get(num).getId().equals(id))
                .findFirst()
                .orElse(-1);

        if (customerIndex == -1) {
            throw new Exception(
                    "Cannot delete Customer with ID: " + id
                    + " because it does not exists"
            );
        }

        Customer.customerList.remove(customerIndex);
    }
    // update
    public static void update(Long id) throws Exception {
        int customerIndex = IntStream.range(0, Customer.customerList.size())
                .filter(num -> Customer.customerList.get(num).getId().equals(id))
                .findFirst()
                .orElse(-1);

        if (customerIndex == -1) {
            throw new Exception();
        }
    }
    // getByCustomerDocument
    public static Customer getByCustomerDocument(String document) throws Exception {
        Optional<Customer> foundCustomer = Customer.customerList
                .stream()
                .filter(customer -> customer.getDocument().equals(document))
                .findFirst();

        if (foundCustomer.isEmpty()) {
            throw new CustomerWithDocumentNotExists(
                    "A Customer with this document: " + document + " does not exists"
            );
        }

        return foundCustomer.get();
    }

    public static Customer getById(Long id) throws Exception {
        Optional<Customer> foundCustomer = Customer.customerList
                .stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();

        if (foundCustomer.isEmpty()) {
            throw new CustomerWithIdNotExists(
                    "A Customer with this ID: " + id + " does not exists"
            );
        }

        return foundCustomer.get();
    }

    public static Boolean existsByCustomerDocument(String document) throws Exception {
        Optional<Customer> foundCustomer = Customer.customerList
                .stream()
                .filter(customer -> customer.getDocument().equals(document))
                .findFirst();

        if (foundCustomer.isEmpty()) {
            throw new CustomerWithDocumentNotExists(
                    "A Customer with this document: " + document + " does not exists"
            );
        }

        return true;
    }
    public static Boolean existsById(Long id) throws Exception {
        Optional<Customer> foundCustomer = Customer.customerList
                .stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();

        if (foundCustomer.isEmpty()) {
            throw new CustomerWithIdNotExists(
                    "A Customer with this ID: " + id + " does not exists"
            );
        }

        return true;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

//    public List<Account> getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(List<Account> accounts) {
//        this.accounts = accounts;
//    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "CustomerID: " + this.id;
    }
}
