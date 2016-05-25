/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require(['ojs/ojcore', 'knockout', 'jquery',
    'ojs/ojknockout', 'ojs/ojmasonrylayout', 'ojs/ojbutton', 'promise', 'ojs/ojtable'],
        function (oj, ko, $)
        {
            $(document).ready(
                    function ()
                    {
                        function MyModel() {
                            var self = this;

                            self.chemicals = [
                                {name: 'Hydrogen', sizeClass: 'oj-masonrylayout-tile-1x1', collapsedSizeClass: 'oj-masonrylayout-tile-2x2', expandedSizeClass: 'oj-masonrylayout-tile-3x2', expanded: ko.observable(false), inserted: ko.observable(true)},
                                {name: 'Helium', sizeClass: 'oj-masonrylayout-tile-2x3', collapsedSizeClass: 'oj-masonrylayout-tile-1x1', expandedSizeClass: 'oj-masonrylayout-tile-1x2', expanded: ko.observable(false), inserted: ko.observable(true)},
                                {name: 'Lithium', sizeClass: 'oj-masonrylayout-tile-1x3', collapsedSizeClass: 'oj-masonrylayout-tile-1x1', expandedSizeClass: 'oj-masonrylayout-tile-2x2', expanded: ko.observable(false), inserted: ko.observable(true)},
                                {name: 'Beryllium', sizeClass: 'oj-masonrylayout-tile-1x1', collapsedSizeClass: 'oj-masonrylayout-tile-1x1', expandedSizeClass: 'oj-masonrylayout-tile-2x1', expanded: ko.observable(false), inserted: ko.observable(true)},
                                {name: 'Boron', sizeClass: 'oj-masonrylayout-tile-1x1', collapsedSizeClass: 'oj-masonrylayout-tile-1x1', expandedSizeClass: 'oj-masonrylayout-tile-1x2', expanded: ko.observable(false), inserted: ko.observable(true)},
                                {name: 'Carbon', sizeClass: 'oj-masonrylayout-tile-2x1', collapsedSizeClass: 'oj-masonrylayout-tile-3x1', expandedSizeClass: 'oj-masonrylayout-tile-3x1', expanded: ko.observable(false), inserted: ko.observable(true)},
                                {name: 'Nitrogen', sizeClass: 'oj-masonrylayout-tile-2x1', collapsedSizeClass: 'oj-masonrylayout-tile-1x1', expandedSizeClass: 'oj-masonrylayout-tile-2x1', expanded: ko.observable(false), inserted: ko.observable(true)},
                                {name: 'Oxygen', sizeClass: 'oj-masonrylayout-tile-1x1', collapsedSizeClass: 'oj-masonrylayout-tile-3x1', expandedSizeClass: 'oj-masonrylayout-tile-2x1', expanded: ko.observable(false), inserted: ko.observable(true)}
                            ];


                            self.handledCollapse = false;

                            //This listener is called when the resize button in the tile is clicked.
                            demoResizeTile = function (data, event)
                            {
                                var target = event.target;
                                var tile = $(target).closest(".demo-tile");
                                var masonryLayout = tile.closest(":oj-masonrylayout");
                                masonryLayout.ojMasonryLayout("resizeTile", "#" + tile.attr("id"), data.expanded() ? data.collapsedSizeClass : data.expandedSizeClass);
                            };

                             //This listener is called before the tile is removed.
                            //This listener is called before the tile is resized.
                            demoHandleBeforeResize = function (event, ui)
                            {
                                 var tile = ui.tile;
                                console.log("Tile before resize: " + tile.attr("id"));
                                //get the ko binding context for the tile DOM element
                                var context = ko.contextFor(tile[0]);
                                //get the data for the tile, which will be an item in the 
                                //self.chemicals array defined above
                                var data = context.$data;
                                //If the tile is being collapsed, hide the additional expanded content
                                //before the tile is resized.
                                if (data.expanded())
                                {
                                    data.expanded(false);
                                    //Remember that this resize is a collapse so that the "resize" event
                                    //listener called after the tile is resized does not also try to 
                                    //handle the operation.
                                    self.handledCollapse = true;
                                }
                            };

                            //This listener is called after the tile is resized.
                            demoHandleResize = function (event, ui)
                            {
                                console.log("Tile after resize: " + ui.tile.attr("id"));
                                //Only handle the event if the tile is being expanded.
                                if (!self.handledCollapse)
                                {
                                    var tile = ui.tile;
                                    
                                    //get the ko binding context for the tile DOM element
                                    var context = ko.contextFor(tile[0]);
                                    //get the data for the tile, which will be an item in the 
                                    //self.chemicals array defined above
                                    var data = context.$data;
                                    //If the tile is being expanded, show the additional content after 
                                    //the tile is resized.
                                    if (!data.expanded())
                                    {
                                        data.expanded(true);
                                    }
                                }
                                //Reset the flag for the next resize.
                                self.handledCollapse = false;
                            };

                            demoHandleBeforeRemove = function (event, ui)
                            {
                                var tile = ui.tile;
                                console.log("Tile before removed: " + tile.attr("id"));                                
                            };
                            
                            demoRemoveTile = function (data, event)
                            {
                                var target = event.target;
                                var tile = $(target).closest(".demo-tile");
                                var masonryLayout = tile.closest(":oj-masonrylayout");
                                masonryLayout.ojMasonryLayout("removeTile", "#" + tile.attr("id"));
                            };

                            //This listener is called after the tile is removed from the masonry layout.
                            demoHandleRemove = function (event, ui)
                            {
                                var tile = ui.tile;
                                
                                 console.log("Tile after removed: " + tile.attr("id")); 
                                 //get the ko binding context for the tile DOM element
                                var context = ko.contextFor(tile[0]);
                                //get the data for the tile, which will be an item in the 
                                //self.chemicals array defined above
                                var data = context.$data;
                                data.inserted(false);

                                //Temporarily store the removed tile in a hidden holding area.
                                var removedTilesHolder = $("#removedTilesHolder");
                                removedTilesHolder.append($(tile));
                            };

                            demoHandleBeforeInsert = function (event, ui)
                            {
                                var tile = ui.tile;
                                console.log("Tile being inserted: " + tile.attr("id"));                                
                            };
                            
                            demoHandlerBeforeReorder = function (event, ui)
                            {
                                var tile = ui.tile;
                                console.log("Tile being reordered: " + tile.attr("id")); 
                                $("#index").html("<label><b>"+ tile.index()+" <\/b> <\/label>");                                
                            };
                            
                             demoHandlerReorder = function (event,ui)
                            {
                                var tile = ui.tile;
                                console.log("Tile already reordered: " + tile.attr("id"));  
                                $("#index").html("<label><b>"+ tile.index()+" <\/b> <\/label>");                                
                                
                            };
                            
                            destroy = function (event, ui)
                            {
                                console.log("Destroy function called");  
                                var masonryLayout = $(":oj-masonrylayout");
                                masonryLayout.ojMasonryLayout("destroy");
                            };
                            
                            destroyHandler = function (event, ui)
                            {
                                console.log("Already Destroyed");  
                                                                 
                            };
                            
                            demoInsertTile = function (tileId, name)
                            {
                                var masonryLayout = $(":oj-masonrylayout");
                                var chemicals = self.chemicals;
                                var insertIndex = 0;
                                for (var i = 0; i < chemicals.length; i++)
                                {
                                    if (chemicals[i].name === name)
                                        break;
                                    else if (chemicals[i].inserted())
                                        insertIndex++;
                                }
                                masonryLayout.ojMasonryLayout("insertTile", "#" + tileId, insertIndex);
                            };

                            //This listener is called after the tile is inserted into the masonry layout.
                            demoHandleInsert = function (event, ui)
                            {
                                var tile = ui.tile;
                                //get the ko binding context for the tile DOM element
                                var context = ko.contextFor(tile[0]);
                                //get the data for the tile, which will be an item in the 
                                //self.chemicals array defined above
                                var data = context.$data;
                                data.inserted(true);
                            };


                            getDragHandleId = function(index)
                                {
                                  return 'dragHandle' + (index + 1);
                                };

                                getDragHandleLabelledBy = function(index)
                                {
                                  return getDragHandleId(index) + ' ' + getLabelId(index);
                                };
                                
                            getTileId = function (index)
                            {
                                return 'tile' + (index + 1);
                            };

                            getLabelId = function (index)
                            {
                                return 'label' + (index + 1);
                            };

                            getButtonTitle = function (data)
                            {
                                return data.expanded() ? 'Collapse' : 'Expand';
                            };

                            getButtonId = function (index)
                            {
                                return 'resizeButton' + (index + 1);
                            };

                            getButtonLabelledBy = function (index)
                            {
                                return getButtonId(index) + ' ' + getLabelId(index);
                            };

                            getButtonLabel = function (data)
                            {
                                return data.expanded() ? 'Collapse' : 'Expand';
                            };

                            getButtonStartIcon = function (data)
                            {
                                return (data.expanded() ? 'oj-fwk-icon-arrow-n-start' : 'oj-fwk-icon-arrow-s-end') + ' oj-component-icon';
                            };


                            // Expanded Content
                            var deptArray = [{DepartmentId: 1001, DepartmentName: 'ADFPM 1001 neverending', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 556, DepartmentName: 'BB', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 10, DepartmentName: 'Administration', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 20, DepartmentName: 'Marketing', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 30, DepartmentName: 'Purchasing', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 40, DepartmentName: 'Human Resources1', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 50, DepartmentName: 'Administration2', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 60, DepartmentName: 'Marketing3', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 70, DepartmentName: 'Purchasing4', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 80, DepartmentName: 'Human Resources5', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 90, DepartmentName: 'Human Resources11', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 100, DepartmentName: 'Administration12', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 110, DepartmentName: 'Marketing13', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 120, DepartmentName: 'Purchasing14', LocationId: 200, ManagerId: 300},
                                {DepartmentId: 130, DepartmentName: 'Human Resources15', LocationId: 200, ManagerId: 300}];

                            self.datasource = new oj.ArrayTableDataSource(deptArray, {idAttribute: 'DepartmentId'});



                        }

                        ko.applyBindings(new MyModel(),
                                document.getElementById('masonrylayout-basic-example'));
                    }
            );
        });

