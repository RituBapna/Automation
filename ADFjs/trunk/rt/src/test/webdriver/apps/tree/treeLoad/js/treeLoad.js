define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents'],
        function(oj, ko, $)
        {
            function treeDataModel() {
                self = this;

                self.homeClickedCount = ko.observable(0);

                self.treeData1 = [
                    {
                        "title": "Home " + self.homeClickedCount(),
                        "attr": {"id": "home", "special_node": true, "special_data": "hello1", "title":"Home node with special data", "type":"myroot"},
                        "data":{"attr":{"data-bind":"click: alertHomeNodeClicked, title: 'this is my title', css:{showNodeRed}"}},                          
                        "metadata": {
                            "type": "T123",
                            "val": 42,
                            "active": true
                        }
                    },
                    {
                        "title": "News",
                        "attr": {
                            "id": "news",
                            "author": "Jackie",
                            "creationDate": "1/10/2014"
                        },
                        "data": {
                            "title": "Alternate Title",
                            "attr": {"top": "hat"}         //this is set on the <a>
                        }
                    },
                    {
                        "title": "Blogs",
                        "attr": {"id": "blogs"},
                        "children": [{"title": "Today",
                                "attr": {"id": "today"}
                            },
                            {"title": "Yesterday",
                                "attr": {"id": "yesterday"}
                            },
                            {"title": "2 Days Back",
                                "attr": {"id": "2daysback"}
                            },
                            {"title": "Archive",
                                "attr": {"id": "archive"}
                            }
                        ]
                    },
                    {
                        "title": "Links",
                        "attr": {"id": "links"},
                        "children": [{"title": "Oracle",
                                "attr": {"id": "oracle"}
                            },
                            {"title": "IBM",
                                "attr": {"id": "ibm"}
                            },
                            {"title": "Microsoft",
                                "attr": {"id": "ms"},
                                "children": [{"title": "USA",
                                        "attr": {"id": "msusa"},
                                        "children": [{"title": "North",
                                                "attr": {"id": "msusanorth"}
                                            },
                                            {"title": "South",
                                                "attr": {"id": "msusasouth"}
                                            },
                                            {"title": "East",
                                                "attr": {"id": "msusaeast"}
                                            },
                                            {"title": "West",
                                                "attr": {"id": "msusawest"}
                                            }
                                        ]
                                    },
                                    {"title": "Europe",
                                        "attr": {"id": "msuerope"}
                                    },
                                    {"title": "Asia",
                                        "attr": {"id": "msasia"},
                                        "children": [{"title": "Japan",
                                                "attr": {"id": "asiajap"}
                                            },
                                            {"title": "China",
                                                "attr": {"id": "asiachina"}
                                            },
                                            {"title": "India",
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
                        "children": [{"title": "All",
                                "attr": {"id": "refsall"}
                            },
                            {"title": "USA",
                                "attr": {"id": "refsusa"}
                            },
                            {"title": "Europe",
                                "attr": {"id": "refseurope"}
                            },
                            {"title": "Asia",
                                "attr": {"id": "refsasia"}
                            }
                        ]
                    },
                    {
                        "title": "Suppliers",
                        "attr": {"id": "sups"},
                        "children": [{"title": "Gold Tier",
                                "attr": {"id": "supgold"}
                            },
                            {"title": "Silver Tier",
                                "attr": {"id": "supsilver"}
                            },
                            {"title": "Non-contract",
                                "attr": {"id": "supcon"}
                            },
                            {"title": "Independent",
                                "attr": {"id": "supind"}
                            }
                        ]
                    }
                ];

                self.expNodes = ["#references", "#sups"];

                self.jsonDS = new oj.JsonTreeDataSource(self.treeData1);

                self.dndopts = {
                    "reorder": true
                };
                
      
   


                self.getBaseUrl = function() {
                    var href = window.location.href;
                    var lastIndexOfSlash = href.lastIndexOf("/");
                    var subStr = href.substring(0, lastIndexOfSlash);
                   // var subStrTemp = href.substring(0, lastIndexOfSlash);
                   // var lastIndexOfSlashAgain = subStrTemp.lastIndexOf("/");
                   // var subStr = subStrTemp.substring(0, lastIndexOfSlashAgain);
                    return subStr;
                }

               
                self.baseurl = self.getBaseUrl();

                self.getUrl = function(node) {
                    console.log("in getURL");
                    oj.Logger.info("In getUrl: " + node);
                    var urlToReturn = self.baseurl + "/js/treedata/treedatainjsonfile.json";
                    return urlToReturn;
                }

                self.dataSuccess = function(data, status, obj) {
                    oj.Logger.info("In dataSuccess"+ status);
                    //alert(data);
                    return data;
                }

                self.dataFailure = function(reason, feedback, obj) {
                    oj.Logger.info("In dataFailure");
                    //alert(' In dataFailure');
                    oj.Logger.info("Ajax error reason= " + reason + " feedback=" + feedback.message);
                }

                self.dataSuccessTransform = function(data, status, obj) {
                    oj.Logger.info("In dataSuccessTransform" + status);
                    //alert(data);                  
                    return data;
                }

                self.getNodeData = function(node) {
                    oj.Logger.info("in getNodeData: " + node)
                    return self.jsondata;
                }

                self.jsondatafunc = function(node, fn) {
                    oj.Logger.info("in jsondatafunc: " + node)
                    return fn(self.treeData1);
                }

                self.alertHomeNodeClicked = function(data, e) {
                    var resultTemp = " Home Node Clicked";
                     var count = self.homeClickedCount();                     
                    self.homeClickedCount(count+1);
                    $("#functionResult").html("<label> " + resultTemp + "<\/label><br/><label>" + self.homeClickedCount() + "<\/label>");
                }


                $("#treeHtmlContainer").on("ojselect", function(e, ui) {
                    //  get tree node id attribute
                    //alert('ojselect');
                    var id = ui.item.attr("id");
                    var resultTemp = " Selected Event:    <b>ojselect<\/b><br/>";
                    resultTemp = resultTemp + "<label> id=" + id + "<\/label>";
                    $("#resultsContainer").html(resultTemp);
                });


                $("#treeContainer").on("ojselect", function(e, ui) {
                    //  get tree node id attribute
                    var id = ui.item.attr("id");
                    var resultTemp = " Selected Event:    <b>ojselect<\/b><br/>";
                    resultTemp = resultTemp + "<label> id=" + id + "<\/label>";
                    $("#resultsContainer").html(resultTemp);
                });

                $("#treeContainer").on("ojhover", function(e, ui) {


                    var node = ui.item;
                    var id = ui.item.attr("id");
                    var author = ui.item.attr("author");
                    var creationDate = ui.item.attr("creationDate");

                    //Meta Data 
                    var metaData = "Type:   " + ui.item.data("type") + "   " + "Val:    " + ui.item.data("val") + "   " + "Active:    " + ui.item.data("active");

                    var resultsTemp = " Selected Event:    <b>ojhover<\/b><br/>" + "<label> id=" + id + "<\/label><br/>"
                            + "<label> author=" + author + "<\/label><br/>" + "<label> creationDate=" + creationDate
                            + "<\/label><br/>" + "<label> MetaData=" + metaData + "<\/label><br/>";

                    $("#resultsContainer").html(resultsTemp);

                });

            }

            var vm1 = new treeDataModel();

            $(document).ready(function() {

                ko.applyBindings(vm1, document.getElementById('treeContainer'));
            });
        }); 