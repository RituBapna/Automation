define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'dataVM', 'ojs/ojknockout',
        'ojs/ojcomponents', 'ojs/ojtable'],
function(oj, ko, $, bku, datavm)
{
    function TableBaseModel(){
        self = this;
        var bk = new bku();
        datavm.call(this);
        // var dvm = new datavm();

        self.id = ko.observable("#table");
        self.hasPaging = ko.observable(false);
        self.pagingId = ko.observable("#paging");
        self.hasExpander = ko.observable(false);
        self.expanderId = ko.observable("#t1");
        self.idList = ko.observableArray(["#table"]);
        self.pagingIdList = ko.observableArray(["#paging"]);
        self.expanderIdList = ko.observableArray(["#t1","#t2","#t3","#t4"]);
        self.module = ko.observable("TableBaseModel");
        self.ojName = ko.observable("ojTable");
        self.ojPagingName = ko.observable("ojPagingControl");
        self.ojExpanderName = ko.observable("ojRowExpander");
        self.version = ko.observable(1.0);
        self.menu = ko.observable("");
        self.menuList = ["","#myMenu","#myMenu2"];
        self.display = ko.observable("");
        self.displayList = ["list","grid"];
        self.dndColReorder = ko.observable("");
        self.enabledList = ko.observableArray(["enabled","disabled"]);
        self.datasource = ko.observable(self.baseDataSource());
        self.datasourceDescr = ko.observable(self.baseDescr);
        self.mode = ko.observable("page"); //loadMore
        self.modeList = ko.observableArray(["page","loadMore"]);
        self.sortable = ko.observable("enabled");
        self.sortableList = ko.observableArray(["enabled","disabled"]);
        self.contentVisible = ko.observable(false);
        self.disabled = ko.observable(false);
        self.scrollPolicy = ko.observable("loadMoreOnScroll");
        self.spFetchSize = ko.observable("4");
        self.spMaxCount = ko.observable("");
        self.scrollPolicyList = ["","auto","loadMoreOnScroll"];
        self.spMaxCountList = ["-1","4","25"];
        self.spFetchSizeList = ["4","10","25"];
        self.exposed = ko.observable("exposed");
        self.exposedList = ["exposed","hidden"];
        self.emptyTextList = [null,"No data"];
        self.emptyText = ko.observable();
        self.ready = ko.observable(false);

        self.selectionModeRow = ko.observable("single");
        self.selectionModeColumn = ko.observable("single");
        self.verticalGridVisible = ko.observable("enabled");
        self.horizontalGridVisible = ko.observable("enabled");
        self.className = ko.observable();
        self.headerClassName = ko.observable();
        self.footerClassName = ko.observable();
        self.style = ko.observable('');
        self.headerStyle = ko.observable('');
        self.footerStyle = ko.observable('');
        self.accRowHeaderCol = ko.observable('column1');
        self.sortProperty = ko.observable();

        self.multiList = [ "", "single", "multiple"];
        self.enableList = ["enabled","disabled"];
        self.classNameList = ['redStyle','blackStyle'];
        self.styleList = ['color : red','color : yellow','','text-align:right','text-align:center','text-align:left'];
        self.accRowHeaderColList = ['','column1','column2','column3'];
        self.sortPropertyList = ['DepartmentId','LocationId',''];
        self.pagingLayoutOption = ko.observable();
        self.pagingMaxPageLinks = ko.observable();
        self.pagingMaxPageLinksList = ["5","6"]
        self.pagingType = ko.observable();
        self.pagingTypeList = ["numbers","dots"];
        self.pagingOrientation = ko.observable();
        self.pagingOrientationList = ["horizontal","vertical"];

        self.subIdCell = 'oj-table-cell';
        self.subIdHeader = 'oj-table-header';
        self.subIdSortAsc = 'oj-table-sort-ascending';
        self.subIdSortDes = 'oj-table-sort-descending';
        self.subIdFooter = 'oj-table-footer';

        self.setAllOption = function(optionName, optionValue) {
            bk.setAllOption(self.ojName(),self.idList(), optionName,optionValue);
        }

        self.setAllMethod = function(method,value1) {
            bk.setAllMethod(self.ojName(),self.idList(),method,value1);
        }

        self.setAllJq = function(method, value1, value2, value3) {
            bk.setAllJq(self.idList(),method,value1,value2, value3);
        }

        self.setAllPagingJq = function(method, value1, value2) {
            bk.setAllJq(self.pagingIdList(),method,value1,value2);
        }

        self.setAllPagingMethod = function(method,value1) {
            bk.setAllMethod(self.ojPagingName(),self.pagingIdList(),method,value1);
        }

        self.setAllExpanderJq = function(method, value1, value2) {
            bk.setAllJq(self.expanderIdList(),method,value1,value2);
        }

        self.setAllExpanderMethod = function(method,value1) {
            bk.setAllMethod(self.ojExpanderName(),self.expanderIdList(),method,value1);
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

        // for paging
        self.pagingGet = function(id, method, value1)
        {
            return bk.get(self.ojPagingName(),id,method,value1);
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
            var subId = self.get(self.id(),'getSubIdByNode', "'" + node + "'");
            bk.msgLog(self.id() + ".getSubIdByNode {} = " + subId );
            return subId;
        }


        // For testing, just use first row, first column always
        // For more robust, you could change this to find selection and the use that.
        self.getNodeBySubId = function() {
            var locatorCell = {'subId': self.subIdCell,'rowIndex' : 0,'columnIndex':0};
            var locatorHeader = {'subId': self.subIdHeader, 'index':0};
            var locatorSortAsc = {'subId': self.subIdSortAsc, 'index':0};
            var locatorSortDes = {'subId': self.subIdSortDes, 'index':0};
            var locatorFooter = {'subId': self.subIdFooter, 'index':0};

            var value = self.getNodeBySubIdFunc(locatorCell);
            bk.msgLog("getNodeBySubId(" + ko.toJSON(locatorCell) + ") : " + value.outerHTML);
            value = self.getNodeBySubIdFunc(locatorHeader);
            bk.msgLog("getNodeBySubId(" + ko.toJSON(locatorHeader) + ") : " + value.outerHTML);
            value = self.getNodeBySubIdFunc(locatorSortAsc);
            bk.msgLog("getNodeBySubId(" + ko.toJSON(locatorSortAsc) + ") : " + value.outerHTML);
            value = self.getNodeBySubIdFunc(locatorSortDes);
            bk.msgLog("getNodeBySubId(" + ko.toJSON(locatorSortDes) + ") : " + value.outerHTML);
            value = self.getNodeBySubIdFunc(locatorFooter);
            bk.msgLog("getNodeBySubId(" + ko.toJSON(locatorFooter) + ") : " + value.outerHTML);
            }


        self.getSubIdByNode = function() {
            // for node, just use the jquery selector, which is in self.id();
            var targetNode = self.id();
            var locatorCell = {'subId': self.subIdCell,'rowIndex' : 0,'columnIndex':0};
            var locatorHeader = {'subId': self.subIdHeader, 'index':0};
            var locatorSortAsc = {'subId': self.subIdSortAsc, 'index':0};
            var locatorSortDes = {'subId': self.subIdSortDes, 'index':0};
            var locatorFooter = {'subId': self.subIdFooter, 'index':0};

            bk.msgLog("getSubIdByNode: find node for each of the 5 subids, then get subid and verify matches")
            var value = self.getNodeBySubIdFunc(locatorCell);
            var subid = self.getSubIdByNodeFunc("#" + value.id);
            bk.msgLog("1. Returned subid = " + subid + " should match initial locator = " + ko.toJSON(locatorCell));

            value = self.getNodeBySubIdFunc(locatorHeader);
            subid = self.getSubIdByNodeFunc("#" + value.id);
            bk.msgLog("2. Returned subid = " + subid + " should match initial locator = " + ko.toJSON(locatorHeader));

            value = self.getNodeBySubIdFunc(locatorSortAsc);
            subid = self.getSubIdByNodeFunc("#" + value.id);
            bk.msgLog("3. Returned subid = " + subid + " should match initial locator = " + ko.toJSON(locatorSortAsc));

            value = self.getNodeBySubIdFunc(locatorSortDes);
            subid = self.getSubIdByNodeFunc("#" + value.id);
            bk.msgLog("4. Returned subid = " + subid + " should match initial locator = " + ko.toJSON(locatorSortDes));

            value = self.getNodeBySubIdFunc(locatorFooter);
            subid = self.getSubIdByNodeFunc("#" + value.id);
            bk.msgLog("5. Returned subid = " + subid + " should match initial locator = " + ko.toJSON(locatorFooter));
        }


        self.setPagingOption = function(id, optionName, optionValue)
        {
            return bk.setOption(self.ojPagingName(),id,optionName,optionValue);
        }

        self.refreshCollection = function(data, event) {
            bk.msgLog("Refreshing collection");
            var collection = data.mockDeptCol();
            collection.refresh();
            }

        self.resetCollection = function(data, event) {
            bk.msgLog("Resetting collection");
            var collection = data.mockDeptCol();
            collection.reset();
            }


        self.refresh = function(paging) {
            self.resetReady();
            self.setAllMethod("refresh");
            if (paging == true) {
                self.pagingGet(self.pagingId(),"firstPage");
                self.pagingGet(self.pagingId(),"refresh");
            }
        }

        self.refreshRow = function(paging) {
            self.resetReady();
            // get selected row and refresh that one.
            // if more than 1 row selected, just refresh first one .. adequate for testing.
            // if no rows in table, return;
            // if no selected row, refresh first row
            var rowToRefresh = 0;

            var selObj = self.currentSelection();

            if (selObj != null) {
                rowToRefresh = selObj[0].startIndex.row;
                }

            self.setAllMethod("refreshRow",rowToRefresh);
        }

        self.resetReady = function() {
            self.ready(false);
            oj.Logger.info("reset table ready");
//            bk.msgLog("reset table ready");
        }

        self.setHelp = function() {
            self.setOption(self.id(),"help",{"definition":"fill out the name", "source":"http:\\www.oracle.com" });
        }

        self.toggleExposed = function() {
            bk.cycle("exposed",self.exposedList,self.exposed);
        }

        self.toggleDisplay = function() {
            bk.cycle("display",self.displayList,self.display);
//            self.setAllOption("display", self.display());
        }

        self.toggleDndColReorder = function() {
            bk.cycle("dndColReorder",self.enabledList(),self.dndColReorder);
            // self.setAllOption("dndColReorder", self.dndColReorder());
        }


        	self.toggleAccRowHeaderCol = function() {
            self.resetReady();
            bk.cycle("accRowHeaderCol",self.accRowHeaderColList,self.accRowHeaderCol);
        }

		self.toggleSelectionModeRow = function() {
            self.resetReady();
            bk.cycle("selectionModeRow",self.multiList,self.selectionModeRow);
        }

		self.toggleSelectionModeColumn = function() {
            self.resetReady();
            bk.cycle("selectionModeColumn",self.multiList,self.selectionModeColumn);
        }
		self.toggleVerticalGridVisible = function() {
            self.resetReady();
            bk.cycle("verticalGridVisible",self.enableList,self.verticalGridVisible);
        }

		self.toggleHorizontalGridVisible = function() {
            self.resetReady();
            bk.cycle("horizontalGridVisible",self.enableList,self.horizontalGridVisible);
        }
		self.toggleHeaderClassName = function() {
            self.resetReady();
            bk.cycle("headerClassName",self.classNameList,self.headerClassName);
        }

		self.toggleHeaderStyle = function() {
            self.resetReady();
            bk.cycle("headerStyle",self.styleList,self.headerStyle);
        }

		self.toggleFooterClassName = function() {
            self.resetReady();
            bk.cycle("footerClassName",self.classNameList,self.footerClassName);
        }

		self.toggleFooterStyle = function() {
            self.resetReady();
            bk.cycle("footerStyle",self.styleList,self.footerStyle);
        }

		self.toggleStyle = function() {
            self.resetReady();
            bk.cycle("style",self.styleList,self.style);
        }

		self.toggleClassName = function() {
            self.resetReady();
            bk.cycle("className",self.classNameList,self.className);
        }

    self.rowcount_func = function(footerContext)
    {
      var datasource = footerContext.datasource;
      return datasource.totalSize();
    };


        self.toggleMenu = function() {
            self.resetReady();
            bk.cycle("contextMenu",self.menuList,self.menu);
            self.setAllOption("contextMenu", self.menu());
        }

        self.toggleMode = function() {
            self.resetReady();
            bk.cycle("mode",self.modeList(),self.mode);
            self.setAllOption("mode", self.mode());
            self.refresh(true);
        }

	self.togglePagingOrientation = function() {
            bk.cycle("pagingOrientation",self.pagingOrientationList,self.pagingOrientation);
            self.setPageOptions();
        }

        self.togglePagingType = function() {
            bk.cycle("pagingType",self.pagingTypeList,self.pagingType);
            self.setPageOptions();
        }

        self.togglePagingMaxPageLinks = function() {
            bk.cycle("pagingMaxPageLinks",self.pagingMaxPageLinksList,self.pagingMaxPageLinks);
            self.setPageOptions();
        }

        self.setPageOptions = function() {
            $("#paging").ojPagingControl("option",
                    "pageOptions", {
                        "layout": self.pagingLayoutOption(),
                        "type": self.pagingType(),
                        "orientation": self.pagingOrientation(),
                        "maxPageLinks": self.pagingMaxPageLinks()
                        }
                    );
        }

        self.toggleSortable = function() {
            self.resetReady();
            bk.cycle("sortable",self.sortableList(),self.sortable);
        }

        self.toggleSortProperty = function() {
            self.resetReady();
            bk.cycle("sortProperty",self.sortPropertyList,self.sortProperty);
        }

        self.toggleScrollPolicy = function() {
            self.resetReady();
            bk.cycle("scrollPolicy",self.scrollPolicyList,self.scrollPolicy);
        }
        self.toggleScrollPolicyFetchSize = function() {
            self.resetReady();
            bk.cycle("spFetchSize",self.spFetchSizeList,self.spFetchSize);
        }
        self.toggleScrollPolicyMaxCount = function() {
            self.resetReady();
            bk.cycle("spMaxCount",self.spMaxCountList,self.spMaxCount);
        }

        self.toggleDatasource = function() {
            self.resetReady();
            bk.cycle("data",self.datasourceList(),self.datasource);
            bk.cycle("descr",self.datasourceDescrList(),self.datasourceDescr);
            // self.setAllOption("data", self.datasource());
        }

        self.togglePagingDatasource = function() {
            self.resetReady();
            self.datasource().off(oj.PagingModel.EventType['PAGE'],self.onPageChange);
            self.datasource().off(oj.PagingModel.EventType['BEFOREPAGE'],self.onBeforePageChange);
            self.datasource().off(oj.PagingModel.EventType['PAGECOUNT'],self.onPageCountChange);
            bk.cycle("data",self.pagingDatasourceList(),self.datasource);
            bk.cycle("descr",self.pagingDatasourceDescrList(),self.datasourceDescr);
            self.datasource().on(oj.PagingModel.EventType['BEFOREPAGE'],self.onBeforePageChange);
            self.datasource().on(oj.PagingModel.EventType['PAGE'],self.onPageChange);
            self.datasource().on(oj.PagingModel.EventType['PAGECOUNT'],self.onPageCountChange);
            // self.refresh(true);  Don't refresh to avoid 2 ready events
        }

        self.toggleDisabled = function() {
            bk.msgLog("disabled = " + self.disabled());
            bk.cycle("disabled",bk.boolVals,self.disabled);
            self.setAllOption("disabled", self.disabled());
        }

        self.toggleEmptyText = function() {
            bk.msgLog("emptyText = " + self.emptyText());
            bk.cycle("emptyText",self.emptyTextList,self.emptyText);
            self.setAllOption("emptyText", self.emptyText());
        }


        self.onCreate = function( event, ui ) {
            var eventTxt = "Component created : " + event.target.id;
//            oj.Logger.info(event.target.id);
            if (event.target.id.length > 0) {
                var currentTxt = $('#msg-log').val();
                currentTxt = currentTxt == null ? '' : currentTxt;
                $('#msg-log').val(eventTxt + "\n" + currentTxt);
            }
            // oj.Logger.info(event.target);
        };

        self.onDestroy = function( event, ui ) {
            var eventTxt = "Component destroyed : " + event.target.id;
//            oj.Logger.info(event.target.id);
            if (event.target.id.length > 0) {
                var currentTxt = $('#msg-log').val();
                currentTxt = currentTxt == null ? '' : currentTxt;
                $('#msg-log').val(eventTxt + "\n" + currentTxt);
            }
            // oj.Logger.info(event.target);
        };

        self.onSort = function( event, ui ) {
            var eventTxt = "Table sort event received : " + event.target.id;
            oj.Logger.info(event.target.id);
            if (event.target.id.length > 0) {
                var currentTxt = $('#msg-log').val();
                currentTxt = currentTxt == null ? '' : currentTxt;
                $('#msg-log').val(eventTxt + "\n" + currentTxt);
            }
            // oj.Logger.info(event.target);
        };


        self.selectionToString = function(selectionObj) {
            var eventTxt = "";

            if (selectionObj == null)
            {
                return("Selection is null \n");
            }

            var i = 0;
            for (i = 0; i < selectionObj.length; i++)
            {
                var range = selectionObj[i];
                var startIndex = range.startIndex;
                var endIndex = range.endIndex;
                var startKey = range.startKey;
                var endKey = range.endKey;

                if (startIndex.row != null)
                {
                    eventTxt = eventTxt + "Row Selection\n";
                    eventTxt = eventTxt + "start row index: " + startIndex.row + ", end row index: " + endIndex.row + "\n";
                }
                if (startKey.row != null)
                {
                    eventTxt = eventTxt + "start row key: " + startKey.row + ", end row key: " + endKey.row + "\n";
                }

                if (startIndex.column != null)
                {
                    eventTxt = eventTxt + "Column Selection\n";
                    eventTxt = eventTxt + "start column index: " + startIndex.column + ", end column index: " + endIndex.column + "\n";
                }
                if (startKey.column != null)
                {
                    eventTxt =eventTxt + "start column key: " + startKey.column + ", end column key: " + endKey.column + "\n";
                }
            }
            return eventTxt;
        }

        self.onOptionChange = function (context, data) {
        var eventTxt = "Triggered ojoptionchange event for selection: \n";
        if (data['option'] == 'selection')
        {
            var selectionObj = data['value'];

            if (selectionObj == null)
            {
                return("Selection is null \n");
            }

            var i = 0;
            for (i = 0; i < selectionObj.length; i++)
            {
                var range = selectionObj[i];
                var startIndex = range.startIndex;
                var endIndex = range.endIndex;
                var startKey = range.startKey;
                var endKey = range.endKey;

                if (startIndex.row != null)
                {
                    eventTxt = eventTxt + "Row Selection\n";
                    eventTxt = eventTxt + "start row index: " + startIndex.row + ", end row index: " + endIndex.row + "\n";
                }
                if (startKey.row != null)
                {
                    eventTxt = eventTxt + "start row key: " + startKey.row + ", end row key: " + endKey.row + "\n";
                }

                if (startIndex.column != null)
                {
                    eventTxt = eventTxt + "Column Selection\n";
                    eventTxt = eventTxt + "start column index: " + startIndex.column + ", end column index: " + endIndex.column + "\n";
                }
                if (startKey.column != null)
                {
                    eventTxt = eventTxt + "start column key: " + startKey.column + ", end column key: " + endKey.column + "\n";
                }
            }

            var currentTxt = $('#msg-log').val();
            currentTxt = currentTxt == null ? '' : currentTxt;
            $('#msg-log').val(eventTxt + "\n" + currentTxt);
            }
        window.console.log(eventTxt);
        };


        self.onBeforeCurrentRow = function(event, data)
        {
            var newCurrentRow = data.currentRow;
            var oldCurrentRow = data.previousCurrentRow;
            var eventTxt = "Triggered ojbeforecurrentrow event: \npreviousCurrentRow: ";
            if (!oldCurrentRow)
            {
                eventTxt = eventTxt + 'null \n';
            }
            else
            {
                eventTxt = eventTxt + "{rowIndex: " + oldCurrentRow['rowIndex'] + " rowKey: " +  oldCurrentRow['rowKey'] + "} \n";
            }
            eventTxt = eventTxt + "currentRow: {rowIndex: " + newCurrentRow['rowIndex'] + " rowKey: " +  newCurrentRow['rowKey'] + "} \n";
            window.console.log("%s", eventTxt);
            var currentTxt = $('#msg-log').val();
            currentTxt = currentTxt == null ? '' : currentTxt;
            $('#msg-log').val(eventTxt + "\n" + currentTxt);

        }


        self.currentSelection = function()
        {
            var selectionObj = $("#table").ojTable("option", "selection");
            var selectionTxt = "";

            bk.msgLog(self.selectionToString(selectionObj));
            return selectionObj;
        };

        self.onLayoutOption = function() {
            self.setPageOptions();
            eventTxt = "onLayoutOption: " + self.pagingLayoutOption() + "\n";
            window.ko.dataFor(document.body).bk.msgLog(eventTxt);
        }


        self.onExposer = function() {
            oj.Logger.info("onExposer");
            self.toggleExposed();
        }

        self.onReady = function( event, ui ) {
            var eventTxt = "Table ready event received : " + event.target.id;
            var vm = window.ko.dataFor(document.body);
            var model = vm.model;
            // vm.bk.msgLog("Setting ready = true (was = " + model.ready());
            model.ready(true);
//            vm.bk.msgLog(eventTxt);
            window.console.log(eventTxt);
        };

        self.onPageChange = function( event, ui ) {
            var eventTxt = "Page Change event received : page = " + event.page +
              ", prev page = " + event.previousPage;
            var vm = window.ko.dataFor(document.body);
            var model = vm.model;
            vm.bk.msgLog(eventTxt);
        };

        self.onBeforePageChange = function( event, ui ) {
            var eventTxt = "Before Page Change event received : page = " + event.page +
              ", prev page = " + event.previousPage;
            var vm = window.ko.dataFor(document.body);
            var model = vm.model;
            vm.bk.msgLog(eventTxt);
        };

        self.onPageCountChange = function( event, ui ) {
            var eventTxt = "Page Count Change event received : pageCount = " + event.pageCount +
              ", prev pageCount= " + event.previousPageCount;
            var vm = window.ko.dataFor(document.body);
            var model = vm.model;
            vm.bk.msgLog(eventTxt);
        };


        // Call this in child classes to set event handlers on ids
        self.setHandlers = function() {
            oj.Logger.info("setHandlers");
            self.setAllJq("on","ojcreate",self.onCreate);
            self.setAllJq("on","ojdestroy",self.onDestroy);
            self.setAllJq("on","ojready",self.onReady);
            self.setAllJq("on","ojsort",self.onSort);
            self.setAllJq("on","ojbeforecurrentrow",self.onBeforeCurrentRow);
            self.setAllJq("on","ojoptionchange ",self.onOptionChange);
            if (self.hasPaging()) {
                oj.Logger.info("setHandlers: paging");
                self.setAllPagingJq("on","ojcreate",self.onCreate);
                self.setAllPagingJq("on","ojdestroy",self.onDestroy);
                // if paging layout is there set it, otherwise nothing happens
                $( "#autoOptId" ).on("click", self.onLayoutOption);
                $( "#inputOptId" ).on("click", self.onLayoutOption);
                $( "#rangeTextOptId" ).on("click", self.onLayoutOption);
                $( "#pagesOptId" ).on("click", self.onLayoutOption);
                $( "#navOptId" ).on("click", self.onLayoutOption);
                // No point putting these for initial page since only 1 page for it.

                // Just add when datasource changes
                // self.datasource().on(oj.PagingModel.EventType['PAGE'],self.onPageChange);
                // self.datasource().on(oj.PagingModel.EventType['BEFOREPAGE'],self.onBeforePageChange);
                // self.datasource().on(oj.PagingModel.EventType['PAGECOUNT'],self.onPageCountChange);
                //Other paging events to add: SYNC, ADD, REMOVE, CHANGE, REFRESH, RESET
            }
            if (self.hasExpander()) {
                oj.Logger.info("setHandlers: expander");
                self.setAllExpanderJq("on","ojcreate",self.onCreate);
                self.setAllExpanderJq("on","ojdestroy",self.onDestroy);
            }
            // For test pages, only one of 3 is there, if not found nothing happens
            oj.Logger.info("Adding exposer handlers");
            $("#collapsiblePage").on("ojexpand",self.onExposer);
            $("#collapsiblePage").on("ojcollapse",self.onExposer);
            $("#modalDialog1").on("ojopen", self.onExposer);
            $("#modalDialog1").on("ojclose", self.onExposer);
            $("#popup1").on("ojopen", self.onExposer);
            $("#popup1").on("ojclose", self.onExposer);
        }

        // Can only set handlers after ids set
        // Instead of calling this here:
        // self.setHandlers();
        // Put this at end of app.js after loading html frags:
        // masterVM.model.setHandlers();
        // that way you can dynamically build page and apply handlers.


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
            if (self.hasPaging() == true) {
                self.setAllPagingMethod("destroy");
                }
            }

        self.getPage = function(event, ui) {
          if(self.hasPaging() == true) {
            var pgNum = self.datasource().getPage() + 1;
            bk.msgLog("Currrently at Page " + pgNum);
          }
        }

        self.setPage = function(event, ui) {
          if(self.hasPaging() == true) {
            var pgNum = self.datasource().getPage();
            var pgCount = self.datasource().getPageCount();
            if(pgCount > 1) {
              if(pgNum == 0){
                bk.msgLog("Was at Page 1, setting to Page 2 ");
                self.datasource().setPage(1);
              } else {
                bk.msgLog("Was at Page " + (pgNum + 1) + ", setting to Page 1 ");
                self.datasource().setPage(0);
              }
            } else {
              bk.msgLog("Only 1 page, unable to change pages");
            }
          }
        }


        self.getPagingModel = function(event, ui) {
          if(self.hasPaging() == true) {
            var pgNum = self.datasource().getPage();
            var startItemIndex = self.datasource().getStartItemIndex();
            var endItemIndex = self.datasource().getEndItemIndex();
            var pgCount = self.datasource().getPageCount();
            var totalSize = self.datasource().totalSize();
            var totalSizeConfidence = self.datasource().totalSizeConfidence();
            bk.msgLog("getPage = " + pgNum);
            bk.msgLog("startItemIndex = " + startItemIndex);
            bk.msgLog("endItemIndex = " + endItemIndex);
            bk.msgLog("pgCount = " + pgCount);
            bk.msgLog("totalSize = " + totalSize);
            bk.msgLog("totalSizeConfidence = " + totalSizeConfidence);
          }
        }

        // header renderer function
        // Return the value we want to set as content for the header.
        self.oracle_link_hdr_func = function(context)
        {
          return 'Oracle Link';
        };

        // an alternative header renderer function that has icons
        self.oracle_link_hdr_icon_func = function(context) {
            context.columnHeaderSortableIconRenderer(null, function(headerContentDiv)
            {
              var headerTextDiv = $(document.createElement('div'));
              headerTextDiv.attr('style', 'display: inline-block;');
              headerTextDiv.text('Oracle Link');
              var empIconDiv = $(document.createElement('div'));
              empIconDiv.attr('style', 'display: inline-block; padding-left: 5px; font-size: 18px; vertical-align: top;');
              empIconDiv.attr('role', 'img');
              empIconDiv.attr('class', 'icon-zoomin');
              headerContentDiv.append(headerTextDiv);
              headerContentDiv.append(empIconDiv);
            });
        }

        // cell renderer function
        // Directly append HTML to the cell passed in
        // via context.parentElement
        self.oracle_link_func = function(context)
        {
          var link = $(document.createElement('a'));
          link.attr('href', 'http://www.oracle.com');
          link.append('Oracle');
          $(context.cellContext.parentElement).append(link);
        };

        // cell renderer function
        // Grab the row data from the context and then
        // return the value we want to set as content for the cell.
        // The content can be arbitrary HTML or a String value.
        self.dept_name_func = function(context)
        {
          return context.row.DepartmentName;
        };


         self.displayButtons = [{id: 'normal', label: 'normal'},
                                {id: 'reversed', label: 'reversed columns'},
                                {id: 'noid', label: 'no id columns'}];

         self.columns = {
             normal:   [{headerText: 'Department Id', field: 'DepartmentId'},
                        {headerText: 'Department Name', field: 'DepartmentName'},
                        {headerText: 'Location Id', field: 'LocationId'},
                        {headerText: 'Manager Id', field: 'ManagerId'}],
             reversed: [{headerText: 'Manager Id', field: 'ManagerId'},
                        {headerText: 'Location Id', field:'LocationId'},
                        {headerText: 'Department Name', field: 'DepartmentName'},
                        {headerText: 'Department Id', field: 'DepartmentId'}],
             noid:     [{headerText: 'Department Name', field:'DepartmentName'}]
         };

         self.display = new ko.observable('normal');

         self.myTable =
                 {component: 'ojTable', data: self.datasource,
            accessibility: ko.pureComputed( function() {
                return {rowHeader: self.accRowHeaderCol()} } ),
            selectionMode: ko.pureComputed(
                function() { return {row: self.selectionModeRow(),
                    column: self.selectionModeColumn()} } ),
            verticalGridVisible: self.verticalGridVisible,
            horizontalGridVisible: self.horizontalGridVisible,
            display: self.display(),
            dnd: ko.pureComputed(
                function() {
                    return {reorder : {columns: self.dndColReorder()}}
                }),
             columnsDefault: ko.pureComputed(
                function() {return {sortable: self.sortable(),
                    headerClassName: self.headerClassName(),
                    headerStyle: self.headerStyle(),
                    className: self.className(),style: self.style(),
                    footerClassName: self.footerClassName(),
                    footerStyle: self.footerStyle()}}),
            columns:
               [{headerText: 'Department Id',field: 'DepartmentId', id: 'column1',footerTemplate: 'rowcount_label'},
               {headerText: 'Department Name', field: 'DepartmentName', id: 'column2'},
               ko.pureComputed(
                function() { return {headerText: 'Location Id',
                    field: 'LocationId',
                    id: 'column3',
                    sortProperty: self.sortProperty()} }),
               {headerText: 'Manager Id', field: 'ManagerId',
                headerClassName: 'blackStyle',headerStyle : 'color: grey;',
                className : 'blackStyle',style : 'color: grey;',
                footerClassname: 'blackStyle', footerStyle: 'color: grey;',
                id: 'column4',sortable: 'disabled', footerTemplate: 'rowcount'}],
                 rootAttributes: {'style':'width: 100%;'}};

         self.myTableCellTemplate =
                 {component: 'ojTable', data: self.datasource,
            accessibility: ko.pureComputed( function() {
                return {rowHeader: self.accRowHeaderCol()} } ),
            selectionMode: ko.pureComputed(
                function() { return {row: self.selectionModeRow(),
                    column: self.selectionModeColumn()} } ),
            verticalGridVisible: self.verticalGridVisible,
            horizontalGridVisible: self.horizontalGridVisible,
            display: self.display(),
            dnd: ko.pureComputed(
                function() {
                    return {reorder : {columns: self.dndColReorder()}}
                }),
            columnsDefault: ko.pureComputed(
                function() {return {sortable: self.sortable(),
                    headerClassName: self.headerClassName(),
                    headerStyle: self.headerStyle(),
                    className: self.className(),style: self.style(),
                    footerClassName: self.footerClassName(),
                    footerStyle: self.footerStyle()}}),
            columns:
               [{headerText: 'Department Id',field: 'DepartmentId', id: 'column1',
                    footerTemplate: 'rowcount_label',template: 'dept_name'},
               {headerText: 'Department Name', field: 'DepartmentName', id: 'column2'},
              ko.pureComputed(
                function() { return {headerText: 'Location Id',
                    field: 'LocationId',
                    id: 'column3',
                    sortProperty: self.sortProperty()} }),
               {headerText: 'Manager Id', field: 'ManagerId',
                headerClassName: 'blackStyle',headerStyle : 'color: grey;',
                className : 'blackStyle',style : 'color: grey;',
                footerClassname: 'blackStyle', footerStyle: 'color: grey;',
                id: 'column4',sortable: 'disabled', footerTemplate: 'rowcount'},
               {headerTemplate: 'oracle_link_hdr',
                      template: 'oracle_link'}],
                 rootAttributes: {'style':'width: 100%;'}};

        self.myTableRowTemplate =
                 {component: 'ojTable', data: self.datasource,
            accessibility: ko.pureComputed( function() {
                return {rowHeader: self.accRowHeaderCol()} } ),
            selectionMode: ko.pureComputed(
                function() { return {row: self.selectionModeRow(),
                    column: self.selectionModeColumn()} } ),
            verticalGridVisible: self.verticalGridVisible,
            horizontalGridVisible: self.horizontalGridVisible,
            display: self.display(),
            dnd: ko.pureComputed(
                function() {
                    return {reorder : {columns: self.dndColReorder()}}
                }),
            columnsDefault: ko.pureComputed(
                function() {return {sortable: self.sortable(),
                    headerClassName: self.headerClassName(),
                    headerStyle: self.headerStyle(),
                    className: self.className(),style: self.style(),
                    footerClassName: self.footerClassName(),
                    footerStyle: self.footerStyle()}}),
            columns:
               [{headerText: 'Department Id',field: 'DepartmentId', id: 'column1',footerTemplate: 'rowcount_label'},
               {headerText: 'Department Name', field: 'DepartmentName', id: 'column2'},
              ko.pureComputed(
                function() { return {headerText: 'Location Id',
                    field: 'LocationId',
                    id: 'column3',
                    sortProperty: self.sortProperty()} }),
               {headerText: 'Manager Id', field: 'ManagerId',
                headerClassName: 'blackStyle',headerStyle : 'color: grey;',
                className : 'blackStyle',style : 'color: grey;',
                footerClassname: 'blackStyle', footerStyle: 'color: grey;',
                id: 'column4',sortable: 'disabled', footerTemplate: 'rowcount'}],
                rowTemplate: 'row_tmpl',
                 rootAttributes: {'style':'width: 100%;'}};

    oj.Logger.info("TableBaseModel created " + self.ojName());
    }

    TableBaseModel.prototype = Object.create(datavm.prototype);
    TableBaseModel.prototype.constructor = TableBaseModel;

    return TableBaseModel;
});
