package pad4pets.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import pad4pets.dto.requestDto.PetDTORequest
import pad4pets.dto.responseDto.PetDTOResponse
import pad4pets.service.entity.Pet
import pad4pets.service.PetService


@CrossOrigin
@RestController
@RequestMapping("/api/pets")
class PetController(
        private val petService: PetService
) {

    @PostMapping
    fun addPet(@RequestBody pet: PetDTORequest, authentication: Authentication): PetDTOResponse {
        val userId= authentication.principal as Long
        return petService.add(pet,  userId).toDto()
    }

    @PutMapping("/{id}")
    fun updatePet( @RequestBody pet: PetDTORequest, authentication: Authentication, @PathVariable id: Long): PetDTOResponse {
        val userId = authentication.principal as Long
        return  petService.updatePet(id, pet, userId).toDto()
    }

    @GetMapping
    fun getAllPetsByUserId(authentication: Authentication): List<PetDTOResponse> {
        val userId = authentication.principal as Long
        return  petService.getPetList(userId)
                .map{it.toDto()}
    }

    @DeleteMapping("/{id}")
    fun deletePetById(@PathVariable id: Long, authentication: Authentication) {
        val userId = authentication.principal as Long
        return petService.deletePet(id, userId)
    }
}