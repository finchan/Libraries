<%--
  User: Xavier
  Date: 2017/10/9
  Time: 15:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form v.s. Payload</title>
</head>
<body>
    <form name="formdata" id="formdata">
        <input type="text" name="formdatainput" id="formdatainput">
        <input type="button" value="Form Data" id="formdataBtn">
        <br>
        <input type="text" name="payloadData" id="payloadData">
        <input type="button" value="Payload Data" id="payloadDataBtn">
    </form>
    <script>
        function dataSubmit( evt ) {
            var xhr = new XMLHttpRequest( );
            xhr.open("POST", "${pageContext.request.contextPath}/lab/ajax/formdataservlet/formdata");
            xhr.onreadystatechange = function() {
                if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
                    console.log("Data is sent successfully!");
                }
            }
            if(evt.target.id == "formdataBtn") {
                //Form Data
                xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            }
            xhr.send("formdatainput=formDataValue");
        }

        var formdataBtn = document.getElementById("formdataBtn");
        formdataBtn.addEventListener("click", dataSubmit, false);
        var payloadDataBtn = document.getElementById("payloadDataBtn");
        payloadDataBtn.addEventListener("click", dataSubmit, false);
    </script>
</body>
</html>
