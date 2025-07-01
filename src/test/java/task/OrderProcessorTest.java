package task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderProcessorTest {

    @Test
    @DisplayName("Top 2 customers – simple case")
    void topTwoCustomers_simple() {
        var orders = List.of(
                new Order("Alice",   120.0),
                new Order("Bob",     200.0),
                new Order("Alice",    80.0),
                new Order("Charlie", 150.0),
                new Order("Bob",     50.0)
        );

        // Totals: Alice=200, Bob=250, Charlie=150
        var top2 = OrderProcessor.topCustomers(orders, 2);

        assertEquals(List.of("Bob", "Alice"), top2);
    }

    @Test
    @DisplayName("n greater than customer count – returns all in descending order")
    void topN_greaterThanCustomers_shouldReturnAllDesc() {
        var orders = List.of(
                new Order("X", 10.0),
                new Order("Y", 20.0)
        );

        var top5 = OrderProcessor.topCustomers(orders, 5);

        assertEquals(List.of("Y", "X"), top5);
    }

    @Test
    @DisplayName("Empty orders – returns empty list")
    void topCustomers_emptyOrders_returnsEmptyList() {
        var top = OrderProcessor.topCustomers(List.of(), 3);
        assertTrue(top.isEmpty());
    }

    @Test
    @DisplayName("n = 0 – returns empty list")
    void topCustomers_nZero_returnsEmptyList() {
        var orders = List.of(
                new Order("A",  50.0),
                new Order("B", 100.0)
        );
        var top0 = OrderProcessor.topCustomers(orders, 0);
        assertTrue(top0.isEmpty());
    }

    @Test
    @DisplayName("Negative n – returns empty list")
    void topCustomers_negativeN_returnsEmptyList() {
        var orders = List.of(
                new Order("A",  50.0),
                new Order("B", 100.0)
        );
        var topNeg = OrderProcessor.topCustomers(orders, -1);
        assertTrue(topNeg.isEmpty());
    }

    @Test
    @DisplayName("Tie-breaking – equal totals ordered by customer name ascending")
    void topCustomers_equalTotals_tieBreaksByName() {
        var orders = List.of(
                new Order("Beta",  50.0),
                new Order("Alpha", 50.0),
                new Order("Gamma", 30.0)
        );

        // Totals: Beta=50, Alpha=50, Gamma=30
        // Top2 should be: [Alpha, Beta] (resolve tie by name)
        var top2 = OrderProcessor.topCustomers(orders, 2);

        assertEquals(List.of("Alpha", "Beta"), top2);
    }

    @Test
    @DisplayName("n equals customer count – returns all in descending order")
    void topCustomers_nEqualsCustomerCount_returnsAllDesc() {
        var orders = List.of(
                new Order("C", 300.0),
                new Order("A", 100.0),
                new Order("B", 200.0)
        );
        // Totals: C=300, B=200, A=100
        var top3 = OrderProcessor.topCustomers(orders, 3);
        assertEquals(List.of("C", "B", "A"), top3);
    }
}
