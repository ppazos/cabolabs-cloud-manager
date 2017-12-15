package com.cabolabs.cloud.security

class User {

   String username
   String password
   Date dateCreated

   static constraints = {
      username unique: true
   }
}
