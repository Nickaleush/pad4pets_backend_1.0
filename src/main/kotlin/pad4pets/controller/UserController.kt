package pad4pets.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pad4pets.dto.requestDto.RoleToUserDto
import pad4pets.entity.Role
import pad4pets.entity.User
import pad4pets.service.UserService

@CrossOrigin
@RestController
@RequestMapping("/api")
class UserController(
        private val userService: UserService
) {
    @GetMapping("/users")
    fun getUsers(): List<User> = userService.getUsers()

    @PostMapping("/user/save")
    fun createUser(@RequestBody user: User) = userService.saveUser(user)

    @PostMapping("/role/changeUserRole")
    fun addRoleToUser(@RequestBody form: RoleToUserDto) = userService.changeUserRole(form.username,form.roleName)
}