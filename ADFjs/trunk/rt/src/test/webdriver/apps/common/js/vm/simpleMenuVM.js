define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'ojs/ojknockout', 'ojs/ojcomponents'],
function(oj, ko, $, bku)
{
function MenuModel() {
    var self = this;
    var bk = new bku();
    
    self.menuLauncher= ko.observable("(Not launched yet)");        
    self.selectedMenuItem = ko.observable("(None selected yet)");
    
    self.menuItemSelect = function( event, ui ) {
        bk.msgLog("menu item selected = " + ui.item.children("a").text());
        self.selectedMenuItem(ui.item.children("a").text());
        // console.log("menuItemSelect");
    };
    
    // Only works with UI menu opens; if api opens, look at menuVM
    self.menuOpen = function( event, ui ) {
        bk.msgLog("menu opened by = " + ui.openOptions.launcher.attr("id"));
        self.menuLauncher(ui.openOptions.launcher.attr("id"));
    };

    
    oj.Logger.info("simpleMenuModel created")
}
return MenuModel;
});