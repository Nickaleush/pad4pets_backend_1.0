package pad4pets.repository

import org.springframework.data.jpa.repository.JpaRepository
import pad4pets.entity.Pet

interface PetRepository: JpaRepository<Pet, Long> {
    fun findByName(name:String): Pet?

}