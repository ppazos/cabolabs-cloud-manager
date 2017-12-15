package com.cabolabs.cloud.transactions

import com.cabolabs.cloud.subscription.*

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
   Subscription subscription

   static constraints = {
      type inList: ['CHARGE', 'CREDIT'] // TODO: enum
      type inList: ['PAID', 'PENDING'] // TODO: enum
      billingPeriodFrom nullable: true
      billingPeriodTo nullable: true
   }
}
