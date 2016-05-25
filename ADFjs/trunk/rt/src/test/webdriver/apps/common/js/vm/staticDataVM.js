define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'ojs/ojknockout', 'ojs/ojcomponents', 'ojs/ojtable', 'ojs/ojarraytabledatasource', 'ojs/ojpagingtabledatasource'],
function(oj, ko, $, bku)
{   
  function staticDataModel()
  {
    self = this;
    var bk = new bku();
    // self.datasource = ko.observable();

    var emptyDeptArray = [];
    self.emptyDataSource = ko.observable(new oj.ArrayTableDataSource(emptyDeptArray, {idAttribute: 'DepartmentId'}));
    self.emptyDescr = "ArrayTableDataSource empty";
    
    var baseDeptArray = [{DepartmentId: 15, DepartmentName: 'QA', LocationId: 200, ManagerId: 300},
        {DepartmentId: 25, DepartmentName: 'Dev', LocationId: 200, ManagerId: 300},
        {DepartmentId: 35, DepartmentName: 'Marketing', LocationId: 200, ManagerId: 300},
        {DepartmentId: 45, DepartmentName: 'Shipping', LocationId: 200, ManagerId: 300}];
    self.baseDataSource = ko.observable(new oj.ArrayTableDataSource(baseDeptArray, {idAttribute: 'DepartmentId'}));
    self.baseDescr = "ArrayTableDataSource base";
    var mediumDeptArray = [{DepartmentId: 1001, DepartmentName: 'ADFPM 1001 neverending', LocationId: 200, ManagerId: 300},
        {DepartmentId: 556, DepartmentName: 'BB', LocationId: 200, ManagerId: 300},
        {DepartmentId: 10, DepartmentName: 'Administration', LocationId: 200, ManagerId: 300},
        {DepartmentId: 20, DepartmentName: 'Marketing', LocationId: 200, ManagerId: 300},
        {DepartmentId: 30, DepartmentName: 'Purchasing', LocationId: 200, ManagerId: 300},
        {DepartmentId: 40, DepartmentName: 'Human Resources1', LocationId: 200, ManagerId: 300},
        {DepartmentId: 50, DepartmentName: 'Administration2', LocationId: 200, ManagerId: 300},
        {DepartmentId: 60, DepartmentName: 'Marketing3', LocationId: 200, ManagerId: 300},
        {DepartmentId: 70, DepartmentName: 'Purchasing4', LocationId: 200, ManagerId: 300},
        {DepartmentId: 80, DepartmentName: 'Human Resources5', LocationId: 200, ManagerId: 300},
        {DepartmentId: 90, DepartmentName: 'Human Resources11', LocationId: 200, ManagerId: 300},
        {DepartmentId: 100, DepartmentName: 'Administration12', LocationId: 200, ManagerId: 300},
        {DepartmentId: 110, DepartmentName: 'Marketing13', LocationId: 200, ManagerId: 300},
        {DepartmentId: 120, DepartmentName: 'Purchasing14', LocationId: 200, ManagerId: 300},
        {DepartmentId: 130, DepartmentName: 'Human Resources15', LocationId: 200, ManagerId: 300}];
    self.mediumDataSource = ko.observable(new oj.ArrayTableDataSource(mediumDeptArray, {idAttribute: 'DepartmentId'}));
    self.mediumDescr = "ArrayTableDataSource medium";
    var largeDeptArray = [{DepartmentId: 10015, DepartmentName: 'ADFPM 1001 neverending', LocationId: 200, ManagerId: 300},
        {DepartmentId: 556, DepartmentName: 'BB', LocationId: 200, ManagerId: 300},
        {DepartmentId: 10, DepartmentName: 'Administration', LocationId: 200, ManagerId: 300},
        {DepartmentId: 20, DepartmentName: 'Marketing', LocationId: 200, ManagerId: 300},
        {DepartmentId: 30, DepartmentName: 'Purchasing', LocationId: 200, ManagerId: 300},
        {DepartmentId: 40, DepartmentName: 'Human Resources1', LocationId: 200, ManagerId: 300},
        {DepartmentId: 50, DepartmentName: 'Administration2', LocationId: 200, ManagerId: 300},
        {DepartmentId: 60, DepartmentName: 'Marketing3', LocationId: 200, ManagerId: 300},
        {DepartmentId: 70, DepartmentName: 'Purchasing4', LocationId: 200, ManagerId: 300},
        {DepartmentId: 80, DepartmentName: 'Human Resources5', LocationId: 200, ManagerId: 300},
        {DepartmentId: 90, DepartmentName: 'Human Resources11', LocationId: 200, ManagerId: 300},
        {DepartmentId: 100, DepartmentName: 'Administration12', LocationId: 200, ManagerId: 300},
        {DepartmentId: 110, DepartmentName: 'Marketing13', LocationId: 200, ManagerId: 300},
        {DepartmentId: 120, DepartmentName: 'Purchasing14', LocationId: 200, ManagerId: 300},
        {DepartmentId: 130, DepartmentName: 'Human Resources15', LocationId: 200, ManagerId: 300},
        {DepartmentId: 1001, DepartmentName: 'ADFPM 1001 neverending', LocationId: 200, ManagerId: 300},
        {DepartmentId: 55611, DepartmentName: 'BB', LocationId: 200, ManagerId: 300},
        {DepartmentId: 1011, DepartmentName: 'Administration', LocationId: 200, ManagerId: 300},
        {DepartmentId: 2011, DepartmentName: 'Marketing', LocationId: 200, ManagerId: 300},
        {DepartmentId: 3011, DepartmentName: 'Purchasing', LocationId: 200, ManagerId: 300},
        {DepartmentId: 4011, DepartmentName: 'Human Resources1', LocationId: 200, ManagerId: 300},
        {DepartmentId: 5011, DepartmentName: 'Administration2', LocationId: 200, ManagerId: 300},
        {DepartmentId: 6011, DepartmentName: 'Marketing3', LocationId: 200, ManagerId: 300},
        {DepartmentId: 7011, DepartmentName: 'Purchasing4', LocationId: 200, ManagerId: 300},
        {DepartmentId: 8011, DepartmentName: 'Human Resources5', LocationId: 200, ManagerId: 300},
        {DepartmentId: 9011, DepartmentName: 'Human Resources11', LocationId: 200, ManagerId: 300},
        {DepartmentId: 10011, DepartmentName: 'Administration12', LocationId: 200, ManagerId: 300},
        {DepartmentId: 11011, DepartmentName: 'Marketing13', LocationId: 200, ManagerId: 300},
        {DepartmentId: 12011, DepartmentName: 'Purchasing14', LocationId: 200, ManagerId: 300},
        {DepartmentId: 13011, DepartmentName: 'Human Resources15', LocationId: 200, ManagerId: 300},
        {DepartmentId: 100111, DepartmentName: 'ADFPM 1001 neverending', LocationId: 200, ManagerId: 300},
        {DepartmentId: 55622, DepartmentName: 'BB', LocationId: 200, ManagerId: 300},
        {DepartmentId: 1022, DepartmentName: 'Administration', LocationId: 200, ManagerId: 300},
        {DepartmentId: 2022, DepartmentName: 'Marketing', LocationId: 200, ManagerId: 300},
        {DepartmentId: 3022, DepartmentName: 'Purchasing', LocationId: 200, ManagerId: 300},
        {DepartmentId: 4022, DepartmentName: 'Human Resources1', LocationId: 200, ManagerId: 300},
        {DepartmentId: 5022, DepartmentName: 'Administration2', LocationId: 200, ManagerId: 300},
        {DepartmentId: 6022, DepartmentName: 'Marketing3', LocationId: 200, ManagerId: 300},
        {DepartmentId: 7022, DepartmentName: 'Purchasing4', LocationId: 200, ManagerId: 300},
        {DepartmentId: 8022, DepartmentName: 'Human Resources5', LocationId: 200, ManagerId: 300},
        {DepartmentId: 9022, DepartmentName: 'Human Resources11', LocationId: 200, ManagerId: 300},
        {DepartmentId: 10022, DepartmentName: 'Administration12', LocationId: 200, ManagerId: 300},
        {DepartmentId: 11022, DepartmentName: 'Marketing13', LocationId: 200, ManagerId: 300},
        {DepartmentId: 12022, DepartmentName: 'Purchasing14', LocationId: 200, ManagerId: 300},
        {DepartmentId: 13022, DepartmentName: 'Human Resources15', LocationId: 200, ManagerId: 300}];
    self.largeDataSource = ko.observable(new oj.ArrayTableDataSource(largeDeptArray, {idAttribute: 'DepartmentId'}));
    self.largeDescr = "ArrayTableDataSource large";
    var countDeptArray = [{DepartmentId: 1001, DepartmentName: 'ADFPM 1001 neverending', LocationId: 200, ManagerId: 300, EmployeeCount: 20},
        {DepartmentId: 556, DepartmentName: 'BB', LocationId: 200, ManagerId: 300, EmployeeCount: 10},
        {DepartmentId: 10, DepartmentName: 'Administration', LocationId: 200, ManagerId: 300, EmployeeCount: 30},
        {DepartmentId: 20, DepartmentName: 'Marketing', LocationId: 200, ManagerId: 300, EmployeeCount: 20},
        {DepartmentId: 30, DepartmentName: 'Purchasing', LocationId: 200, ManagerId: 300, EmployeeCount: 50},
        {DepartmentId: 40, DepartmentName: 'Human Resources1', LocationId: 200, ManagerId: 300, EmployeeCount: 200},
        {DepartmentId: 50, DepartmentName: 'Administration2', LocationId: 200, ManagerId: 300, EmployeeCount: 20},
        {DepartmentId: 60, DepartmentName: 'Marketing3', LocationId: 200, ManagerId: 300, EmployeeCount: 30},
        {DepartmentId: 70, DepartmentName: 'Purchasing4', LocationId: 200, ManagerId: 300, EmployeeCount: 20},
        {DepartmentId: 80, DepartmentName: 'Human Resources5', LocationId: 200, ManagerId: 300, EmployeeCount: 20},
        {DepartmentId: 90, DepartmentName: 'Human Resources11', LocationId: 200, ManagerId: 300, EmployeeCount: 10},
        {DepartmentId: 100, DepartmentName: 'Administration12', LocationId: 200, ManagerId: 300, EmployeeCount: 20},
        {DepartmentId: 110, DepartmentName: 'Marketing13', LocationId: 200, ManagerId: 300, EmployeeCount: 20},
        {DepartmentId: 120, DepartmentName: 'Purchasing14', LocationId: 200, ManagerId: 300, EmployeeCount: 20},
        {DepartmentId: 130, DepartmentName: 'Human Resources15', LocationId: 200, ManagerId: 300, EmployeeCount: 20}];
    self.countDataSource = ko.observable(new oj.ArrayTableDataSource(countDeptArray, {idAttribute: 'DepartmentId'}));
    self.countDescr = "ArrayTableDataSource with count column";

    self.emptyPaging = new oj.PagingTableDataSource(self.emptyDataSource());        
    self.basePaging = new oj.PagingTableDataSource(self.baseDataSource());
    self.mediumPaging = new oj.PagingTableDataSource(self.mediumDataSource());
    self.largePaging = new oj.PagingTableDataSource(self.largeDataSource());
    self.emptyPagingDescr = "PagingTableDataSource(" + self.emptyDescr + ")";
    self.basePagingDescr = "PagingTableDataSource(" + self.baseDescr + ")";
    self.mediumPagingDescr = "PagingTableDataSource(" + self.mediumDescr + ")";
    self.largePagingDescr = "PagingTableDataSource(" + self.largeDescr + ")";
    self.countPagingDescr = "PagingTableDataSource(" + self.countDescr + ")";

    self.pagingDatasourceList = ko.observableArray([self.basePaging,self.largePaging,self.emptyPaging]);
    self.datasourceList = ko.observableArray([self.baseDataSource,self.largeDataSource, self.emptyDataSource]);
    self.datasourceDescrList = ko.observableArray([self.baseDescr,self.largeDescr, self.emptyDescr]);
    self.pagingDatasourceDescrList = ko.observableArray([self.basePagingDescr,self.largePagingDescr, self.emptyPagingDescr]);
    
    oj.Logger.info("staticDataModel created");
  }

  return staticDataModel;  
});	