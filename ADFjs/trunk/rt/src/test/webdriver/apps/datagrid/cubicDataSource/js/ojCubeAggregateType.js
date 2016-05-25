/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojmodel', 'ojs/ojcube', 'ojs/ojdatagrid','ojs/ojselectcombobox'],
function(oj, ko, $)
{
    function generateCube(dataArr, axes,aggType) {
        var func = function(curr, n) { 
           return n ? (curr ? curr - n : 0-n) : curr; 
           }
        
        if(aggType == oj.CubeAggType['CUSTOM']){
            cube = new oj.DataValueAttributeCube(dataArr, axes,    
                    [{attribute:'units',aggregation:aggType,callback:func},
                    {attribute:'sales',aggregation:aggType,callback:func},
                    {attribute:'tax',aggregation:aggType,callback:func}]);
        }
       else
             cube =  new oj.DataValueAttributeCube(dataArr, axes,    
                    [{attribute:'units',aggregation:aggType},
                    {attribute:'sales',aggregation:aggType},
                    {attribute:'tax',aggregation:aggType}]);
        return cube;
    };
    
    function dataGridModel(collection) {
        var vm = this;
        
        var dataArr = collection.map(function(model) {
            return model.attributes;
        });
        
        var axes = [
            {axis: 0, levels: [
                    {attribute: 'city'},
                    {dataValue: true}]},
            {axis: 1, levels: [
                    {attribute: 'year'}]}];
        
       vm.aggregateType = ko.observableArray([{value: 'SUM', label: 'SUM'},
            {value: 'AVERAGE', label: 'AVERAGE'},
            {value: 'COUNT', label: 'COUNT'},
            {value: 'FIRST', label: 'FIRST'},
            {value: 'MIN', label: 'MIN'},
            {value: 'MAX', label: 'MAX'},
            {value: 'NONE', label: 'NONE'},
            {value: 'VARIANCE', label: 'VARIANCE'},
            {value: 'STDDEV', label: 'STDDEV'},
            {value: 'CUSTOM', label: 'CUSTOM'}
            
        ]);
   
                
         vm.dataSource =  ko.observable();
         vm.dataSource = new oj.CubeDataGridDataSource(generateCube(dataArr, axes,oj.CubeAggType['SUM']));
         
         vm.countRows= ko.observable(0);
         vm.countColumns= ko.observable(0);
        
        vm.countRows("Rows: "+ this.dataSource.getCountPrecision("row") + "Count :" + this.dataSource.getCount("row"));        
        vm.countColumns("Columns: " + this.dataSource.getCountPrecision("column") + "Count :" + this.dataSource.getCount("column"));
        
        
        this.changeAggregateType = function(context, valueParam)
        {
            var aggType = oj.CubeAggType['SUM'];
            if (valueParam.option == "value") {
                var newVal = valueParam.value;
                
                //aggType = "oj.CubeAggType['" + newVal +"']";
                //alert(aggType);
               
                if (newVal == "SUM")
                    aggType = oj.CubeAggType['SUM'];
                else if (newVal == "AVERAGE")
                    aggType = oj.CubeAggType['AVERAGE']
                else if (newVal == "COUNT")
                    aggType = oj.CubeAggType['COUNT']
                else if (newVal == "FIRST")
                    aggType = oj.CubeAggType['FIRST']
                else if (newVal == "MIN")
                    aggType = oj.CubeAggType['MIN']
                 else if (newVal == "MAX")
                    aggType = oj.CubeAggType['MAX']
                else if (newVal == "NONE")
                    aggType = oj.CubeAggType['NONE']                    
                else if (newVal == "VARIANCE")
                    aggType = oj.CubeAggType['VARIANCE']
                else if (newVal == "STDDEV")
                    aggType = oj.CubeAggType['STDDEV'] 
                else if (newVal == "CUSTOM")
                    aggType = oj.CubeAggType['CUSTOM']
            
            	vm.dataSource.setCube(generateCube(dataArr, axes,aggType));
            }
        
            
        };
        
        vm.addProductToXAxis = function(){
            var axes1 = [
            {axis: 0, levels: [
                    {attribute: 'city'},
                    {attribute: 'product'},
                    {dataValue: true}]},
            {axis: 1, levels: [
                    {attribute: 'year'}]}];
            vm.dataSource.setCube(generateCube(dataArr, axes1,oj.CubeAggType['SUM']));
            vm.countRows("Rows: "+ this.dataSource.getCountPrecision("row") + "Count :" + this.dataSource.getCount("row"));        
            vm.countColumns("Columns: " + this.dataSource.getCountPrecision("column") + "Count :" + this.dataSource.getCount("column"));
        
            
        }
        
        vm.addProductToYAxis = function(){
            var axes1 = [
            {axis: 0, levels: [
                    {attribute: 'city'},
                    {attribute: 'product'},
                    {dataValue: true}]},
            {axis: 1, levels: [
                    {attribute: 'year'}]}];
            var cube =generateCube(dataArr, axes1,oj.CubeAggType['SUM']);
            cube.pivot(0,1,1,1,oj.Cube.PivotType.SWAP);
            
            vm.dataSource.setCube(cube);
            vm.countRows("Rows: "+ this.dataSource.getCountPrecision("row") + "Count :" + this.dataSource.getCount("row"));        
            vm.countColumns("Columns: " + this.dataSource.getCountPrecision("column") + "Count :" + this.dataSource.getCount("column"));
        
        }
        
       
        
        
    };
    
    var collection = new oj.Collection(null, {
        url: 'js/data/cubeDataDetail.json'
    });

    collection.fetch({success:function() {
        ko.applyBindings(new dataGridModel(collection), 
            document.getElementById('wrapper'));
    }});
});