package io.github.susimsek.springgraalvmdemo.repository;

import io.github.susimsek.springgraalvmdemo.entity.UserRoleMappingEntity;
import io.github.susimsek.springgraalvmdemo.entity.UserRoleMappingId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMappingEntity, UserRoleMappingId> {

    Optional<UserRoleMappingEntity> findByUserIdAndRoleId(String userId, String roleId);
}
