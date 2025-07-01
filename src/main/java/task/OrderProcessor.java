package task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderProcessor {

    public static List<String> topCustomers(List<Order> orders, int n) {
        Map<String, Double> totals = new HashMap<>();
        orders.stream()
                .forEach(o ->
                        totals.put(
                                o.getCustomer(),
                                totals.getOrDefault(o.getCustomer(), 0.0) + o.getAmount()
                        )
                );

        return totals.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .subList(0, n);
    }
}

class Order {

    private final String customer;
    private final double amount;
    public Order(String customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }
    public String getCustomer() { return customer; }
    public double getAmount() { return amount; }
}