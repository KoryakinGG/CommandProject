package ru.rybinskov.ideas4transfer.service.user_service;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import ru.rybinskov.ideas4transfer.domain.Brand;
import ru.rybinskov.ideas4transfer.domain.Role;
import ru.rybinskov.ideas4transfer.domain.User;
import ru.rybinskov.ideas4transfer.dto.BrandDto;
import ru.rybinskov.ideas4transfer.dto.RoleDto;
import ru.rybinskov.ideas4transfer.dto.ShopDto;
import ru.rybinskov.ideas4transfer.dto.UserDto;
import ru.rybinskov.ideas4transfer.exception.ResourceNotFoundException;
import ru.rybinskov.ideas4transfer.exception.WarehouseException;
import ru.rybinskov.ideas4transfer.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    Collection<Role> role;

    @MockBean
    Collection<RoleDto> roleDto;

    @MockBean
    List<Brand> brand;

    @MockBean
    List<BrandDto> brandDto;

    User user;
    UserDto userDto;
    List<User> userList;
    List<UserDto> userDtoList;

    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();
        userList.add(new User(1L,"Фродо","яочкую321","Бэггинс", "frodo@shir.mid", "89252546574",role, brand));
        userList.add(new User(2L,"Гэндальф","всехспасу123","Серый", "lightSword@middleearth.mid", "911",role, brand));
        userList.add(new User(3L,"Саурон","повелительмира","Темный", "sauron@dark.mid", "666",role, brand));

        userDtoList = new ArrayList<>();
        userList.add(new User(1L,"Арагорн","888нуженновыймеч888","Сын Араторна", "aragorn@graymaster.mid", "02",role, brand));
        userList.add(new User(2L,"Гимли","пивопивопиво","Сын Глоина", "gnom14@gnom.mid", "89654565874",role, brand));
        userList.add(new User(3L,"Леголаз","ясамыйкрасивый","Попаду в глаз", "lego@forest.mid", "8965364658",role, brand));

        user = new User(1L,"Голум","мояпрелесть","Кольценосец", "golum@shir.mid", "89252546575",role, brand);
        userDto = new UserDto(1L,"Боромир",roleDto, "Гондорский", "boromir@shir.mid", "8974545695", brandDto);
    }

    @AfterEach
    void tearDown() {
        userList.clear();
        userDtoList.clear();
    }
    //TestMethod_Condition_ExpectedResult
    @Test
    void getByUserIdMethod_whenUserServiceGetLongId_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findById(Mockito.any());

        UserDto userDto = userService.getById(1L);
        assertNotNull(userDto);
        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void getAllUsersMethod_whenUserServiceGetAll_thenOk() {
        Mockito.doReturn(userList)
                .when(userRepository)
                .findAll();

        List<UserDto> userDto = userService.findAll();
        assertEquals(userList.size(),userDto.size());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveUserMethod_whenUserServiceGetUserDto_thenOk() throws ResourceNotFoundException, WarehouseException {
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findByUsername(Mockito.any());
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findById(Mockito.any());
        Mockito.doReturn(user)
                .when(userRepository)
                .save(Mockito.any());
        UserDto userDto1 = userService.save(userDto);
        assertNotNull(userDto1);
        Mockito.verify(userRepository,Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void deleteUserMethod_whenUserServiceGetLongId_thenOk() throws ResourceNotFoundException {
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findById(1L);
        userService.delete(1L);
        Mockito.verify(userRepository, Mockito.times(1)).delete(ArgumentMatchers.any());
    }

    @Test
    void loadUserByUsername_whenUserServiceGetUserName_thenOk() {
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findByUsername(Mockito.any());
        UserDetails userDto1 = userService.loadUserByUsername(user.getUsername());
        assertNotNull(userDto1);
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(user.getUsername());
    }

    @Test
    void givenResourceNotFoundException_whenUserIdIsNotFound() {
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findById(1L);
        userDto.setId(6L);
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.save(userDto);
        });
    }
}