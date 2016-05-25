define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputBaseVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, inputBase)
{
    function InputPasswordModel(){
        self = this;
        inputBase.call(this);
        var bk = new bku();
        self.module("InputPasswordModel");
        self.ojName("ojInputPassword");
        self.id("#text-input");
        self.idList(["#text-input"]);
        self.subIdList(['oj-inputpassword-input']);


        // Can only set handlers after ids set
        self.setHandlers();

    oj.Logger.info("InputPasswordModel created " + self.ojName());
    }

    InputPasswordModel.prototype = Object.create(inputBase.prototype);
    InputPasswordModel.prototype.constructor = InputPasswordModel;

    return InputPasswordModel;
});
