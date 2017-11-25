package com.cabolabs.cloud.subscription

class SubscriberPaymentConfig {

   String gateway
   String method
   static constraints = {
      gateway inList:['stripe', 'paypal']
      method inList:['debit/credit', 'paypal']
   }
}
