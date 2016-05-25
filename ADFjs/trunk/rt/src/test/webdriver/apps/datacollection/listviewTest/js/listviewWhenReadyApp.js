require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'promise', 'ojs/ojlistview', 'ojs/ojcollectiontabledatasource', 'ojs/ojmodel', 'mockjax', 'mockpagingrest'],
function(oj, ko, $)
{ 
    function lvmod(){
            var self=this; 
            self.buttonClick=function(){
            var ready=$("#listview").ojListView("whenReady");
                if (ready!=null || ready != undefined){
                    $("#promiseReturnedText").html("Promise returned");
                }
            
            console.log(ready);
            console.log($("#promiseReturnedText").innerHTML);
            //console.log($("#promiseReturnedText")innerHTML);//Promise {[[PromiseStatus]]: "resolved", [[PromiseValue]]: null}
        } 
    }
      
    $(document).ready(function() 
        {
            $.getJSON("js/listview_json/tweets.json",
                function (data) 
                {
                    // responseTime is only added so that the activity indicator is more noticeable
                        var server = new MockPagingRESTServer({"Tweets": data}, {collProp:"Tweets", id:"source", responseTime:1000});

                        var model = oj.Model.extend({
                            idAttribute: 'source'
                        });

                        var collection = new oj.Collection(null, {
                            url: server.getURL(),
                            fetchSize: 15,
                            model: model
                        });
                    ko.applyBindings({dataSource: new oj.CollectionTableDataSource(collection)}, document.getElementById('listview'));
                });  
            
            ko.applyBindings(new lvmod(), document.getElementById('buttonBar'));
        });
});	




/*

require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'promise', 'ojs/ojlistview','ojs/ojbutton', 'ojs/ojcollectiontabledatasource', 'ojs/ojmodel', 'mockjax', 'mockpagingrest'],
function(oj, ko, $)
{  
    var global;
    function lvmod(){
        var self=this;
        var datasrc;
         self.jsonDataSorce;
          self.data=     $.getJSON("js/listview_json/tweets.json",
                        function (data) 
                        {
                            var self=this;
                            // responseTime is only added so that the activity indicator is more noticeable
                            self.server = new MockPagingRESTServer({"Tweets": data}, {collProp:"Tweets", id:"source", responseTime:1000});

                            self.model = oj.Model.extend({
                                idAttribute: 'source'
                            });

                            self.collection = new oj.Collection(null, {
                                url: self.server.getURL(),
                                fetchSize: 15,
                                model: self.model
                            });

                            datasrc=new oj.CollectionTableDataSource(self.collection);
                           

                        }); 
                    self.jsonDataSorce=datasrc;
                    self.buttonClick=function(){
                    var ready=$("#listview").ojListView("whenReady");
                   // console.log(ready);
                      } 
                    //console.log(self.data);
                  self.jsonDataSorce= self.data.success(function(data){
                        
                        console.log("global :");
                        
                         global=data;
                       console.log(global);
                      return global;
                    })
                   console.log("json stored response is:")
                   console.log(self.jsonDataSorce);

                }
        var vm=new lvmod();
        //console.log(vm);
       //console.log(vm.data.responseJSON);
        $(document).ready(function(){          
            ko.applyBindings(vm, document.getElementById('wrapper'));
        }); 
             
    }
);
*/
