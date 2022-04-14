package pad4pets.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import pad4pets.entity.Pet

interface PetRepository: JpaRepository<Pet, Long> {

//    @Query("SELECT pet " +
//            "FROM Pet pet " +
//            "WHERE pet.name = :name")
//    fun findByNameByUserEmail(name:String): Pet?

    @Query("SELECT pet " +
            "FROM Pet pet " +
            "WHERE pet.user.email = :email")
    fun findPetListByEmail(email: String): List<Pet>

}