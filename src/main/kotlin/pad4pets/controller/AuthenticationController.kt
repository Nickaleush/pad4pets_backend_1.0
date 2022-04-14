package pad4pets.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pad4pets.dto.requestDto.LoginDto
import pad4pets.dto.requestDto.SignInDto
import pad4pets.service.AuthService

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
        private val authService: AuthService
) {
    @PostMapping("/login")
    fun logIn(@RequestBody loginForm: LoginDto): String{
        return authService.logIn(loginForm)
    }

    @PostMapping("/signUp")
    fun signIn(@RequestBody signInForm: SignInDto): String {
        return authService.signIn(signInForm)
    }

}