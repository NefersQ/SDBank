package Accenture.SDBank.controller;
import Accenture.SDBank.dto.AccountDto;
import Accenture.SDBank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

        public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    //add
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    //get
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
            AccountDto accountDto = accountService.getAccountById(id);
            return ResponseEntity.ok(accountDto);
    }
    //getAll
    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("This account has been deleted!");
    }
    //deposit
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Integer> request){
            Integer amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }
    //deposit
    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Integer> request){
            int amount = request.get("amount");
            AccountDto accountDto = accountService.withdraw(id, amount);
            return ResponseEntity.ok(accountDto);
    }


}
