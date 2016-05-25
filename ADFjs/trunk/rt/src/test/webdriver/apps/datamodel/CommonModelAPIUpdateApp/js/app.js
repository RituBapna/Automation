function viewModel() {
    var self = this;
    self.serviceURL = 'http://den00bwa.us.oracle.com:8001/hrws/rest/main/Departments';
    self.Departments = ko.observableArray([]);
     self.workingId = ko.observable();
     self.currentDeptName =ko.observable();
 self.DeptCol = ko.observable();
     
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
self.DeptCol(new DeptCollection());
    
    // populate the collection by calling fetch()
    self.DeptCol().fetch({
        success: function(collection, response, options) {
            deptData = collection;

            // This will create a ko.observable() for each element in the deptData response 
            // and assign the resulting array to the Departments ko observeableArray.
            self.Departments = oj.KnockoutUtils.map(deptData, null, true);
            
            //perform a Knockout applyBindings() call binding this viewModel with the curent DOM 
            ko.applyBindings(self, document.getElementById("mainContent"));
            
            //Show the content div after the REST call is completed.
            $('#mainContent').show();
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log('Error in fetch: '+ textStatus);
        }
    });
    
    self.showChangeNameForm = function(deptId, data, event) {
                    var currName = data.DepartmentName;
                    self.workingId(deptId);
                    self.currentDeptName(currName);
                 //   $('#editDialog').ojDialog('open');
                };

     self.updateDeptName = function(formData, event) {
                    var currentId = self.workingId();
                    var newName = formData.elements[0].value;
                    if (newName != self.currentDeptName() && newName != '') {
                        var myCollection = self.DeptCol();
                        var myModel = myCollection.get(currentId);
                        myModel.save({'DepartmentName': newName}, {
                            //contentType: 'application/vnd.oracle.adf.resource+json',
                            contentType: 'application/vnd.oracle.adf.resourceitem+json',
                            success: function(myModel, response, options) {
                              //  $('#editDialog').ojDialog('close');
                            },
                            error: function(jqXHR, textStatus, errorThrown) {
                                alert("Update failed with: " + JSON.stringify(jqXHR));
                              //  $('#editDialog').ojDialog('close');
                            }
                        });
                    } else {
                        alert('Department Name is not different or the new name is not valid');
                    //    $('#editDialog').ojDialog('close');
                    }
                };
}

parseDept = function(response) {
    return {DepartmentId: response['DepartmentId'], DepartmentName: response['DepartmentName']};
};

parseSaveDept = function(response) {
    return {DepartmentId: response['DepartmentId'], DepartmentName: response['DepartmentName']};
};




vm = new viewModel();