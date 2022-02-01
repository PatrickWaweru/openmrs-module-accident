<%-- <div class="ke-page-content">
<div>

    <button id="refresh">
        <img src="${ ui.resourceLink("kenyaui", "images/glyphs/ok.png") }" /> Add Department
    </button>

    <br/>
    <br/>
</div>
<br/>
<table>
  <tr>
   <th>Depatment Id</th>
   <th>Name</th>
   <th>Description</th>
  </tr>
  <% if (departments) { %>
     <% departments.each { %>
      <tr>
        <td>${ ui.format(it.id) }</td>
        <td>${ ui.format(it.name) }</td>
        <td>${ ui.format(it.description) }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="2">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>

</div> --%>

<div class="ke-page-content">
        <h2>Departments</h2>
        <div class="clear"></div>

        <% if (departments) { %>
        <table class="simple-table">
            <tr>
                <th align="left" width="20%">Depatment Id</th>
                <th align="left" width="30%">Name</th>
                <th align="left" width="50%">Description</th>
            </tr>
        <% departments.each { department -> %>

        <tr>
                <td>${department.id}</td>

                <td>${department.nane ?: ""}</td>

                <td>${department.description ?: ""}</td>
                <%-- <td class="column-five">
                    <button
                            onclick="ui.navigate('${ ui.pageLink("facilityreporting", "reportDatasetsList", [reportId: report.id, returnUrl: ui.thisUrl() ])}')">
                        <img src="${ui.resourceLink("kenyaui", "images/glyphs/view.png")}"/> View Datasets
                    </button>
                </td> --%>
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



