package pad4pets.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ProductServiceInterceptor : HandlerInterceptor {
    @Throws(Exception::class)
    override fun preHandle(
            request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(
            request: HttpServletRequest, response: HttpServletResponse, handler: Any,
            modelAndView: ModelAndView?) {
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse,
                                 handler: Any, exception: Exception?) {
    }
}

@Component
class ProductServiceInterceptorAppConfig : WebMvcConfigurerAdapter() {
    @Autowired
    var productServiceInterceptor: ProductServiceInterceptor? = null
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(productServiceInterceptor!!)
    }
}