define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'buttonBaseVM', 'ojs/ojknockout', 'ojs/ojcomponents'],
function(oj, ko, $,bku, bbVM)
{ 
    function ButtonModel(){
        self = this;
        bbVM.call(this);
        var bk = new bku();
        self.module("ButtonModel");        
        self.ojName("ojButton");
        self.id("#button");
        self.idList(["#button","#anchor","#inputButton","#submit", "#reset",
                     "#buttonIcon1","#buttonIcon2","#buttonIcon3","#buttonIcon4","#buttonIcon5"]);
                
        // Can only set handlers after ids set        
        self.setHandlers();
                
        self.clickedButton = ko.observable("(None clicked yet)");
        self.buttonClick = function(data, event){
            event = event || data; // for click handlers that are bound via both KO and JQ, which pass event as 2nd and 1st param, respectively
            bk.msgLog("clickedButton = " + self.clickedButton());
            self.clickedButton(event.currentTarget.id);
            bk.msgLog("button clicked = " + event.currentTarget.id);            
            return true;
        }
        self.enableReset = function() {
            $("#reset").ojButton("option", "disabled", false);
            $("#reset").ojButton("refresh");
            bk.msgLog("#reset button re-enabled");
        }
    oj.Logger.info("ButtonModel created" + "[id = " + self.id() + "]");
    }
    
    ButtonModel.prototype = Object.create(bbVM.prototype);
    ButtonModel.prototype.constructor = ButtonModel;

    return ButtonModel;
});