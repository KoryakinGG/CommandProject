package ru.rybinskov.ideas4transfer.service.order_service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.rybinskov.ideas4transfer.domain.order.TransferOrder;
import ru.rybinskov.ideas4transfer.domain.user.Role;
import ru.rybinskov.ideas4transfer.dto.OrderViewDto;
import ru.rybinskov.ideas4transfer.dto.SimpleViewDto;
import ru.rybinskov.ideas4transfer.repository.OrderRepository;
import ru.rybinskov.ideas4transfer.repository.UserRepository;

import java.util.List;

@Service
@Primary
public class OrderServiceImpl implements OrderService, OrderNodeService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Получает все заказы в упрощённом, сжатом виде для отображения в таблице заказов
     */
    public List<SimpleViewDto> getAllSimplifiedViewOrdersByUser(String username) {
            return orderRepository.findAllSimplifiedViewOrdersByUSer(username);
    }

    @Override
    public List<OrderViewDto> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public void save(OrderViewDto order) {
        orderRepository.save(order);
    }

    @Override
    public void update(OrderViewDto order) {
        orderRepository.update(order);
    }

    @Override
    public OrderViewDto getOrderById(Long id) {
        return orderRepository.findById(id);
    }

//    @Override
//    public TransferNote getTransferNote(TransferOrder order) {
//        TransferNote transferNote = TransferNote.builder().
//                sender(order.getSender()).
//                receiver(order.getOrderDetails().getReceiver()).
//                numberOfBoxes(order.getOrderDetails().getNumberOfBoxes()).
//                build();
//        return transferNote;
//    }
}
