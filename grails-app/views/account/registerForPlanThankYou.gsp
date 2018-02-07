<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title><g:message code="default.registerForPlanThankYou.label" /></title>
  </head>
  <body>
    <div class="nav" role="navigation">
      <ul>
          <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
          <li><g:link class="list" action="index"><g:message code="default.list.label" /></g:link></li>
      </ul>
    </div>
    <div id="create-account" class="content scaffold-create" role="main">
      <h1><g:message code="default.registerForPlanThankYou.label"  /></h1>
<!-- if refresh is pressed this will fail, session is better to put the data -->
        <div class="message" role="status">
          Check your ${user.email} email, we have sent you the final step to finish your account setup.
          You have created a subscription for the plan '${plan.name}' of '${plan.resource.name}'.
        </div>

    </div>
  </body>
</html>
