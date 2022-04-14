package pad4pets.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pad4pets.entity.Pet
import pad4pets.service.PetService
import java.util.*


@CrossOrigin
@RestController
@RequestMapping("/api/pets")
class PetController(
        private val petService: PetService
) {
    @PostMapping
    fun addPet(@RequestBody pet: Pet) = petService.add(pet)

    @PutMapping
    fun updatePet( @RequestBody pet: Pet) = petService.updatePet(pet)

    @GetMapping("user/{userId}")
    fun getAllPetsByUserId(@PathVariable userId: Long) = petService.getPetList(userId)
}