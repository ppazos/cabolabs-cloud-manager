package com.cabolabs.cloud.security

class AuthController {

   def login()
   {
      if (params.doit)
      {

      }

      // render login
   }

   def logout()
   {

   }

   def resetPasswordRequest(String email)
   {
      def user = User.findByEmail(email)

      if (!user)
      {
         flash.message = message(code:"user.forgotPassword.emailDoesntExists")
         redirect(action:'show', id:params.id)
         return
      }


   }
}
