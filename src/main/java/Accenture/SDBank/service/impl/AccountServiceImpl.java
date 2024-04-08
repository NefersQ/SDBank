package Accenture.SDBank.service.impl;
import Accenture.SDBank.dto.AccountDto;
import Accenture.SDBank.mapper.AccountMapper;
import Accenture.SDBank.model.Account;
import Accenture.SDBank.repository.AccountRep;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements Accenture.SDBank.service.AccountService {

    private AccountRep accountRep;

    public AccountServiceImpl(AccountRep accountRep) {
        this.accountRep = accountRep;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRep.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRep.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist!"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRep.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public AccountDto deposit(Long id, int amount) {
        Account account = accountRep.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist!"));
        int total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRep.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRep.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist!"));
        accountRep.deleteById(id);
    }

    @Override
    public AccountDto withdraw(Long id, int amount) {
        Account account = accountRep.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist!"));
        if(account.getBalance() < amount){
            throw new RuntimeException("You don't have so much money!");
        }
        int total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRep.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

}
