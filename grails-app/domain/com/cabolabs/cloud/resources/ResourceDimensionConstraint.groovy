package com.cabolabs.cloud.resources

import com.cabolabs.cloud.plans.*

class ResourceDimensionConstraint {

   ResourceDimension dimension

   String limit = 'MAX'
   BigDecimal value

   // true if constraint applies in general, false
   // if it applies to the current billing period.
   boolean isAbsolute = false

   // the constraint is defined for a plan
   Plan plan

   static constraints = {
      limit inList: ['MIN', 'MAX']
   }

   static mapping = {
      limit column: 'rdc_limit'
   }
}
