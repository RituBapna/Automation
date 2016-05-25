require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents'],
        function(oj, ko, $)
        {
            function treeDataModel() {
                self = this;

               

            }

            var vm1 = new treeDataModel();

            $(document).ready(function() {

                var treeData1 = [
		                    {
		                        "title": "Home " ,
		                        "attr": {"id": "home", "special_node": true, "special_data": "hello1", "title":"Home node with special data", "type":"myroot"},
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
		
		   
		
                var jsonDS = new oj.JsonTreeDataSource(treeData1);
                
                var nodes = [$("#sups"), $("#references")];
       
    
    
                $("#myTree").ojTree({
                    id: "demoTree1",
                    data: jsonDS,
                    initExpanded: ['#links', '#sups']
                    })
                    
                ko.applyBindings(vm1, document.getElementById('treewithoutko'));
            });
        });




