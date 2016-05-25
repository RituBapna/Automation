require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents', 'ojs/ojdatagrid', 'ojs/ojrowexpander', 'ojs/ojcollectiontreedatasource','ojs/ojflattenedtreedatagriddatasource', 'ojs/ojflattenedtreetabledatasource', 'ojs/ojjsontreedatasource'],
        function(oj, ko, $)
        {
            var Employee = oj.Model.extend({idAttribute: 'EmployeeId'});
            var empData = null;


            function expandFunc(e, d) {
                var rowKey = ui['rowKey'];
                alert(rowKey);

            }

            function collapseFunc(event, ui) {
                var rowKey = ui['rowKey'];
                alert(rowKey);

            }
            
            function getChildCollection(rootCollection, model) {
            // Create a collection
            var employees = new oj.Collection(null, {model:Employee});
            var mgrId = model === null ? null : model.id;
            // Root collection, check where ManagerId == null
            for (var i = 0; i < empData.length; i++) {
                if (empData[i]["ManagerId"] === mgrId) {
                    employees.add(empData[i]);
                }
            }
            return employees;
        }
        
        function parseMetadata(model) {
            function findEmployee(id) {
                if (id) {
                    for (var i = 0; i < empData.length; i++) {
                        if (id === empData[i].EmployeeId) {
                            return i;
                        }
                    }
                }
                return -1;
            }
            
            function findManager(id) {
                if (id) {
                    for (var i = 0; i < empData.length; i++) {
                        if (id === empData[i].ManagerId) {
                            return i;
                        }
                    }
                }
                return -1;
            }
            
            function countDepth(model, depth) {
                if (model && model.id) {
                    var managerLoc = findEmployee(model.get("ManagerId"));
                    if (managerLoc > -1) {
                        // I have a manager, increment depth and search for my manager's Model 
                        depth++;
                        return countDepth(new Employee(empData[managerLoc]), depth);
                    }
                }
                return depth;
            }            
            
           // Look this up in the data table: then back out to see depth/leaf
           var retObj = {};
           retObj['key'] = model.id;

           // Does anyone have model.id as a ManagerId?  If not, it's a leaf
           var leaf = true;
           var managerLoc = findManager(model.id);
           if (managerLoc > -1) {
               // Not a leaf
               leaf = false;
           }
           retObj['leaf'] = leaf;
           retObj['depth'] = countDepth(model, 1);
           return retObj;
       }
    
	$(document).ready(
		function() 
		{
			$.getJSON("js/collectionData.json", function(data) 
			{
                            empData = data;
                            var treeDataSource = new oj.CollectionTreeDataSource({root:getChildCollection(null, null),
                                                                              parseMetadata:parseMetadata,
                                                                              childCollectionCallback:getChildCollection});

                            var options = {'columns': ['LastName', 'FirstName', 'Salary']};
                            var dataSource = new oj.FlattenedTreeDataGridDataSource(treeDataSource, options);
                            ko.applyBindings({dataSource:dataSource}, document.getElementById('datagrid'));
			});
		}
	);
});

           