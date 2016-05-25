require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout'], function (oj, ko, $) {
    oj.Logger.option("level", oj.Logger.LEVEL_INFO);

    function progressBarViewModel() {

        var self = this;
                self.progressValue = ko.observable(20);
                   
                self.indeterminate = function() {
                    self.progressValue(-1);
                };
                
                
                self.addContextMenu = function (event, ui) {
                        $("#progressbar").ojProgressbar("option", "contextMenu", "#myMenu");
                        $("#resultsFunction").html("<label> <b> addContextMenu </b><\/label>");
                };
                
                self.removeContextMenu = function (event, ui) {
                        $("#progressbar").ojProgressbar("option", "contextMenu", ""); 
                        $("#resultsFunction").html("<label> <b> removeContextMenu </b><\/label>");
                };
                
                
                self.disableProgressbar = function (event, ui) {
                        $("#progressbar").ojProgressbar( "option", "disabled", true );
                        $("#spinner-input").ojInputNumber( "option", "disabled", true );
                        $("#resultsFunction").html("<label> <b> ProgressBar disabled.</b><\/label>");                      
                        
                };

                self.enableProgressbar = function (event, ui) {
                        $("#progressbar").ojProgressbar( "option", "disabled", false );
                        $("#spinner-input").ojInputNumber( "option", "disabled", false );
                        $("#resultsFunction").html("<label> <b> ProgressBar enabled.</b><\/label>");
                        
                };
                
         
              /*  self.createProgressBar = function(event, ui) {
                        oj.Logger.info("In createProgressBar ...");                       
                        $("#progressbar").ojProgressbar("create");                        
                      
                    }*/
                
                self.destroyProgressBar = function(event, ui) {
                        oj.Logger.info("In destroyProgressBar ...");                       
                        $("#progressbar").ojProgressbar("destroy"); 
                        $("#resultsFunction").html("<label> <b> ProgressBar destroyed.</b><\/label>");
                      
                    }
                
    }
    
    $(document).ready(function () {
        pbVM = new progressBarViewModel();
        $("#progressbar").on("ojcreate", function(e, ui) {
                oj.Logger.info("In ojcreate event of progressBar");
                $("#resultsFunction").html("<label> <b> In ojcreate event of progressBar </b><\/label>");
              
            });



            $("#progressbar").on("ojdestroy", function(e, ui) {
                oj.Logger.info("In oj destroy event of progressBar");
                $("#resultsFunction").html("<label> <b> In ojdestroy event of progressBar </b><\/label>");
            });
            
            ko.applyBindings(pbVM, document.getElementById('progress-container'));
        
        
                            
    });
    
})