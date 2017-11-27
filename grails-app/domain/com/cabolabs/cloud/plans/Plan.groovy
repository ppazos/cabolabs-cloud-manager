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

   // Defines if payments are requested in advance
   // or at the end of the billing period. If it is
   // in advance, the next payment will be requested
   // at the beggining if the next billind period.
   String payMode = 'END_OF_BILLING_PERIOD'

   Resource resource

   static constraints = {
      description nullable: true
      priceDailyFraction nullable: true
      payMode inList: ['START_OF_BILLING_PERIOD', 'END_OF_BILLING_PERIOD'] // TODO: enum
   }

   def beforeInsert()
   {
      this.priceDailyFraction = price / billingPeriod.value
   }
}
