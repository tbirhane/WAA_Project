package edu.mum.cs.onlinehabeshaclothing.repository;

import edu.mum.cs.onlinehabeshaclothing.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

   // @Query("select a from Account a where a.userName=email")
   // public Account findByUserName(String email);
    public Account findAccountByUsername(String username);

}
