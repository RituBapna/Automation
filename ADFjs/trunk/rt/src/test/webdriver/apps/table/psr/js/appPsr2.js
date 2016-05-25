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
            if (source == "collection") { // since created async, won't render on start
                masterVM.model.datasource(self.mockDatasource());
                masterVM.model.datasourceDescr(self.mockDescr);
            } else if (source == "obsArray") {
                masterVM.model.datasource(self.obsDataSource());
                masterVM.model.datasourceDescr(self.obsDescr);
            } else {  // default is array
                masterVM.model.datasource(self.baseDataSource());
                masterVM.model.datasourceDescr(self.baseDescr);
            }


            $(document).ready(function(){
                masterVM.model.setHandlers();
                ko.applyBindings(masterVM);
               });
            
        });