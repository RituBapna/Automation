require(['ojs/ojcore','jquery','knockout','listviewVM',
        'ojs/ojknockout','app'],
        function(oj,$,ko,lvVM) {   
                                
        masterVM  = {
                lvMod : new lvVM()
                
               };
                    
        $(document).ready(function(){
                                    //ko.applyBindings(masterVM.lvMod); 
            
                                     //ko.applyBindings(masterVM.lvMod, document.getElementById('contentSelectionwrapperDiv'));
                                     ko.applyBindings(masterVM.lvMod, document.getElementById('listview'));
                                     ko.applyBindings(masterVM.lvMod, document.getElementById('nestedLV'));
                                     ko.applyBindings(masterVM.lvMod, document.getElementById('selectionDiv'));
                                    ko.applyBindings(masterVM.lvMod, document.getElementById('outputText'));
                                });
              });

                    
        