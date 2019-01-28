package com.accountspayments.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accountspayments.model.AccountsPayment;

@Repository
public interface AccountsPaymentRepository extends CrudRepository<AccountsPayment, Long>{

}
