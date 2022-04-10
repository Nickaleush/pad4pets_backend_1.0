package pad4pets.controller

import org.springframework.web.bind.annotation.*
import pad4pets.entity.Pet
import pad4pets.service.PetService


@CrossOrigin
@RestController
@RequestMapping("/api/pets")
class PetController(
        private val petService: PetService
) {
    @PostMapping
    fun addPet(@RequestBody pet: Pet): Pet {
        return petService.add(pet)
    }

    @PutMapping
    fun updatePetName( @RequestBody pet: Pet):Pet {
        return petService.updatePet(pet)
    }
}