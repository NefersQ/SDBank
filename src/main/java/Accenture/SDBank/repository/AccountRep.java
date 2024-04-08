package Accenture.SDBank.repository;

import Accenture.SDBank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRep extends JpaRepository<Account, Long> {

}

