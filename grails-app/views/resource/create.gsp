<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'resource.label', default: 'Resource')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-resource" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-resource" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.resource}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.resource}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.resource}" method="POST">
                <fieldset class="form">
                    <!--<f:all bean="resource"/>-->
                    <div class="fieldcontain required">
                      <label for="name">Name
                        <span class="required-indicator">*</span>
                      </label>
                      <input type="text" name="name" value="" required="" id="name">
                    </div>
                    <div class="fieldcontain required">
                      <label for="description">Description
                        <span class="required-indicator">*</span>
                      </label>
                      <textarea name="description" required="" id="description"></textarea>
                    </div>
                    <div class="fieldcontain required">
                      <label for="publisher">Publisher
                        <span class="required-indicator">*</span>
                      </label>
                      <g:select from="${accounts}" name="publisher" required="" id="publisher" optionKey="id" optionValue="companyName" />
                    </div>

                    <div class="fieldcontain required">
                      <label for="isPublished">Is published?
                        <span class="required-indicator">*</span>
                      </label>
                      <g:checkBox name="isPublished" value="${false}" />
                    </div>

                    <div class="fieldcontain required">
                      <label for="marketingUrl">Marketing URL
                        <span class="required-indicator">*</span>
                      </label>
                      <input type="text" name="marketingUrl" value="" required="" id="marketingUrl">
                    </div>
                    <div class="fieldcontain required">
                      <label for="appUrl">App URL
                        <span class="required-indicator">*</span>
                      </label>
                      <input type="text" name="appUrl" value="" required="" id="appUrl">
                    </div>

                    TODO: otras URLs
                    TODO: form para usar en edit

                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
