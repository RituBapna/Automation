/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojradioset', 'ojs/ojtable', 'ojs/ojnavigationlist', 'ojs/ojbutton', 'ojs/ojselectcombobox', 'ojs/ojinputtext', 'ojs/ojgauge', 'ojs/ojinputnumber'], function (oj, ko, $)// this callback gets executed when all required modules are loaded
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

        var self = this;

        //self.datasource = ko.observableArray(data);
        //  self.dataSource = new oj.ArrayTableDataSource(self.dataObservableArray, {idAttribute: 'id'});
        self.selectedListItem = ko.observable("None");
        self.currentFocusedItem = ko.observable("None");
        self.displayValue = ko.observable('all');
        self.currItemValue = ko.observable();
        self.selectedValue = ko.observable(null);
        self.edgeValue = ko.observable('start');
        self.drillModeValue = ko.observable('sliding');
        self.expandedValueStr = ko.observable('auto');
        self.expandedValueArray = ko.observableArray();
        self.rootLabelValue = ko.observable('');
        self.expandedComboboxValue = ko.observableArray();
        self.menuDisplayThresholdValue = ko.observable(0);

        self.expandedValue = ko.computed(function () {
            oj.Logger.info("expandedValue calculated")
            if (self.expandedValueArray().length > 0) {
            oj.Logger.info("expandedValue array value set: " +self.expandedValueArray()[0])
                return self.expandedValueArray();               
            }
            else 
            return self.expandedValueStr();
        })

        self.expandedComboOptionChangeHandler = function (event, data) {
            if (data.option == 'value') {
                self.expandedValueArray(data.value);
                oj.Logger.info("expandedValue set by combobox: " + data.value[0]);
            }
        }

        self.currentExpandedValue = ko.observable();
        self.getCurrentExpandedValue = function () {
            var array = $('#navList').ojNavigationList("getExpanded");
            String
            val = "";
            for (var i = 0;i < array.length;i++) {
                val = val + array[i] +",";
            }
            self.currentExpandedValue(val);
        }
        self.itemOnly = function (context) {
            oj.Logger.info("**********itemOnly: FOR KEY : " + context.key);
            oj.Logger.info("itemOnly: index : " + context.index);
            oj.Logger.info("itemOnly: depth : " + context.depth);
            oj.Logger.info("itemOnly: parent Key : " + context.parentKey);
            oj.Logger.info("itemOnly: leaf : " + context.leaf);
            oj.Logger.info("itemOnly: ******");
            return context.leaf;
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
            $('#navList').ojNavigationList("option", "currentItem", "zoomout");
            // $('#navList').ojNavigationList("refresh");
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