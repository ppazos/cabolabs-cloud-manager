package com.cabolabs.cloud.subscription

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import com.cabolabs.cloud.accounts.*

@Transactional(readOnly = true)
class SubscriptionController {

   static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

   def index(Integer max) {

      params.max = Math.min(max ?: 10, 100)
      if (!params.offset) params.offset = 0

      def list
      def query = Subscription.createCriteria()

      if (session.user.role == 'publisher')
      {
         // show all subscriptions to my resources
         def publisher_accounts = PublisherAccount.findAllByContact(session.user)

         list = query.list(params) {
            plan {
               resource {
                  'in'('publisher', publisher_accounts)
               }
            }
         }
      }
      else if (session.user.role == 'subscriber')
      {
         // show all my subscriptions
         def subscriber_accounts = SubscriberAccount.findAllByContact(session.user)
         
         list = query.list(params) {
            'in'('subscriber', subscriber_accounts)
         }
      }
      // TODO: admin

      respond list, model:[subscriptionCount: list.totalCount]
   }

    def show(Subscription subscription) {
        respond subscription
    }

    def create() {
        respond new Subscription(params)
    }

    @Transactional
    def save(Subscription subscription) {
        if (subscription == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (subscription.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond subscription.errors, view:'create'
            return
        }

        subscription.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscription.id])
                redirect subscription
            }
            '*' { respond subscription, [status: CREATED] }
        }
    }

    def edit(Subscription subscription) {
        respond subscription
    }

    @Transactional
    def update(Subscription subscription) {
        if (subscription == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (subscription.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond subscription.errors, view:'edit'
            return
        }

        subscription.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscription.id])
                redirect subscription
            }
            '*'{ respond subscription, [status: OK] }
        }
    }

    @Transactional
    def delete(Subscription subscription) {

        if (subscription == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        subscription.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscription.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
