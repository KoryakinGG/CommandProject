package ru.rybinskov.ideas4transfer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import ru.rybinskov.ideas4transfer.domain.order.OrderStatus;

import ru.rybinskov.ideas4transfer.domain.user.Role;
import ru.rybinskov.ideas4transfer.dto.OrderViewDto;
import ru.rybinskov.ideas4transfer.dto.SimpleViewDto;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class OrderRepository implements Repository<OrderViewDto> {

    private JdbcTemplate jdbcTemplate;
    private UserRepository userRepository;

    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    public List<SimpleViewDto> getAll() {
        return null;
    }


    @Override
    public OrderViewDto findById(Long id) {
        OrderViewDto orderViewDto = jdbcTemplate.query(
                "SELECT o.id, o.sender, o.receiver, o.created, o.order_status , o.user_id, " +
                        "od.number_of_boxes, od.comment, " +
                        "sd.driver_name, sd.car_number, sd.shipment_date, sd.delivery_date " +
                        "from orders_tbl o " +
                        "left join order_details_tbl od on od.order_id = o.id " +
                        "left join shipment_details_tbl sd on sd.order_id = o.id " +
                        "where o.id = ?",
                (r, i) -> OrderViewDto.builder()
                        .orderId(r.getLong(1))
                        .sender(r.getString(2))
                        .receiver(r.getString(3))
                        .created(r.getTimestamp(4).toLocalDateTime())
                        .orderStatus(OrderStatus.valueOf(r.getString(5)))
                        .userId(r.getLong(6))
                        .numberOfBoxes(r.getLong(7))
                        .comment(r.getString(8))
                        .driverName(r.getString(9))
                        .carNumber(r.getString(10))
                        .shipmentDate(r.getTimestamp(11).toLocalDateTime())
                        .deliveryDate(r.getTimestamp(12).toLocalDateTime())
                        .build(), id).stream().findAny().orElse(null);
        System.out.println("ПОЛУЧЕН СПИСОК: " + orderViewDto);
        return orderViewDto;
    }


    @Override
    public void save(OrderViewDto entity) {
        LocalDateTime time = LocalDateTime.of(9999, 9, 9, 9, 9, 9);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into orders_tbl (sender, receiver, created, order_status, user_id) " +
                            "values (?, ?, ?, ?, ?)", new String[]{"id"});
            ps.setString(1, entity.getSender());
            ps.setString(2, entity.getReceiver());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(4, OrderStatus.NEW.getStatus());
            ps.setLong(5, entity.getUserId());
            return ps;
        }, keyHolder);
        long key = (long) keyHolder.getKey();

        jdbcTemplate.update(
                "insert into order_details_tbl (number_of_boxes, comment, order_id) " +
                        "values (?, ?, ?)",
                entity.getNumberOfBoxes(),
                entity.getComment(),
                key);

        jdbcTemplate.update(
                "insert into shipment_details_tbl (driver_name, car_number, shipment_date, delivery_date, order_id) " +
                        "values (?, ?, ?, ?, ?)",
                "n/a",
                "n/a",
                time,
                time,
                key);
    }

    @Transactional
    @Override
    public void update(OrderViewDto entity) {
        jdbcTemplate.update("update orders_tbl set receiver = ?, order_status = ? " +
                        "where id = ?",
                entity.getReceiver(),
                entity.getOrderStatus().getStatus(),
                entity.getOrderId());

        jdbcTemplate.update(
                "update order_details_tbl set number_of_boxes = ?, comment = ? " +
                        "where order_id = ?",
                entity.getNumberOfBoxes(),
                entity.getComment(),
                entity.getOrderId());

        jdbcTemplate.update(
                "update shipment_details_tbl set shipment_date = ?, delivery_date = ? " +
                        "where order_id = ?",
                entity.getShipmentDate(),
                entity.getDeliveryDate(),
                entity.getOrderId());
    }

    @Override
    public void delete(OrderViewDto entity) {

    }


    @Override
    public List<OrderViewDto> findAll() {
        return null;
    }

    public List<SimpleViewDto> findAllSimplifiedViewOrdersByUSer(String username) {
        if (userRepository.findRoleByName(username).equals(Role.ROLE_MANAGER)) {
            System.out.println("Получаем список для менеджера");
            return findAllSimplifiedOrdersForManager();
        } else if (userRepository.findRoleByName(username).equals(Role.ROLE_TC_MANAGER)) {
            System.out.println("Получаем список для менеджера транспортной компании");
            return findAllSimplifiedOrdersForTC();
        } else {
            System.out.println("Получаем список для юзера");
            List<OrderViewDto> orderViewDto = jdbcTemplate.query(
                    "SELECT o.id, o.sender, o.receiver, o.created, o.order_status , o.user_id, " +
                            "od.number_of_boxes, od.comment, " +
                            "sd.driver_name, sd.car_number, sd.shipment_date, sd.delivery_date " +
                            "from orders_tbl o " +
                            "left join order_details_tbl od on od.order_id = o.id " +
                            "left join shipment_details_tbl sd on sd.order_id = o.id " +
                            "where o.sender = ?",
                    (r, i) -> OrderViewDto.builder()
                            .orderId(r.getLong(1))
                            .sender(r.getString(2))
                            .receiver(r.getString(3))
                            .created(r.getTimestamp(4).toLocalDateTime())
                            .orderStatus(OrderStatus.valueOf(r.getString(5)))
                            .userId(r.getLong(6))
                            .numberOfBoxes(r.getLong(7))
                            .comment(r.getString(8))
                            .driverName(r.getString(9))
                            .carNumber(r.getString(10))
                            .shipmentDate(r.getTimestamp(11).toLocalDateTime())
                            .deliveryDate(r.getTimestamp(12).toLocalDateTime())
                            .build(), username);
            System.out.println("ПОЛУЧЕН СПИСОК: " + orderViewDto);
            return orderViewDto.stream().map(this::mapSimpleViewDto).collect(Collectors.toList());
        }
    }

    private List<SimpleViewDto> findAllSimplifiedOrdersForTC() {
        List<OrderViewDto> orderViewDto = jdbcTemplate.query(
                "SELECT o.id, o.sender, o.receiver, o.created, o.order_status , o.user_id, " +
                        "od.number_of_boxes, od.comment, " +
                        "sd.driver_name, sd.car_number, sd.shipment_date, sd.delivery_date " +
                        "from orders_tbl o " +
                        "left join order_details_tbl od on od.order_id = o.id " +
                        "left join shipment_details_tbl sd on sd.order_id = o.id " +
                        "where o.order_status = ?",
                (r, i) -> OrderViewDto.builder()
                        .orderId(r.getLong(1))
                        .sender(r.getString(2))
                        .receiver(r.getString(3))
                        .created(r.getTimestamp(4).toLocalDateTime())
                        .orderStatus(OrderStatus.valueOf(r.getString(5)))
                        .userId(r.getLong(6))
                        .numberOfBoxes(r.getLong(7))
                        .comment(r.getString(8))
                        .driverName(r.getString(9))
                        .carNumber(r.getString(10))
                        .shipmentDate(r.getTimestamp(11).toLocalDateTime())
                        .deliveryDate(r.getTimestamp(12).toLocalDateTime())
                        .build(), OrderStatus.APPROVED_BY_MANAGER.getStatus());
        System.out.println("ПОЛУЧЕН СПИСОК: " + orderViewDto);
        return orderViewDto.stream().map(this::mapSimpleViewDto).collect(Collectors.toList());
    }

    public SimpleViewDto mapSimpleViewDto(OrderViewDto orderViewDto) {
        return SimpleViewDto.builder()
                .orderId(orderViewDto.getOrderId())
                .sender(orderViewDto.getSender())
                .receiver(orderViewDto.getReceiver())
                .shipmentDate(orderViewDto.getShipmentDate())
                .orderStatus(orderViewDto.getOrderStatus())
                .build();
    }

    public List<SimpleViewDto> findAllSimplifiedOrdersForManager() {
        List<OrderViewDto> orderViewDto = jdbcTemplate.query(
                "SELECT o.id, o.sender, o.receiver, o.created, o.order_status , o.user_id, " +
                        "od.number_of_boxes, od.comment, " +
                        "sd.driver_name, sd.car_number, sd.shipment_date, sd.delivery_date " +
                        "from orders_tbl o " +
                        "left join order_details_tbl od on od.order_id = o.id " +
                        "left join shipment_details_tbl sd on sd.order_id = o.id",
                (r, i) -> OrderViewDto.builder()
                        .orderId(r.getLong(1))
                        .sender(r.getString(2))
                        .receiver(r.getString(3))
                        .created(r.getTimestamp(4).toLocalDateTime())
                        .orderStatus(OrderStatus.valueOf(r.getString(5)))
                        .userId(r.getLong(6))
                        .numberOfBoxes(r.getLong(7))
                        .comment(r.getString(8))
                        .driverName(r.getString(9))
                        .carNumber(r.getString(10))
                        .shipmentDate(r.getTimestamp(11).toLocalDateTime())
                        .deliveryDate(r.getTimestamp(12).toLocalDateTime())
                        .build());
        System.out.println("ПОЛУЧЕН СПИСОК: " + orderViewDto);
        return orderViewDto.stream().map(this::mapSimpleViewDto).collect(Collectors.toList());
    }
}
