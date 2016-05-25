require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'promise', 'ojs/ojlistview', 'ojs/ojbutton'],
function(oj, ko, $)
{
        var ListviewNoneToBlockModel = function () 
        { 
            var self = this; 
            self.showHierarchicalStaticContentLV = function() { 
            //$( "#hierarchicalStaticContentListView" ).ojListView("refresh" ); 
           // $("#listView").css('display', 'block'); 
              //  var displayVal=$("#listview").ojListView('widget').css('display'); 
                
                    $("#listview").ojListView('widget').css('display', 'block'); 
                
        }; 
            self.getDisplayVal=function(){
                
             $("#loadOutputPage").html( $("#listview").ojListView('widget').css('display'));  
            };

    }; 
    
    $(document).ready(
        function(){
            $('#loadOutputPage').load("../../common/output.html",
                  function(){
                    ko.applyBindings(new ListviewNoneToBlockModel(),document.getElementById('wrapperDiv'));
                    
                    $("#getDisplayVal").on('click', new ListviewNoneToBlockModel().getDisplayVal);
           
                //$("#getDisplayVal").on({'click': new ListviewNoneToBlockModel().getDisplayVal()});
        })
    })
    
});	