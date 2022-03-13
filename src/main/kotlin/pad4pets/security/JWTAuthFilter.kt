package pad4pets.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class JWTAuthFilter(
        private val authenticationManager: AuthenticationManager
): OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val value = request.getHeader("Authorization")
        if (value != null && value.startsWith("Bearer_")) {
            val token = value.substring(7)
            try {
                val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(token,token))
                SecurityContextHolder.getContext().authentication = authentication
            } catch (exception:Exception) {SecurityContextHolder.getContext().authentication = null}
        } else SecurityContextHolder.getContext().authentication = null
        filterChain.doFilter(request,response)
    }
}