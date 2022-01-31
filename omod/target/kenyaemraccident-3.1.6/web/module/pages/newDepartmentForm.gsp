<%
    ui.decorateWith("kenyaemr", "standardPage", [layout: "sidebar" ])
    def menuItems = [
            [ label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to Departments", href: ui.pageLink("kenyaemraccident", "kenyaemraccident") ]
    ]
    ui.includeCss("kenyaemraccident", "table_formatter.css")
%>

<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Navigation", items: menuItems ]) }
    </div>
</div>

<div class="ke-page-content">
    <div>
        ${ ui.includeFragment("kenyaemraccident", "newDepartment", [returnUrl: ui.pageLink("kenyaemraccident", "kenyaemraccident")]) }
    </div>
</div>