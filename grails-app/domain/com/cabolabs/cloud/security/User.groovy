package com.cabolabs.cloud.security

class User {

   String usernane
   String password
   Date dateCreated

   static constraints = {
      username unique: true
   }
}
