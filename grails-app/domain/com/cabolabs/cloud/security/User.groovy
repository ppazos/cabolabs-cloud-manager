package com.cabolabs.cloud.security

class User {

   String username
   String password
   Date dateCreated
   String phone // business contact phone
   String roleInOrganization // CTO, CEO, CIO, ...
   String role // TODO: mapping because is reserved work on dbms

   static constraints = {
      username unique: true
      role inList:['admin', 'publisher', 'subscriber'] // TODO: enum
   }
}
