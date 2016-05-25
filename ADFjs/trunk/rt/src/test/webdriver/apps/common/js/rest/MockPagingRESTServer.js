var MockPagingRESTServer = function (data, options) { //idField, collProp, timeout, url, idurl, pagingUrl, proxy) {
    options = options || {};
    var idField = options['id'];
    var collProp = options['collProp'];
    MockPagingRESTServer.idHash = options['idHash'];
    MockPagingRESTServer.idUrl = new RegExp("^\\/context-root\\/ojet\\/"+collProp+"\\/([\\d]+)$", 'i');
    MockPagingRESTServer.pagingUrl = new RegExp("^\\/context-root\\/ojet\\/"+collProp+"(?:\\?limit=([\\d]*))?(?:(?:&offset=([\\d]*))|(?:&fromID=([\\d]*))|(?:&since=([\\d]*))|(?:&until=([\\d]*))?)(?:&orderBy=([\\w:\\w,?]*,?))?(?:&q=([\\S][^&]*,?))?(?:&totalResults=(true|false))?",'i');
    
    this.url = options['url'] ? options['url'] : "/context-root/ojet/"+options['collProp'];
    this.idUrl = options['idUrl'] ? options['idUrl'] : MockPagingRESTServer.idUrl;
    this.pagingUrl = options['pagingUrl'] ? options['pagingUrl'] : MockPagingRESTServer.pagingUrl
    this.collection = collProp;
    this.timeout = options['timeout'] ? options['timeout'] : MockPagingRESTServer.timeout;
    this.idPool = 1000;
    this.noRecords = false;
    this.noTotalResults = options['noTotalResults'];
    this.noCopy = options['nocopy'];
    
    // Copy return value property names
    this.propertyNames = {};
    for (var prop in MockPagingRESTServer.propertyNameDefaults) {
        if (this.propertyNames[prop] === undefined && MockPagingRESTServer.propertyNameDefaults.hasOwnProperty(prop)) {
            this.propertyNames[prop] = MockPagingRESTServer.propertyNameDefaults[prop];
        }        
    }
    
    this.data = data;
    this.idField = idField;
    var self = this;
        
    var mockOptions = {
      url: self.pagingUrl,
      urlParams: MockPagingRESTServer.pagingUrlParams,
      type: 'GET',
      contentType: 'json',
      responseTime: 50,
      response: function(settings) { 
        // Get parameter values
        var limit = settings.urlParams.limit ? settings.urlParams.limit : 15;
        var since = settings.urlParams.since;
        var orderBy = settings.urlParams.orderBy;
        var until = settings.urlParams.until;
        var offset = settings.urlParams.offset;
        var fromID = settings.urlParams.fromID;
        var query = settings.urlParams.q;
        if (!query) {
            query = self.filter;
        }
        
        var totalResults = settings.urlParams.totalResults === undefined ? true : settings.urlParams.totalResults;
        // Master override
        if (self.noTotalResults) {
            totalResults = false;
        }

        var dataArr = self.getDataArray(self.data, self.collection);
        //offset = self.getOffset(dataArr, offset, fromID);
          
        var sorts = self.getSortAttrs(orderBy);
        var attrs = self.getQueryAttrs(query);
        
        var retVal =  self.limitData(self.data, self.collection, limit, fromID, offset, sorts, attrs);
        retVal = self.addReturnOptions(retVal, self.filter ? retVal[collProp].length : dataArr.length, {totalResults: totalResults,
                                                limit: limit,
                                                offset: retVal.offset,
                                                collection: self.collection}, self.propertyNames);
        retVal = JSON.stringify(retVal);
        
        
        if (settings['dataType'] === 'jsonp' && settings['jsonpCallback']) {
            this.responseText = settings['jsonpCallback'] + "(" + retVal + ");";
        }
        else {
          this.responseText = retVal;
        }
      },
    };
    
    var proxy = options['proxy'];
    if (proxy) {
        mockOptions['proxy'] = proxy;
    }
    
    // fetch
    $.mockjax(mockOptions);      
    
    // delete
    $.mockjax({
        url: this.idUrl,
        urlParams: ['id'],
        type: 'DELETE',
        response: function (settings) {
            var id = settings.urlParams.id, data;
            // Find id in data list
            data = self.getData();
            for (var i = 0; i < data.length; i++) {
                if (data[i][self.idField] == id) {
                    data.splice(i, 1);
                    this.responseText = JSON.stringify({totalResults:data.length});
                    return;
                }
            }
        }
    });

    // create
    $.mockjax({
        url: this.url,
        type: 'POST',
        response: function (settings) {
            // Forced error for testing
            if (self.forceAddError) {
                this.responseText = undefined;
                this.status = 500;
                return;
            }
            var data = self.getData();
            var obj = JSON.parse(settings.data);
            if (!data.some(function(element, index, array) {
                return obj[self.idField] === array[index][self.idField];
            })) {
                data.push(obj);
                var item = data[data.length-1];
                // Assign an ID
                if (!item[self.idField]) {
                    item[self.idField] = self.idPool.toString();
                    self.idPool++;
                }
                this.responseText = item;
            }
            else {
                // Already there--error
                this.responseText = undefined;
                this.status = 500;
            }
        }
    });
    
    // update
    $.mockjax({
        url: self.idUrl,
        urlParams: ['id'],
        type: 'PUT',
        response: function (settings) {
            var id = settings.urlParams.id, data;
            // Find id in data list
            var data = self.getData();
            for (var i = 0; i < data.length; i++) {
                var x = data[i];
                if (x[self.idField] == id) {
                    // Update data fields
                    data[i] = JSON.parse(settings.data);
                    this.responseText = data[i];
                }
            }

        }
    });
};

