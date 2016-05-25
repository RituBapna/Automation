/**
 * A top-level require call executed by the Application.
 * 
 */

require(['ojs/ojcore','jquery','knockout','inputTimeVM','genericOptionsVM','bkUtils',
                 'simpleMenuVM','ojs/ojdatetimepicker','app'],
                function(oj,$,ko,model,gvm,bku,svm) {   

            oj.Test.ready = true;
                        
            masterVM  = {
                    model : new model(),
                    gvm : new gvm(),
                    bk : new bku(),
                    svm : new svm()                    
                };
            
            masterVM.gvm.isDisabled(true);
            if (typeof compExposed != 'undefined') {
                if (compExposed == false) {
                        masterVM.model.toggleExposed();    
                }
            }
                        
            $(document).ready(function(){
                // Apply Bindings after loading control div
                $('#controls-container').load("../../common/controlsContainer.html",
                    function () {
                       $("#inputControls-container").load("../../common/inputControlsContainer.html",
                            function() {
                                $("#menu_container").load("../../common/menuDivs.html",
                                    function() {
                                    ko.applyBindings(masterVM);                                
                                    $("#text-input").on({'ojoptionchange': masterVM.model.valueChangeHandler});
                                });
                            });
                        });
              });
            
        });




