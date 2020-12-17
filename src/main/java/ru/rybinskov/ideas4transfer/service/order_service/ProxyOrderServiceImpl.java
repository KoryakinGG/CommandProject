package ru.rybinskov.ideas4transfer.service.order_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.order.TransferOrder;
import ru.rybinskov.ideas4transfer.dto.TransferNote;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProxyOrderServiceImpl implements OrderNodeService {

    private final OrderServiceImpl orderService;

    private final Map<TransferOrder, TransferNote> notes = new HashMap<>();

    public ProxyOrderServiceImpl(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Override
    public TransferNote getTransferNote(TransferOrder order) {
        if (notes.containsKey(order)) {
            System.out.println("return from cache");
            return notes.get(order);
        } else {
            System.out.println("return from service");
            TransferNote transferNote = orderService.getTransferNote(order);
            notes.put(order, transferNote);
            return transferNote;
        }
    }
}
