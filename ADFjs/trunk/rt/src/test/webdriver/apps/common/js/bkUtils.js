define(['jquery','knockout','ojs/ojcore','ojs/ojcomponents'],
function($,ko,oj)
{ 
    function bkUtils() {
       var self = this;
       self.boolVals = [false,true];
       var dbg = ko.observable("true");
       self._logMsg = null;
       
       setInterval(function(){
              if (self._logMsg != null)
              {
                var curText = $("#msg-log").val();
                $("#msg-log").val(self._logMsg + "\n" + curText);
                if (dbg) {
                    console.log(self._logMsg);
                }
                self._logMsg = null;
              }
            },1000);
       
       self.msgLog = function(msg) {
            if (self._logMsg == null) {
                self._logMsg = msg;
            } else
                self._logMsg = msg + "\n" + self._logMsg;
       }
       
       self.clearLog = function() {
            $("#msg-log").val("");
       }
       
       self.version = function() {
            console.log("Version 1.0");
       };
       
       self.getReqParam = function(name){
            var param = (new RegExp('[?&]' + encodeURIComponent(name)+'=([^&]*)')).exec(location.search);
            if(param != undefined) {
                var val = decodeURIComponent(param[1]);
                // self.msgLog("Req Param: " + name + " = " + val);
                return val;
            }
            return undefined;
        }
        
       self.showNextVal = function(valArray,curVal) {
            var nextVal = 0;  // if curVal not in Array, skip to first item
            var arrayVal;            
            for ( var i = 0;  i < valArray.length; i++ ) {
                if (ko.isObservable(valArray[i])) {                    
                    arrayVal = valArray[i]();                    
                } else {
                    arrayVal = valArray[i];
                }
                if (curVal instanceof Array) {
                    if (arrayVal == null) {
                        nextVal = 0;
                    } else
                    if (curVal[0] == arrayVal[0]) {
                        if(i + 1 == valArray.length)
                            nextVal = 0;
                        else
                            nextVal = 1 + i;
                        break;
                        }                
                } else {
                    if (curVal == arrayVal) {
                        if(i + 1 == valArray.length)
                            nextVal = 0;
                        else
                            nextVal = 1 + i;
                        break;
                        }
                    }
                }
            if (ko.isObservable(valArray[nextVal])) {
                return valArray[nextVal]();
            }
            return valArray[nextVal];
        };

        // cycleFunc("rendered",vm.enableValues,vm.rendered);
        // self.toggleRendered = function() { cycleFunc("rendered",vm.enableValues,vm.rendered()));
        //        self.langDir(bk.cycleFunc("dir",self.dirValues,self.langDir())); }
        self.cycleFunc = function(property, valArray, curVal) {
            // self.msgLog("Old " + property + " = " + curVal);            
            nextVal = self.showNextVal(valArray,curVal);            
            self.msgLog("Setting val = " + nextVal + " (was = " + curVal + ")");
            return nextVal;
        };

        // Easiest way to use this to cycle values for observables from array
        //   bk.cycle("dir",self.dirValues,self.langDir);
        self.cycle = function(property,valArray,curValFunc) {
            curValFunc(self.cycleFunc(property,valArray,curValFunc()));
        }

        
        self.cycleNextTable = function(tableId, property, valArray) {
            var curVal = $(tableId).ojTable("option",property);
            var nextVal = self.cycleFunc(property,valArray,curVal);            
            $(tableId).ojTable("option",property,nextVal);
            return $(tableId).ojTable("option",property);
        }

        
        self.setAllOption = function(ojName, idList, optionName, optionValue) {
            for (var i = 0, len = idList.length; i < len; ++i) {
                var id = idList[i];
                self.setOption(ojName, id,optionName,optionValue);
//                self.msgLog("ojName = " + ojName + " id = " + id + " optionName = " + optionName + " optionValue = " + optionValue);
            }
        }

        self.setAllMethod = function(ojName, idList, method,value1) {
            for (var i = 0, len = idList.length; i < len; ++i) {
                var id = idList[i];
                self.get(ojName,id,method,value1);
            }
        }
                
        
        self.setAllJq = function(idList, method, value1, value2, value3) {
            for (var i = 0, len = idList.length; i < len; ++i) {
                var jid = idList[i];
                self.jq(jid,method, value1, value2, value3);
            }
        }
        
        self.jq = function(jid, method, value1, value2, value3)
        {
        var jqstring;
        if(value1 == undefined) {
            jqstring = "$('" + jid + "')." + method + "()";            
        } else if (value2 == undefined) {
            jqstring = "$('" + jid + "')." + method + "('" + value1 + "')";            
        } else if (typeof value2 === 'string') {
            jqstring = "$('" + jid + "')." + method + "('" + value1 + "')";            
        } else if (value3 == undefined) {
            jqstring = "$('" + jid + "')." + method + "('" + value1 + "'," + value2 + ")";
        } else {
            jqstring = "$('" + jid + "')." + method + "('" + value1 + "'," + value2 + "," + value3 + ")";
        }
//        self.msgLog(jqstring);
        return eval(jqstring);
        }

        
        // aliases for get
        self.setMethod = function(ojName, id, method,value1) { return get(ojName, id,method,value1); }
        self.getMethod = function(ojName, id, method,value1) { return get(ojName, id,method,value1); }
        self.get = function(ojName, id, method, value1)
        {
            var eString;
            if (value1 == undefined) {               
               eString = "$('" + id + "')." + ojName + "('" + method + "')";
            } else {
               eString = "$('" + id + "')." + ojName + "('" + method + "'," + value1 + ")";
            }
           oj.Logger.info("eString = " + eString);
            return eval(eString);
        }
        
        self.getOption = function(ojName, id, optionName)
        {
            return eval("$(id)." + ojName + "('option', optionName)");            
        }
        
        self.setOption = function(ojName, id, optionName, optionValue)
        {
//            self.msgLog("setOption(" + ojName + "," + id + "): " + optionName + " to " + optionValue);
            eval("$(id)." + ojName + "('option', optionName, optionValue)");
        }

        self.disableButton = function(buttonId) {            
            $(buttonId).prop('disabled',true);
        }

        self.enableButton = function(buttonId) {            
            $(buttonId).prop('disabled',false);
        }
        
        self.printArray = function(title,array) {
            self.msgLog(title + ":");
            var index;
            for (index = 0; index < array.length; ++index) {
                self.msgLog(index + ":" + array[index]);
            }
        }
        
    }
    return bkUtils;
});