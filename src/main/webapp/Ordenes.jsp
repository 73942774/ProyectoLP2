

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" 
              integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="color:blue;font-size:300%;text-align:center;">ORDENES</h1>
            <div class="col-sm-8">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>IDVENTA</th>
                            <th>IDCLIENTE</th>
                            <th>IDEMPLEADO</th>
                            <th>NUMERO DE SERIE</th>
                            <th>FECHA DE LA VENTA</th>
                            <th>MONTO</th>
                            <th>ESTADO</th>
     
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="v" items="${ventas}">
                            <tr>
                                <td>${v.getId()}</td>
                                <td>${v.getIdcliente()}</td>
                                <td>${v.getIdempleado()}</td>
                                <td>${v.getNumserie()}</td>
                                <td>${v.getFecha()}</td>
                                <td>${v.getMonto()}</td>
                                <td>${v.getEstado()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
            </div>
        </div>       
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>

