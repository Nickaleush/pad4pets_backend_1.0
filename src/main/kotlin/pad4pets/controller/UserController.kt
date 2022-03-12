package pad4pets.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pad4pets.entity.User
import pad4pets.repository.UserRepository

@CrossOrigin
@RestController
@RequestMapping("/users")
class UserController(
        private val userRepository: UserRepository
) {
    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id")id:Long): User =
            userRepository.getById(id)

    @PostMapping
    fun create(@RequestBody user: User): User {
       return userRepository.save(user)
    }
}