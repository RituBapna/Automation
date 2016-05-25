define(['ojs/ojcore', 'knockout', 'jquery', 'bkUtils','ojs/ojknockout', 'ojs/ojcomponents'],
function(oj, ko, $, bkutils)
{ 
    function sortButtonModel(){
        var self = this;
        var bku = new bkutils();
        
        self.sortVal = ko.observable("");
        self.sortArray = [ null, "DepartmentId","DepartmentName" ];                
                
        self.cycleSort = function() {
            self.sortVal(bku.cycleFunc("sortProperty",self.sortArray,self.sortVal()));
        }        
    
    oj.Logger.info("sortButtonVM created");
    }
    return sortButtonModel;
});