package com.perrin.accounts.service;

import com.perrin.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto CustomerDto object
     * */
    void createAccount(CustomerDto customerDto);

    /**
     * @param phone Mobile number
     * @return AccountsDto object
     * */
    CustomerDto fetchAccountByNumber(String phone);

    /**
     * @param customerDto CustomerDto object
     * @return boolean indicating whether account is updated
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);
}
