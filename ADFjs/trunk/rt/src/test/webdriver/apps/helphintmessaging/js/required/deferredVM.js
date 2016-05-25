

define(['knockout', 'jquery', 'ojs/ojcore'], function (ko, $, oj) {

    var deferredAppViewModel = function () {
        var self = this;
        oj.Logger.option("level", oj.Logger.LEVEL_INFO);

        //all components
        self.readOnlyValue = ko.observable(false);
        self.disabledValue = ko.observable(false);
        self.placeHolderValue = ko.observable("Some Place holder Value");
        self.titleValue = ko.observable('<html><b>My title value</b></html>');
        //  self.titleValue = ko.observable('My title value');
        self.helpDefinitionValue = ko.observable("Some Help text");
        self.helpSourceValue = ko.observable("http://oracle.com");
        self.converterHintDisplayValue = ko.observableArray(['placeholder']);
        self.validatorHintDisplayValue = ko.observableArray(['notewindow']);
        self.titleDisplayValue = ko.observableArray(['notewindow']);
        self.messagesDisplayValue = ko.observableArray(['notewindow']);
        self.messagingDisplayOptionsValue = ko.computed(function () {
            return {messages: self.messagesDisplayValue(), converterHint: self.converterHintDisplayValue(),
                validatorHint: self.validatorHintDisplayValue(), title: self.titleDisplayValue()};
        })

        self.invalidcomponents = ko.observable();
        self.text2OptionChangeValue = ko.observable("");

        self.changePlaceHolderValue = function (d, e) {
            if (self.placeHolderValue() == 'Some Place holder Value')
                self.placeHolderValue('changed place holder value');
            else
                self.placeHolderValue('place holder value');
        }

        self.changeTitleValue = function (d, e) {
            if (self.titleValue() == '<html><b>My title value</b></html>') {
                // $("text2").ojInputText("option", "title", "<html><b>Changed My title value</b></html>");
                self.titleValue('<html><b>Changed My title value</b></html>');
                //  oj.ComponentBinding.deliverChanges();
            }
            else {
                //  $("text2").ojInputText("option", "title", "<html><b>My title value</b></html>");
                self.titleValue('<html><b>My title value</b></html>');
                // oj.ComponentBinding.deliverChanges();
            }
        }

        self.changeHelpDefinitionValue = function (d, e) {
            if (self.helpDefinitionValue() == 'Some Help text')
                self.helpDefinitionValue('changed Some Help text');
            else
                self.helpDefinitionValue(' Help text');
        }

        self.changeHelpSourceValue = function (d, e) {
            if (self.helpSourceValue() == 'http://oracle.com')
                self.helpSourceValue('http://goggle.com');
            else
                self.helpSourceValue('http://oracle.com');
        }

        self.toggleReadOnly = function (d, e) {
            if (self.readOnlyValue()) {
                self.readOnlyValue(false);
                oj.ComponentBinding.deliverChanges();
            }
            else {
                self.readOnlyValue(true);
                oj.ComponentBinding.deliverChanges();
            }
        }

        self.toggleDisabled = function (d, e) {
            if (self.disabledValue()) {
                self.disabledValue(false);
                oj.ComponentBinding.deliverChanges();
            }
            else {
                self.disabledValue(true);
                oj.ComponentBinding.deliverChanges();
            }
        }

        self.customMsgTypeValue = ko.observable(['WARNING']);
        self.customMsgOfSelectedType = ko.computed(function () {
            var msgType = self.customMsgTypeValue()[0];
            var s = "custom " + msgType + " Summary ";
            var d = "custom " + msgType + " Detail ";
            var msgs = [];
            msgs.push(new oj.Message(s, d, oj.Message.SEVERITY_LEVEL[msgType]));
            return msgs;
        })

        //used for adding mixed messages
        var summary1 = "custom Error Summary : 1";
        var detail1 = "custom Error Detail : 1";
        var summary2 = "custom Info Summary : 2";
        var detail2 = "custom Info Detail : 2";
        self.customMsgs = ko.observableArray([]);
        self.customMsgs().push(new oj.Message(summary1, detail1, oj.Message.SEVERITY_LEVEL['ERROR']));
        self.customMsgs().push(new oj.Message(summary2, detail2, oj.Message.SEVERITY_LEVEL['INFO']));



        self.showDeferredMessages = function (d, e) {

            var trackerObj = ko.utils.unwrapObservable(self.invalidcomponents);
            trackerObj.showMessages();

        }

        self.showDeferredMessagesAndSetFocus = function (d, e) {

            var trackerObj = ko.utils.unwrapObservable(self.invalidcomponents);
            trackerObj.showMessages();
            var ret = trackerObj.focusOnFirstInvalid()

        }

        self.setFocusOnFirstInvalid = function (d, e) {

            var trackerObj = ko.utils.unwrapObservable(self.invalidcomponents);

            var ret = trackerObj.focusOnFirstInvalid()

        }

        self.setCustomMessagesOnAllComponent = function (e, d) {


            $("#text1").ojInputText("option", "messagesCustom", self.customMsgOfSelectedType());
            $("#text2").ojInputText("option", "messagesCustom", self.customMsgOfSelectedType());
            $("#text3").ojInputText("option", "messagesCustom", self.customMsgOfSelectedType());

            $("#spinner1").ojInputNumber("option", "messagesCustom", self.customMsgOfSelectedType());
            $("#spinner2").ojInputNumber("option", "messagesCustom", self.customMsgOfSelectedType());
            $("#spinner3").ojInputNumber("option", "messagesCustom", self.customMsgOfSelectedType());

            $("#select").ojSelect("option", "messagesCustom", self.customMsgOfSelectedType());
            $("#grpdata").ojSelect("option", "messagesCustom", self.customMsgOfSelectedType());

            $("#checkboxSetId").ojCheckboxset("option", "messagesCustom", self.customMsgOfSelectedType());
            $("#radiosetSetBasicDemoId").ojRadioset("option", "messagesCustom", self.customMsgOfSelectedType());

            $("#combobox1").ojCombobox("option", "messagesCustom", self.customMsgOfSelectedType());

            $("#text-area").ojTextArea("option", "messagesCustom", self.customMsgOfSelectedType());
            $("#resizeBoth").ojTextArea("option", "messagesCustom", self.customMsgOfSelectedType());

            $("#password").ojInputPassword("option", "messagesCustom", self.customMsgOfSelectedType());
            $("#password1").ojInputPassword("option", "messagesCustom", self.customMsgOfSelectedType());

            $("#dateTime").ojInputDate("option", "messagesCustom", self.customMsgOfSelectedType());
            $("#dateTime2").ojInputDateTime("option", "messagesCustom", self.customMsgOfSelectedType());
            $("#dateTime3").ojInputTime("option", "messagesCustom", self.customMsgOfSelectedType());

        }

        self.setMixedCustomMessagesOnAllComponent = function (e, d) {


            $("#text1").ojInputText("option", "messagesCustom", self.customMsgs());
            $("#text2").ojInputText("option", "messagesCustom", self.customMsgs());
            $("#text3").ojInputText("option", "messagesCustom", self.customMsgs());

            $("#spinner1").ojInputNumber("option", "messagesCustom", self.customMsgs());
            $("#spinner2").ojInputNumber("option", "messagesCustom", self.customMsgs());
            $("#spinner3").ojInputNumber("option", "messagesCustom", self.customMsgs());

            $("#select").ojSelect("option", "messagesCustom", self.customMsgs());
            $("#grpdata").ojSelect("option", "messagesCustom", self.customMsgs());

            $("#checkboxSetId").ojCheckboxset("option", "messagesCustom", self.customMsgs());
            $("#radiosetSetBasicDemoId").ojRadioset("option", "messagesCustom", self.customMsgs());

            $("#text-area").ojTextArea("option", "messagesCustom", self.customMsgs());
            $("#resizeBoth").ojTextArea("option", "messagesCustom", self.customMsgs());

            $("#password").ojInputPassword("option", "messagesCustom", self.customMsgs());
            $("#password1").ojInputPassword("option", "messagesCustom", self.customMsgs());

            $("#dateTime").ojInputDate("option", "messagesCustom", self.customMsgs());
            $("#dateTime2").ojInputDateTime("option", "messagesCustom", self.customMsgs());
            $("#dateTime3").ojInputTime("option", "messagesCustom", self.customMsgs());

        }

        self.resetAllMessages = function (e, d) {
            $("#text1").ojInputText("reset");
            $("#text2").ojInputText("reset");
            $("#text3").ojInputText("reset");
            $("#spinner1").ojInputNumber("reset");
            $("#spinner2").ojInputNumber("reset");
            $("#spinner3").ojInputNumber("reset");

            $("#select").ojSelect("reset");
            $("#grpdata").ojSelect("reset");

            $("#checkboxSetId").ojCheckboxset("reset");
            $("#radiosetSetBasicDemoId").ojRadioset("reset");

            $("#text-area").ojTextArea("reset");
            $("#resizeBoth").ojTextArea("reset");

            $("#password").ojInputPassword("reset");
            $("#password1").ojInputPassword("reset");

            $("#dateTime").ojInputDate("reset");
            $("#dateTime2").ojInputDateTime("reset");
            $("#dateTime3").ojInputTime("reset");
        }

        self.optionChangeListener = function (e, d) {
            oj.Logger.info("optionChange for ID: " + e.target.id + ", option that has changed: " + d.option);
            var pvlen, vlen;

            if (d.previousValue)
                pvlen = d.previousValue.length;
            if (d.value)
                vlen = d.value.length;
            oj.Logger.info("previousValue length : " + pvlen + ",  value length; " + vlen);
            oj.Logger.info("_________________________________");


        }

        self.clearText2LogMsg = function () {
            self.text2OptionChangeValue("");
        };

        self.optionChangeListenerText2 = function (e, d) {
            oj.Logger.info("optionChangeListenerText2: optionChange for ID: " + e.target.id + ", option that has changed: " + d.option);

            var val = self.text2OptionChangeValue();
            if (d.option == "messagesShown") {
                val += "OptionChange Event for : " + d.option + ", previous Value lenth: " + d.previousValue.length + ",  current Value length: " + d.value.length + "\n";
                val += "messagesShown Previous Value: ";
                for (var i = 0; i < d.previousValue.length; i++) {
                    val += "[" + i + ": severity: " + d.previousValue[i].severity + ", summary: " + d.previousValue[i].summary + ", detail: " + d.previousValue[i].detail + ", ";
                }
                 val +="\n";
                val += "messagesShown Current Value: ";
                for (var i = 0; i < d.value.length; i++) {
                    val += "[" + i + ": severity: " + d.value[i].severity + ", summary: " + d.value[i].summary + ", detail: " + d.value[i].detail + ", ";
                }
                 val +="\n";
            }
            else if (d.option == "messagesHidden") {
                val += "OptionChange Event for : " + d.option + ", previous Value lenth: " + d.previousValue.length + ",  current Value length: " + d.value.length + "\n";
                val += "messagesHidden Previous Value: ";
                for (var i = 0; i < d.previousValue.length; i++) {
                    val += "[" + i + ": severity: " + d.previousValue[i].severity + ", summary: " + d.previousValue[i].summary + ", detail: " + d.previousValue[i].detail + ", ";
                }
                 val +="\n";
                val += "messagesHidden Current Value: ";
                for (var i = 0; i < d.value.length; i++) {
                    val += "[" + i + ": severity: " + d.value[i].severity + ", summary: " + d.value[i].summary + ", detail: " + d.value[i].detail + ", ";
                }
                 val +="\n";
            }
            else if (d.option == "messagesCustom") {
                val += "OptionChange Event for : " + d.option + ", previous Value lenth: " + d.previousValue.length + ",  current Value length: " + d.value.length + "\n";
                val += "messagesCustom Previous Value: ";
                for (var i = 0; i < d.previousValue.length; i++) {
                    val += "[" + i + ": severity: " + d.previousValue[i].severity + ", summary: " + d.previousValue[i].summary + ", detail: " + d.previousValue[i].detail + ", ";
                }
                val +="\n";
                val += "messagesCustom Current Value: ";
                for (var i = 0; i < d.value.length; i++) {
                    val += "[" + i + ": severity: " + d.value[i].severity + ", summary: " + d.value[i].summary + ", detail: " + d.value[i].detail + ", ";
                }

                val +="\n";
            }
            else {
                val += "OptionChange Event for : " + d.option + ", previous Value: " + d.previousValue + ",  current Value: " + d.value + "\n";
            }
            self.text2OptionChangeValue(val)
        }

        self.setText2ToInvalidValueAndSetFocus = function (d, e) {

            var trackerObj = ko.utils.unwrapObservable(self.invalidcomponents);
            // trackerObj.showMessages();
            $("#text2").ojInputText("option", "value", "xxx");
            $("#text3").ojInputText("option", "value", "yyy");
            $("#text2").ojInputText("validate");
            $("#text3").ojInputText("validate");
            var ret = trackerObj.focusOnFirstInvalid()

        }

        self.areThereInvalidCompoents = ko.observable();
        self.checkInvalid = function (d, e) {

            var trackerObj = ko.utils.unwrapObservable(self.invalidcomponents);
            var hasInvalidComponents = ko.utils.unwrapObservable(trackerObj["invalidShown"]);
            self.areThereInvalidCompoents(hasInvalidComponents);
        }

        self.areThereDeferredInvalidCompoents = ko.observable();
        self.checkDeferredInvalid = function (d, e) {

            var trackerObj = ko.utils.unwrapObservable(self.invalidcomponents),
                    hasInvalidDeferredComponents = ko.utils.unwrapObservable(trackerObj["invalidHidden"]);
            self.areThereDeferredInvalidCompoents(hasInvalidDeferredComponents);
        }


        //inputtext
        self.textValue = ko.observable();
        self.textValue2 = ko.observable();
        self.isText1Valid = ko.observable();
        self.isText2Valid = ko.observable();
        self.isText3Valid = ko.observable();

        self.equalToTextValue = {
            /**
             * validates the value against the value stored in the password observable
             * @param {type} value
             * @returns {Boolean}
             */
            validate: function (value)
            {
                var compareTo = self.textValue.peek();
                if (!value && !compareTo)
                    return true;
                else if (value !== compareTo)
                {
                    throw new Error('Value must match');
                }
                return true;
            }
        };

        self.getMessagesUsingOptionText2 = function (e, d) {

            var msgHidden = $("#text2").ojInputText("option", "messagesHidden");
            var msgShown = $("#text2").ojInputText("option", "messagesShown");
            var msgCustom = $("#text2").ojInputText("option", "messagesCustom");
        }

        self.setMessagesUsingOptionText2 = function (e, d) {
            $("#text2").ojInputText("option", "messagesHidden", "hiddenMessage set using API");
            $("#text2").ojInputText("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#text2").ojInputText("option", "messagesHidden");
            var msgShown = $("#text2").ojInputText("option", "messagesShown");
            var msgCustom = $("#text2").ojInputText("option", "messagesCustom");
        }

        //setNodeBySubId is not supported for messaging
        self.testGetNodeBySubIdValidatorHint = function (e, d) {

            var node = $("#text2").ojInputText("getNodeBySubId", {subId: 'oj-messaging-popup-validator-hint'});

        }

        self.getMessagesUsingOptionText3 = function (e, d) {

            var msgHidden = $("#text3").ojInputText("option", "messagesHidden");
            var msgShown = $("#text3").ojInputText("option", "messagesShown");
            var msgCustom = $("#text3").ojInputText("option", "messagesCustom");
        }

        self.setMessagesUsingOptionText3 = function (e, d) {
            $("#text3").ojInputText("option", "messagesHidden", "hiddenMessage set using API");
            $("#text3").ojInputText("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#text3").ojInputText("option", "messagesHidden");
            var msgShown = $("#text3").ojInputText("option", "messagesShown");
            var msgCustom = $("#text3").ojInputText("option", "messagesCustom");
        }

        //inputnumber
        self.minvalue = ko.observable(-10);
        self.maxvalue = ko.observable(10);
        // self.spinnervalue = ko.observable(10);
        self.spinnervalue = ko.observable();

        self.spinnerstep = ko.observable(10);
        self.spinnervalrequired = ko.observable(true);

        self.isSpinner1Valid = ko.observable();
        self.isSpinner2Valid = ko.observable();
        self.isSpinner3Valid = ko.observable();

        self.setSpinnerValueToUndefined = function (d, e) {
            d.spinnervalue(undefined);
            oj.ComponentBinding.deliverChanges();
            //   $("#spinner2").ojInputNumber("option", "value", undefined);
        }

        self.setSpinnerValueToNull = function (d, e) {
            d.spinnervalue("");
            oj.ComponentBinding.deliverChanges();
            // $("#spinner3").ojInputNumber("option", "value", "");
            // $("#spinner2").ojInputNumber("option", "value", "");
        }

        self.setSpinnerValueToNaN = function (d, e) {
            d.spinnervalue(Number.NaN);
            oj.ComponentBinding.deliverChanges();
            // $("#spinner2").ojInputNumber("option", "value", Number.NaN);
            // $("#spinner3").ojInputNumber("option", "value", Number.NaN);
        }
        self.setSpinnerValueToNegativeInfinity = function (d, e) {
            d.spinnervalue(-Number.MAX_VALUE * 2);
            oj.ComponentBinding.deliverChanges();
            // $("#spinner2").ojInputNumber("option", "value", -Number.MAX_VALUE*2);
            // $("#spinner3").ojInputNumber("option", "value", -Number.MAX_VALUE*2);
        }

        self.setSpinnerValueToInfinity = function (d, e) {
            d.spinnervalue(Number.MAX_VALUE * 2);
            oj.ComponentBinding.deliverChanges();
            //  $("#spinner2").ojInputNumber("option", "value", Number.MAX_VALUE*2);
            //  $("#spinner3").ojInputNumber("option", "value", Number.MAX_VALUE*2);
        }
        self.disableSpinners = function (d, e) {

            $("#spinner2").ojInputNumber("option", "disabled", true);
            $("#spinner1").ojInputNumber("option", "disabled", true);
            $("#spinner3").ojInputNumber("option", "disabled", true);
        }

        self.enableSpinners = function (d, e) {

            $("#spinner2").ojInputNumber("option", "disabled", false);
            $("#spinner1").ojInputNumber("option", "disabled", false);
            $("#spinner3").ojInputNumber("option", "disabled", false);
        }

        self.getMessagesUsingOptionSpinner1 = function (e, d) {

            var msgHidden = $("#spinner1").ojInputNumber("option", "messagesHidden");
            var msgShown = $("#spinner1").ojInputNumber("option", "messagesShown");
            var msgCustom = $("#spinner1").ojInputNumber("option", "messagesCustom");
        }

        self.setMessagesUsingOptionSpinner1 = function (e, d) {
            $("#spinner1").ojInputNumber("option", "messagesHidden", "hiddenMessage set using API");
            $("#spinner1").ojInputNumber("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#spinner1").ojInputNumber("option", "messagesHidden");
            var msgShown = $("#spinner1").ojInputNumber("option", "messagesShown");
            var msgCustom = $("#spinner1").ojInputNumber("option", "messagesCustom");
        }

        self.getMessagesUsingOptionSpinner2 = function (e, d) {

            var msgHidden = $("#spinner2").ojInputNumber("option", "messagesHidden");
            var msgShown = $("#spinner2").ojInputNumber("option", "messagesShown");
            var msgCustom = $("#spinner2").ojInputNumber("option", "messagesCustom");
        }

        self.setMessagesUsingOptionSpinner2 = function (e, d) {
            $("#spinner2").ojInputNumber("option", "messagesHidden", "hiddenMessage set using API");
            $("#spinner2").ojInputNumber("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#spinner2").ojInputNumber("option", "messagesHidden");
            var msgShown = $("#spinner2").ojInputNumber("option", "messagesShown");
            var msgCustom = $("#spinner2").ojInputNumber("option", "messagesCustom");
        }

        self.getMessagesUsingOptionSpinner3 = function (e, d) {

            var msgHidden = $("#spinner3").ojInputNumber("option", "messagesHidden");
            var msgShown = $("#spinner").ojInputNumber("option", "messagesShown");
            var msgCustom = $("#spinner3").ojInputNumber("option", "messagesCustom");
        }

        self.setMessagesUsingOptionSpinner3 = function (e, d) {
            $("#spinner3").ojInputNumber("option", "messagesHidden", "hiddenMessage set using API");
            $("#spinner3").ojInputNumber("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#spinner3").ojInputNumber("option", "messagesHidden");
            var msgShown = $("#spinner3").ojInputNumber("option", "messagesShown");
            var msgCustom = $("#spinner3").ojInputNumber("option", "messagesCustom");
        }

        //select component
        self.selectval = ko.observableArray([]);
        self.browsers = ko.observableArray([
            {value: 'Internet Explorer', label: 'Internet Explorer'},
            {value: 'Firefox', label: 'Firefox'},
            {value: 'Chrome', label: 'Chrome'},
            {value: 'Opera', label: 'Opera'},
            {value: 'Safari', label: 'Safari'}
        ]);


        self.grpselectval = ko.observableArray([]);
        self.groupData = ko.observableArray([
            {value: '', label: ''},
            {label: "Alaskan/Hawaiian Time Zone",
                children: [
                    {value: "AK", label: "Alaska"},
                    {value: "HI", label: "Hawaii"}
                ]},
            {label: "Pacific Time Zone",
                children: [
                    {value: "CA", label: "California"},
                    {value: "NV", label: "Nevada"},
                    {value: "OR", label: "Oregon"},
                    {value: "WA", label: "Washington"}
                ]}
        ]);

        self.isSelectValid = ko.observable();
        self.isGrpdataValid = ko.observable();

        self.getMessagesUsingOptionSelect = function (e, d) {

            var msgHidden = $("#select").ojSelect("option", "messagesHidden");
            var msgShown = $("#select").ojSelect("option", "messagesShown");
            var msgCustom = $("#select").ojSelect("option", "messagesCustom");
        }

        self.setMessagesUsingOptionSelect = function (e, d) {
            $("#select").ojSelect("option", "messagesHidden", "hiddenMessage set using API");
            $("#select").ojSelect("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#select").ojSelect("option", "messagesHidden");
            var msgShown = $("#select").ojSelect("option", "messagesShown");
            var msgCustom = $("#select").ojSelect("option", "messagesCustom");
        }

        self.getMessagesUsingOptionGrpdata = function (e, d) {

            var msgHidden = $("#grpdata").ojSelect("option", "messagesHidden");
            var msgShown = $("#grpdata").ojSelect("option", "messagesShown");
            var msgCustom = $("#grpdata").ojSelect("option", "messagesCustom");
        }

        self.setMessagesUsingOptionGrpdata = function (e, d) {
            $("#grpdata").ojSelect("option", "messagesHidden", "hiddenMessage set using API");
            $("#grpdata").ojSelect("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#grpdata").ojSelect("option", "messagesHidden");
            var msgShown = $("#grpdata").ojSelect("option", "messagesShown");
            var msgCustom = $("#grpdata").ojSelect("option", "messagesCustom");
        }

        //checkboxset

        self.currentColor = ko.observable([]);
        self.isCheckboxValid = ko.observable();

        self.getMessagesUsingOptionCheckbox = function (e, d) {

            var msgHidden = $("#checkboxSetId").ojCheckboxset("option", "messagesHidden");
            var msgShown = $("#checkboxSetId").ojCheckboxset("option", "messagesShown");
            var msgCustom = $("#checkboxSetId").ojCheckboxset("option", "messagesCustom");
        }

        self.setMessagesUsingOptionCheckbox = function (e, d) {
            $("#checkboxSetId").ojCheckboxset("option", "messagesHidden", "hiddenMessage set using API");
            $("#checkboxSetId").ojCheckboxset("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#checkboxSetId").ojCheckboxset("option", "messagesHidden");
            var msgShown = $("#checkboxSetId").ojCheckboxset("option", "messagesShown");
            var msgCustom = $("#checkboxSetId").ojCheckboxset("option", "messagesCustom");
        }
        //Radioset

        self.currentRadioColor = ko.observable();

        self.isRadioValid = ko.observable();

        self.getMessagesUsingOptionRadio = function (e, d) {

            var msgHidden = $("#radiosetSetBasicDemoId").ojRadioset("option", "messagesHidden");
            var msgShown = $("#radiosetSetBasicDemoId").ojRadioset("option", "messagesShown");
            var msgCustom = $("#radiosetSetBasicDemoId").ojRadioset("option", "messagesCustom");
        }

        self.setMessagesUsingOptionRadio = function (e, d) {
            $("#radiosetSetBasicDemoId").ojRadioset("option", "messagesHidden", "hiddenMessage set using API");
            $("#radiosetSetBasicDemoId").ojRadioset("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#radiosetSetBasicDemoId").ojRadioset("option", "messagesHidden");
            var msgShown = $("#radiosetSetBasicDemoId").ojRadioset("option", "messagesShown");
            var msgCustom = $("#radiosetSetBasicDemoId").ojRadioset("option", "messagesCustom");
        }

        self.clearRadioValue = function (d, e) {
            // self.currentRadioColor();
            $("#radiosetSetBasicDemoId").ojRadioset("option", "value", "");
            $("#radiosetSetBasicDemoId").ojRadioset("validate");
        }

        // combobox
        self.comboboxval = ko.observableArray([]);
        self.isComboboxValid = ko.observable();

        self.getMessagesUsingOptionCombobox = function (e, d) {

            var msgHidden = $("#combobox1").ojRadioset("option", "messagesHidden");
            var msgShown = $("#combobox1").ojRadioset("option", "messagesShown");
            var msgCustom = $("#combobox1").ojRadioset("option", "messagesCustom");
        }

        self.setCustomMessagesUsingOptionCombobox = function (e, d) {
            $("#combobox1").ojCombobox("option", "messagesCustom", "Custom message set using API");

            var msgHidden = $("#combobox1").ojCombobox("option", "messagesHidden");
            var msgShown = $("#combobox1").ojCombobox("option", "messagesShown");
            var msgCustom = $("#combobox1").ojCombobox("option", "messagesCustom");
        }

        self.setMessagesUsingOptionCombobox = function (e, d) {
            $("#combobox1").ojCombobox("option", "messagesHidden", "hiddenMessage set using API");
            $("#combobox1").ojCombobox("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#combobox1").ojCombobox("option", "messagesHidden");
            var msgShown = $("#combobox1").ojCombobox("option", "messagesShown");
            var msgCustom = $("#combobox1").ojCombobox("option", "messagesCustom");
        }

        //textArea

        self.textAreaValue = ko.observable('');
        self.textAreaValue1 = ko.observable('');

        self.equalToTextAreaValue = {
            /**
             * validates the value against the value stored in the password observable
             * @param {type} value
             * @returns {Boolean}
             */
            validate: function (value)
            {
                var compareTo = self.textAreaValue.peek();
                if (!value && !compareTo)
                    return true;
                else if (value !== compareTo)
                {
                    throw new Error('Value must match');
                }
                return true;
            }
        };

        self.isTextArea1Valid = ko.observable();
        self.isTextArea2Valid = ko.observable();

        self.getMessagesUsingOptionTextArea1 = function (e, d) {

            var msgHidden = $("#text-area").ojTextArea("option", "messagesHidden");
            var msgShown = $("#text-area").ojTextArea("option", "messagesShown");
            var msgCustom = $("#text-area").ojTextArea("option", "messagesCustom");
        }

        self.setMessagesUsingOptionTextArea1 = function (e, d) {
            $("#text-area").ojTextArea("option", "messagesHidden", "hiddenMessage set using API");
            $("#text-area").ojTextArea("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#text-area").ojTextArea("option", "messagesHidden");
            var msgShown = $("#text-area").ojTextArea("option", "messagesShown");
            var msgCustom = $("#text-area").ojTextArea("option", "messagesCustom");
        }

        self.getMessagesUsingOptionTextArea2 = function (e, d) {

            var msgHidden = $("#resizeBoth").ojTextArea("option", "messagesHidden");
            var msgShown = $("#resizeBoth").ojTextArea("option", "messagesShown");
            var msgCustom = $("#resizeBoth").ojTextArea("option", "messagesCustom");
        }

        self.setMessagesUsingOptionTextArea2 = function (e, d) {
            $("#resizeBoth").ojTextArea("option", "messagesHidden", "hiddenMessage set using API");
            $("#resizeBoth").ojTextArea("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#resizeBoth").ojTextArea("option", "messagesHidden");
            var msgShown = $("#resizeBoth").ojTextArea("option", "messagesShown");
            var msgCustom = $("#resizeBoth").ojTextArea("option", "messagesCustom");
        }

        //password
        self.pwdValue = ko.observable();
        self.pwdValue1 = ko.observable();

        self.equalToPasswordValue = {
            /**
             * validates the value against the value stored in the password observable
             * @param {type} value
             * @returns {Boolean}
             */
            validate: function (value)
            {
                var compareTo = self.pwdValue.peek();
                if (!value && !compareTo)
                    return true;
                else if (value !== compareTo)
                {
                    throw new Error('Value must match');
                }
                return true;
            }
        };

        self.isPasswordValid = ko.observable();
        self.isPassword1Valid = ko.observable();

        self.getMessagesUsingOptionPassword = function (e, d) {

            var msgHidden = $("#password").ojInputPassword("option", "messagesHidden");
            var msgShown = $("#password").ojInputPassword("option", "messagesShown");
            var msgCustom = $("#password").ojInputPassword("option", "messagesCustom");
        }

        self.setMessagesUsingOptionPassword = function (e, d) {
            $("#password").ojInputPassword("option", "messagesHidden", "hiddenMessage set using API");
            $("#password").ojInputPassword("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#password").ojInputPassword("option", "messagesHidden");
            var msgShown = $("#password").ojInputPassword("option", "messagesShown");
            var msgCustom = $("#password").ojInputPassword("option", "messagesCustom");
        }

        self.getMessagesUsingOptionPassword1 = function (e, d) {

            var msgHidden = $("#password1").ojInputPassword("option", "messagesHidden");
            var msgShown = $("#password1").ojInputPassword("option", "messagesShown");
            var msgCustom = $("#password1").ojInputPassword("option", "messagesCustom");
        }

        self.setMessagesUsingOptionPassword1 = function (e, d) {
            $("#password1").ojInputPassword("option", "messagesHidden", "hiddenMessage set using API");
            $("#password1").ojInputPassword("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#password1").ojInputPassword("option", "messagesHidden");
            var msgShown = $("#password1").ojInputPassword("option", "messagesShown");
            var msgCustom = $("#password1").ojInputPassword("option", "messagesCustom");
        }
        // date picker
        self.value = ko.observable();
        self.isDateTimeValid = ko.observable();
        self.isDateTime2Valid = ko.observable();
        self.isDateTime3Valid = ko.observable();

        self.getMessagesUsingOptionDateTime = function (e, d) {

            var msgHidden = $("#dateTime").ojInputDate("option", "messagesHidden");
            var msgShown = $("#dateTime").ojInputDate("option", "messagesShown");
            var msgCustom = $("#dateTime").ojInputDate("option", "messagesCustom");
        }

        self.setMessagesUsingOptionDateTime = function (e, d) {
            $("#dateTime").ojInputDate("option", "messagesHidden", "hiddenMessage set using API");
            $("#dateTime").ojInputDate("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#dateTime").ojInputDate("option", "messagesHidden");
            var msgShown = $("#dateTime").ojInputDate("option", "messagesShown");
            var msgCustom = $("#dateTime").ojInputDate("option", "messagesCustom");
        }

        self.getMessagesUsingOptionDateTime2 = function (e, d) {

            var msgHidden = $("#dateTime2").ojInputDateTime("option", "messagesHidden");
            var msgShown = $("#datetime2").ojInputDateTime("option", "messagesShown");
            var msgCustom = $("#dateTime2").ojInputDateTime("option", "messagesCustom");
        }

        self.setMessagesUsingOptionDateTime2 = function (e, d) {
            $("#dateTime2").ojInputDateTime("option", "messagesHidden", "hiddenMessage set using API");
            $("#dateTime2").ojInputDateTime("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#dateTime2").ojInputDateTime("option", "messagesHidden");
            var msgShown = $("#dateTime2").ojInputDateTime("option", "messagesShown");
            var msgCustom = $("#dateTime2").ojInputDateTime("option", "messagesCustom");
        }

        self.getMessagesUsingOptionDateTime3 = function (e, d) {

            var msgHidden = $("#dateTime3").ojInputTime("option", "messagesHidden");
            var msgShown = $("#dateTime3").ojInputTime("option", "messagesShown");
            var msgCustom = $("#dateTime3").ojInputTime("option", "messagesCustom");
        }

        self.setMessagesUsingOptionDateTime3 = function (e, d) {
            $("#dateTime3").ojInputTime("option", "messagesHidden", "hiddenMessage set using API");
            $("#dateTime3").ojInputTime("option", "messagesShown", "messagesShown set using API");
            var msgHidden = $("#dateTime3").ojInputTime("option", "messagesHidden");
            var msgShown = $("#dateTime3").ojInputTime("option", "messagesShown");
            var msgCustom = $("#dateTime3").ojInputTime("option", "messagesCustom");
        }

        self.toggleDivAsPopup = function (id, d, e) {

            //  $("#"+id).ojPopup("open", 'btn1', {'my':'left top', 'at': 'right+50 bottom', 'collision': 'flip'});
            if ($("#" + id).ojPopup("isOpen"))
                $("#" + id).ojPopup("close");
            else
                $("#" + id).ojPopup("open");
        }

        self.menuItemSelect = function (e, d) {
            oj.Logger.info("menu Item selected :  " + d.item[0].id);
        }

        //Slider
        self.slidermax = ko.observable(200);
        self.slidermin = ko.observable(0);
        self.currentSliderValue = ko.observable(100);
        self.sliderstep = ko.observable(10);
    }


    $(document).ready(function () {
        var rvm = new deferredAppViewModel();
        ko.applyBindings(rvm, document.getElementById("main-container"));
    })
})





