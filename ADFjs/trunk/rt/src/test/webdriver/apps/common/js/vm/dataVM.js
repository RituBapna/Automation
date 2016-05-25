define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'staticDataVM', 'ojs/ojknockout', 'ojs/ojcomponents', 'ojs/ojtable', 'ojs/ojarraytabledatasource', 'ojs/ojpagingtabledatasource', 'ojs/ojcollectiontabledatasource', 'ojs/ojflattenedtreetabledatasource',
        'ojs/ojrowexpander','mockjax', 'mockrest', 'mockpagingrest'],
function(oj, ko, $, bku, datavm)
{
  
  oj.Logger.option("level", oj.Logger.LEVEL_INFO);
  
  function DataModel()
  {
    self = this;
    var bk = new bku();
    datavm.call(this);
    self.newEmpId = 200;
    self.source = ko.observable("array"); //default source

    // Observables
    self.obsDeptArray = ko.observableArray([{DepartmentId: 15, DepartmentName: 'QA', LocationId: 200, ManagerId: 300},
        {DepartmentId: 25, DepartmentName: 'Dev', LocationId: 200, ManagerId: 300},
        {DepartmentId: 35, DepartmentName: 'Marketing', LocationId: 200, ManagerId: 300},
        {DepartmentId: 45, DepartmentName: 'Shipping', LocationId: 200, ManagerId: 300}]);
    self.obsDataSource = ko.observable(new oj.ArrayTableDataSource(self.obsDeptArray, {idAttribute: 'DepartmentId'}));
    self.obsDescr = "observable of ArrayTableDataSource of observableArray"
    
    var dept1 = ko.observable({DepartmentId: 15, DepartmentName: 'QA', LocationId: 200, ManagerId: 300});
    var dept2 = ko.observable({DepartmentId: 25, DepartmentName: 'Dev', LocationId: 200, ManagerId: 300});
    var dept3 = ko.observable({DepartmentId: 35, DepartmentName: 'Marketing', LocationId: 200, ManagerId: 300});
    var dept4 = ko.observable({DepartmentId: 45, DepartmentName: 'Shipping', LocationId: 200, ManagerId: 300});
    var dept5 = ko.observable({DepartmentId: 55, DepartmentName: 'Sales', LocationId: 200, ManagerId: 300});    
    self.nestedObsDeptArray = ko.observableArray([dept1(),dept2(),dept3(),dept5()]);
    self.nestedObsDataSource = ko.observable(new oj.ArrayTableDataSource(self.nestedObsDeptArray, {idAttribute: 'DepartmentId'}));
    self.nestedObsDescr = "observable of ArrayTableDataSource of observableArray of observables"
    
    self.addObsDept = function (num) {
      if (num == undefined) {
         self.obsDeptArray.push({DepartmentId: self.newEmpId++, DepartmentName: 'Shipping', LocationId: 200, ManagerId: 300});
      } else {
         for(var i = 0; i < num; i++){
           self.obsDeptArray.push({DepartmentId: self.newEmpId++, DepartmentName: 'Shipping', LocationId: 200, ManagerId: 300});          //code
        }
      }
    }
    

    
    self.removeObsDept = function(row) {
      self.obsDeptArray.remove(row);
    }

    self.removeMultipleObsDept = function(num) {
      var size = self.obsDeptArray().length;
      if (size < num) {
        bk.msgLog("Not enough rows, deleting remaining " + size);
        num = size;
      }
      bk.msgLog("Removing last " + num + " rows");
      for(var i = 0; i < num ; i ++ ) {
          self.obsDeptArray.pop();  
      }
    }
        
    
    self.addNestedObsDept = function () {
      self.nestedObsDeptArray.push({DepartmentId: self.newEmpId++, DepartmentName: 'Shipping', LocationId: 200, ManagerId: 300});
    }
          
    self.obsPaging = new oj.PagingTableDataSource(self.obsDataSource());
    self.nestedObsPaging = new oj.PagingTableDataSource(self.nestedObsDataSource());
    self.obsPagingDescr = "PagingTableDataSource(" + self.obsDescr + ")";
    self.nestedObsPagingDescr = "PagingTableDataSource(" + self.nestedObsDescr + ")";

    self.pagingDatasourceList.push(self.obsPaging);
    self.pagingDatasourceList.push(self.nestedObsPaging);
    self.datasourceList.push(self.obsDataSource());
    self.datasourceList.push(self.nestedObsDataSource());
    self.datasourceDescrList.push(self.obsDescr);
    self.pagingDatasourceDescrList.push(self.obsPagingDescr);
    self.datasourceDescrList.push(self.nestedObsDescr);
    self.pagingDatasourceDescrList.push(self.nestedObsPagingDescr);
    
    // Mock object Json Collection using local file
    self.mockDeptCol = ko.observable();
    self.mockDatasource = ko.observable();
    self.mockPaging = ko.observable();
    self.initMockRows = ko.observable(0);
    self.initMockComplete = ko.observable(false);
    self.datasourceList.push(self.mockDatasource);
    self.pagingDatasourceList.push(self.mockPaging);
    self.mockDescr = "mock CollectionTableDataSource local file";
    self.mockPagingDescr = "PagingTableDataSource(" + self.mockDescr + ")";
    self.datasourceDescrList.push(self.mockDescr);
    self.pagingDatasourceDescrList.push(self.mockPagingDescr);
    self.parseDept = function(response) {
        // bk.msgLog("parsing dept: " + response['DepartmentId']);
        return {DepartmentId: response['DepartmentId'],
            DepartmentName: response['DepartmentName'],
            LocationId: response['LocationId'],
            ManagerId: response['ManagerId']};
    };
 
    self.mockServiceURL = 'http://mockrest/stable/rest/Departments';
    self.mockDepartment = oj.Model.extend({
        urlRoot: self.mockServiceURL,
        parse: self.parseDept,
        idAttribute: 'DepartmentId'
    });
 
    self.mockDept = new self.mockDepartment();
    self.mockDeptCollection = oj.Collection.extend({
        url: self.mockServiceURL,
        fetchSize: 20,
        model: self.mockDept
    });
 
    self.mockDeptCol(new self.mockDeptCollection());
    $.ajax({dataType:"json", 
            url:"../../common/js/json/nodepartments.json",
            crossDomain: true,
            success:function (data) {
              new MockRESTServer(data, {id:"DepartmentId",
                 url:/^(http:\/\/mockrest\/stable\/rest\/Departments*)|(menuDivs.html\/)$/i,
                 idUrl:/^(http:\/\/mockrest\/stable\/rest\/Departments*)|(menuDivs.html\/)$/i});
              oj.Logger.info("Success in fetch of mock server");
              self.mockDatasource(new oj.CollectionTableDataSource(self.mockDeptCol()));
	      self.mockPaging(new oj.PagingTableDataSource(self.mockDatasource()));
              if (self.source() == "collection") {
                if (self.hasPaging()) {
                  self.datasource(self.mockPaging());
                } else {
                  self.datasource(self.mockDatasource());
                }
              }
              if (self.initMockRows() > 0) {
                if (self.initMockComplete() == false) {
                  self.addMulipleMockDepartment(self.initMockRows());
                  self.initMockComplete(true);
                }
              }
              oj.Logger.log("mock ajax complete");
                },
            error: function(jqXHR, textStatus, errorThrown) {
                bk.msgLog('Error in fetch: ' + textStatus);
				}
            }); 
        
    self.addMockDepartment = function(formElement, event) {
      oj.Logger.info("Adding mock dept");
      var recordAttrs = { DepartmentId: self.newEmpId++,DepartmentName: "QA",LocationId: "200", ManagerId: "300"};
      self.mockDeptCol().create(recordAttrs, {
            'contentType': 'application/vnd.oracle.adf.resource+json',
          success: function(response) {
            oj.Logger.log('Success in Create mock dept');
            },
          error: function(jqXHR, textStatus, errorThrown) {
            bk.msgLog('Error in Create: ' + textStatus);
            }});
      }
       

    
    // http://stackoverflow.com/questions/10782510/backbone-create-multiple-models-in-collection-serverside
    // backbone doesn't really help with bulk additions, ug - bk
    self.addMulipleMockDepartment = function (num) {
      oj.Logger.info("Adding " + num + " mock dept rows...");
      for (var i = 0 ; i < num ; i++) {
        var recordAttrs = { DepartmentId: self.newEmpId++,DepartmentName: "QA",LocationId: "200", ManagerId: "300"};
        self.mockDeptCol().create(recordAttrs, {
            'contentType': 'application/vnd.oracle.adf.resource+json',
          success: function(response) {
              // bk.msgLog("response = " + ko.toJSON(response) + " " + response.attributes["DepartmentId"] + " " + (self.newEmpId-1));
              if (1 + response.attributes["DepartmentId"] == self.newEmpId) {
                oj.Logger.log("...Finished creating " + num + " mock depts")
              }
            },
          error: function(jqXHR, textStatus, errorThrown) {
            bk.msgLog('Error in Create: ' + textStatus);
            }});
        oj.Logger.log("Added mock row " + i + " with deptid " + recordAttrs.DepartmentId);
        }
      // bk.msgLog("Added " + num + " mock depts");                
    }
    
    // Bob's attempt at faster bulk collection adds
    self.addBulkMockDepartment = function (num) {
        // bk.msgLog("Adding bulk " + num + " mock depts");                
        self.initMockRows(num);
        self.initMockComplete(false);
    $.ajax({dataType:"json", 
            url:"../../common/js/json/departments.json",
            crossDomain: true,
            success:function (data) {
              new MockRESTServer(data, {id:"DepartmentId",
                 url:/^(http:\/\/mockrest\/stable\/rest\/Departments*)|(menuDivs.html\/)$/i,
                 idUrl:/^(http:\/\/mockrest\/stable\/rest\/Departments*)|(menuDivs.html\/)$/i});
              // bk.msgLog("Success in fetch of mock server");
              self.mockDatasource(new oj.CollectionTableDataSource(self.mockDeptCol()));
	      self.mockPaging(new oj.PagingTableDataSource(self.mockDatasource()));
              if (self.initMockRows() > 0) {
                if (self.initMockComplete() == false) {
                  self.addMulipleMockDepartment(self.initMockRows());
                  self.initMockComplete(true);
                }
              }
              // bk.msgLog("mock ajax complete");
                },
            error: function(jqXHR, textStatus, errorThrown) {
                bk.msgLog('Error in fetch: ' + textStatus);
				}
            });
      // bk.msgLog("Added bulk " + num + " mock depts");                
    }
    
    // Mock2 object Json Collection using local file
    /* self.mock2Server;
    self.mock2Model;
    self.mock2Collection;
    self.mock2Datasource;
    self.mock2PagingDatasource; */
    	
    $.ajax({dataType:"json", 
            url:"../../common/js/json/departments.json",
            crossDomain: true,
            success:function (data) {
              self.mock2Server = new MockPagingRESTServer(data, {collProp: "Departments", id:"DepartmentId" });
              oj.Logger.info("Success in fetch of mock2 paging server");
              self.mock2Model = oj.Model.extend({idAttribute:"DepartmentId"});
	      self.mock2Collection = oj.Collection.extend({
                    url: self.mock2Server.getURL(), fetchSize:10, model:self.mock2Model});
              self.mock2CollectionInstance = new self.mock2Collection;
              self.mock2Datasource = new oj.CollectionTableDataSource(self.mock2CollectionInstance);
              self.mock2PagingDatasource = new oj.PagingTableDataSource(self.mock2Datasource);              
              self.pagingDatasourceList.push(self.mock2PagingDatasource);                  
              self.mock2Descr = "mock2 CollectionTableDataSource local file";
              self.mock2PagingDescr = "PagingTableDataSource(" + self.mock2Descr + ")";
              self.pagingDatasourceDescrList.push(self.mock2PagingDescr);
              oj.Logger.log("mock2 ajax complete");
                },
            error: function(jqXHR, textStatus, errorThrown) {
                bk.msgLog('Error in mock2 fetch: ' + textStatus);
                }
            }); 
    
    
    // Local Json from file
    self.localJsonDataSource = ko.observable();
    self.localJsonPaging = ko.observable();
    self.datasourceList.push(self.localJsonDataSource);
    self.pagingDatasourceList.push(self.localJsonPaging);
    self.localJsonDescr = "FlattenedTreeTableDataSource local file data size 4"
    self.localJsonPagingDescr = "PagingTableDataSource(" + self.localJsonDescr + ")";
    self.datasourceDescrList.push(self.localJsonDescr);
    self.pagingDatasourceDescrList.push(self.localJsonPagingDescr);

    $.getJSON("../../common/js/json/deptData.json", function(data) 
      {
        var jsonOptions = [];
        self.localJsonDataSource(new oj.FlattenedTreeTableDataSource(new oj.FlattenedTreeDataSource(new oj.JsonTreeDataSource(data), jsonOptions)));
        self.localJsonPaging(new oj.PagingTableDataSource(self.localJsonDataSource()));
      });

      
    self.initManyRows = function(num) {
       // There are 4 initial rows for some ds, so adjust adds
        self.addMultipleBaseDept(num-4);
        self.addObsDept(num-4);                
        self.initMockRows(num);  // async, done eventually
    }
    
    oj.Logger.info("DataModel created");
  }

  DataModel.prototype = Object.create(datavm.prototype);
  DataModel.prototype.constructor = DataModel;

  return DataModel;  
});	