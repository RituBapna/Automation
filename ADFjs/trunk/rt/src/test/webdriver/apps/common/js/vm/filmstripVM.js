define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojfilmstrip', 'ojs/ojselectcombobox', 'ojs/ojbutton', 'ojs/ojmenu', 'ojs/ojpagingcontrol', 'ojs/ojradioset'],
        function(oj, ko, $)
        {
            function FilmStripModel() {
                var self = this;
                  self.val = ko.observableArray(["Chrome"]);
                
                self.browsers = ko.observableArray([
            {value: 'internet-explorer', label: 'Internet Explorer'},
            {value: 'firefox',    label: 'Firefox'},
            {value: 'chrome',   label: 'Chrome'},
            {value: 'opera',    label: 'Opera'},
            {value: 'safari',   label: 'Safari'}
        ]);
              self.currentNavArrowPlacement = ko.observable("adjacent");
               // self.currentNavArrowPlacement = ko.observableArray(["adjacent"])
                self.currentNavArrowVisibility = ko.observable("visible");
               // self.fsArrowCurrentItemValue = ko.observable("1");
                 self.fsArrowCurrentItemValue = ko.observableArray(["1"])
                self.maxItemsPerPageValue = ko.observable(2);
                self.orientationValue = ko.observable("horizontal");
                self.maxItemsPerPageOptions = ko.observableArray([
                    {id: '0', value: '0', label: '0'},
                    {id: '1', value: '1', label: '1'},
                    {id: '2', value: '2', label: '2'},
                    {id: '3', value: '3', label: '3'},
                    {id: '4', value: '4', label: '4'}
                ]);
                self.fsArrowCurrentItemOptions = ko.observableArray([
                    {id: '0', value: '0', label: '0'},
                    {id: '1', value: '1', label: '1'},
                    {id: '2', value: '2', label: '2'},
                    {id: '3', value: '3', label: '3'}
                ]);
                self.orientationOptions = ko.observableArray([
                    {id: 'vertical', value: 'vertical', label: 'vertical'},
                    {id: 'horizontal', value: 'horizontal', label: 'horizontal'}
                ]);
                self.chemicals = [
                    {name: 'Acetylene'},
                    {name: 'Ammonia'},
                    {name: 'Boron Trichloride'},
                    {name: 'Boron Trifluoride'},
                    {name: 'Carbon Dioxide'},
                    {name: 'Carbon-Monoxide'},
                    {name: 'Deuterium'},
                    {name: 'Dichlorosilane'},
                    {name: 'Ethylene'},
                    {name: 'Ethane'},
                    {name: 'Helium'},
                    {name: 'Hydrogen'}
                ];

                self.showNext = ko.observable(false);
                self.showPrev = ko.observable(false);

                self.handleFilmStripPageChange = function(event, ui)
                {
                    var pageCount = ui.pageCount;
                    var pageIndex = ui.pageIndex;
                    self.showNext(pageIndex !== pageCount - 1);
                    self.showPrev(pageIndex > 0);
                };

                self.demoPrevPage = function(data, event)
                {
                    var filmStripArrow = $(":oj-filmstrip");
                    var currPage = filmStripArrow.ojFilmStrip("getPageIndex");
                    filmStripArrow.ojFilmStrip("goToPage", currPage - 1);
                };

                self.demoNextPage = function(data, event)
                {
                    var filmStripArrow = $(":oj-filmstrip");
                    var currPage = filmStripArrow.ojFilmStrip("getPageIndex");
                    filmStripArrow.ojFilmStrip("goToPage", currPage + 1);
                };

                self.getItemInitialDisplay = function(index)
                {
                    return index < 3 ? '' : 'none';
                };

                self.getPrevButtonDisplay = function()
                {
                    return self.showPrev() ? 'inline-block' : 'none';
                };

                self.getNextButtonDisplay = function()
                {
                    return self.showNext() ? 'inline-block' : 'none';
                };
                self.toggleFSWithArraow = function() {
                    var fsArrowDiaplayVar = ($('#filmstrip-navarrows-example').css('display'));
                    if (fsArrowDiaplayVar === 'block')
                        $("#filmstrip-navarrows-example").css('display', 'none');
                    else {
                        $("#filmstrip-navarrows-example").css('display', 'block');
                    }
                };
                self.setRootAttributes = function(event, data) {
                    $("#filmStripArrow").ojFilmStrip({"rootAttributes":
                                {
                                    'id': 'myId',
                                    'style': 'max-width:100%; color:blue;font-size: x-large;',
                                    'class': 'my-class'
                                }
                    });
                };

                self.destroyFS = function() {
                    $("#filmStripArrow").ojFilmStrip("destroy");
                    $('#eventText').append("filmStrip (id='diagram1') Component has been destroyed" + "<br>");
                };
                self.fsArrowCurrentItemUpdate = function(event, data) {
                };
                self.maxItemsPerPageUpdate = function(event, data) {
                    if (data.value) {
                        if (data.value[0] === "0")
                        {
                            self.maxItemsPerPageValue(0);
                        }
                        else if (data.value[0] === "1")
                        {
                            self.maxItemsPerPageValue(1);
                        }
                        else if (data.value[0] === "2")
                        {
                            self.maxItemsPerPageValue(2);
                        }
                        else if (data.value[0] === "3")
                        {
                            self.maxItemsPerPageValue(3);
                        }
                        else if (data.value[0] === "4")
                        {
                            self.maxItemsPerPageValue(4);
                        }
                        return true;
                    }
                };
                self.orientationUpdate = function(event, data) {
                    if (data.value) {
                        var values = $("#filmStripArrow").ojFilmStrip("option", "translations");
                        if (data.value[0] === "vertical")
                        {
                            self.orientationValue('vertical');
                        }
                        else if (data.value[0] === "horizontal")
                        {
                            self.orientationValue('horizontal');
                        }
                        return true;
                    }
                };
                $("#filmStripArrow").on("ojbeforepagechange", function(event, ui) {
                    console.log("ojbeforepagechange---- changed event");
                });
                
                $("#filmStripArrow").on({
                  'ojdestroy': function (event, data) {
                      $('span#destroyTextId').html("Filmstrip destroyed:: " + event.target.id + "<br>");
                      alert("destroy");
                      /*window.console.log("The DOM node id for the destroyed component is : %s", event.target.id);*/
                  }
                });
                 $("#filmStripArrow").on( "ojcreate", function( event, ui ) {
                    //alert("created");
                     console.log("Filmstrip created :: " + event.target.id + "<br>");
                    $('span#createTextId').html("Filmstrip created :: " + event.target.id + "<br>");
                 });
                $("#navArrowVisibilityRadioset").on({
                    'ojoptionchange': function(event, data) {
                    }
                });
                
                $("#navArrowPlacementRadioset").on({
                    'ojoptionchange': function(event, data) {
                    }
                });
                
                self.getItemsPerPage = function() {
                    var itemsPerPage = $("#filmStripArrow").ojFilmStrip("getItemsPerPage");
                    $('span#getItemsPerPageId').html("No of items  per page :: " + itemsPerPage + "<br>");
                };
                self.getPageCount = function() {
                    $('span#getPageCountId').html("No of page count :: " + $("#filmStripArrow").ojFilmStrip("getPagingModel").getPageCount() + "<br>");
                };
                self.pagingModel = null;


                self.goToSelectedPage = function(event, data) {
                    var filmStrip = $("#filmStripArrow");
                    var pagingModel = filmStrip.ojFilmStrip("getPagingModel");
                    self.pagingModel = pagingModel;
                    var totalPage = pagingModel.getPageCount();
                    var currentPage = pagingModel.getPage();
                    if ((currentPage + 1) === totalPage)
                    {
                        currentPage = 0;
                        pagingModel.setPage(currentPage);
                        $('span#goToPageId').html("Current Page  is:: " + currentPage + "<br>");
                    }
                    else
                    {
                        pagingModel.setPage(currentPage + 1);
                        $('span#goToPageId').html("Current Page  is:: " + currentPage + "<br>");
                    }
                };
                self.getPageIndex = function() {
                    $('#eventText').html("Page index :: " + $("#filmStripArrow").ojFilmStrip("getPagingModel").getStartItemIndex() + "<br>");
                    var startIndex=$("#filmStripArrow").ojFilmStrip("getPagingModel").getStartItemIndex();
                    var endIndex=$("#filmStripArrow").ojFilmStrip("getPagingModel").getEndItemIndex();
                    $('span#getPageIndexId').html("Start Page Index :: " + startIndex  + "  End Page Index :: " + endIndex +"<br>");
                    console.log($("#filmStripArrow").ojFilmStrip("getPagingModel").getStartItemIndex() + "   ::  "+ $("#filmStripArrow").ojFilmStrip("getPagingModel").getEndItemIndex());
                };
            }
            ;
            return FilmStripModel;
           
        }
);

