package ru.vladimir.training.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vladimir.training.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
