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

   // sends email because the user forgot his password
   def resetPasswordRequest(String email)
   {
      if (!email)
      {
         flash.message = message(code:"user.resetPasswordRequest.noEmail")
         redirect action:'login'
         return
      }

      def user = User.findByEmail(email)

      if (!user)
      {
         flash.message = message(code:"user.resetPasswordRequest.emailDoesntExists")
         redirect(action:'login')
         return
      }

      // TODO

   }

   // access from email to reset the password from a password reset view
   def resetPassword(String token)
   {
      if (!token)
      {
         flash.message = message(code:"user.resetPassword.noToken")
         redirect action:'login'
         return
      }

      def user = User.findByResetPasswordToken(token)

      if (!user)
      {
         flash.message = message(code:"user.resetPassword.invalidToken")
         redirect(action:'login')
         return
      }

      if (request.post)
      {
         // TODO: do pass reset
         // https://github.com/ppazos/cabolabs-ehrserver/blob/master/grails-app/controllers/com/cabolabs/security/UserController.groovy#L670
      }

      // TODO: render password reset view

   }
}
