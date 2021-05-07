package com.eaglesoar.payment.application.account.repository;

import com.eaglesoar.payment.domain.model.AccountStatus;
import lombok.Data;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Audited
@AuditTable(value = "ACCOUNT_HISTORY")
@Table(name = "ACCOUNT", uniqueConstraints = {@UniqueConstraint(name = "account_unique_account_number", columnNames = "accountNumber")})
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
