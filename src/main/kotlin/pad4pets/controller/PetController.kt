package pad4pets.controller

import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import pad4pets.entity.Pet
import pad4pets.entity.User
import pad4pets.service.PetService


@CrossOrigin
@RestController
@RequestMapping("/api/pets")
class PetController(
        private val petService: PetService
) {
    //TODO: вынести principals в отдельную переменную email
    @PostMapping
    fun addPet(@RequestBody pet: Pet, authentication: Authentication) = petService.add(pet,  authentication.principal as Long )

    @PutMapping
    fun updatePet( @RequestBody pet: Pet, authentication: Authentication) = petService.updatePet(pet, authentication.principal as Long)

    @GetMapping
    fun getAllPetsByUserId(authentication: Authentication) = petService.getPetList(authentication.principal as Long)
}