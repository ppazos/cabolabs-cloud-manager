<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="dashboard.title" /></title>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" controller="resource" action="index"><g:message code="domain.resource.index" /></g:link></li>
            </ul>
        </div>
        <div id="list-resource" class="content scaffold-list" role="main">
            <h1><g:message code="dashboard.title" /></h1>

            ...
        </div>
    </body>
</html>
