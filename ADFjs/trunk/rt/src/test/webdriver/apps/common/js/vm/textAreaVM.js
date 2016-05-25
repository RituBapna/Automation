define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'inputBaseVM','ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, inputBase)
{
    function TextAreaModel(){
        self = this;
        inputBase.call(this);
        var bk = new bku();
        self.module("TextAreaModel");
        self.ojName("ojTextArea");
        self.id("#text-input");
        self.idList(["#text-input"]);
        self.subIdList(['oj-textarea-input']);


        // Can only set handlers after ids set
        self.setHandlers();

        self.resize=ko.observable("none");
        self.resizeList=["none","both","horizontal","vertical"];

        self.toggleResize = function() {
            bk.cycle("resize",self.resizeList,self.resize);
            self.jq("focus");
        }


    oj.Logger.info("TextAreaModel created " + self.ojName());
    }


    TextAreaModel.prototype = Object.create(inputBase.prototype);
    TextAreaModel.prototype.constructor = TextAreaModel;
    return TextAreaModel;
});
