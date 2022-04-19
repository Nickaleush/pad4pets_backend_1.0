package pad4pets.dto.responseDto

data class PetDTOResponse(
        val id: Long?,
        var name: String,
        var birth: String,
        var type: String,
        var breed: String,
        var sex: String,
        var color: String,
        var sterilization: String
)
