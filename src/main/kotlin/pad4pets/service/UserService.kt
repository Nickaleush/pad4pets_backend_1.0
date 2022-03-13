package pad4pets.service

import org.springframework.stereotype.Service
import pad4pets.entity.Role
import pad4pets.entity.User

@Service
interface UserService {
    fun saveUser(user: User): User
    fun changeUserRole(username: String, roleName: Role)
    fun getUser(username: String): User
    fun getUsers(): List<User>
}