define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputBaseVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, inputBase)
{
    function InputNumberModel(){
        self = this;
        inputBase.call(this);
        var bk = new bku();
        self.module("InputNumberModel");
        self.ojName("ojInputNumber");
        self.id("#inputnumber-id");
        self.idList(["#inputnumber-id"]);
        self.value("30");
        self.valueList = ["","30","60"];
        self.subIdList(['oj-inputnumber-input','oj-inputnumber-down','oj-inputnumber-up']);

        // Can only set handlers after ids set
        self.setHandlers();

        self.togglePattern = function() {
            alert("Option pattern not supported for ojInputNumber");
        };

        self.setInputNumberValueTo30 = function() {
            bk.msgLog("setting value to 30");
            self.setOption(self.id(),"value", 30 );
        };
        self.setMinTo50 = function()
        {
            bk.msgLog("setting min to 30");
            self.setOption(self.id(),"min", 50 );
        }
        self.setMaxTo80 = function()
        {
            self.setOption(self.id(),"max", 80 );
        }
        self.setMinAndMaxToNull = function()
        {
            self.setOption(self.id(),"min", null);
            self.setOption(self.id(),"max", null);
        }
    oj.Logger.info("InputNumberModel created " + self.ojName());
    }

    InputNumberModel.prototype = Object.create(inputBase.prototype);
    InputNumberModel.prototype.constructor = InputNumberModel;

    return InputNumberModel;
});
