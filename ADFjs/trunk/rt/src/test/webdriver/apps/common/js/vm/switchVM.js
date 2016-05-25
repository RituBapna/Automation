define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputBaseVM','ojs/ojknockout',
  'ojs/ojswitch'],
function(oj, ko, $, bku, inputBase)
{
    function SwitchModel(){
        self = this;
        inputBase.call(this);
        var bk = new bku();
        self.module("SwitchModel");
        self.ojName("ojSwitch");
        self.id("#switch");
        self.idList(["#switch"]);
        self.value(true);
        self.valueList = [true,false];
        self.focusId("#switch");
        self.subIdList(['oj-switch-track','oj-switch-thumb']);


        // Can only set handlers after ids set
        self.setHandlers();

        self.togglePattern = self.notSupported;
        self.toggleConverter = self.notSupported;
        self.togglePlaceholder = self.notSupported;

        self.focus = function() {
            bk.msgLog(self.focusId() + ".focus");
            bk.jq(self.focusId(),"focus");
        }

        self.getNodeBySubId = function() {
            var locator = {'subId': 'oj-switch-track'};
            var locator2 = {'subId': 'oj-switch-thumb'};
            var track = $('#switch').ojSwitch('getNodeBySubId',locator);
            var thumb = $('#switch').ojSwitch('getNodeBySubId',locator2);
            bk.msgLog("getNodeBySubId(" + ko.toJSON(locator) + ") : " + track.outerHTML);
            bk.msgLog("getNodeBySubId(" + ko.toJSON(locator2) + ") : " + thumb.outerHTML);
            return {'track':track,'thumb':thumb};
            }

        self.getNodeBySubIdString = function(id) {
            var locator = {'subId': id };
            console.log(locator);
            var value = self.get(self.id(),'getNodeBySubId',locator);
            bk.msgLog("getNodeBySubId(" + ko.toJSON(locator) + ") : " + value.outerHTML);
            return {locator: value} ;
        }


    oj.Logger.info("SwitchModel created " + self.ojName());
    }

    SwitchModel.prototype = Object.create(inputBase.prototype);
    SwitchModel.prototype.constructor = SwitchModel;

    return SwitchModel;
});
