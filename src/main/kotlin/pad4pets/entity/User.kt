package pad4pets.entity

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var email: String?    = null,
        var username: String? = null,
        var password: String? = null,

    @Enumerated(EnumType.STRING)
        var role: Role = Role.WITHOUT_PREMIUM,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") // получение питомцев по полю из другой таблицы
        val pets: List<Pet> = mutableListOf()
)
