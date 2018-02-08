package cabolabs.cloud.manager

class UrlMappings {

   static mappings = {
      "/"(
         controller: "auth",
         action: "login"
      )
      "/auth/reset"(
        controller: "auth",
        action: "resetPassword"
      )

      "/$controller/$action?/$id?(.$format)?"{
         constraints {
            // apply constraints here
         }
      }

      //"/"(view:"/index") // grails default screen
      "500"(view:'/error')
      "404"(view:'/notFound')
   }
}
