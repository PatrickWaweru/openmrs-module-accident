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
    <p >${today ?: ""}</p>
</div>



