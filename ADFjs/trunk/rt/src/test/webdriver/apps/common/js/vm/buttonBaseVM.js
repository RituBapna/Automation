define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'compBaseVM', 'ojs/ojknockout', 
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku, compBase)
{
    function ButtonModel(){
        self = this;
        compBase.call(this);
        self.module("ButtonModel");
        self.chroming = ko.observable("");
        self.chromingList = ["full","half","outlined",""];

        var bk = new bku();
        
        self.printMessageArray = function(msgArray) {
            for (i = 0; i < msgArray.length; i++) 
            {
              msg = msgArray[i]; 
              bk.msgLog("Message: " + msg['summary'] + " display: " + msg['display']);
            }
        }
        
       self.toggleChroming = function() {
            bk.msgLog("chroming = " + self.chroming());        
            bk.cycle("chroming",self.chromingList,self.chroming);        
            self.setAllOption("chroming", self.chroming());      
        }

        
        self.showMessages = function() {
            self.get(self.id(),'showMessages');
            bk.msgLog(self.id() + ".showMessages ");
            // put focus on self.id() to popup message box?
            self.focus();
        }
                
        self.valueChangeHandler = function (context, valueParam) {
            if (valueParam.option == "value") {
//               var valueObj = buildValueChange(valueParam);
//                bk.msgLog("Value Change: " + JSON.stringify(valueObj, null, 4));
                bk.msgLog("option change[value]: " + valueParam['value'] + " (from " + valueParam['previousValue'] + ")");
            } else if (valueParam['option'] == 'messages') {
                var newValue = valueParam['value'];
                var previousValue = valueParam['previousValue'];
                bk.msgLog("option change[message]: " +  newValue + " (from " + previousValue + ")");                    
            }
        };
                
      self.optionChangeHandler = function(event, ui) {
          if (ui.option === "checked") {
              bk.msgLog("checked " + ui.value);
              // do stuff...
          }
      };                
                
        function buildValueChange (valueParam) {
            var valueObj = {};     
            valueObj.previousValue = valueParam.previousValue;
            valueObj.value = valueParam.value;     
            return valueObj;
        }
                                
    oj.Logger.info("ButtonModel created " + self.ojName());
    }
 
    ButtonModel.prototype = Object.create(compBase.prototype);
    ButtonModel.prototype.constructor = ButtonModel;

    return ButtonModel;
});