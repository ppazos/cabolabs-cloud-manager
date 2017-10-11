package com.cabolabs.cloud.plans

class PlanAssociation {

   Date since
   Date util
   String status
   BigDecimal balance

   SubscriberAccount subscriber
   Plan plan

   static constraints = {
      until nullable: true
      status inList: ['INACTIVE', 'ACTIVE', 'SUSPENDED']
   }
}
