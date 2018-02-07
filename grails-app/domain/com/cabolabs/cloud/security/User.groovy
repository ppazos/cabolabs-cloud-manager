package com.cabolabs.cloud.security

class User {

   String id
   String username

   // FIXME: this is just for testing initial password assignment, users with this UUID
   // will be inactive until they reset their passwrods, that will be hashed, ciphered
   // and with salt.
   // https://www.codeproject.com/Articles/704865/Salted-Password-Hashing-Doing-it-Right
   String password = java.util.UUID.randomUUID() as String

   Date dateCreated
   String phone // business contact phone
   String position // role in organization / job title: CTO, CEO, CIO, ...
   String role // TODO: mapping because is reserved work on dbms
   boolean active = false
   String email

   static constraints = {
      username unique: true
      phone nullable: true
      position nullable: true
      role inList:['admin', 'publisher', 'subscriber'] // TODO: enum
   }
   static mapping = {
      id generator:'uuid2'
   }
}
