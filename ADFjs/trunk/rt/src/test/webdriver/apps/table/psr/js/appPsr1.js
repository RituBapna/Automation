/**
 * A top-level require call executed by the Application.
 * 
 */
require(['ojs/ojcore','jquery','knockout','tablePsrVM','genericOptionsVM','bkUtils',
                 'simpleMenuVM', 'ojs/ojgauge','ojs/ojknockout','ojs/ojtable',
                 'ojs/ojpagingcontrol'],
                function(oj,$,ko,tmodel,gvm,bku,svm) {   

            oj.Test.ready = true;
                        
            masterVM  = {
                    model : new tmodel(),
                    gvm : new gvm(),
                    bk : new bku(),
                    svm : new svm()
                };

            var count = masterVM.bk.getReqParam("count");
            if (count == undefined) {
                count = 4;
            }
            masterVM.model.initManyRows(count);

            var source = masterVM.bk.getReqParam("source");
            masterVM.model.source(source);
            masterVM.model.hasPaging(false);
            if (source == "collection") { // since created async, this won't render on start
                masterVM.model.datasource(self.mockPaging());
                masterVM.model.datasourceDescr(self.mockPagingDescr);
            } else if (source == "obsArray") {
                masterVM.model.datasource(self.obsPaging);
                masterVM.model.datasourceDescr(self.obsPagingDescr);
            } else {  // default is array
                masterVM.model.datasource(self.basePaging);
                masterVM.model.datasourceDescr(self.basePagingDescr);
            }            
            
            var mode = masterVM.bk.getReqParam("mode");
            masterVM.model.mode(mode);
            
            masterVM.model.hasPaging(true);                        
            
            $(document).ready(function(){
                masterVM.model.setHandlers();
                ko.applyBindings(masterVM);
                });
            
        });
