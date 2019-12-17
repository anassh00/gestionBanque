<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/style1.css" />
<title>banque</title>
</head>
  
<body>
	<div>
	<f:form modelAttribute="banqueForm" method="post" action="chargerCompte">
		<table>
			<tr>
				<td>Code:</td>
				<td><f:input path="code"/></td>
				<td><f:errors path="code"></f:errors></td>
			</tr>
			<tr>
				<td><input type="submit" value="OK"></td>
			</tr>
		</table>
	</f:form>
	</div>
	<c:if test="${not empty banqueForm.exception }">
				<div>${banqueForm.exception }</div>
			</c:if>
	<c:if test="${not empty banqueForm.compte }">
	<div>
		<table>
			<tr>
				<td>Solde :</td>
				<td>${banqueForm.compte.solde}</td>
			</tr>
			<tr>
				<td>Date :</td>
				<td>${banqueForm.compte.dateCreation}</td>
			</tr>
			<tr>
				<td>Type Compte :</td>
				<td>${banqueForm.typeCompte}</td>
			</tr>
			<c:if test="${banqueForm.typeCompte=='CompteCourant'}">
				<tr>
					<td>Découvert :</td>
					<td>${banqueForm.compte.decouvert}</td>
				</tr>
			</c:if>
			<c:if test="${banqueForm.typeCompte=='CompteEpargne'}">
				<tr>
					<td>Taux :</td>
					<td>${banqueForm.compte.taux}</td>
				</tr>
			</c:if>
			
		</table>
		<div>
			<table>
				<tr>
					<td>Nom Client :</td>
					<td>${banqueForm.compte.client.nomClient}</td>
				</tr>
			</table>
		</div>
		<div>
			<table>
				<tr>
					<td>Nom Employé :</td>
					<td>${banqueForm.compte.employe.nomEmploye}</td>
				</tr>
			</table>
		</div>
	
	
	<div>
			<f:form modelAttribute="banqueForm" action="saveOperation">
				<f:hidden path="code"/>
				
					<tr>
						<td>Versement<f:radiobutton path="typeOperation" value="VERSEMENT" onclick="this.form.submit()"/></td>
						<td>Retrait<f:radiobutton path="typeOperation" value="RETRAIT" onclick="this.form.submit()"/></td>
						<td>Virement<f:radiobutton path="typeOperation" value="VIREMENT" onclick="this.form.submit()"/></td>
					</tr>
					<c:if test="${ not empty banqueForm.typeOperation }">
					
					    <tr>
					    	<td>Montant:</td>
					    	<td><f:input path="montant"/> </td>
					    	<td><f:errors path="montant"></f:errors> </td>
					    </tr>
					 		<c:if test="${ banqueForm.typeOperation == 'VIREMENT' }">
					 			<tr>
					 				<td>Compte:</td>
					 				<td><f:input path="Code2"/></td>
					 				<td><f:errors path="Code2"></f:errors> </td>
					 			</tr>
					 		</c:if>
					 		<tr>
					 			<td><input type="submit" name="action" value="Save"> </td>
					 		</tr>
					</c:if>
					
				
			</f:form>
		</div>
   
	   <div>
    <table class="table1">
      <tr>
        <th>Num</th><th>Type</th><th>date</th> <th>montant</th>
      </tr>
      <c:forEach items="${banqueForm.operations }" var="op">
        <tr>
          <td>${op.numeroOperation }</td>
          <td>${op}</td>
          <td>${op.dateOperation }</td>
          <td>${op.montant }</td>
        </tr>
      </c:forEach>
    </table>
    <div>
		 <c:forEach begin="0" end="${banqueForm.nbpages-1}" var="p">
        <c:choose>
           <c:when test="${p==banqueForm.page}">
             <span class="current">Page ${p }</span>
           </c:when>
           <c:otherwise>
             <span class="autrePage">
               <a href="chargerCompte?page=${p}&code=${banqueForm.code}" >Page ${p }</a>
             </span>
           </c:otherwise>
        </c:choose>
        
     </c:forEach>
    </div>
   </div>

</div>
	</c:if>
   
   <c:if test="${not empty banqueForm.exception }">
    <div>
      ${banqueForm.exception}
    </div>
   </c:if>
	
</body> 




























</html>