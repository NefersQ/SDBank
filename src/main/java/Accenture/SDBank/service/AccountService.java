package Accenture.SDBank.service;

import Accenture.SDBank.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();
    AccountDto deposit(Long id,int amount);
    void deleteAccount(Long id);
    AccountDto withdraw(Long id, int amount);
}
