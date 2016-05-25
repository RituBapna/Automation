define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'compBaseVM', 'ojs/ojknockout', 'ojs/ojinputnumber', 'ojs/ojbutton'], function (oj, ko, $, bku, compBase) {

    function InputBaseModel() {
        self = this;
        compBase.call(this);
        self.module("InputBaseModel");
        var bk = new bku();

        self.value = ko.observable("30");
        self.prevValue = ko.observable();
        self.rawValue = ko.observable();
        self.valueList = ["", "30", "60"];
        self.readOnly = ko.observable(false);
        self.required = ko.observable(false);
        self.title = ko.observable("tooltip1");
        self.titleList = ["", "tooltip1", "tooltip2"];
        self.pattern = ko.observable("");
        self.patternList = ["", "[a-zA-Z0-9]{3,}", "[a-zA-Z0-9]{,3}"];
        self.help = ko.observable("");
        self.helpList = ["", {"definition" : "help1", "source" : "../../common/help1.html"}];
        self.placeholder = ko.observable("");
        self.placeholderList = ["", "placeholder1", "placeholder2"];
        self.messages = ko.observable("");
        self.messagesList = ["", {'summary' : 'Error Summary', 'detail' : 'Error Detail'}];
        self.reqtrans = ko.observable( {
            'required' :  {
                hint : 'custom: enter a number', messageSummary : 'custom: \'{label}\' is Required', messageDetail : 'custom: please enter a valid value for \'{label}\''
            }
        });
        self.tracker = ko.observable();
        self.changedEnabled = ko.computed(function() 
            { return self.value() == self.prevValue(); } ); 
        
        self.value.subscribe(function(previousValue){
           self.prevValue(previousValue);
        }, this, "beforeChange");
        
        self.setHelp = function () {
            self.setOption(self.id(), "help", 
            {
                "definition" : "fill out the name", "source" : "http:\\www.oracle.com"
            });
        }

        // Have two converters, one simple grouping and second currency
        self.baseConverter = oj.Validation.converterFactory(oj.ConverterFactory.CONVERTER_TYPE_NUMBER).createConverter( {
            "maximumFractionDigits" : 0, "minimumFractionDigits" : 0, "minimumIntegerDigits" : 2, "style" : "decimal", "useGrouping" : true
        });

        // Initialize converter instance using currency options
        var options = {
            style : 'currency', 'currency' : 'USD', maximumFractionDigits : 2
        };
        var numberConverterFactory = oj.Validation.converterFactory("number");
        self.salaryConverter = numberConverterFactory.createConverter(options);

        self.converterList = ko.observableArray([null, self.baseConverter, self.salaryConverter]);
        self.converter = ko.observable(self.baseConverter);

        self.toggleConverter = function () {
            // bk.msgLog(self.converterList());
            bk.cycle("converter", self.converterList(), self.converter);
            self.setOption(self.id(), "converter", self.converter());
        }

        self.validateSearchText = {
            validate : function () {
                var currentValue = $($("#combobox").data('oj-ojCombobox').getNodeBySubId( {
                    subId : "oj-combobox-input"
                })).val();
                try {
new RegExp(currentValue);
                }
                catch (error) {
                    throw new Error("Invalid regular expression");
                }
                return true;
            }
        }

        self.numValidator = [{type : 'numberRange', options :  {hint :  {min : 'Enter a value greater than {min}'},min : 100}}];

        self.smallnumValidator = [{type : 'numberRange', options :  {hint :  {min : 'Enter a value less than {max}'},max : 100}}];

        self.validator = ko.observable(self.numValidator);
        self.validatorsList = [null, self.numValidator, self.smallnumValidator];
        self.toggleValidator = function () {
            bk.cycle("validators", self.validatorsList, self.validator);
            self.setOption(self.id(), "validators", self.validator());
        }

        self.toggleReadOnly = function () {
            bk.cycle("readOnly", bk.boolVals, self.readOnly);
            self.setAllOption("readOnly", self.readOnly());
        }

        self.toggleRequired = function () {
            bk.cycle("required", bk.boolVals, self.required);
            self.setAllOption("required", self.required());
        }

        self.togglePattern = function () {
            bk.cycle("pattern", self.patternList, self.pattern);
            self.setAllOption("pattern", self.pattern());
            // self.setAllMethod("refresh");
        }

        self.toggleHelp = function () {
            bk.cycle("help", self.helpList, self.help);
            self.setAllOption("help", self.help());
        }

        self.togglePlaceholder = function () {
            bk.cycle("placeholder", self.placeholderList, self.placeholder);
            self.setAllOption("placeholder", self.placeholder());
        }

        self.toggleMessages = function () {
            // this should add a message each time invoked.
            var msgs = [];
            msgs.push( {
                'summary' : 'Error Summary', 'detail' : 'Error Detail'
            });
            self.setAllOption("messagesCustom", msgs);
            var val = self.getOption(self.id(), "messagesCustom");
            bk.msgLog("Set custom message, now retrieving it:");
            self.printMessageArray(val);

        }

        self.toggleValue = function () {
            bk.cycle("value", self.valueList, self.value);
            self.setAllOption("value", self.value());
        }

        self.toggleTitle = function () {
            bk.cycle("title", self.titleList, self.title);
            self.setAllOption("title", self.title());
        }

        self.isValid = function () {
            var value = self.get(self.id(), 'isValid');
            bk.msgLog(self.id() + ".isValid = " + value);

        }
        self.validate = function () {
            var value = self.get(self.id(), 'validate');
            bk.msgLog(self.id() + ".validate = " + value);

        }

        self.reset = function () {
            self.setAllMethod("reset");
            //            var value = self.get(self.id(),'reset');
            //            bk.msgLog(self.id() + ".reset = " + value);
        }

        self.getMessages = function () {
            var value = self.getOption(self.id(), 'messagesHidden');
            bk.msgLog(self.id() + ".messagesHidden = " + value);
            self.printMessageArray(value);
            value = self.getOption(self.id(), 'messagesShown');
            bk.msgLog(self.id() + ".messagesShown = " + value);
            self.printMessageArray(value);
        }

        self.printMessageArray = function (msgArray) {
            for (i = 0;i < msgArray.length;i++) {
                msg = msgArray[i];
                bk.msgLog("Message: " + msg['summary'] + " display: " + msg['display']);
            }
        }

        self.showMessages = function () {
            self.get(self.id(), 'showMessages');
            bk.msgLog(self.id() + ".showMessages ");
            // put focus on self.id() to popup message box?
            self.focus();
        }

        self.valueChangeHandler = function (context, valueParam) {
            if (valueParam.option == "value") {
                //               var valueObj = buildValueChange(valueParam);
                //                bk.msgLog("Value Change: " + JSON.stringify(valueObj, null, 4));
                bk.msgLog("option change[value]: " + valueParam['value'] + " (from " + valueParam['previousValue'] + ")");
            }
            else if (valueParam['option'] == 'messages') {
                var newValue = valueParam['value'];
                var previousValue = valueParam['previousValue'];
                bk.msgLog("option change[message]: " + newValue + " (from " + previousValue + ")");
            }
            else if (valueParam['option'] === "rawValue") {
              bk.msgLog("rawValue option change handler");
              var val = self.getOption(self.id(), "rawValue");
              bk.msgLog("rawValue = " + val);
            }
        };

        function buildValueChange(valueParam) {
            var valueObj = {
            };
            valueObj.previousValue = valueParam.previousValue;
            valueObj.value = valueParam.value;
            return valueObj;
        }

        oj.Logger.info("InputBaseModel created " + self.ojName());
    }

    InputBaseModel.prototype = Object.create(compBase.prototype);
    InputBaseModel.prototype.constructor = InputBaseModel;

    return InputBaseModel;
});