package com.gkfcsolution.quickpollsondage.repository;

import com.gkfcsolution.quickpollsondage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByPhone(String phone);
}
