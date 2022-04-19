package pad4pets.service.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

enum class Role{
        WITHOUT_PREMIUM,
        PREMIUM
}
