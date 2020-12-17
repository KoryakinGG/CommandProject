package ru.rybinskov.ideas4transfer.service.order_service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.dao.MockBD;
import ru.rybinskov.ideas4transfer.domain.order.TransferOrder;
import ru.rybinskov.ideas4transfer.dto.TransferNote;

@Service
@Primary
public class OrderServiceImpl implements OrderService, OrderNodeService {

//    OrderFactory orderFactory;

    public OrderServiceImpl() {
    }

    public TransferOrder getOrderById(Long id) {
        return MockBD.getOrderById(id);
    }

    @Override
    public TransferNote getTransferNote(TransferOrder order) {
        TransferNote transferNote = TransferNote.builder().
                sender(order.getSender()).
                receiver(order.getOrderDetails().getReceiver().getName()).
                numberOfBoxes(order.getOrderDetails().getNumberOfBoxes()).
                build();
        return transferNote;
    }


//    @Override
//    public String getOrderDescription(String type) {
//        return expressFactory.createOrder(3L, LocalDateTime.now(), null,
//                OrderType.BETWEEN_STORES, OrderStatus.NEW, "Работает!").getDescription();
//    }
}