MockPagingRESTServer.prototype.getQueryAttrs = function(queryStr) {
    if (queryStr === undefined) {
        return;
    }
    var fields = queryStr.split(",");
    var list = [], obj;
    for (var i = 0; i < fields.length; i++) {
        obj = {};
        var comp = fields[i].split("+");
        var sublist = [];
        for (var j = 0; j < comp.length; j++) {
            sublist.push(comp[j]);
        }
        //obj[comp[0]] = comp[1];
        list.push(sublist);
    }
    return list;
};

MockPagingRESTServer.prototype.setNoRecords = function(norec) {
    this.noRecords = norec;
};

MockPagingRESTServer.prototype.getSortAttrs = function(sortStr) {
    if (sortStr === undefined) {
        return;
    }
    var fields = sortStr.split(",");
    var list = [];
    for (var i = 0; i < fields.length; i++) {
        var comp = fields[i].split(":");
        list.push({attr: comp[0], dir: comp[1] === "asc" ? 1 : -1});
    }
    return list;
};

MockPagingRESTServer.prototype.getLimit = function(limit, offset, all) {
    return (limit !== undefined) ? parseInt(limit) : parseInt(all)-parseInt(offset);
};

MockPagingRESTServer.prototype.getOffset = function(dataArr, offset, fromID) {
    var retOffset = parseInt((offset !== undefined) ? offset : 0);
    
    return this.calculateStart(dataArr, retOffset, fromID);
};

MockPagingRESTServer.prototype.calculateStart = function(dataArr, offset, fromID) {
    if (fromID) {
        // Find this ID in the data
        if (MockPagingRESTServer.idHash) {
            return MockPagingRESTServer.idHash[fromID];
        }
        for (var i = 0; i < dataArr.length; i++) {
            if (dataArr[i] && dataArr[i][this.idField] && fromID == dataArr[i][this.idField]) {
                // Found it
                return i;
            }
        }
        // Not found
        return -1;
    }
    
    return offset;
};

MockPagingRESTServer.prototype.hasMore = function(all, options) {
    var limit = this.getLimit(options.limit, options.offset, all);
    return options.offset+limit < all;
};

MockPagingRESTServer.prototype.hasAttrs = function(obj, attrs) {
    if (!obj) {
        return false;
    }
    if (attrs.indexOf("=") > -1) {
        var conds = attrs.split("=");
        if (obj[conds[0]] != conds[1]) {
            return false;
        }
    }
    else if (attrs.indexOf("<") > -1) {
        var conds = attrs.split("<");
        if (new Number(obj[conds[0]]) >= new Number(conds[1])) {
            return false;
        }
    }
    else if (attrs.indexOf(">") > -1) {
        var conds = attrs.split(">");
        if (new Number(obj[conds[0]]) <= new Number(conds[1])) {
            return false;
        }
    }
    return true;
};

MockPagingRESTServer.prototype.hasAttrSet = function(obj, attrs) {    
    var val, attr;    
    for (var i = 0; i < attrs.length; i++) {
        var subattrs = attrs[i];
        var ok = true;
        for (var j = 0; j < subattrs.length; j++) {
            if (!this.hasAttrs(obj, subattrs[j])) {
                // One of the and conditions failed--this condition will fail
                ok = false;
                break;
            }
        }
        // If this piece of the or condition passed, we pass
        if (ok) {
            return true;
        }
    }
    return false;
};

MockPagingRESTServer.prototype.selectData = function(data, attrs) {
    if (attrs) {
        var newArray = [];
        for (var i = 0; i < data.length; i++) {
            if (this.hasAttrSet(data[i], attrs)) {
                newArray.push(data[i]);
            }
        }
        return newArray;
    }
    return data;
};

