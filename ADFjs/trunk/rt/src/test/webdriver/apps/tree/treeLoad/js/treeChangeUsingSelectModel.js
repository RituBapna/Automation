require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents', 'ojs/ojselectcombobox'],
        function (oj, ko, $)
        {
            oj.Logger.option("level", oj.Logger.LEVEL_INFO);

            function treeChangeUsingSelectModel() {
                var self = this;

                self.treeFuncData1 =
                        [
                            {
                                "title": "Select Me",
                                "attr": {"id": "node0"}
                            },
                            {
                                "title": "News",
                                "attr": {"id": "news", "title": "New node with NO special data", "type": "myleaf"}
                            },
                            {
                                "title": "Blogs",
                                "attr": {"id": "blogs", "type": "myfolder"},
                                "children": [{"title": "Today",
                                        "data": {"title": "Today"},
                                        "attr": {"id": "today", "special_node": true, "special_data": "hello2", "type": "myleaf"},
                                        "metadata": {"owner": "versha2"}
                                    },
                                    {"title": "Yesterday",
                                        "data": {"title": "Yesterday"},
                                        "attr": {"id": "yesterday", "title": " yesterday node"}
                                    },
                                    {"title": "2 Days Back",
                                        "data": {"title": "2 Days Back"},
                                        "attr": {"id": "2daysback"}
                                    },
                                    {"title": "Archive",
                                        "data": {"title": "Archive"},
                                        "attr": {"id": "archive"}
                                    }
                                ]
                            }
                        ];

                self.treeFuncData2 =
                        [
                            {
                                "title": "Home2",
                                "attr": {"id": "home2"}
                            },
                            {
                                "title": "TreeNode2",
                                "attr": {"id": "treenode2", "title": "TreeNode2"}
                            }
                        ];

                self.treeFuncData3 =
                        [
                            {
                                "title": "Home3",
                                "attr": {"id": "home3"}
                            },
                            {
                                "title": "Tree3 Node",
                                "attr": {"id": "news", "title": "TreeNode3"}
                            }
                        ];


                self.selectedValue = ko.observableArray(["ONE"]);



                self.jsondatafunc = function (node, fn) {
                    oj.Logger.info("in jsondatafun %%%c: " + node);
                    
                   var val = self.selectedValue()[0] + "";
                    if (val == "ONE")
                        return fn(self.treeFuncData1);
                    else if (val.toString() == "TWO")
                        return fn(self.treeFuncData2);
                    else if (val == "THREE")
                        return fn(self.treeFuncData3);
                    else
                        return fn(self.treeFuncData1);
                }

                self.optionChangedHandler = function (event, data) {
                    if(data.option == "value")
                        $("#treeFunctionMain").ojTree("refresh");
                }
                
                self.changeSelectToTWO = function(event, data){
                	 $( "#select11" ).ojSelect("option", "value", ['TWO']); 
			self.selectedValue(["TWO"]);
                        $("#treeFunctionMain").ojTree("refresh");
                }
                
                self.changeSelectToTHREE = function(event, data){
			$( "#select11" ).ojSelect("option", "value", ['THREE']); 
			self.selectedValue(["THREE"]);
			$("#treeFunctionMain").ojTree("refresh");
                }

            }

            vmTree11 = new treeChangeUsingSelectModel();

            $(document).ready(function () {
                
                ko.applyBindings(vmTree11, document.getElementById('treeContainer'));


            });

        });         