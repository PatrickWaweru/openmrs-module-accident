<%  
    ui.decorateWith("kenyaui", "panel", [heading: "Add Department", frameOnly: true])

    def departmentName = [
            [
                    [object: department, property: "name", label: "Department Name *"]

            ]
    ]

    def departmentDescription = [
            [

                    [object: department, property: "description", label: "Department Description *"]

            ]
    ]

%>

<div class="ke-panel-content">

<form id="department-form" method="post" action="${ui.actionLink("kenyaemraccident", "newDepartment", "saveDepartment")}">

    <div class="ke-panel-content">

        <div class="ke-form-globalerrors" style="display: none"></div>

        <div class="ke-form-instructions">
            <strong>*</strong> indicates a required field
        </div>

        <fieldset>
            <legend>Department</legend>
            <table>
                <tr>
                    <td class="ke-field-label">Name</td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="name" rows="5" cols="80" value="${(department.name != null) ? department.name : ""}" />
                    </td>
                </tr>
                <tr>
                    <td class="ke-field-label">Description</td>
                </tr>
                <tr>
                    <td>
                        <textarea name="description" rows="5" cols="80">${(department.description != null) ? department.description : ""}</textarea>
                    </td>
                </tr>
            </table>
        </fieldset>

        <div class="ke-panel-footer">
            <button type="submit">
                <img src="${ui.resourceLink("kenyaui", "images/glyphs/ok.png")}"/> Create Department
            </button>

            <button type="button" class="cancel-button"><img
                    src="${ui.resourceLink("kenyaui", "images/glyphs/cancel.png")}"/> Cancel</button>

        </div>
    </div>
</form>

</div>

<script type="text/javascript">

    //On ready
    jQuery(function () {


        jQuery('#department-form .cancel-button').click(function () {
            ui.navigate('${ config.returnUrl }');
        });
        kenyaui.setupAjaxPost('department-form', {
            onSuccess: function (data) {
                if (data.id) {
                    <% if (config.returnUrl) { %>
                        ui.navigate('${ config.returnUrl }');
                    <% } else { %>
                        ui.navigate('kenyaemraccident', 'kenyaemraccident', {id: id});
                    <% } %>
                } else {
                    kenyaui.notifyError('Saving department was successful, but with unexpected response');
                }
            }
        });


    }); // end of jQuery initialization bloc


</script>
