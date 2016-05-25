require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents', 'ojs/ojtable', 'ojs/ojdatagrid', 'ojs/ojcollectiondatagriddatasource','ojs/ojarraytabledatasource', 'ojs/ojpagingtabledatasource','ojs/ojpagingdatagriddatasource', 'ojs/ojcollectionpagingdatasource', 'ojs/ojcollectiontabledatasource'],
        function(oj, ko, $)
        {
            var serverLocation = "http://10.88.188.122:8001/";     
            
            oj.Logger.option("level", oj.Logger.LEVEL_INFO);
            function departmentsVM() {
                var self = this;
                self.serviceURL = serverLocation + 'AdfBCApp-RESTWebService-context-root/rest/main/DeptView1';
                self.Employees = ko.observableArray([]);
                self.showDetails = ko.observable(false);
                self.noEmployees = ko.observable(false);
                self.updateDeptName = ko.observable();
                self.somethingChecked = ko.observable(false);
                self.refreshMe = ko.observable(true);
                self.validationMessage = ko.observable("");
                self.deptIdToEdit = ko.observable("");
                self.Department = oj.Model.extend({
                    urlRoot: serverLocation + "AdfBCApp-RESTWebService-context-root/rest/main/DeptView1",
                    url: function() {
                        var base = serverLocation + 'AdfBCApp-RESTWebService-context-root/rest/main/DeptView1';
                        if (this.isNew())
                            return base;
                        else
                            return base + "/" + this.get(this.idAttribute);
                    },
                    parse: parseDept,
                    parseSave: parseSaveDept,
                    idAttribute: 'Deptno'
                });
                
                //if you add fetchSize: 4,to the collection then it fails. try it out                    
                
                var myDept = new self.Department();
                var DeptCollection = oj.Collection.extend({
                    url: serverLocation + "AdfBCApp-RESTWebService-context-root/rest/main/DeptView1",
                    model: myDept,   
                    modelLimit: -1,
                    parse: parseDepts,
                    comparator: "Deptno"
                });

                self.Departments = ko.observableArray([]);

                self.pagingTableDatasource = ko.observable();
                self.arrayDataGridDatasource = ko.observable();
                self.arrayPagingDatasource = ko.observable();
                self.arrayTableDatasource = ko.observable();
                self.pagingDataGridDatasourceUsingArray  = ko.observable();

                self.DeptCol = ko.observable();
                self.DeptCol(new DeptCollection());
                self.DeptCol().fetch({
                    success: function(collection, response, options) {
                        deptData = collection;
                        self.Departments = oj.KnockoutUtils.map(deptData, self.addComputedObservable, true);
                        console.log("self.Departments()" + self.Departments().length);

                        self.arrayTableDatasource(new oj.ArrayTableDataSource(self.Departments(), {idAttribute: 'Deptno'}));
                        self.pagingTableDatasource(new oj.PagingTableDataSource(new oj.ArrayTableDataSource(self.Departments(), {idAttribute: 'Deptno'})));

                        self.arrayDataGridDatasource(new oj.ArrayDataGridDataSource(self.Departments()));
                     //Supported only on main
                        self.arrayPagingDatasource(new oj.PagingDataGridDataSource(new oj.ArrayDataGridDataSource(self.Departments())));
                        console.log("arrayDS"+ self.arrayPagingDatasource().size())
                        console.log("data" + ko.toJSON(self.arrayDataGridDatasource().getData()[0]));
                        console.log("data" + ko.toJSON(self.arrayDataGridDatasource().getData()[1]));
                        
                        $("datagridArrayPaging").ojDataGrid({"data": self.arrayPagingDatasource()});
                        $("datagridArrayPaging").ojDataGrid("refresh");

                    //    self.pagingDataGridDatasourceUsingArray(new oj.PagingDataGridDataSource(
                       //                                                     self.arrayDataGridDatasource()));
                       
                       
                      //  $("arrayDatagridCollectionDS").ojDataGrid({"data": self.arrayDataGridDatasource()});
                     //   $("arrayDatagridCollectionDS").ojDataGrid("refresh");
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.log('Error in fetch: ' + textStatus);
                    }
                });
                
                   
                
                self.collTableDatasource = ko.observable();
                self.collTableDatasource(new oj.CollectionTableDataSource(self.DeptCol()));                        

                self.collDataGridDatasource = ko.observable();
                self.pagingDataGridDatasourceUsingColl = ko.observable();
                self.collDataGridDatasource(new oj.CollectionDataGridDataSource(new self.DeptCol()));
                self.pagingDataGridDatasourceUsingColl(new oj.PagingDataGridDataSource(self.collDataGridDatasource()));

                //Not used
                self.collPagingDatasource = ko.observable();
                self.collPagingDatasource(new oj.CollectionPagingDataSource(new self.DeptCol()));


                self.addComputedObservable = function(object) {
                    object.Property1 = ko.computed(
                            function() {
                                return object.Deptno() + " " + object.Dname();
                            });
                    object.Property2 = ko.computed(
                            function() {
                                return  object.Dname() + " Hello!";
                            });

                }
                
               




            }


            parseDepts = function(response) {
                return response.items;
            };
            parseDept = function(response) {

                return {Deptno: response['Deptno'], Dname: response['Dname'], Loc: response['Loc']};
            };
            parseSaveDept = function(response) {
                return {items: [{Deptno: response['Deptno'], Dname: response['Dname'], Loc: response['Loc']}]};
            };

            vm = new departmentsVM();
            $(document).ready(function() {

                ko.applyBindings(vm);
                $.ajaxSetup({
                    xhrFields:
                            {
                                withCredentials: true
                            },
                    crossDomain: true
                });
            });
        });


