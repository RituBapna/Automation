require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents','ojs/ojselectcombobox'],
        function(oj, ko, $)
        {
            oj.Logger.option("level", oj.Logger.LEVEL_INFO);

            function treeFunctionViewModel() {
                var self = this;

                self.coreoptsFunc = {
                    "animDuration": 2050,
                    "selectionMode": "multiple", //none, single or multiple
                    "selectPrevOnDelete": true,
                    "expandParents": true,
                    "icons": true,
                    "rtl": false
                };

                self.getBaseUrl = function() {
                    var href = window.location.href;
                    var lastIndexOfSlash = href.lastIndexOf("/");
                    var subStr = href.substring(0, lastIndexOfSlash);
                    return subStr;
                }
                self.baseurl = self.getBaseUrl();

                self.typesoptsFunc = {
                    "types": {
                        "myroot": {
                            "image": self.baseurl + "/img/root.gif",
                            "select": function() {
                                return false;
                            },
                            "delete": function() {
                                return false;
                            },
                            "move": function() {
                                return false;
                            }
                        },
                        "myfolder": {
                            "image": self.baseurl + "/img/folder.gif"
                        },
                        "myleaf": {
                            "image": self.baseurl + "/img/leaf.png"
                        },
                        "noexpand": {
                            "image": self.baseurl + "/img/noexpand.png"
                                    //,"expand":  false,
                                    // "collapse":  false

                        },
                        "default": {
                            "image": self.baseurl + "/img/default.gif"
                        }

                    }
                };

                self.homeClickedCount = ko.observable(0);

                self.nodeClicked = function(e, d) {
                    oj.Logger.info("In Node clicked ko function")
                    var count = self.homeClickedCount();
                    self.homeClickedCount(count + 1);
                }

                self.collapsibleTreeData =
                        [
                            { 
                            "title": "Select Me",
                            "attr": {"id": "node0"}
                          },
                          {"title": "Home1",
                                "attr": {"id": "home1", "type": "myfolder"},
                                "data": {"title": "Home"},
                                "metadata": {"owner": "jack"}
                            },
                            {
                                "title": "News1",
                                "attr": {"id": "news1", "title": "New node with NO special data", "type": "myleaf"}
                            },
                            {
                                "title": "Blogs1",
                                "attr": {"id": "blogs1", "type": "myfolder"},
                                "children": [{"title": "Today1",
                                        "data": {"title": "Today1"},
                                        "attr": {"id": "today1", "special_node": true, "special_data": "hello2", "type": "myleaf"},
                                        "metadata": {"owner": "versha2"}
                                    },
                                    {"title": "Yesterday1",
                                        "data": {"title": "Yesterday1"},
                                        "attr": {"id": "yesterday1", "title": " yesterday1 node"}
                                    },
                                    {"title": "2 Days Back1",
                                        "data": {"title": "2 Days Back1"},
                                        "attr": {"id": "2daysback1"}
                                    },
                                    {"title": "Archive1",
                                        "data": {"title": "Archive1"},
                                        "attr": {"id": "archive1"}
                                    }
                                ]
                            }
                       ];
                       
                            
                            
                      self.treeFuncData =
                       	[	
                            { 
                            "title": "Select Me",
                            "attr": {"id": "node0"}
                          },
                          {"title": "Home ",
                                "attr": {"id": "home", "type": "myfolder", "special_node": true, "special_data": "hello1", "data-bind": "attr:{title:'Hello There!' }"},
                                "data": {"title": "Home ", "attr": {"data-bind": "attr:{title:'hello there!' }, click: nodeClicked,  css:{showNodeRed}"}},
                                "metadata": {"owner": "jack"}
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
                            },
                            {
                                "title": "Links",
                                "attr": {"id": "links", "type": "myfolder"},
                                "children": [{"title": "Oracle",
                                        "attr": {"id": "oracle", "type": "myleaf"}
                                    },
                                    {"title": "IBM",
                                        "attr": {"id": "ibm"}
                                    },
                                    {"title": "Microsoft",
                                        "attr": {"id": "ms", "type": "myfolder"},
                                        "children": [{"title": "USA",
                                                "attr": {"id": "msusa", "special_node": true, "special_data": "hello3", "type": "myfolder"},
                                                "metadata": {"owner": "versha3"},
                                                "children": [{"title": "North",
                                                        "attr": {"id": "msusanorth", "title": "First Node under USA"}
                                                    },
                                                    {"title": "South",
                                                        "attr": {"id": "msusasouth"}
                                                    },
                                                    {"title": "East",
                                                        "attr": {"id": "musaeast"}
                                                    },
                                                    {"title": "West",
                                                        "attr": {"id": "musawest"}
                                                    }
                                                ]
                                            },
                                            {"title": "Europe",
                                                "attr": {"id": "mseurope"}
                                            },
                                            {"title": "Asia",
                                                "attr": {"id": "msasia"},
                                                "children": [{"title": "Japan",
                                                        "attr": {"id": "asiajap"}
                                                    },
                                                    {"title": "China",
                                                        "attr": {"id": "asiachine"}
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
                                "attr": {"id": "sponsors", "type": "noexpand"}
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
                                        "attr": {"id": "refasia"}
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


                self.dndoptsFunc = {
                    "reorder": true
                };


                self.expnodes = ["#references", "#sups"];

                self.jsondatafunc = function(node, fn) {
                    oj.Logger.info("in jsondatafunc: " + node)
                    // fn(node);

                    return fn(self.treeFuncData);
                }

                self.jsonopts = {"data": self.jsondatafunc, "ajax": false}


                self.refreshData = ko.computed(function() {
                    return {
                        "data": [
                            {"title": "Home ",
                                "attr": {"id": "home", "type": "myfolder", "special_node": true, "special_data": "hello1", "data-bind": "attr:{title:'Hello There!' }"},
                                "data": {"title": "Home ", "attr": {"data-bind": "attr:{title:'hello there!' }, click: nodeClicked,  css:{showNodeRed}"}},
                                "metadata": {"owner": "jack"}
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
                            },
                            {
                                "title": "Links",
                                "attr": {"id": "links", "type": "myfolder"},
                                "children": [{"title": "Oracle",
                                        "attr": {"id": "oracle", "type": "myleaf"}
                                    },
                                    {"title": "IBM",
                                        "attr": {"id": "ibm"}
                                    },
                                    {"title": "Microsoft",
                                        "attr": {"id": "ms", "type": "myfolder"},
                                        "children": [{"title": "USA",
                                                "attr": {"id": "msusa", "special_node": true, "special_data": "hello3", "type": "myfolder"},
                                                "metadata": {"owner": "versha3"},
                                                "children": [{"title": "North",
                                                        "attr": {"id": "msusanorth", "title": "First Node under USA"}
                                                    },
                                                    {"title": "South",
                                                        "attr": {"id": "msusasouth"}
                                                    },
                                                    {"title": "East",
                                                        "attr": {"id": "musaeast"}
                                                    },
                                                    {"title": "West",
                                                        "attr": {"id": "musawest"}
                                                    }
                                                ]
                                            },
                                            {"title": "Europe",
                                                "attr": {"id": "mseurope"}
                                            },
                                            {"title": "Asia",
                                                "attr": {"id": "msasia"},
                                                "children": [{"title": "Japan",
                                                        "attr": {"id": "asiajap"}
                                                    },
                                                    {"title": "China",
                                                        "attr": {"id": "asiachine"}
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
                                "attr": {"id": "sponsors", "type": "noexpand"}
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
                                        "attr": {"id": "refasia"}
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
                        ]
                    };
                }, self);




// *********************************START HANDLER****************************************************           


                self.treeSelectHandler = function(e, data) {
                    oj.Logger.info("In treeSelectHandler event");
                    var id = data.item.attr("id");     //  get node id attribute  

                    if (id == "home") {
                        var count = vmTree.homeClickedCount();
                        vmTree.homeClickedCount(count + 1);
                        $("#resultsFunction").html("<label> In <b>treeSelectHandler event<\/b> -- id=" + id + vmTree.homeClickedCount() + "<\/label>");
                    }
                    else
                        $("#resultsFunction").html("<label> In <b>treeSelectHandler event<\/b> -- id=" + id + "<\/label>");


                }

                self.expandHandler = function(e, data) {
                    oj.Logger.info("In expandHandler ");
                    var id = data.item.attr("id");     //  get node id attribute      
                    $("#resultsFunction").html("<label> In <b>expandHandler event<\/b> -- id=" + id + "<\/label>");

                }

                self.collapseHandler = function(e, data) {
                    oj.Logger.info("In collapseHandler ");
                    var id = data.item.attr("id");     //  get node id attribute      
                    $("#resultsFunction").html("<label> In <b>collapseHandler event<\/b> -- id=" + id + "<\/label>");

                }

                self.hoverHandler = function(e, data) {
                    oj.Logger.info("In hoverHandler ");
                    var id = data.item.attr("id");     //  get node id attribute      
                    $("#resultsFunction").html("<label> In <b>hoverHandler event<\/b> -- id=" + id + "<\/label>");

                }
                self.dehoverHandler = function(e, data) {
                    oj.Logger.info("In dehoverHandler ");
                    var id = data.item.attr("id");     //  get node id attribute      
                    $("#resultsFunction").html("<label> In <b>dehoverHandler event<\/b> -- id=" + id + "<\/label>");

                }
                self.loadedHandler = function(e, data) {
                    oj.Logger.info("In loadedHandler ");

                    $("#resultsFunction").html("<label> <b>loadedHandler<\/b> <\/label>");

                }
                self.moveHandler = function(e, data) {
                    oj.Logger.info("In moveHandler ");
                    var id = data.item.attr("id");     //  get node id attribute      
                    $("#resultsFunction1").html("<label> <b>Moved id=" + id + "<\/label><\/b>");

                }
                self.collapseAllHandler = function(e, data) {
                    oj.Logger.info("In collapseAllHandler ");
                    $("#resultsFunction").html("<label><b> In collapseAllHandler <\/b> <\/label>");

                }
                self.expandAllHandler = function(e, data) {
                    oj.Logger.info("In expandAllHandler ");
                    //  get node id attribute      
                    $("#resultsFunction").html("<label> <b> In expandAllHandler<\/b> <\/label>");

                }
                self.createHandler = function(e, data) {
                    oj.Logger.info("In createHandler ");
                    $("#resultsFunction").html("<label> <b> In createHandler <\/b><\/label>");

                }

                self.removeHandler = function(e, data) {
                    oj.Logger.info("In removeHandler ");
                    var id = data.item.attr("id");     //  get node id attribute      

                    $("#resultsFunction").html("<label> <b> In removeHandler <\/b> id=" + id + "<\/label>");

                }
                self.deselectHandler = function(e, data) {
                    oj.Logger.info("In deselectHandler ");
                    var id = data.item.attr("id");     //  get node id attribute 

                    $("#resultsFunction").html("In <b> deselect handler <\/b> <label> id=" + id + "<\/label>");

                }
                self.deselectAllHandler = function(e, data) {
                    oj.Logger.info("In deselectAllHandler ");
                    var id = data.item.attr("id");     //  get node id attribute   

                    $("#resultsFunction").html("<label> <b>In deselectAllHandler <\/b>id=" + id + "<\/label>");

                }
                self.renameHandler = function(e, data) {
                    oj.Logger.info("In renameHandler ");
                    var id = data.item.attr("id");     //  get node id attribute      
                    $("#resultsFunction").html("<label> <b> In rename Handler <\/b> id=" + id + "<\/label>");

                }
                self.refreshHandler = function(e, data) {
                    oj.Logger.info("In refreshHandler ");

                    var item = data.item;
                    if (item == -1) {
                        $("#resultsFunction").html("<label> <b> In refreshHandler <\/b> WHOLE TREE REFRESHED   <\/label>");
                    } else {
                        var id = data.item.attr("id");
                        $("#resultsFunction").html("<label> <b> In refreshHandler <\/b> id=" + id + "<\/label>");
                    }
                }

// ************************************END HANDLERS*************************************************   

// *********************************** START EVENTS **************************************************

                self.selectLinksNode = function(d, e) {
                    oj.Logger.info("In selectLinksNode ...");
                    if (!$("#treeFunctionMain").ojTree("isSelected", $("#links"))){
                        $("#treeFunctionMain").ojTree("select", $("#links"));
                        $("#resultsFunction1").html("<label> <b> Selected Links Node </b><\/label>");
                    }
                }

                self.selectFewNodesAndExpand = function(d, e) {
                    oj.Logger.info("In selectFewNodesAndExpand ...");
                    //create an array of Nodes
                    var nodes = [$("#links"), $("#ms"), $("#mseurope"), $("#musaeast"), $("#refsall")];
                    $("#treeFunctionMain").ojTree("option", "selection", nodes);

                    //Expand the selected nodes
                    $("#treeFunctionMain").ojTree("expanded", nodes);

                    //get all selected nodes
                    nodes1 = $("#treeFunctionMain").ojTree("option", "selection");

                    var resultsTemp = "";
                    for (i = 0; i < nodes1.length; i++) {
                        resultsTemp += " " + $("#treeFunctionMain").ojTree("getText", nodes1[i]);
                    }
                    $("#resultsFunction1").html("<label><b> Selected and Expanded Items: <\/b>" + resultsTemp + "<\/label><br/>");

                }


                self.deselectLastSelectedNode = function(d, e) {
                    oj.Logger.info("In deselectSelectedNode ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option","selection")[0];
                    if (selectedNode) {
                        $("#treeFunctionMain").ojTree("deselect", selectedNode);
                        $("#resultsFunction1").html("<label> <b> DeSelected " +  ( $("#treeFunctionMain").ojTree("getText", selectedNode) ) + " Node </b><\/label>");

                    }
                }

                self.deselectAllSelectedNode = function(d, e) {
                    oj.Logger.info("In deselectAllSelectedNode ...");
                    var selectedNodes = $("#treeFunctionMain").ojTree("option", "selection");
                    $("#treeFunctionMain").ojTree("deselectAll");
                    $("#resultsFunction1").html("<label> <b> DeSelected All </b><\/label>");
                }

                self.expandSelectedNode = function(d, e) {
                    oj.Logger.info("In expandSelectedNode ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    if ($("#treeFunctionMain").ojTree("isExpanded"), selectedNode)
                        $("#treeFunctionMain").ojTree("expand", selectedNode);
                        
                        
                }

                self.expandLastTwoNodes = function(d, e) {
                    oj.Logger.info("In expandLastTwoNodes ...");
                    //create an array of Nodes = Suppliers and References
                    var nodes = [$("#sups"), $("#references")];
                    $("#treeFunctionMain").ojTree("expanded", nodes);
                    //get all expanded nodes
                    nodes = $("#treeFunctionMain").ojTree("expanded");
                    $("#resultsFunction1").html("<label> Expanded References and Suppliers<\/label><br/>");

                }

                self.expandAllSelectedNode = function(d, e) {
                    oj.Logger.info("In expandAllSelectedNode ...");
                    var result =" expandAllSelectedNode method ";
                    var selectedNodes = $("#treeFunctionMain").ojTree("option", "selection");
                    for (i = 0; i < selectedNodes.length; i++) {
                        if (!$("#treeFunctionMain").ojTree("isExpanded", selectedNodes[i])){
                            $("#treeFunctionMain").ojTree("expandAll", selectedNodes[i]);
                            result += $("#treeFunctionMain").ojTree("getText", selectedNodes[i]) + " ";
                         }
                            $("#resultsFunction1").html("<label> " + result + "<\/label><br/>");
                    }

                }




                self.collapseAllSelectedNode = function(d, e) {
                    oj.Logger.info("In collapseAllSelectedNode ...");
                    var result =" collapseAllSelectedNode method ";
                    var selectedNodes = $("#treeFunctionMain").ojTree("option", "selection");
                    for (i = 0; i < selectedNodes.length; i++) {
                        if (!$("#treeFunctionMain").ojTree("isCollapsed", selectedNodes[i])) {
                            $("#treeFunctionMain").ojTree("collapseAll", selectedNodes[i]);
                            result += $("#treeFunctionMain").ojTree("getText", selectedNodes[i]) + " ";

                        }
                        $("#resultsFunction1").html("<label> " + result + "<\/label><br/>");
                    }

                }

                self.collapseSelectedNode = function(d, e) {
                    oj.Logger.info("In collapseSelectedNode ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    if (selectedNode) 
                        $("#treeFunctionMain").ojTree("collapse", selectedNode);
                        
                }


                self.createUnderFirstSelectedNodeAndExpand = function(d, e) {
                    oj.Logger.info("In createUnderSelectedNodeAndExpand ...");
                    var newNode = {"title": "NewNode",
                        "attr": {"id": "nn"},
                        "state": "open"
                    };

                    var subNewNode2 = {"title": "SubNode1",
                        "attr": {"id": "sn1"}
                    };
                    var subNewNode3 = {"title": "SubNode2",
                        "attr": {"id": "sn2"}
                    };
                    var subNewNode4 = {"title": "SubNode3",
                        "attr": {"id": "sn3"}
                    };

                    var subNewNode21 = {"title": "SubNode21",
                        "attr": {"id": "sn21"}
                    };
                    var subNewNode22 = {"title": "SubNode22",
                        "attr": {"id": "sn22"}
                    };



                    //  Insert new nodes under currently selected node
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];



                    if (selectedNode) {
                        var parent = $("#treeFunctionMain").ojTree("create", selectedNode, 0, newNode);
                        var subparent1 = $("#treeFunctionMain").ojTree("create", parent, 0, subNewNode2);
                        $("#treeFunctionMain").ojTree("create", parent, 0, subNewNode3);
                        $("#treeFunctionMain").ojTree("create", parent, 0, subNewNode4);
                        $("#treeFunctionMain").ojTree("create", subparent1, 0, subNewNode21);
                        $("#treeFunctionMain").ojTree("create", subparent1, 0, subNewNode22);
                        $("#treeFunctionMain").ojTree("expandAll", selectedNode);
                    }


                }

                self.hoverAndDehoverSelectedNode = function(d, e) {
                    oj.Logger.info("In hoverAndDehoveSelectedNode ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    if (selectedNode) {
                        $("#treeFunctionMain").ojTree("hover", selectedNode);
                        $("#treeFunctionMain").ojTree("dehover");
                    }
                    $("#resultsFunction1").html("<label> Called Hover and Dehover. <\/label><br/>");
                }

                self.removeSelectedNode = function(d, e) {
                    oj.Logger.info("In deleteSelectedNode ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    oj.Logger.info("selectedNode: "+ selectedNode);
                    if (selectedNode) {
                        $("#treeFunctionMain").ojTree("remove", selectedNode);

                    }
                }


                self.destroyTree = function(d, e) {
                    oj.Logger.info("In destroySelectedNode ...");
                    $("#treeFunctionMain").ojTree("destroy");


                }

                self.getRoot = function(d, e) {
                    oj.Logger.info("In getRoot ...");
                    //create an array of Nodes = Suppliers and References
                    var node = $("#treeFunctionMain").ojTree("getRoot");
                    $("#resultsFunction1").html("<label> Root Node: " + $("#treeFunctionMain").ojTree("getText", node) + "<\/label><br/>");



                }


                self.subIdOptions = ko.observableArray([{value: 'disclosure', label: 'disclosure'},
                    {value: 'icon', label: 'icon'},
                    {value: 'link', label: 'link'},
                    {value: 'title', label: 'title'}
                ]);
                



                self.valueChangeHandler = function(context, valueParam) {
                    if (valueParam.option == "value") {
                        var valueObj = getNodeBySubId(valueParam);
                    }
                };


                self.getChildren = function(d, e) {
                    oj.Logger.info("In getChildren ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    if (selectedNode) {
                        var resultNode = $("#treeFunctionMain").ojTree("getChildren", selectedNode);
                    }
                    var result = "By <b>getChildren.  </b> ";
                    if (resultNode != null) {
                        for (i = 0; i < resultNode.length; i++) {
                            result += "<br/><b>Text: " + $("#treeFunctionMain").ojTree("getText", resultNode[i]);
                            $("#resultsFunction1").html("<label> </b>" + result + "<\/label><br/>");
                        }
                    }
                    else
                        $("#resultsFunction1").html("<label> </b>No Childrens.<\/label><br/>");

                }

                self.getParent = function(d, e) {
                    oj.Logger.info("In getParent ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    if (selectedNode) {
                        var resultNode = $("#treeFunctionMain").ojTree("getParent", selectedNode);
                    }
                    var result = "By <b>getParent.  </b> ";
                    if (resultNode != null) {
                        result += "<br/><b>Text: " + $("#treeFunctionMain").ojTree("getText", resultNode);
                        result += "</b><b>      <br/>Path: " + $("#treeFunctionMain").ojTree("getPath", resultNode);
                        $("#resultsFunction1").html("<label> </b>" + result + "<\/label><br/>");
                    }
                    else
                        $("#resultsFunction1").html("<label> </b>No Parent.<\/label><br/>");

                }

                self.getNextSibling = function(d, e) {
                    oj.Logger.info("In getNextSibling ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    if (selectedNode) {
                        var resultNode = $("#treeFunctionMain").ojTree("getNextSibling", selectedNode);
                    }
                    var result = "By <b>getNextSibling.  </b> ";
                    if (resultNode != null) {
                        result += "<br/><b>Text: " + $("#treeFunctionMain").ojTree("getText", resultNode);
                        result += "</b><b>      <br/>Path: " + $("#treeFunctionMain").ojTree("getPath", resultNode);
                        $("#resultsFunction1").html("<label> </b>" + result + "<\/label><br/>");
                    }
                    else
                        $("#resultsFunction1").html("<label> </b>No Next Sibling.<\/label><br/>");

                }

                self.getPrevSibling = function(d, e) {
                    oj.Logger.info("In getPrevSibling ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    if (selectedNode) {
                        var resultNode = $("#treeFunctionMain").ojTree("getPrevSibling", selectedNode);
                    }
                    var result = "By <b>getPrevSibling.  </b> ";
                    if (resultNode != null) {
                        result += "<br/><b>Text: " + $("#treeFunctionMain").ojTree("getText", resultNode);
                        result += "</b><b>      <br/>Path: " + $("#treeFunctionMain").ojTree("getPath", resultNode);
                        $("#resultsFunction1").html("<label> </b>" + result + "<\/label><br/>");
                    }
                    else
                        $("#resultsFunction1").html("<label> </b>No Previous Sibling.<\/label><br/>");

                }


                function getNodeBySubId(valueParam) {
                    oj.Logger.info("In getNodeBySubId ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];

                    var subId = valueParam.value;
                    if (subId == "icon") {
                        var result = "By <b>" + subId + ".  </b> ";
                        result += "<br/>SelectedNode: <b> " + $("#treeFunctionMain").ojTree("getText", selectedNode) + ".  </b> ";
                        var locator = {"subId": "oj-tree-node[" + selectedNode + "]['icon']"};
                        var node = $("#treeFunctionMain").ojTree("getNodeBySubId", locator);
                        result += "<br/><b>Text: " + $("#treeFunctionMain").ojTree("getText", node);
                        result += "</b><b>      <br/>Path: " + $("#treeFunctionMain").ojTree("getPath", node);
                        $("#resultsFunction1").html("<label> </b>" + result + "<\/label><br/>");
                    }
                    else if (subId == "disclosure") {
                        var result = "<b> By " + subId + ".  </b> ";
                        result += "<br/>SelectedNode: <b> " + $("#treeFunctionMain").ojTree("getText", selectedNode) + ".  </b> ";
                        var locator = {"subId": "oj-tree-node[" + selectedNode + "]['disclosure']"};
                        var node = $("#treeFunctionMain").ojTree("getNodeBySubId", locator);
                        result += "<br/><b>Text: " + $("#treeFunctionMain").ojTree("getText", node);
                        result += "</b><b>      <br/>Path: " + $("#treeFunctionMain").ojTree("getPath", node);
                        $("#resultsFunction1").html("<label> </b>" + result + "<\/label><br/>");
                    }
                    else if (subId == "title") {
                        var result = "<b> By " + subId + ".  </b> ";
                        result += "<br/>SelectedNode: <b> " + $("#treeFunctionMain").ojTree("getText", selectedNode) + ".  </b> ";
                        var locator = {"subId": "oj-tree-node[" + selectedNode + "]['title']"};
                        var node = $("#treeFunctionMain").ojTree("getNodeBySubId", locator);
                        result += "<br/><b>Text: " + $("#treeFunctionMain").ojTree("getText", node);
                        result += "</b><b>      <br/>Path: " + $("#treeFunctionMain").ojTree("getPath", node);
                        $("#resultsFunction1").html("<label> </b>" + result + "<\/label><br/>");
                    }
                    else if (subId == "link") {
                        var result = "<b> By " + subId + ".  </b> ";
                        result += "<br/>SelectedNode: <b> " + $("#treeFunctionMain").ojTree("getText", selectedNode) + ".  </b> ";
                        var locator = {"subId": "oj-tree-node[" + selectedNode + "]['link']"};
                        var node = $("#treeFunctionMain").ojTree("getNodeBySubId", locator);
                        result += "<br/><b>Text: " + $("#treeFunctionMain").ojTree("getText", node);
                        result += "</b><b>      <br/>Path: " + $("#treeFunctionMain").ojTree("getPath", node);
                        $("#resultsFunction1").html("<label> </b>" + result + "<\/label><br/>");
                    }


                }

                self.isLeaf = function(d, e) {
                    oj.Logger.info("In isLeaf ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    var isLeaf = $("#treeFunctionMain").ojTree("isLeaf", selectedNode);
                    var text = $("#treeFunctionMain").ojTree("getText", selectedNode);
                    if (isLeaf)
                        $("#resultsFunction1").html("<label> <b>Selected Node: </b>" + text + " is a LEAF node<\/label><br/>");
                    else
                        $("#resultsFunction1").html("<label> <b>Selected Node:</b> " + text + " is NOT a LEAF node and has children.<\/label><br/>");

                }

                self.getText = function(d, e) {
                    oj.Logger.info("In getText ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    var text = $("#treeFunctionMain").ojTree("getText", selectedNode);
                    if (text)
                        $("#resultsFunction1").html("<label> <b>getText() Results: </b>Selected Node Text: " + text + "<\/label><br/>");
                    else
                        $("#resultsFunction1").html("<label> <b>getText() Results: </b>No Nodes Selected.<\/label><br/>");

                }

                self.moveSelectedNode = function(position) {
		    oj.Logger.info("In moveSelectedNode ...");
		    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
		    if (selectedNode) {
			$("#treeFunctionMain").ojTree("move", selectedNode, $("#links"), position, false);
			$("#resultsFunction1").html("<label> <b> Moved " + $("#treeFunctionMain").ojTree("getText", selectedNode) + " to " + position + "<\/label><br/>");                      

		    }

		}

		self.copySelectedNode = function(position) {
		    oj.Logger.info("In copySelectedNode ...");
		    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
		    if (selectedNode) {
			$("#treeFunctionMain").ojTree("move", selectedNode, $("#links"), position, true);
			$("#resultsFunction1").html("<label> <b> Copied " + $("#treeFunctionMain").ojTree("getText", selectedNode) + " to " + position + "<\/label><br/>");                      
		    }

		}

		self.renameSelectedNode = function(position) {
		    oj.Logger.info("In renameSelectedNode ...");
		    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
		    if (selectedNode) {
			$("#treeFunctionMain").ojTree("rename", selectedNode, "Node renamed");
			    $("#resultsFunction1").html("<label> <b> Renamed to " + $("#treeFunctionMain").ojTree("getText", selectedNode)+ "<\/label><br/>");                              
		    }
                }
                
                self.count = ko.observable(0);

                self.refreshTree = function(d, e) {
                    oj.Logger.info("In refreshTree ...");
                    self.count(self.count() +1) ;
                    var treenode = {"attr": {"id": "fred" + self.count(), type: "myfolder",
                            "myattr": "thing"
                        },
                        "title": "Smith",
                        "metadata": {"p1": "Hello"},
                        "children": [
                            {"attr": {"id": "Bill" + self.count() , type: "myleaf"},
                                "title": "Jones"
                            }
                        ]
                    };

                    self.refreshData().data.push(treenode);
                    $("#treeFunctionMain").ojTree("option", "data", self.refreshData());
                    
                }
                
                
                self.addNodes  = function (d,e)
		{
		    // If selected node is "Select Me", add new nodes to tree.
		    // If selected node is "Delete Me", remove the added nodes.

		    oj.Logger.info("In addDelNodes ...");
		   // var node = $("#treeFunctionMain").ojTree("option", "selection")[0];

		    var node = $("#node0"); if (node) $("#treeFunctionMain").ojTree("select", node);

		    //  Add new node before "Select Me" node
		    $("#treeFunctionMain").ojTree("create", node, "before", {"title": "Delete Me", "attr": {"id": "node1"}});

		    //  Add new node after "Select Me" node
		    $("#treeFunctionMain").ojTree("create", node, "after", {"title": "Node 2", "attr": {"id": "node2"}});

		    //  Add new node inside "Select Me" node
		    $("#treeFunctionMain").ojTree("create", node, "inside", {"title": "Node 3", "attr": {"id": "node3"}});


		};
		                
		self.deleteNodes = function(d,e){
		    var node = $("#node1");
		    if (node) 
			$("#treeFunctionMain").ojTree("select", node);

		    var nodeId = $(node).attr("id");
		    if (nodeId == "node1") {

			$("#treeFunctionMain").ojTree("remove", "#node1");
			$("#treeFunctionMain").ojTree("remove", "#node2");
			$("#treeFunctionMain").ojTree("remove", "#node3");
		    }
		                   
                }


                self.toggleExpandSelectedNode = function(position) {
                    oj.Logger.info("In toggleExpandSelectedNode ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    if (selectedNode) {
                        $("#treeFunctionMain").ojTree("toggleExpand", selectedNode);
                        $("#resultsFunction1").html("<label> <b> Toggled " + $("#treeFunctionMain").ojTree("getText", selectedNode)+ "<\/label><br/>");                              

                    }

                }

                self.toggleSelectLinksNode = function(position) {
                    oj.Logger.info("In toggleSelectLinksNode ...");
                    var selectedNode = $("#links");
                    if (selectedNode) {
                        $("#treeFunctionMain").ojTree("toggleSelect", selectedNode);

                    }

                }

                self.addContextMenuToCollapsibleTree = function(d, e) {
                    oj.Logger.info("In addContextMenuToTree ...");
                    $("#treeCollapsible").ojTree("option", "contextMenu", "#myMenu");
                }

                self.removeContextMenuToCollapsibleTree = function(d, e) {
                    oj.Logger.info("In removeContextMenuToTree ...");
                    $("#treeCollapsible").ojTree("option", "contextMenu", "");
                }

                self.showIcons = function(d, e) {
                    oj.Logger.info("In showIcons ...");
                    
                    oj.Test.domNodeForLocator("{\"element\":\"#treeCollapsible\",\"subId\":\"oj-tree-node['#blogs1']['title']\"}");

                    if ($("#treeFunctionMain").ojTree("isIcons")) {
                        $("#treeFunctionMain").ojTree("option", "icons", false);
                    }
                    else {
                        $("#treeFunctionMain").ojTree("option", "icons", true);
                    }

                }


                self.disableTree = function(d, e) {
                    oj.Logger.info("In disableTree ...");

                    var disabled = $("#treeFunctionMain").ojTree("option", "disabled");

                    if (disabled) {
                        $("#treeFunctionMain").ojTree("option", "disabled", false);
                    }
                    else {
                        $("#treeFunctionMain").ojTree("option", "disabled", true);
                    }

                }



//************************************ MENU RELATED FUNCTIONS********************************************


                self.selectedMenuItem = ko.observable("(None selected yet)");

                self.menuItemSelect = function(event, ui) {
                    var itemSel = ui.item.children("a").text();
                    self.selectedMenuItem(itemSel);
                    $("#resultsFunction").html("<label> Selected menu Item: " + itemSel + "<\/label><br/>");
                }

                self.createMenuItemSelect = function(event, ui) {
                    oj.Logger.info("In createMenuItemSelect ...");
                    var idTemp = ui.item.children("a").parent().attr('id');
                    switch (idTemp) {
                        case "before":
                            vmTree.createNode("before");
                            break;
                        case "after":
                            vmTree.createNode("after");
                            break;
                        case "inside":
                            vmTree.createNode("inside");
                            break;
                        case "first":
                            vmTree.createNode("first");
                            break;
                        case "last":
                            vmTree.createNode("last");
                            break;
                        default:
                    }
                }

                self.createNode = function(position) {
                    oj.Logger.info("In createNode ... : position: " + position);
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];

                    var newNode = {"title": "NewNodeAdded" + position,
                        "attr": {"id": "nna"+ position},
                        "state": "open"
                    };

                    var newNode2 = {"title": "NewNode2" + position,
                        "attr": {"id": "nn2"+ position}
                    };
                    var newNode3 = {"title": "NewNode3" + position,
                        "attr": {"id": "nn3"+ position}
                    };
                    var newNode4 = {"title": "NewNode4" + position,
                        "attr": {"id": "nn4"+ position}
                    };

                    //  Insert new nodes under currently selected node    
                    if (selectedNode) {
                        var parent = $("#treeFunctionMain").ojTree("create", selectedNode, position, newNode, null, true);

                        $("#treeFunctionMain").ojTree("create", parent, 0, newNode2);
                        $("#treeFunctionMain").ojTree("create", parent, 0, newNode3);
                        $("#treeFunctionMain").ojTree("create", parent, 0, newNode4);
                        $("#treeFunctionMain").ojTree("expanded", newNode);

                    }

                }

                self.moveMenuItemSelect = function(event, ui) {
                    oj.Logger.info("In moveMenuItemSelect ...");
                    switch (ui.item.children("a").parent().attr('id')) {
                        case "before":
                            vmTree.moveSelectedNode("before");
                            break;
                        case "after":
                            vmTree.moveSelectedNode("after");
                            break;
                        case "inside":
                            vmTree.moveSelectedNode("inside");
                            break;
                        case "first":
                            vmTree.moveSelectedNode("first");
                            break;
                        case "last":
                            vmTree.moveSelectedNode("last");
                            break;
                        default:
                    }

                }

                self.copyMenuItemSelect = function(event, ui) {
                    oj.Logger.info("In copyMenuItemSelect ...");
                    switch (ui.item.children("a").parent().attr('id')) {
                        case "before":
                            vmTree.copySelectedNode("before");
                            break;
                        case "after":
                            vmTree.copySelectedNode("after");
                            break;
                        case "inside":
                            vmTree.copySelectedNode("inside");
                            break;
                        case "first":
                            vmTree.copySelectedNode("first");
                            break;
                        case "last":
                            vmTree.copySelectedNode("last");
                            break;
                        default:
                    }

                }

                self.getPathMenuItemSelect = function(event, ui) {
                    oj.Logger.info("In getPathMenuItemSelect ...");
                    var pathResult = null;
                    switch (ui.item.children("a").parent().attr('id')) {
                        case "ids":
                            {
                                pathResult = vmTree.getPathOfSelectedNode(true);
                                break;
                            }
                        case "nodenames":
                            {
                                pathResult = vmTree.getPathOfSelectedNode(false);
                                break;
                            }

                        default:
                    }
                    $("#resultsFunction1").html("<label> <b>" + pathResult + "</b> </label>");

                }

                self.getPathOfSelectedNode = function(idmode) {
                    oj.Logger.info("In getPathOfSelectedNode ...");
                    var selectedNode = $("#treeFunctionMain").ojTree("option", "selection")[0];
                    var path = "";
                    if (selectedNode) {
                        path = $("#treeFunctionMain").ojTree("getPath", selectedNode, idmode);
                    }
                    else {
                        pathResult = " Please select a node and then invoke the operation.";
                        oj.Logger.info(pathResult);
                        return pathResult;
                    }

                    var pathResult = "path: ";
                    if (idmode) {
                        for (i = 0; i < path.length; i++) {
                            pathResult += "  -->  " + i + " = " + $("#treeFunctionMain").ojTree("getText", $("#" + path[i]));
                        }
                    }
                    else
                        pathResult += path;
                    oj.Logger.info(pathResult);
                    return pathResult;
                }







            }

            vmTree = new treeFunctionViewModel();

            $(document).ready(function() {
                ko.applyBindings(vmTree, document.getElementById('mainContents'));

                var node;
                $("#treeFunctionMain").on("ojselect", function(e, ui) {

                    oj.Logger.info("In ojselect event (on method)");
                    var nodeId;
                    var id = ui.item.attr("id");     //  get node or menu id
                    if (node) {
                        nodeId = node.attr("id");
                        $("#resultsFunction1").html("<label> <b> ojselect event method -- <\/b>  menu id=" + id + " on tree node id=" + nodeId + "<\/label>");
                    }
                    else {
                        $("#resultsFunction1").html("<label> <b> ojselect event method -- <\/b> node id=" + id + " selected<\/label>");
                    }
                    node = null;
                });


                $("#treeFunctionMain").on("ojbefore", function(e, ui) {
                    oj.Logger.info("In tree before event");
                    $("#resultsFunction1").html("<label> <b> ojbefore event method -- <\/b> Function Name=" + ui.func + "<\/label>");


                });

                $("#treeFunctionMain").on("ojexpand", function(e, ui) {
                    oj.Logger.info("In tree expand event");
                    var id = ui.item.attr("id");     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojexpand event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojcollapse", function(e, ui) {
                    oj.Logger.info("In treecollapse event");
                    var id = ui.item.attr("id");     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojcollapse event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojcreate", function(e, ui) {
                    oj.Logger.info("In treecreate event");
                    var id = ui.item.attr("id");     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojcreate event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojdehover", function(e, ui) {
                    oj.Logger.info("In treedehover event");
                    var id = ui.item.attr("id");     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojdehover event method -- <\/b> node id=" + id + " selected<\/label>");
                });

                $("#treeFunctionMain").on("ojremove", function(e, ui) {
                    oj.Logger.info("In tree node remove event");
                    var id = ui.item.attr("id");     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojremove event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojdeselect", function(e, ui) {
                    oj.Logger.info("In treedeselect event");
                    var id = ui.item.attr("id");     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojdeselect event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojdeselectall", function(e, ui) {
                    oj.Logger.info("In treedeselectall event");
                    var id = ui.item.attr("id");     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojdeselectall event method -- <\/b> node id=" + id + " selected<\/label>");

                });

                $("#treeFunctionMain").on("ojdestroy", function(e, ui) {
                    oj.Logger.info("In treedestroy event");
                    // var id   = ui.item.attr("id") ;     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojdestroy event method -- <\/b> <\/label>");


                });
                $("#treeFunctionMain").on("ojfocus", function(e, ui) {
                    oj.Logger.info("In treefocus event");
                    var id = ui.item.attr("id");     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojfocus event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojhover", function(e, ui) {
                    oj.Logger.info("In treehover event");
                    var id = ui.item.attr("id");     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojhover event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojloaded", function(e, ui) {
                    oj.Logger.info("In treeloaded event");
                    var item = ui.item;
                    var id;
                    if (item =-1 )
                        id = null;
                    else                            
                       id = item.attr("id");     //  get node or menu id
                    $("#resultsFunction1").html("<label> <b> ojloaded event method -- <\/b> node id=" + id + " selected. Expanded links and Suppliers.<\/label>");
                    $("#treeFunctionMain").ojTree("expanded", [$("#links"), $("#sups")]);


                });

                $("#treeFunctionMain").on("ojmove", function(e, ui) {
                    oj.Logger.info("In treemove event");
                    var id = ui.item.attr("id");
                    $("#resultsFunction1").html("<label> <b> ojmove event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojrename", function(e, ui) {
                    oj.Logger.info("In treerename event");
                    var id = ui.item.attr("id");
                    $("#resultsFunction1").html("<label> <b> ojrename event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojunfocus", function(e, ui) {
                    oj.Logger.info("In treeunfocus event");
                    var id = ui.item.attr("id");
                    $("#resultsFunction1").html("<label> <b> ojunfoces event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojcollapseall", function(e, ui) {
                    oj.Logger.info("In ojcollapseall event");
                    var id = ui.item.attr("id");
                    $("#resultsFunction1").html("<label> <b> ojcollapseall event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojexpandall", function(e, ui) {
                    oj.Logger.info("In ojexpandall event");
                    var id = ui.item.attr("id");
                    $("#resultsFunction1").html("<label> <b> ojexpandall event method -- <\/b> node id=" + id + " selected<\/label>");


                });

                $("#treeFunctionMain").on("ojoptionchange", function(e, ui) {
                   
                 if (ui.option == "selection") {
                   oj.Logger.info("OjOptionChange - Selection event : " + $("#treeFunctionMain").ojTree("getText", ui.value[0]));
                   $("#resultsFunction").html("<label> <b> "+ $("#treeFunctionMain").ojTree("getText", ui.value[0]) +"</b></label>") ;
                 }
              });
              
              $("#treeFunctionMain").on("ojrefresh", function(e, ui) {
                    oj.Logger.info("In ojrefresh event");
                    var item = ui.item;
                    if (item == -1) {
                        $("#resultsFunction1").html("<label> <b> In ojrefresh event <\/b> WHOLE TREE REFRESHED   <\/label>");
                    } else {
                        var id = ui.item.attr("id");
                        $("#resultsFunction1").html("<label> <b> In ojrefresh event <\/b> id=" + id + "<\/label>");
                    }
                });

            });

        });         