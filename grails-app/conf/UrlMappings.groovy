class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        /*"/api/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }*/

        "/api/todos" (resources: 'todo')
        "/api/todos" (resources: 'user')

        "/todo/index" (controller: 'todo', action: 'index')

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
