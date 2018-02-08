package cabolabs.cloud.manager

class UrlMappings {

    static mappings = {
       "/auth/reset"(
          controller: "auth",
          action: "resetPassword"
       )

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
