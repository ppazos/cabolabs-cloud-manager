package com.cabolabs.cloud.transactions

import com.cabolabs.cloud.plans.*

class Transaction {

   Date dateCreated
   BigDecimal amount
   String number

   String type
   String status

   String description

   // For charges
   Date billingPeriodFrom
   Date billingPeriodTo

   // The transaction is for a Plan and Account
   PlanAssociation planAssociation

   static constraints = {
      type inList: ['CHARGE', 'CREDIT']
      billingPeriodFrom nullable: true
      billingPeriodTo nullable: true
   }
}
