package com.cabolabs.cloud.subscription

import com.cabolabs.cloud.accounts.*
import com.cabolabs.cloud.transactions.*
import com.cabolabs.cloud.plans.*

/**
 * Represents the subscription of a subscriber to a resource.
 */
class Subscription {

   Date dateCreated
   Date since
   Date until
   String status
   BigDecimal balance = 0.0

   SubscriberAccount subscriber
   Plan plan

   List payments = []
   static hasMany = [payments:SubscriberPaymentConfig]

   static constraints = {
      until nullable: true
      status inList: ['INACTIVE', 'ACTIVE', 'SUSPENDED', 'CLOSED'] // TODO: enum, closed should happen at the end of the current period.
   }

   static transients = ['transactions', 'billingCycleCloseDates', 'billingCycleStartDates', 'billingCycleCloseDatesSince']

   def getTransactions()
   {
      Transaction.findAllByPlanAssociation(this)
   }

   def getBillingCycleCloseDates()
   {
      // If billing period is 30, there wont be an invoice for FEB, if someone
      // requires one invoice each month, we should use a different invoice
      // date than the end of billing period date.
      def res = []
      Calendar.with {

         def cal = instance // invokes static getInstance()

         // Assumes first billing period of the plan starts on Jan 1st
         // check https://github.com/ppazos/cabolabs-cloud-manager/issues/7
         cal.set(DAY_OF_MONTH, 1)
         cal.set(MONTH, 0)

         cal.set(HOUR_OF_DAY, 23)
         cal.set(MINUTE, 59)
         cal.set(SECOND, 59)

         // for number of billing periods
         // 1..12 for 30
         // 1..13 for 28
         (1..365.intdiv(this.plan.billingPeriod.value)).each{

            cal.add(DATE, this.plan.billingPeriod.value)
            res << cal.time
         }
      }

      return res
   }

   def getBillingCycleStartDates()
   {
      def closes = this.billingCycleCloseDates
      def res = []
      closes.each {
         res << it - this.plan.billingPeriod.value
      }

      return res
   }

   def getBillingCycleCloseDatesSince(int days)
   {
      def closes = this.billingCycleCloseDates
      def res = []
      def now = new Date()
      closes.each {
         if ( (now - days .. now).containsWithinBounds(it) ) res << it
      }

      return res
   }
}
