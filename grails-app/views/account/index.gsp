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

            <g:if test="${type == 'publisher'}">
               <table class="table">
                 <thead>
                   <tr>
                     <th class="sortable"><a href="/account/index?sort=uid&max=10&order=asc">UID</a></th>
                     <th class="sortable"><a href="/account/index?sort=companyName&max=10&order=asc">Company Name</a></th>
                     <th class="sortable"><a href="/account/index?sort=companyUrl&max=10&order=asc">Company Url</a></th>
                     <th class="sortable"><a href="/account/index?sort=companyAddress1&max=10&order=asc">Company Address1</a></th>
                     <th class="sortable"><a href="/account/index?sort=country&max=10&order=asc">Country</a></th>
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
            </g:if>
            <g:if test="${type == 'subscriber'}">
               <table class="table">
                 <thead>
                   <tr>
                     <th class="sortable"><a href="/account/index?sort=companyName&max=10&order=asc">Company Name</a></th>
                     <th class="sortable"><a href="/account/index?sort=balance&max=10&order=asc">Balance</a></th>
                     <th>Actions</th>
                   </tr>
                 </thead>
                 <tbody>
                   <g:each in="${accountList}" var="account">
                     <tr>
                       <td><g:link action="show" id="${account.id}">${account.companyName}</g:link></td>
                       <td>${account.balance}</td>
                       <td>
                         <a href="#" class="btn btn-success">Add credit</a>
                         <pre>
    1. abrir modal y mostrar las opciones de pago de la cuenta
    2. selecciona una opcion, y mostramos pantalla para comenzar el pago (ej. botones de PayPal, integracion de UI con stripe, etc.)
    3. sigue el proceso del pago (puede ir a otro sitio y volver)
    4. necesitamos SubscriberPaymentConfigController para listar y gestionar las opciones de pago para los subscribers
                         </pre>
                       </td>
                     </tr>
                   </g:each>

                 </tbody>
               </table>
            </g:if>


        </div>
    </body>
</html>
