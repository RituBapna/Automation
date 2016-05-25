/**
 * Header module
 */
define(['knockout'
], function (ko) {
    /**
     * The view model for the header module
     */

    function HeaderViewModel() {
        var self = this;

        // 
        // Dropdown menu states
        // 
        self.selectedMenuItem = ko.observable("(None selected yet)");

        self.menuItemSelect = function (event, ui) {
            switch (ui.item.attr("id")) {
                case "open":
                    this.selectedMenuItem(ui.item.children("a").text());
                    break;
                default:
                    // this.selectedMenuItem(ui.item.children("a").text());
            }
        };

        // Data for application name
        var appName = {
            "id": "qs",
            "name": "JET QuickStart Basic"
        };

        // 
        // Toolbar buttons
        // 
        var toolbarData = {
            // user name in toolbar
            "userName": "versha.pradhan@oracle.com",
            "toolbar_buttons": [
                {
                    "label": "toolbar_button1",
                    "iconClass": "demo-palette-icon-24",
                    "url": "#"
                },
                {
                    "label": "toolbar_button2",
                    "iconClass": "demo-gear-icon-16",
                    "url": "#"
                }
            ],
            // Data for global nav dropdown menu embedded in toolbar.
            "global_nav_dropdown_items": [
                {"label": "preferences",
                    "url": "#"
                },
                {"label": "help",
                    "url": "#"
                },
                {"label": "about",
                    "url": "#"
                },
                {"label": "sign out",
                    "url": "#"
                }
            ]
        };

        self.appId = appName.id;
        self.appName = appName.name;

        self.userName = ko.observable(toolbarData.userName);
        self.toolbarButtons = toolbarData.toolbar_buttons;
        self.globalNavItems = toolbarData.global_nav_dropdown_items;

    }
    return HeaderViewModel;
});