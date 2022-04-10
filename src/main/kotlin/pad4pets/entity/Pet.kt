package pad4pets.entity

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "pets")
data class Pet(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        var name: String,
        var birth: Date,
        var type: String,
        var sex: String,
        var color: String,
        var sterilization: Boolean
)
