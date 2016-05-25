define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputBaseVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, inputBase)
{
    function RadiosetModel(){
        self = this;
        inputBase.call(this);
        var bk = new bku();
        self.module("RadiosetModel");
        self.ojName("ojRadioset");
        self.id("#radioset");
        self.idList(["#radioset"]);
        self.value("30");
        self.valueList = ["30","90","120"];
        self.focusId("#low");
        self.subIdList(['oj-radioset-inputs']);

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
            var locator = {'subId': 'oj-radioset-inputs'};
            // value = bk.get(self.id(),"getNodeBySubId", locator);
            var radios = $('#radioset').ojRadioset("getNodeBySubId",locator);
            self.printItemsArray("getNodeBySubId(" + ko.toJSON(locator) + ")",radios);
            return radios;
            }



    oj.Logger.info("RadiosetModel created " + self.ojName());
    }

    RadiosetModel.prototype = Object.create(inputBase.prototype);
    RadiosetModel.prototype.constructor = RadiosetModel;

    return RadiosetModel;
});
