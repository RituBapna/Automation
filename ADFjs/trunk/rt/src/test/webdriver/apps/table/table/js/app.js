/**
 * A top-level require call executed by the Application.
 * 
 */

require(['ojs/ojcore','jquery','knockout','tableBaseVM','genericOptionsVM','bkUtils',
                 'simpleMenuVM', 'app','ojs/ojknockout','ojs/ojtable'],
                function(oj,$,ko,tmodel,gvm,bku,svm) {   

            oj.Test.ready = true;
                        
            masterVM  = {
                    model : new tmodel(),
                    gvm : new gvm(),
                    bk : new bku(),
                    svm : new svm()
                };
            
            // start with mock Collection ds
            // masterVM.model.goMockCollection();
            masterVM.model.initMockRows(4);  // async, done eventually
            if (typeof compExposed != 'undefined') {
                if (compExposed == false) {
                        masterVM.model.toggleExposed();    
                }
            }

           // only way to unique id the table row menu buttons                                                
            function setMbIds() {
                  $('[id^="mb"]').each(function(i, e){
                      $(this).attr("id", "mbid_" + i);
                  }); 
            };
                        
                        
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
                                                setTimeout( setMbIds, 100 );
                                                });
                                    });
                            });
              });
            
        });



