package pad4pets.service

import org.springframework.stereotype.Service
import pad4pets.entity.Pet
import pad4pets.entity.User
import pad4pets.repository.PetRepository

@Service
interface PetService {
    fun add(pet: Pet, email: String): Pet
    fun updatePet(pet: Pet, email: String): Pet
    fun getPetList(email: String): List<Pet>
}

@Service
class PetServiceImpl(
        private val petRepository: PetRepository,
):PetService {

    override fun add(pet: Pet,email: String): Pet {
        pet.user = User(email= email)
        return  petRepository.save(pet)
    }

    override fun updatePet(pet: Pet, email: String): Pet {
        if (pet.id != null && petRepository.existsById(pet.id)){
            pet.user = User(email= email)
           return petRepository.save(pet)
        } else throw IllegalArgumentException("Pet is not found!") // TODO:обработать
    }

    override fun getPetList(email: String) = petRepository.findPetListByEmail(email)

}