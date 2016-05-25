define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputBaseVM','ojs/ojknockout',
  'ojs/ojcomponents', 'ojs/ojbutton', 'ojs/ojselectcombobox'],
function(oj, ko, $, bku, inputBase)
{
    function ComboboxModel(){
        self = this;
        inputBase.call(this);
        var bk = new bku();
        self.module("ComboboxModel");
        self.ojName("ojCombobox");
        self.id("#combobox");
        self.idList(["#combobox","#depcombobox"]);
        self.multiple = ko.observable(false);
        self.disabledItem = ko.observable(false);
        self.isDep = ko.observable(false);
        self.init = false;
        self.subIdList(['oj-combobox-arrow','oj-combobox-drop','oj-combobox-input',
          'oj-combobox-remove','oj-combobox-results','oj-combobox-selection','oj-listitem']);


        // Can only set handlers after ids set
        self.setHandlers();

        self.value = ko.observableArray();
        self.depvalue = ko.observableArray([""]);
        self.valueList = [[],["30"],["60"],["90"],["120"]];
        self.obsValueList = ko.observableArray([
            {value: '0', label: '0'},
            {value: '30',    label: '30'},
            {value: '70',   label: '70'},
            {value: '80',    label: '$80.00'},
            {value: '110',   label: '$110.00'}
        ]);
        self.groupData = ko.observableArray([
          {label: "Alaskan/Hawaiian Time Zone",
            children: [
              {value: "AK", label: "Alaska, USA"},
              {value: "HI", label: "Hawaii, USA (!@#$%^&*(<>,.;':) \/ [] {}"}
          ]},
          {label: "Pacific Time Zone",
            children: [
              {value: "CA", label: "California"},
              {value: "NV", label: "Nevada"},
              {value: "OR", label: "Oregon"},
              {value: "WA", label: "Washington"}
          ]}
        ]);

        self.groupDataWithKeys = ko.observableArray([
          {regions: "Central Time Zone",
            states: [
              {state_abbr: "SD", state_name: "South Dakota"},
              {state_abbr: "ND", state_name: "North Dakota"}
          ]},
          {regions: "Eastern Time Zone",
            states: [
              {state_abbr: "NC", state_name: "North Carolina"},
              {state_abbr: "SC", state_name: "South Carolina"},
              {state_abbr: "FL", state_name: "Florida"}
          ]}
        ]);

        self.triLevelGroupData = ko.observableArray([
          {regions: "Alaskan/Hawaiian Time Zone",
            states: [
              {state_abbr: "AK", state_name: "Alaska",
                cities: [
                  {city_abbr: "AN", city_name: "Anchorage"}
              ]},
              {state_abbr: "HI", state_name: "Hawaii",
                cities: [
                  {city_abbr: "HO", city_name: "Honolulu"},
                  {city_abbr: "HL", city_name: "Hilo"}
              ]}
          ]},
          {regions: "Pacific Time Zone",
            states: [
              {state_abbr: "CA", state_name: "California",
                cities: [
                  {city_abbr: "SF", city_name: "San Francisco"},
                  {city_abbr: "LA", city_name: "Los Angeles"}
              ]},
              {state_abbr: "NV", state_name: "Nevada",
                cities: [
                  {city_abbr: "LV", city_name: "Las Vegas"}
              ]},
              {state_abbr: "OR", state_name: "Oregon",
                cities: [
                  {city_abbr: "PL", city_name: "Portland"},
                  {city_abbr: "BD", city_name: "Bend"}
              ]},
              {state_abbr: "WA", state_name: "Washington",
                cities: [
                  {city_abbr: "ST", city_name: "Seattle"},
                  {city_abbr: "SK", city_name: "Spokane"}
              ]}
          ]}
        ]);

        self.test1 = ko.observable(
        [{"_value":"Internet Explorer","_label":"Internet Explorer","cccc":"some other XXX val"},
            {"_value":"Firefox","_label":"Firefox"},
            {"_value":"Chrome","_label":"Chrome"},
            {"_value":"Opera","_label":"Opera"},
            {"_value":"Safari","_label":"Safari"}]);

        self.parData = ko.observableArray([
            {value: undefined, label: " "},
            {value: 'Girls', label: 'Girls'},
            {value: 'Boys',    label: 'Boys'}
        ]);
        self.girlsData = ko.observableArray([
            {value: undefined, label: " "},
            {value: 'Emma', label: 'Emma'},
            {value: 'Clara', label: 'Clara'}
        ]);
        self.boysData = ko.observableArray([
            {value: undefined, label: " "},
            {value: 'Perry', label: 'Perry'},
            {value: 'Robert', label: 'Robert'}
        ]);

        self.keys1 = {label: 'regions', children: 'states', childKeys: {value: 'state_abbr', label: 'state_name'}};

        self.keys2 = {label: 'regions', children: 'states', childKeys: {value: 'state_abbr', label: 'state_name',
                            children: 'cities', childKeys: {value: 'city_abbr', label: 'city_name'}}}

        self.options = ko.observable(self.groupDataWithKeys());
        self.optionsKeys = ko.observable(self.keys1);
        self.paroptions = ko.observable(self.parData());
        self.depoptions = ko.observable([]);
        self.optionsList = ko.observableArray([self.obsValueList, self.groupData, self.groupDataWithKeys, self.triLevelGroupData]);
        self.optionsKeysList = ko.observableArray([null, null, self.keys1, self.keys2]);
        self.value = ko.observable(["30"]);

        self.toggleMultiple = function() {
            bk.cycle("multiple",bk.boolVals,self.readOnly);
            self.setAllOption("multiple", self.readOnly());
            self.setAllMethod("refresh");
        }

        self.toggleDisabledItem = function() {
            bk.cycle("disabledItem",bk.boolVals,self.disabledItem);
        }

        self.toggleOptions = function() {
            self.value("");
            bk.cycle("optionsKeys",self.optionsKeysList(),self.optionsKeys);
            bk.cycle("options",self.optionsList(),self.options);
            self.value("");
            // Test variations, changing options vs ko vars...
//            self.setAllOption("options", self.options());
//            self.setAllOption("optionsKeys", self.optionsKeys());
            self.refresh();
        }

        self.showOptions = function() {
            bk.msgLog("options: " + JSON.stringify(self.test1()));
/*            var index;
            for (index = 0; index < self.test1().length; ++index) {
                var myobj = self.test1()[index];
                bk.msgLog(index + ":" + JSON.stringify(myobj));
            }
*/
        }

        self.beforeExpandHandler = function() {
            bk.msgLog("ojCombobox before expanded");
        }

         self.addOption = function() {
            self.obsValueList.push({value: '31', label: '31'});
        }

        // override for combobox to support adding items
        self.valueChangeHandler = function (context, valueParam) {
            if (valueParam.option == "value") {
//               var valueObj = buildValueChange(valueParam);
//                bk.msgLog("Value Change: " + JSON.stringify(valueObj, null, 4));
                bk.msgLog("option change[value]: " + valueParam['value'] + " (from " + valueParam['previousValue'] + ")");
                  var newVal = valueParam.value;
                if (self.isDep()) {
                    bk.msgLog("isDep : " + newVal);
                    if (newVal == "Boys") {
                        bk.msgLog("switching to " + newVal);
                        self.depoptions(self.boysData());
                        self.depvalue([""]);
                    } else if (newVal == "Girls") {
                        bk.msgLog("switching to " + newVal);
                        self.depoptions(self.girlsData());
                        self.depvalue([""]);
                    }
                    // self.depoptions.valueHasMutated();
                    // self.setAllMethod("refresh");
                    if(self.init)
                        self.refresh();
                    return;
                }
                // check if it exists in the array
                var match = ko.utils.arrayFirst(self.obsValueList(), function (item) {
                    return item.value == newVal;
                });

                if (!match) {
                    if (newVal != "") {
                        self.obsValueList.push({value: newVal, label: newVal});
                        bk.msgLog("added item " + newVal);                        //code
                    }
                }
            } else if (valueParam['option'] == 'messages') {
                var newValue = valueParam['value'];
                var previousValue = valueParam['previousValue'];
                bk.msgLog("option change[message]: " +  newValue + " (from " + previousValue + ")");
            }
            else if (valueParam['option'] === "rawValue") {
              bk.msgLog("rawValue option change handler");
              var val = self.getOption(self.id(), "rawValue");
              bk.msgLog("rawValue = " + val);
            }


        };

        self.myCombobox =
          {component: 'ojCombobox', value: self.value,
                multiple : self.multiple,
                optionChange: self.valueChangeHandler,
                beforeExpand : self.beforeExpandHandler,
                rootAttributes: {style:'max-width:20em'}};

    oj.Logger.info("ComboboxModel created " + self.ojName());
    }

    ComboboxModel.prototype = Object.create(inputBase.prototype);
    ComboboxModel.prototype.constructor = ComboboxModel;
    return ComboboxModel;
});
