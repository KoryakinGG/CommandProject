1. Профел рефакторинг класса UserServiceImpl.
2. Создал UserDto, содержащий в себе все поля сущности
3. Добавил методы обработки UserDto в UserController: getUserDtoById(), updateUser(), deleteUser().
   Теперь реализованы все основные методы CRUD у UserController
4. Привел таблицы связей сущности User в рабочее состояние
5. Изменил отношение User к Warehouse с OneToMany на OneToOne.
