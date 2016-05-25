require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents', 'ojs/ojselectcombobox', 
    'ojs/ojtable', 'ojs/ojarraytabledatasource', 'ojs/ojpagingtabledatasource', 'ojs/ojdatagrid', 
    'ojs/ojpagingdatagriddatasource', 'ojs/ojcollectionpagingdatasource', 'ojs/ojcollectiontabledatasource', 'ojs/ojcollectiondatagriddatasource', 'mockjax', 'mockpagingrest'],
        function(oj, ko, $)
        {
            var serverLocation = "http://10.88.188.122:8001/";
            
            oj.Logger.option("level", oj.Logger.LEVEL_INFO);
            function departmentsVM() {
                var self = this;

                self.deptToFetch = ko.observable("12");

                self.mydeptno = ko.observable("");



                self.serviceURL = serverLocation + 'AdfBCApp-RESTWebService-context-root/rest/main/DeptView1';

                self.Department1 = oj.Model.extend({
                    urlRoot: serverLocation + "AdfBCApp-RESTWebService-context-root/rest/main/DeptView1",
                    url: function() {
                        var base = serverLocation + 'AdfBCApp-RESTWebService-context-root/rest/main/DeptView1';
                        if (this.isNew())
                            return base;
                        else
                            return base + "/" + this.get(this.idAttribute);
                    },
                    parse: parseDept,
                    parseSave: parseSaveDept,
                    idAttribute: 'Deptno',
                    validate: function(attrs, options) {
                        console.log("In Validate", JSON.stringify(attrs));
                    },
                    /*sync: function(method, attrs, options) {
                        console.log("In Sync: called ", method);
                    },*/
                    //Check in your console that the log dispatched first the custom event for attributes and then the global event
                    change: function(event) {
                        console.log('MODEL  have been changed:', this.toJSON());
                        console.log("hasChanged: " + this.hasChanged())
                       // console.log("changedAttributes: " + JSON.stringify(this.changedAttributes()))
                        console.log("changed: " + this.changed)
                        console.log("previous: " + this.previous("Dname"))
                        console.log("all attributes: " + this.attributes)
                    },
                    nameChangeHandler: function(event) {
                        console.log('NAME have been changed:', this.toJSON());
                        console.log("hasChanged: " + this.hasChanged())
                      // console.log("changedAttributes: " + JSON.stringify(this.changedAttributes()))
                        console.log("changed: " + this.changed)
                        console.log("previous: " + this.previous("Dname"))
                        console.log("all attributes: " + this.attributes)
                    },
                    idChangeHandler: function(event) {
                        console.log('ID have been changed:', this.toJSON());
                        console.log("hasChanged: " + this.hasChanged())
                       // console.log("changedAttributes: " + JSON.stringify(this.changedAttributes()))
                        console.log("changed: " + this.changed)
                        console.log("previous: " + this.previous("Deptno"))
                        console.log("all attributes: " + this.attributes)
                    }
                });

                
                self.myDept1 = new self.Department1();
                var DeptCollection1 = oj.Collection.extend({
                    url: serverLocation + "AdfBCApp-RESTWebService-context-root/rest/main/DeptView1",
                    model: self.myDept1,
                    parse: parseDepts,
                    modelLimit: -1,
                    comparator: "Dname",
                    sortDirection: -1,
                    sortSupported: true,
                    fetchSize: 5
                });
                
                  
                

                self.DeptColl = ko.observable();
                self.DeptColl(new DeptCollection1());
                self.lengthOfColl = ko.observable(0);

                 //Fetch needs to be done only when not using datasources
                self.DeptColl().fetch({
                    success: function(collection, response, options) {
                        self.lengthOfColl = collection.length;
                        console.log("Collection isEmpty method result:     " + self.DeptColl().isEmpty());
                        console.log("Collection Department  length: " + collection.length);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.log('Error in fetch: ' + textStatus);
                    }
                });
                
                
                self.sortWithIndex = function(d,e){
                    var strResult = "<label>";
                    
                    self.DeptColl().sort({startIndex: 7}).then(function (actual)
                    {
                        strResult +=  "Model Length: " + actual.models.length + "<br/>" ;
			strResult +=  "LastFetchCount: " + self.DeptColl().lastFetchCount + "<br/>" ;
			strResult +=  "<\/label>";
			
                        $("#resultsFunction").html(strResult);
                    });
                }
                
                
                self.testSetRangeLocal = function(d,e){
                    
                   self.DeptColl().setRangeLocal(0, 10).then(function (actual) 
                    { 
                        self.DeptColl().on('remove', function() { 
                            self.DeptColl().setRangeLocal(0, 10).then(function(actual) 
                            { 
                                self.DeptColl().at(0).then(function(model){
                                        console.log("Model at index 0" + JSON.stringify(model))
                                     }); 
                            }); 
                        }); 
                        self.DeptColl().get('7').then(function(model) { 
                           if (model != null) {
				   console.log("found model 7 and deleting it");
				   $("#resultsFunction").html("<label>found model 7 and deleting it<\/label>");
				   model.destroy();
			       }
			       else{
				   $("#resultsFunction").html("<label>Did not find model 7 and deleting it<\/label>");
				   console.log("Did not find model 7 and deleting it");
				}
                             
                        }); 
                    }); 
           
                }
                
                self.testSyncMethodOptions = function(d, e) {
			self.myDept = new self.Department1();
			self.deptCol = new DeptCollection1()
			var saveSync = this.deptCol.sync;
			var result = "";
			self.deptCol.sync = function(method, model, options) {
			    // Check for paging options
			    result += "FetchSize:" + options.fetchSize + "<br/>";
			    result += "Sort:" + options.sort + "<br/>";
			    result += "SortDir:" + options.sortDir + "<br/>";
			    $("#resultsFunction").html("<label> " + result + " <\/label>");
			    return oj.sync(method, model, options);
			};


			self.deptCol.on(oj.Events.EventType['SYNC'],function(eventType,collection){
			    console.log("Inside Sync Method Event");
			});

		
			self.deptCol.fetch({
				success: function(collection, response, options) {
				    console.log("Collection Department  length: " + collection.length);
				},
				error: function(jqXHR, textStatus, errorThrown) {
				    console.log('Error in fetch: ' + textStatus);
				}
			});
                }


                self.addEventToCollection = function(eventType,collection){
                    console.log('inside addevent coll');
                   // console.log(JSON.stringify(collection));
                }
                
                self.removeEventToCollection = function(eventType,collection){
                    console.log('inside removeevent coll');
                  ////  console.log(JSON.stringify(collection));
                }
                
                self.requestEventToCollection = function(eventType,collection){
                    console.log('inside requestEventToCollection coll');
                   // console.log(JSON.stringify(collection));
                }
                
                self.resetEventToCollection = function(eventType,collection){
                    console.log('inside resetEventToCollection coll');
                   // console.log(JSON.stringify(collection));
                }
                
                self.refreshEventToCollection = function(eventType,collection){
                    console.log('inside refreshEventToCollection coll');
                   // console.log(JSON.stringify(collection));
                }
                
                self.changeEventToCollection = function(eventType,collection){
                    console.log('inside changeEventToCollection coll');
                   // console.log(JSON.stringify(collection));
                }
                
                self.allEventToCollection = function(eventType,collection){
                    console.log('inside allEventToCollection coll'+ eventType);
                   // console.log(JSON.stringify(collection));
                }
                
                self.sortEventToCollection = function(eventType,collection){
                    console.log('inside sortEventToCollection coll');
                  //  console.log(JSON.stringify(collection));
                }
                
                self.destroyEventToCollection = function(eventType,collection){
                    console.log('inside destroyEventToCollection coll');
                   /// console.log(JSON.stringify(collection));
                }
                
                self.errorEventToCollection = function(eventType,collection){
                    console.log('inside invalidEventToCollection coll');
                   // console.log(JSON.stringify(collection));
                }
                
                self.invalidEventToCollection = function(eventType,collection){
                    console.log('inside errorEventToCollection coll');
                   // console.log(JSON.stringify(collection));
                }
                
                self.allremovedEventToCollection = function(eventType,collection){
                    console.log('inside allremovedEventToCollection coll');
                   // console.log(JSON.stringify(collection));
                }
                
                self.alladdedEventToCollection = function(eventType,collection){
                    console.log('inside alladdedEventToCollection coll');
                  //  console.log(JSON.stringify(collection));
                }
                
                

                //self.DeptCollClone = self.DeptColl().clone();



                self.collTableDatasource = ko.observable();
                self.collTableDatasource(new oj.PagingTableDataSource(new oj.CollectionTableDataSource(self.DeptColl())));
               // $('#paging').ojPagingControl('page', self.collTableDatasource().getPage()); 
                //  self.collTableDatasource(new oj.CollectionTableDataSource(self.DeptColl()));
                
                
                self.refreshDeptCollection = function(d, e) {
                    self.DeptColl().refresh();
                    
                }

                self.index = ko.observable("0");

                self.getModelAt = function(d, e) {
                    self.DeptColl().at(parseInt(self.index())).then(function(model){
                        console.log(JSON.stringify(model));
                        $("#resultsFunction").html(JSON.stringify(model));    
                    });
                    
                }

                self.hasMoreWithNoTotalResults = function (d, e) {
                    
                    var index = self.DeptColl().length -1 ;
                    if(self.DeptColl().hasMore == true){
                        self.DeptColl().at(index).then(function (model) {
                            var result;
                            result = "HasMore: "+ self.DeptColl().hasMore;
                            result += ", TotalResults: " + self.DeptColl().totalResults;
                            $("#resultsFunction").html(result);
                        });
                    }

                }

                
                
                self.displayModelUsingEach = function (d, e) {
	                           console.log("**********displayModelUsingEach***********")
	                           var result = "";
	                           try{
	                               self.DeptColl().each(function (object) {
	                                   var attributes = object.attributes;
	                                   console.log(JSON.stringify(attributes));
	                                   result += "," + JSON.stringify(attributes.Deptno);
	                               })
	                           } catch (err) {
	                                   result = "Message: " + err.message;
	                                   console.log(result);
	                            }
	                               $("#resultsFunction").html("<label>" + result + "<\/label>");
	                       };
	       
	       self.displayModelUsingFilter = function (d, e) {
		   var result = "";
		   try{
			   var arrayReturn = self.DeptColl().filter(function (object) {
			   var attributes = object.attributes;
			   var deptno = attributes.Deptno;
			   if (deptno == 10 || deptno == 30)
			       return true;
			   else
			       return false;
			   });
		   } catch (err) {
			   result = "Message: " + err.message;
			   console.log(result);
		    }
		       $("#resultsFunction").html("<label>" + result + "<\/label>");
	       
                };

                self.findWhereByDeptNo = function(d, e) {
                    var deptNum = Number(self.deptToFetch());
                    self.DeptColl().findWhere({Deptno: deptNum}).then(function(model){
                        console.log("**********findWhereByDeptNo***********")
                        console.log("Collection isEmpty method result:     " + self.DeptColl().isEmpty());
                        console.log(JSON.stringify(model));
                        $("#resultsFunction").html("<label>"+ JSON.stringify(model) + "<\/label>");
                        console.log("**********************************")
                    });
                }

                self.getLastIndexOfModel = function(d, e) {
                    var modelNotThere = self.DeptColl().get("100").then(function (model) {
			    console.log("**********getLastIndexOfModel***********");
			    var result = "";
			    try {
				var index = self.DeptColl().lastIndexOf(model, 3);

			    } catch (err) {
				result = "Message: " + err.message;
				console.log(result);
			    }
			    $("#resultsFunction").html("<label>" + result + "<\/label>");
                    });
                    
                }

                self.removeDept = function(d, e) {
                    self.DeptColl().get(self.deptToFetch()).then(function(model){
                        console.log("**********removeDept***********");
                        self.DeptColl().remove(model);
                        $("#resultsFunction").html("<label>" + "Length: "+ self.DeptColl().length + "<\/label>");
                        console.log("Department Removed");
                      //  $('#paging').ojPagingControl('page', self.collTableDatasource().getPage());
                      //self.collTableDatasource().setPage(self.collTableDatasource().getPage()); 
                        console.log("**********************************");
                    });
                }


                self.getFirstModel = function(d, e) {
                    self.DeptColl().first().then(function(model){
                        console.log("**********getFirstModel***********")
                        console.log(JSON.stringify(model));
                        $("#resultsFunction").html(JSON.stringify(model));
                        console.log("**********************************")
                    });
                    
                }

                self.getFirst5Model = function(d, e) {
                  self.DeptColl().first(5).then(function(modelArray){
                        console.log("**********getFirst5Model***********");
                        var result ="";
                        for (i = 0; i < modelArray.length; i++){
                            console.log(JSON.stringify(modelArray[i]));
                            result += "," + JSON.stringify(modelArray[i].get("Deptno"));
                         }
                        $("#resultsFunction").html(result);
                        console.log("**********************************")
                    });
                }

                self.getLastModel = function(d, e) {
                    var model = self.DeptColl().last().then(function(model){
                        console.log("**********getLastModel***********")
                        console.log(JSON.stringify(model));
                        $("#resultsFunction").html(JSON.stringify(model))
                        console.log("**********************************")
                    });
                }

                self.getLast5Model = function(d, e) {
                    self.DeptColl().last(5).then(function(modelArray){
                        console.log("**********getLast5Model***********");
                        var result ="";
                        for (i = 0; i < modelArray.length; i++){
                            console.log(JSON.stringify(modelArray[i]));
                            result += "," + JSON.stringify(modelArray[i].get("Deptno"));
                          }
                          $("#resultsFunction").html(result);
                        console.log("**********************************")
                    });
                }

                self.getMax = function(d, e) {
                    var result = "";
		    try{
			    var model = self.DeptColl().max(function(object) {
				var attributes = object.attributes;
				var deptno = attributes.Deptno;
				return deptno;
			    });
                    } catch (err) {
		    			   result = "Message: " + err.message;
		    			   console.log(result);
		    		    }
		       $("#resultsFunction").html("<label>" + result + "<\/label>");
                    
                }

                self.getMin = function(d, e) {
                    var result = "";
		    try{
				var model = self.DeptColl().min(function(object) {
				var attributes = object.attributes;
				var deptno = attributes.Deptno;
				return deptno;
			    });
                    } catch (err) {
				   result = "Message: " + err.message;
				   console.log(result);
		    		    		    }
		       $("#resultsFunction").html("<label>" + result + "<\/label>");
                }

                self.pluckAllDeptByName = function (d, e) {
		     var result = "";
		    try {
			var modelArray = self.DeptColl().pluck("Dname");} catch (err) {
			result = "Message: " + err.message;
			console.log(result);
		    }
		    $("#resultsFunction").html("<label>" + result + "<\/label>");
		}


                self.pushNewDept = function(d, e) {
                    var newDeptModel = new self.Department1({Deptno: '15', Dname: 'DEFAULT PLUCK DEPT',
                        Loc: 'DEFAULT LOC',
                        link: serverLocation + 'AdfBCApp-RESTWebService-context-root/rest/main/DeptView1/15/child/EmpView'});

                    console.log("**********pushNewDept***********");
                    var addedDept = self.DeptColl().push(newDeptModel, {at: self.DeptColl().length, merge: true}).then(function(addedDept){
                        console.log("Department Pushed at index " + self.DeptColl().length + ":  " + JSON.stringify(addedDept));
                        $("#resultsFunction").html("Department Pushed at index " + self.DeptColl().length + ":  " + JSON.stringify(addedDept));
                        console.log("**********************************");
                    });
                }

                self.popNewDept = function(d, e) {
                    var newDeptModel = new self.Department1({Deptno: '15', Dname: 'DEFAULT PLUCK DEPT',
                        Loc: 'DEFAULT LOC',
                        link: serverLocation + 'AdfBCApp-RESTWebService-context-root/rest/main/DeptView1/15/child/EmpView'});

                    console.log("**********popNewDept***********");
                    var addedDept = self.DeptColl().pop(newDeptModel).then(function(addedDept){
                        console.log("Department popped:    " + JSON.stringify(addedDept));
                        $("#resultsFunction").html("Department popped:    " + JSON.stringify(addedDept));
                        console.log("**********************************");
                    });
                }

                self.shiftDept = function(d, e) {
                    console.log("**********shiftDept***********");
                    var shiftDept = self.DeptColl().shift().then(function(shiftDept){
                        console.log("Department Shifted:    " + JSON.stringify(shiftDept));
                        $("#resultsFunction").html("Department Shifted:    " + JSON.stringify(shiftDept));
                        console.log("**********************************");
                    });
                }

                self.unShiftDept = function(d, e) {
                    console.log("**********unShiftDept***********");
                    var newDeptModel = new self.Department1({Deptno: '30', Dname: 'SALES',
                        Loc: 'CHICAGO',
                        link: serverLocation + 'AdfBCApp-RESTWebService-context-root/rest/main/DeptView1/30/child/EmpView'});

                    var unShiftDept = self.DeptColl().unshift(newDeptModel).then(function(unShiftDept){
                        console.log("Department UnShifted:    " + JSON.stringify(unShiftDept));
                        $("#resultsFunction").html("Department UnShifted:    " + JSON.stringify(unShiftDept));
                        console.log("**********************************");
                    });
                }

                self.sliceDept = function(d, e) {
                    console.log("**********shiftDept***********");
                    var slicedDeptArray = self.DeptColl().slice(3).then(function(slicedDeptArray){
                        console.log("New Sliced Department");
                        var result ="";
                        for (i = 0; i < slicedDeptArray.length; i++){
                            console.log(JSON.stringify(slicedDeptArray[i]));
                            result += slicedDeptArray[i].get("Deptno") + ",";

                         }
                         $("#resultsFunction").html("Department Sliced: "+ result);
                        console.log("**********************************");
                    });
                }
                
                self.setNewDataInCollection = function(d, e) {
                    console.log("**********setNewDataInCollection***********");
                    
                    var newDeptModel = new self.Department1({Deptno: '11', Dname: 'name',
                        Loc: 'location'});
                    console.log("before set"+ JSON.stringify(newDeptModel));
                    
                    self.DeptColl().set(newDeptModel).then(function(newDeptModel){                        
                        console.log("inside set"+ JSON.stringify(newDeptModel));
                    });
                }

               /* self.sortByOptions = ko.observableArray([{value: '1', label: 'Deptno'},
                    {value: '2', label: 'Dname'},
                    {value: '3', label: 'Loc'}
                ]);



                self.sortBy = function(context, valueParam) {
                    console.log("**********sortBy***********");
                    var sortedDeptArray = self.DeptColl().sortBy(function(model) {
                        if (valueParam.option == "value") {
                            var newVal = valueParam.value;
                            if (newVal == '1')
                                return model.get("Deptno");
                            else if (newVal == '2')
                                return model.get("Dname");
                            else if (newVal == '3')
                                return model.get("Loc");
                        }
                    });
                    var result ="";
                    for (i = 0; i < sortedDeptArray.length; i++){
                        console.log(JSON.stringify(sortedDeptArray[i]));
                        result += sortedDeptArray[i].get("Deptno") + ",";
                    }
                    $("#resultsFunction").html(result);
                    console.log("**********************************");
                    
                    var indexBy = self.DeptColl().indexBy(function(model) {
                        if (valueParam.option == "value") {
                            var newVal = valueParam.value;
                            if (newVal == '1')
                                return model.get("Deptno");
                            else if (newVal == '2')
                                return model.get("Dname");
                            else if (newVal == '3')
                                return model.get("Loc");
                        }
                    });
                    console.log("indexBy"+ JSON.stringify(indexBy));
                }
*/

                self.withoutModels = function(d, e) {
                    console.log("**********WithoutModels test***********");
                    var newDeptModel = new self.Department1({Deptno: '30'});
                    var newDeptModel1 = new self.Department1({Deptno: '20'});
			
		    var result = "";
		    try{
			    var withoutDeptArray = self.DeptColl().without(newDeptModel, newDeptModel1).then(function(withoutDeptArray){
				var result ="";
				for (i = 0; i < withoutDeptArray.length; i++){
				    console.log(JSON.stringify(withoutDeptArray[i]));
				    result += withoutDeptArray[i].get("Deptno") + ",";			                    
				 }
				 $("#resultsFunction").html("Departments Present: "+ result);
				 console.log("**********************************");
			    });
                    } catch (err) {
		    				   result = "Message: " + err.message;
		    				   console.log(result);
		    		    		    		    }
		       $("#resultsFunction").html("<label>" + result + "<\/label>");
                    
                }




                self.noMoreData = ko.observable(false);
                self.prevLength = ko.observable(0);

                self.fetchMore = function(d, e) {

                    var length = self.DeptColl().length;
                     console.log("Length: "+ length);
                    if (self.prevLength() != length) {
                        self.DeptColl().at(self.DeptColl().length, {fetchSize: 3}).then(function(model){
                            console.log("Length: "+ self.DeptColl().length);
                            self.prevLength(length);
                            });
                    }
                    else
                        self.noMoreData(true);
                }


                self.displayCollectionProperties = function(d, e) {

                    var comparator = self.DeptColl().comparator;
                    var fetchSize = self.DeptColl().fetchSize;
                    var lastFetchCount = self.DeptColl().lastFetchCount;
                    var lastFetchSize = self.DeptColl().lastFetchSize;
                    var length = self.DeptColl().length;
                    var modelLimit = self.DeptColl().modelLimit;
                    var sortSupported = self.DeptColl().sortSupported;
                    var totalResults = self.DeptColl().totalResults;
                    var url = self.DeptColl().url;


                    var results = "<label> Comparator:     " + comparator + ", <\/label>";
                    results += "<label> fetchSize:     " + fetchSize + "<\/label><br/>";
                    results += "<label> lastFetchCount:     " + lastFetchCount + ", <\/label>";
                    results += "<label> lastFetchSize:     " + lastFetchSize + "<\/label><br/>";
                    results += "<label> length:     " + length + ", <\/label>";
                    results += "<label> modelLimit:     " + modelLimit + "<\/label><br/>";
                    results += "<label> sortSupported:     " + sortSupported + ", <\/label>";
                    results += "<label> totalResults:     " + totalResults + "<\/label><br/>";
                    results += "<label> url:     " + url + "<\/label>";

                    $("#resultsFunction").html(results);
                }



                self.displayModelProperties = function(d, e) {
                    
                    var dept = new self.Department1({Deptno: self.deptToFetch()});
                    console.log("dept"+ JSON.stringify(dept));
                    self.DeptColl().get(dept).then(function(newDeptModel){
                        console.log("Model got using get method from collection" + JSON.stringify(newDeptModel));
                    
                        console.log("Model got using new Model class" + JSON.stringify(newDeptModel));

                        var attributes = newDeptModel.attributes;
                        console.log("Attributes:  Deptno:  " + attributes["Deptno"] + ", Dname: " + attributes["Dname"] + ", Loc:" + attributes["Loc"]);
                        $("#resultsFunction").html("<label>Attributes:  Deptno:  " + attributes["Deptno"] + ", Dname: " + attributes["Dname"] + ", Loc:" + attributes["Loc"] + "<\/label>");

                        if (newDeptModel.has("Deptno"))
                            console.log("Deptno:   " + newDeptModel.get("Deptno"));
                        else
                            console.log("No property by name Deptno");
                        console.log("Dname:   " + newDeptModel.get("Dname"));
                        console.log("Loc:   " + newDeptModel.get("Loc"));
                        if (newDeptModel.has("Dummy"))
                            console.log("Dummy:   " + newDeptModel.get("Dummy"));
                        else
                            console.log("No property by name Dummy");
                     });

                }

                self.modelAttributes = function(d, e) {

                          var newDeptModel = new self.Department1({Deptno: '30', Dname: 'SALES',
			Loc: 'CHICAGO',
			link: serverLocation + 'AdfBCApp-RESTWebService-context-root/rest/main/DeptView1/30/child/EmpView'});

		    var attributes = newDeptModel.attributes;
		    console.log("Attributes:  Deptno:  " + attributes.Deptno + ", Dname: " + attributes.Dname + ", Loc:" + attributes.Loc);

		    var result = "Attributes:  Deptno:  " + attributes["Deptno"] + ", Dname: " + attributes["Dname"] + ", Loc:" + attributes["Loc"];

		    var pairValue = newDeptModel.pairs();
		    console.log("*********Pairs() method value result*********");
		    result += "<br/>" ;
		    for (i = 0; i < pairValue.length; i++){
			console.log(JSON.stringify(pairValue[i]));
			result += " " + JSON.stringify(pairValue[i]);
		    }

		    var keys = newDeptModel.keys();
		    var values = newDeptModel.values();

		    console.log("*********keys/values() method value result*********");
		    result += "<br/>" ;
		    for (i = 0; i < keys.length; i++){
			console.log(JSON.stringify(keys[i]) + ": " + values[i]);
			result += " " + JSON.stringify(keys[i]) + ": " + values[i];
		    }


		    var keyAttr = ["Deptno", "Dname"];
		    var modelAttr1 = newDeptModel.pick(keyAttr);
		    console.log("*********pick() method value result (Shd have only Deptno and Dname)*********")
		    console.log(JSON.stringify(modelAttr1));
		    result += "<br/>" +  JSON.stringify(modelAttr1);

		    var omitKeys = ["Dname", "Loc"];
		    var omitModel = newDeptModel.omit(omitKeys);
		    console.log("*********omitModels() method value result(Shd have only Deptno and link)*********")
		    console.log(JSON.stringify(omitModel));
		    result += "<br/>" +  JSON.stringify(omitModel);

		    var invertValue = newDeptModel.invert();
		    console.log("*********invert() method value result*********")
		    console.log(JSON.stringify(invertValue));
		    result += "<br/>" + JSON.stringify(invertValue);

		    console.log("****URL:  ******" + newDeptModel.url());
		    $("#resultsFunction").html("<label>"+ result + "<\/label>");
			





                }

                self.unSetandSet = function(d, e) {

                    self.DeptColl().get(self.deptToFetch()).then(function(model){
                        console.log("Model got using get method from collection" + JSON.stringify(model));

                        console.log("Deptno has changed: " + model.hasChanged(["Deptno"]))
                        console.log("Dname has changed: " + model.hasChanged(["Dname"]))
                        console.log("Loc has changed: " + model.hasChanged(["Loc"]))
                        console.log("link has changed: " + model.hasChanged(["link"]))

                        var result = "Loc has changed originally: " + model.hasChanged(["Loc"]);

                        var locValue = model.get("Loc");
                        var linkValue = model.get("link");

                        model.unset("Loc");

                        result += "<br/>" + "Loc has changed initially: " + model.hasChanged(["Loc"]);

                        console.log("Loc has changed initially: " + model.hasChanged(["Loc"]))
                        console.log(" All changedAttributes: " + JSON.stringify(model.changedAttributes()));
                        model.unset("link");
                        console.log("link has changed: " + model.hasChanged(["link"]))
                        console.log(" All changedAttributes: " + JSON.stringify(model.changedAttributes()));

                        console.log("Model after unsetting Loc and link attribute: " + JSON.stringify(model));

                        model.set("Loc", locValue);
                        console.log("Loc has changed afterwards: " + model.hasChanged(["Loc"]))
                        result +=  "<br/>" + "Loc has changed afterwards: " + model.hasChanged(["Loc"]);
                        console.log(" All changedAttributes: " + JSON.stringify(model.changedAttributes()));

                        model.set("link", linkValue);
                        console.log("link has changed: " + model.hasChanged(["link"]))
                        console.log(" All changedAttributes: " + JSON.stringify(model.changedAttributes()));
                        console.log("Model after setting Loc and link attribute again: " + JSON.stringify(model));

                        $("#resultsFunction").html("<label>"+ result + "<\/label>");
                    });
                }


                self.changeSortDirection = function(d, e) {
                    var direction = self.DeptColl().sortDirection;
                    console.log('before:' + direction);
                    if (direction == 1)
                        self.DeptColl().sortDirection = -1;
                    else
                        self.DeptColl().sortDirection = 1;
                    self.DeptColl().sort();
                  //  $("deptTable").ojTable("refresh");
                    console.log('after: ' + self.DeptColl().sortDirection);

                }


                self.comparatorOptions = ko.observableArray([{value: 'Deptno', label: 'Deptno'},
                    {value: 'Dname', label: 'Dname'},
                    {value: 'Loc', label: 'Loc'}
                ]);


                self.changeComparator = function(context, valueParam) {
                    if (valueParam.option == "value") {
                        var newVal = valueParam.value;
                        if (newVal == 'Deptno')
                            self.DeptColl().comparator = 'Deptno';
                        else if (newVal == 'Dname')
                            self.DeptColl().comparator = 'Dname';
                        else if (newVal == 'Loc')
                            self.DeptColl().comparator = 'Loc';

                        self.DeptColl().sort(2);
                    }

                }

                self.addDepartment = function(formElement, event) {
                    console.log("Dname" + $('#newDepartId').val() + $('#newDepartName').val());
                    var newDeptModel = new self.Department1({Deptno: $('#newDepartId').val(), Dname: $('#newDepartName').val(),
                        Loc: $('#newLoc').val(),
                        link: serverLocation + 'AdfBCApp-RESTWebService-context-root/rest/main/DeptView1/' + $('#newDepartId').val() + '/child/EmpView'});

                	var jsonModel = JSON.stringify(newDeptModel);
                    console.log("json: : " + jsonModel + " idAttribute: " + newDeptModel.idAttribute + " id: " + newDeptModel.id + "  isNew() " + newDeptModel.isNew() + " cid: " + newDeptModel.cid);

                    var attributes = newDeptModel.attributes;
                    console.log("Attributes:  Deptno:  " + attributes["Deptno"] + ", Dname: " + attributes["Dname"] + ", Loc:" + attributes["Loc"]);


                    console.log('before length' + self.DeptColl().length);
                    var return1 = self.DeptColl().create(newDeptModel, {
                        'contentType': 'application/vnd.oracle.adf.resourceitem+json',
                        wait: true, 
                        success: function(collection, response, options) {
                            console.log(response);
                            console.log('Success in Create'+options.xhr.status);
                            //workaround for bug
                            //self.collTableDatasource().setPage(self.collTableDatasource().getPage()); 
                            $('#paging').ojPagingControl('page', self.collTableDatasource().getPage()); 
                
                            
                        },
                        error: function(jqXHR, textStatus, obj) {
                            self.DeptColl().remove(newDeptModel);
                            console.log('Error in Create: ' + JSON.stringify(jqXHR));
                            console.log('Error so remove the model'+obj.toString());
                            console.log('Error in Create: ' + JSON.stringify(obj.xhr));
                            
                        }
                    });
                    //console.log(JSON.stringify(return1));
                    console.log('after length' + self.DeptColl().length);
                    
                }

                self.editDept = function(viewModel, event) {

                    console.log("dept to update: " + event.currentTarget.id);
                    $("#update-form #deptno1").val(viewModel.Deptno);
                    $("#update-form #dname1").val(viewModel.Dname);
                    $("#update-form #loc1").val(viewModel.Loc);

                    $("#update-form").ojDialog("open");
                }



                self.updateDepartment = function(formElement, event) {
                    console.log("updateDepartment called");
                    var deptno = $('#deptno1').val();
                    var dname = $('#dname1').val();
                    var loc = $('#loc1').val();
                    //var newDeptModel1 = self.DeptColl().get(deptno);
                    var newDeptModel1 = new self.Department1({Deptno: deptno});
                    var link = serverLocation + 'AdfBCApp-RESTWebService-context-root/rest/main/DeptView1/' + deptno + '/child/EmpView';


                    console.log("newDeptModel before:" + JSON.stringify(newDeptModel1));

                    newDeptModel1.save({'Dname': dname, 'Loc': loc,'dummy':'dummVal'}, {
                        contentType: 'application/vnd.oracle.adf.resourceitem+json',
                        success: function(data, response, options) {
                            console.log('response' + response);
                            self.DeptColl().sort();
                             self.DeptColl().add(newDeptModel1, {merge: true});
                            console.log("after sort");

                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log("Update failed with textStatus: " + textStatus + "jqXHR:"+ JSON.stringify(jqXHR) + "errorThrown: "+ errorThrown);
                        }
                    });

                    console.log("Is newDeptModel1 valid: " + newDeptModel1.isValid())
                    if (!newDeptModel1.isValid()) {
                        console.log(newDeptModel1.get("Deptno") + " " + newDeptModel1.validationError);
                    }

                    console.log("newDeptModel after:" + JSON.stringify(newDeptModel1));

                    console.log("Deptno has changed (shd be false): " + newDeptModel1.hasChanged(["Deptno"]))
                    console.log("Dname has changed (shd be true): " + newDeptModel1.hasChanged(["Dname"]))
                    console.log("Loc has changed (shd be true): " + newDeptModel1.hasChanged(["Loc"]))


                   // self.DeptColl().set([newDeptModel1], {remove: false}, {validate: true});

                    


                    $("#update-form").ojDialog("close");
                }

                self.deleteDept = function(viewModel, event) {
                    console.log("dept to delete: " + event.currentTarget.id);
                    console.log(viewModel);
                    var value = viewModel.Deptno;
                    console.log(value);
                    
                 //   self.DeptColl().get(value).then(function(model) { 
                    //         model.destroy({wait:true}); 
                    //    }); 

                    self.DeptColl().get(value).then(function(newDeptModel){
                            console.log(JSON.stringify(newDeptModel));
                            if (newDeptModel) {
                                newDeptModel.destroy({wait:true,
                                    success: function(collection, response, options) {
                                        console.log("In Success");
                                        self.DeptColl().contains(newDeptModel).then(function(contains){
                                                if(contains){
                                                    var arrayRemoved = self.DeptColl().remove(newDeptModel); 
                                                    var result ="";
                                                    result += "Removed Model:" + JSON.stringify(arrayRemoved);
                                                    $("#resultsFunction").html(result);
                                                     
                                                }
                                                else
                                                    $("#resultsFunction").html("No Model Found");
                                                console.log("**********************************")
                                            });
                                            
                                            //self.collTableDatasource().setPage(self.collTableDatasource().getPage());
                                            $('#paging').ojPagingControl('page', self.collTableDatasource().getPage()); 
                
                                    },
                                    error: function(jqXHR, textStatus, errorThrown) {
                                        console.log('Error in fetch: ' + textStatus);
                                    }
                                });
                            }
                    });
                }

                self.showDetails = ko.observable(false);
                self.noEmployees = ko.observable(false);
                self.arrayEmpTableDatasource = ko.observable();
                self.collEmpTableDatasource = ko.observable();
                self.employeeCol = ko.observable();
                self.Employees = ko.observableArray([]);
                self.Employee = ko.observable();

                self.displayEmployees = function(url, deptno, data, event) {
                    
                    self.Employee = oj.Model.extend({
                        urlRoot: url,
                        parse: parseEmp,
                        parseSave: parseSaveEmp,
                        idAttribute: 'Empno'
                    });

                    var myEmployee = new self.Employee();
                    var EmployeeCollection = oj.Collection.extend({
                        url: url,
                        model: myEmployee,
                        parse: parseEmployees
                    });

                    self.employeeCol(new EmployeeCollection());



                    self.mydeptno(deptno);


                    self.employeeCol().fetch({
                        success: function(collection, response, options) {

                            if (collection.length > 0)
                                self.noEmployees(false);
                            else
                                self.noEmployees(true);

                            /*  alert("Collection length within Fetch success method"+ collection.length);
                             if (collection.length > 0) {
                             self.Employees(oj.KnockoutUtils.map(collection));
                             self.showDetails(true);
                             self.noEmployees(false);
                             console.log("self.Employees()" + self.Employees().length);
                             } else {
                             self.Employees([]);
                             self.showDetails(false);
                             self.noEmployees(true);
                             }
                             self.arrayEmpTableDatasource(new oj.ArrayTableDataSource(new self.Employees()));
                             alert('size:'+self.arrayEmpTableDatasource().size()); 
                             */

                            //self.collEmpTableDatasource(new oj.CollectionTableDataSource(self.employeeCol()));
                            //alert('self.collEmpTableDatasource() length inside fetch method' + self.collEmpTableDatasource().size());
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log('Error in fetch: ' + textStatus);
                            self.noEmployees(true);
                        }
                    });


                    self.collEmpTableDatasource(new oj.CollectionTableDataSource(self.employeeCol()));
                    
                    self.employeeCol().on("add",self.addEventToCollection,this);
                    self.employeeCol().on("remove",self.removeEventToCollection,this);
                    self.employeeCol().on("request",self.requestEventToCollection,this);                
                    self.employeeCol().on("reset",self.resetEventToCollection,this);                
                    self.employeeCol().on("refresh",self.refreshEventToCollection,this);                
                    self.employeeCol().on("change",self.changeEventToCollection,this);   
                    self.employeeCol().on("sort",self.sortEventToCollection,this);   
                    self.employeeCol().on("all",self.allEventToCollection,this);                
                    self.employeeCol().on("destroy",self.destroyEventToCollection,this);
                    self.employeeCol().on("allremoved",self.allremovedEventToCollection,this);
                    self.employeeCol().on("error",self.errorEventToCollection,this);
                    self.employeeCol().on("invalid",self.invalidEventToCollection,this);
                
                



                }


                self.addEmployee = function(formElement, event) {
                    var newEmpModel = new self.Employee({Empno: formElement.elements[0].value, Ename: formElement.elements[1].value,
                        Job: formElement.elements[2].value, Deptno: formElement.elements[3].value});

                    var jsonModel = JSON.stringify(newEmpModel);
                    console.log("json: : " + jsonModel);

                    self.employeeCol().create(newEmpModel, {
                        'contentType': 'application/vnd.oracle.adf.resourceitem+json',
                        success: function(response) {
                            console.log(response);
                            console.log('Success in Create');
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log('Error in Create: ' + JSON.stringify(jqXHR));
                            self.employeeCol().remove(newEmpModel);
                        }
                    });

                    console.log('after length' + self.employeeCol().length);
                    $("#deptTable").ojTable("refresh");
                }

                self.editEmployee = function(viewModel, event) {
                    console.log("dept to update: " + event.currentTarget.id + viewModel.Ename + viewModel.Job);
                    $("#update-empform #deptno").val(viewModel.Deptno);
                    $("#update-empform #empno").val(viewModel.Empno);
                    $("#update-empform #ename").val(viewModel.Ename);
                    $("#update-empform #job").val(viewModel.Job);

                    $("#update-empform").ojDialog("open");
                }



                self.updateEmployee = function(formElement, event) {
                    console.log("updateDepartment called");
                    var empno = $('#empno').val();
                    var ename = $('#ename').val();
                    var job = $('#job').val();
                    var deptno = $('#deptno').val();
                    var newEmpModel = new self.Employee({Empno: empno});
                    //   var newEmpModel = self.employeeCol().get(empno);
                    console.log(JSON.stringify(newEmpModel));

                    newEmpModel.save({'Ename': ename, 'Job': job, 'Deptno': deptno}, {
                        contentType: 'application/vnd.oracle.adf.resourceitem+json',
                        success: function(data, response, options) {
                            console.log("Response: " + response);
                            self.employeeCol().add(newEmpModel, {merge: true});

                            // self.employeeCol().set([newEmpModel], {merge:true});
                            self.employeeCol().sort();
                           // $("#empTable").ojTable("refresh");

                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log("Update failed with " + textStatus);
                        }
                    });

                    

                    $("#update-empform").ojDialog("close");
                   // $("#empTable").ojTable("refresh");
                }

                self.deleteEmployee = function(viewModel, event) {
                    console.log("dept to delete: " + event.currentTarget.id);
                    console.log(viewModel);
                    var value = viewModel.Empno;
                    console.log(value);
                    var newEmpModel = self.employeeCol().get(value);
                    console.log(JSON.stringify(newEmpModel));
                    if (newEmpModel) {
                        newEmpModel.destroy({
                            success: function(collection, response, options) {
                                console.log("In Success");
                                self.employeeCol().remove(newEmpModel);
                               // $("#empTable").ojTable("refresh");
                        
                            },
                            error: function(jqXHR, textStatus, errorThrown) {
                                console.log('Error in fetch: ' + textStatus);
                            }
                        });
                    }
                   // $("#empTable").ojTable("refresh");
                }


                
                 // Events declaration

                self.myDept1.on("invalid", function(eventType,model) {
                    console.log(model.get("Deptno") + " " + eventType);
                });
                
                
                
                


                self.DeptColl().on("add",self.addEventToCollection,this);
                self.DeptColl().on("remove",self.removeEventToCollection,this);
                self.DeptColl().on("request",self.requestEventToCollection,this);                
                self.DeptColl().on("reset",self.resetEventToCollection,this);                
                self.DeptColl().on("refresh",self.refreshEventToCollection,this);                
                self.DeptColl().on("change",self.changeEventToCollection,this);   
                self.DeptColl().on("sort",self.sortEventToCollection,this);   
                self.DeptColl().on("all",self.allEventToCollection,this);                
                self.DeptColl().on("destroy",self.destroyEventToCollection,this);
                self.DeptColl().on("allremoved",self.allremovedEventToCollection,this);
                self.DeptColl().on("alladded",self.alladdedEventToCollection,this);
                self.DeptColl().on("error",self.errorEventToCollection,this);
                self.DeptColl().on("invalid",self.invalidEventToCollection,this);
                
                
                self.myDept1.on("add",self.addEventToCollection,this);
                self.myDept1.on("remove",self.removeEventToCollection,this);
                self.myDept1.on("request",self.requestEventToCollection,this);                
                self.myDept1.on("reset",self.resetEventToCollection,this);                
                self.myDept1.on("refresh",self.refreshEventToCollection,this);                
                self.myDept1.on("change",self.changeEventToCollection,this);   
                self.myDept1.on("sort",self.sortEventToCollection,this);   
                self.myDept1.on("all",self.allEventToCollection,this);                
                self.myDept1.on("destroy",self.destroyEventToCollection,this);
                self.myDept1.on("alladded",self.alladdedEventToCollection,this);
                self.myDept1.on("allremoved",self.allremovedEventToCollection,this);
                self.myDept1.on("error",self.errorEventToCollection,this);
                self.myDept1.on("invalid",self.invalidEventToCollection,this);                
                
                


            }

            parseEmployees = function(response) {
                return response.items;
            };

            parseDepts = function(response) {
                if( response.items) return response.items; 
                else return response;
            };

            parseEmp = function(response) {

                return {Empno: response['Empno'], Ename: response['Ename'], Job: response['Job'], Deptno: response['Deptno']};
            };

            parseSaveEmp = function(response) {
                return {Empno: response['Empno'], Ename: response['Ename'], Job: response['Job'], Deptno: response['Deptno']};
            };

            parseDept = function(response) {

                return {Deptno: response['Deptno'], Dname: response['Dname'], Loc: response['Loc'], link: (response.links[2]) ? response.links[2].href : ""};
            };
            parseSaveDept = function(response) {
                console.log('in parseSaveDept: ' + response['Deptno'] + response['Dname'] + response['Loc']);
                return {Deptno: response['Deptno'], Dname: response['Dname'], Loc: response['Loc']};
            };

            vm = new departmentsVM();
            $(document).ready(function() {

                ko.applyBindings(vm, document.getElementById('homeContainer'));
                
                $.ajaxSetup({
                    xhrFields:
                            {
                                withCredentials: true
                            },
                    crossDomain: true
                });
            });
        });