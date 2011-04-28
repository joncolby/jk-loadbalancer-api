class EastwoodChartTagLib {

    static namespace = "chart"

	def baseChart(attrs) { 
		def baseString = '' 
		
        if (attrs.size != null) {
			baseString += 'chs='+attrs.size[0]+'x'+attrs.size[1]
        } else {
            baseString += 'chs=400x200'
        }

		if (attrs.dataType == null || attrs.dataType == 'simple') {
			baseString += '&chd=s:' + encodeAsSimpleChartData(attrs.data)
		} else if (attrs.dataType == 'text') {
			baseString += '&chd=t:' + encodeAsTextChartData(attrs.data)
		} else if (attrs.dataType == 'extended') {
			baseString += '&chd=e:' + encodeAsExtendedChartData(attrs.data)
		}
		
		if (attrs.axes != null) {
			baseString += '&chxt=' + attrs.axes
		}
		
		// takes a map in the form
		// [0:[Jan,Feb,Mar], 1:[0,100]...]
		if (attrs.axesLabels != null) {
			baseString += '&chxl='
			baseString += processAxesLabels(attrs.axesLabels)
		}
		
		// takes a map in the form
		// [0:[Jan,Feb,Mar], 1:[0,100]...]
		if (attrs.axesPositions != null) {
			baseString += '&chxp='
			baseString += processAxesPositions(attrs.axesPositions)
		}
		
		// takes a map in the form
		// [0:[Jan,Feb,Mar], 1:[0,100]...]
		if (attrs.axesRanges != null) {
			baseString += '&chxr='
			baseString += processAxesPositions(attrs.axesRanges)
		}
		
		// takes a map in the form
		// [0:[Jan,Feb,Mar], 1:[0,100]...]
		if (attrs.axesStyles != null) {
			baseString += '&chxs='
			baseString += processAxesPositions(attrs.axesStyles)
		}
		
		if (attrs.colors != null) {
			baseString += '&chco='
			def colors = ''
			for(color in attrs.colors) {
				colors += color + ','
			}
			baseString += colors.substring(0,colors.length()-1)
		}
		
		if (attrs.fill != null) {
			baseString += '&chf='
			if (attrs.fill instanceof ArrayList)
				baseString += attrs.fill[0] + '|' + attrs.fill[1]
			else baseString += attrs.fill
		}
		
		// takes an ArrayList of the titles
		// Valid for all types except pie charts
		if (attrs.legend != null) {
			baseString += '&chdl='
			def legendLabels = ''
			for(label in attrs.legend) {
				legendLabels += label + '|'
			}
			baseString += legendLabels.substring(0,legendLabels.length()-1)
		}
		// chart title and attributes
		if (attrs.title != null)
			baseString += '&chtt='+attrs.title.encodeAsHTML()
		// takes an array of length 2 [color, font size]
		if (attrs.titleAttrs != null) {
			println attrs.titleAttrs
			baseString += "&chts=${attrs.titleAttrs[0]},${attrs.titleAttrs[1]}"
		}
		return baseString
	}

	def processAxesLabels(params) {
		def labelString = ''
		for (axis in params.keySet()) {
			labelString += "${axis}:|"
			for (value in params[axis]) {
				labelString += "${value}|"
			}
		}
		return labelString.substring(0,labelString.length()-1)
	}
	
	String processAxesPositions(params) {
		String posString = ''
		for (axis in params.keySet()) {
			posString += "${axis},"
			for (value in params[axis]) {
				posString += "${value},"
			}
			posString = posString.substring(0,posString.length()-1) + '|'
		}
		return posString.substring(0,posString.length()-1)
	}

	String parseShapeRangeFill(params) {
		def text = ''
		if (params.shapeRangeFill != null) {
			text += '&chm='
			def shapeText = ''
			for (shape in params.shapeRangeFill) {
				for (val in shape) {
					shapeText += (val + ',')
				}
				shapeText = shapeText.substring(0, shapeText.length() - 1) + '|'
			}
			shapeText = shapeText.substring(0, shapeText.length() - 1)
			text += shapeText
		}
		return text
	}

    def encodeAsSimpleChartData(str) {
        def result 
        if (str[0] instanceof ArrayList) {
            def encodedData = []
                for (array in str) {
                    encodedData.add(encodeAsSimpleChartData(array))
                }
            def multipleSets = ''
                for (set in encodedData) multipleSets += set + ','
                    result = multipleSets.substring(0,multipleSets.length()-1)
        } else {
            def key = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'
                def maxValue = Collections.max(str)
                def data = ''
                for (i in str) {
                    def currentValue = i
                        if (currentValue != null && currentValue >= 0) {
                            data += key.charAt(Math.round((key.size() - 1) * currentValue / maxValue).intValue())
                        } else {
                            data += '_'
                        }
                }
            result = data
        }
        return result
    }

    def encodeAsTextChartData(str) {
        def result 
        if (str[0] instanceof ArrayList) {
            def encodedData = []
                for (array in str) {
                    encodedData.add(encodeAsTextChartData(array))
                }
            def multipleSets = ''
                for (set in encodedData) multipleSets += set + '|'
                    result = multipleSets.substring(0,multipleSets.length()-1)
        } else {
            def key = 0.0..100.0
                def maxValue = Collections.max(str)
                def data = ''
                for (i in str) {
                    def currentValue = i
                        if (currentValue != null && currentValue >= 0) {
                            data += key.get(Math.round((key.size() - 1) * currentValue / maxValue).intValue())+','
                        } else {
                            data += '-1'
                        }
                }
            result = data.substring(0,data.length()-1)
        }
        return result
    }

    def encodeAsExtendedChartData(str) {
        def values = []
        if (str[0] instanceof ArrayList) {
            def encodedData = []
                for (array in str) {
                    encodedData.add(encodeAsExtendedChartData(array))
                }
            def multipleSets = ''
                for (set in encodedData) multipleSets += set + ','
                    return multipleSets.substring(0, multipleSets.length()-1)
        } else {
            //Compose list of keys
            def key = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-."
                if (values.size() == 0) {
                    for (i in key)
                        for (k in key)
                            values += (i+k)
                }
            def maxValue = Collections.max(str)
                def data = ''
                for (i in str) {
                    def currentValue = i
                        if (currentValue != null && currentValue >= 0) {
                            data += values[(Math.round((values.size() - 1) * currentValue / maxValue).intValue())]
                        } else {
                            data += '_'
                        }
                }
            return data.substring(0,data.length()-1)
        }
    }

	// Attributes:
	// size	- ArrayList
	// type - simple, text, or extended
	def pie = { attrs, body ->
        String url = grailsAttributes.getApplicationUri(request)
		out <<  "<img src=\"${url}chart?"

		out << baseChart(attrs)
		
		if (attrs.labels != null) {
			out << '&chl='
			def labels = ''
			for(label in attrs.labels) {
				labels += label + '|'
			}
			out << labels.substring(0,labels.length()-1)
		}
		
		if (attrs.type != '3d')
			out << '&cht=p'
		else out << '&cht=p3'
		
		out << '\" />'
	}
	
	def line = { attrs, body ->
        String url = grailsAttributes.getApplicationUri(request)
		out <<  "<img src=\"${url}chart?"

		out << baseChart(attrs)
		
		//chart line styles
		if (attrs.lineStyles != null) {
			out << '&chls='
			def lineText = ''
			for (line in attrs.lineStyles) {
				for (val in line) {
					lineText += (val + ',')
				}
				lineText = lineText.substring(0, lineText.length() - 1) + '|'
			}
			lineText = lineText.substring(0, lineText.length() - 1)
			out << lineText
		}
		//grid styles
		if (attrs.gridLines != null) {
			out << '&chg='
			out << attrs.gridLines
		}
		out << parseShapeRangeFill(attrs)		
		
		if (attrs.type != 'xy')
			out << '&cht=lc'
		else out << '&cht=lxy'
		
		out << '\" />'
	}
	
	// You must specify the type for a bar chart, there
	// are just too many combinations
	def bar = { attrs, body ->
        String url = grailsAttributes.getApplicationUri(request)
		out <<  "<img src=\"${url}chart?"

		out << baseChart(attrs)

		out << '&cht='+attrs.type
		
		out << '\" />'
	}
	
	def venn = { attrs, body ->
        String url = grailsAttributes.getApplicationUri(request)
		out <<  "<img src=\"${url}chart?"

		out << baseChart(attrs)

		out << '&cht=v'
		
		out << '\" />'
	}
	
	def scatter = { attrs, body ->
        String url = grailsAttributes.getApplicationUri(request)
		out <<  "<img src=\"${url}chart?"

		out << baseChart(attrs)
		out << '&cht=s'
		out << parseShapeRangeFill(attrs)
		//grid lines
		if (attrs.gridLines != null) {
			out << '&chg='
			out << attrs.gridLines
		}
		
		out << '\" />'
	}
}

