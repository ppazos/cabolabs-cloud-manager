package com.cabolabs.cloud.plans

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import com.cabolabs.cloud.resources.*
import com.cabolabs.cloud.accounts.*

@Transactional(readOnly = true)
class PlanController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Plan.list(params), model:[planCount: Plan.count()]
    }

    def show(Plan plan) {
        respond plan
    }

   def create() {
      // resources of any PublisherAccount of the logged user
      def resources = Resource.withCriteria {
         'in'('publisher', PublisherAccount.findAllByContact(session.user))
      }
      def billingPeriods = EBillingPeriod.values()
      respond new Plan(params), model:[resources: resources, billingPeriods: billingPeriods]
   }

    @Transactional
    def save(Plan plan) {
        if (plan == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (plan.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond plan.errors, view:'create'
            return
        }

        plan.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'plan.label', default: 'Plan'), plan.id])
                redirect plan
            }
            '*' { respond plan, [status: CREATED] }
        }
    }

    def edit(Plan plan) {
        respond plan
    }

    @Transactional
    def update(Plan plan) {
        if (plan == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (plan.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond plan.errors, view:'edit'
            return
        }

        plan.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'plan.label', default: 'Plan'), plan.id])
                redirect plan
            }
            '*'{ respond plan, [status: OK] }
        }
    }

    @Transactional
    def delete(Plan plan) {

        if (plan == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        plan.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'plan.label', default: 'Plan'), plan.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'plan.label', default: 'Plan'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
