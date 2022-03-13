package pad4pets.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import pad4pets.entity.User
import pad4pets.repository.UserRepository

@Service
class UserDetailsServiceImpl(
        private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findByUsername(username)?: throw IllegalArgumentException()
        return org.springframework.security.core.userdetails.User(user.username, user.password,
                mutableListOf(SimpleGrantedAuthority(user.role.name)))
    }
}