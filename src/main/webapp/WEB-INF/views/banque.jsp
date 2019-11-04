<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/style.css" />
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
	<c:if test="${not empty banqueModel.exception }">
				<div>${banqueModel.exception }</div>
			</c:if>
	<c:if test="${not empty banqueModel.compte }">
	<div>
		<table>
			<tr>
				<td>Solde :</td>
				<td>${banqueModel.compte.solde}</td>
			</tr>
			<tr>
				<td>Date :</td>
				<td>${banqueModel.compte.dateCreation}</td>
			</tr>
			<tr>
				<td>Type Compte :</td>
				<td>${banqueModel.typeCompte}</td>
			</tr>
			<c:if test="${banqueModel.typeCompte=='CompteCourant'}">
				<tr>
					<td>Découvert :</td>
					<td>${banqueModel.compte.decouvert}</td>
				</tr>
			</c:if>
			<c:if test="${banqueModel.typeCompte=='CompteEpargne'}">
				<tr>
					<td>Taux :</td>
					<td>${banqueModel.compte.taux}</td>
				</tr>
			</c:if>
			
		</table>
		<div>
			<table>
				<tr>
					<td>Nom Client :</td>
					<td>${banqueModel.compte.client.nomClient}</td>
				</tr>
			</table>
		</div>
		<div>
			<table>
				<tr>
					<td>Nom Employé :</td>
					<td>${banqueModel.compte.employe.nomEmploye}</td>
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
					<c:if test="${ not empty banqueModel.typeOperation }">
					
					    <tr>
					    	<td>Montant:</td>
					    	<td><f:input path="montant"/> </td>
					    	<td><f:errors path="montant"></f:errors> </td>
					    </tr>
					 		<c:if test="${ banqueModel.typeOperation == 'VIREMENT' }">
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
			<table class="tab1">
				<tr>
					<th>Numéro</th><th>Type</th><th>Date</th><th>Montant</th>
				</tr>
				<c:forEach items="${ banqueModel.operations }" var="x">
				<tr>
					<td>${ x.numeroOperation }</td>
					<td>${ x }</td>
					<td>${ x.dateOperation }</td>
					<td>${ x.montant }</td>
				</tr>
				</c:forEach>
			</table>
	</div>

</div>
	</c:if>
	
	
	
</body> 




























</html>