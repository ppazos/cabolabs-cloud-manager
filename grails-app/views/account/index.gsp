<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-account" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-account" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
<!--
            <f:table collection="${accountList}" />
-->
            <table class="table">
              <thead>
                <tr>
                  <th class="sortable"><a href="/account/index?sort=uid&amp;max=10&amp;order=asc">UID</a></th>
                  <th class="sortable"><a href="/account/index?sort=companyName&amp;max=10&amp;order=asc">Company Name</a></th>
                  <th class="sortable"><a href="/account/index?sort=companyUrl&amp;max=10&amp;order=asc">Company Url</a></th>
                  <th class="sortable"><a href="/account/index?sort=companyAddress1&amp;max=10&amp;order=asc">Company Address1</a></th>
                  <th class="sortable"><a href="/account/index?sort=country&amp;max=10&amp;order=asc">Country</a></th>
                </tr>
              </thead>
              <tbody>
                <g:each in="${accountList}" var="account">
                  <tr>
                    <td><g:link action="show" id="${account.id}">${account.uid}</g:link></td>
                    <td>${account.companyName}</td>
                    <td>${account.companyUrl}</td>
                    <td>${account.companyAddress1}</td>
                    <td>${account.country}</td>
                  </tr>
                </g:each>

              </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${accountCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
