package pad4pets.service

import org.springframework.stereotype.Service
import pad4pets.entity.Pet
import pad4pets.repository.PetRepository

@Service
interface PetService {
    fun add(pet: Pet): Pet
    fun updateName(id: Long, name: String): Pet
}

@Service
class PetServiceImpl(
        private val petRepository: PetRepository,
):PetService {

    override fun add(pet: Pet): Pet {
        val pet = Pet(null,pet.name, pet.birth, pet.type, pet.sex, pet.color, pet.sterilization)
        return  petRepository.save(pet)
    }

    override fun updateName(id: Long, name: String): Pet {
        val updatedPetOptional = petRepository.findById(id)
        if (updatedPetOptional.isPresent){
            val updatedPet = updatedPetOptional.get()
            updatedPet.name = name
            return  petRepository.save(updatedPet)
        } else throw IllegalArgumentException("Pet is not found!") // TODO:обработать
    }
}