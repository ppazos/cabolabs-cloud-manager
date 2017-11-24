package cabolabs.cloud.manager

import com.cabolabs.cloud.accounts.*
import com.cabolabs.cloud.resources.*
import com.cabolabs.cloud.plans.*

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

// TODO: calculate priceDailyFraction from billingPeriod and price
       def plans = [
          new Plan(name:'Bronze', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:10.0,  priceDailyFraction:0.333, resource:resources[0]),
          new Plan(name:'Silver', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:25.0,  priceDailyFraction:0.833, resource:resources[0]),
          new Plan(name:'Gold',   isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:100.0, priceDailyFraction:3.333, resource:resources[0]),

          new Plan(name:'Bronze', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:15.0,  priceDailyFraction:0.5, resource:resources[1]),
          new Plan(name:'Silver', isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:30.0,  priceDailyFraction:1.0, resource:resources[1]),
          new Plan(name:'Gold',   isEnabled:true, billingPeriod:EBillingPeriod.MONTHLY, price:120.0, priceDailyFraction:4.0, resource:resources[1])
       ]

       plans*.save()
    }
    def destroy = {
    }
}
