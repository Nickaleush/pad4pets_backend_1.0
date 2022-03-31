package pad4pets.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class JWTAuthManager(
        private val jwtProvider: JWTService,
        private val userDetailsService: UserDetailsService
): AuthenticationManager {

    override fun authenticate(authentication: Authentication): Authentication {
        val token = authentication.credentials.toString()
        val login = jwtProvider.getLogin(token)
        val userDetails = userDetailsService.loadUserByUsername(login)
        return UsernamePasswordAuthenticationToken(userDetails.username, userDetails.password, userDetails.authorities)
    }
}