package ru.otus.dz31.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dz31.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);
}
