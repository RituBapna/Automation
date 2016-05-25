define(['ojs/ojcore', 'knockout', 'ojs/ojknockout-validation', 'ojs/ojvalidation', 'jquery'], function (oj, ko) {
    /* 
 * Your application specific code will go here
 */

 
    function employeeVM() {
        var self = this;
        self.required = new oj.RequiredValidator();

        self.email = ko.observable();
      
        self.password = ko.observable();
        self.generalInfo = ko.observable();
        self.employeeTypeSelected = ko.observable();
        self.employeeTypes = ko.observableArray([{label : "Full Time", id : 1, value : "fulltime"},{label : "Contractor", id : 1, value : "contractor"},{label : "Part Time", id : 1, value : "parttime"}]);
        self.employeeSkillSet = ko.observableArray([{label : "DB Administrator", value : "DB_ADMIN", id : 1},{label : "java Programmer", value : "JAVA", id : 2},{label : "Web Designer", value : "WEB_DESIGNER", id : 3},{label : "J2EE Certified", value : "J2EE", id : 4},{label : "UI Designer", value : "UI_DESIGNER", id : 5},{label : "Technical Writter", value : "TECH_WRITER", id : 6}]);
        self.primarySkillSelected = ko.observable();
        self.secondarySkillSet = ko.observableArray([]);
        self.empImage = ko.observable();
        self.files = [];
        self.empImageFilename = ko.observable();

        self.empId = ko.observable();

        self.firstName = ko.observable();

        self.titles = ko.observableArray(["Mr.", "Ms.", "Miss", "Dr."]);
        //self.titleSelected = ko.observable();
        self.titleSelected = ko.observable();
        self.hiredate = ko.observable();
        self.hiredatetime = ko.observable();
        self.hiredatetimelocal = ko.observable();
        self.hiredatemonth = ko.observable();
        self.hiredatetime = ko.observable();
        self.hiredateweek = ko.observable();
        self.salary = ko.observable(0.0);
        self.comm = ko.observable(0);
        self.rating = ko.observable();
        self.ranking = ko.observable(0);
        self.URL = ko.observable();

      

    }

    $(document).ready(function () {
     
        ko.applyBindings(new employeeVM(), document.getElementById('mainContent'));
    });
});