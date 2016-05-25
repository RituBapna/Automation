define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputBaseVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, inputBase)
{
    function CheckboxsetModel(){
        self = this;
        inputBase.call(this);
        var bk = new bku();
        self.module("CheckboxsetModel");
        self.ojName("ojCheckboxset");
        self.id("#checkboxset");
        self.idList(["#checkboxset"]);
        self.value(["30"]);
        self.valueList = [["30"],["90"],["120"]];
        self.focusId("#low");
        self.subIdList(['oj-checkboxset-inputs']);


        // Can only set handlers after ids set
        self.setHandlers();

        self.togglePattern = self.notSupported;
        self.toggleConverter = self.notSupported;
        self.togglePlaceholder = self.notSupported;
        self.toggleReadOnly = self.notSupported;

        self.focus = function() {
            bk.msgLog(self.focusId() + ".focus");
            bk.jq(self.focusId(),"focus");
        }

        self.getNodeBySubId = function() {
            var locator = {'subId': 'oj-checkboxset-inputs'};
            // value = bk.get(self.id(),"getNodeBySubId", locator);
            var radios = $('#checkboxset').ojCheckboxset("getNodeBySubId",locator);
            self.printItemsArray("getNodeBySubId(" + ko.toJSON(locator) + ")",radios);
            return radios;
            }




    oj.Logger.info("CheckboxsetModel created " + self.ojName());
    }

    CheckboxsetModel.prototype = Object.create(inputBase.prototype);
    CheckboxsetModel.prototype.constructor = CheckboxsetModel;
    return CheckboxsetModel;
});
