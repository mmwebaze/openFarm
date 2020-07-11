package org.bashemera.openfarm.service;

import org.bashemera.openfarm.exception.AccountCreationException;
import org.bashemera.openfarm.form.AccountForm;

public interface AccountService {
	
	public int createAccount(AccountForm accountForm) throws AccountCreationException;
	public int disableAccount(String id);
	public int deleteAcount(String id);
	public AccountForm checkPassword(AccountForm accountForm);
}
