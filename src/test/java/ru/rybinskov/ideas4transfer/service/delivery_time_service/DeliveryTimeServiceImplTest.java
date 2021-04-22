package ru.rybinskov.ideas4transfer.service.delivery_time_service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rybinskov.ideas4transfer.domain.DeliveryTime;
import ru.rybinskov.ideas4transfer.dto.DeliveryTimeDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.repository.DeliveryTimeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryTimeServiceImplTest {

    @Autowired
    DeliveryTimeServiceImpl deliveryTimeService;

    @MockBean
    DeliveryTimeRepository deliveryTimeRepository;

    List<DeliveryTimeDto> deliveryTimeDtoList;
    List<DeliveryTime> deliveryTimeList;
    DeliveryTimeDto deliveryTimeDto;
    DeliveryTime deliveryTime;

    @BeforeEach
    void setUp() {
        deliveryTimeDtoList = new ArrayList<>();
        deliveryTimeDtoList.add(new DeliveryTimeDto(1L, "первая половина дня"));
        deliveryTimeDtoList.add(new DeliveryTimeDto(2L, "вторая половина дня"));
        deliveryTimeDtoList.add(new DeliveryTimeDto(3L, "в течении дня"));

        deliveryTimeList = new ArrayList<>();
        deliveryTimeList.add(new DeliveryTime(1L, "первая половина"));
        deliveryTimeList.add(new DeliveryTime(2L, "вторая половина дня"));
        deliveryTimeList.add(new DeliveryTime(3L, "в течении дня"));

        deliveryTimeDto = new DeliveryTimeDto(1L, "первая половина дня");
        deliveryTime = new DeliveryTime(1L, "вторая половина дня");
    }

    @AfterEach
    void tearDown() {
        deliveryTimeDtoList.clear();
        deliveryTimeList.clear();
    }

    @Test
    void findAllMethod_whenDeliveryTimeServiceGetAll_thenOk() {
        Mockito.doReturn(deliveryTimeList)
                .when(deliveryTimeRepository)
                .findAll();

        List<DeliveryTimeDto> deliveryTimeDtoList1 = deliveryTimeService.findAll();
        assertNotNull(deliveryTimeDtoList1);
        Mockito.verify(deliveryTimeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByIdMethod_whenDeliveryTimeServiceGetId_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(deliveryTime))
                .when(deliveryTimeRepository)
                .findById(Mockito.any());

        DeliveryTimeDto deliveryTimeDto1 = deliveryTimeService.findById(1L);
        assertNotNull(deliveryTimeDto1);
        Mockito.verify(deliveryTimeRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void SaveDeliveryTime_whenDeliveryTimeServiSave_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(deliveryTime))
                .when(deliveryTimeRepository)
                .findById(Mockito.any());

        Mockito.doReturn(deliveryTime)
                .when(deliveryTimeRepository)
                .save(Mockito.any());

        DeliveryTimeDto report = deliveryTimeService.save(deliveryTimeDto);
        assertNotNull(report);
    }

    @Test
    void deleteByIdMethod_whenDeliveryTimeServiceDelete_thenOk() {
        Mockito.doNothing().when(deliveryTimeRepository).deleteById(1L);
        deliveryTimeService.delete(1L);
        Mockito.verify(deliveryTimeRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void saveAllMethod_whenDeliveryTimeServiceSaveAll_thenOk() {
        Mockito.doReturn(deliveryTimeDtoList)
                .when(deliveryTimeRepository)
                .saveAll(Mockito.any());
        deliveryTimeService.saveAll(deliveryTimeDtoList);
        Mockito.verify(deliveryTimeRepository, Mockito.times(1)).saveAll(ArgumentMatchers.any());
    }

    @Test
    void givenResourceNotFoundException_whenDeliveryTimeIdIsNotFound() {
        Mockito.doReturn(Optional.of(deliveryTime))
                .when(deliveryTimeRepository)
                .findById(1L);
        deliveryTimeDto.setId(3L);
        assertThrows(ResourceNotFoundException.class, () -> {
            deliveryTimeService.save(deliveryTimeDto);
        });
    }
}