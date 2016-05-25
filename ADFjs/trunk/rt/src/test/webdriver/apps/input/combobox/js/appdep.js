/**
 * A top-level require call executed by the Application.
 * 
 */

require(['ojs/ojcore','jquery','knockout','comboboxVM','genericOptionsVM','bkUtils',
                 'simpleMenuVM'],
        function(oj,$,ko,model,gvm,bku,svm) {   
                        
            oj.Test.ready = true;                        
                        
            masterVM  = {
                    model : new model(),
                    gvm : new gvm(),
                    bk : new bku(),
                    ko : ko,
                    svm : new svm()
                };

            masterVM.gvm.isDisabled(true);
            masterVM.model.isDep(true);
            
            $(document).ready(function(){
                // Apply Bindings after loading control div
                $('#controls-container').load("../../common/controlsContainer.html",
                    function () {
                       $("#inputControls-container").load("../../common/inputControlsContainer.html",
                            function() {
                                $("#menu_container").load("../../common/menuDivs.html",
                                function() {
                                    ko.applyBindings(masterVM);
                                    masterVM.model.init = true;
                                    $( "#combobox" ).on("ojexpand", masterVM.model.expandHandler );
                                });
                            });
                        });
              });
            
        });
