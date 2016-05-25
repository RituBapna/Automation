define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'ojs/ojknockout',
  'ojs/ojinputnumber', 'ojs/ojbutton'],
function(oj, ko, $, bku)
{
    function CompBaseModel(){
        self = this;
        var bk = new bku();

        self.id = ko.observable("");
        self.idList = ko.observableArray([""]);
        self.focusId = ko.observable("");
        self.module = ko.observable("CompBaseModel");
        self.ojName = ko.observable("");
        self.version = ko.observable(1.0);
        self.menu = ko.observable("");
        self.menuList = ["","#myMenu","#myMenu2"];
        self.translations = ko.observable("");
        self.disabled = ko.observable(false);
        self.exposed = ko.observable("exposed");
        self.exposedList = ["exposed","hidden"];
        self.ready = ko.observable(true);
        self.subIdList = ko.observableArray([""]);

        self.setAllOption = function(optionName, optionValue) {
            bk.setAllOption(self.ojName(),self.idList(), optionName,optionValue);
        }

        self.setAllMethod = function(method,value1) {
            bk.setAllMethod(self.ojName(),self.idList(),method,value1);
        }

        self.setAllJq = function(method, value1, value2) {
            bk.setAllJq(self.idList(),method,value1,value2);
        }

        self.jq = function(method,value1,value2) {
            bk.jq(self.id(), method, value1, value2);
        }

        // aliases for get
        self.setMethod = function(id, method,value1) { return get(id,method,value1); }
        self.getMethod = function(id, method,value1) { return get(id,method,value1); }

        self.get = function(id, method, value1)
        {
            return bk.get(self.ojName(),id,method,value1);
        }

        self.getOption = function(id, optionName)
        {
            return bk.getOption(self.ojName(),id,optionName);
        }

        self.setOption = function(id, optionName, optionValue)
        {
            bk.setOption(self.ojName(),id,optionName,optionValue);
        }

        self.notSupported = function() {
            alert("This option not supported for this component");
        }

        self.toggleDisabled = function() {
            bk.msgLog("disabled = " + self.disabled());
            bk.cycle("disabled",bk.boolVals,self.disabled);
            self.setAllOption("disabled", self.disabled());
        }

        self.toggleMenu = function() {
            bk.cycle("contextMenu",self.menuList,self.menu);
            self.setAllOption("contextMenu", self.menu());
        }

        self.toggleExposed = function() {
            bk.cycle("exposed",self.exposedList,self.exposed);
        }

        self.setExposed = function() {
            bk.msgLog("Status: exposed");
            self.exposed("exposed");
        }

        self.setHidden = function() {
            bk.msgLog("Status: hidden");
            self.exposed("hidden");
        }

        self.toggleTranslations = function() {
            var values = self.getOption("translations");
            if (values.length == 0) {
                bk.msgLog("No translations, setting trans for 'required'");
                self.setAllOption("translations",self.reqtrans());
             }
            for (i = 0; i < values.length; i++)
            {
              trans = values[i];
              bk.msgLog("Translation: " + trans);
            }

            self.setAllOption("translations", { someKey: "someValue",
                                                    someOtherKey: "someOtherValue" } );
        }

        self.refresh = function() {
            self.setAllMethod("refresh");
//            var value = self.get(self.id(),'refresh');
//            bk.msgLog(self.id() + ".refresh = " + value);
        }

        self.widget = function() {
            var value = self.get(self.id(),'widget');
            bk.msgLog(self.id() + ".widget = " + " (" + value[0].className + ")");
        }

        self.focus = function() {
            bk.msgLog(self.id() + ".focus");
            self.jq("focus");
        }

        self.getNodeBySubIdFunc = function(locator) {
            bk.msgLog("locator = " + ko.toJSON(locator));
            var node = self.get(self.id(),'getNodeBySubId', ko.toJSON(locator));
            bk.msgLog(self.id() + ".getNodeBySubId {'subId': "+ locator.subId + "} = " + node + " (" + (node != null ? node.outerHTML : node) + ")");
            return node;
        }

        self.getNodeBySubIdString = function(locator) {
            var value = self.get(self.id(),'getNodeBySubId',locator);
            bk.msgLog("getNodeBySubId(" + ko.toJSON(locator) + ") : " + value.outerHTML);
            return {locator: value} ;
        }

        self.getSubIdByNodeFunc = function(node) {
            var locator = {'node': node };
            console.log(locator);
            var subId = self.get(self.id(),'getSubIdByNode', locator);
            bk.msgLog(self.id() + ".getSubIdByNode {'node': "+ node.outerHTML + "} = " + subId + " (" + subId + ")");
            return subId;
        }

        self.getNodeBySubId = function() {
            // subIdList
            for (var i = 0, len = self.subIdList().length; i < len; ++i) {
                var id = self.subIdList()[i];
                self.getNodeBySubIdString("'" + id + "'");
              }
        }

        function outerHTML(node){
            return node.outerHTML || new XMLSerializer().serializeToString(node);
        }

        self.onCreate = function( event, ui ) {
            var eventTxt = "Component created : " + event.target.id;
            oj.Logger.info(event.target.id);
            if (event.target.id.length > 0) {
                var currentTxt = $('#msg-log').val();
                currentTxt = currentTxt == null ? '' : currentTxt;
                $('#msg-log').val(eventTxt + "\n" + currentTxt);
            }
            // oj.Logger.info(event.target);
        };

        self.onDestroy = function( event, ui ) {
            var eventTxt = "Component destroyed : " + event.target.id;
            oj.Logger.info(event.target.id);
            if (event.target.id.length > 0) {
                var currentTxt = $('#msg-log').val();
                currentTxt = currentTxt == null ? '' : currentTxt;
                $('#msg-log').val(eventTxt + "\n" + currentTxt);
            }
            // oj.Logger.info(event.target);
        };

       self.onExposer = function() {
            oj.Logger.info("onExposer");
            self.toggleExposed();
        }

        self.onLogEvent = function (event, ui) {
            var eventTxt = "Event = " + event.type;
            var vm = window.ko.dataFor(document.body);
            var model = vm.model;
            vm.bk.msgLog(eventTxt);
        }

        // Call this in child classes to set event handlers on ids
        self.setHandlers = function() {
            self.setAllJq("unbind","ojcreate",self.onCreate);
            self.setAllJq("unbind","ojdestroy",self.onDestroy);
            self.setAllJq("on","ojcreate",self.onCreate);
            self.setAllJq("on","ojdestroy",self.onDestroy);
           // For test pages, only one of 3 is there, if not found nothing happens
            oj.Logger.info("Adding exposer handlers");
            $("#collapsiblePage").off("ojexpand",self.setExposed);
            $("#collapsiblePage").off("ojcollapse",self.setHidden);
            $("#modalDialog1").off("ojopen", self.setExposed);
            $("#modalDialog1").off("ojclose", self.setHidden);
            $("#popup1").off("ojopen", self.setExposed);
            $("#popup1").off("ojclose", self.setHidden);
            $("#collapsiblePage").on("ojexpand",self.setExposed);
            $("#collapsiblePage").on("ojcollapse",self.setHidden);
            $("#modalDialog1").on("ojopen", self.setExposed);
            $("#modalDialog1").on("ojclose", self.setHidden);
            $("#popup1").on("ojopen", self.setExposed);
            $("#popup1").on("ojclose", self.setHidden);
        }

        self.addComponent = function (event, ui) {
            bk.msgLog("Adding Comp");
            var comp = $(document.createElement('input'));
            comp.attr('id', '#addedComp');
            $("#menu_container").append(comp);
            comp.ojInputText();
        }

        self.removeComponent = function (event, ui) {
            bk.msgLog("Removing Comp");
            $("#addedComp").remove();
        }

        self.destroyComponent = function (event, ui) {
            self.setAllMethod("destroy");
            }


        self.printItemsArray = function(title,items) {
            bk.msgLog(title + ":");
            var index;
            for (index = 0; index < items.length; ++index) {
                bk.msgLog("id = " + items[index].id + ", value = " + items[index].value);
                }
        }

    oj.Logger.info("CompBaseModel created " + self.ojName());
    }

    return CompBaseModel;
});
