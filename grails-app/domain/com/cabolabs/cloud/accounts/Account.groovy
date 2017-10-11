package com.cabolabs.cloud.accounts

class Account {

   Date dateCreated

   String uid = java.util.UUID.randomUUID() as String

   String companyName
   String companyUrl
   String companyAddress1
   String companyAddress2

   String country
   String apiKey = java.util.UUID.randomUUID() as String


   static constraints = {
   }
}
