package com.cabolabs.cloud

import com.cabolabs.cloud.security.User
class DashboardController {

   static defaultActon = "index"

   def index() {

      // Simulates login from a publisher user for testing
      //session.user = User.findByUsername('publisher1')

      render view:'index'
   }

   def subscriber()
   {
      // Simulates login from a subscriber user for testing
      //session.user = User.findByUsername('subscriber1')

      render view:'index'
   }
}
