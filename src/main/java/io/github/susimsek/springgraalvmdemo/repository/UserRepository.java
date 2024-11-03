package io.github.susimsek.springgraalvmdemo.repository;

import io.github.susimsek.springgraalvmdemo.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @EntityGraph(attributePaths = "roles")
    Optional<UserEntity> findByUsername(String username);
}
