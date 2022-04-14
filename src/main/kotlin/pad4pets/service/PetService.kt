package pad4pets.service

import org.springframework.stereotype.Service
import pad4pets.dto.requestDto.PetDTO
import pad4pets.entity.Pet
import pad4pets.entity.User
import pad4pets.repository.PetRepository

@Service
interface PetService {
    fun add(pet: Pet, userId: Long): Pet
    fun updatePet(pet: Pet, userId: Long): Pet
    fun getPetList(userId: Long): List<PetDTO>
}

@Service
class PetServiceImpl(
        private val petRepository: PetRepository
):PetService {

    override fun add(pet: Pet,userId: Long): Pet {
        pet.user = User(id = userId)
        return petRepository.save(pet)
    }

    override fun updatePet(pet: Pet, userId: Long): Pet {
        if (pet.id != null && petRepository.existsById(pet.id)){
            pet.user = User(id= userId)
           return petRepository.save(pet)
        } else throw IllegalArgumentException("Pet is not found!") // TODO:обработать
    }

    private fun Pet.toDto(): PetDTO =
            PetDTO(
                    id = this.id,
                    name = this.name,
                    birth = this.birth,
                    type =this.type,
                    breed = this.breed ,
                    sex = this.sex ,
                    color = this.color ,
                    sterilization = this.sterilization
            )

    override fun getPetList(userId: Long) = petRepository.findPetListByUserId(userId)  .map { it.toDto() }// TODO: обработать ошибку, если email = null

}