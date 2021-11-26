package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    interface PersonToAccountMapper {
        Account map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    private List<Account> map(List<Person> personList, PersonToAccountMapper mapper) {
        List<Account> accounts = new ArrayList<>();
        personList.forEach(p -> accounts.add(mapper.map(p)));
        return accounts;
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // Transformer la liste de personnes en liste de comptes
        // Tous les objets comptes ont un solde à 100 par défaut
        PersonToAccountMapper newMapper = new PersonToAccountMapper() {
			@Override
			public Account map(Person p) {
				Account account = new Account(p, 100);
				return account;
			}
		};
		List<Account> result = map(personList, newMapper);
        assert result.size() == personList.size();
        for (Account account : result) {
            assert account.getBalance().equals(100);
            assert account.getOwner() != null;
        }
    }
    // end::test_map_person_to_account[]
}