function viewModel() {
    var self = this;
    self.serviceURL = 'http://den00bwa.us.oracle.com:8001/hrws/rest/main/Departments';
    self.Departments = ko.observableArray([]);
    self.somethingChecked = ko.observable(false);

    // think of this as a single record in the DB, or a single row in your table
    var Department = oj.Model.extend({
        urlRoot: self.serviceURL,
        idAttribute: 'DepartmentId'
    });
    
    var myDept = new Department();
    
    // this defines our collection and what models it will hold
    var DeptCollection = oj.Collection.extend({
        url: self.serviceURL+"?limit=50",
        model: myDept,
        comparator: 'DepartmentId'
    });

    self.DeptCol = new DeptCollection();
    
    // populate the collection by calling fetch()
    self.DeptCol.fetch({
        success: function(collection, response, options) {
            deptData = collection;
            self.Departments = oj.KnockoutUtils.map(deptData, null, true);
            // The above mapping created a ko.observable() for each element in the deptData response

            ko.applyBindings(self);
            
            //Show the content div after the REST call is completed.
            $('#mainContent').show();
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log('Error in fetch: '+ textStatus);
        }
    });
}

function enableDelete(data, event) {
    self = this;
    if (!$('input[type=checkbox]:checked').length) {
        vm.somethingChecked(false);
    } else {
        vm.somethingChecked(true);
    }
    return true;
}

function deleteDepartment(data, event) {
    var deptIds = [];
    deptIds = findDeptIds();
    var collection = data.DeptCol;
    deptIds.forEach(function(value, index, arr) {
        var model = collection.get(parseInt(value));
        collection.remove(model);
        model.destroy();
    });
}

function findDeptIds() {
    var selectedIdsArray = [];
    $("input:checkbox").each(function() {
        var currentContext = $(this);
        if (currentContext.is(":checked")) {
            selectedIdsArray.push(currentContext.attr("id"));
        }
    });
    return selectedIdsArray;
}

vm = new viewModel();