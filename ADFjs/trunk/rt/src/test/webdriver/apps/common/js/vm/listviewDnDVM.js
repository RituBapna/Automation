define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'promise', 'ojs/ojlistviewdnd', 'ojs/ojtable', 'ojs/ojarraytabledatasource'],
        function (oj, ko, $) {
            function TableViewDnDModel(dataArray)
                {
                    var self = this;
                    self.selection = [];
                    self.arr = ko.observableArray(dataArray);
                    self.datasource = new oj.ArrayTableDataSource(self.arr, {idAttribute: 'id'});

                    self.handleDrop = function (event, ui)
                    {
                        var data, i;

                        event.preventDefault();

                        data = event.originalEvent.dataTransfer.getData("application/ojlistviewitems+json");
                        if (data != null)
                        {
                            data = JSON.parse(data);
                            for (i = data.length - 1; i >= 0; i--)
                            {
                                dataArray.splice(ui.rowIndex, 0, data[i]);
                            }
                            self.arr.valueHasMutated();
                        }
                    };

                    self.handleDragEnd = function (event, ui)
                    {
                        if (event.originalEvent.dataTransfer.dropEffect === "move")
                        {
                            for (i = 0; i < self.selection.length; i++)
                            {
                                var start = self.selection[i].startIndex.row;
                                var end = self.selection[i].endIndex.row;
                                dataArray.splice(start, end - start + 1);
                            }

                            self.arr.valueHasMutated();
                        }
                    };
                }
            //listview model starts here
    
            function ListViewDnDModel(dataArray)
            {
                var self = this;
                self.selection = [];
                self.arr = ko.observableArray(dataArray);
                self.datasource = new oj.ArrayTableDataSource(self.arr, {idAttribute: 'id'});

                self.handleDrop = function (event, ui)
                {
                    var data, context, index, i;

                    data = event.originalEvent.dataTransfer.getData("application/ojtablerows+json");
                    data = JSON.parse(data);

                    context = $('#listview').ojListView('getContextByNode', ui.item);
                    index = context.index;
                    if (ui.position === "after")
                    {
                        index = index + 1;
                    }

                    for (i = data.length - 1; i >= 0; i--)
                    {
                        dataArray.splice(index, 0, data[i].data);
                    }
                    self.arr.valueHasMutated();

                    return false;
                };

                self.handleDragEnd = function (event, ui)
                {
                    var i, j;

                    if (event.originalEvent.dataTransfer.dropEffect === "move")
                    {
                        for (i = 0; i < self.selection.length; i++)
                        {
                            for (j = 0; j < dataArray.length; j++)
                            {
                                // remove the selected items from array
                                if (dataArray[j].id == self.selection[i])
                                {
                                    dataArray.splice(j, 1);
                                    break;
                                }
                            }
                        }

                        self.arr.valueHasMutated();
                    }
                };
            }
    
    //-----------------------ListViewModel ends here---------------------------------//
            
             var tableDataArray = [{id: 10, name: 'Dan Raphael', department: 'Research', salary: 150000, job: 'Director', url: '../../common/images/nBox/16.png'},
                            {id: 11, name: 'David Young', department: 'Marketing', salary: 120000, job: 'Marketing Manager', url: '../../common/images/nBox/18.png'},
                            {id: 12, name: 'Julia Chen', department: 'Marketing', salary: 120000, job: 'Marketing Manager', url: '../../common/images/nBox/7.png'},
                            {id: 13, name: 'Jon Wu', department: 'Research', salary: 120000, job: 'Product Manager', url: '../../common/images/nBox/15.png'},
                            {id: 14, name: 'Kelly Sullivan', department: 'Marketing', salary: 150000, job: 'Director', url: '../../common/images/nBox/3.png'},
                            {id: 15, name: 'Laura Bissot', department: 'Accounts', salary: 80000, job: 'Accountant', url: '../../common/images/nBox/4.png'},
                            {id: 16, name: 'Simon Austin', department: 'Research', salary: 100000, job: 'Developer', url: '../../common/images/nBox/17.png'}];
      
                  var listviewDataArray = [{id: 0, name: 'Amy Bartlet', department: 'Accounts', salary: 200000, job: 'Vice President', url: '../../common/images/nBox/1.png'},
                               {id: 1, name: 'Andy Jones', department: 'Accounts', salary: 150000, job: 'Director', url: '../../common/images/nBox/10.png'},
                               {id: 2, name: 'Andrew Bugsy', department: 'Research', salary: 100000, job: 'Individual Contributor', url: '../../common/images/nBox/11.png'},
                               {id: 3, name: 'Annett Barnes', department: 'Research', salary: 100000, job: 'Individual Contributor', url: '../../common/images/nBox/2.png'},
                               {id: 4, name: 'Bob Jones', department: 'Sales', salary: 75000, job: 'Salesman', url: '../../common/images/nBox/12.png'},
                               {id: 5, name: 'Bart Buckler', department: 'Accounts', salary: 75000, job: 'Purchasing', url: '../../common/images/nBox/13.png'},
                               {id: 6, name: 'Bobby Fisher', department: 'Research', salary: 100000, job: 'Individual Contributor', url: '../../common/images/nBox/14.png'}];
                
                
                
    
            function MasterVM(){
                var master={};
                master.listviewDnDModel=new ListViewDnDModel(listviewDataArray);
                master.tableDndDodel= new TableViewDnDModel(tableDataArray);
                return [master.listviewDnDModel,master.tableDndDodel];
            }
            
    
    
           return MasterVM;
        });


