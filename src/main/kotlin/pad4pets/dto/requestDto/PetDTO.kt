package pad4pets.dto.requestDto

data class PetDTO(
        val id: Long?,
        var name: String,
        var birth: String,
        var type: String,
        var breed: String,
        var sex: String,
        var color: String,
        var sterilization: String
)
