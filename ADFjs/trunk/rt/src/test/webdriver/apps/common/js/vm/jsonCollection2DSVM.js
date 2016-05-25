define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils', 'ojs/ojknockout', 'ojs/ojcomponents', 'ojs/ojcollectiontabledatasource',
        'ojs/ojtable'],
function(oj, ko, $, bku)
{   
  function viewModel()
  {
    var self = this;
    var bk = new bku();
    self.DeptCol = ko.observable();
    self.datasource = ko.observable();
    self.serviceURL = 'http://jdevqa-pc1.us.oracle.com:9680/empdeptrest/resources/depttasks/dept';
    self.parseDept = function(response) {
        oj.Logger.log("response = " + response['DEPTNO'] + " " + response['DNAME'] + " " + response['LOC']);
        return {DepartmentId: response['DEPTNO'],
            DepartmentName: response['DNAME'],
            LocationId: response['LOC']};
    };
    self.Department = oj.Model.extend({
        urlRoot: self.serviceURL,
        parse: self.parseDept,
        idAttribute: 'DepartmentId'
    });
    self.myDept = new self.Department();
    self.DeptCollection = oj.Collection.extend({
        url: self.serviceURL + "?limit=50",
        model: self.myDept
    });
    self.DeptCol(new self.DeptCollection());
    self.DeptCol().fetch({
            success: function(){
                oj.Logger.log("self.DeptCol() = " + self.DeptCol().models);
                self.DeptCol().models.forEach(function(entry) {
                    oj.Logger.log(entry);
                });
                self.datasource(new oj.CollectionTableDataSource(self.DeptCol()));
                oj.Logger.info("mock fetch success");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                bk.msgLog('Error in fetch: ' + textStatus);
            }
        });
    oj.Logger.info("jsonCollection2DSVM created");
  }
  return viewModel;
}); 