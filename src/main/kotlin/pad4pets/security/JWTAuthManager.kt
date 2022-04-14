package pad4pets.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import pad4pets.entity.User
import pad4pets.repository.UserRepository

@Configuration
class JWTAuthManager(
        private val jwtProvider: JWTService,
        private val userDetailsService: UserDetailsService,
        private val userRepository: UserRepository
): AuthenticationManager {

    override fun authenticate(authentication: Authentication): Authentication {
        val token = authentication.credentials.toString()
        val email = jwtProvider.getEmail(token)
        val user = userRepository.findByEmail(email)?: throw IllegalArgumentException() // TODO: обработать exc
        return UsernamePasswordAuthenticationToken(user.id, user.password, mutableListOf(SimpleGrantedAuthority(user.role.name)))
    }
}