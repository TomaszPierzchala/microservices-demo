package io.pivotal.microservices.services.web;

import java.math.BigDecimal;

import javax.validation.constraints.Size;

public class AccountModel {

	private Long id;

	@Size(min=9,max=9,message="Account number should be 9 digits")
	private String number;
	@Size(min=1,message="Not empty string")
	private String owner;
	private BigDecimal balance;

	
	public AccountModel() {
		balance = BigDecimal.ZERO;
		id = -1l;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String accountNumber) {
		this.number = accountNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public BigDecimal getBalance() {
		return balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public void setBalance(BigDecimal value) {
		balance = value;
		balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	@Override
	public String toString() {
		return number + " [" + owner + "]: $" + balance;
	}

	public Account getAccount() {
		Account account = new Account();
		account.setId(-1l);
		account.setNumber(number);
		account.setOwner(owner);
		account.setBalance(balance);
		return account;
	}
}
