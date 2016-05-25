require(['ojs/ojcore','jquery','knockout','menuVM','genericOptionsVM','bkUtils',
                 'simpleMenuVM','app'],
         function(oj,$,ko,model,gvm,bku,svm) {   

         oj.Test.ready = true;
            
         masterVM  = {
            model : new model(),
            gvm : new gvm(),
            bk : new bku(),
            svm : new svm()
            };            

        $(document).ready(function(){
                $('#controls-container').load("../../common/controlsContainer.html",
                    function () {
                       $("#menu-controls-container").load("../../common/menuControlsContainer.html",
                            function() {
                                $("#menu_container").load("../../common/menuDivsIcons.html",
                                    function() {
                                    ko.applyBindings(masterVM);                                
//                                    $("#anchor").click(masterVM.bvm.buttonClick);
//                                    $("#text-input").on({'ojoptionchange': masterVM.model.valueChangeHandler});
                                });
                            });
                        });
              });
         
});
