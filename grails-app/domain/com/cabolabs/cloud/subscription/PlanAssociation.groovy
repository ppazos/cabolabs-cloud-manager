package com.cabolabs.cloud.plans

import com.cabolabs.cloud.accounts.*
import com.cabolabs.cloud.transactions.*

/**
 * Represents the subscription of a subscriber to a resource.
 */
class PlanAssociation {

   Date since
   Date until
   String status
   BigDecimal balance

   SubscriberAccount subscriber
   Plan plan

   List payments = []
   static hasMany = [payments:SubscriberPaymentConfig]

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
