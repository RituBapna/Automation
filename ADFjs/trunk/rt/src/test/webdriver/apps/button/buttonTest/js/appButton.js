require(['ojs/ojcore','jquery','knockout','buttonVM','genericOptionsVM',
        'simpleMenuVM','ojs/ojknockout','app'],
        function(oj,$,ko,model,gvm,svm) {   
                                
        oj.Test.ready = true;
                                
        masterVM  = {
                model : new model(),
                gvm : new gvm(),
                svm : new svm()
               };
                    
        $(document).ready(function(){
                $('#controls-container').load("../../common/controlsContainer.html",
                    function () {
                       $("#button-controls-container").load("../../common/buttonControlsContainer.html",
                            function() {
                                $("#menu_container").load("../../common/menuDivsIcons.html",
                                    function() {
                                    ko.applyBindings(masterVM);                                
                                    $("#anchor").click(masterVM.model.buttonClick);
//                                    $("#text-input").on({'ojoptionchange': masterVM.model.valueChangeHandler});
                                });
                            });
                        });
              });

                    
        });
