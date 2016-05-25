function viewModel() {
    var self = this;
    self.serviceURL = 'http://den00bwa.us.oracle.com:8001/hrws/rest/main/Departments';
    self.Departments = ko.observableArray([]);

    // think of this as a single record in the DB, or a single row in your table
    var Department = oj.Model.extend({
        urlRoot: self.serviceURL,
        parse: parseDept,
        parseSave: parseSaveDept,
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
    if (response['Departments']) {
        var innerResponse = response['Departments'][0];
        return {DepartmentId: innerResponse['DepartmentId'], DepartmentName: innerResponse['DepartmentName']};
    }
    return {DepartmentId: response['DepartmentId'], DepartmentName: response['DepartmentName']};
};

parseSaveDept = function(response) {
   // return {Departments: [{DepartmentId: response['DepartmentId'], DepartmentName: response['DepartmentName']}]};
    return {DepartmentId: response['DepartmentId'], DepartmentName: response['DepartmentName']};
};

function addDepartment(formElement, event) {
    var recordAttrs = {DepartmentId: formElement.elements[0].value, DepartmentName: formElement.elements[1].value};
    this.DeptCol.create(recordAttrs, {
       //'contentType': 'application/vnd.oracle.adf.resource+json', 
        'contentType': 'application/vnd.oracle.adf.resourceitem+json',
        success: function(response){
            console.log('Success in Create');
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log('Error in Create: ' + JSON.stringify(jqXHR));
        }
    });
}

vm = new viewModel();