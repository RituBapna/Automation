/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojmodel', 'ojs/ojcube', 'ojs/ojdatagrid'],
function(oj, ko, $)
{
    function generateCube(dataArr, axes) {
        return new oj.DataValueAttributeCube(dataArr, axes
        ,[{attribute:'units',aggregation:oj.CubeAggType['SUM'],label: 'Units(SUM)'},
                    {attribute:'sales',label: 'Sales'},
                    {attribute:'tax',aggregation:oj.CubeAggType['AVERAGE'],label: 'Tax(AVERAGE)'}]);
    };
    
    function dataGridModel(collection) {
        var vm = this;
        
        var dataArr = collection.map(function(model) {
            return model.attributes;
        });
        
        var axes = [
            {axis: 0, levels: [
                    {attribute: 'city'},
                    {attribute: 'year'},
                    {dataValue: true}]},
            {axis: 1, levels: [
                    {attribute: 'product'},
                    {attribute: 'color'},
                    {attribute: 'drivetrain'}]}];
                
                
         this.dataSource = new oj.CubeDataGridDataSource(generateCube(dataArr, axes));
        // update the model in the collection
        this.rotate = function()
        {
            
            var temp = axes[1].axis;
            axes[1].axis = axes[0].axis;
            axes[0].axis = temp;
            vm.dataSource.setCube(generateCube(dataArr, axes));
            
        };
        
        this.showKeys = function(){
            var keys = vm.dataSource.keys([{column:'2', row:'1'}]);
            keys.then(function(returnVal){
                alert(JSON.stringify(returnVal));
                alert(JSON.stringify(returnVal['row']));    
                alert(JSON.stringify(returnVal['column']));
            })
            var indexes = vm.dataSource.indexes(keys);
            indexes.then(function(indexes) {
                alert(indexes.row,3);
                alert(indexes.column,3);
            });
            
            var cube = generateCube(dataArr, axes);
            
            var axis0 = cube.getAxes()[0];
            var axis1 = cube.getAxes()[1];
            var values = axis0.getValues(0); // get the values of 1st column
            var values1 = axis1.getValues(2);
            alert("Axis 0: " + values[0].getValue() + ":" + values[1].getValue() + ":"+ values[2].getValue());
            alert("Axis 1: "+ values1[0].getValue() + ":" + values1[1].getValue() + ":"+ values1[2].getValue());
            alert(axis0.size());
            var valuesResult = "";
            for(i=0;i<axis0.size();i++){
                var values = axis0.getValues(i); // get the values of 1st column
                var values1 = axis1.getValues(i);
                for(j=0;i<values.size();i++){
                    valuesResult = valuesResult + ":" + values[j];
                    //alert("Axis 0: " + values[i].getValue() + ":" + values[1].getValue() + ":"+ values[2].getValue());
                    //alert("Axis 1: "+ values1[0].getValue() + ":" + values1[1].getValue() + ":"+ values1[2].getValue());
                }
                alert(valuesResult);
            }
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