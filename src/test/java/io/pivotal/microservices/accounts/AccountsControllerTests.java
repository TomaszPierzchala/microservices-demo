package io.pivotal.microservices.accounts;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class AccountsControllerTests extends AbstractAccountControllerTests {

	protected static final Account theAccount = new Account(ACCOUNT_1,
			ACCOUNT_1_NAME);
	private static List<Account> dao_accounts = new ArrayList<Account>();
	
	static {
		dao_accounts.add(theAccount);
	}

	protected static class TestAccountRepository implements AccountRepository {

		@Override
		public Account findByNumber(String accountNumber) {
			if (accountNumber.equals(ACCOUNT_1))
				return theAccount;
			else
				return null;
		}

		@Override
		public List<Account> findByOwnerContainingIgnoreCase(String partialName) {
			List<Account> accounts = new ArrayList<Account>();

			if (ACCOUNT_1_NAME.toLowerCase().indexOf(partialName.toLowerCase()) != -1)
				accounts.add(theAccount);

			return accounts;
		}

		@Override
		public int countAccounts() {
			return dao_accounts.size();
		}

		@Override
		public List<Account> findAllByOrderByIdDesc() {
			return dao_accounts;
		}

		@Override
		public Account save(Account account) {
			dao_accounts.add(account);
			return account;
		}
	}

	protected TestAccountRepository testRepo = new TestAccountRepository();

	@Before
	public void setup() {
		accountController = new AccountsController(testRepo);
	}
}
