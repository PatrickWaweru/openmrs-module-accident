<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<h2><spring:message code="kenyaemraccident.title" /></h2>

<br/>
<table>
  <tr>
   <th>Department Id</th>
   <th>Name</th>
   <th>Description</th>
  </tr>
  <c:forEach var="department" items="${departments}">
      <tr>
        <td>${department.id}</td>
        <td>${department.name}</td>
        <td>${department.description}</td>
      </tr>		
  </c:forEach>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>
