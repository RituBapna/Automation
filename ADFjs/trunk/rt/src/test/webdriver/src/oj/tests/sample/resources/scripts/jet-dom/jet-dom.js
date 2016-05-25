define(function(require, exports, module)
{
//*******************************************************************************************************************//
//---------------------------------------------------DOMNODE---------------------------------------------------------//
//*******************************************************************************************************************//
var DOMNode = function() {};
var ELEMENT_NODE                = DOMNode.ELEMENT_NODE = 1;
var ATTRIBUTE_NODE              = DOMNode.ATTRIBUTE_NODE = 2;
var TEXT_NODE                   = DOMNode.TEXT_NODE = 3;
var DOCUMENT_NODE               = DOMNode.DOCUMENT_NODE = 9;
var DOCUMENT_FRAGMENT_NODE      = DOMNode.DOCUMENT_FRAGMENT_NODE = 11;



DOMNode.prototype = {
  // mirror node type properties in the prototype, so they are present
  // in instances of DOMNode (and subclasses)
  ELEMENT_NODE:                { value: ELEMENT_NODE },
  ATTRIBUTE_NODE:              { value: ATTRIBUTE_NODE },
  TEXT_NODE:                   { value: TEXT_NODE },  
  DOCUMENT_NODE:               { value: DOCUMENT_NODE },  
  DOCUMENT_FRAGMENT_NODE:      { value: DOCUMENT_FRAGMENT_NODE },

  // Node that are not inserted into the tree inherit a null parent
  parentNode: { value: null, writable: true },
  parentElement: { get: function() {
    return (this.parentNode && this.parentNode.nodeType===ELEMENT_NODE) ? this.parentNode : null;
  }},
  
  hasChildNodes: { value: function() { 
    return this.childNodes.length > 0;
  }},

  firstChild: { get: function() {
    return this.childNodes.length === 0 ? null : this.childNodes[0];
  }},

  lastChild: { get: function() {
    return this.childNodes.length === 0 ? null : this.childNodes[this.childNodes.length-1];
  }},
  
  previousSibling: { get: function() {
    if (!this.parentNode) return null;
    var sibs = this.parentNode.childNodes, i = this.index;
    return i === 0 ? null : sibs[i-1];
  }},

  nextSibling: { get: function() {
    if (!this.parentNode) return null;
    var sibs = this.parentNode.childNodes, i = this.index;
    return i+1 === sibs.length ? null : sibs[i+1];
  }}
};


//*******************************************************************************************************************//
//----------------------------------------------DOMELEMENT-----------------------------------------------------------//
//*******************************************************************************************************************//
function DOMElement(doc, name) {
  this.ownerDocument = doc;
  this.nodeName = name;
  this.firstChild = null;
  this.parentNode = null;
  this.nextSibling = null;
  this.previousSibling = null;
  this.attributes = {};
  this.childNodes = [];
  this.childElementCount = 0;
  this.nodeValue = null;
  this.nodeType = DOMNode.ELEMENT_NODE;      
};

function recursiveGetText(node, a) {
  if (node.nodeType === DOMNode.TEXT_NODE) {
    a.push(node.nodeValue);
  }
  else {
    for(var i = 0, n = node.childNodes.length;  i < n; i++)
      recursiveGetText(node.childNodes[i], a);
  }
};


DOMElement.prototype = Object.create(DOMNode.prototype, {  
  innerHTML: {
    get: function() {      
      return this.toString();
    },
    set: function(){}
  },
  
  textContent: {
    get: function(){
      var strings = [];
      recursiveGetText(this, strings);
      return strings.join('');
    },

    set: function(newtext){        
      this.removeChildren();
      if (newtext !== null && newtext !== '') {
        this.appendChild(this.ownerDocument.createTextNode(newtext));
      }
    }   
  },
  className: {
    get: function(){
       return this.attributes['class'];
    },
    set: function(newval){
      this.setAttribute('class', newval);
    }
  },
  style: {value: function() {
     if (!this._style)
       this._style = new CSSStyleDeclaration(this);
     return this._style;
  }},
  
  appendChild: {value: function(child) {
    DOMDocument.appendChildCount++;
    if (child) {
      // remove child from old parent
      if (child.parentNode && child.parentNode.removeChild)
        child.parentNode.removeChild(child);
      // check if child is a current child
      var idx = this.childNodes.indexOf(child);
      if (idx != -1) {
        this.childNodes.splice(idx, 1);
        // Temporarily decrement element count
        this.childElementCount--;
      }

      child.parentNode = this;
      this.childNodes.push(child);
      // Increment element count
      this.childElementCount++;
      _setFamilyTree(this);
    }
    return child;
  }},
  
  cloneNode: {value: function(deep) {
    var clone = new DOMElement(this.ownerDocument, this.nodeName);
    for (var name in this.attributes)
      clone.attributes[name] = this.attributes[name];

    if (deep) {
      for (var i = 0; i < this.childNodes.length; i++) {        
        var childClone = this.childNodes[i].cloneNode(deep);
        clone.appendChild(childClone);
      }
    }
    return clone;
  }},
  
  getAttributeNS: {value: function(namespace, name) {
    return this.attributes[name];
  }},
  
  hasAttributeNS: {value: function(namespace, name) {    
    return this.getAttributeNS(namespace, name) != null;
  }},
  
  insertBefore: {value: function(newChild, referenceChild) {
    var index = this.childNodes.indexOf(referenceChild);
    if (index > - 1) {
      // remove child from old parent
      if (newChild.parentNode)
        newChild.parentNode.removeChild(newChild);
      // check if child is a current child
      var childIdx = this.childNodes.indexOf(newChild);
      if (childIdx != -1) {
        this.childNodes.splice(childIdx, 1);
        // Temporarily decrement element count
        this.childElementCount--;
      }

      newChild.parentNode = this;
      // index could have changed now
      this.childNodes.splice(this.childNodes.indexOf(referenceChild), 0, newChild);
      // Increment element count
      this.childElementCount++;
      _setFamilyTree(this);
    }
    else {
      // if the reference element doesn't exist in the current list of children, insert new element at the end
      this.appendChild(newChild);
    }
  }},
  
  removeChild: {value: function(child) {    
    var index = this.childNodes.indexOf(child);
    if (index != - 1) {
      child.parentNode = null;
      this.childNodes.splice(index, 1);
      // Decrement element count
      this.childElementCount--;
      _setFamilyTree(this);
    }
  }},
  
  removeChildren: {value: function() {    
    if (this.childNodes)
    {
      this.childNodes = [];
    }
    
    this.childElementCount = 0;
    _setFamilyTree(this);    
  }},
  
  removeAttributeNS: {value: function(namespace, name) {    
    delete this.attributes[name];
  }},
  
  removeAttribute: {value: function(name) {  
    delete this.attributes[name];
  }},
  
  replaceChild: {value: function(newChild, oldChild) {
    var index = this.childNodes.indexOf(oldChild);
    if (index > - 1) {
      // remove child from old parent
      if (newChild.parentNode)
        newChild.parentNode.removeChild(newChild);
      // check if child is a current child
      var childIdx = this.childNodes.indexOf(newChild);
      if (childIdx != -1) {
        this.childNodes.splice(childIdx, 1);
        // Decrement element count
        this.childElementCount--;
      }

      newChild.parentNode = this;
      oldChild.parentNode = null;
      // index could have changed
      this.childNodes.splice(this.childNodes.indexOf(oldChild), 1, newChild);  
      _setFamilyTree(this);
    }
  }},
  
  setAttributeNS: {value: function(namespace, name, value) {  
    this.attributes[name] = value;  
  
    if (name == 'id')
    {
      this.ownerDocument.addId(value, this);
    }
  }},
  
  setAttribute: {value: function( name, value) {  
    this.attributes[name] = value;
  
    if (name == 'id')
    {
      this.ownerDocument.addId(value, this);
    }
  }},
  
  getAttribute: {value: function(name) {  
    return this.attributes[name];
  }},
  
  addEventListener: {value: function(type, listener, useCapture) {
    DOMDocument.addListenerCount++;
  }},
  
  removeEventListener: {value: function(type, listener, useCapture) {
    DOMDocument.removeListenerCount++;
  }},
  
  
  _getSortedSvgAttributes: {value: function() {
  var attrs = [];
  // Because we use Java for text measurement and do some internal conversions, we need to remove the font-size
  // ending before writing out the string
  if (this.attributes[CSSStyleDeclaration.FONT_SIZE]) {    
    var numVal = parseFloat(this.attributes[CSSStyleDeclaration.FONT_SIZE]);
    this.attributes[CSSStyleDeclaration.FONT_SIZE] = (isNaN(numVal) ? DOMUtils.parseFontSize(this.attributes[CSSStyleDeclaration.FONT_SIZE]) : numVal);
  }

  for (var element in this.attributes) {    
    if (element == CSSStyleDeclaration.FONT_FAMILY)
    {
      this.attributes[CSSStyleDeclaration.FONT_FAMILY] = this.attributes[CSSStyleDeclaration.FONT_FAMILY].replace(/"/g, "'");      
    }    
    attrs.push(element);    
  }
  return attrs.sort();
}},
  
  toString: {value: function() {
    var svg = '<' + this.nodeName;
    var sortedAttrs = this._getSortedSvgAttributes();
    for (var i = 0; i < sortedAttrs.length; i++) {
      var element = sortedAttrs[i];
      var value = this.attributes[element];
      if (value != null && typeof value != 'undefined') {
        if (typeof value == 'number')
          value = DOMUtils.roundDecimal(value);
        svg += ' ' + element + '="' + value + '"';
      }
    }
    svg += '>';
        
    for (var j = 0; j < this.childNodes.length; j++){
      svg += this.childNodes[j].toString();        
    }
    svg += '<\/' + this.nodeName + '>';
    return svg;
  }}  
});

function _setFamilyTree(node){
    var numChildren = node.childNodes.length;
    if (numChildren > 0) {
      for (var i = 0; i < numChildren; i++) {
        var child = node.childNodes[i];
        if (i == 0)
          child.previousSibling = null;
        if (i > 0)
          child.previousSibling = node.childNodes[i - 1];
        if (i < numChildren - 1)
          child.nextSibling = node.childNodes[i + 1];
        if (i == numChildren - 1)
          child.nextSibling = null;
      }
      firstChild = node.childNodes[0];
      lastChild = node.childNodes[numChildren - 1];
    } else {
      firstChild = null;
      lastChild = null;
    }
  };

//********************************************************************************************************************//
//---------------------------------------------------DOMDOCUMENT------------------------------------------------------//
//********************************************************************************************************************//

function DOMDocument (){
  this.documentElement = {};      
  this.nodeType = DOMNode.DOCUMENT_NODE;  
  this.ownerDocument = null; 
  this.byId = {};
  this.addListenerCount = 0;
  this.removeListenerCount = 0;
  Object.defineProperty(this, 'body', {
     get: function() {
      return namedHTMLChild(this.documentElement, 'body');
    }
  });
};



DOMDocument.prototype = Object.create(DOMNode.prototype, {  
  nodeName: { value: '#document'},
  nodeValue: {
    get: function() {
      return null;
    },
    set: function() {}
  },
  parentNode: { value: null },
  appendChild: { value: function(child) { 
    this.documentElement = child;
    child.parentNode = this;  
  }},
  createDocumentFragment: { value: function() {
     return new DOMDocumentFragment(this);
  }},
  createElement: { value : function(name) {
      var elem;        
      if (name.toUpperCase() == 'SVG') {            
        elem = new SVGElement(this, name);
        DOMUtils.setAttrNullNS(elem, 'xmlns', DOMUtils.SVG_NS);
        DOMUtils.setAttrNullNS(elem, 'xmlns:xlink', DOMUtils.XLINK_NS);
      }
      else{
        var elem = new DOMElement(this, name);
      }
 
      return elem;
  }},
  createElementNS: { value: function(namespace, name){
    if (namespace === DOMUtils.HTML_NS) {
      return new DOMElement(this, name);
    }
    else if (namespace === DOMUtils.SVG_NS) {            
      return new SVGElement(this, name);
    }
  
    return new DOMElement(this,name);
  }},
  getChildNodes: { value: function(name) {
    return this.documentElement;
  }},
  addId: {value: function(id, n){
    var val = this.byId[id];
    if (!val) {
      this.byId[id] = n;
    }
    else {      
       if (!Array.isArray(val)) {
        val = [val];
        this.byId[id] = val;
      }
      val.push(n);
      val.sort();
    }
  }},
  createTextNode: { value: function(text) {
    return new DOMTextNode(this, text);
  }},
  
  getElementById: { value: function(id) {  
    var n = this.byId[id];
    if (!n) return null;
    if (Array.isArray(n)) { // there was more than one element with this id
      return n[0];  // array is sorted in document order
    }
    return n;
  }},
  getElementsByTagName: {value: function(name){
    var elements = [];  
    this._getRecursiveElementsByTagName(this.documentElement, name, elements);  
    return elements;
  }},
  
  //jquery requirement
  addEventListener:{value : function (type, listener, capture) {} },
  
  _getRecursiveElementsByTagName: {value: function _getRecursiveElementsByTagName(parent, name, elements){
    if (parent instanceof DOMElement)
    {
      var children = parent.childNodes;
      for (var i = 0, n = children.length; i < n; i++)
      {
        var child = children[i];      
        if (child.nodeType == DOMNode.ELEMENT_NODE)
        {
          if(child.nodeName == name)
          {           
            elements.push(child.cloneNode(true));
            elements.push(child);
          }
         if (child.childNodes.length > 0)
         {
            _getRecursiveElementsByTagName(child, name, elements);
         }
        }
      }
    }
 }} 
});

function namedHTMLChild(parent, name) {
    if (parent) {
      var kids = parent.childNodes;
      for(var i = 0, n = kids.length; i < n; i++) {
        if (kids[i].nodeType === DOMNode.ELEMENT_NODE && kids[i].nodeName === name) {
          return kids[i];
        }
      }
    }
    return null;
  }
//********************************************************************************************************************//  
//------------------------------------DOMDOCUMENTFRAGMENT------------------------------------------- -----------------//
//********************************************************************************************************************//
function DOMDocumentFragment(doc) {
  this.nodeType = DOCUMENT_FRAGMENT_NODE;
  this.ownerDocument = doc;
  this.childNodes = [];
};

DOMDocumentFragment.prototype = Object.create(DOMElement.prototype, {
  nodeName: { value: '#document-fragment' },
  nodeValue: { 
    get: function() { 
      return null;
    },
    set: function() {}
  },
  
  // Utility methods
  clone: { value: function clone() {
      return new DocumentFragment(this.ownerDocument);
  }}
});  
//*******************************************************************************************************************//
//---------------------------------------------------DOMWindow-------------------------------------------------------//
//*******************************************************************************************************************//
function DOMWindow() {
  this.document = new DOMImplementation().createHTMLDocument();  
  this.document.defaultView = this; 
  this.location = new Location(this, "about:blank");
  navigator = { 
    appName: "node",
    appVersion: "0.1",
    platform: "JavaScript",
    userAgent: "dom"
  };  
};
// jquery requirement
DOMWindow.prototype.addEventListener = function addEventListener(type, listener, capture) {} ;  

function Location(window, href) {
  this.window = window;
  this.href = href;
} ;
// needed for jquery
DOMWindow.prototype.getComputedStyle = function(element){
    return element.style();
}; 
//*******************************************************************************************************************//
//---------------------------------------------------TEXTNODE--------------------------------------------------------//
//*******************************************************************************************************************//
function DOMTextNode(doc, data) {  
  this.nodeType = DOMNode.TEXT_NODE;
  this.ownerDocument = doc;
  this._data = data;  
};

var nodeValue = {
  get: function() { return this._data; },
  set: function(v) {
    if (v === this._data) return;
    this._data = v;    
  }
 };

DOMTextNode.prototype = Object.create(DOMNode.prototype, {
  nodeName: { value: "#text" },
  nodeValue: nodeValue,
  textContent: nodeValue,
  data: nodeValue,
  toString: function() {
    return this.nodeValue;
  },
  cloneNode: {value: function() {
    var clone = new DOMTextNode(this.ownerDocument, this.nodeValue);
    for (var name in this.attributes)
      clone.attributes[name] = this.attributes[name];  
    return clone;
  }},
  
  appendData: { value: function appendData(data) {
    this._data = this._data + data;
  }},
  
  length: { get: function() { return this._data.length; }},
  hasChildNodes: { value: function() { return false; }},
  childNodes: { get: function() {
    if (!this._childNodes) this._childNodes = [];
    return this._childNodes;
  }},
  
  toString: {value: function() {
    return this.nodeValue;
  }}
});
//********************************************************************************************************************//
//-----------------------------------------------------------Rectangle------------------------------------------------//
//********************************************************************************************************************//
function Rectangle(x, y, w, h){
  this.x = ((x === null || isNaN(x)) ? 0 : x);
  this.y = ((y === null || isNaN(y)) ? 0 : y);
  this.w = ((w === null || isNaN(w)) ? 0 : w);
  this.h = ((h === null || isNaN(h)) ? 0 : h);
}

Rectangle.create = function(ar)
{
  return new Rectangle(ar[0], ar[1], ar[2], ar[3]);
};

Rectangle.prototype.getUnion = function(rect) 
{
  var u = new Rectangle();

  if (rect && rect.w !== 0 && rect.h !== 0) {  // ignore zero size rect's
    if (this.w !== 0 && this.h !== 0) {        //  ..     ..   ..    ..
      var thisR = this.x + this.w;             // this right
      var thisB = this.y + this.h;             // this bottom
      var rectR = rect.x + rect.w;             // rect right
      var rectB = rect.y + rect.h;             // rect bottom

      var minx = Math.min(this.x, rect.x);
      var miny = Math.min(this.y, rect.y);

      u.w = (thisR < rectR) ? (rectR - minx) : (thisR - minx);
      u.h = (thisB > rectB) ? (thisB - miny) : (rectB - miny);
      u.x = minx;
      u.y = miny;
    }
    else {
      u.x = rect.x;
      u.y = rect.y;
      u.w = rect.w;
      u.h = rect.h;
    }
  }
  else {
    u.x = this.x;
    u.y = this.y;
    u.w = this.w;
    u.h = this.h;
  }

  return u;
};

//********************************************************************************************************************//
//-----------------------------------------------------------JavaDimensionUtils---------------------------------------//
//********************************************************************************************************************//
var JavaDimensionUtils = function(){};

JavaDimensionUtils.getDimensions = function(element){    
    var dims = JavaDimensionUtils._getDimensions(element);
    return {'x' : dims.x, 'y' : dims.y, 'width' : dims.w, 'height' : dims.h};
  };

JavaDimensionUtils._getDimensions = function(element){
  var isContainer = false;
  var children = element.childElementCount ? element.childElementCount : 0;
  var selfDims = JavaDimensionUtils._getDimensionsSelf(element);
  
  if (!selfDims)
  {    
    selfDims = new Rectangle();
    if (children > 0)
    {
      selfDims.x = Number.MAX_VALUE;
      selfDims.y = Number.MAX_VALUE;
    }
    isContainer = true;    
  }
  // if we have child elements find min/max values for x,y of their children
  var minX, minY, maxX, maxY;
  var bChild = false;

  for (var i = children - 1; i >= 0; i--) 
  {    
    var child = element.childNodes[i];
    var dims;    
    if (child.nodeType != DOMNode.ELEMENT_NODE)
    {   
      continue;
    }
    //ccsenter 12.09.2015
    //call _getDimensions to not mix the x,y,w,h with x,y,width,height 
    dims = JavaDimensionUtils._getDimensions(child);  
    
    if (!dims) 
    {      
      continue;
    }
    
    if (dims.w !== 0 && dims.h !== 0) {      
      // see footnote at end of function.  svg ignores      
      dims = JavaDimensionUtils._applyMatrix(child, dims);// ignores zero size and doesn't attempt to apply      
    }
    // the matrix, so translation is ignored.
    else if (i < children - 1) {
      // ignore zero-sized, except for one.
      continue;
    }
    
    if ((!bChild) || (dims.x < minX)) {
      minX = dims.x;
    }
    if ((!bChild) || (dims.y < minY)) {
      minY = dims.y;
    }
    if ((!bChild) || ((dims.x + dims.w) > maxX)) {
      maxX = dims.x + dims.w;
    }
    if ((!bChild) || ((dims.y + dims.h) > maxY)) {
      maxY = dims.y + dims.h;
    }
    bChild = true;// a child had been measured
  }
   if (bChild) {
    if (!isContainer) {
      // Create union if not a container. Containers have no initial dimension.
      minX = Math.min(minX, selfDims.x);
      minY = Math.min(minY, selfDims.y);
      maxX = Math.max(maxX, selfDims.x + selfDims.w);
      maxY = Math.max(maxY, selfDims.y + selfDims.h);
    }

    selfDims.x = minX;
    selfDims.y = minY;
    selfDims.w = maxX - minX;
    selfDims.h = maxY - minY;
  }
  
  return selfDims;
}
  
JavaDimensionUtils._getDimensionsSelf = function(element){    
    var selfDims;
    var r;
    var objName = element instanceof DOMElement ? element.nodeName.toLowerCase() : "";    
    
    if (objName == 'line') {
      var minX = Math.min(element.getAttributeNS(null, 'x1'), element.getAttributeNS(null, 'x2'));
      var minY = Math.min(element.getAttributeNS(null, 'y1'), element.getAttributeNS(null, 'y2'));
      var w = Math.abs(element.getAttributeNS(null, 'x1') - element.getAttributeNS(null, 'x2'));
      var h = Math.abs(element.getAttributeNS(null, 'y1') - element.getAttributeNS(null, 'y2'));

      selfDims = new Rectangle(minX, minY, w, h);
    }
    else if ((objName == 'rect') || (objName == 'image')) {
      selfDims = new Rectangle(element.getAttributeNS(null, 'x'), element.getAttributeNS(null, 'y'), element.getAttributeNS(null, 'width'), element.getAttributeNS(null, 'height'));
    }
    else if (objName == 'circle') {
      r = element.getAttributeNS(null, 'r');
      var w = r + r;
      selfDims = new Rectangle(element.getAttributeNS(null, 'cx') - r, element.getAttributeNS(null, 'cy') - r, w, w);
    }
    else if (objName == 'ellipse') {      
      var rx = element.getAttributeNS(null, 'rx');
      var ry = element.getAttributeNS(null, 'ry');
      var cx = element.getAttributeNS(null, 'cx');
      var cy = element.getAttributeNS(null, 'cy');
      selfDims = new Rectangle(cx - rx, cy - ry, 2 * rx, 2 * ry);
    }    
    else if (objName == 'path') {
      selfDims = JavaDimensionUtils.getPathDimensions(element.getCommands());
    }
    else if ((objName == 'polyline' ) || (objName == 'polygon')) {
      var points = element.getAttributeNS(null, 'points');
      //ccsenter: split the coordinates either around space and comma
      var arpoints = points.split('/[ ,]+/');  
      selfDims = JavaDimensionUtils.getPolygonDimensions(arpoints);
    }  
 
    else if (objName == 'text') {
      var map = JavaDimensionUtils._getCSSStyleMap(element);
      selfDims = JavaDimensionUtils._getTextFormattedDimensions(element, map);
    }
        
    return selfDims;
  };
  
JavaDimensionUtils._getTextFormattedDimensions = function(elem, styleMap) {
    var dims = new Rectangle();
    for (var i = 0; i < elem.childNodes.length; i++) {
        var childElem = elem.childNodes[i];        
        if (childElem.nodeName == '#text' && childElem.nodeValue) {
            dims = dims.getUnion(JavaDimensionUtils._getJavaTextDim(elem, childElem.nodeValue, styleMap));
        }
        else if (childElem.nodeName == 'tspan') {
            var tspanElem = childElem;
            var map = new Hashtable(styleMap);
            // update style map
            var val = tspanElem.getAttributeNS(null, 'font-family');
            if (val)
                map.put(TextAttribute.FAMILY, val.replace(/"/g, "'"));

            val = tspanElem.getAttributeNS(null, 'font-style');
            if (val == 'italic' || val == 'oblique')
                map.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);

            val = tspanElem.getAttributeNS(null, 'font-weight');
            if (val == 'regular')
                map.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
            else if (val == 'bold')
                map.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);

            // check children for text elements with updated style map
            var textDims = JavaDimensionUtils._getTextFormattedDimensions(childElem, map);
            
            var dy = tspanElem.getAttributeNS(null, 'dy');
            if (dy)
                textDims.y += (parseFloat(dy.substr(0, dy.indexOf('em'))) * textDims.h);
            var dx = tspanElem.getAttributeNS(null, 'dx');
            if (dx)
                textDims.x += (parseFloat(dx.substr(0, dx.indexOf('em'))) * textDims.h);

            dims = dims.getUnion(textDims);
        } // end if tspan
    }// end for
    return dims;
};
    
JavaDimensionUtils._getJavaTextDim = function(element, textStr, styleMap) {
        var font = new Font(styleMap);
        var bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        var g = bi.createGraphics();
        var metrics = g.getFontMetrics(font);
        var rect = metrics.getStringBounds(textStr, g);
        var textDims = new Rectangle(element.getAttributeNS(null, 'x'), element.getAttributeNS(null, 'y'), rect.getWidth(), rect.getHeight());

        // adjust dimensions for text horizontal and vertical alignment
        var anchor = element.getAttributeNS(null, 'text-anchor');
        var baseline = element.getAttributeNS(null, 'dominant-baseline');
        
        if (anchor === 'end')
            textDims.x -= textDims.w;
        else if (anchor === 'middle')
            textDims.x -= textDims.w / 2;

        if (baseline === 'bottom')
            textDims.y -= textDims.h;
        else if (baseline === 'middle')
            textDims.y -= textDims.h / 2;
        else if (baseline === 'auto')
            textDims.y = textDims.y - textDims.h + metrics.getDescent();

        return textDims;
    };   
  
JavaDimensionUtils._getCSSStyleMap = function(element) {
    var map = new Hashtable();
    var fontFamily = element.getAttributeNS(null, "font-family");
    if (fontFamily)
    {
      map.put(TextAttribute.FAMILY, fontFamily);
    }
    else
    {
      map.put(TextAttribute.FAMILY, 'Helvetica, Arial, sans-serif');
    }
    
    var fontSize = element.getAttributeNS(null, "font-size");
    if (fontSize)
    {
      map.put(TextAttribute.SIZE, fontFamily);
    }
    else
    {
      map.put(TextAttribute.FAMILY, '12');
    }
    
    var angleRad = element.getAttributeNS(null, "rotate");
    if (angleRad)
      map.put(TextAttribute.TRANSFORM, AffineTransform.getRotateInstance(angleRad));


    var val = element.getAttributeNS(null, 'font-style');
    if (val && val == 'italic' || val == 'oblique')
      map.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);

    val = element.getAttributeNS(null,'font-weight');
    if (val) {
      if (val == 'regular')
        map.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
      else if (val == 'bold')
        map.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
    }

    val = element.getAttributeNS(null,'text-decoration');
    if (val && val == 'underline')
      map.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

    
    return map;
  };
  
  
 JavaDimensionUtils.getPathDimensions = function(aCmds){
    if (! (aCmds && aCmds.length)) {
      return new Rectangle();
    }

  var len = aCmds.length;
  var c;
  var xSubPath, ySubPath;
  var bFirst = true;           // false after first command
  var bRel;                    // true if relative command
  var x, y, x2, y2, x3, y3;

  var minX = Number.MAX_VALUE;
  var maxX = Number.MIN_VALUE;
  var minY = Number.MAX_VALUE;
  var maxY = Number.MIN_VALUE;
  var aPos = [];
  var i, j, k;

  for (i = 0; i < len; i++) {
    bRel = false;
    var iMulti = 0;
    j = 0;

    c = aCmds[i];

    switch (c)
    {
      case 'm' : bRel = true;
      case 'M' : do {
        x = aCmds[i + 1];
        y = aCmds[i + 2];

        if (bFirst) {        // note if first is 'm', it is treated as absolute.
          bFirst = false;
        }
        else if (bRel) {
          x += xSubPath;
          y += ySubPath;
        }
        xSubPath = x;
        ySubPath = y;

        aPos[j++] = x;
        aPos[j++] = y;
        iMulti++;
        i += 2;
      } while (! isNaN(aCmds[i + 1]));
      break;

      case 'c' : bRel = true;
      case 'C' : do {
        x = aCmds[i + 1];
        y = aCmds[i + 2];
        x2 = aCmds[i + 3];
        y2 = aCmds[i + 4];
        x3 = aCmds[i + 5];
        y3 = aCmds[i + 6];

        if (bRel) {
          x += xSubPath;
          y += ySubPath;
          x2 += xSubPath;
          y2 += ySubPath;
          x3 += xSubPath;
          y3 += ySubPath;
        }
        xSubPath = x3;
        ySubPath = y3;

        aPos[j++] = x3;
        aPos[j++] = y3;
        iMulti++;
        i += 6;
      } while (! isNaN(aCmds[i + 1]));
      break;

      case 'q' : bRel = true;
      case 'Q' : do {
        x = aCmds[i + 1];
        y = aCmds[i + 2];
        x2 = aCmds[i + 3];
        y2 = aCmds[i + 4];
        if (bRel) {
          x += xSubPath;
          y += ySubPath;
          x2 += xSubPath;
          y2 += ySubPath;
        }
        xSubPath = x2;
        ySubPath = y2;

        aPos[j++] = x2;
        aPos[j++] = y2;
        iMulti++;
        i += 4;
      } while (! isNaN(aCmds[i + 1]));
      break;

      case 'l' : bRel = true;
      case 'L' :
        do {
          x = aCmds[i + 1];
          y = aCmds[i + 2];
          if (bRel) {
            x += xSubPath;
            y += ySubPath;
          }
          xSubPath = x;
          ySubPath = y;

          aPos[j++] = x;
          aPos[j++] = y;
          iMulti++;
          i += 2;
        } while (! isNaN(aCmds[i + 1]));
        break;

      case 'h' : bRel = true;
      case 'H' :
        do {
          x = aCmds[i + 1];
          if (bRel) {
            x += xSubPath;
          }
          xSubPath = x;

          aPos[j++] = x;
          aPos[j++] = ySubPath;
          iMulti++;
          i += 1;
        } while (! isNaN(aCmds[i + 1]));
        break;

      case 'v' : bRel = true;
      case 'V' :
        do {
          y = aCmds[i + 1];
          if (bRel) {
            y += ySubPath;
          }
          ySubPath = y;

          aPos[j++] = xSubPath;
          aPos[j++] = y;
          iMulti++;
          i += 1;
        } while (! isNaN(aCmds[i + 1]));
        break;

      case 'z' :
      case 'Z' : break;

      default : break;
    }                                    // end switch

    j = 0;
    for (k = 0; k < iMulti; k++) {
      x = aPos[j++];
      y = aPos[j++];
      minX = Math.min(minX, x);
      maxX = Math.max(maxX, x);
      minY = Math.min(minY, y);
      maxY = Math.max(maxY, y);
    }
  }

  return new Rectangle(minX, minY, Math.abs(maxX - minX), Math.abs(maxY - minY));
  };
  
  JavaDimensionUtils.getPolygonDimensions = function(aPts){
    if ((! aPts) || (aPts.length === 0)) {
      return new Rectangle();
    }

    var minX = Number.MAX_VALUE;
    var maxX = Number.MIN_VALUE;
    var minY = Number.MAX_VALUE;
    var maxY = Number.MIN_VALUE;

    var len = aPts.length;
    var x, y;
    for (var i = 0; i < len; i++) {
      x = aPts[i++];
      y = aPts[i];
      minX = Math.min(minX, x);
      maxX = Math.max(maxX, x);
      minY = Math.min(minY, y);
      maxY = Math.max(maxY, y);
    }

    return new Rectangle(minX, minY, Math.abs(maxX - minX), Math.abs(maxY - minY));
  };
  
  JavaDimensionUtils._applyMatrix = function(element, selfDims) {
    var mat = element.getAttribute('transform');
    

    if (mat && mat.indexOf('matrix') > 0 ){
      mat = mat.substring(mat.indexOf('(') + 1, mat.length - 2 );
    
      var matArr = mat.split(',');
      matArr.prototype.isIdentity = function()
      {
        return (this[0] == 1 && this[1] == 0 && this[2] == 0 && this[3] == 1 && this[4] == 0 && this[5] == 0);
      }
      matArr.prototype.transformPoint(px, py)
      {
        var newX = this[0] * px + this[1] * py + this[4] * 1;
        var newY = this[2] * px + this[3] * py + this[5] * 1;
        
        return {"x": newX, "y": newY};
      }
      
      if (matArr && !matrArr.isIdentity()) {
        
        var tl, tr, bl, br;

        tl = matArr.transformPoint(selfDims.x, selfDims.y);
        tr = matArr.transformPoint(selfDims.x + selfDims.w, selfDims.y);
        bl = matArr.transformPoint(selfDims.x, selfDims.y + selfDims.h);
        br = matArr.transformPoint(selfDims.x + selfDims.w, selfDims.y + selfDims.h);

        var xmin = Math.min(Math.min(tl.x, tr.x), Math.min(bl.x, br.x));
        var xmax = Math.max(Math.max(tl.x, tr.x), Math.max(bl.x, br.x));
        var ymin = Math.min(Math.min(tl.y, tr.y), Math.min(bl.y, br.y));
        var ymax = Math.max(Math.max(tl.y, tr.y), Math.max(bl.y, br.y));

        selfDims.x = xmin;
        selfDims.y = ymin;
        selfDims.w = xmax - xmin;
        selfDims.h = ymax - ymin;
      }     
    }
    return selfDims;
  };


//*******************************************************************************************************************//
//--------------------------------------------------------DOMSVGELEMENT----------------------------------------------//
//*******************************************************************************************************************//
function SVGElement() {
  DOMElement.apply(this, arguments);     
  DOMUtils.setAttrNullNS(this, 'xmlns', DOMUtils.SVG_NS);
  DOMUtils.setAttrNullNS(this, 'xmlns:xlink', DOMUtils.XLINK_NS);
};

SVGElement.prototype = Object.create(DOMElement.prototype, {
    style: { get: function() {
        if (!this._style)
          this._style = new CSSStyleDeclaration(this);
        return this._style;
    }},
    getBBox : {value : function() {             
      return JavaDimensionUtils.getDimensions(this);                    
    }}
  }
);

//********************************************************************************************************************//
//---------------------------------------------CSSStyleDeclaration----------------------------------------------------//
//********************************************************************************************************************//

function CSSStyleDeclaration(elt) {  
  this._element = elt;
}

// Utility function for parsing style declarations
// Pass in a string like "margin-left: 5px; border-style: solid"
// and this function returns an object like
// {"margin-left":"5px", "border-style":"solid"}
function parseStyles(style) {  
  var result = {};
  if (style && style.length > 0) {
    var splits = style.split(';');
    for (var i = 0; i < splits.length; i++) {
      var s = splits[i];
      if (s && s.length > 0) {        
        var colonIndex = s.indexOf(':');
        if (colonIndex > - 1) {
          var attrName = (s.substring(0, colonIndex)).trim();
          var attrVal = (s.substring(colonIndex + 1)).trim();

          if (attrName && attrName.length > 0 && attrVal && attrVal.length > 0) {

            //inline images with data url
            if (attrName == this.BACKGROUND_IMAGE && attrVal.indexOf('data:image/') >= 0) {
              attrVal = attrVal + ';' + splits[i + 1];
              i++;
            }

            result[attrName] = attrVal;
          }
        }
      }
    }
  }
  
  return result;
}

CSSStyleDeclaration.prototype = Object.create(Object.prototype, {

  // Return the parsed form of the element's style attribute.
  // If the element's style attribute has never been parsed
  // or if it has changed since the last parse, then reparse it
  // Note that the styles don't get parsed until they're actually needed
  _parsed: { get: function() {      
    if (!this._parsedStyles || this.cssText !== this._lastParsedText) {
      var text = this.cssText;
      this._parsedStyles = parseStyles(text);
      this._lastParsedText = text;      
    }
    
    return this._parsedStyles;
  }},

  
  cssText: {
    get: function() {          
      return this._element.getAttribute("style");
    },
    set: function(value) {    
      this._element.setAttribute("style", value);
    }
  },

  getPropertyValue: { value: function(property) {  
    return this._parsed[property.toLowerCase()];
  }}

});


  
// Background Properties
CSSStyleDeclaration.BACKGROUND = 'background';
CSSStyleDeclaration.BACKGROUND_COLOR = 'background-color';
CSSStyleDeclaration.BACKGROUND_IMAGE = 'background-image';
CSSStyleDeclaration.BACKGROUND_REPEAT = 'background-repeat';
CSSStyleDeclaration.BACKGROUND_POSITION = 'background-position';

// Border Properties
CSSStyleDeclaration.BORDER = 'border';
CSSStyleDeclaration.BORDER_TOP = 'border-top';
CSSStyleDeclaration.BORDER_BOTTOM = 'border-bottom';
CSSStyleDeclaration.BORDER_LEFT = 'border-left';
CSSStyleDeclaration.BORDER_RIGHT = 'border-right';

CSSStyleDeclaration.BORDER_WIDTH = 'border-width';
CSSStyleDeclaration.BORDER_TOP_WIDTH = 'border-top-width';
CSSStyleDeclaration.BORDER_BOTTOM_WIDTH = 'border-bottom-width';
CSSStyleDeclaration.BORDER_LEFT_WIDTH = 'border-left-width';
CSSStyleDeclaration.BORDER_RIGHT_WIDTH = 'border-right-width';

CSSStyleDeclaration.BORDER_COLOR = 'border-color';
CSSStyleDeclaration.BORDER_TOP_COLOR = 'border-top-color';
CSSStyleDeclaration.BORDER_BOTTOM_COLOR = 'border-bottom-color';
CSSStyleDeclaration.BORDER_LEFT_COLOR = 'border-left-color';
CSSStyleDeclaration.BORDER_RIGHT_COLOR = 'border-right-color';

CSSStyleDeclaration.BORDER_STYLE = 'border-style';


CSSStyleDeclaration.BORDER_RADIUS = 'border-radius';
CSSStyleDeclaration.BORDER_TOP_LEFT_RADIUS = 'border-top-left-radius';
CSSStyleDeclaration.BORDER_TOP_RIGHT_RADIUS = 'border-top-right-radius';
CSSStyleDeclaration.BORDER_BOTTOM_RIGHT_RADIUS = 'border-bottom-right-radius';
CSSStyleDeclaration.BORDER_BOTTOM_LEFT_RADIUS = 'border-bottom-left-radius';

// Margin Properties
CSSStyleDeclaration.MARGIN = 'margin';
CSSStyleDeclaration.MARGIN_TOP = 'margin-top';
CSSStyleDeclaration.MARGIN_BOTTOM = 'margin-bottom';
CSSStyleDeclaration.MARGIN_LEFT = 'margin-left';
CSSStyleDeclaration.MARGIN_RIGHT = 'margin-right';

// Padding Properties
CSSStyleDeclaration.PADDING = 'padding';
CSSStyleDeclaration.PADDING_TOP = 'padding-top';
CSSStyleDeclaration.PADDING_BOTTOM = 'padding-bottom';
CSSStyleDeclaration.PADDING_LEFT = 'padding-left';
CSSStyleDeclaration.PADDING_RIGHT = 'padding-right';

// Font Properties
CSSStyleDeclaration.COLOR = 'color';
CSSStyleDeclaration.FONT_FAMILY = 'font-family';
CSSStyleDeclaration.FONT_SIZE = 'font-size';
CSSStyleDeclaration.FONT_STYLE = 'font-style';
CSSStyleDeclaration.FONT_WEIGHT = 'font-weight';
CSSStyleDeclaration.TEXT_DECORATION = 'text-decoration';
CSSStyleDeclaration.TEXT_ALIGN = 'text-align';

//Bug 13842185 - DIFFICULT TO SIZE PGLS WHEN USING PADDING AND BORDERS
//value for box-sizing
CSSStyleDeclaration.BORDER_BOX = 'border-box';
CSSStyleDeclaration.CONTENT_BOX = 'content-box';

// Size Properties
CSSStyleDeclaration.HEIGHT = 'height';
CSSStyleDeclaration.WIDTH = 'width';

//value for background-image
CSSStyleDeclaration.NONE = 'none';

//values for background-repeat
CSSStyleDeclaration.NO_REPEAT = 'no-repeat';
CSSStyleDeclaration.REPEAT = 'repeat';
CSSStyleDeclaration.REPEAT_X = 'repeat-x';
CSSStyleDeclaration.REPEAT_Y = 'repeat-y';

//value for margin
CSSStyleDeclaration.AUTO = 'auto';
CSSStyleDeclaration.AUTO_MARGIN = '8';

//HV recognizes these for "gradient" or "solid"
CSSStyleDeclaration.BORDER_TYPE = 'border-type';
CSSStyleDeclaration.FILL_TYPE = 'fill-type';  

//*****************************************************************************************************************//
//---------------------------------------------------DOMUtils------------------------------------------------------//
//*****************************************************************************************************************//

var DOMUtils = new Object();


/** @const **/
DOMUtils.SVG_NS = 'http://www.w3.org/2000/svg';
/** @const **/
DOMUtils.XLINK_NS = 'http://www.w3.org/1999/xlink';
DOMUtils.HTML_NS =  'http://www.w3.org/1999/xhtml';
DOMUtils.NAMED_FONTS_SIZES = {
    "xx-small": 8,
    "x-small":  9,
    "small":    10,
    "medium":   12,
    "large":    14,
    "x-large":  16,
    "xx-large": 18,
    "smaller":  10,
    "larger":   14
};
  

/**
 * Wrapper for setAttributeNS method.  When the value of the attribute matches the default value, the DOM will not be
 * updated unless the attribute has already been set to a different value.
 * @param {object} elem DOM element
 * @param {string} name the name of the attribute.
 * @param {string} value The value of the attribute.
 * @param {string=} defaultValue The default value of the attribute, which can be provided to optimize performance.
 */
DOMUtils.setAttrNullNS = function(elem, name, value, defaultValue) {
  DOMUtils.setAttrNS(elem, null, name, value, defaultValue);
};

/**
 * Wrapper for setAttributeNS method.  When the value of the attribute matches the default value, the DOM will not be
 * updated unless the attribute has already been set to a different value.
 * @param {object} elem DOM element
 * @param {string} namespace The namespace of the attribute.
 * @param {string} name the name of the attribute.
 * @param {string} value The value of the attribute.
 * @param {string=} defaultValue The default value of the attribute, which can be provided to optimize performance.
 */
DOMUtils.setAttrNS = function(elem, namespace, name, value, defaultValue) {
  // Note: We're not strict about value or defaultValue being String types, since browser implementations are not. The
  //       code in this function should always assume that users may pass objects that would be converted to Strings.

  // If defaultValue specified and value matches default, optimize the DOM calls
  // removeAttrNS fails for IE9 and IE10 and x,y attributes of text elements in Chrome version 34, so exclude it here. (when not in test environment)
  if (defaultValue != null && value == defaultValue) {
    if (DOMUtils.hasAttrNS(elem, namespace, name)) {      
        DOMUtils.removeAttrNS(elem, namespace, name);
    }
    return;
  }

  // Otherwise set the attribute
  elem.setAttributeNS(namespace, name, value);
};

/**
 * Wrapper for hasAttributeNS method
 * @param {object} elem DOM element
 * @param {string} namespace The namespace of the attribute.
 * @param {string} name the name of the attribute.
 * @return {boolean} true if the element has the specified attribute.
 */
DOMUtils.hasAttrNS = function(elem, namespace, name) {
  return elem.hasAttributeNS(namespace, name);
};

/**
 * Remove an attribute from a DOM element.
 * @param {object} elem DOM element
 * @param {string} namespace Namespace to use
 * @param {string} name Attribute name to remove
 */
DOMUtils.removeAttrNS = function(elem, namespace, name) {
  //  This might be an over-optimization, but we know that hasAttrNS is cheap
  if (DOMUtils.hasAttrNS(elem, namespace, name))
    elem.removeAttributeNS(namespace, name);
};
DOMUtils.roundDecimal = function(value) {  
    return value;
};

/**
 * Wrapper for getAttributeNS method
 * @param {object} elem DOM element
 * @param {string} name Attribute name to get
 * @return {string} Value associated with given name
 */
DOMUtils.getAttrNullNS = function(elem, name) {
  return elem.getAttributeNS(null, name);
};

DOMUtils.parseFontSize = function(fontVal){
  var val = fontVal.trim();
  val = val.toLowerCase();
  if (NAMED_FONTS_SIZES[val])
  {
    return NAMED_FONTS_SIZES[val];
  }
  if (val.charAt(val.length - 1) == '%')
  {
    return val.substr(0, length - 1);
  }
  
  var last2 = val.substr(length -2);
  if (last2 == 'in' || last2 == 'cm' ||
  last2 == 'mm' ||
  last2 == 'pt' ||
  last2 == 'pc' ||
  last2 == 'em' ||
  last2 == 'ex' ||
  last2 == 'px')
  {
    return val.substr(0, length - 2);
  }
  
  return val;
}

//*******************************************************************************************************************//
//---------------------------------------------------DOMImplementation------------------------------------------------------------//
//*******************************************************************************************************************//
function DOMImplementation() {}

DOMImplementation.prototype = {
  /*
   * creates the scheleton html with a body and the top level div tag hosting the
   * generated SVG.
   */
  createHTMLDocument: function createHTMLDocument() {    
    var d = new DOMDocument();    
    var html = d.createElement('html');
    d.appendChild(html);    
    var body = d.createElement('body');        
    html.appendChild(body);    
    var div = d.createElement('div');
    div.setAttributeNS(null, "id", "chart");    
    body.appendChild(div);     
    return d;    
  }
};
//main entry to create the window with the document.
function createWindow(){
  return new DOMWindow();
};

if(typeof require == 'function'){	
	exports.createWindow = createWindow;
}
var clearTimeout,    
    clearInterval;
    exports.clearTimeout = function(){};
    exports.clearInterval = function(){};    
});
