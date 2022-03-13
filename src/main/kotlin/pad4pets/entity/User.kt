package pad4pets.entity

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var email: String ,
    var username: String,
    var password: String,
    @Enumerated(EnumType.STRING)
     var role: Role
)
