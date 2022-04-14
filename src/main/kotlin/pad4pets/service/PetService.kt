package pad4pets.service

import org.springframework.stereotype.Service
import pad4pets.entity.Pet
import pad4pets.repository.PetRepository

@Service
interface PetService {
    fun add(pet: Pet): Pet
    fun updatePet(pet: Pet): Pet
    fun getPetList(userId: Long): List<Pet>
}

@Service
class PetServiceImpl(
        private val petRepository: PetRepository,
):PetService {

    override fun add(pet: Pet): Pet {
        return  petRepository.save(pet)
    }

    override fun updatePet(pet: Pet): Pet {
        if (pet.id != null && petRepository.existsById(pet.id)){
           return petRepository.save(pet)
        } else throw IllegalArgumentException("Pet is not found!") // TODO:обработать
    }

    override fun getPetList(userId: Long) = petRepository.findPetListById(userId)

}