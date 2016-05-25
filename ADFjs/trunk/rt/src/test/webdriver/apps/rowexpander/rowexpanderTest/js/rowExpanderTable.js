require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents', 'ojs/ojtable', 'ojs/ojflattenedtreetabledatasource', 'ojs/ojflattenedtreedatagriddatasource', 'ojs/ojjsontreedatasource', 'ojs/ojdatagrid', 'ojs/ojrowexpander'],
        function(oj, ko, $)
        {

            function rowExpanderTableViewModel(successCallback) {
                self = this;

                $.getJSON("js/tableData.json", function(data)
                {
                    self.treedata = ko.observable();
                    self.treedata(data);
                    self.jsonTreeDS = ko.observable();
                    jsonTreeDS = new oj.JsonTreeDataSource(self.treedata())

                    var tabOptions = [];
                    self.tableDatasource = new oj.FlattenedTreeTableDataSource(new oj.FlattenedTreeDataSource(jsonTreeDS, tabOptions));


                    

                    var gridOptions = {'columns': ['name', 'resource', 'start', 'end']};
                    self.gridDatasource = new oj.FlattenedTreeDataGridDataSource(jsonTreeDS, gridOptions);

                    var initExpandedOptions = {'expanded': ['t1', 't3'], 'columns': ['name', 'resource', 'start', 'end']};
                    self.initExpandedGridDS = new oj.FlattenedTreeDataGridDataSource(jsonTreeDS, initExpandedOptions);

                    var allExpandedOptions = {'expanded': 'all', 'columns': ['name', 'resource', 'start', 'end']};
                    self.allExpandedGridDS = ko.observable();
                    self.allExpandedGridDS(new oj.FlattenedTreeDataGridDataSource(jsonTreeDS, allExpandedOptions));

                    self.addData = function(e, d) {
                        var node =
                                {"attr": {"id": "t1New",
                                        "name": "New Task 1",
                                        "resource": " New Name",
                                        "start": "1/1/2014",
                                        "end": "10/1/2014"
                                    },
                                    "children": [
                                        {"attr": {"id": "t1New-1",
                                                "name": "New Task 1-1",
                                                "resource": "Larry New",
                                                "start": "1/1/2014",
                                                "end": "3/1/2014"
                                            },
                                            "children": [
                                                {"attr": {"id": "t1New-1-1",
                                                        "name": "Task 1-1-1 New",
                                                        "resource": "Larry New",
                                                        "start": "1/1/2014",
                                                        "end": "2/1/2014"
                                                    }
                                                },
                                                {"attr": {"id": "t1New-1-2",
                                                        "name": "Task 1-1-2 New",
                                                        "resource": "Larry New",
                                                        "start": "2/1/2014",
                                                        "end": "3/1/2014"
                                                    }
                                                }
                                            ]
                                        }
                                    ]
                                };

                        self.treedata().push(node);




                        var treeDS = new oj.JsonTreeDataSource(self.treedata())
                        var dataSourceNew = new oj.FlattenedTreeDataGridDataSource(treeDS, allExpandedOptions);
                        $("#datagridAllExp").ojDataGrid("option", "data", dataSourceNew);
                    }

                    self.expandFunc = function(e, ui) {
			    var rowKey = ui['rowKey'];
			    $("#resultsFunction1").html("<label> <b> Expand Func: RowKey: "+  rowKey +  "</b><\/label>");

			}

			self.collapseFunc = function(event, ui) {
			    var rowKey = ui['rowKey'];
			    $("#resultsFunction1").html("<label> <b> Collapse Func: RowKey: "+  rowKey +  "</b><\/label>");

			}

			self.destroyRowEx = function(event, ui) {
			    oj.Logger.info("In destroyRowEx ...");                       
			    $("#t1").ojRowExpander("destroy");                        

			}

			 self.createFunc = function(e, ui) {
				    oj.Logger.info("In ojexpander create event");
				    var id = e.target.id; 
				    $("#resultsFunction").html("<label> <b> t1 ojcreate event method -- <\/b>event id=" + id + " <\/label>");
				    oj.Logger.info("<label> <b> t1 ojcreate event method -- <\/b> event id=" + id + " <\/label>");
			}

			self.destroyFunc = function(e, ui) {
				    oj.Logger.info("In ojexpander destroy event");
				    var id =e.target.id; 
				    $("#resultsFunction").html("<label> <b> t1 ojdestroy event method -- <\/b> event id=" + id + " <\/label>");
			}

		    self.getNodeBySubId = function(event, ui) {
			    oj.Logger.info("In getNodeBySubId ...");

			    var locator = {subId: 'oj-rowexpander-icon'};
			    var node = $("#t1").ojRowExpander("getNodeBySubId", locator);
			    $("#resultsFunction").html("<label> "+ node[0] + "<\/label><br/>");
			    alert(node[1]);

			}



			successCallback();
		    });
		}

		$(document).ready(function() {
		    var vm1 = rowExpanderTableViewModel(
			    function() {
				$("#t1").on("ojcreate", function(e, ui) {
				    oj.Logger.info("In ojexpander create event");
				    var id = e.target.id;  
				    $("#resultsFunction").html("<label> <b> t1 ojcreate event method -- <\/b> event id=" + id + " <\/label>");
				    oj.Logger.info("<label> <b> t1 ojcreate event method -- <\/b> event id=" + id + " <\/label>");

				});



				$("#t1").on("ojdestroy", function(e, ui) {
				    oj.Logger.info("In ojexpander destroy event");
				    var id =e.target.id;  
				    $("#resultsFunction").html("<label> <b> t1 ojdestroy event method -- <\/b> event id=" + id + " <\/label>");


				});




				ko.applyBindings(vm1, document.getElementById('rowExpanderTableContainer'));




			    }
		    );

		});
		    
        });