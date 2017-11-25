package cabolabs.cloud.manager

import com.cabolabs.cloud.accounts.*
import com.cabolabs.cloud.resources.*
import com.cabolabs.cloud.plans.*
import com.cabolabs.cloud.subscription.*

class BootStrap {

    def init = { servletContext ->

       def publisher_accounts = [
          new PublisherAccount(companyName:'CaboLabs', companyUrl:'https://www.cabolabs.com', companyAddress1:'Juan Paullier 995/703', country:'UY'),
          new PublisherAccount(companyName:'Veratech', companyUrl:'https://www.cabolabs.com', companyAddress1:'Juan Paullier 995/703', country:'UY'),
          new PublisherAccount(companyName:'GLYMS', companyUrl:'https://www.cabolabs.com', companyAddress1:'Juan Paullier 995/703', country:'UY'),
          new PublisherAccount(companyName:'VirtualBox', companyUrl:'https://www.cabolabs.com', companyAddress1:'Juan Paullier 995/703', country:'UY'),
          new PublisherAccount(companyName:'InfoClinic', companyUrl:'https://www.cabolabs.com', companyAddress1:'Juan Paullier 995/703', country:'UY'),
       ]

       publisher_accounts*.save()

       def resources = [
          new Resource(name:'CloudEHRServer', isPublished:true, publisher:publisher_accounts[0], appUrl:'https://server001.cloudehrserver.com', marketingUrl:'https://cloudehrserver.com'),
          new Resource(name:'Psy.Notes', isPublished:true, publisher:publisher_accounts[0], appUrl:'https://server001.psynotes.com', marketingUrl:'https://psynotes.com')
       ]

       resources*.save()

       // calculates priceDailyFraction from billingPeriod and price
       def plans = [
          new Plan(name:'Bronze', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:10.0,  resource:resources[0]),
          new Plan(name:'Silver', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:25.0,  resource:resources[0]),
          new Plan(name:'Gold',   isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:100.0, resource:resources[0]),

          new Plan(name:'Bronze', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:15.0,  resource:resources[1]),
          new Plan(name:'Silver', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:30.0,  resource:resources[1]),
          new Plan(name:'Gold',   isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:120.0, resource:resources[1])
       ]

       //plans*.save()
       plans.each {
         if (!it.save()) println it.errors
       }

       // subscriber associated to a plan
       def subscribers = [
          new SubscriberAccount(companyName:'Client1', companyUrl:'http://client1.com', companyAddress1:'123 St. Thomas', country:'US')
       ]

       subscribers*.save()

       def payment_info = new SubscriberPaymentConfig(gateway:'stripe', method:"debit/credit").save()

       // created 100 days ago
       def subscription = PlanAssociation(since:new Date()-100, status:'ACTIVE', plan:plans[0], subscriber:subscribers[0], payments:payment_info).save()

       // TODO: sample transactions, one per month since 100 days ago, so we have 3 completed and one running (calculated)
       def transactions = []

       // TODO: need a process that calculates charging days from the billing period, and since, and the amount from priceDailyFraction if the period was not reached.
       // use those calculated charging dates as the transaction dates for the transactions above ^

    }
    def destroy = {
    }
}
