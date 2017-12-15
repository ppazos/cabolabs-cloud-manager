package com.cabolabs.cloud.payments

import com.cabolabs.cloud.accounts.*

class SubscriberPaymentConfig {

   SubscriberAccount subscriber
   String gateway
   String method
   static constraints = {
      gateway inList:['stripe', 'paypal']
      method inList:['debit/credit', 'paypal']
   }
}
