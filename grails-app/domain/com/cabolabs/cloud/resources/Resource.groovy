package com.cabolabs.cloud.resources

class Resource {

   String uid = java.util.UUID.randomUUID() as String
   String name
   String description
   boolean isPublished
   Date dateCreated

   // remote information about the reosource
   String appUrl       // where the app is published or can be accessed/used
   String docsUrl      // documentation
   String marketingUrl // marketing website of the app
   String metadataUrl  // predefined set of information about the app, some fields might be dynamic
   String statusUrl    // url where the working status of he app can be checked


   static constraints = {
      description nullable: true
      statusUrl nullable: true
      docsUrl nullable: true
   }
}
