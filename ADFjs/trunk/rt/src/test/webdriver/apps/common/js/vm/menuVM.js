define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputBaseVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, inputBase)
{
    function MenuModel(){
        self = this;
        inputBase.call(this);
        var bk = new bku();
        self.module("MenuModel");
        self.ojName("ojMenu");
        self.id("#myMenu0");
        // We won't toggle menus, but list all so that setAllOptions applies to popups
        self.idList(["#myMenu0","#myMenu", "#myMenu2"]);
        self.initialFocus = ko.observable("none");
        self.initialFocusList = ["none","menu","firstItem"];
        self.selectedMenuItem = ko.observable("(None selected yet)");
        self.menuLauncher= ko.observable("(Not launched yet)");
        self.launcherList = ["#menulink1","#menulink2"];
        self.launcher = ko.observable("#menulink1");
        self.submenuposition = ko.observable({ "my": "start top", "at": "end top" });
        self.position = ko.observable({ "my": "start top", "at": "start bottom" });
        self.positionList = [{ "my": "start top", "at": "start bottom" },{ "my": "end center", "at": "end center" }, { "my": "start top", "at": "end top" }];

        // Can only set handlers after ids set
        self.setHandlers();

        self.addHandlers = function() {
          self.setAllJq("on","ojopen ojclose ojbeforeopen ojselect",self.onLogEvent);
        }

        self.addHandlers();

        self.menuItemSelect = function( event, ui ) {
            bk.msgLog("menu item selected = " + ui.item.children("a").text());
            self.selectedMenuItem(ui.item.children("a").text());
        };

        self.menuOpen = function( event, ui ) {
            // If opened by UI, the launcher is object
            // If opened by api call, launcher is string
            if (typeof ui.openOptions.launcher === 'string') {
                bk.msgLog("menu opened by = " + ui.openOptions.launcher);
                self.menuLauncher(ui.openOptions.launcher);
            } else {
                bk.msgLog("menu opened by = " + ui.openOptions.launcher.attr("id"));
                self.menuLauncher(ui.openOptions.launcher.attr("id"));
            }
        };


        self.toggleInitialFocus = function() {
            // $( ".selector" ).ojMenu( "option", "openOptions.initialFocus", "none" );
            bk.cycle("openOptions.initialFocus",self.initialFocusList,self.initialFocus);
            self.setAllOption("openOptions.initialFocus", self.initialFocus());
            // self.refresh();
        };

        self.togglePosition = function() {
            bk.cycle("openOptions.position",self.positionList,self.position);
            self.setAllOption("openOptions.position", self.position());
            // self.refresh();
        };

        self.toggleSubmenuPosition = function() {
            bk.cycle("submenuOpenOptions.position",self.positionList,self.submenuposition);
            self.setAllOption("submenuOpenOptions.position", self.submenuposition());
            // self.refresh();
        };


        self.toggleLauncher = function() {
            // $( ".selector" ).ojMenu( "option", "openOptions.initialFocus", "none" );
            bk.cycle("launcher",self.launcherList,self.launcher);
            // self.refresh();
        };


        self.openMenu = function() {
            $( "#myMenu2" ).ojMenu( "open", null, {launcher: self.launcher()} );
        };

        self.menuItems = [
            {id: 'new',    label: 'New',        disabled: false},
            {id: 'open',   label: 'Open',       disabled: false},
            {id: 'save',   label: 'Save',       disabled: false},
            {id: 'saveas', label: 'Save As...', disabled: false},
            {id: 'print',  label: 'Print...',   disabled: true}
        ];

    oj.Logger.info("MenuModel created " + self.ojName());
    }

    MenuModel.prototype = Object.create(inputBase.prototype);
    MenuModel.prototype.constructor = MenuModel;

    return MenuModel;
});
