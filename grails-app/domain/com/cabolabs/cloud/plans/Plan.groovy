package com.cabolabs.cloud.plans

class Plan {

   String uid = java.util.UUID.randomUUID() as String
   String name
   String description
   boolean isEnabled
   Date dateCreated

   EBillingPeriod billingPeriod
   BigDecimal price
   String priceCurrency
   BigDecimal priceDailyFraction // = price / billingPeriod in days

   Resource resource

   static constraints = {
      description nullable: true
   }
}
