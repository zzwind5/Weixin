(function ($) {

    // some helper methods
    var methods = {
        getSerializedTags: function () {
            var currentTags = [];
            $(this).find("li.tagItem").each(function (i, e) {
                currentTags.push($(e).text());
            });
            return currentTags.join(',');
        },
        getTags: function () {
            var currentTags = [];
            $(this).find("li.tagItem").each(function (i, e) {
                currentTags.push($(e).text());
            });
            return currentTags;
        },
        version: function () {
            return "1.3.0";
        }
    };
    var tagMap={};

    // main plugin initialization
    $.fn.tagHandler = function (options) {
        if (typeof(options) == 'object' || typeof(options) == 'undefined') {

            var opts = $.extend({}, $.fn.tagHandler.defaults, options);
            debug($(this), opts);

            // processes each specified object and adds a tag handler to each
            return this.each(function () {

                // checks to make sure the supplied element is a <ul>
                if (!$(this).is('ul')) {
                    return true;
                }

                // caches the container to avoid scope issues.
                var tagContainer = this;
                var tagContainerObject = $(tagContainer);

                // adds an id to the tagContainer in case it doesn't have one
                if (!tagContainer.id) {
                    var d = new Date();
                    tagContainer.id = d.getTime();
                }

                // wraps the <ul> element in a div mainly for use in positioning
                // the save button and loader image.
                tagContainerObject.wrap('<div class="' + opts.className + '" />');

                // adds the the tag class to the tagContainer and creates the tag
                // input field
                tagContainerObject.addClass(opts.className + "Container");
                if (opts.allowEdit) {
                    tagContainerObject.html('<li class="tagInput"><input class="tagInputField" type="text" placeholder="输入号码回车" /></li>');
                }
                var inputField = tagContainerObject.find(".tagInputField");

                // master tag list, will contain 3 arrays of tags
                var tags = [];
                tags.availableTags = [];
                tags.originalTags = [];
                tags.assignedTags = [];
                
                //==== Chris beigin
                tagMap[this.id]={};
                tagMap[this.id].tags=tags;
                tagMap[this.id].inputField=inputField;
                tagMap[this.id].sorttags=opts.sorttags;
                tagMap[this.id]. autocomplete=opts.autocomplete;
                //====Chris end

                // adds save/loader divs to the tagContainer if needed
                if (opts.updateURL !== '') {
                    if (!opts.autoUpdate) {
                        $("<div />").attr({ id: tagContainer.id + "_save", title: "Save Tags" }).addClass("tagUpdate").click(function () {
                            saveTags(tags, opts, tagContainer.id);
                        }).appendTo(tagContainerObject.parent());
                    }
                    $("<div />").attr({ id: tagContainer.id + "_loader", title: "Saving Tags" }).addClass("tagLoader").appendTo(tagContainerObject.parent());
                }

                // adds autocomplete functionality for the tag names
                if (opts.autocomplete && typeof($.fn.autocomplete) == 'function' && opts.initLoad) {
                    $(inputField).autocomplete({
                        source: tags.availableTags,
                        select: function (event, ui) {
                            var $el = $(this);
                            if (!checkTag($.trim(ui.item.value), tags.assignedTags)) {
                                if (opts.maxTags > 0 && tags.assignedTags.length >= opts.maxTags) {
                                    alert('Maximum tags allowed: ' + opts.maxTags);
                                }
                                else {
                                    var newTag = $.trim(ui.item.value);
                                    var rc = 1;
                                    if (typeof(opts.onAdd) == "function") {
                                        rc = opts.onAdd.call(this, newTag);
                                    }
                                    if (rc || typeof(rc) == "undefined") {
                                        tags = addTag(this, newTag, tags, opts.sortTags);
                                        if (opts.updateURL !== '' && opts.autoUpdate) {
                                            saveTags(tags, opts, tagContainer.id);
                                        }
                                        $(inputField).autocomplete("option", "source", tags.availableTags);
                                        if (typeof(opts.afterAdd) == "function") {
                                            opts.afterAdd.call(this, newTag);
                                        }
                                    }
                                }
                                $el.focus();
                            }
                            $el.val("");
                            return false;
                        },
                        minLength: opts.minChars
                    });
                }
                // Make an AJAX request to get the list of tags based on typed data
                else if (opts.autocomplete && typeof($.fn.autocomplete) == 'function') {
                    $(inputField).autocomplete({
                        source: function (request, response) {
                            opts.getData[opts.queryname] = request.term;
                            var lastXhr = $.getJSON(opts.getURL, opts.getData, function (data, status, xhr) {
                                response(data.availableTags);
                            });
                        },
                        select: function (event, ui) {
                            var $el = $(this);
                            if (!checkTag($.trim(ui.item.value), tags.assignedTags)) {
                                if (opts.maxTags > 0 && tags.assignedTags.length >= opts.maxTags) {
                                    alert('Maximum tags allowed: ' + opts.maxTags);
                                }
                                else {
                                    var newTag = $.trim(ui.item.value);
                                    var rc = 1;
                                    if (typeof(opts.onAdd) == "function") {
                                        opts.onAdd.call(this, newTag);
                                    }
                                    if (rc || typeof(rc) == "undefined") {
                                        tags = addTag(this, $.trim(ui.item.value), tags, opts.sortTags);
                                        if (opts.updateURL !== '' && opts.autoUpdate) {
                                            saveTags(tags, opts, tagContainer.id);
                                        }
                                        if (typeof(opts.afterAdd) == "function") {
                                            opts.afterAdd.call(this, newTag);
                                        }
                                    }
                                }
                                $el.focus();
                            }
                            $el.val('');
                            return false;
                        },
                        minLength: opts.minChars
                    });
                }

                // initializes the tag lists
                // tag lists will be pulled from a URL
                if (opts.getURL !== '' && opts.initLoad) {
                    $.ajax({
                        url: opts.getURL,
                        cache: false,
                        data: opts.getData,
                        dataType: 'json',
                        success: function (data, text, xhr) {
                            if (data.availableTags.length) {
                                tags.availableTags = data.availableTags.slice();
                                tags.originalTags = tags.availableTags.slice();
                            }
                            if (opts.sortTags) {
                                tags = sortTags(tags);
                            }
                            if (data.assignedTags.length) {
                                tags.assignedTags = data.assignedTags.slice();
                                if (opts.sortTags) {
                                    tags = sortTags(tags);
                                }

                                tags = addAssignedTags(opts, tags, inputField, tagContainer);

                            }
                            if (opts.autocomplete && typeof($.fn.autocomplete) == 'function' && opts.allowEdit) {
                                $(inputField).autocomplete("option", "source", tags.availableTags);
                            }
                        },
                        error: function (xhr, text, error) {
                            debug(xhr, text, error);
                            alert(opts.msgError);
                        }
                    });

                    // show assigned tags only if we load the data as we write
                }
                else if (opts.getURL !== '') {

                    tags.assignedTags = opts.assignedTags.slice();
                    if (opts.sortTags) {
                        tags = sortTags(tags);
                    }

                    tags = addAssignedTags(opts, tags, inputField, tagContainer);

                    // or load the lists of tags   
                }
                else {

                    if (opts.availableTags.length) {
                        tags.availableTags = opts.availableTags.slice();
                        tags.originalTags = tags.availableTags.slice();
                    }
                    if (opts.sortTags) {
                        tags = sortTags(tags);
                    }
                    if (opts.assignedTags.length) {
                        tags.assignedTags = opts.assignedTags.slice();
                        if (opts.sortTags) {
                            tags = sortTags(tags);
                        }

                        tags = addAssignedTags(opts, tags, inputField, tagContainer);
                    }
                    if (opts.autocomplete && typeof($.fn.autocomplete) == 'function' && opts.allowEdit && opts.initLoad) {
                        $(inputField).autocomplete("option", "source", tags.availableTags);
                    }
                }

                // all tag editing functionality only activated if set in options
                if (opts.allowEdit) {
                    // delegates a click event function to all future <li> elements with
                    // the tagItem class that will remove the tag upon click
                    tagContainerObject.delegate("li.tagItem", "click", function () {
                        var $el = $(this);
                        var rc = 1;

                        if (typeof(opts.onDelete) == "function") {
                            rc = opts.onDelete.call(this, $.trim($el.text()));
                        }

                        if (rc) {
                            tags = removeTag($el, tags, opts.sortTags);
                            if (opts.updateURL !== '' && opts.autoUpdate) {
                                saveTags(tags, opts, tagContainer.id);
                            }
                        }

                        if (typeof(opts.afterDelete) == "function") {
                            opts.afterDelete.call(this, $.trim($el.text()));
                        }

                        if (opts.autocomplete && typeof($.fn.autocomplete) == 'function' && opts.initLoad) {
                            $(inputField).autocomplete("option", "source", tags.availableTags);
                        }
                    });

                    // checks the keypress event for enter or comma, and adds a new tag
                    // when either of those keys are pressed
                    $(inputField).keypress(function (e) {
                        var $el = $(this);
                        if (e.which === 13 || e.which === 44 || e.which === opts.delimiter.charCodeAt(0)) {
                            e.preventDefault();
                            if ($el.val() !== "" && !checkTag($.trim($el.val()), tags.assignedTags)) {

                                // check if the tag is in availableTags
                                if (!opts.allowAdd && !checkTag($.trim($el.val()), tags.availableTags)) {
                                    alert(opts.msgNoNewTag);
                                    return;
                                }

                                if (opts.maxTags > 0 && tags.assignedTags.length >= opts.maxTags) {
                                    alert('Maximum tags allowed: ' + opts.maxTags);
                                }
                                else {
                                    var newTag = $.trim($el.val());

                                    // allow addition onAdd return code to control whether
                                    // addition is allowed to go through
                                    var rc = 1;
                                    if (typeof(opts.onAdd) == "function") {
                                        rc = opts.onAdd.call(this, newTag);
                                    }

                                    if (rc || typeof(rc) == "undefined") {
                                        tags = addTag(this, newTag, tags, opts.sorttags);
                                        if (opts.updateURL !== '' && opts.autoUpdate) {
                                            saveTags(tags, opts, tagContainer.id);
                                        }
                                        if (opts.autocomplete && typeof($.fn.autocomplete) == 'function' && opts.initload) {
                                            $(inputField).autocomplete("option", "source", tags.availableTags);
                                        }
                                        if (typeof(opts.afterAdd) == "function") {
                                            opts.afterAdd.call(this, newTag);
                                        }
                                    }
                                }
                                $el.val("");
                                $el.focus();
                            }
                        }
                    });

                    // checks the keydown event for the backspace key as checking the
                    // keypress event doesn't work in IE
                    $(inputField).keydown(function (e) {
                        var $el = $(this);
                        if (e.which === 8 && $el.val() === "") {
                            var deleted_tag = tagContainerObject.find(".tagItem:last").text();
                            if (typeof(opts.onDelete) == "function") {
                                opts.onDelete.call(this, $.trim(deleted_tag));
                            }
                            tags = removeTag(tagContainerObject.find(".tagItem:last"), tags, opts.sortTags);
                            if (opts.updateURL !== '' && opts.autoUpdate) {
                                saveTags(tags, opts, tagContainer.id);
                            }
                            if (typeof(opts.afterDelete) == "function") {
                                opts.afterDelete.call(this, $.trim(deleted_tag));
                            }
                            if (opts.autocomplete && typeof($.fn.autocomplete) == 'function' && opts.initLoad) {
                                $(inputField).autocomplete("option", "source", tags.availableTags);
                            }
                            $el.focus();
                        }
                    });

                    // sets the input field to show the autocomplete list on focus
                    // when there is no value
                    $(inputField).focus(function () {
                        if ($(inputField).val() === '' && opts.autocomplete && typeof($.fn.autocomplete) == 'function' && opts.initLoad) {
                            $(inputField).autocomplete("search", "");
                        }
                    });

                    // sets the focus to the input field whenever the user clicks
                    // anywhere on the tagContainer -- since the input field by default
                    // has no border it isn't obvious where to click to access it
                    tagContainerObject.click(function () {
                        $(inputField).focus();
                    });
                }
                this.getTags = function () {
                    return tags.assignedTags;
                };
                return 1;
            });
        }
        else if (typeof(options) == "string" && methods[options]) {
            return methods[options].apply(this, Array.prototype.slice.call(arguments, 1));
        }
    };
    
    //====Chris begin
    $.fn.addTag=function(tag){
    	 if(typeof(tag) == "string"){
	    	 var me= tagMap[this.get(0).id];
	    	 var tags=me.tags;
	    	 var inputField = me.inputField;
	         var newTag = $.trim(tag);
	         var sorttags = me.sorttags;
	         if(!newTag || checkTag(newTag, tags.assignedTags)) return this;
	         tags = addTag(inputField, newTag, tags, sorttags);
	         if (me.autocomplete && typeof($.fn.autocomplete) == 'function') {
	             $(inputField).autocomplete("option", "source", tags.availableTags);
	         }
    	 }else if (typeof(tag) == 'object' ){
    		 var me= tagMap[this.get(0).id];
	    	 var tags=me.tags;
	    	 var inputField = me.inputField;
	         var newTag = $.trim(tag.tag);
	         var sorttags = me.sorttags;
	        // if(!newTag || checkTag(newTag, tags.assignedTags)) return this;
	         tags = addObjectTag(inputField, tag, tags, sorttags);
    	 }
         return this;
    };
    //type:person 或 空
   $.fn.getPersonTags=function(type){
	   var persons=[];
    	$(this).find("li.tagItem").each(function (i, e) {
            var tag= this.tag;
            if($(this).data('type')=='person'){
		   		persons.push(tag);
	   		}else if($(this).data('type')=='dept'){
	   			if(type=='type:person'){
	   				for ( var int = 0; int < tag.persons.length; int++) {
		   				persons.push(tag.persons[int]);
					}
	   			}else{
	   				persons.push(tag);
	   			}
	   		}else{
	   			var mobile= $(this).text();
	   			persons.push({name:mobile,type:'mobile',mobile:mobile});
	   		}
        });
	   return persons;
   };
    $.fn.removeTag=function(tagObject){
	   	 if (typeof(tagObject) == 'object' ){
	   		 var tagItemId='#'+tagObject.type +tagObject.id;
	   		$(this).find(tagItemId).remove();
	   	 }
        return this;
   };
    
    /*var tagObject={
    	  type:'group', //or group or member
    	  id:'',
    	  name:''
    	  mobile:'',
    	  group:'',
    	  type:'person',
    	  scope:false,
    	  persons:[]
    }*/
    function addObjectTag(tagField, tagObject, tags, sort) {
    	var tag=tagObject.name;
        tags.assignedTags.push(tag);
        var targItem =$("<li />").addClass("tagItem").text(tag).attr("id",tagObject.type +tagObject.id);
        targItem.get(0).tag=tagObject;
        targItem.data('type',tagObject.type);
        targItem.insertBefore($(tagField).parent());
        if (sort) {
            tags = sortTags(tags);
        }
        return tags;
    }
    //====Chris end

    // plugin option defaults
    $.fn.tagHandler.defaults = {
        allowEdit: true,
        allowAdd: true,
        assignedTags: [],
        autocomplete: false,
        autoUpdate: false,
        availableTags: [],
        className: 'tagHandler',
        debug: false,
        delimiter: '',
        getData: {},
        getURL: '',
        initLoad: true,
        maxTags: 0,
        minChars: 0,
        msgNoNewTag: "You don't have permission to create a new tag.",
        msgError: "There was an error getting the tag list.",
        onAdd: {},
        onDelete: {},
        afterAdd: {},
        afterDelete: {},
        queryname: 'q',
        sortTags: true,
        updateData: {},
        updateURL: ''
    };

    // checks to to see if a tag is already found in a list of tags
    function checkTag(value, tags) {
        var check = false;
        jQuery.each(tags, function (i, e) {
            if (e === value) {
                check = true;
                return false;
            }
        });
        return check;
    }

    // removes a tag from a tag list
    function removeTagFromList(value, tags) {
        jQuery.each(tags, function (i, e) {
            if (e === value) {
                tags.splice(i, 1);
            }
        });

        return tags;
    }

    // adds a tag to the tag box and the assignedTags list
    function addTag(tagField, value, tags, sort) {
        tags.assignedTags.push(value);
        tags.availableTags = removeTagFromList(value, tags.availableTags);
        $("<li />").addClass("tagItem").text(value).insertBefore($(tagField).parent());

        if (sort) {
            tags = sortTags(tags);
        }
        return tags;
    }


    // removes a tag from the tag box and the assignedTags list
    function removeTag(tag, tags, sort) {
        var value = $(tag).text();
        tags.assignedTags = removeTagFromList(value, tags.assignedTags);
        if (checkTag(value, tags.originalTags)) {
            tags.availableTags.push(value);
        }
        $(tag).remove();
        //====Chris begin
        
        //====Chris end

        if (sort) {
            tags = sortTags(tags);
        }
        return tags;
    }

    // sorts each of the sets of tags
    function sortTags(tags) {
        tags.availableTags = tags.availableTags.sort();
        tags.assignedTags = tags.assignedTags.sort();
        tags.originalTags = tags.originalTags.sort();

        return tags;
    }

    // saves the tags to the server via ajax
    function saveTags(tags, opts, tcID) {
        var sendData = {
            tags: tags.assignedTags
        };
        $.extend(sendData, opts.updateData);
        $.ajax({
            type: 'POST',
            url: opts.updateURL,
            cache: false,
            data: sendData,
            dataType: 'json',
            beforeSend: function () {
                if ($("#" + tcID + "_save").length) {
                    $("#" + tcID + "_save").fadeOut(200,
                    function () {
                        $("#" + tcID + "_loader").fadeIn(200);
                    });
                }
                else {
                    $("#" + tcID + "_loader").fadeIn(200);
                }
            },
            complete: function () {
                $("#" + tcID + "_loader").fadeOut(200,
                function () {
                    if ($("#" + tcID + "_save").length) {
                        $("#" + tcID + "_save").fadeIn(200);
                    }
                });
            }
        });
    }

    // adds any already assigned tags to the tag box
    function addAssignedTags(opts, tags, inputField, tagContainer) {
        $(tags.assignedTags).each(function (i, e) {
            if (opts.allowEdit) {
                $("<li />").addClass("tagItem").text(e).insertBefore($(inputField).parent());
            }
            else {
                $("<li />").addClass("tagItem").css("cursor", "default").text(e).appendTo($(tagContainer));
            }
            tags.availableTags = removeTagFromList(e, tags.availableTags);
        });

        return tags;
    }

    // some debugging information
    function debug(tagContainer, options) {
        if (options.debug && window.console && window.console.log) {
            window.console.log(tagContainer);
            window.console.log(options);
            window.console.log($.fn.tagHandler.defaults);
        }
    }

})(jQuery);
