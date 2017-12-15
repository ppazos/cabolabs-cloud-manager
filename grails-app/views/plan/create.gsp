<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-plan" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-plan" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.plan}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.plan}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.plan}" method="POST">
                <fieldset class="form">
                    <!--<f:all bean="plan"/>-->
                    <div class="fieldcontain required">
                      <label for="name">Name
                        <span class="required-indicator">*</span>
                      </label>
                      <input type="text" name="name" value="" required="" id="name">
                    </div>
                    <div class="fieldcontain required">
                      <label for="description">Description</label>
                      <textarea name="description" id="description"></textarea>
                    </div>

                    <div class="fieldcontain required">
                      <label for="price">Price
                        <span class="required-indicator">*</span>
                      </label>
                      <input type="number" name="price" value="0.0" required="" id="price" min="0" step="0.01">
                    </div>

                    <div class="fieldcontain required">
                      <label for="currency">Currency
                        <span class="required-indicator">*</span>
                      </label>
                      <g:select from="['USD', 'UYU']" name="currency" required="" id="currency" />
                    </div>

                    <div class="fieldcontain required">
                      <label for="resource">Resource
                        <span class="required-indicator">*</span>
                      </label>
                      <g:select from="${resources}" name="resource" required="" id="resource" optionKey="id" />
                    </div>

                    <div class="fieldcontain required">
                      <label for="isEnabled">Is enabled?
                        <span class="required-indicator">*</span>
                      </label>
                      <g:checkBox name="isEnabled" value="${false}" />
                    </div>

                    <div class="fieldcontain required">
                      <label for="billingPeriod">Billing period
                        <span class="required-indicator">*</span>
                      </label>
                      <g:select from="${billingPeriods}" name="billingPeriod" required="" id="billingPeriod" />
                    </div>

                    <div class="fieldcontain required">
                      <label for="payMode">Pay mode
                        <span class="required-indicator">*</span>
                      </label>
                      <g:select from="['START_OF_BILLING_PERIOD', 'END_OF_BILLING_PERIOD']" name="payMode" required="" id="payMode" />
                    </div>

                    // TODO: form template

                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
