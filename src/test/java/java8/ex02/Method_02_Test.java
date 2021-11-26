package java8.ex02;

import java.util.List;

import org.junit.Test;

import java8.data.Data;
import java8.data.Person;

/**
 * Exercice 02 - Redéfinition
 */
public class Method_02_Test {

	// tag::IDao[]
	interface IDao {
		List<Person> findAll();

		// Créer une méthode String format()
		// La méthode retourne une chaîne de la forme [<nb_personnes> persons]
		// exemple de résultat : "[14 persons]", "[30 persons]"
		public default String format() {
			List<Person> people = findAll();

			int taille = findAll().size();

			return "[" + taille + " persons]";
		}
	}
	// end::IDao[]

	// tag::DaoA[]
	class DaoA implements IDao {

		List<Person> people = Data.buildPersonList(20);

		@Override
		public List<Person> findAll() {
			return people;
		}

		// Redéfinir la méthode String format()
		// La méthode retourne une chaîne de la forme DaoA[<nb_personnes> persons]
		// exemple de résultat : "DaoA[14 persons]", "DaoA[30 persons]"
		// L'implémentation réutilise la méthode format() de l'interface
		@Override
		public String format() {
			return "DaoA" + IDao.super.format();
		}
	}
	// end::DaoA[]

	@Test
	public void test_daoA_format() throws Exception {

		DaoA daoA = new DaoA();

		// Invoquer la méthode format() pour que le test soit passant
		String result = daoA.format();

		assert "DaoA[20 persons]".equals(result);
	}
}
