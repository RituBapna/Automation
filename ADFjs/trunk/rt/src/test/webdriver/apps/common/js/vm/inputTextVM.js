define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputBaseVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, inputBase)
{
    function InputTextModel(){
        self = this;
        inputBase.call(this);
        var bk = new bku();
        self.module("InputTextModel");
        self.ojName("ojInputText");
        self.id("#text-input");
        self.idList(["#text-input"]);
        self.subIdList(['oj-inputtext-input']);


        // Can only set handlers after ids set
        self.setHandlers();

    oj.Logger.info("InputTextModel created " + self.ojName());
    }

    InputTextModel.prototype = Object.create(inputBase.prototype);
    InputTextModel.prototype.constructor = InputTextModel;
    return InputTextModel;
});
