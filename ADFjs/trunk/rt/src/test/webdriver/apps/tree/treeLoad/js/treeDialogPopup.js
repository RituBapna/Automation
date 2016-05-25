require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents'],
        function(oj, ko, $)
        {
        oj.Logger.option("level", oj.Logger.LEVEL_INFO);
                function treePopupViewModel() {
                var self = this;
                        self.expnodes = ["#references", "#sups"];
                        
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
                                "image": self.baseurl + "/img/noexpand.gif"
                                },
                                "default": {
                                "image": self.baseurl + "/img/default.gif"
                                }
                        }
                        };
                        self.treeFuncData = [
                        {
                        "title": "Home",
                                "attr": {"id": "home", "type": "myleaf"}
                        },
                        {
                        "title": "News",
                                "attr": {"id": "news", "type": "myleaf"}
                        },
                        {
                        "title": "Blogs",
                                "attr": {"id": "blogs", "type": "myfolder"},
                                "children": [{"title": "Today",
                                        "attr": {"id": "today", "type": "myleaf"}
                                },
                                {"title": "Yesterday",
                                        "attr": {"id": "yesterday", "type": "myleaf"}
                                },
                                {"title": "2 Days Back",
                                        "attr": {"id": "2daysback", "type": "myleaf"}
                                },
                                {"title": "Archive",
                                        "attr": {"id": "archive", "type": "myleaf"}
                                }
                                ]
                        },
                        {
                        "title": "Links",
                                "attr": {"id": "links", "type": "myfolder"},
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
                        
                        
                        self.treeFuncData11 = [
                        {
                        "title": "Home",
                                "attr": {"id": "home11", "type": "myleaf"}
                        },
                        {
                        "title": "News",
                                "attr": {"id": "news11", "type": "myleaf"}
                        },
                        {
                        "title": "Blogs",
                                "attr": {"id": "blogs11", "type": "myfolder"},
                                "children": [{"title": "Today",
                                        "attr": {"id": "today11", "type": "myleaf"}
                                },
                                {"title": "Yesterday",
                                        "attr": {"id": "yesterday11", "type": "myleaf"}
                                },
                                {"title": "2 Days Back",
                                        "attr": {"id": "2daysback11", "type": "myleaf"}
                                },
                                {"title": "Archive",
                                        "attr": {"id": "archive11", "type": "myleaf"}
                                }
                                ]
                        },
                        {
                        "title": "Links",
                                "attr": {"id": "links11", "type": "myfolder"},
                                "children": [{"title": "Oracle",
                                        "attr": {"id": "oracle11"}
                                },
                                {"title": "IBM",
                                        "attr": {"id": "ibm11"}
                                },
                                {"title": "Microsoft",
                                        "attr": {"id": "ms11"},
                                        "children": [{"title": "USA",
                                                "attr": {"id": "msusa"},
                                                "children": [{"title": "North",
                                                        "attr": {"id": "msusanorth11"}
                                                },
                                                {"title": "South",
                                                        "attr": {"id": "msusasouth11"}
                                                },
                                                {"title": "East",
                                                        "attr": {"id": "msusaeast11"}
                                                },
                                                {"title": "West",
                                                        "attr": {"id": "msusawest11"}
                                                }
                                                ]
                                        },
                                        {"title": "Europe",
                                                "attr": {"id": "msuerope11"}
                                        },
                                        {"title": "Asia",
                                                "attr": {"id": "msasia11"},
                                                "children": [{"title": "Japan",
                                                        "attr": {"id": "asiajap11"}
                                                },
                                                {"title": "China",
                                                        "attr": {"id": "asiachina11"}
                                                },
                                                {"title": "India",
                                                        "attr": {"id": "asiaindia11"}
                                                }
                                                ]
                                        }
                                        ]
                                }
                                ]
                        },
                        {
                        "title": "Sponsors",
                                "attr": {"id": "sponsors11"}
                        },
                        {
                        "title": "Corporate",
                                "attr": {"id": "corporate11"}
                        },
                        {
                        "title": "References",
                                "attr": {"id": "references11"},
                                "children": [{"title": "All",
                                        "attr": {"id": "refsall11"}
                                },
                                {"title": "USA",
                                        "attr": {"id": "refsusa11"}
                                },
                                {"title": "Europe",
                                        "attr": {"id": "refseurope11"}
                                },
                                {"title": "Asia",
                                        "attr": {"id": "refsasia11"}
                                }
                                ]
                        },
                        {
                        "title": "Suppliers",
                                "attr": {"id": "sups11"},
                                "children": [{"title": "Gold Tier",
                                        "attr": {"id": "supgold11"}
                                },
                                {"title": "Silver Tier",
                                        "attr": {"id": "supsilver11"}
                                },
                                {"title": "Non-contract",
                                        "attr": {"id": "supcon11"}
                                },
                                {"title": "Independent",
                                        "attr": {"id": "supind11"}
                                }
                                ]
                        }
                        ];
                        
                        
                        self.dndoptsFunc = {
                            "reorder": true
                        };
                        self.cmopts1 = {
                            "menu": "moveNodeMenu"
                        };
                        
                        self.stillCanClickMe = function(d, e) {
                            oj.Logger.info("In stillCanClickMe ...");
                            $('#modalDialog0').ojDialog('open');
                        };
                        
                        self.closeMD1 = function(d, e) {
                            oj.Logger.info("In closeMD1 ...");
                            $('#modalDialog1').ojDialog('close');
                        };
                        
                        self.layoutData = new oj.JsonTreeDataSource(self.treeFuncData);
                        self.layoutData11 = new oj.JsonTreeDataSource(self.treeFuncData11);
                        
                        self.moveSelectedNode = function(position){
                                oj.Logger.info("In moveSelectedNode ...");
                                var selectedNode = $("#treeModelDialog1").ojTree("option","selection")[0];  
                                if (selectedNode) {
                                    $("#treeModelDialog1").ojTree("move", selectedNode, $("#links11"), position, false);                                     
                                }       
                                
                                $("#treeModelDialog1").ojTree("expandAll", $("#links11"));
                                //$("#treeModelDialog1").ojTree("refresh");    
                        }


                        self.moveMenuItemSelect = function(event, ui) {
                            oj.Logger.info("In moveMenuItemSelect ...");
                            var strAttr = ui.item.children("a").parent().attr('id');
                            switch (strAttr){
                                case "before": vmPopupTree.moveSelectedNode("before"); break;
                                case "after": vmPopupTree.moveSelectedNode("after"); break;
                                case "inside": vmPopupTree.moveSelectedNode("inside"); break;
                                case "first": vmPopupTree.moveSelectedNode("first"); break;
                                case "last": vmPopupTree.moveSelectedNode("last"); break;
                                default:
                             }                             
                         }
                }

                vmPopupTree = new treePopupViewModel();
                
                $(document).ready(function() {
                        $("#okButton").click(function() {
                                $("#modalDialog1").ojDialog("close");
                        });
                        
                        $("#popup1").on("ojbeforeclose", function () {
                            $("#popup2").ojPopup("close");   
                        });
                        
                ko.applyBindings(vmPopupTree, document.getElementById('vmPopupContainer'));
      });
});


