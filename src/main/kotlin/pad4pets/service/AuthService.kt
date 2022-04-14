package pad4pets.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pad4pets.dto.requestDto.LoginDto
import pad4pets.dto.requestDto.SignInDto
import pad4pets.entity.Role
import pad4pets.entity.User
import pad4pets.repository.UserRepository
import pad4pets.security.JWTService

@Service
interface AuthService {
   fun logIn(loginDto: LoginDto): String
   fun signIn(signInDto: SignInDto): String
}

@Service
class AuthServiceImpl(
        private val userRepository: UserRepository,
        private val jwtProvider: JWTService,
        private val passwordEncoder: PasswordEncoder
): AuthService{

    override fun logIn(loginDto: LoginDto): String {
        val user = userRepository.findByEmail(loginDto.email)?: throw IllegalArgumentException() //обработать экспшн
        if (passwordEncoder.matches(loginDto.password, user.password)) {
            return jwtProvider.createToken(user.username, user.role)
        } else throw IllegalArgumentException() //обработать
    }

    override fun signIn(signInDto: SignInDto): String {
        val user = User(null,signInDto.email, signInDto.username, passwordEncoder.encode(signInDto.password), Role.WITHOUT_PREMIUM )
        userRepository.save(user)
        return jwtProvider.createToken(user.username, user.role)
    }
}
