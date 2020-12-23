package ru.rybinskov.ideas4transfer.domain.order;

public enum OrderStatus {
    NEW("NEW"),
    APPROVED_BY_MANAGER("APPROVED_BY_MANAGER"),
    APPROVED_BY_TC("APPROVED_BY_TC"),
    CANCELLED("CANCELLED");

    private final String status;

    private OrderStatus(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
