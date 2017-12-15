package com.cabolabs.cloud

import com.cabolabs.cloud.security.User
class DashboardController {

   def index() {

      // Simulates login from a publisher user for testing
      session.user = User.findByUsername('publisher1')

      render view:'index'
   }
}
