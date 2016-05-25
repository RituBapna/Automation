require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents'],
function(oj, ko, $)
{  
  oj.Logger.option("level", oj.Logger.LEVEL_INFO);
  
    function treeLayoutViewModel() {
         var self = this; 
         
        /* self.coreoptsFunc =  {
                     "animDuration": 2050,
                     "selectionMode" : "multiple", //none, single or multiple
                     "selectPrevOnDelete": true,
                     "expandParents": true,
                     "icons": true,
                     "rtl":false
                   }; */
                   
         
                   
        self.getBaseUrl = function() {
                    var href = window.location.href;
                    var lastIndexOfSlash = href.lastIndexOf("/");
                    var subStr = href.substring(0, lastIndexOfSlash);
                    return subStr;
                }
                self.baseurl = self.getBaseUrl();
                
                self.typesoptsFunc = {
                   "types": {
                              "myroot" :   {
                                              "image"  : self.baseurl +"/img/root.gif",
                                              "select" : function() { return false; },
                                              "delete" : function() { return false; },
                                              "move" :   function() { return false; }
                                           },
                              "myfolder" : {
                                              "image" : self.baseurl +"/img/folder.gif"
                                           },
                              "myleaf" :   {
                                             "image" :  self.baseurl +"/img/leaf.png"
                                           },
                              "noexpand" : {          
                                            "image" : self.baseurl +"/img/noexpand.gif"
                                         },
                              "default" : {          
                                             "image" : self.baseurl +"/img/default.gif"                                             
                                          }
                            }
                 };
                 
       
           
           self.treeFuncData = [
             { 
               "title": "Home",
               "attr": {"id": "home",  "type":"myleaf"}
             },
             { 
               "title": "News",
               "attr": {"id": "news",  "type":"myleaf"}
             },
             { 
               "title": "Blogs",
               "attr": {"id": "blogs",  "type":"myfolder"},
               "children": [ { "title": "Today",
                               "attr": {"id": "today",  "type":"myleaf"}
                             },
                             { "title": "Yesterday",
                               "attr": {"id": "yesterday",  "type":"myleaf"}
                             },
                             { "title": "2 Days Back",
                               "attr": {"id": "2daysback",  "type":"myleaf"}
                             },
                             { "title": "Archive",
                               "attr": {"id": "archive",  "type":"myleaf"}
                             }
                           ]
             },
             {
               "title": "Links", 
               "attr": {"id": "links",  "type":"myfolder"},
               "children": [ { "title": "Oracle",
                               "attr": {"id": "oracle"}
                             },
                             { "title": "IBM",
                               "attr": {"id": "ibm"}
                             },
                             { "title": "Microsoft",
                               "attr": {"id": "ms"},
                               "children": [ { "title": "USA",
                                               "attr": {"id": "msusa"},
                                               "children": [ { "title": "North",
                                                               "attr": {"id": "msusanorth"}
                                                             },
                                                             { "title": "South",
                                                               "attr": {"id": "msusasouth"}
                                                             },
                                                             { "title": "East",
                                                               "attr": {"id": "msusaeast"}
                                                             },
                                                             { "title": "West",
                                                               "attr": {"id": "msusawest"}
                                                             }
                                                           ]
                                             },
                                             { "title": "Europe",
                                               "attr": {"id": "msuerope"}
                                             },
                                             { "title": "Asia",
                                               "attr": {"id": "msasia"},
                                               "children": [ { "title": "Japan",
                                                               "attr": {"id": "asiajap"}
                                                             },
                                                             { "title": "China",
                                                               "attr": {"id": "asiachina"}
                                                             },
                                                             { "title": "India",
                                                               "attr": {"id": "asiaindia"}
                                                             }
                                                           ]
                                             }
                                           ]
                             }
                           ]
             },
             { 
               "title": "Sponsors",
               "attr": {"id": "sponsors"}
             },
             { 
               "title": "Corporate",
               "attr": {"id": "corporate"}
             },
             { 
               "title": "References",
               "attr": {"id": "references"},
               "children": [ { "title": "All",
                               "attr": {"id": "refsall"}
                             },
                             { "title": "USA",
                               "attr": {"id": "refsusa"}
                             },
                             { "title": "Europe",
                               "attr": {"id": "refseurope"}
                             },
                             { "title": "Asia",
                               "attr": {"id": "refsasia"}
                             }
                           ]
             },
             { 
               "title": "Suppliers",
               "attr": {"id": "sups"},
               "children": [ { "title": "Gold Tier",
                               "attr": {"id": "supgold"}
                             },
                             { "title": "Silver Tier",
                               "attr": {"id": "supsilver"}
                             },
                             { "title": "Non-contract",
                               "attr": {"id": "supcon"}
                             },
                             { "title": "Independent",
                               "attr": {"id": "supind"}
                             }
                           ]
             }
           ] ;
             
        
           self.dndoptsFunc = {
               "reorder" : true
           };
           
           
            self.layoutData = new oj.JsonTreeDataSource(self.treeFuncData) ;
            
            self.expandAreaOptions = ko.observableArray([ "", "disclosureIcon", "header"]);
            
            self.expandAreaValue = ko.observable("disclosureIcon");
            
            self.expandedOptions = ko.observableArray([true,false]);
            
            self.expandedValue = ko.observable(true);
            
            self.disabledOptions = ko.observableArray([true,false]);
            
            self.disabledValue = ko.observable(false);            
            
            self.expandOnOptions = ko.observableArray(["","click","mouseover"]);
            
            self.expandOnValue = ko.observable("click");
            
            
//************************************ START Menu Event Handling **************************

                self.menu11ItemSelect = function( event, ui ) {
                    oj.Logger.info("In menu11ItemSelect ...");                    
                    var itemSel = ui.item.children("a").text();
                    $("#resultsLayoutContainer").html("<label> Selected menu Item: " + itemSel + "<\/label><br/>");
                }
                
                self.menu22ItemSelect = function( event, ui ) {
                    oj.Logger.info("In menu22ItemSelect ...");
                    var itemSel = ui.item.children("a").text();
                    $("#resultsLayoutContainer").html("<label> Selected menu Item: " + itemSel + "<\/label><br/>");
                }
                
        
//************************************ END Menu Event Handling **************************  


        self.collapseOrExpandCollapsible = function(d, e){
               oj.Logger.info("In collapseCollapsible ...");
               var expanded = $( "#ojCollapsibleWithBox" ).ojCollapsible( "option", "expanded" );
               if(expanded)
                    $("#ojCollapsibleWithBox").ojCollapsible("option", "expanded", false );
               else
                   $("#ojCollapsibleWithBox").ojCollapsible("option", "expanded", true );
               
                    
               expanded1 = $( "#ojCollapsibleWithBox" ).ojCollapsible( "option", "expanded" );
        }
        
        self.addContextmenu = function(d, e){
            oj.Logger.info("In addContextmenu ...");               
            var menu = $( "#treeBoxLoadDatasource" ).ojTree( "option", "contextMenu" );
            $("#treeBoxLoadDatasource").ojTree("option","contextMenu", "#myMenu22");            
             
        }
        
        self.removeContextmenu = function(d, e){
            oj.Logger.info("In removeContextmenu ...");               
            var menu = $( "#treeBoxLoadDatasource" ).ojTree( "option", "contextMenu" );
            $("#treeBoxLoadDatasource").ojTree("option","contextMenu", "");
             
        }
        
        //self.dirValues = [ "ltr", "rtl"];
        self.langDir = ko.observable("rtl");
        self.toggle = function() {
                var lang = self.langDir;
                if(lang === 'ltr') self.langDir = "rtl";
                else self.langDir = "ltr";
                $("#treeBoxLoadDatasource").ojTree("refresh"); 
        }
        
        
        


     
}
    
    vmLayoutTree = new treeLayoutViewModel();
    
   $(document).ready(function(){
       ko.applyBindings(vmLayoutTree, document.getElementById('bodyContainer'));
       
            $("#ojCollapsibleWithBox").on("ojbeforecollapse", function(e, ui) {
                   oj.Logger.info("In ojCollapsible ojbeforecollapse event");
                  $("#resultsFunction1").html("<label> <b> ojbeforecollapse event method -- <\/b> Function Name=" + ui.func + "<\/label>");                             
            }); 
            
            $("#ojCollapsibleWithBox").on("ojbeforeexpand", function(e, ui) {
                   oj.Logger.info("In ojCollapsible ojbeforeexpand event");
                  $("#resultsFunction1").html("<label> <b> ojbeforeexpand event method -- <\/b> Function Name=" + ui.func + "<\/label>");                             
            }); 
            
            $("#ojCollapsibleWithBox").on("ojcollapse", function(e, ui) {
                   oj.Logger.info("In ojCollapsible ojcollapse event");
                  $("#resultsFunction1").html("<label> <b> ojcollapse event method -- <\/b> Function Name=" + ui.func + "<\/label>");                             
            }); 
            
            $("#ojCollapsibleWithBox").on("ojexpand", function(e, ui) {
                   oj.Logger.info("In ojCollapsible ojexpand event");
                  $("#resultsFunction1").html("<label> <b> ojexpand event method -- <\/b> Function Name=" + ui.func + "<\/label>");                             
            }); 
        
    });
    
});         