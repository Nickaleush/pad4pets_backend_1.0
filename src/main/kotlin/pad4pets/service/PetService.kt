package pad4pets.service

import org.springframework.stereotype.Service
import pad4pets.dto.requestDto.PetDTORequest
import pad4pets.service.entity.Pet
import pad4pets.service.entity.User
import pad4pets.repository.PetRepository

@Service
interface PetService {
    fun add(petDTO: PetDTORequest, userId: Long): Pet
    fun updatePet(petId:Long, petDTO: PetDTORequest, userId: Long): Pet
    fun getPetList(userId: Long): List<Pet>
    fun deletePet(petId: Long, userId: Long)
}

@Service
class PetServiceImpl(
        private val petRepository: PetRepository
):PetService {

    override fun add(petDTO: PetDTORequest, userId: Long): Pet {
        val pet = Pet(
                name = petDTO.name,
                birth = petDTO.birth,
                type = petDTO.type,
                breed = petDTO.breed,
                sex = petDTO.sex,
                color = petDTO.color,
                sterilization = petDTO.sterilization,
                user = User(id = userId)
        )
        return petRepository.save(pet)
    }

    override fun updatePet(petId:Long, petDTO: PetDTORequest, userId: Long): Pet {
        val updatedPet = Pet(
                id = petId,
                name = petDTO.name,
                birth = petDTO.birth,
                type = petDTO.type,
                breed = petDTO.breed,
                sex = petDTO.sex,
                color = petDTO.color,
                sterilization = petDTO.sterilization,
                user = User(id = userId)
        )
        if ( petRepository.existsByUserId(petId,userId)) {
            updatedPet.user = User(id= userId)
           return petRepository.save(updatedPet)
        } else throw IllegalArgumentException("Pet is not found!") // TODO:обработать exception
    }

    override fun getPetList(userId: Long): List<Pet> = petRepository.findPetListByUserId(userId) // TODO: обработать ошибку, если email = null

    override fun deletePet(petId: Long,userId: Long) {
        if (petRepository.existsByUserId(petId,userId)){
            petRepository.deletePetByPetId(petId)
        } else throw IllegalArgumentException("Pet is not found!") // TODO:обработать exception
    }



}