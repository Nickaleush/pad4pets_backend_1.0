package pad4pets.dto.requestDto

import pad4pets.entity.Role

data class RoleToUserDto(
        val username:String,
        val roleName: Role
)

