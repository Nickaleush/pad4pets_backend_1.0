package pad4pets.service.impl




import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pad4pets.entity.Role
import pad4pets.entity.User
import pad4pets.repository.UserRepository
import pad4pets.service.UserService

import javax.transaction.Transactional

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
class UserServiceImpl(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder
): UserService {

    override fun saveUser(user: User): User {
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    override fun changeUserRole(username: String, roleName: Role) {
       val user: User = userRepository.findByUsername(username)?: throw IllegalArgumentException() //
       user.role = roleName
    }

    override fun getUser(username: String): User {
        return userRepository.findByUsername(username)?: throw IllegalArgumentException()
    }

    override fun getUsers(): List<User> {
        return userRepository.findAll()
    }
}