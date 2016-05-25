define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'tableBaseVM', 'ojs/ojknockout',
        'ojs/ojcomponents', 'ojs/ojtable', 'ojs/ojpagingtabledatasource', 'ojs/ojcollectiontabledatasource','ojs/ojmodel'],
function(oj, ko, $, bku, tableBaseVM)
{
    function TablePsrModel(){
        self = this;
        var bk = new bku();
        tableBaseVM.call(this);
        
        self.module("TablePsrModel");
        self.version(1.0);
        self.datasource(self.baseDataSource());
        self.datasourceDescr(self.baseDescr);
        self.width = ko.observable("auto");
        self.height = ko.observable("auto");
        self.dimList = ko.observableArray(["auto","200px"]);
        self.styleClass = ko.observable("croppedTable");
        self.nothingChecked = ko.observable(true);
        self.ready = ko.observable(true);
        

        self.goData = function(data, descr) {
            bk.msgLog("Switching to " + descr);
            self.datasource(data);  
            self.datasourceDescr(descr);
            self.refresh(self.hasPaging());
        }
                
        
        self.goMockCollection = function() {
            self.goData(self.mockDatasource(),self.mockDescr);
        }

        self.goPagingMockCollection = function() {
            self.goData(self.mockPaging(),self.mockPagingDescr);
        }

        self.goBaseDataSource = function() {
            self.goData(self.baseDataSource(),self.baseDescr);
        }

        self.goBasePaging = function() {
            self.goData(self.basePaging,self.basePagingDescr);
        }

        self.goObsDataSource = function() {
            self.goData(self.obsDataSource(),self.obsDescr);
        }

        self.goObsPaging = function() {
            self.goData(self.obsPaging,self.obsPagingDescr);
        }
        
        // Do all routine, can switch to this eventually...
/*        self.changeRows = function(data, event, changeType, num) {
            var isPagingTableDataSource = self.datasource() instanceof oj.PagingTableDataSource;
            var wrappedDataSource = self.datasource();
            if (isPagingTableDataSource) {
                bk.msgLog("isPagingTableDataSource");            
                wrappedDataSource = self.datasource().getWrappedDataSource();
            }
            if (wrappedDataSource == self.obsDataSource()) {
                bk.msgLog(changeType + " obsArray");
                if (changeType == "add") {
                        self.addObsDept(num);                
                } else if (changeType == "delete") {
                    if (num == undefined) {
                        self.deleteObsDept(data,event);
                    } else {
                        self.removeMultipleObsDept(num);  
                    }
                } else if (changeType == "modify") {
                    if (num == undefined) {
                        self.modifyObsDept(data, event);                
                    } else {
                        self.modifyMultipleObsDept(data, event,num);
                    }
               }
            } else if (wrappedDataSource instanceof oj.CollectionTableDataSource) {
                bk.msgLog(changeType + " mock Collection");
                if (changeType == "add") {
                    if (num == undefined) {                    
                        self.addMockDepartment();
                    } else {
                        self.addBulkMockDepartment(num);
                    }       
                } else if (changeType == "delete") {
                    if (num == undefined) {
                        self.deleteMockDepartment(data, event);                
                    } else {
                        self.deleteMultipleMockDepartment(data, event, num);
                    }
                } else if (changeType == "modify") {
                    if (num == undefined) {
                        self.modifyMockDepartment(data, event);
                    } else {
                        self.modifyMultipleMockDepartment(data, event,num);
                    }
               }
            } else if (wrappedDataSource == self.baseDataSource()) {                
                bk.msgLog(changeType + " array");
                if (changeType == "add") {
                    if (num == undefined) {
                        self.addBaseDept(data,event);
                    } else {
			self.addMultipleBaseDept(num);
                    }
                } else if (changeType == "delete") {
                    if (num == undefined) {
                        self.deleteBaseDept(data,event);
                    } else {
                        self.deleteMulipleBaseDept(data,event,num);
                    }
                } else if (changeType == "modify") {
                    if (num == undefined) {
                        self.modifyBaseDept(data,event);
                    } else {
                        self.modifyMultipleBaseDept(data,event,200);
                    }
               }
            } else {
                bk.msgLog("Unknown datasource type, not adding");
                return;                    
            }
        }
*/


                
        self.addRow = function(data, event) {
            var isPagingTableDataSource = self.datasource() instanceof oj.PagingTableDataSource;
            var wrappedDataSource = self.datasource();
            if (isPagingTableDataSource) {
                bk.msgLog("isPagingTableDataSource");            
                wrappedDataSource = self.datasource().getWrappedDataSource();
            }
            bk.msgLog("wrappedDataSource = " + wrappedDataSource);
            if (wrappedDataSource == self.obsDataSource()) {
                bk.msgLog("Adding to obsArray");
                self.addObsDept();
            } else if (wrappedDataSource instanceof oj.CollectionTableDataSource) {
                bk.msgLog("Adding to mock Collection.");
                self.addMockDepartment();
            } else if (wrappedDataSource == self.baseDataSource()) {                
                bk.msgLog("Adding  to array");
                self.addBaseDept(data,event);
            } else {
                bk.msgLog("Unknown datasource type, not adding");
                return;                    
            }
        }

        self.add200Rows = function(data, event) {
            var isPagingTableDataSource = self.datasource() instanceof oj.PagingTableDataSource;
            var wrappedDataSource = self.datasource();
            if (isPagingTableDataSource) {
                bk.msgLog("isPagingTableDataSource");            
                wrappedDataSource = self.datasource().getWrappedDataSource();
            }
            if (wrappedDataSource == self.obsDataSource()) {
                bk.msgLog("Adding 200 rows to obsArray");
                self.addObsDept(200);
            } else if (wrappedDataSource instanceof oj.CollectionTableDataSource) {
                bk.msgLog("Adding 200 rows to mock Collection");
                self.addBulkMockDepartment(200);
            } else if (wrappedDataSource == self.baseDataSource()) {                
                bk.msgLog("Adding 200 rows to array");
                self.addMultipleBaseDept(200);
            } else {
                bk.msgLog("Unknown datasource type, not adding");
                return;                    
            }
        }
        
        
        self.delete200Rows = function(data, event) {
            var isPagingTableDataSource = self.datasource() instanceof oj.PagingTableDataSource;
            var wrappedDataSource = self.datasource();
            if (isPagingTableDataSource) {
                bk.msgLog("isPagingTableDataSource");            
                wrappedDataSource = self.datasource().getWrappedDataSource();
            }
            if (wrappedDataSource == self.obsDataSource()) {
                bk.msgLog("Deleting 200 rows from obsArray");
                self.removeMultipleObsDept(200);
            } else if (wrappedDataSource instanceof oj.CollectionTableDataSource) {
                bk.msgLog("Deleting 200 rows from mock Collection");
                self.deleteMultipleMockDepartment(data, event, 200);
            } else if (wrappedDataSource == self.baseDataSource()) {                
                bk.msgLog("Deleting 200 rows from array");
                self.deleteMulipleBaseDept(data,event,200);
            } else {
                bk.msgLog("Unknown datasource type, not deleting");
                return;                    
            }
        }

        self.deleteRows = function(data, event) {
            // bk.msgLog("adding new row");
            var isPagingTableDataSource = self.datasource() instanceof oj.PagingTableDataSource;
            var wrappedDataSource = self.datasource();
            if (isPagingTableDataSource) {
                bk.msgLog("isPagingTableDataSource");            
                wrappedDataSource = self.datasource().getWrappedDataSource();
            }
            if (wrappedDataSource == self.obsDataSource()) {
                bk.msgLog("Deleting from obsArray");
                self.deleteObsDept(data,event);
            } else if (wrappedDataSource instanceof oj.CollectionTableDataSource) {
                bk.msgLog("Deleting from mock Collection");
                self.deleteMockDepartment(data, event);
            } else if (wrappedDataSource == self.baseDataSource()) {                
                bk.msgLog("Deleting from array");
                self.deleteBaseDept(data,event);
            } else {
                bk.msgLog("Unknown datasource type, not deleting");
                return;                    
            }
        }

        
        self.modifyRows = function(data, event) {
            var isPagingTableDataSource = self.datasource() instanceof oj.PagingTableDataSource;
            var wrappedDataSource = self.datasource();
            if (isPagingTableDataSource) {
                bk.msgLog("isPagingTableDataSource");            
                wrappedDataSource = self.datasource().getWrappedDataSource();
            }
            if (wrappedDataSource == self.obsDataSource()) {
                bk.msgLog("Modifying obsPaging rows");
                self.modifyObsDept(data, event);
            } else if (wrappedDataSource instanceof oj.CollectionTableDataSource) {
                bk.msgLog("Modifying mockPaging rows");
                self.modifyMockDepartment(data, event);
            } else if (wrappedDataSource == self.baseDataSource()) {                
                bk.msgLog("Modifying basePaging rows");
                self.modifyBaseDept(data,event);
            } else {
                bk.msgLog("Unknown datasource type, not modifying");
                return;
            }
        }

        self.modify200Rows = function(data, event) {
            var isPagingTableDataSource = self.datasource() instanceof oj.PagingTableDataSource;
            var wrappedDataSource = self.datasource();
            if (isPagingTableDataSource) {
                bk.msgLog("isPagingTableDataSource");            
                wrappedDataSource = self.datasource().getWrappedDataSource();
            }
            if (wrappedDataSource == self.obsDataSource()) {
                bk.msgLog("Modifying 200 obsPaging rows");
                self.modifyMultipleObsDept(data, event,200);  
            } else if (wrappedDataSource instanceof oj.CollectionTableDataSource) {
                bk.msgLog("Modifying 200 mockPaging rows");
                self.modifyMultipleMockDepartment(data, event,200);  
            } else if (wrappedDataSource == self.baseDataSource()) {                
                bk.msgLog("Modifying 200 basePaging rows");
                self.modifyMultipleBaseDept(data,event,200);

            } else {
                bk.msgLog("Unknown datasource type, not modifying");
                return;
            }
        }

        
        self.enableDeleteModify = function(uncheckAll) {
            if (uncheckAll == true) {
                self.uncheckAll();
            }
            if (!$('input[type=checkbox]:checked').length) {                
                self.nothingChecked(true);
            } else {
                self.nothingChecked(false);
            }
            return true;
        }
        
        self.deleteMockDepartment = function(data, event) {
            var deptIds = [];
            deptIds = self.findDeptIds();
            var collection = data.mockDeptCol();
            deptIds.forEach(function(value, index, arr) {
                collection.get(parseInt(value)).then(function(model) {
                     collection.remove(model);
                     model.destroy({wait:true});
                    });                
                });
                self.enableDeleteModify();
            }

        self.deleteMultipleMockDepartment = function(data, event, num) {
            var deptIds = [];
            var collection = data.mockDeptCol();
            bk.msgLog("Collection size " + collection.size());
            var size = collection.size();
            if (size < num) {
                bk.msgLog("Too few rows : deleting remaining " + size);
                num = size;
            }
            collection.first(num).then(function(firstArray) {
                bk.msgLog("Deleting first " + firstArray.length);
                var collection = data.mockDeptCol();
                firstArray.forEach(function(value, index, arr) {                
                            // value is model
                            collection.remove(value);
                            value.destroy({wait:true});
                    });
    //            collection.remove(firstArray);
                bk.msgLog("Collection now has " + collection.size());
            });
            self.enableDeleteModify();
            }
            
        self.modifyMultipleMockDepartment = function(data, event, num) {
            var deptIds = [];
            var collection = data.mockDeptCol();
            var size = collection.size();
            if (size < num) {
                bk.msgLog("Too few rows to modify : modifying remaining " + size);
                num = size;
            }
            for(var i = 0; i < size; i++) {
                var model = collection.at(i);
                if (model) {
                        model.set("ManagerId",self.newEmpId++);                        
                    }
            }
            self.enableDeleteModify();
            }

            
            
        self.findDeptIds = function() {
            var selectedIdsArray = [];
            $("input:checkbox").each(function() {
                var $this = $(this);
                if ($this.is(":checked")) {
                    selectedIdsArray.push($this.attr("id"));
                    }
                });
                return selectedIdsArray;
            }

        self.uncheckAll = function() {
            $("input:checkbox").each(function() {
                var $this = $(this);
                if ($this.is(":checked")){
                    $this.prop("checked", false);                    
                    }
                });
            }        
            
        self.deleteObsDept = function(data, event) {
            var deptIds = [];
            deptIds = self.findDeptIds();
            var dsa = data.obsDeptArray; //observableArray inside array ds
            bk.msgLog("dsa = " + ko.toJSON(dsa));
            bk.msgLog("deptIds = " + ko.toJSON(deptIds));
            deptIds.forEach(function(value, index, arr) {
                var ival = parseInt(value);
                bk.msgLog("deptId to delete = " + ival);
                var row = dsa.remove(function(item) { return item.DepartmentId == ival });
                bk.msgLog("row = " + row);
            });
            // bk.msgLog("dsa = " + ko.toJSON(dsa));
            if (dsa.length == 0) {
                self.refresh(self.hasPaging());
            }
            self.enableDeleteModify();
            }
        
        self.deleteBaseDept = function(data, event) {
            var deptIds = [];
            deptIds = self.findDeptIds();
            // data is the whole model here, ie self
            // bk.msgLog("data = " + ko.toJSON(data));
            var dsp = data.basePaging;
            var ds = dsp.getWrappedDataSource(); // should be same as self.baseDataSource
            deptIds.forEach(function(value, index, arr) {
                var ival = parseInt(value);
                bk.msgLog("deptId to delete = " + ival);
                ds.get(ival).then(function(rowArray) {
                    var row = rowArray['data'];
                    var key = rowArray['key'];
                    var index = rowArray['index'];
                    bk.msgLog("ds.get(" + ival + ")  = " + row);
                    if (row != null) {
                        ds.remove(row);
                        }                                                
                    });
                });
            
            if (ds.totalSize() == 0) {
                self.refresh(self.hasPaging());
            }
            self.enableDeleteModify();
            }
            

            
        self.deleteMulipleBaseDept = function(data, event, num) {
            var rowsDone = 0;
            var dsp = data.basePaging;
            var ds = dsp.getWrappedDataSource(); // should be same as self.baseDataSource
            var size = ds.totalSize();
            if (num > size) {
                bk.msgLog("Not enough rows, deleting remaining " + size);
                num = size;
            } 
            for(var i = 0; i < num ; i ++){
                ds.at(i).then(function(rowArray) {
                    var row = rowArray['data'];                    
                    if (row != null) {
                        ds.remove(row);                                
                        rowsDone++;
                        }                               
                    });
                }                        

            // should check to see rowsDone == size and loop, but 2 sec wait is good enough
            setTimeout(function(){
                bk.msgLog("Deleted " + rowsDone + " rows");
                self.refresh(true);
                self.enableDeleteModify();
                }, 2000);
        
            }

                        
        self.addBaseDept = function(data,event) {
            var newRow = {DepartmentId: self.newEmpId++ , DepartmentName: 'BB', LocationId: 200, ManagerId: 300};            
            var ds = data.baseDataSource();
            bk.msgLog("adding row to base = " + ko.toJSON(newRow));
            ds.add(newRow);            
        }

        self.addMultipleBaseDept = function(num) {
            oj.Logger.info("Adding " + num + " rows");
            var newRows = [];
            for (var i = 0; i < num; i++) {
                var newRow = {DepartmentId: self.newEmpId++ , DepartmentName: 'BB', LocationId: 200, ManagerId: 300};            
                newRows.push(newRow);            
            }
            var ds = self.baseDataSource();
            ds.add(newRows);            
        }

        
        self.modifyMockDepartment = function(data, event) {
            var deptIds = [];
            deptIds = self.findDeptIds();
            var collection = data.mockDeptCol();
            deptIds.forEach(function(value, index, arr) {
                collection.get(parseInt(value)).then(function(model) {
                        model.set("ManagerId",self.newEmpId++);                        
                        });                    
                });
                self.enableDeleteModify(true);
            }

        self.modifyObsDept = function(data, event) {        
            // You have to modify both obsArray as well as Table
            var deptIds = [];
            var rows = [];
            deptIds = self.findDeptIds();
            var ds = data.obsDataSource();
            var dsa = data.obsDeptArray; //observableArray inside array ds
            deptIds.forEach(function(value, index, arr) {
                var ival = parseInt(value);
                bk.msgLog("deptId to modify = " + ival);                
                ds.get(ival).then(function(rowArray) {
                    var row = rowArray['data'];
                    if (row != null) {
                        row.ManagerId = row.ManagerId + 1;
                        rows.push(row);
                        }                                
                    });
                });
            ds.change(rows);
            bk.msgLog("Sending out notification");
            dsa.valueHasMutated();
            bk.msgLog("dsa = " + ko.toJSON(dsa));
            self.enableDeleteModify(true)
            self.refresh(false);  // no need to update paging...could speed this up with only row refresh
            }

    self.modifyMultipleObsDept = function(data,event,num) {
            var dsa = self.obsDeptArray();
            var size = dsa.length;
            if (size < num) {
               bk.msgLog("Not enough rows, modifying remaining " + size);
               num = size;
                }
            for(var i = 0; i < num ; i ++ ) {
              var item = dsa[i];
              // bk.msgLog("item.DepartmentId = " + item.DepartmentId);
              item.ManagerId = 1 + item.ManagerId;              
              }
            bk.msgLog("Sending out notification");
            self.obsDeptArray.valueHasMutated();
            bk.msgLog("dsa = " + ko.toJSON(dsa));
            self.enableDeleteModify(true);
            self.refresh(false); // no need to update paging control
    }

    self.modifyMultipleBaseDept = function(data,event,num) {
    
            var ds = data.baseDataSource(); // ArrayTableDataSource
            var size = ds.totalSize();
            var rows = [];
            if (num >= size) {
                bk.msgLog("Not enough rows : modifying remaining " + size);
                num = size;
            } 
            for(var i = 0; i < num ; i ++){
                ds.at(i).then(function(rowArray) {
                    var row = rowArray['data'];
                    if (row != null) {
                        row.ManagerId = row.ManagerId + 1;
                        rows.push(row);
                        }                                
                    });
            }            
            ds.change(rows);
            self.refresh(self.hasPaging());
            self.enableDeleteModify();
    }

            
    self.modifyBaseDept = function(data, event) {
           var deptIds = [];
           var rows = [];
            deptIds = self.findDeptIds();
            var ds = data.baseDataSource(); // ArrayTableDataSource            
            deptIds.forEach(function(value, index, arr) {
                var ival = parseInt(value);
                ds.get(ival).then(function(rowArray) {
                    var row = rowArray['data'];
                    if (row != null) {
                        row.ManagerId = row.ManagerId + 1;
                        rows.push(row);
                        }                
                    });        
                });
            ds.change(rows);
            // refresh table, since without knockout
            self.refresh(self.hasPaging());
            self.enableDeleteModify(true);
            }

        self.toggleDisabled = function() {
            bk.msgLog("disabled = " + self.disabled());        
            bk.cycle("disabled",bk.boolVals,self.disabled);        
            self.setAllOption("disabled", self.disabled());      
        }
                            
        
    oj.Logger.info("TablePsrModel created " + self.ojName());
    }
 
    TablePsrModel.prototype = Object.create(tableBaseVM.prototype);
    TablePsrModel.prototype.constructor = TablePsrModel;

    return TablePsrModel;
});