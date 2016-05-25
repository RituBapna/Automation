/**
 * A top-level require call executed by the Application.
 * 
 */

require(['ojs/ojcore','jquery','knockout','sliderVM','app'],
                function(oj,$,ko,svm) {   

            //oj.Test.ready = true;
                        
            masterVM  = {
                    
                    sliderModelVM : new svm()                    
                };
            
           /* masterVM.gvm.isDisabled(true);
            if (typeof compExposed != 'undefined') {
                if (compExposed == false) {
                        masterVM.model.toggleExposed();    
                }
            }*/
    $(document).ready(function(){
       $('#loadOutputPage').load("../../common/output.html",function(){
           //ko.applyBindings(masterVM.svm, document.getElementById('wrapperDiv'));
           ko.applyBindings(masterVM);
           $("#inputslider-id").on({'ojoptionchange': masterVM.sliderModelVM.valueChangeHandler});
       }) 
        
    });
                        
             
        });




