package com.eaglesoar.paymentapplication.repository;

import lombok.Data;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "ACCOUNT")
@Audited
@AuditTable(value = "ACCOUNT_HISTORY")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String accountNumber;
    private BigDecimal balance;
    private String currency;
    @Enumerated(value = EnumType.STRING)
    private AccountStatus status;

    public static AccountEntity of(String accountNumber, BigDecimal balance, String currency, AccountStatus status) {
        AccountEntity account = new AccountEntity();
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setCurrency(currency);
        account.setStatus(status);
        return account;
    }

}
