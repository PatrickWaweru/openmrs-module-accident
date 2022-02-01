<%
    ui.decorateWith("kenyaemr", "standardPage", [layout: "sidebar" ])
    def menuItems = [
            [ label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to home", href: ui.pageLink("kenyaemr", "userHome") ]
    ]
    ui.includeCss("kenyaemraccident", "table_formatter.css")
    //${ ui.includeFragment("kenyaemraccident", "departmentsList") }
    def url = "http://localhost:8080/kenyaemr/afyastat/afyastatHome.page"
%>

<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Navigation", items: menuItems ]) }
    </div>
</div>

<div class="ke-page-content">
    <div>
        ${ ui.includeFragment("kenyaemraccident", "departmentsList", [returnUrl: url]) }
    </div>
</div>