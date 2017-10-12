package com.cabolabs.cloud.resources

class ResourceDimension {

   // e.g. "Storage usage in bytes", "Number of users", "Number of clinical documents", etc.
   String name

   // e.g. "com.cabolabs.resourcex.storage_usage"
   String key

   Resource resource

   static constraints = {
   }
}
