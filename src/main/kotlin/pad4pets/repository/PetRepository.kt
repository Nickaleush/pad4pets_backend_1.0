package pad4pets.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import pad4pets.entity.Pet

interface PetRepository: JpaRepository<Pet, Long> {

    @Query("SELECT pet " +
            "FROM Pet pet " +
            "WHERE pet.user.id = :userId")
    fun findPetListByUserId(userId: Long): List<Pet>

    @Query("SELECT pet " +
            "FROM Pet pet " +
            "WHERE pet.user.id = :userId " +
            "AND pet.id = :petId")
    fun existsByUserId(petId: Long, userId: Long): Boolean

    @Modifying
    @Query("DELETE " +
            "FROM Pet pet " +
            "WHERE pet.id = :petId")
    fun deletePetByPetId(petId: Long)

}