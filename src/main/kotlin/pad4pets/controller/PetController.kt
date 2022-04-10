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

    @PatchMapping("/{id}/name")
    fun updatePetName(@PathVariable id: Long, @RequestBody name: String):Pet {
        return petService.updateName(id,name)
    }
}