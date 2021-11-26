package java8.ex01;

import java8.data.Data;
import java8.data.domain.Order;
import java8.data.domain.Pizza;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 01 - Recherche
 */
public class Stream_01_Test {

    @Test
    public void test_stream_filter() throws Exception {
        List<Pizza> pizzas = new Data().getPizzas();

        // Récupérer la liste des pizzas dont le prix est >= 1300
        // Utiliser l'API Stream
        List<Pizza> result = pizzas.stream().filter(p -> p.getPrice()>=1300).collect(Collectors.toList());

        assertThat(result, hasSize(3));
        assertThat(result, everyItem(hasProperty("price", anyOf(equalTo(1300), greaterThan(1300)))));
    }

    @Test
    public void test_stream_anyMatch() throws Exception {

        List<Pizza> pizzas = new Data().getPizzas();

        // Valider si au moins une pizza à un prix >= 1300
        Boolean result1 = pizzas.stream().anyMatch(p -> p.getPrice()>=1300);

        // Valider si au moins une pizza à un prix >= 2000
        Boolean result2 = pizzas.stream().anyMatch(p -> p.getPrice()>=2000);

        assertThat(result1, is(true));
        assertThat(result2, is(false));
    }

    @Test
    public void test_stream_allMatch() throws Exception {

        List<Pizza> pizzas = new Data().getPizzas();

        // Valider que toutes les pizzas ont un prix >= 1300
        Boolean result1 = pizzas.stream().allMatch(p -> p.getPrice()>=1300);;

        // Valider que toutes les pizzas ont un prix >= 900
        Boolean result2 = pizzas.stream().anyMatch(p -> p.getPrice()>=900);;

        assertThat(result1, is(false));
        assertThat(result2, is(true));
    }


    @Test
    public void test_stream_noneMatch() throws Exception {

        List<Pizza> pizzas = new Data().getPizzas();

        // Valider qu'aucune pizza n'a un prix >= 2000
        Boolean result1 = pizzas.stream().noneMatch(p -> p.getPrice()>=2000);;

        assertThat(result1, is(true));
    }

    @Test
    public void test_stream_findFirst() throws Exception {
        List<Order> orders = new Data().getOrders();

        // Récupérer une commande faite par un client dont le prénom est "Sophie"
        Optional<Order> result = orders.stream()
        		.filter(o -> o.getCustomer().getFirstname().contentEquals("Sophie")).findFirst();

        assertThat(result.isPresent(), is(false));
    }
}
