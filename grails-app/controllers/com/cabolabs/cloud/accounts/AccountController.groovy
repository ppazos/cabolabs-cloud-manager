package com.cabolabs.cloud.accounts

import com.cabolabs.cloud.security.User
import com.cabolabs.cloud.plans.Plan
import com.cabolabs.cloud.subscription.Subscription

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AccountController {

   static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

   def mailService
   
   def index(Integer max)
   {
      params.max = Math.min(max ?: 10, 100)
      if (!params.offset) params.offset = 0

      // publisher or subscriber accounts, depending on the user role
      def list = Account.findAllByContact(session.user)

      //println Account.list(params)
      //[accountList:Account.list(params), accountCount: Account.count()]
      [accountList: list, type: session.user.role]
   }

    def show(Account account) {
        respond account
    }

    def create() {
        respond new Account(params)
    }

    @Transactional
    def save(Account account) {
        if (account == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (account.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond account.errors, view:'create'
            return
        }

        account.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'account.label', default: 'Account'), account.id])
                redirect account
            }
            '*' { respond account, [status: CREATED] }
        }
    }

    def edit(Account account) {
        respond account
    }

    @Transactional
    def update(Account account) {
        if (account == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (account.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond account.errors, view:'edit'
            return
        }

        account.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'account.label', default: 'Account'), account.id])
                redirect account
            }
            '*'{ respond account, [status: OK] }
        }
    }

    @Transactional
    def delete(Account account) {

        if (account == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        account.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'account.label', default: 'Account'), account.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

   // Create an account when a plan is selected on an external app
   def registerForPlan(String plan_id)
   {
      def plan = Plan.get(plan_id)
      if (!plan)
      {
         // TODO: handle and feedback
         println "plan doesn't exists ${plan_id}"
      }

      render view:'create'
   }

   // without transactional, nothing is saved...
   // https://github.com/grails/grails-core/issues/10615
   // or move the code to a service, or flush: true should work
   @Transactional
   def registerForPlanSave(String user_username, String user_email, String plan_id)
   {
      def plan = Plan.get(plan_id)
      if (!plan)
      {
         // TODO: handle and feedback
         println "plan doesn't exists ${plan_id}"
      }

      // TODO: check if user_email already exists and retrieve feedback / link to login and remember password
println "create user"
      def user = new User(username: user_username, email: user_email, role: 'subscriber')
      try
      {
         println user.save(failOnError: true)
      }
      catch (Exception e)
      {
         // TODO: handle and feedback
         println "1 "+ e.message
         println user.errors
      }


println "create account"
      // Create subscriber account
      def account = new SubscriberAccount(params)
      account.contact = user
      try
      {
         println account.save(failOnError: true)
      }
      catch (Exception e)
      {
         // TODO: handle and feedback
         println "2 "+ e.message
         println account.errors
      }


      // Create the account-plan association (subscription)

      // Association with plan is INACTIVE until the rules to activate it are met
      // (e.g. credit is added to the account), we need a service to check the
      // rules and change the status if needed.

      // TODO: need to calculate the until date based on the plan config
println "create subscription"
      def association = new Subscription(status: 'INACTIVE',
                                         since: new Date(),
                                         plan: plan,
                                         subscriber: account)

      // TODO: validate and save account and subscription
      try
      {
         println association.save(failOnError: true)
      }
      catch (Exception e)
      {
         // TODO: handle and feedback
         println "3 "+ e.message
         println association.errors
      }


      // TODO: send password reset email
      mailService.sendMail {
         to user.user_email
         from "info@cabolabs.com"
         subject "Finishing your account setup"
         text 'this is some text'
      }

      session.user_id = user.id
      session.plan_id = plan.id
      redirect action:'registerForPlanThankYou'
   }

   def registerForPlanThankYou()
   {
      // render thank you!
      println session.user_id
      [user: User.get(session.user_id), plan: Plan.get(session.plan_id)]
   }
}
