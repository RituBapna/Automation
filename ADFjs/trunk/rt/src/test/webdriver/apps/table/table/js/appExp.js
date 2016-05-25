/**
 * A top-level require call executed by the Application.
 * 
 */

require(['ojs/ojcore','jquery','knockout','tableBaseVM','genericOptionsVM','bkUtils',
                 'simpleMenuVM','ojs/ojknockout','ojs/ojtable','ojs/ojrowexpander'],        
                function(oj,$,ko,tmodel,gvm,bku,svm) {   

            oj.Test.ready = true;
                        
            masterVM  = {
                    model : new tmodel(),
                    gvm : new gvm(),
                    bk : new bku(),
                    svm : new svm(),
                    datasource: undefined
                };
            
            $.getJSON("../../common/js/json/projectData.json", function(data) 
                {
                var options = [];
                masterVM.datasource = new 
                        oj.FlattenedTreeTableDataSource(new
                        oj.FlattenedTreeDataSource(new
                        oj.JsonTreeDataSource(data), options));
                });

            
            masterVM.model.datasource(masterVM.datasource);
            masterVM.model.datasourceDescr("json data from file for row expander");
            masterVM.model.hasExpander(true);                        
                        
            $(document).ready(function(){
                // Apply Bindings after loading control div
                $('#controls-container').load("../../common/controlsContainer.html",
                    function () {
                       $("#tableControls-container").load("../../common/tableControlsContainer.html",
                            function() {
                                         $("#menu_container").load("../../common/menuDivs.html",
                                              function() {
                                                masterVM.model.setHandlers();
                                                ko.applyBindings(masterVM);

                                });
                            });
                        });
              });
            
        });


