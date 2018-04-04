package io.pivotal.microservices.services.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import io.pivotal.microservices.services.web.WebAccountsService;
import io.pivotal.microservices.services.web.annotation.AccountNonExists;

public class AccountNonExistsConstraintValidator implements ConstraintValidator<AccountNonExists, String> {

	private final String NOT_FOUND_MSG = "404 Not Found";
	
	@Autowired
	private WebAccountsService accountsService;

	public AccountNonExistsConstraintValidator() {}
	
	public AccountNonExistsConstraintValidator(WebAccountsService accountsService) {
		this.accountsService = accountsService;
	}

	@Override
	public void initialize(AccountNonExists arg0) {
	}

	@Override
	public boolean isValid(String accountNumber, ConstraintValidatorContext ctx) {
		boolean isValid = false;
		try {
			accountsService.findByNumber(accountNumber);
		} catch (Exception e) {
			if(e.getMessage().equals(NOT_FOUND_MSG)) {
				isValid = true;
			}
		}
		return isValid;
	}

}
