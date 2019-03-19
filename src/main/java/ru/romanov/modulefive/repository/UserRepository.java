package ru.romanov.modulefive.repository;

import org.springframework.data.repository.CrudRepository;
import ru.romanov.modulefive.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findOneByUserName(String userName);
}
