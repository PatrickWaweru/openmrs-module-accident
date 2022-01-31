<%  
    ui.decorateWith("kenyaui", "panel", [heading: "Departments List", frameOnly: true])
%>

<style>
table {
    width: 100%;
}

/**
thead tr {
    display: block;
}

thead, tbody {
    display: block;
}
tbody.scrollable {
    height: 400px;
    overflow-y: auto;
}
th, td {
    padding: 5px;
    text-align: left;
    height: 30px;
    border-bottom: 1px solid #ddd;
}
tr:nth-child(even) {background-color: #f2f2f2;}
**/
</style>

<div class="ke-panel-content">

        <% if (departments) { %>
        <table class="simple-table">
            <tr>
                <th align="left" width="20%">Depatment Id</th>
                <th align="left" width="30%">Name</th>
                <th align="left" width="50%">Description</th>
            </tr>
        <% departments.each { department -> %>

        <tr>
                <td align="left" width="20%">${department.id}</td>

                <td align="left" width="30%">${department.name ?: ""}</td>

                <td align="left" width="50%">${department.description ?: ""}</td>
        </tr>
        <% } %>
        </table>
        <% } else { %>

        <div>No departments available</div>
        <% } %>
    <div style="padding-top: 10px">
            <button class="addConfiguration" name="addConfiguration" type="button"
                    onclick="ui.navigate('${ ui.pageLink("kenyaemraccident", "newDepartmentForm", [ returnUrl: ui.thisUrl() ])}')">
                <img src="${ui.resourceLink("kenyaui", "images/glyphs/add.png")}"/> Add Department
            </button>
    </div>


</div>



