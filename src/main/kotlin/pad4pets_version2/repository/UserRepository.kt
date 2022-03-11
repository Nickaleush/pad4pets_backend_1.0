package pad4pets_version2.repository

import pad4pets_version2.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

}
