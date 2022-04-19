package pad4pets.security


import io.jsonwebtoken.*
import org.springframework.stereotype.Service
import pad4pets.service.entity.Role
import java.util.*

@Service
class JWTService() {

    fun createToken(email: String?, userRole: Role): String {
        val claims = Jwts.claims().setSubject(email)
        claims["role"] = userRole.name
        val currentDate = Date()
        val validationTime = Date(currentDate.time + 600000)
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(validationTime)
                .signWith(SignatureAlgorithm.HS256, "Dimaloh").compact()
    }

    fun getEmail(token: String): String {
        return Jwts.parser().setSigningKey("Dimaloh").parseClaimsJws(token).body.subject
    }

    fun getRole(token: String): String {
        return Jwts.parser().setSigningKey("Dimaloh").parseClaimsJws(token).body.get("role", String::class.java)
    }

    fun validate(token: String?): Boolean {
        return try {
            val claims: Jws<Claims> = Jwts.parser().setSigningKey("Dimaloh").parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: JwtException) {
            System.err.println("JWT is expired or invalid " + e.message)
            throw IllegalArgumentException("JWT is expired or invalid " + e.message)
        } catch (e: IllegalArgumentException) {
            System.err.println("JWT is expired or invalid " + e.message)
            throw IllegalArgumentException("JWT is expired or invalid " + e.message)
        }
    }
}