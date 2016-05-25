require(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojcomponents', 'ojs/ojtable', 'ojs/ojcollectiontabledatasource'],
        function (oj, ko, $)
        {
            oj.Logger.option("level", oj.Logger.LEVEL_INFO);
            function toDoVM() {
                var self = this;

                self.showTable1 = ko.observable(false);

                self.ToDos = ko.observableArray([]);

                self.ToDo = oj.Model.extend({
                    parse: parseToDo, parseSave: parseSaveToDo, idAttribute: 'Id',
                    validate: function (attrs, options) {
                        console.log("In Validate", JSON.stringify(attrs));
                    }
                    
                });
                var myTodo = new self.ToDo();
                var ToDoCollection = oj.Collection.extend({
                    url: "js/data/todo.json", model: myTodo, parse: parseToDos, comparator: "Id"
                });
                self.ToDoCol = ko.observable();
                               

                self.ToDoCol(new ToDoCollection());
                self.datasource = ko.observable();         
                        
                self.ToDoCol().fetch({
                    success: function (collection, response, options) {
                        todoData = collection;
                        self.ToDos = oj.KnockoutUtils.map(todoData, null, true);
                        // The above mapping created ko.observable() for departmentId, departmentName and link
                        // The link observable was created using the parseDepts function because link was not a 
                        // top level element in the returned JSON object
                        console.log("self.ToDos()" + self.ToDos().length);
                        self.datasource(new oj.CollectionTableDataSource(self.ToDoCol()));
                        self.showTable1(true);
                    },
                    error: function (collection, response, options) {
                        console.log("error");
                        // The above mapping created ko.observable() for departmentId, departmentName and link
                        // The link observable was created using the parseDepts function because link was not a 
                        // top level element in the returned JSON object

                    }
                });



                self.newId = ko.observable();
                self.newTitle = ko.observable();
                self.newCreatedBy = ko.observable();

                self.addNewToDo = function (vm, e) {
                 console.log("Changes before Add: "+ self.ToDoCol().changes);
                 $("#resultsFunction").html("<label>Changes before Add: "+ self.ToDoCol().changes +"<\/label>");
                    self.ToDoCol().create({Id: vm.newId(), Title: vm.newTitle(), CreatedBy: vm.newCreatedBy()}, {contentType: 'application/vnd.oracle.adf.resource+json',
                        success: function (user) {
                            console.log("Created");
                        },
                        failure: function (user) {
                            console.log('failed');
                        }
                    });
                  $("#resultsFunction").html("<label>Changes after Add: "+ self.ToDoCol().changes +"<\/label>");
                  $('#addPopup').ojPopup('close');
                }
                
                
                self.refetchCollection = function(vm,e){
                    self.ToDoCol().refresh();
                    $("#resultsFunction").html("<label>Changes after Refetch: "+ self.ToDoCol().changes +"<\/label>");                 
                }

                self.deleteToDo = function (vm, e) {
                    model = self.ToDoCol().get(vm.Id);
                    console.log("self.ToDos()before  delete" + self.ToDos().length);
                    self.ToDoCol().remove([model]);
                    console.log("self.ToDos()after delete" + self.ToDos().length);
                    console.log("Changes after Delete: "+ self.ToDoCol().changes);
                   
                    //$('#table').ojTable('refresh');
                    $("#resultsFunction").html("<label>Changes after delete: "+ self.ToDoCol().changes +"<\/label>");
                                        
                }

                self.editRowId = ko.observable();
                self.editRowTitle = ko.observable();
                self.editRowCreatedBy = ko.observable();

                self.editRow = ko.observable();
                self.openEditPopup = function (vm, e) {
                    self.editRow = self.ToDoCol().get(vm.Id);
                    self.editRowId(self.editRow.get("Id"));
                    self.editRowTitle(self.editRow.get("Title"));
                    self.editRowCreatedBy(self.editRow.get("CreatedBy"));
                    $('#editPopup').ojPopup('open');


                }

                self.editToDo = function (vm, e) {
                    var id = vm.editRowId();
                    var title = vm.editRowTitle();
                    var createdBy = vm.editRowCreatedBy();
                    console.log("size" + self.ToDoCol().size());
                    model = self.ToDoCol().get(id);
                    console.log(JSON.stringify(model));
                    model.set({Id: id, Title: title, CreatedBy: createdBy}, {validate: true});

                    self.ToDoCol().add([model], {merge: true});
                    console.log("size" + self.ToDoCol().size());
                    console.log(JSON.stringify(self.ToDoCol()));

                    $('#editPopup').ojPopup('close');
                    $("#resultsFunction").html("<label>Changes after Edit: "+ self.ToDoCol().changes +"<\/label>");
                    

                }



            }

            parseToDos = function (response) {
                return response.todos;
            };
            parseToDo = function (response) {
                if (response['ToDos']) {
                    var innerResponse = response['ToDos'][0];
                    return {Id: innerResponse['Id'], Title: innerResponse['Title'], CreatedBy: innerResponse['CreatedBy']};
                }
                return {Id: response['Id'], Title: response['Title'], CreatedBy: response['CreatedBy']};
            };
            parseSaveToDo = function (response) {
                return {Id: response['Id'], Title: response['Title'], CreatedBy: response['CreatedBy']};
            };
            $(document).ready(function () {


                vm = new toDoVM();
                // Activates knockout.js
                ko.applyBindings(vm);
                console.log("vm todo lenth" + vm.ToDos().length);
                $.ajaxSetup({
                    xhrFields:
                            {
                                withCredentials: true
                            },
                    crossDomain: true
                });
            });
        });

