import org.ig.sr.auth.Role
import org.ig.sr.auth.User
import org.ig.sr.auth.UserRole

class BootStrap {

    def init = { servletContext ->

        def admin = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def roleUser = new Role(authority: 'ROLE_USER').save(flush: true)
        def user = new User(username: 'user', password: 'pass').save(flush: true)

        UserRole.create user, admin, true
        UserRole.create user, roleUser, true

    }
    def destroy = {
    }
}
