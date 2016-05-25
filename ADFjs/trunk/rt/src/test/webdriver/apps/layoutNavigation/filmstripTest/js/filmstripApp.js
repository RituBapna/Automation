require(['ojs/ojcore','jquery','knockout','filmstripVM',
        'ojs/ojknockout','app'],
        function(oj,$,ko,fmVM) {   
                                
        masterVM  = {
                fsMod : new fmVM()
                
               };
                    
        $(document).ready(function(){
                                    ko.applyBindings(masterVM.fsMod);                                
                                });
              });

                    
        