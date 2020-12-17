package ru.rybinskov.ideas4transfer.dao;

import ru.rybinskov.ideas4transfer.domain.order.TransferOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockBD {
    private static Map<Long, TransferOrder> transferOrders = new HashMap<>();

    private MockBD() {
    }

    public static boolean addOrder(TransferOrder order) {
        if (order != null) {
            transferOrders.put(order.getId(), order);
            return true;
        }
        return false;
    }

    public static TransferOrder removeOrder(TransferOrder order) {
        return transferOrders.remove(order.getId());
    }

    public static TransferOrder getOrderById(Long id) {
        return transferOrders.get(id);
    }

    public static List<TransferOrder> getAll() {
        List<TransferOrder> orders = new ArrayList<>();
        for (Map.Entry<Long, TransferOrder> entry : transferOrders.entrySet()) {
            orders.add(entry.getValue());
        }
        return orders;
    }
}
