package ru.rybinskov.ideas4transfer.service.order_service;

import ru.rybinskov.ideas4transfer.domain.order.TransferOrder;
import ru.rybinskov.ideas4transfer.dto.TransferNote;

public interface OrderNodeService {

    TransferNote getTransferNote(TransferOrder order);
}
