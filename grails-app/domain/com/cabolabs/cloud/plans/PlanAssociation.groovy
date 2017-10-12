package com.cabolabs.cloud.plans

import com.cabolabs.cloud.accounts.*
import com.cabolabs.cloud.transactions.*

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

   static transients = ['transactions']

   def getTransactions()
   {
      Transaction.findAllByPlanAssociation(this)
   }
}
