package pad4pets.security

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import pad4pets.entity.Role


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig(
        private val userDetailsService: UserDetailsService,
        private val jwtAuthFilter: JWTAuthFilter
): WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(passwordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .logout().disable()
        http.sessionManagement().sessionCreationPolicy(STATELESS)
        http.authorizeHttpRequests().antMatchers("/api/auth/**").permitAll()
        http.authorizeHttpRequests().antMatchers("/api/pets/**").permitAll()
        //http.authorizeHttpRequests().antMatchers("/api/pets/**").hasAuthority(Role.WITHOUT_PREMIUM.name)
        http.authorizeHttpRequests().anyRequest().authenticated()
        http.addFilterBefore(jwtAuthFilter, BasicAuthenticationFilter::class.java)
    }

    @Bean
    fun passwordEncoder():PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}