package fun7App.repository;

import fun7App.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    public Users findByUserId(String userId);
}
