package pad4pets.service.entity

import pad4pets.dto.requestDto.PetDTORequest
import pad4pets.dto.responseDto.PetDTOResponse
import javax.persistence.*

@Entity
@Table(name = "pets")
data class Pet(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var name: String,
        var birth: String,
        var type: String,
        var breed: String,
        var sex: String,
        var color: String,
        var sterilization: String,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var user: User?
) {
         fun toDto(): PetDTOResponse = PetDTOResponse(id, name, birth, type, breed , sex , color , sterilization)
}