MockPagingRESTServer.prototype.getDataArray = function(obj, collection, sorts, attrs) {
    if (this.noRecords) {
        return [];
    }
    
    // Make a copy, otherwise we'll mess up original in case it wants to be sorted differently
    var dataCopy 
    if (this.noCopy) {
        // Performance issues
        dataCopy = obj[collection];
    }
    else {
        dataCopy = this.selectData(obj[collection].slice(0), attrs);
    }
    if (sorts) {
        // Throw out hash
        MockPagingRESTServer.idHash = null;
        // Sort
        dataCopy.sort(function(a, b) {
            var attr, dir;
            for (var i = 0; i < sorts.length; i++) {
                attr = sorts[i].attr;
                dir = sorts[i].dir;
                if (a[attr] > b[attr]) {
                    return dir;
                }
                else if (a[attr] < b[attr]) {
                    return -dir;
                }
            }
            return 0;
        });
    }
    
    return dataCopy;
};

MockPagingRESTServer.prototype.limitData = function(data, collection, limit, fromID, offset, sorts, attrs) {
    // Copy info over to avoid modifying original
    var newData = {};
    for (var prop in data) {
        if (data.hasOwnProperty(prop)) {
            if (prop === collection) {
                // Copy the actual data and apply any offsets or limits
                newData[prop] = [];
                var dataArr = this.getDataArray(data, collection, sorts, attrs);
                offset = this.getOffset(dataArr, offset, fromID);
                if (offset > -1) {
                    newData['offset'] = offset;
                    limit = this.getLimit(limit, offset, dataArr.length);               
                    if (limit+offset > dataArr.length) {
                        limit = dataArr.length-offset;
                    }
                    for (var i = offset; i < offset+limit; i++) {
                        newData[prop].push(dataArr[i]);
                    }
                }
            }
            else {
                newData[prop] = data[prop];
            }
        }
    }
    return newData;
};

MockPagingRESTServer.prototype.addReturnOptions = function(returnObj, all, options, propertynames) {
    var options = options || {};
    if (!propertynames) {
        propertynames = MockPagingRESTServer.propertyNameDefaults;
    }
    
    if (options.totalResults) {
        returnObj[propertynames.totalResults] = all;
    }
    if (propertynames.hasMore) {
        returnObj[propertynames.hasMore] = this.hasMore(all, options);
    }
    returnObj[propertynames.limit] = options.limit;
    return returnObj;
};

MockPagingRESTServer.propertyNameDefaults = {
    totalResults: 'totalResults',
    limit: 'limit',
    offset: 'offset',
    count: 'count',
    hasMore: 'hasMore'
};

MockPagingRESTServer.parameterNameDefaults = {
    limit: 'limit',
    offset: 'offset',
    fromID: 'fromID',
    since: 'since',
    until: 'until',
    orderBy: 'orderBy',
    q: 'q',
    totalResults: 'totalResults'
};

MockPagingRESTServer.prototype.getURL = function() {
    return this.url;
};

MockPagingRESTServer.prototype.shutdown = function () {
    $.mockjax.clear();
};

MockPagingRESTServer.prototype.getData = function() {
    var prop;
    
    // Check to see if data is lurking down property (like ADF bc REST)
    if (this.data instanceof Array)
        return this.data;
    
    for (prop in this.data) {
        if (this.data.hasOwnProperty(prop)) {
            if (this.data[prop] instanceof Array) {
                return this.data[prop];
            }
        }
    }
    return this.data;
};

MockPagingRESTServer.timeout = 10;
//MockPagingRESTServer.idUrl = /^\/context-root\/ojet\/items\/([\d]+)$/i;

//MockPagingRESTServer.pagingUrl = /^\/context-root\/ojet\/items(?:\?limit=([\d]*))?(?:(?:&offset=([\d]*))|(?:&fromID=([\d]*))|(?:&since=([\d]*))|(?:&until=([\d]*))?)(?:&totalResults=(true|false))?/i;
MockPagingRESTServer.pagingUrlParams = [MockPagingRESTServer.parameterNameDefaults.limit, MockPagingRESTServer.parameterNameDefaults.offset, 
                                        MockPagingRESTServer.parameterNameDefaults.fromID, MockPagingRESTServer.parameterNameDefaults.since,
                                        MockPagingRESTServer.parameterNameDefaults.until, MockPagingRESTServer.parameterNameDefaults.orderBy,
                                        MockPagingRESTServer.parameterNameDefaults.q, MockPagingRESTServer.parameterNameDefaults.totalResults];