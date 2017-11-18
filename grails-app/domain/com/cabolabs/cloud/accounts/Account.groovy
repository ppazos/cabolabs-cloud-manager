package com.cabolabs.cloud.accounts

class Account {

   Date dateCreated

   String uid = java.util.UUID.randomUUID() as String

   String companyName
   String companyUrl
   String companyAddress1
   String companyAddress2

   String country
   
   // TODO: add hashed prefix based on the company uid
   // or maybe use JWT.
   String apiKey = java.util.UUID.randomUUID() as String


   static constraints = {
      companyAddress2 nullable: true
   }
}
