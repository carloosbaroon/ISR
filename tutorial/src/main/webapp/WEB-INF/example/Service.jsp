<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Service</title>
</head>

<body>
<s:form action="Consumir">
    <s:textfield name="cantidad"/>
    <s:submit value="cantidad"/>
</s:form>


<s:form action="imc">
    <label><b>Calcular IMC</b></label><br>
    
    <s:textfield name="edad"/>
    <s:textfield name="altura"/>
    <s:textfield name="peso"/>
    <s:submit value="imc"/>
</s:form>

</body>
</html>
