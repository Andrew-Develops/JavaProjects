package business.service;

import business.dto.AccountDTO;
import business.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.CustomerDAO;
import persistence.entities.Account;
import persistence.entities.Customer;

@Service
public class CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    public void insertCustomerDTO(CustomerDTO customerDTO) {
        //mai intai transformam un obiect de tip CustomerDTO in obiect de tip Customer
        Customer customer = getCustomerFromCustomerDTO(customerDTO);
        //apoi setam Account pe customer
        setAccount(customerDTO, customer);
        //apoi introducem customer in baza de date
        customerDAO.insertCustomer(customer);
    }

    //setam account pe customer
    //mai intai luam account de pe customerDTO si apoi il punem pe customer
    public void setAccount(CustomerDTO customerDTO, Customer customer) {
        Account account = new Account();
        account.setUserName(customerDTO.getAccountDTO().getUserName());
        account.setPassword(customerDTO.getAccountDTO().getPassword());
        customer.setAccount(account);
    }

    //transformam un obiect de tip CustomerDTO in obiect de tip Customer
    public Customer getCustomerFromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setBirthDate(customerDTO.getBirthDate());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setEmail(customerDTO.getEmail());

        return customer;
    }

    //cautam customer dupa userName
    public CustomerDTO findCustomerByUserName(String userName) {
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = customerDAO.findCustomerByUserName(userName);

        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setBirthDate(customer.getBirthDate());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setEmail(customer.getEmail());

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserName(customer.getAccount().getUserName());
        accountDTO.setPassword(customer.getAccount().getPassword());
        accountDTO.setLoggedIn(customer.getAccount().isLoggedIn());
        customerDTO.setAccountDTO(accountDTO);

        return customerDTO;
    }

    //cautam customer dupa userName si password
    public CustomerDTO findCustomerAccount(String userName, String password) {
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = customerDAO.findCustomerByUserNameAndPassword(userName, password);

        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setBirthDate(customer.getBirthDate());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setEmail(customer.getEmail());

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserName(customer.getAccount().getUserName());
        accountDTO.setPassword(customer.getAccount().getPassword());
        accountDTO.setLoggedIn(customer.getAccount().isLoggedIn());
        customerDTO.setAccountDTO(accountDTO);

        return customerDTO;

    }

    //cautam customer dupa email
    public CustomerDTO findCustomerByEmail(String email) {
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = customerDAO.findCustomerByEmail(email);

        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setBirthDate(customer.getBirthDate());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setEmail(customer.getEmail());

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserName(customer.getAccount().getUserName());
        accountDTO.setPassword(customer.getAccount().getPassword());
        accountDTO.setLoggedIn(customer.getAccount().isLoggedIn());
        customerDTO.setAccountDTO(accountDTO);

        return customerDTO;
    }

}
