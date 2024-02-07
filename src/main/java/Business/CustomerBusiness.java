package Business;

import Dao.CustomerDao;
import Model.Customer;

import java.util.List;

public class CustomerBusiness {
    private final CustomerDao customerDao;

    public CustomerBusiness() {
        this.customerDao = new CustomerDao();
    }

    public void createCustomer(Customer customer) {
        customerDao.createCustomer(customer);
    }

    public Customer getCustomerById(int customerId) {
        return customerDao.getCustomerById(customerId);
    }

    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId) {
        customerDao.deleteCustomer(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }


}



