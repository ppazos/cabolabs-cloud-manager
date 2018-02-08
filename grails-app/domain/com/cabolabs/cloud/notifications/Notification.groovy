package com.cabolabs.cloud.notifications

import com.cabolabs.cloud.security.User

class Notification {

   String message
   Date dateCreated
   String status = "new"
   String source
   String category
   User forUser

   static constraints = {
      category inList: ['INFO', 'WARNING', 'ALERT', 'SUCCESS']
   }
}
