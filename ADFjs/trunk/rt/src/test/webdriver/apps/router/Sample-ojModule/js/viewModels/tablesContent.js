define(['ojs/ojcore',
   'knockout',
   'jquery',
   'ojs/ojknockout',
   'ojs/ojrouter',
   'ojs/ojbutton',
   'ojs/ojmodel',
   'ojs/ojarraytabledatasource',
   'ojs/ojcollectiontabledatasource',
   'promise',
   'ojs/ojinputtext',
   'ojs/ojtable',
   'ojs/ojnavigationlist'
], function(oj, ko, $) {

   var useREST = false, // Change to false to enable RESTful datasources.
       serviceURL = 'http://slc01qrl.us.oracle.com:7801/stable/rest/latest',
       DeptDataTable,
       EmpDataTable;

   /**
    * DataTable object for static content.
    * The contract is to provide 2 methods: getColumns and getDataSource.
    */
   function DataTable(columnsArray, dataArray, idAttribute) {
      // Private variable part of the closure
      var dataSource = new oj.ArrayTableDataSource(dataArray, {idAttribute: idAttribute}),
          recordStates = {};

      return {
         initialize: function() {
            dataSource.fetch({'silent' : true}).then(function(result) {
               for (var i = 0; i < result.data.length; i++) {
                  var row = result.data[i];
                  recordStates[row.id.toString()] = new oj.RouterState(row.id.toString(),
                                                                      { value: row } );
               }
            });
         },

         getColumns: function() {
            return columnsArray;
         },

         getDataSource: function() {
            return dataSource;
         },

         getRecordStates: function() {
            return recordStates;
         }
      };
   };

   function describe(resUrl, name) {
      var url = resUrl + '/' + name;
      var promise = Promise.resolve($.getJSON(url + '/describe'));
      return promise.then(function(allData) {
         var result = null;
         if (!allData['Resources'] || !allData['Resources'][name]) {
            throw new Error('Cannot find metadata for resource' + name + '.');
         } else {
            result = allData['Resources'][name];
         }
         return result;
      })
   };


   /**
    * RESTfull DataTable.
    * Same contract as DataTable but return data from an ADFbc REST resource.
    */
   function RestDataTable(resUrl, name) {
      var url = resUrl + '/' + name,
          columns = [],
          dataSource,
          recordStates = {},
          isLoaded = false;

      // Return a promise the metadata will be loaded.
      // var describeTable = function() {
      //    var jqXHR = $.getJSON(url + '/describe');
      //    return jqXHR.then(function(allData) {
      //       var result = null;
      //       if (!allData['Resources'] || !allData['Resources'][name]) {
      //          console.log('Cannot find metadata for resource' + name + '.');
      //       } else {
      //          result = allData['Resources'][name];
      //       }
      //       return result;
      //    })
      // };

      // var describePromise = function() {
      //    var promise = Promise.resolve($.getJSON(url + '/describe'));
      //    return promise.then(function(allData) {
      //       var result = null;
      //       if (!allData['Resources'] || !allData['Resources'][name]) {
      //          console.log('Cannot find metadata for resource' + name + '.');
      //       } else {
      //          result = allData['Resources'][name];
      //       }
      //       return result;
      //    })
      // };

      // Initialize all member variable using the result of describe ajax call.
      var _init = function(resource) {
         // Build the columns array
         for (var index = 0; index < resource['attributes'].length; index++) {
            var attr = resource['attributes'][index];
            columns.push({
               displayIndex: index,
               headerText: attr.title || attr.name,
               field: attr.name
            });
         };

         // throw new Error('Error while initilizing DataTable ' + name + '.');

         // Initialize idAttribute using the PK defined in the resource metadata
         var idAttribute = null;
         if (resource.collection || resource.collection.finders || resource.collection.finders[0]) {
            idAttribute = resource.collection.finders[0].attributes[0].name;
         }

         // Initialize rangeSize using the resource metadata
         var rangeSize = 10; // default value
         if (resource.collection || resource.collection.rangeSize) {
            rangeSize = resource.collection.rangeSize;
         }

         // The model for a row of data
         var RowModel = oj.Model.extend({
            urlRoot: url,
            idAttribute: idAttribute
         });

         var RowCollection = oj.Collection.extend({
            url: url + "?limit=" + rangeSize,
            model: new RowModel()
         });

         dataSource = new oj.CollectionTableDataSource(new RowCollection());

         return dataSource.fetch({
            error: function(model, options, e) {
               console.log('Could not fetch data: ' + e);
            }
         });
      };

      return {
         initialize: function() {
            describe(resUrl, name)
               .then(_init)
               // Launch the fetch right away so that it will do the work while user is
               // looking at the list of tables.
               .then(function(result) {
                  for (var i = 0; i < result.data.length; i++) {
                     var row = result.data[i];
                     // Retrieve the id from the self.href bit
                     var id = row.links.self.href.split('/').pop();
                     recordStates[id] = new oj.RouterState(id, { value: row } );

                  }
                  isLoaded = true;
               })
               .catch(function(error) {
                  var message = (error instanceof Error) ? error.message
                                                         : 'Caught error while making the describe call.';
                  console.log(message);
               });
         },

         getColumns: function() {
            return columns;
         },

         getDataSource: function() {
            return dataSource;
         },

         getRecordStates: function() {
            return recordStates;
         }
      };
   };

   function createDataTables() {
      if (useREST) {
         DeptDataTable = new RestDataTable(serviceURL, 'Departments');
         EmpDataTable = new RestDataTable(serviceURL, 'Employees');
      } else {
         DeptDataTable = new DataTable([
            {headerText: 'Department Id', field: 'id'},
            {headerText: 'Department Name', field: 'name'},
            {headerText: 'Location', field: 'loc'}
         ], [
            {id: 10, name: 'Accounting', loc: 'New York'},
            {id: 20, name: 'Research', loc: 'Dallas'},
            {id: 30, name: 'Sales', loc: 'Chicago'},
            {id: 40, name: 'Operations', loc: 'Boston'}
         ], 'id');

         EmpDataTable =  new DataTable([
            {headerText: 'Employee Id', field: 'id'},
            {headerText: 'Employee Name', field: 'name'},
            {headerText: 'Job', field: 'job'},
            {headerText: 'Salary', field: 'sal'},
            {headerText: 'Dept', field: 'deptno'}
         ], [
            {id:7369, name: 'Smith', job: 'Clerk', sal: 800, deptno: 20},
            {id:7499, name: 'Allen', job: 'Salesman', sal: 1600, deptno: 30},
            {id:7521, name: 'Ward', job: 'Salesman', sal: 1250, deptno: 30},
            {id:7566, name: 'Jones', job: 'Manager', sal: 2975, deptno: 20},
            {id:7654, name: 'Martin', job: 'Salesman', sal: 1250, deptno: 30},
            {id:7698, name: 'Blake', job: 'Manager', sal: 2850, deptno: 30},
            {id:7782, name: 'Clark', job: 'Manager', sal: 2450, deptno: 10},
            {id:7788, name: 'Scott', job: 'Analyst', sal: 3000, deptno: 20},
            {id:7839, name: 'King', job: 'President', sal: 5000, deptno: 10}
         ], 'id');
      }

      try {
         DeptDataTable.initialize();
         EmpDataTable.initialize();
      } catch (ex) {
         console.log('Something bad happen during initialize: ' + ex.message());
      };
   };

   /**
    * The view model for the tables page.
    */
   var viewModel = {
      router: undefined,

      initialize: function(params) {
         if (this.router) {
            return;
         }
         var parentRouter = params.valueAccessor().params['ojRouter']['parentRouter'];

         /**
          * Create a new nested router. The parentRouter is coming from the module data
          * in index.html.
          * startId is the name of the property to use for the state value on the click event.
          */
         this.router = parentRouter.createChildRouter('table')
            .configure({
               'dept': {
                  label: 'Department Table',
                  value: DeptDataTable
               },
               'emp': {
                  label: 'Employee Table',
                  value: EmpDataTable
               }
            });

         // The observable used to display the current table.
         this.table = this.router.currentValue;

         /**
          * Create a new router nested in the router to manage the state of
          * the record.
          */
         var table = this.table;
         this.recordRouter = this.router.createChildRouter('id').
            // Uses a callback to get State from id instead of a dictionary of states
            configure(function(stateId) {
               var state;

               if (stateId && table()) {
                  var data = table().getRecordStates();
                  if (data) {
                     state = data[stateId];
                  }
               }
               return state;
            });

         oj.Router.sync().then(function() {
            oj.Logger.info('Done.');
         });
      },

      selectHandler: function(event, ui) {
         if ('menu' === event.target.id && event.originalEvent) {
            // Invoke go() with the selected item.
            viewModel.router.go(ui.key);
         }
      },

      // The observable used to display the current record. Computes the record data
      // from the state of the recordRouter.
      record: ko.pureComputed(function() {
         var newRecordValue = null;
         if (viewModel) {
            newRecordValue = viewModel.recordRouter.currentValue();
         }
         // The object returned is an oj.Row
         return newRecordValue;
      }, viewModel),

      // The observable used to change the template between table and record view.
      // Uses the recordRouter state.
      displayMode: ko.pureComputed(function() {
         return viewModel.recordRouter.currentState() ? 'record-template' : 'table-template';
      }, viewModel),

      // Callback used by the template to enable the active row listener on
      // the new table. See afterRender def in template in tablesContent.html
      afterTemplate: function() {
         if (viewModel.displayMode() === 'table-template') {
            $('#dataGrid').on('ojbeforecurrentrow', viewModel.currentRowListener);
         }
      },

      // Listener for table click on row
      currentRowListener: function (event, ui) {
         viewModel.recordRouter.go(ui.currentRow.rowKey.toString());
      }
   };

   createDataTables();

   return viewModel;
});


