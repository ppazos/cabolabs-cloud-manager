package cabolabs.cloud.manager

import com.cabolabs.cloud.accounts.*
import com.cabolabs.cloud.resources.*
import com.cabolabs.cloud.plans.*
import com.cabolabs.cloud.subscription.*
import com.cabolabs.cloud.transactions.*
import com.cabolabs.cloud.security.*

class BootStrap {

   def init = { servletContext ->

       // ================================================
       // Test users
       def users = [
          new User(username:'admin1',      password:'admin1',      role:'admin',      email: 'pablo1@cabolabs.com'),
          new User(username:'publisher1',  password:'publisher1',  role:'publisher',  email: 'pablo2@cabolabs.com'),
          new User(username:'publisher2',  password:'publisher2',  role:'publisher',  email: 'pablo3@cabolabs.com'),
          new User(username:'subscriber1', password:'subscriber1', role:'subscriber', email: 'pablo4@cabolabs.com'),
          new User(username:'subscriber2', password:'subscriber2', role:'subscriber', email: 'pablo5@cabolabs.com')
       ]

       users*.save()

       // ================================================


      // Test accounts, resources and plans
      def publisher_accounts = [
        new PublisherAccount(contact: users[1], companyName:'CaboLabs',
                             companyUrl:'https://www.cabolabs.com',
                             companyAddress1:'Juan Paullier 995/703', country:'UY'),
        new PublisherAccount(contact: users[2], companyName:'Veratech',
                             companyUrl:'https://www.veratech.com',
                             companyAddress1:'XXXX 12345', country:'ES')
        //,
        //new PublisherAccount(companyName:'GLYMS', companyUrl:'https://www.cabolabs.com', companyAddress1:'Juan Paullier 995/703', country:'UY'),
        //new PublisherAccount(companyName:'VirtualBox', companyUrl:'https://www.cabolabs.com', companyAddress1:'Juan Paullier 995/703', country:'UY'),
        //new PublisherAccount(companyName:'InfoClinic', companyUrl:'https://www.cabolabs.com', companyAddress1:'Juan Paullier 995/703', country:'UY'),
      ]

      publisher_accounts*.save()

      def resources = [
        new Resource(name:'CloudEHRServer', isPublished:true, publisher:publisher_accounts[0], appUrl:'https://server001.cloudehrserver.com', marketingUrl:'https://cloudehrserver.com'),
        new Resource(name:'Psy.Notes', isPublished:true, publisher:publisher_accounts[0], appUrl:'https://server001.psynotes.com', marketingUrl:'https://psynotes.com')
      ]

      resources*.save()

      // calculates priceDailyFraction from billingPeriod and price
      def plans = [
        new Plan(name:'Bronze', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY,  price:10.0, resource:resources[0]),
        new Plan(name:'Silver', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY,  price:25.0, resource:resources[0]),
        new Plan(name:'Gold',   isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:100.0, resource:resources[0]),

        new Plan(name:'Bronze', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY,  price:15.0, resource:resources[1]),
        new Plan(name:'Silver', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY,  price:30.0, resource:resources[1]),
        new Plan(name:'Gold',   isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:120.0, resource:resources[1])
      ]

      //plans*.save()
      plans.each {
       if (!it.save()) println it.errors
      }

      // =========================================================
      // Subscribers and Subscriptions
      // subscriber associated to a plan
      def subscribers = [
         new SubscriberAccount(companyName:'Client1', companyUrl:'http://client1.com',
                               companyAddress1:'123 St. Thomas', country:'US',
                               contact: users[3]),
         new SubscriberAccount(companyName:'Client2',
                               companyAddress1:'Miguel Barreiro 3285', country:'UY',
                               contact: users[4])
      ]

      subscribers*.save()

      // has some validation errors
      //def payment_info = new SubscriberPaymentConfig(gateway:'stripe', method:"debit/credit", subscriber:subscribers[0]).save()

      def subscription = new Subscription(since:new Date()-100,
                                          status:'ACTIVE',
                                          plan:plans[0],
                                          subscriber:subscribers[0])
      if (!subscription.validate())
      {
         println subscription.errors
      }
      else
      {
         subscription.save()
      }

      // created 100 days ago
      //def subscription = new Subscription(since:new Date()-100, status:'ACTIVE', plan:plans[0], subscriber:subscribers[0], payments:payment_info).save()

      def close_dates = subscription.getBillingCycleCloseDatesSince(100)

       // TODO: generate the transaction number per Plan, as a numeric string
       // TODO: sample transactions, one per month since 100 days ago, so we have 3 completed and one running (calculated)
      def transactions = [

      ]

      def start, i = 1
      close_dates.each {
         start = it - subscription.plan.billingPeriod.value
         transactions << new Transaction(type:'CHARGE', subscription:subscription,
                                         number:'0000000000'+i,
                                         billingPeriodFrom:start, billingPeriodTo:it)
         i++
      }

      transactions*.save()

      // TODO: need a process that calculates charging days from the billing period, and since, and the amount from priceDailyFraction if the period was not reached.
      // use those calculated charging dates as the transaction dates for the transactions above ^
   }
   def destroy = {
   }
}
