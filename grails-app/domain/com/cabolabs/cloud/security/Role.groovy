package com.cabolabs.cloud.security

class Role {

   String authority

   static constraints = {
      authority inList:['admin', 'publisher', 'subscriber'] // TODO: enum
   }
}
