function viewModel() {
    var self = this;
    self.serviceURL = 'http://den00bwa.us.oracle.com:8001/hrws/rest/main/Departments';
    self.Departments = ko.observableArray([]);

    // think of this as a single record in the DB, or a single row in your table
    var Department = oj.Model.extend({
        urlRoot: self.serviceURL,
        parse: parseDept,
        idAttribute: 'DepartmentId'
    });
    
    var myDept = new Department();
    
    // this defines our collection and what models it will hold
    var DeptCollection = oj.Collection.extend({
        url: self.serviceURL+"?limit=50",
        model: myDept
    });

    self.DeptCol = new DeptCollection();
    
    // populate the collection by calling fetch()
    self.DeptCol.fetch({
        success: function(collection, response, options) {
            deptData = collection;
            
            // This will create a ko.observable() for each element in the deptData response 
            // and assign the resulting array to the Departments ko observeableArray.
            self.Departments = oj.KnockoutUtils.map(deptData, null, true);
            
            //perform a Knockout applyBindings() call binding this viewModel with the curent DOM 
            ko.applyBindings(self);
            
            //Show the content div after the REST call is completed.
            $('#mainContent').show();
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log('Error in fetch: '+ textStatus);
        }
    });
}

parseDept = function(response) {
    return {DepartmentId: response['DepartmentId'], DepartmentName: response['DepartmentName']};
};

vm = new viewModel();