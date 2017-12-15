package com.cabolabs.cloud.accounts

import com.cabolabs.cloud.security.User

class Account {

   Date dateCreated

   String uid = java.util.UUID.randomUUID() as String

   String companyName
   String companyUrl
   String companyAddress1
   String companyAddress2

   String country

   // publisher for PublisherAccount, subscriber for SubscriberAccount
   User contact

   // TODO: add hashed prefix based on the company uid
   // or maybe use JWT.
   String apiKey = java.util.UUID.randomUUID() as String


   static constraints = {
      companyUrl nullable: true // subscribers might not have an URL, TODO: require for publishers
      companyAddress2 nullable: true
   }

   String toString()
   {
      return this.companyName
   }
}
