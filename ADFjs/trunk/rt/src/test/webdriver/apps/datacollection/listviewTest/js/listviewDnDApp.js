require(['ojs/ojcore','jquery','knockout','listviewDnDVM',
        'ojs/ojknockout','app'],
        function(oj,$,ko,lvDnDVM) {   
                                
        
        $(document).ready(function(){
                                    MasterVM= new lvDnDVM();
                                    //lvModel=MasterVM.lvVM;
                                    lvDnDModel=MasterVM[0];
                                    tableDnDModel=MasterVM[1];
            
                                     ko.applyBindings(lvDnDModel, document.getElementById('listview2'));
                                     ko.applyBindings(tableDnDModel, document.getElementById('table2'));
                                    
                                });
              });

                    
        