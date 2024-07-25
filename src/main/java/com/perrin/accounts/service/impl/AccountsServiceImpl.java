package com.perrin.accounts.service.impl;

import com.perrin.accounts.constants.AccountsConstants;
import com.perrin.accounts.dto.AccountsDto;
import com.perrin.accounts.dto.CustomerDto;
import com.perrin.accounts.entity.Accounts;
import com.perrin.accounts.entity.Customer;
import com.perrin.accounts.exception.CustomerAlreadyExistsException;
import com.perrin.accounts.exception.ResourceNotFoundException;
import com.perrin.accounts.mapper.AccountsMapper;
import com.perrin.accounts.mapper.CustomerMapper;
import com.perrin.accounts.repository.AccountsRepository;
import com.perrin.accounts.repository.CustomerRepository;
import com.perrin.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    Random random = new Random();

    /**
     * @param customerDto CustomerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        if (customerRepository.existsByMobileNumber(customer.getMobileNumber())) {
            throw new CustomerAlreadyExistsException(
                    "Customer already exists with given phone " + customer.getMobileNumber()
            );
        }

        customerRepository.save(customer);
        accountsRepository.save(createNewAccountEntity(customer));
    }

    /**
     * @param phone Mobile number
     * @return AccountsDto object
     */
    @Override
    public CustomerDto fetchAccountByNumber(String phone) {
        Customer customer = customerRepository.findByMobileNumber(phone)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "phone Number", phone));
        var accountDto = AccountsMapper.mapToAccountsDto(accountsRepository
                        .findByCustomerId(customer.getCustomerId()).
                        orElseThrow(() -> new ResourceNotFoundException(
                                "Account",
                                "customerId",
                                String.valueOf(customer.getCustomerId()))
                        ),
                new AccountsDto()
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(accountDto);
        return customerDto;
    }

    /**
     * @param customerDto CustomerDto object
     * @return boolean indicating whether account is updated
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccountEntity(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        Long randomAccNumber = 1000000000L + random.nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
