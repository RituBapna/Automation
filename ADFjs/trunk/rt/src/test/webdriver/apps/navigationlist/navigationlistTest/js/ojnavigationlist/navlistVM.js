/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojradioset', 'ojs/ojtable', 'ojs/ojarraytabledatasource', 'ojs/ojnavigationlist', 'ojs/ojbutton', 'ojs/ojselectcombobox', 'ojs/ojinputtext', 'ojs/ojgauge'], function (oj, ko, $)// this callback gets executed when all required modules are loaded
{
    oj.Logger.option("level", oj.Logger.LEVEL_INFO);

    function getId(panel) {
        if (panel) {
            return panel.attr("id");
        }
        return null;
    }

    function getOptionChangeEventData(data) {
        var dataStr = "";
        for (d in data) {
            dataStr += " " + d + ": " + data[d] + ";";
        }
        return dataStr;
    }

    function getEventData(eventType, data) {

        var dataStr = "";
        switch (eventType) {
            case 'ojoptionchange':
                dataStr = getOptionChangeEventData(data);
                break;
            default :
                dataStr = getOptionChangeEventData(data);
                break;
        }
        /*
                 for (d in data) {
                 dataStr += " " + d + ": " + getId(data[d]) + ";";
                 }
                 */
        return dataStr;
    }

    function ViewModel() {
        var emptyIntialList = [];
        var data = [{name : 'Settings', details : 'This is the detail for Settings.', 'rating' : 3, id : 'settings'},{name : 'Tools', details : 'This is the detail for Tools.', 'rating' : 4, id : 'tools', hasChildren : true, children : toolsData},{name : 'Base', details : 'This is the detail for Base.', 'rating' : 5, id : 'base'},{name : 'Environment', details : 'This is the detail for Environment.', disabled : 'true', 'rating' : 1, id : 'environment'},{name : 'Security', details : 'This is the detail for Security.', 'rating' : 2, id : 'security'}];
        var toolsData = [{name : 'Automation', details : 'This is the detail for Automation.', id : 'automtion'},{name : 'Development', details : 'This is the detail for Development.', id : 'development'},{name : 'Code Coverage', details : 'This is the detail for Code Coverage.', id : 'coverage'}]
        var self = this;
        //  self.dataObservableArray_template = ko.observableArray(emptyIntialList);
        //  self.dataSource_template = new oj.ArrayTableDataSource(self.dataObservableArray_template, {idAttribute: 'id'});
        //   self.dataObservableArray_renderer = ko.observableArray(emptyIntialList);
        //  self.dataSource_renderer = new oj.ArrayTableDataSource(self.dataObservableArray_renderer, {idAttribute: 'id'});
        self.dataObservableArray = ko.observableArray(emptyIntialList);
        self.dataSource = new oj.ArrayTableDataSource(self.dataObservableArray, 
        {
            idAttribute : 'id'
        });
        self.selectedListItem = ko.observable("None");
        self.currentFocusedItem = ko.observable("None");
        self.displayValue = ko.observable('all');
        self.currItemValue = ko.observable();
        self.selectedValue = ko.observable();
        self.edgeValue = ko.observable('start');
        self.drillModeValue = ko.observable('none');
        self.expandedValue = ko.observableArray();
        self.rootLabelValue = ko.observable('');
        self.initializeNavList = function () {
            self.dataObservableArray(data);
            self.selectedValue('security');
            // $('#navList').ojNavigationList("refresh");
        };

        self.removeLastNavListItem = function () {
            self.dataObservableArray.pop();
            // $('#navList').ojNavigationList("refresh");
        };

        self.addNavListItem = function () {
            self.dataObservableArray.push( {
                name : 'New Item', id : 'newItem'
            });
            // $('#navList').ojNavigationList("refresh");
        };

        //renderer
        //
        self.currentRendererReturnType = ko.observable('dom');
        self.navListItemRenderer_str = function (itemContext) {
            oj.Logger.info("renderer str: " + itemContext.data.id + "   Index: " + itemContext.index);
            //oj.Logger.info("renderer str: " + itemContext.component);
            //oj.Logger.info("renderer: " + itemContext.data.id)
            return itemContext.data.id;
        }

        self.navListItemRenderer_dom = function (itemContext) {
            var dom;
            oj.Logger.info("renderer dom: " + itemContext.component);
            oj.Logger.info("itemContext: key: " + itemContext.key + " , index: " + itemContext.index);
            //var item = "<li  id=\"" + itemContext.data.id + "\" class=\"oj-navigationlist-item-element oj-navigationlist-item oj-default\"> <a href=\"#\" ><span class=\"oj-navigationlist-item-icon  demo-icon-font demo-library-icon\"></span>" + itemContext.data.name + "</a></li>";
            if (itemContext.data.hasChildren)

                var item = "<a href=\"#\" ><span class=\"oj-navigationlist-item-icon  demo-icon-font demo-library-icon\"></span>" + itemContext.data.name + "</a>";
            else 
            var item = "<a href=\"#\" ><span class=\"oj-navigationlist-item-icon  demo-icon-font demo-library-icon\"></span>" + itemContext.data.name + "</a>";
            oj.Logger.info("renderer: " + item);
            $(itemContext.parentElement).attr("id", itemContext.data.id);
            if (itemContext.data.disabled)  $(itemContext.parentElement).addClass("oj-disabled");
            $(itemContext.parentElement).append(item);
           
        }

        self.currentRenderer = ko.computed(function () {
            switch (self.currentRendererReturnType()) {
                case 'str':
                    return self.navListItemRenderer_str;
                    break;
                case 'dom':
                    return self.navListItemRenderer_dom;
                    break;
                default :
                    self.navListItemRenderer_str;
                    break;
            }
        });

        self.currentExpandedValue = ko.observable();
        self.getCurrentExpandedValue = function () {
            var array = $('#navList').ojNavigationList("getExpanded");
            String
            val = "";
            for (var i = 0;i < array.length;i++) {
                val = val + array[i] + ",";
            }
            self.currentExpandedValue(val);
        }

        self.itemOnly = function (context) {
            return true;
            //  return context['leaf'];
        };
        //Menu 
        //
        self.selectedMenuItem = ko.observable("(None selected yet)");
        self.menuLauncher = ko.observable("(Not launched yet)");
        self.menuItemToAdd = ko.observable();

        self.menuItemSelect = function (event, ui) {
            self.selectedMenuItem(ui.item.children("a").text());
        };

        self.menuOpen = function (event, ui) {
            self.menuLauncher(ui.openOptions.launcher.attr("id"));

            var item = " <li id=\"" + self.menuItemToAdd() + "\" class=\"oj-menu-item\" role=\"presentation\"><a href=\"#\"><span class=\"oj-menu-item-icon demo-icon-font demo-home-icon\"></span>" + self.menuItemToAdd() + "</a></li>";
            oj.Logger.info("Added Menu Item : " + item);
            $("#myMenu").append(item);

        };
        //Methods
        self.testVetoable = ko.observable(false);
        self.getCurrentFocusedNavListItem = function () {
            var currItem = $('#navList').ojNavigationList("option", "currentItem");
            self.currentFocusedItem(currItem);
        };

        self.currentSelectionOptionValue = ko.observable();
        self.getCurrentSelectedNavListItem = function () {
            var val = $('#navList').ojNavigationList("option", "selection");
            self.currentSelectionOptionValue(val);
        };

        self.optionData = ko.observable();
        self.optionValues = ko.computed(function () {
            return self.optionData();
        });
        self.clearOptionData = function () {
            this.optionData("");
        };
        self.appendOptionData = function (data) {
            self.optionData("");
            var dataStr = "";
            for (d in data) {
                dataStr += " " + d + ": " + data[d] + ";";
            }
            self.optionData(dataStr);
        }
        self.getAllOptions = function () {
            var data = $("#navList").ojNavigationList("option");
            self.appendOptionData(data);
        }

        self.setCurrentNavListItem = function () {
            $('#navList').ojNavigationList("option", "currentItem", "security");
            //  $('#navList').ojNavigationList("refresh");
        };

        //events
        self.evtData = ko.observable();
        self.clearLogMsg = function () {
            this.evtData("");
        };

        self.logMsg = ko.computed(function () {
            return self.evtData();
        });

        self.appendData = function (data) {
            var ss = this.evtData();
            ss = ss ? ss + "\n" : "";
            this.evtData(ss + data);
        }
        self.onSelect = function (event, data) {
            // if (this == event.target)
            self.appendData("OPTION : " + event.type + ": " + event.target.id + " data {" + getEventData(event.type, data) + "}");
            if (data.option === 'selection') {
                self.selectedListItem(data.value);
            }
            if (data.option === 'currentitem') {
                self.currentFocusedItem(data.value);
            }
        };

        self.onNavListEvent = function (event, data) {
            //if (this == event.target)
            self.appendData("OPTION : " + event.type + ": " + event.target.id + " data {" + getEventData(event.type, data) + "}");
            if (event.type == "ojbeforecurrentitem") {
                $("#myMenu #" + self.menuItemToAdd()).remove();
                self.menuItemToAdd(data.key);
            }

            if (self.testVetoable())
                return false;//veto
            else 
                return true;
        };

        self.detailValueChanged = function (event, data) {
            oj.Logger.info("DetailValue Changed")
        }

        $("#navList").on("ojbeforeselect ojbeforeexpand ojoptionchange ojbeforecollapse ojexpand ojcollapse ojdestroy ojcreate ojbeforecurrentitem", function (event, data) {
            //  if (this == event.target)
            self.appendData("ON : " + event.type + ": " + event.target.id + " data {" + getEventData(event.type, data) + "}");

            if (self.testVetoable())
                return false;//veto
            else 
                return true;
        });

    }

    $(document).ready(function () {
        var vm = new ViewModel();
        ko.applyBindings(vm, document.getElementById('navlistdemo'));
    })
});