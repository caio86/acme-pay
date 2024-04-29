package br.com.acmepay.application.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import br.com.acmepay.application.domain.exception.CustomerWithDocumentNotExists;
import br.com.acmepay.application.domain.exception.CustomerWithIdNotExists;

public class CustomerDomain {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    // private List<Account> accounts;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private static final List<CustomerDomain> CUSTOMER_DOMAIN_LIST = new ArrayList<>();

    // create
    public void create(CustomerDomain customerDomain) {
        this.setId(customerDomain.id);
        this.setCreated_at(LocalDateTime.now());
        this.setUpdated_at(null);
        this.setName(customerDomain.name);
        this.setEmail(customerDomain.email);
        this.setPhone(customerDomain.phone);
        this.setDocument(customerDomain.document);
        CustomerDomain.CUSTOMER_DOMAIN_LIST.add(this);
    }

    // list
    public static List<CustomerDomain> list() {
        return CustomerDomain.CUSTOMER_DOMAIN_LIST;
    }

    // delete
    public static void delete(Long id) throws Exception {
        int customerIndex = IntStream.range(0, CustomerDomain.CUSTOMER_DOMAIN_LIST.size())
                .filter(num -> CustomerDomain.CUSTOMER_DOMAIN_LIST.get(num).getId().equals(id))
                .findFirst()
                .orElse(-1);

        if (customerIndex == -1) {
            throw new Exception(
                    "Cannot delete Customer with ID: " + id
                            + " because it does not exists");
        }

        CustomerDomain.CUSTOMER_DOMAIN_LIST.remove(customerIndex);
    }

    // update
    public static void update(Long id) throws Exception {
        int customerIndex = IntStream.range(0, CustomerDomain.CUSTOMER_DOMAIN_LIST.size())
                .filter(num -> CustomerDomain.CUSTOMER_DOMAIN_LIST.get(num).getId().equals(id))
                .findFirst()
                .orElse(-1);

        if (customerIndex == -1) {
            throw new Exception();
        }
    }

    // getByCustomerDocument
    public static CustomerDomain getByCustomerDocument(String document) throws Exception {
        Optional<CustomerDomain> foundCustomer = CustomerDomain.CUSTOMER_DOMAIN_LIST
                .stream()
                .filter(customerDomain -> customerDomain.getDocument().equals(document))
                .findFirst();

        if (foundCustomer.isEmpty()) {
            throw new CustomerWithDocumentNotExists(
                    "A Customer with this document: " + document + " does not exists");
        }

        return foundCustomer.get();
    }

    public static CustomerDomain getById(Long id) throws Exception {
        Optional<CustomerDomain> foundCustomer = CustomerDomain.CUSTOMER_DOMAIN_LIST
                .stream()
                .filter(customerDomain -> customerDomain.getId().equals(id))
                .findFirst();

        if (foundCustomer.isEmpty()) {
            throw new CustomerWithIdNotExists(
                    "A Customer with this ID: " + id + " does not exists");
        }

        return foundCustomer.get();
    }

    public static Boolean existsByCustomerDocument(String document) throws Exception {
        Optional<CustomerDomain> foundCustomer = CustomerDomain.CUSTOMER_DOMAIN_LIST
                .stream()
                .filter(customerDomain -> customerDomain.getDocument().equals(document))
                .findFirst();

        if (foundCustomer.isEmpty()) {
            throw new CustomerWithDocumentNotExists(
                    "A Customer with this document: " + document + " does not exists");
        }

        return true;
    }

    public static Boolean existsById(Long id) throws Exception {
        Optional<CustomerDomain> foundCustomer = CustomerDomain.CUSTOMER_DOMAIN_LIST
                .stream()
                .filter(customerDomain -> customerDomain.getId().equals(id))
                .findFirst();

        if (foundCustomer.isEmpty()) {
            throw new CustomerWithIdNotExists(
                    "A Customer with this ID: " + id + " does not exists");
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

    // public List<Account> getAccounts() {
    // return accounts;
    // }
    //
    // public void setAccounts(List<Account> accounts) {
    // this.accounts = accounts;
    // }

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
