package com.cabolabs.cloud.resources

import com.cabolabs.cloud.accounts.*

class Resource {

   String uid = java.util.UUID.randomUUID() as String
   String name
   String description
   boolean isPublished = false
   Date dateCreated

   // remote information/metadata about the resource
   String appUrl       // where the app is published or can be accessed/used
   String docsUrl      // documentation
   String marketingUrl // marketing website of the app
   String metadataUrl  // predefined set of information about the app, some fields might be dynamic
   String statusUrl    // url where the working status of he app can be checked

   // onwing account of the resource
   PublisherAccount publisher

   static constraints = {
      description nullable: true
      statusUrl nullable: true
      docsUrl nullable: true
      metadataUrl nullable: true
   }

   static transients = ['dimensions', 'plans']

   def getDimensions()
   {
      ResourceDimension.findAllByResource(this)
   }

   def getPlans()
   {
      Plan.findAllByResource(this)
   }
}
