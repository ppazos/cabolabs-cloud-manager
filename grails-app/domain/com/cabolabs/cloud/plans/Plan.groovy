package com.cabolabs.cloud.plans

import com.cabolabs.cloud.resources.*

class Plan {

   String uid = java.util.UUID.randomUUID() as String
   String name
   String description
   boolean isEnabled = false
   Date dateCreated

   EBillingPeriod billingPeriod
   BigDecimal price
   String priceCurrency = 'USD'
   BigDecimal priceDailyFraction // = price / billingPeriod in days

   Resource resource

   static constraints = {
      description nullable: true
   }
}
